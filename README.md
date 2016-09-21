# PandaHttpAndroid
PandaHttp的Android版本实现

## 前言

PandaHttpAndroid是Android开发提供的一个基础 HTTP 封装框架，它的特点及原则如下:

1. 限定同步式的网络操作
2. 提供对 JSON以及 Form两种请求方式的支持
3. 简化对网络的操作

## 为什么需要这个框架

略，请参照PandaHttpSwift的这个点的说明

## 有哪些版本
会提供 OC,Swift,Java三种版本，以支持 Android和 IOS 的开发

#使用示例

### GET请求的示例

~~~java

        HttpRequest request = HttpRequest.jsonRequest("http://ip:port:8081/account/search?search=l&page=1&pagesize=10",HttpRequestMethod.HTTP_GET);

        HttpResponse response = NetworkSession.sharedInstance().syncRequest(request);

        if (response.isRequestOK()){
            Map result = response.exceptedMapResult();
            System.out.print("结果:"+result);
        }

~~~

> 返回结果

~~~

结果:{status=0.0, msg=Everything is ok, result={total=1.0, pageCount=10.0, totalPage=1.0, page=1.0, values=[{uuid=c1ef7ea0-502b-4513-940c-57bfae08732b, version=0.0, username=lingen, nickname=御剑, mobile=123456, email=lingen@foxmail.com, createTime=1.474461815379E12, updateTime=1.474461815379E12, enable=true}]}}


~~~

返回的值也是一个 JSON

### Post请求

~~~java

        String url = "http://ip:port:8081/account";
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
        
        
~~~

>返回结果

~~~

返回结果：{status=10011.0, msg=mobile exists, result=null}

~~~

### Put请求

>> Pust请求与 Post请求基本一致，使用HttpRequestMethod.HTTP_PUT

### Delete请求

>> 参照Get请求，使用HttpRequestMethod.HTTP_DELETE

