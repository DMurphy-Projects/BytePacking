public class PackingTest {

    public static void main(String[] args)
    {
        BinaryPacker binaryPacker = new BinaryPacker(10);

        binaryPacker.seek(0);
        binaryPacker.write(255, 8);
        binaryPacker.seek(3);
        binaryPacker.write(0, 2);

        binaryPacker.printBinary();
    }
}
