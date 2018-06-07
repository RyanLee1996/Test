package ryan.httpio;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashSet;

public class HttpLoad {
    private static InputStream is;
    private static BufferedInputStream bis;
    private static HttpURLConnection httpUrl;

    public static void main(String[] args) {
        String urlPath = "http://127.0.0.1/Agileone_1.2/index.php/notice";
        HashSet<String> imageUrls = new HashSet<>();
        int count = 0;
        try {
//            WebClient wc=new WebClient(BrowserVersion.FIREFOX_24);
            URL url = new URL(urlPath);
            httpUrl = (HttpURLConnection) url.openConnection();
            /*建立TCP链接*/
            httpUrl.setRequestMethod("POST");
            httpUrl.setConnectTimeout(5000);
            httpUrl.setReadTimeout(5000);
            httpUrl.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.108 Safari/537.36");
            httpUrl.setRequestProperty("Cookie","PHPSESSID=64488265a6b64904ca4c2fa64be7e4c6; username=admin; password=admin");
            httpUrl.connect();
            is = httpUrl.getInputStream();
            bis = new BufferedInputStream(is);
            int len = 0;
            byte[] contentBytes = new byte[1024];
            StringBuffer content = new StringBuffer();
            while ((len = bis.read(contentBytes)) != -1) {
                content.append(new String(contentBytes, 0, len, "UTF-8"));
            }
            System.out.println(content);
        }
        catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                is.close();
                bis.close();
                httpUrl.disconnect();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
