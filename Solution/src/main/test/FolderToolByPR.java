import java.io.IOException;
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
        String regex = "^麻酥酥喲 -\\s(.*?$)";
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
        System.out.println(isConnect());
    }
    private static URL urlStr;

    private static HttpURLConnection connection;

    private static int state = -1;
    /**
     * 判断网络图片是否存在
     * posturl 图片地址链接
     */
    private static synchronized boolean isConnect() {
        try {
            urlStr = new URL("https://www.ishsh.com/wp-content/uploads/2018/09/lls2-18.jpg");          //创建URL对象
            connection = (HttpURLConnection) urlStr.openConnection();     //连接URL
            state = connection.getResponseCode();       //获取URL连接状态码
            if (state == 200) {
                return true;
            } else if (state == 404){
                return false;
            }
        } catch (Exception ex) {

        }
        return false;
    }
}
