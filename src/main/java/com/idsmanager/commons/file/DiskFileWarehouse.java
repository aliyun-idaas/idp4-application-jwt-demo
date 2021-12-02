package com.idsmanager.commons.file;

import com.idsmanager.commons.utils.UUIDGenerator;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.Assert;
import org.springframework.util.FileCopyUtils;

import java.io.*;

/**
 * @author Shengzhao Li
 */

public class DiskFileWarehouse implements FileWarehouse, InitializingBean {

    private static Logger logger = LoggerFactory.getLogger(DiskFileWarehouse.class);
    //Disk root path
    private String diskRootPath = "../upload/";

    public DiskFileWarehouse() {
    }

    private String write(InputStream inputStream) {
        String fileName = UUIDGenerator.generate();
        String fullPath = diskRootPath + fileName;
        try {
            FileCopyUtils.copy(inputStream, new FileOutputStream(fullPath));
            logger.debug("Write data to file [{}] finished.", fullPath);
        } catch (IOException e) {
            throw new IllegalStateException("Write file to disk wrong,the file path: " + fullPath, e);
        }
        return fileName;
    }

    @Override
    public String write(byte[] data) {
        ByteArrayInputStream bais = new ByteArrayInputStream(data);
        return write(bais);
    }

    @Override
    public byte[] read(String path) {
        File file = new File(diskRootPath + path);
        if (!file.exists()) {
            return null;
        }
        try {
            logger.debug("Read data from file [{}].", file.getAbsolutePath());
            return FileCopyUtils.copyToByteArray(file);
        } catch (IOException e) {
            throw new IllegalStateException("Read file from disk wrong,the file path: " + file.getAbsolutePath(), e);
        }
    }

    @Override
    public void delete(String path) {
        File file = new File(diskRootPath + path);
        try {
            if (file.exists()) {
                logger.debug("Delete file [{}].", file.getAbsolutePath());
                FileUtils.forceDelete(file);
            }
        } catch (IOException e) {
            throw new IllegalStateException("Delete file from disk wrong,the file path: " + file.getAbsolutePath(), e);
        }
    }

    @Override
    public File file(String path) {
        return new File(diskRootPath + path);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        Assert.notNull(diskRootPath, "diskRootPath is required");
        //Check and initial directory
        checkDiskRootPath();
    }

    private void checkDiskRootPath() {
        File file = new File(diskRootPath);
        if (!file.exists()) {
            final boolean mkSuccess = file.mkdirs();
            if (!mkSuccess) {
                throw new IllegalStateException("Create the directory by diskRootPath[" + diskRootPath + "] failed, please check.");
            }
        }
        if (!file.isDirectory()) {
            throw new IllegalStateException("The diskRootPath[" + diskRootPath + "] should be a directory, but it is not");
        }
    }

    public void setDiskRootPath(String diskRootPath) {
        this.diskRootPath = diskRootPath;
    }
}