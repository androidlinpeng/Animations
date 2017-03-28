package com.msgcopy.application;

import android.os.Environment;
import java.io.File;


/**
 * Created by daniel on 15-6-16.
 */
public class FileUtils {

    public static String getTempPath(){
        return Environment.getExternalStorageDirectory() +
                File.separator +
                "msgcopy" +
                File.separator +
                "com.msgcopy.application"+
                File.separator +
                "paletteview" +
                File.separator;
    }
    // 根据filename创建一个处于/sdcard/msgcopy/包名/tmp文件夹下的临时文件
    public static File createTmpFile(String fileName) {
        if (!(fileName.equals("")&&fileName.equals(null))) {
            try{
                if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
                    File file = new File(getTempPath() + fileName);
                    file.getParentFile().mkdirs();
                    if (file.exists()) {
                        file.delete();
                    }
                    file.createNewFile();
                    return file;
                }else{
                    return null;
                }
            }catch (Exception e){
                e.printStackTrace();
            }
            return null;
        } else {
            return null;
        }
    }
}
