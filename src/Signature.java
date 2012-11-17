import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class Signature extends ParentSecure {
    private String originalSignature;
    private int offset;
    private int sizeToRead;

    public Signature(String file, int offset) {
        this.offset = offset;
        this.sizeToRead = 32;
        this.originalSignature = checkSignature(file);
    }

    private String checkSignature(String file) {
        byte[] buf = new byte[this.sizeToRead];
        byte[] result = new byte[0];

        try {
            RandomAccessFile inputFile = new RandomAccessFile(file, "r");
            inputFile.seek(this.offset);
            inputFile.read(buf, 0, buf.length);
            MessageDigest md = MessageDigest.getInstance("MD5");
            result = md.digest(buf);
            inputFile.close();
        } catch (FileNotFoundException e) {
            System.out.println("Что за файл такой - " + file);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return Arrays.toString(result);
    }

    @Override
    protected boolean checkFile(String file) {
        return checkSignature(file).equals(this.originalSignature);
    }
}
