package com.miniye.guardian.watchdogs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InterruptedIOException;

public class FileGuardThread extends FileWatchdog {
    static Logger log = LoggerFactory.getLogger(FileGuardThread.class);


    public FileGuardThread(String filename, Class clazz){
        super(filename, clazz);
    }

    @Override
    public void doOnChange() {
        try {
            getFileOperator().gFile.loadFile();
            log.info("reload modified file: " + getFileOperator().getAbsoluteFilePath());
        } catch (Exception e) {
            if( e instanceof InterruptedException || e instanceof InterruptedIOException) {
                Thread.currentThread().interrupt();
            }
            log.error("Could not load property file [" + getFileOperator().gFile.getFilename() + "]." + e.toString());
        }
    }

}
