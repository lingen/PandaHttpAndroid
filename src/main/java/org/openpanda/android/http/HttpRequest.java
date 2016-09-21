package org.openpanda.android.http;

import com.google.gson.Gson;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.URLEncoder;
import java.util.Map;

/**
 * Created by lingen on 5/17/16.
 */
public class HttpRequest {

    /**
     * 默认超时时间
     */
    private static final int DEFAULT_TIMEOUT = 60 * 2;


    private HttpRequest(){

    }

    /**
     * 请求类型
     */
    private HttpRequestMethod method;

    /**
     * 设定Content Type
     */
    private HttpContentType contentType;


    /**
     * 请求参数
     */
    private Map<String,Object> params;

    /**
     * 超时时间
     */
    private int timeout;

    /**
     * 网址
     */
    private String url;

    /**
     * 传入一个网址及一个请求类型,构建一个HTTP请求
     * @param url  网址
     * @param method 类型
     * @return
     */
    public static HttpRequest jsonRequest(String url, HttpRequestMethod method){
        HttpRequest request = new HttpRequest();
        request.method = method;
        request.url = url;
        request.timeout = DEFAULT_TIMEOUT;
        request.contentType = HttpContentType.JSON_CONTENT_TYPE;
        return request;
    }

    /**
     * 传入网址,请求类型,及HTTP请求类型,参数,构建一个HTTP请求
     * @param url
     * @param method
     * @param params
     * @return
     */
    public static HttpRequest jsonRequest(String url, HttpRequestMethod method, Map<String,Object> params){
        HttpRequest request = new HttpRequest();
        request.method = method;
        request.url = url;
        request.params = params;
        request.timeout = DEFAULT_TIMEOUT;
        request.contentType = HttpContentType.JSON_CONTENT_TYPE;
        return request;
    }

    /**
     * 构建 一个请求
     * @param url
     * @param method
     * @param params
     * @param timeout
     * @return
     */
    public static HttpRequest jsonRequest(String url, HttpRequestMethod method, Map<String,Object> params, int timeout){
        HttpRequest request = new HttpRequest();
        request.method = method;
        request.url = url;
        request.params = params;
        request.timeout = DEFAULT_TIMEOUT;
        request.contentType = HttpContentType.JSON_CONTENT_TYPE;
        return null;
    }

    /**
     * 构建一个FORM请求
     * @param url
     * @param method
     * @return
     */
    public static HttpRequest formRequest(String url, HttpRequestMethod method){
        HttpRequest request = new HttpRequest();
        request.method = method;
        request.url = url;
        request.timeout = DEFAULT_TIMEOUT;
        request.contentType = HttpContentType.FORM_CONTENT_TYPE;
        return request;
    }

    /**
     * 构建一个FORM请求
     * @param url
     * @param method
     * @return
     */
    public static HttpRequest formRequest(String url, HttpRequestMethod method, Map<String,Object> params){
        HttpRequest request = new HttpRequest();
        request.method = method;
        request.url = url;
        request.params = params;
        request.timeout = DEFAULT_TIMEOUT;
        request.contentType = HttpContentType.FORM_CONTENT_TYPE;
        return request;
    }

    /**
     * 构建一个FORM请求
     * @param url
     * @param method
     * @return
     */
    public static HttpRequest formRequest(String url, HttpRequestMethod method, Map<String,Object> params, int timeout){
        HttpRequest request = new HttpRequest();
        request.method = method;
        request.url = url;
        request.params = params;
        request.timeout = DEFAULT_TIMEOUT;
        request.contentType = HttpContentType.FORM_CONTENT_TYPE;
        return request;
    }

    //========== GET SET ================



    public Map<String, Object> getParams() {
        return params;
    }

    public void setParams(Map<String, Object> params) {
        this.params = params;
    }

    public int getTimeout() {
        return timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public HttpRequestMethod getMethod() {
        return method;
    }

    public void setMethod(HttpRequestMethod method) {
        this.method = method;
    }

    public HttpContentType getContentType() {
        return contentType;
    }

    public void setContentType(HttpContentType contentType) {
        this.contentType = contentType;
    }

    //========================================


    public byte[] getRequestData(){
        if (HttpContentType.FORM_CONTENT_TYPE == this.contentType){
            return getFormRequestData();
        }
        else if(HttpContentType.JSON_CONTENT_TYPE == this.contentType){
            return getJsonRequestData();
        }

        return null;
    }

    public byte[] getFormRequestData() {
        StringBuffer stringBuffer = new StringBuffer();        //存储封装好的请求体信息
        try {
            for (Map.Entry<String, Object> entry : this.params.entrySet()) {
                stringBuffer.append(entry.getKey())
                        .append("=")
                        .append(URLEncoder.encode(String.valueOf(entry.getValue()), "UTF-8"))
                        .append("&");
            }
            stringBuffer.deleteCharAt(stringBuffer.length() - 1);    //删除最后的一个"&"
        } catch (Exception e) {
            e.printStackTrace();
        }
        return stringBuffer.toString().getBytes();
    }


    public byte[] getJsonRequestData() {
        if (params == null){
            return null;
        }
        String jsonValue = new Gson().toJson(params);
        return jsonValue.getBytes();
    }
}
