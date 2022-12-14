package com.minhlv.socialappapi.utils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Calendar;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.web.multipart.MultipartFile;

import com.minhlv.socialappapi.exception.CustomException;

public class FileUploadUtil {
    private FileUploadUtil() {
    }

    public static String saveFile(String uploadDir, String fileName, MultipartFile multipartFile) throws IOException {
        Path uploadPath = Paths.get(uploadDir);
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }
        try (InputStream inputStream = multipartFile.getInputStream()) {
            Path filePath = uploadPath.resolve(fileName);
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
            return filePath.toAbsolutePath().toString();
        } catch (IOException ioe) {
            throw new IOException("Could not save image file: " + fileName, ioe);
        }
    }

    public static Resource getFile(String filePath) {
        try {
            Path path = Paths.get(filePath).toAbsolutePath().normalize();
            Resource resource = new UrlResource(path.toUri());
            if (resource.exists()) {
                return resource;
            } else {
                throw new CustomException("File not found " + filePath, HttpStatus.NOT_FOUND);
            }
        } catch (MalformedURLException ex) {
            throw new CustomException("File not found " + filePath, HttpStatus.NOT_FOUND);
        }

    }

    public static byte[] compressImage(byte[] data) {

        Deflater deflater = new Deflater();
        deflater.setLevel(Deflater.BEST_COMPRESSION);
        deflater.setInput(data);
        deflater.finish();

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] tmp = new byte[4 * 1024];
        while (!deflater.finished()) {
            int size = deflater.deflate(tmp);
            outputStream.write(tmp, 0, size);
        }
        try {
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return outputStream.toByteArray();
    }

    public static byte[] decompressImage(byte[] data) {
        Inflater inflater = new Inflater();
        inflater.setInput(data);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] tmp = new byte[4 * 1024];
        try {
            while (!inflater.finished()) {
                int count = inflater.inflate(tmp);
                outputStream.write(tmp, 0, count);
            }
            outputStream.close();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return outputStream.toByteArray();
    }

    public static String createFilePath() {
        String storePathTemp = "/photos";
        String storeName = "uploads";
        Calendar currentYear = Calendar.getInstance();
        return storePathTemp + File.separator + storeName + File.separator + currentYear.get(Calendar.YEAR)
                + File.separator + (currentYear.get(Calendar.MONTH) + 1) + File.separator;
    }

    public static String replaceFileName(String fileName) {
        String[] arrayNameFile = fileName.split("\\.");
        fileName = fileName.replace(arrayNameFile[0], IDGenerator.get() + "_" + System.currentTimeMillis());
        return fileName;
    }
}
