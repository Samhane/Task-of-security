import java.util.zip.CRC32;
import java.util.zip.Checksum;

public class MyCRC implements Checksum {
    private int value;
    private CRC32 crc;
    private long result;
    private int crc_table[];

    public MyCRC() {
        crc = new CRC32();
    }

    @Override
    public void update(int b) {
        crc.update(b);
    }

    @Override
    public void update(byte[] b, int off, int len) {
        crc.update(b, off, len);
    }

    @Override
    public long getValue() {
        return crc.getValue();
    }

    @Override
    public void reset() {
        this.value = 0;
    }
}
