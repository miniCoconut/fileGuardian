package com.miniye.guardian;

import com.miniye.guardian.model.*;
import com.miniye.guardian.thread.DirectoryGuardThread;
import com.miniye.guardian.thread.FileGuardThread;

public class Service {
    /*
    * ================================================================
    * Guard service for property file or directory
    * ================================================================
    * */
    public static FileOperator guardPropertyFile(String absoluteFilePath) {
        FileGuardThread fg = new FileGuardThread(absoluteFilePath, PropertyFile.class);
        fg.start();
        return fg.getFileOperator();
    }

    public static DirOperator guardPropertyDir(String absoluteDirPath) {
        DirectoryGuardThread dg = new DirectoryGuardThread(absoluteDirPath, PropertyFile.class);
        dg.start();
        return dg.dirOperator;
    }

    public static FileOperator guardPropertyFile(String absoluteFilePath, long delay) {
        FileGuardThread fg = new FileGuardThread(absoluteFilePath, PropertyFile.class);
        fg.setDelay(delay);
        fg.start();
        return fg.getFileOperator();
    }

    public static DirOperator guardPropertyDir(String absoluteDirPath, long delay) {
        DirectoryGuardThread dg = new DirectoryGuardThread(absoluteDirPath, PropertyFile.class);
        dg.setDelay(delay);
        dg.start();
        return dg.dirOperator;
    }

    /*
     * ================================================================
     * Guard service for byte content file or directory
     * ================================================================
     * */
    public static FileOperator guardByteContentFile(String absoluteFilePath) {
        FileGuardThread fg = new FileGuardThread(absoluteFilePath, ByteContentFile.class);
        fg.start();
        return fg.getFileOperator();
    }

    public static DirOperator guardByteContentDir(String absoluteDirPath) {
        DirectoryGuardThread dg = new DirectoryGuardThread(absoluteDirPath, ByteContentFile.class);
        dg.start();
        return dg.dirOperator;
    }

    public static FileOperator guardByteContentFile(String absoluteFilePath, long delay) {
        FileGuardThread fg = new FileGuardThread(absoluteFilePath, ByteContentFile.class);
        fg.setDelay(delay);
        fg.start();
        return fg.getFileOperator();
    }

    public static DirOperator guardByteContentDir(String absoluteDirPath, long delay) {
        DirectoryGuardThread dg = new DirectoryGuardThread(absoluteDirPath, ByteContentFile.class);
        dg.setDelay(delay);
        dg.start();
        return dg.dirOperator;
    }

    /*
     * ================================================================
     * Guard service for jar file or directory
     * ================================================================
     * */
    public static FileOperator guardJarFile(String absoluteDirPath) {
        FileGuardThread fg = new FileGuardThread(absoluteDirPath, JarFile.class);
        fg.start();
        return fg.getFileOperator();
    }

    public static FileOperator guardJarFile(String absoluteDirPath, long delay) {
        FileGuardThread fg = new FileGuardThread(absoluteDirPath, JarFile.class);
        fg.setDelay(delay);
        fg.start();
        return fg.getFileOperator();
    }

    public static DirOperator guardJarDir(String absoluteDirPath) {
        DirectoryGuardThread dg = new DirectoryGuardThread(absoluteDirPath, JarFile.class);
        dg.start();
        return dg.dirOperator;
    }

    public static DirOperator guardJarDir(String absoluteDirPath, long delay) {
        DirectoryGuardThread dg = new DirectoryGuardThread(absoluteDirPath, JarFile.class);
        dg.setDelay(delay);
        dg.start();
        return dg.dirOperator;
    }


}
