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

//        String regex = ".*\\\\\\[.*?]XR.*?(N.*?)\\s(.*?)\\s(.*?)$";
//        FolderToolByXR tool = new FolderToolByXR("F:\\秀人网");
//        tool.folderNameChange(false,regex);

        setFilter();
    }

    public static void setFilter(){
        java.text.DecimalFormat df = new java.text.DecimalFormat("#.00");
        String s1 = df.format(0.968);
        String s2 = df.format(3.1465926);
        System.out.println("s:" + s1);
        System.out.println("s:" + String.format("%.0f",0.968));
    }
}
