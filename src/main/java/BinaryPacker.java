public class BinaryPacker {

    long[] data;
    int position = 0;

    public BinaryPacker(int size)
    {
        data = new long[size];
    }

    public void seek(int position)
    {
        this.position = position;
    }

    public void write(long value, int digits)
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

    private void writeMaskedValue(long value, long mask, int index, int position)
    {
        long base = data[index];

        long positionValue = (base >> position) & mask;
        data[index] = base + ((value - positionValue) << position);
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
