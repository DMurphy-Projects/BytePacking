import Helper.BinaryPacker;

public class PackingTest {

    public static void main(String[] args)
    {
        BinaryPacker binaryPacker = new BinaryPacker(10, 8);

        binaryPacker.seek(54);
        binaryPacker.write(37);
        binaryPacker.write(14);

        binaryPacker.printBinary();
        System.out.println("");

        binaryPacker.seek(54);
        System.out.println(binaryPacker.read());
        System.out.println(binaryPacker.read());
    }
}
