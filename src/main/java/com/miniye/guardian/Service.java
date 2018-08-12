package com.miniye.guardian;

import com.miniye.guardian.filepojo.*;
import com.miniye.guardian.operator.DirOperator;
import com.miniye.guardian.operator.FileOperator;
import com.miniye.guardian.watchdogs.DirWatchdog;
import com.miniye.guardian.watchdogs.FileGuardThread;

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
        DirWatchdog.DirectoryGuardThread dg = new DirWatchdog.DirectoryGuardThread(absoluteDirPath, PropertyFile.class);
        dg.start();
        return dg.dirOperator;
    }

    public static FileOperator guardPropertyFile(String absoluteFilePath, long hotReloadInterval) {
        FileGuardThread fg = new FileGuardThread(absoluteFilePath, PropertyFile.class);
        fg.setDelay(hotReloadInterval);
        fg.start();
        return fg.getFileOperator();
    }

    public static DirOperator guardPropertyDir(String absoluteDirPath, long hotReloadInterval) {
        DirWatchdog.DirectoryGuardThread dg = new DirWatchdog.DirectoryGuardThread(absoluteDirPath, PropertyFile.class);
        dg.setDelay(hotReloadInterval);
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
        DirWatchdog.DirectoryGuardThread dg = new DirWatchdog.DirectoryGuardThread(absoluteDirPath, ByteContentFile.class);
        dg.start();
        return dg.dirOperator;
    }

    public static FileOperator guardByteContentFile(String absoluteFilePath, long hotReloadInterval) {
        FileGuardThread fg = new FileGuardThread(absoluteFilePath, ByteContentFile.class);
        fg.setDelay(hotReloadInterval);
        fg.start();
        return fg.getFileOperator();
    }

    public static DirOperator guardByteContentDir(String absoluteDirPath, long hotReloadInterval) {
        DirWatchdog.DirectoryGuardThread dg = new DirWatchdog.DirectoryGuardThread(absoluteDirPath, ByteContentFile.class);
        dg.setDelay(hotReloadInterval);
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

    public static FileOperator guardJarFile(String absoluteDirPath, long hotReloadInterval) {
        FileGuardThread fg = new FileGuardThread(absoluteDirPath, JarFile.class);
        fg.setDelay(hotReloadInterval);
        fg.start();
        return fg.getFileOperator();
    }

    public static DirOperator guardJarDir(String absoluteDirPath) {
        DirWatchdog.DirectoryGuardThread dg = new DirWatchdog.DirectoryGuardThread(absoluteDirPath, JarFile.class);
        dg.start();
        return dg.dirOperator;
    }

    public static DirOperator guardJarDir(String absoluteDirPath, long hotReloadInterval) {
        DirWatchdog.DirectoryGuardThread dg = new DirWatchdog.DirectoryGuardThread(absoluteDirPath, JarFile.class);
        dg.setDelay(hotReloadInterval);
        dg.start();
        return dg.dirOperator;
    }


}
