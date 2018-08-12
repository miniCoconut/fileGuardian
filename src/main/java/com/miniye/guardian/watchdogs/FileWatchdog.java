package com.miniye.guardian.watchdogs;

import com.miniye.guardian.Constants;
import com.miniye.guardian.model.FileOperator;
import com.miniye.guardian.model.GenericFile;
import com.miniye.guardian.utils.ReflectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class FileWatchdog extends Thread {
    private Logger log = LoggerFactory.getLogger(FileWatchdog.class);

//    guard interval
    protected long delay = Constants.DEFAULT_DELAY;
//    guard thread will stop when interrupted
    boolean interrupted = false;

    private FileOperator fileOperator;

    public void setDelay(long delay) {
        this.delay = delay;
    }

    public FileOperator getFileOperator() {
        return fileOperator;
    }

    protected FileWatchdog(String absoluteFilePath, Class clazz) {
        super("FileWatchDog");
        this.setName(this.getClass().getName());
        this.setDaemon(true);
        this.fileOperator = new FileOperator(clazz, absoluteFilePath);

    }

    protected abstract void doOnChange();

    protected void checkAndConfigure() {
        boolean fileExists;
        try {
            fileExists = fileOperator.gFile.fileExists();
            if(fileExists) {
                if(fileOperator.gFile.fileChanged()) {
                    this.doOnChange();
                }
            } else {
                log.error("file:[" + fileOperator.gFile.getFilename() + "] does not exist.");
            }
        } catch (SecurityException var4) {
            log.error("Was not allowed to read check file existance, file:[" + fileOperator.gFile.getFilename() + "].");
            this.interrupted = true;
            return;
        }


    }

    public void run() {
        if(!fileOperator.isGuard()) {
            log.warn("The guardian for " +fileOperator.getFilename() + " is disabled!" );
        }
        for(; fileOperator.isGuard() && !this.interrupted; this.checkAndConfigure()) {
            try {
                Thread.sleep(this.delay);
            } catch (InterruptedException var2) {

            }
        }
    }
}

