package io.spring.snippets.service.utils;

import io.spring.snippets.CustomException.*;
import org.springframework.util.StringUtils;

import java.util.Objects;

public class Utils {

    public static String getFileExtension(String filename) throws FileWithoutExtensionException {
        var fileExtension = StringUtils.getFilenameExtension(filename);
        if(Objects.isNull(fileExtension)) {
            throw new FileWithoutExtensionException("File Without Extension");
        }
        return fileExtension;
    }
}
