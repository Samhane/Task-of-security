import java.util.zip.Checksum;

public class MyCRC implements Checksum {
    private int value;

    @Override
    public void update(int b) {
    }

    @Override
    public void update(byte[] b, int off, int len) {
    }

    @Override
    public long getValue() {
        return value;
    }

    @Override
    public void reset() {
        value = 0xFFFFFFFF;
    }
}
