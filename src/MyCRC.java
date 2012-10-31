import java.util.zip.CRC32;
import java.util.zip.Checksum;

public class MyCRC implements Checksum {
    private int value;
    private CRC32 crc;
    private long result;
    private int crc_table[];

    public MyCRC() {
        this.crc_table = new int[256];
        makeCRCTable();
        printCRCTable();
        reset();
        crc = new CRC32();
    }

    @Override
    public void update(int b) {
        crc.update(b);
    }

    @Override
    public void update(byte[] b, int off, int len) {
        int c = ~this.value;
        while (--len >= 0) {
            c = crc_table[(c ^ b[off++]) & 0xff] ^ (c >>> 8);
        }
        this.value = ~c;

        crc.update(b, off, len);
    }

    @Override
    public long getValue() {
        this.result = (long) this.value & 0xffffffffL;
        return crc.getValue();
    }

    @Override
    public void reset() {
        this.value = 0;
    }

    private void makeCRCTable() {
        for (int i = 0; i < 256; i++) {
            int c = i;
            for (int j = 8; --j >= 0; ) {
                if ((c & 1) != 0)
                    c = 0xEDB88320 ^ (c >>> 1);
                else
                    c = c >>> 1;
            }
            crc_table[i] = c;
        }
    }

    public void printCRCTable() {
        int count = 0;
        for (int printcrc : crc_table) {
            System.out.print(Integer.toHexString(printcrc) + " ");
        }
        System.out.println();
    }
}
