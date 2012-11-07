import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Signature extends ParentSecure {
    private String originalSignature;
    private int offset;

    public Signature(String file, int offset) {
        this.offset = offset;
        this.originalSignature = checkSignature(file);
    }

    private String checkSignature(String file) {
        byte[] buf = new byte[800000];
        byte[] result = new byte[0];
        int nLength = 0;

        try {
            FileInputStream inputFile = new FileInputStream(file);
            nLength = inputFile.read(buf);
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] current = new byte[2001];
            if ((this.offset + 2000) > nLength) {
                this.offset = 0;
            }

            for (int i = this.offset, j = 0; i < (this.offset + 2000) && i < buf.length && j < current.length; i++, j++) {
                current[j] = buf[i];
            }
            result = md.digest(current);
            inputFile.close();
        } catch (FileNotFoundException notFound) {
            System.out.println("Что за файл такой - " + file);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        long answer = 1;
        for (byte aResult : result) {
            answer *= aResult;
        }

        return Long.toHexString(answer);
    }

    @Override
    protected boolean checkFile(String file) {
        return checkSignature(file).equals(this.originalSignature);
    }
}
