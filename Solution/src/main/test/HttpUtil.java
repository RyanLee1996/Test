import com.sun.istack.internal.NotNull;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HttpUtil {

    private static final String USER_AGENT = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/69.0.3497.100 Safari/537.36";
    private static final String COOKIE = "Hm_lvt_923e234dd8652a8d53c871d62940f91c=1540037119; Hm_lpvt_923e234dd8652a8d53c871d62940f91c=1540046669";
    private static final String IMG_INFO_REGEX = "<div class=\"picsbox picsboxcenter chenxing_pic_images\">[\\s\\S]*?<img.*?src=\"(.*?)\".*?alt=\"(.*?)\".*?/>[\\s\\S]*?</div>";
    private static final String CHILD_LIST_REGEX = "<h3 class=\"post-title\">[\\s\\S]*?<a.*?href=\"(.*?)\".*?title=\"(.*?)\"[\\s\\S]*?</h3>";
    private static final String PAGE_INFO_REGEX = "<div id=\"pagination\".*?<a href=\"(.*?)\".*?</a>";
    private static final String PICTURE_BOX_REGEX = "<div class=\"picsbox picsboxcenter\"([\\s\\S]*?)</div>";
    private static final String PICTURE_URL_REGEX = "<img data-original=\"(.*?)\".*?/>";
    private static final String DOMAIN_NAME = "https://www.ishsh.com";
    private static final String SUCCESS_ALL_LOG_PATH = "autoScript-success-all.log";
    private static final String PATH = "G:";
    private static final int TIME_OUT = 10000;
    public static String getContent(@NotNull CloseableHttpClient client, String httpUrl) {
        String result = "";
        HttpGet request = new HttpGet(httpUrl);
        System.out.println(httpUrl);
        // 设置请求和传输超时时间
        RequestConfig requestConfig = RequestConfig.custom()
                .setSocketTimeout(TIME_OUT).setConnectTimeout(TIME_OUT).build();
        //设置请求头
        request.setHeader( "User-Agent", USER_AGENT);
        request.setHeader("Cookie",COOKIE);
        request.setConfig(requestConfig);
        StringBuilder stringBuilder = new StringBuilder();
        try {
            CloseableHttpResponse response = client.execute(request);
            if (HttpStatus.SC_OK == response.getStatusLine().getStatusCode()) {
                HttpEntity entity = response.getEntity();
                InputStream inputStream = entity.getContent();
                BufferedReader in = new BufferedReader(new InputStreamReader(inputStream));
                String line="";
                while((line=in.readLine())!=null){
                    stringBuilder.append(line).append("\n");
                }
                result = stringBuilder.toString();
            }
        } catch (IOException e) {
            e.printStackTrace();
            result = getContent(client,httpUrl);
        } finally {
            request.releaseConnection();
        }
        return result;
    }
    public static void main(String[] args) {
        CloseableHttpClient client =null;
        try {
            client =   HttpClients.createDefault();
            Map<String,String> map = new LinkedHashMap<String,String>(){{
                put("明星美女","/star/page/8");
                put("模特美女","/model");
                put("高清美女","/gaoqing");
                put("风俗媚娘","/fengsu");
            }};
            for (String key : map.keySet()){
                getAllPic(client,map.get(key),PATH+"\\"+key);
                new FolderToolByPR(PATH).logFileWriter("---------" + key+"完成下载" + "---------",
                        "SUCCESS",SUCCESS_ALL_LOG_PATH);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            if(client!=null){
                try {
                    client.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    private static Set<Map<String,String>> getChildSet(CloseableHttpClient client, String url){
        Set<Map<String,String>> childSet= new LinkedHashSet<>();
        String pageUrl = null;
        String content = getContent(client,DOMAIN_NAME+url);
        if (!content.equals("")){
            Matcher matcher = Pattern.compile(CHILD_LIST_REGEX)
                    .matcher(content);
            while(matcher.find()) {
                Map<String,String> infoMap = new HashMap<>();
                infoMap.put("url",matcher.group(1).replace(DOMAIN_NAME,""));
                infoMap.put("title",matcher.group(2));
                childSet.add(infoMap);
            }
            Matcher matcherPage = Pattern.compile(PAGE_INFO_REGEX)
                    .matcher(content);
            while(matcherPage.find()) {
                pageUrl = matcherPage.group(1);
                childSet.addAll(getChildSet(client,pageUrl.replace(DOMAIN_NAME,"")));
            }
        }
        return childSet;
    }
    private static void getAllPic(CloseableHttpClient client, String url,String filePath){
        Set<Map<String,String>> childSet = getChildSet(client,url);
        for (Map<String,String> dataMap :childSet){
            String title = dataMap.get("title");
            String newUrl = DOMAIN_NAME + dataMap.get("url").replace(".html","-all.html");
            getAllPicture(client,newUrl,title,filePath);
            new FolderToolByPR(filePath).logFileWriter("---------" + dataMap.get("title")+"完成下载: " + newUrl +" ---------",
                    "SUCCESS",SUCCESS_ALL_LOG_PATH);
        }
    }
    private static void getAllPicture(CloseableHttpClient client, String url,String title,String filePath){
//        System.out.println(url);
        Set<String> childSet= new HashSet<>();
        String content = getContent(client,url);
        if (!content.equals("")){
            Matcher matcherRoughly = Pattern.compile(PICTURE_BOX_REGEX)
                    .matcher(content);
            String realContent = null;
            while(matcherRoughly.find()) {
                realContent = matcherRoughly.group(1);
            }
            if (realContent != null){
                Matcher matcher = Pattern.compile(PICTURE_URL_REGEX)
                        .matcher(realContent);
                while(matcher.find()) {
                    String picUrl = matcher.group(1);
                    Matcher m = Pattern.compile("^.*/(.*?)$")
                            .matcher(picUrl);
                    String picName = null;
                    while(m.find()){
                        picName = m.group(1);
                        int i = 0;
                        while (!DownloadPicTool.httpGetImg(client,picUrl,filePath+"\\"+stringFilter(title)+"\\"+stringFilter(picName)) && i<5){
                            i++;
                            if (i == 5){
                                new FolderToolByPR(filePath).logFileWriter(picUrl + "  " +stringFilter(title)+"\\"+stringFilter(picName) + " " + url,
                                        "DEBUG",FolderTool.DEBUG_LOG_PATH);
                            }
                        }
                    }
                }
            }
        }
    }
    public static String stringFilter(String str){
        return str.replaceAll("[\\\\/:\\*\\?\"<>\\|]","");
    }

}
