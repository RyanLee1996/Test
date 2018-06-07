package ryan.httpio;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CrawlerImage {
    private static InputStream is;
    private static BufferedInputStream bis;
    private static HttpURLConnection httpUrl;
    public static void main(String[] args) {
        String urlPath = "https://mm.taobao.com/search_tstar_model.htm?qq-pf-to=pcqq.group";
        String downloadPath = "D:/Download/";
        HashSet<String> imageUrls = getImageUrl(urlPath);
        for(String url : imageUrls){
            downloadImage(url,getImageName(url,downloadPath));
        }
    }
    /**
     * 获取给予的url下的imagrUrl
     * @param urlPath
     * @return
     */
    public static HashSet<String> getImageUrl(String urlPath){
        HashSet<String> imageUrls = new HashSet<>();
        int count = 0;
        try {
//            WebClient wc=new WebClient(BrowserVersion.FIREFOX_24);
            URL url = new URL(urlPath);
            httpUrl= (HttpURLConnection) url.openConnection();
            /*建立TCP链接*/
            httpUrl.setRequestMethod("POST");
            httpUrl.setConnectTimeout(5000);
            httpUrl.setReadTimeout(5000);
            httpUrl.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.64 Safari/537.11");
            httpUrl.connect();
            is = httpUrl.getInputStream();
            bis = new BufferedInputStream(is);
            int len = 0;
            byte[] contentBytes = new byte[1024];
            StringBuffer content = new StringBuffer();
            while ((len = bis.read(contentBytes)) != -1){
                content.append(new String(contentBytes,0,len,"UTF-8"));
            }
            System.out.println(content);
            String regx = ".*?img\\ssrc=\"?\'?(.*?(gif|jpg|png))";
            Pattern p = Pattern.compile(regx);
            Matcher m = p.matcher(content);
            System.out.println("START");
            while (m.find()){
                String imageurl = m.group(1);
                System.out.println(imageurl);
                if (imageurl.startsWith("//")){
                    imageurl = "https:" + imageurl;
                }
                imageUrls.add(imageurl);
            }
            System.out.println("END");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
                bis.close();
                httpUrl.disconnect();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return imageUrls;
    }
    public static void downloadImage(String imageUrl,String downloadPath) {
        try {
// 构造URL
            URL url = new URL(imageUrl);
            // 打开连接
            URLConnection con = url.openConnection();
            //设置请求超时为5s
            con.setConnectTimeout(5*1000);
            // 输入流
            InputStream is = con.getInputStream();
            // 1K的数据缓冲
            byte[] bs = new byte[1024];
            // 读取到的数据长度
            int len;
            // 输出的文件流
            File sf=new File(downloadPath);
            OutputStream os = new FileOutputStream(sf);
            // 开始读取
            while ((len = is.read(bs)) != -1) {
                os.write(bs, 0, len);
            }
            // 完毕，关闭所有链接
            os.close();
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public  static String getImageName(String url,String downloadPath){
        String regx = ".*?\\.(.*?)\\..*/(.*)";
        Pattern p = Pattern.compile(regx);
        Matcher m = p.matcher(url);
        String imagePath = "";
        if (m.find()){
            imagePath = downloadPath + m.group(1)+"_"+m.group(2);
        }
        return imagePath;
    }
}
