package org.openpanda.android.http;

import org.junit.Test;

import javax.xml.bind.annotation.XmlElementDecl;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by lingen on 16/9/21.
 */
public class TestNetworkSession {

    @Test
    public void testGetJson(){

        HttpRequest request = HttpRequest.jsonRequest("http://localhost:8081/account/search?search=l&page=1&pagesize=10",HttpRequestMethod.HTTP_GET);

        HttpResponse response = NetworkSession.sharedInstance().syncRequest(request);

        if (response.isRequestOK()){
            Map result = response.exceptedMapResult();
            System.out.print("结果:"+result);
        }

    }

    @Test
    public void testPostJson(){
        String url = "http://localhost:8081/account";
        Map<String,Object> params = new HashMap();

        params.put("username","lingen");
        params.put("password","123456");
        params.put("nickname","lingen");
        params.put("mobile","123456");
        params.put("email","lingen@foxmail.com");

        HttpRequest request = HttpRequest.jsonRequest(url,HttpRequestMethod.HTTP_POST,params);

        HttpResponse response = NetworkSession.sharedInstance().syncRequest(request);

        if (response.isRequestOK()){
            Map<String,Object> mapResult = response.exceptedMapResult();
            System.out.println("返回结果："+mapResult);
        }
    }

    @Test
    public void testPutJson(){

        String url = "http://localhost:8081//account/changePwd";
        Map<String,Object> params = new HashMap();

        params.put("user_id","123");
        params.put("old_pwd","123");
        params.put("new_pwd","123");

        HttpRequest request = HttpRequest.jsonRequest(url,HttpRequestMethod.HTTP_PUT,params);

        HttpResponse response = NetworkSession.sharedInstance().syncRequest(request);

        if (response.isRequestOK()){
            Map<String,Object> mapResult = response.exceptedMapResult();
            System.out.println(mapResult);
        }else{
            System.out.print(response.getStatusCode());
        }

    }

}
