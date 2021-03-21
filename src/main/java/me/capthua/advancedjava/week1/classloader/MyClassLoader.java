package me.capthua.advancedjava.week1.classloader;

import java.io.*;

public class MyClassLoader extends ClassLoader{

    private static final String FILE_PATH="D:\\docs\\JavaProjects\\advanced-java\\src\\main\\java\\me\\capthua\\advancedjava\\week1\\classloader\\Hello.xlass";

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        new MyClassLoader().findClass("Hello").newInstance();
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        byte[] bytes=handleBytes(readFile(FILE_PATH));
        return defineClass(name,bytes,0,bytes.length);
    }

    private byte[] readFile(String file){
        File byteCodeFile=new File(file);
        int length = (int) byteCodeFile.length();
        ByteArrayOutputStream baos=new ByteArrayOutputStream();
        try {
            FileInputStream fis= new FileInputStream(byteCodeFile);
            byte[] buffer = new byte[1024*4];
            int len = -1;
            while((len = fis.read(buffer)) != -1) {
                baos.write(buffer,0,len);
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("读取文件异常");
        }
        return baos.toByteArray();
    }

    private byte[] handleBytes(byte[] bytes){
        int[] byteArray=new int[bytes.length];
        for(int i=0;i<byteArray.length;i++){
            byteArray[i]=bytes[i];
        }
        for(int i=0;i<byteArray.length;i++){
            bytes[i]=(byte) (255-byteArray[i]);
        }
        return bytes;
    }
}
