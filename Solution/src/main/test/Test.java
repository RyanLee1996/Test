import java.io.BufferedWriter;
import java.io.File;

import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Test {
    private static final String START_PATH = "D:\\Test";
    private static final String LOG_FILE_PATH = "autoInfo.log";
    private static final String[] PICTURE_EXTENSION = {"jpg","png","jpeg","gif","bmp","svg","psd"};
    private static final String[] VIDEO_EXTENSION = {"wmv","asf","asx","rm","rmvb","mpg","mpeg","mpe","3gp","mov","mp4",
            "m4v","avi","dat","mkv","flv","vob","f4v","ts","mts"};
    private static final String PDF_EXTENSION = "pdf";
    private static LinkedHashSet<String> otherExtensionSet = new LinkedHashSet<>();
    public static void main(String[] args){
        List<String> folderList = getFolderList(START_PATH);
        if (folderList == null){
            return;
        }
        for (String folderPath : folderList){
            Map<String,Long> infoMap = getFolderInfo(folderPath);
            if (infoMap != null){
                String fix = getInfoFix(infoMap);
                String relativePath = folderPath.replace(START_PATH + "\\","");
                logFileWriter(fix + " " + relativePath,"  FOLDER-INFO");
                File oldFile = new File(folderPath);
                String newFile = fix + " " + relativePath;
                if (oldFile.renameTo(new File(START_PATH + "\\" +newFile))){
                    logFileWriter("【"+relativePath + "】 CHANGE TO 【" + newFile + "】","         SUCCESS");
                }else {
                    logFileWriter(relativePath,"        FAILURE");
                }
            }
            for (String suffix : otherExtensionSet){
                logFileWriter(suffix,"DEBUG-SUFFIX");
            }
        }

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
        long bytesCount = 0L;
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
                    otherExtensionSet.add(suffix);
                    logFileWriter(getRelativePath(subFile),"              WARN");
                }
            }else if(subFile.isDirectory()){
                logFileWriter(getRelativePath(subFile),"     SUBFOLDER");
                Map<String,Long> tempMap = getFolderInfo(subFile.getPath());
                if (tempMap != null){
                    videoCount+=tempMap.get("videoCount");
                    pictureCount+=tempMap.get("pictureCount");
                    bytesCount+=tempMap.get("bytesCount");
                    pdfCount+=tempMap.get("pdfCount");
                }
            }
        }
        countMap.put("videoCount",videoCount);
        countMap.put("pictureCount",pictureCount);
        countMap.put("bytesCount",bytesCount);
        countMap.put("pdfCount",pdfCount);
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
                break;
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
        if (bytes>1024){
            kilobytes = (bytes / 1024);
        }
        if (kilobytes>1024){
            megabytes = (kilobytes / 1024);
        }
        if (megabytes>1024){
            gigabytes  = (megabytes / 1024);
        }
        if (gigabytes>1){
            return String.format("%.2f", megabytes) + "GB";
        }else if (megabytes>1){
            return String.format("%.0f", megabytes) + "MB";
        }else if (kilobytes>1){
            return String.format("%.0f", kilobytes) + "KB";
        }else {
            return bytes + "B";
        }
    }
    private static boolean ignoreCaseEquals(String str1,String str2){
        return str1 == null ? str2 == null :str1.equalsIgnoreCase(str2);
    }
    private static void logFileWriter(String content,String infoType){
        try {
            File file = new File(START_PATH+"\\"+LOG_FILE_PATH);
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
        long bytesCount= dataMap.get("bytesCount");
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
        return file.getPath().replace(START_PATH+"\\","");
    }
}
