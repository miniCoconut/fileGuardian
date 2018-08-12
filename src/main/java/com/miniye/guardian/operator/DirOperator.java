package com.miniye.guardian.operator;

import com.miniye.guardian.filepojo.GenericFile;
import com.miniye.guardian.utils.ReflectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class DirOperator {

    private Logger log = LoggerFactory.getLogger(DirOperator.class);

    //    A map contains all files in current directory
    //    key:filename value:file instance
    private ConcurrentHashMap<String, GenericFile> genericFilesMap = new ConcurrentHashMap<String, GenericFile>();

    protected String absoluteDirPath;
    private Class clazz;
    boolean guard = true;

    public DirOperator(String absoluteDirPath, Class clazz) {
        this.absoluteDirPath = absoluteDirPath;
        this.clazz = clazz;
    }

    public String getAbsoluteDirPath() {
        return absoluteDirPath;
    }

    public Class getClazz() {
        return clazz;
    }

    public boolean isGuard() {
        return guard;
    }

    public void setGuard(boolean guard) {
        this.guard = guard;
    }

    public boolean hasFile() {
        return !genericFilesMap.isEmpty();
    }

    public int countFiles() {
        return genericFilesMap.size();
    }

    public boolean fileExists(String filename) {
        return genericFilesMap.containsKey(filename);
    }


    public Object getFileContent(String filename) {
        return genericFilesMap.get(filename).getContent();
    }

    public long getFileLastModified(String filename) {
        return genericFilesMap.get(filename).getLastModif();
    }

    //list all loaded files' name instead of those in this dirPath
    public List<String> listFiles() {
        Set<String> filenameSet = genericFilesMap.keySet();
        List<String> filenameList = new ArrayList<String>(filenameSet);

        return filenameList;
    }

    public File[] getFileList() {
        File dir = new File(absoluteDirPath);
        File[] fileList = null;
        if(dir.exists() && dir.isDirectory()) {
            fileList = dir.listFiles();
        }
        return fileList;
    }

    public void InitFilesMap() {
        File[] fileList =getFileList();
        if(fileList != null) {
//          init Files Map
            for (File file : fileList) {
                if (file.isFile()) {
                    try {
                        addFilesMap(file);
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
        }
    }

    public void addFilesMap(File file) throws Exception {

        String filename = file.getName();
        String filePath = file.getAbsolutePath();

        Class[] parameterTypes = {String.class};
        Object[] parameters = {filePath};
        GenericFile gFile = (GenericFile) ReflectionUtils.newInstance4ConstructorWithArgs(clazz, parameterTypes, parameters);
        gFile.loadFile();
        genericFilesMap.put(filename, gFile);
        log.info(filePath + "is added and guarded");
    }

    public void updateFilesMap(File file) throws Exception {
        String filename = file.getName();
        GenericFile gFile = genericFilesMap.get(filename);
        if(gFile != null && gFile.fileChanged()) {
            System.out.println(filename + " changed!");
            genericFilesMap.remove(filename);
            gFile.loadFile();
            genericFilesMap.put(filename, gFile);
            log.info("reload modified file: " + file.getAbsolutePath());
        }
    }

    //    remove deleted files
    public void removeFilesMap(File[] fileList) {
        List<String> filenameList = getAllFilename(fileList);
        Set<String> oldFilenameSet = genericFilesMap.keySet();
        for (String filename : oldFilenameSet) {
            if(!filenameList.contains(filename)) {
                genericFilesMap.remove(filename);
                log.info("remove deleted file: " + filename);
            }
        }
    }


    public List<String> getAllFilename(File[] fileList) {
        List<String> filenameList = new ArrayList<String>();
        for(File file : fileList) {
            filenameList.add(file.getName());
        }

        return filenameList;
    }
}
