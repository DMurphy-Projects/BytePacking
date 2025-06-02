package Helper;

public class BinaryPacker {

    long[] data;
    int position = 0, digits;

    public BinaryPacker(int size, int digits)
    {
        data = new long[size];
        this.digits = digits;
    }

    public void seek(int position)
    {
        this.position = position;
    }

    public void write(long value)
    {
        int index = position / 64, localPosition = position % 64;

        boolean split = (position + digits) > 64;
        if (split)
        {
            int firstSize = 64 - localPosition;
            long firstHalfMask = createMask(firstSize);

            long secondHalfMask = createMask(digits - firstSize);

            writeMaskedValue(value & firstHalfMask, firstHalfMask, index, localPosition);
            writeMaskedValue((value >> firstSize) & secondHalfMask, secondHalfMask,index+1, 0);
        }
        else
        {
            long mask = createMask(digits);
            writeMaskedValue(value & mask, mask, index, localPosition);
        }

        position += digits;
    }

    public long read()
    {
        int index = position / 64, localPosition = position % 64;
        long mask = createMask(digits);
        boolean split = (position + digits) > 64;

        position += digits;
        if (split)
        {
            int firstSize = 64 - localPosition;

            long firstHalfMask = createMask(firstSize);
            long secondHalfMask = createMask(digits - firstSize);

            long firstHalf = readMaskedValue(firstHalfMask, index, localPosition);
            long secondHalf = readMaskedValue(secondHalfMask, index + 1, 0) << firstSize;

            return firstHalf | secondHalf;
        }
        else
        {
            return readMaskedValue(mask, index, localPosition);
        }
    }

    private void writeMaskedValue(long value, long mask, int index, int position)
    {
        long positionValue = readMaskedValue(mask, index, position);
        data[index] += ((value - positionValue) << position);
    }

    private long readMaskedValue(long mask, int index, int position)
    {
        return (data[index] >> position) & mask;
    }

    private long createMask(int size)
    {
        if (size > 63)
        {
            return -1;
        }
        return (1L << size) - 1;
    }

    public void print()
    {
        for (long l: data)
        {
            System.out.println(l);
        }
    }

    public void printBinary()
    {
        for (long l: data)
        {
            printBinary(l);
        }
    }

    public void printBinary(long l)
    {
        System.out.println(String.format("%64s", Long.toBinaryString(l)).replace(' ', '0'));
    }
}
