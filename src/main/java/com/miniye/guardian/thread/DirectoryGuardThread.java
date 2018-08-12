package com.miniye.guardian.thread;

import com.miniye.guardian.watchdogs.DirWatchdog;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DirectoryGuardThread extends DirWatchdog {
    Logger log = LoggerFactory.getLogger(FileGuardThread.class);

    public DirectoryGuardThread(String filename, Class clazz){
        super(filename, clazz);
    }
}
