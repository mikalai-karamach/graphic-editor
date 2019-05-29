package by.bsuir.karamach.util;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public final class FigureClassesReader {
    private FigureClassesReader() {
    }

    public static String readAllFigures(String directory) {

        File folder = new File(directory);
        File[] listOfFiles = folder.listFiles();

        List<String> resultList = new ArrayList<>();
        for (File file : listOfFiles) {
            if (file.isFile()) {
                resultList.add(file.getName());
            }
        }

        StringBuilder stringBuilder = new StringBuilder("Available classes: \n");
        for (String name : resultList) {
            stringBuilder.append(name).append("\n");
        }

        return stringBuilder.toString();
    }
}
