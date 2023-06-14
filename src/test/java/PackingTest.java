public class PackingTest {

    public static void main(String[] args)
    {
        BinaryPacker binaryPacker = new BinaryPacker(10);

        binaryPacker.seek(0);
        binaryPacker.write(-1, 64);

        binaryPacker.printBinary();
    }
}
