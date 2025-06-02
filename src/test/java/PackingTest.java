import Helper.BinaryPacker;

public class PackingTest {

    public static void main(String[] args)
    {
        BinaryPacker binaryPacker = new BinaryPacker(10, 6);

        binaryPacker.seek(60);
        binaryPacker.write(37);
        binaryPacker.write(14);

        binaryPacker.printBinary();
        System.out.println("");

        binaryPacker.seek(0);
        while(binaryPacker.canRead()) {
            System.out.println(binaryPacker.read());
        }
    }
}
