import java.io.File;
import java.util.ArrayList;

public class ControlSumm {
    public void setOriginalControlSumm(String file) {
        this.originalControlSumm = checkControlSumm(file);
    }

    private String originalControlSumm;

    public ArrayList<String> findFile(String path) {
        String[] dirForSearch = new File(path).list();
        ArrayList<String> result = new ArrayList<String>();
        for (String currentPath : dirForSearch) {
            File currentFile = new File(currentPath);
            if (currentFile.isDirectory()) {
                result.addAll(findFile(currentPath));
            } else {
                if (checkControlSumm(currentPath).equals(this.originalControlSumm)) {
                    result.add(currentPath);
                }
            }
        }

        return result;
    }

    private String checkControlSumm(String file) {
        String result = "";
        return result;
    }
}
