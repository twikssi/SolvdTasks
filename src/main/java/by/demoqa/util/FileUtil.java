package by.demoqa.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileUtil {
    public static Logger log = LoggerFactory.getLogger(FileUtil.class);

    public boolean isFilePresent(Path path) {
        return Files.exists(path);
    }

    public void deleteFileIfExists(Path path) {
        if (isFilePresent(path)) {
            try {
                Files.delete(path);
                log.info("File '" + path.getFileName() + "' deleted");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
