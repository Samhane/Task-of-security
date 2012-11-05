import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.zip.Checksum;

public class ControlSumm extends ParentSecure {
    private String originalControlSumm;

    public ControlSumm(String file) {
        this.originalControlSumm = checkControlSumm(file);
    }

    @Override
    protected boolean checkFile(String file) {
        return checkControlSumm(file).equals(this.originalControlSumm);
    }

    private String checkControlSumm(String file) {
        byte[] buf = new byte[8000];
        int nLength = 0;
        Checksum cs = new MyCRC();

        try {
            FileInputStream inputFile = new FileInputStream(file);

            while ((nLength = inputFile.read(buf)) >= 0) {
                cs.update(buf, 0, nLength);
            }

            inputFile.close();
        } catch (FileNotFoundException notFound) {
            System.out.println("Что за файл такой - " + file);
            //скорее всего, в имени файла пробел, а с такими мы не работаем
        } catch (IOException e) {
            e.printStackTrace();
        }

        return Long.toString(cs.getValue());
    }
}
