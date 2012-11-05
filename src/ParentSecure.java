import java.io.File;
import java.util.ArrayList;

public abstract class ParentSecure {
    public ArrayList<String> findFile(String path, boolean inside) {
        String[] dirForSearch = new File(path).list();
        ArrayList<String> result = new ArrayList<String>();

        if (dirForSearch != null) {
            for (String current : dirForSearch) {
                String currentPath;
                if (!inside) {
                    currentPath = path.concat(current);
                } else {
                    currentPath = path.concat("/".concat(current));
                }
                File currentFile = new File(currentPath);
                if (currentFile.isDirectory()) {
                    result.addAll(findFile(currentPath, true));
                } else {
                    if (checkFile(currentPath)) {
                        result.add(currentPath);
                    }
                }
            }
        }

        return result;
    }

    protected abstract boolean checkFile(String currentPath);
}
