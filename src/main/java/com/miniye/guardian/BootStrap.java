package com.miniye.guardian;

import com.miniye.guardian.model.ClassBean;
import com.miniye.guardian.thread.DirectoryGuardThread;
import com.miniye.guardian.thread.FileGuardThread;

import java.io.UnsupportedEncodingException;
import java.util.Map;

public class BootStrap {

    public static void main(String[] args) {

        try {
            String jarPath = "/Users/zhangtengfei5/Documents/workspace/fileGuardian/target/";
            DirectoryGuardThread fg = Service.guardJarDir(jarPath, 3000);

            Map<String, ClassBean> classMap = (Map<String, ClassBean>) fg.getFileContent("file-guardian-1.0-SNAPSHOT.jar");
            for(Map.Entry<String, ClassBean> entry : classMap.entrySet()) {
                System.out.println(entry.getKey());
            }

            ClassBean bean = classMap.get("PropertyFile");
            System.out.println(bean);
            Class[] parameterTypes = {String.class};
            Object[] parameter = {Constants.DEFAULT_DIR_PATH + "conf.properties"};
            bean.newInstance4ConstructorWithArgs(parameterTypes, parameter);
            System.out.println(bean.invokeNonStaticMethod("getContent", null));


        } catch (Exception e) {
            e.printStackTrace();
        }
        System.exit(-1);
        String propertyFilename = Constants.DEFAULT_DIR_PATH + "conf.properties";
        FileGuardThread fg = Service.guardByteContentFile(propertyFilename, 10000);

        String content = null;
        try {
            content = new String((byte[]) fg.getFileContent(), "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        System.out.println(content);
//        DirectoryGuardThread dg = Service.guardPropertyDir(Constants.DEFAULT_DIR_PATH, 5000);
//        System.out.println(dg.listFiles());
        while (true) {
            try {
                Thread.sleep(3000);
//                List<String> fileList = dg.listFiles();
//                for(String filename : fileList){
//                    System.out.println(dg.getFileLastModified(filename));
//                }


            } catch (InterruptedException e) {
                System.out.println(e);
            }
        }


    }
}

