import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.FileUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;



public class DownloadPicTool {
    /**
     * 发送get请求,  下载图片
     *
     * @param
     *
     * @return
     */
    public static boolean httpGetImg(CloseableHttpClient client,String imgUrl,String savePath) {
        File file = new File(savePath);
        Matcher matcher = Pattern.compile("(^.*)\\\\(.*?\\\\.*?$)").matcher(savePath);
        String filePath = null;
        String fileName = null;
        while (matcher.find()){
            filePath = matcher.group(1);
            fileName = matcher.group(2);
        }
        if (file.exists()){
            if (filePath != null){
                new FolderToolByPR(filePath).logFileWriter(fileName,
                        "EXIST",FolderTool.WARN_LOG_PATH);
            }
            return true;
        }
        // 发送get请求
        HttpGet request = new HttpGet(imgUrl);
        // 设置请求和传输超时时间
        RequestConfig requestConfig = RequestConfig.custom()
                .setSocketTimeout(50000).setConnectTimeout(50000).build();
        //设置请求头
        request.setHeader( "User-Agent","Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.1 (KHTML, like Gecko) Chrome/21.0.1180.79 Safari/537.1" );
        request.setConfig(requestConfig);
        try {
            CloseableHttpResponse response = client.execute(request);
            if (HttpStatus.SC_OK == response.getStatusLine().getStatusCode()) {
                HttpEntity entity = response.getEntity();
                InputStream in = entity.getContent();
                FileUtils.copyInputStreamToFile(in, new File(savePath));
                System.out.println("下载图片成功:"+imgUrl);
                if (filePath != null) {
                    new FolderToolByPR(filePath).logFileWriter(imgUrl + "  " + fileName,
                            "SUCCESS", FolderTool.SUCCESS_LOG_PATH);
                }
                return true;
            }else {
                return false;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        } finally {
            request.releaseConnection();
        }
    }


    public static void main(String[] args) {
        CloseableHttpClient client =null;
        try {
            client =   HttpClients.createDefault();
            String  url ="https://www.ishsh.com/30930.html";
            String  path="C:\\Users\\Ryan\\Desktop\\李丽莎\\李丽莎_";
            httpGetImg(client,url, path+".jpg");
            System.out.println("ok");
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
}
