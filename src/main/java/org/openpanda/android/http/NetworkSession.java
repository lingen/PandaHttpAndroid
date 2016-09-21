package org.openpanda.android.http;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

/**
 * Created by lingen on 5/18/16.
 *
 * 网络请求的服务
 */
public class NetworkSession {



    private static NetworkSession networkSession = new NetworkSession();

    /**
     * 构建函数私有化,
     */
    private NetworkSession(){

    }

    /**
     * 返回一个单例对象
     * @return
     */
    public static NetworkSession sharedInstance(){
        return networkSession;
    }

    public HttpResponse syncRequest(HttpRequest request){
        String url = request.getUrl();
        HttpRequestMethod type = request.getMethod();
        int timeout = request.getTimeout();


        HttpURLConnection urlConnection = null;

        DataOutputStream out = null;

        try{
            URL urlRequest = new URL(url);
            urlConnection = (HttpURLConnection)urlRequest.openConnection();

            urlConnection.setConnectTimeout(60 * 1000);
            urlConnection.setReadTimeout(timeout * 1000);
            urlConnection.setRequestMethod(type.getMethod());

            //POST PUT
            urlConnection.setDoOutput(true);
            urlConnection.setDoInput(true);

            urlConnection.setUseCaches(false);
//            urlConnection.setInstanceFollowRedirects(true);

            byte[] data = request.getRequestData();

            if (data!=null){
                urlConnection.setRequestProperty("Content-Type", request.getContentType().getContentType());
                urlConnection.setRequestProperty("Content-Length", String.valueOf(data.length));
            }

            urlConnection.connect();


            if (data!=null){
                out = new DataOutputStream(urlConnection
                        .getOutputStream());
                out.write(data);
                out.flush();
            }

            int statusCode = urlConnection.getResponseCode();
            if (statusCode == HttpURLConnection.HTTP_OK) {
                //读取流
                byte[] result = readBytes(urlConnection.getInputStream());
                return HttpResponse.createOKResponse(result);
            } else {
                return HttpResponse.createFailResponse(statusCode);
            }
        }catch (Exception e){
            e.printStackTrace();
            return HttpResponse.createFailResponse(-1);

        }
    }

    /**
     * 读取流
     * @param is
     * @return
     */
    private byte[] readBytes(InputStream is){
        byte[] bytes = null;
        try{
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int len = 0;
            while ((len = is.read(buffer)) > 0) {
                baos.write(buffer, 0, len);
            }
            bytes = baos.toByteArray();
        }catch (Exception e){
            e.printStackTrace();
        }

        return bytes;
    }
}
