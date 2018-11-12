import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Pattern;

public abstract class FolderTool {
    private static String startPath = "";

    public static final String WARN_LOG_PATH = "autoScript-warning.log";
    private static final String FOLDER_INFO_PATH = "autoScript-folderInfo.log";
    public static final String SUCCESS_LOG_PATH = "autoScript-success.log";
    public static final String DEBUG_LOG_PATH = "autoScript-debug.log";
    private static final String SUBFOLDER_LOG_PATH = "autoScript-subfolder.log";

    private static final String[] PICTURE_EXTENSION = {"jpg","png","jpeg","gif","bmp","svg","psd"};
    private static final String[] VIDEO_EXTENSION = {"wmv","asf","asx","rm","rmvb","mpg","mpeg","mpe","3gp","mov","mp4",
            "m4v","avi","dat","mkv","flv","vob","f4v","ts","mts"};
    private static final String PDF_EXTENSION = "pdf";

    private static LinkedHashSet<String> otherExtensionSet = new LinkedHashSet<>();

    public static String getStartPath() {
        return startPath;
    }
    public static void setStartPath(String startPath) {
        FolderTool.startPath = startPath;
    }
    private FolderTool() {
    }
    public FolderTool(String path) {
        startPath = path;
    }

    public abstract  String recombination(String fileName);
    public  void folderNameChange(boolean isPrefix ,String regex){
        folderNameChange(startPath,isPrefix,regex);
    }
    public  void folderNameChange(boolean isPrefix){
        folderNameChange(startPath,isPrefix,".*?");
    }
    public  void folderNameChange(){
        folderNameChange(startPath,true,".*?");
    }
    public  void folderNameChange(String path,boolean  isPrefix ,String regex){
        startPath = path;
        List<String> folderList = getFolderList(startPath);
        if (folderList == null){
            return;
        }
        for (String folderPath : folderList){
            if (!Pattern.compile(regex)
                    .matcher(folderPath)
                    .matches()){
                continue;
            }
            Map<String,Long> infoMap = getFolderInfo(folderPath);
            if (infoMap != null){
                String fix = getInfoFix(infoMap);
                String relativePath = folderPath.replace(startPath + "\\","");
                logFileWriter(fix + " " +infoMap.get("otherCount")+"/"+
                        new DecimalFormat("0").format(infoMap.get("realBytesCount")*1.0/infoMap.get("bytesCount")*100)
                        +"% " + relativePath,"FOLDER-INFO",FOLDER_INFO_PATH);
                File oldFile = new File(folderPath);
                String newFile = "";
                if(isPrefix){
                    newFile = fix + " " + this.stringRecombination(relativePath);
                }else {
                    newFile = this.stringRecombination(relativePath) + " " + fix;
                }
                if (!relativePath.equals(newFile)){
                    if (oldFile.renameTo(new File(startPath + "\\" +newFile))){
                        logFileWriter("【"+relativePath + "】 CHANGE TO 【" + newFile + "】","SUCCESS",SUCCESS_LOG_PATH);
                    }else {
                        logFileWriter(relativePath,"FAILURE",DEBUG_LOG_PATH);
                    }
                }
            }
        }
        for (String suffix : otherExtensionSet){
            logFileWriter(suffix,"DEBUG-SUFFIX",DEBUG_LOG_PATH);
        }
        System.out.println("操作结束");
    }
    private  String stringRecombination(String str){
        String newStr = str;
        String temp = replaceBrace(str);
        while (!newStr.equals(temp)){
            newStr = temp;
            temp = replaceBrace(temp);
        }
        newStr = newStr.trim();
        return this.recombination(newStr);
    }
    private static String replaceBrace(String str){
        String[] startBraceArray = {"[","(","（","{","｛","【","《"};
        String[] endBraceArray = {"]",")","）","}","｝","】","》"};
        int startIndex = -1;
        int endIndex = -1;

        for (String  startBrace : startBraceArray){
            startIndex = str.indexOf(startBrace);
            if (startIndex>=0){
                break;
            }
        }
        for (String  endBrace : endBraceArray){
            endIndex = str.indexOf(endBrace);
            if (endIndex>=0){
                break;
            }
        }
        if (startIndex >= 0 && endIndex >= 0) {
            StringBuilder sb = new StringBuilder(str);
            return sb.replace(startIndex, endIndex+1,"").toString();
        }
        return str;
    }
    private static Map<String,Long> getFolderInfo(String path){
        Map<String,Long> countMap = new HashMap<>();
        File file = new File(path);
        File[] fileList = file.listFiles();
        if (fileList == null){
            return null;
        }
        long videoCount = 0L;
        long pictureCount = 0L;
        long pdfCount = 0L;
        long otherCount = 0L;
        long bytesCount = 0L;
        long realBytesCount = 0L;
        long directoryCount = 0L;
        for(File subFile : fileList){
            boolean flag = false;
            if(subFile.isFile()){
                long bytes = subFile.length();
                bytesCount+=bytes;
                String fileName = subFile.getName();
                String suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
                for (String pictureSuffix : PICTURE_EXTENSION){
                    if(ignoreCaseEquals(pictureSuffix,suffix)){
                        pictureCount++;
                        flag = true;
                        break;
                    }
                }
                for (String videoSuffix : VIDEO_EXTENSION){
                    if(ignoreCaseEquals(videoSuffix,suffix)){
                        videoCount++;
                        flag = true;
                        break;
                    }
                }
                if(ignoreCaseEquals(PDF_EXTENSION,suffix)){
                    pdfCount++;
                    flag = true;
                }
                if (!flag){
                    otherCount++;
                    otherExtensionSet.add(suffix);
                    logFileWriter(getRelativePath(subFile),"WARN",WARN_LOG_PATH);
                }else {
                    realBytesCount+=bytes;
                }
            }else if(subFile.isDirectory()){
                directoryCount++;
                logFileWriter(getRelativePath(subFile),"SUBFOLDER",SUBFOLDER_LOG_PATH);
                Map<String,Long> tempMap = getFolderInfo(subFile.getPath());
                if (tempMap != null){
                    videoCount+=tempMap.get("videoCount");
                    pictureCount+=tempMap.get("pictureCount");
                    bytesCount+=tempMap.get("bytesCount");
                    pdfCount+=tempMap.get("pdfCount");
                    realBytesCount+=tempMap.get("realBytesCount");
                    otherCount+=tempMap.get("otherCount");
                    directoryCount+=tempMap.get("directoryCount");
                }
            }
        }
        countMap.put("videoCount",videoCount);
        countMap.put("pictureCount",pictureCount);
        countMap.put("bytesCount",bytesCount);
        countMap.put("pdfCount",pdfCount);
        countMap.put("realBytesCount",realBytesCount);
        countMap.put("otherCount",otherCount);
        countMap.put("directoryCount",directoryCount);
        return countMap;
    }
    private static List<String> getFolderList(String folderPath){
        List<String> folderList = new ArrayList<>();
        File file = new File(folderPath);
        File[] fileList = file.listFiles();
        if (fileList == null){
            return null;
        }
        for(File subFile : fileList){
            if(subFile.isFile()){
                continue;
            }else if(subFile.isDirectory()){
                folderList.add(subFile.getPath());
            }
        }
        return folderList;
    }
    private static String getFileSize(long bytes){
        double kilobytes = 0;
        double megabytes = 0;
        double gigabytes = 0;
        if (bytes>1000){
            kilobytes = (bytes / 1024);
        }
        if (kilobytes>1000){
            megabytes = (kilobytes / 1024);
        }
        if (megabytes>1000){
            gigabytes  = (megabytes / 1024);
        }
        if (gigabytes>0){
            return String.format("%.2f", gigabytes) + "GB";
        }else if (megabytes>0){
            return String.format("%.0f", megabytes) + "MB";
        }else if (kilobytes>0){
            return String.format("%.0f", kilobytes) + "KB";
        }else {
            return bytes + "B";
        }
    }
    private static boolean ignoreCaseEquals(String str1,String str2){
        return str1 == null ? str2 == null :str1.equalsIgnoreCase(str2);
    }
    public static void logFileWriter(String content,String infoType,String logPath){
        try {
            File file = new File(startPath+"\\"+logPath);
            String data = getNowTime() + " " + infoType + ": " + content + ";\r\n";
            if (!file.exists()) {
                file.createNewFile();
            }

            FileWriter fileWriter = new FileWriter(file, true);
            BufferedWriter bufferWriter = new BufferedWriter(fileWriter);
            bufferWriter.write(data);
            bufferWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private static String getNowTime(){
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        return df.format(new Date());
    }
    private static String getInfoFix(Map<String,Long> dataMap){
        if (dataMap == null){
            return null;
        }
        String fix = "[";
        long videoCount = dataMap.get("videoCount");
        long pictureCount = dataMap.get("pictureCount");
        long bytesCount= dataMap.get("realBytesCount");
        long pdfCount = dataMap.get("pdfCount");
        if (pictureCount > 0){
            fix += pictureCount;
            fix += "P";
        }
        if (videoCount > 0){
            fix += videoCount;
            fix += "V";
        }
        if (pdfCount > 0){
            fix += pdfCount;
            fix += "PDF";
        }
        if (bytesCount > 0){
            if (!fix.equals("[")){
                fix += "-";
            }
            fix += getFileSize(bytesCount);
            fix += "]";
        }
        return fix;
    }
    private static String getRelativePath(File file){
        return file.getPath().replace(startPath+"\\","");
    }
    public static void deleteFile(String filePath,String[] deleteFileRule) {
        File file = new File(filePath);
        File[] fileList = file.listFiles();
        if (fileList == null){
            return ;
        }
        for(File subFile : fileList){
            if(subFile.isFile()){
                for (String deleteRule : deleteFileRule) {
                    if (subFile.getName().equals(deleteRule) || ignoreCaseEquals(deleteRule, subFile.getName()
                            .substring(subFile.getName().lastIndexOf(".") + 1))) {
                        if (!subFile.delete()) {
                            System.out.println("删除失败：" + subFile.getPath());
                        } else {
                            System.out.println("删除成功：" + subFile.getPath());
                        }
                        break;
                    }
                }
            }else if(subFile.isDirectory()){
                deleteFile(subFile.getPath(),deleteFileRule);
            }
        }
    }
    public void deleteFile(String[] deleteFileRule){
        deleteFile(startPath,deleteFileRule);
    }
    public static void deleteLog(){
        deleteFile(startPath,new String[]{"log"});
    }
}
