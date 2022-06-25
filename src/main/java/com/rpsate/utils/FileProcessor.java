package com.rpsate.utils;

import org.springframework.boot.system.ApplicationHome;
import org.springframework.util.DigestUtils;

import java.io.File;
import java.nio.charset.StandardCharsets;

public class FileProcessor {

    public static Boolean createPath(String path) {
        File uploadPath = new File(path);
        if (!uploadPath.exists()) {
            uploadPath.mkdirs();
        }
        return uploadPath.exists();
    }

    public static String getExtension(String filename) {
        int index = filename.lastIndexOf(".");
        return filename.substring(index + 1);
    }

    public static String getSaveName(String filename) {
        return String.format("%s%s.%s",
                System.currentTimeMillis(),
                DigestUtils.md5DigestAsHex(filename.getBytes(StandardCharsets.UTF_8)),
                getExtension(filename));
    }
}
