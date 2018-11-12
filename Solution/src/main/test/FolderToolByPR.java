import java.net.HttpURLConnection;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FolderToolByPR extends FolderTool {
    private static final String[] DELETE_FILE = {"Thumbs.db","txt","url"};
    public FolderToolByPR(String path) {
        super(path);
    }

    @Override
    public String recombination(String fileName) {
        String regex = "^ -\\s(.*?$)";
        Pattern p=Pattern.compile(regex);
        Matcher matcher=p.matcher(fileName);
        if (matcher.find()){
            return matcher.group(1);
        }else {
            return fileName;
        }

    }

    public static void main(String[] args) {
//        FolderToolByPR tool = new FolderToolByPR("F:\\Partycat");
//        tool.folderNameChange(false);
//        tool.deleteFile(DELETE_FILE);
        System.out.println();
    }
}
