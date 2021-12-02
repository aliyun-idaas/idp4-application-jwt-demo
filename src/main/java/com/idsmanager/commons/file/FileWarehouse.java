package com.idsmanager.commons.file;

import java.io.File;

/**
 * @author Shengzhao Li
 */

public interface FileWarehouse {

    // return file path
    String write(byte[] data);

    byte[] read(String path);

    void delete(String path);

    //The file of path
    File file(String path);

}