package com.miniye.guardian;

import com.miniye.guardian.operator.ClassOperator;
import com.miniye.guardian.operator.DirOperator;
import com.miniye.guardian.operator.FileOperator;

import java.io.UnsupportedEncodingException;
import java.util.Map;
import java.util.Properties;

public class Examples {

    public static void main(String[] args) {


        while (true) {
            try {
                Thread.sleep(3000);

            } catch (InterruptedException e) {
                System.out.println(e);
            }
        }
    }

    /*
    * 热加载属性文件
    * 启动守护线程会返回一个文件操作bean，用于获取文件内容、路径、修改时间等信息
    * */
    public static void guardPropertyFile() {
        String propertyFilePath = "XXXX";
        FileOperator fileOperator = Service.guardPropertyFile(propertyFilePath);
        Properties props = (Properties) fileOperator.getFileContent();
        System.out.println(props);
    }


    public static void guardContentFile() {
        String contentFilename = "";
        FileOperator fileOperator = Service.guardByteContentFile(contentFilename, 10000);

        String content = null;
        try {
            content = new String((byte[]) fileOperator.getFileContent(), "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        System.out.println(content);
    }

    /*
    * 热加载jar包文件夹
    * 启动守护线程会加载文件夹下所有jar包，并返回一个文件夹操作bean
    * 可以通过jar包名获取jar包内容Map，key：类名 value：类操作bean
    * */
    public static void guardJarDirPackage() {
        try {
            //加载文件路径下所有jar包
            String jarDirPath = "/your/jar/dir/path";
            DirOperator dirOperator = Service.guardJarDir(jarDirPath, 3000);

            //获取指定jar包内容
            Map<String, ClassOperator> classMap = (Map<String, ClassOperator>) dirOperator.getFileContent("file-guardian-1.0-SNAPSHOT.jar");
            for(Map.Entry<String, ClassOperator> entry : classMap.entrySet()) {
                System.out.println(entry.getKey());
            }

            //根据类名获取类bean
            ClassOperator bean = classMap.get("PropertyFile");
            System.out.println(bean);
            //实例化bean
            Class[] parameterTypes = {String.class};
            Object[] parameter = {Constants.DEFAULT_DIR_PATH + "conf.properties"};
            bean.newInstance4ConstructorWithArgs(parameterTypes, parameter);
            //调用bean中的函数
            System.out.println(bean.invokeNonStaticMethod("getContent", null));


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

