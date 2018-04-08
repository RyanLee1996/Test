package pers.ryan.uav.tools;

import java.io.*;

public class FileUtil {
    /**
     *
     * @return resources文件夹路径
     */
    public static String getResourcesPath(){
        File directory = new File(".");
        String path = null;
        try {
            path = directory.getCanonicalPath();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return path+"\\src\\main\\resources\\";
    }

    /**
     *  写入信息到resources下，以.txt保存
     * @param planeId 无人机id
     * @param content 信息内容
     */
    public static void inputText(String planeId, String content){
        FileWriter fw = null;
        try {
            /*如果文件存在，则追加内容；如果文件不存在，则创建文件*/
            File f=new File(getResourcesPath()+planeId+".txt");
            fw = new FileWriter(f, true);
        } catch (IOException e) {
            e.printStackTrace();
        }
        PrintWriter pw = new PrintWriter(fw);
        pw.println(content);
        pw.flush();
        try {
            fw.flush();
            pw.close();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
