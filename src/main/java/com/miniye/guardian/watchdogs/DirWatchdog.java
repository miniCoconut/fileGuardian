package com.miniye.guardian.watchdogs;

import com.miniye.guardian.Constants;
import com.miniye.guardian.model.DirOperator;
import com.miniye.guardian.model.GenericFile;
import com.miniye.guardian.utils.ReflectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class DirWatchdog extends Thread {
    private Logger log = LoggerFactory.getLogger(DirWatchdog.class);

    public DirOperator dirOperator;
    protected long delay = Constants.DEFAULT_DELAY;
    boolean interrupted = false;

    protected DirWatchdog(String absoluteDirPath, Class clazz) {
        super("DirWatchDog");
        this.dirOperator = new DirOperator(absoluteDirPath, clazz);
        
        this.dirOperator.InitFilesMap();
        this.setDaemon(true);
    }

    public void setDelay(long delay) {
        this.delay = delay;
    }

    protected void checkAndConfigure(){
        File[] fileList = dirOperator.getFileList();
        if(fileList != null) {
//          add new files and update changed files
            for(File file : fileList) {
                if(file.isFile()) {
                    String filename = file.getName();

                    try {
                        if(dirOperator.fileExists(filename)) {
                            dirOperator.updateFilesMap(file);
                        } else {
                            dirOperator.addFilesMap(file);
                        }
                    } catch (Exception e) {
                        if( e instanceof InterruptedException || e instanceof InterruptedIOException) {
                            Thread.currentThread().interrupt();
                        }
                        if(e instanceof IOException)
                            log.error("fail to load GenericFile. " + e);
                        log.error(e.toString());
                    }

                }
            }
//            remove deleted file
            dirOperator.removeFilesMap(fileList);
        }
    }
    


    public void run() {
        if(!dirOperator.isGuard()) {
            log.warn("The guardian for " + dirOperator.getAbsoluteDirPath() + " is disabled!" );
        }
        for(; dirOperator.isGuard() && !this.interrupted; this.checkAndConfigure()) {
            try {
                Thread.sleep(this.delay);
            } catch (InterruptedException var2) {

            }
        }
    }
    



}
