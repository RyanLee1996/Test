import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FolderToolByXR extends FolderTool{
    private static final String[] FILTER = {"你好"};
    private static final String[] DELETE_FILE = {"Thumbs.db"};
    public FolderToolByXR(String path) {
        super(path);
    }

    @Override
    public  String recombination(String fileName){
        String regex = "^XR.*?(N.*?)\\s(.*?)\\s(.*?$)";
//        for (String rule : FILTER){
//            fileName = fileName.replace(rule,"").trim();
//        }
        String newFileName = fileName;
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(fileName);
        if (matcher.find()){
            String temp = matcher.group(1);
            String time = matcher.group(2);
            String modelName = matcher.group(3);

            String ID = "NO." + temp.substring(temp.length()-4, temp.length());
            newFileName = time + " " + ID + " " + modelName ;
        }
        return newFileName;
    }
    public static void main(String[] args){
//        folderNameChange(startPath,false);
//        deleteFile(startPath,new String[]{"url"});

        String regex = ".*\\\\\\[.*?]XR.*?(N.*?)\\s(.*?)\\s(.*?)$";
        FolderToolByXR tool = new FolderToolByXR("F:\\秀人网");
        tool.folderNameChange(false,regex);


    }
    public static boolean regular(String str,String regex) {
        Pattern p=Pattern.compile(regex);
        Matcher m=p.matcher(str);
        return m.matches();
    }
    @Test
    public static void setFilter(){
        String regex2 = ".*\\\\\\[.*?]XR.*?(N.*?)\\s(.*?)\\s(.*?)$";
//        String regex2 = ".*\\\\XR.*?(N.*?)\\s(.*?)\\s(.*?)$";
        Pattern p=Pattern.compile(regex2);


        String s3 = "F:\\秀人网\\[XIUREN秀人网]XR20131129N00056 2013.11.29 toro羽住";
//        String s3 = "F:\\秀人网\\XR20131129N00056 2013.11.29 toro羽住";
        Matcher matcher=p.matcher(s3);
        if (matcher.find()){
            System.out.println(matcher.group());
            String temp = matcher.group(1);
            String time = matcher.group(2);
            String modelName = matcher.group(3);

            String ID = "NO." + temp.substring(temp.length()-4, temp.length());
            String newFileName = time + " " + ID + " " + modelName ;
            System.out.println(newFileName);
        }
    }
}
