package utils;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class FileReader {

    private String path;

    public FileReader(String path) {
        this.path = path;
    }

    public List<String> getFileWithUtil() {
        String result = "";
        ClassLoader classLoader = getClass().getClassLoader();
        try {
            result = IOUtils.toString(classLoader.getResourceAsStream(this.path));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return Arrays.stream(result.split("#")).collect(Collectors.toList());
    }
}
