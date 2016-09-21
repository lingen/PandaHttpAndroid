package org.openpanda.android.http;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Map;

/**
 * Created by lingen on 5/18/16.
 *
 * 网络请求的返回值
 */
public class HttpResponse {


    private static final int HTTP_RESPONSE_OK  = 200;

    /**
     * 网络请求的返回值
     */
    private byte[] data;

    /**
     * 网络请求的状态码
     */
    private int statusCode;

    /**
     * 错误信息
     */
    private String errorMsg;

    /**
     * 错误码
     */
    private int errorCode;


    public static HttpResponse createOKResponse(byte[] data){
        HttpResponse response = new HttpResponse();
        response.statusCode = HTTP_RESPONSE_OK;
        response.data = data;
        return response;
    }

    public static HttpResponse createFailResponse(int statusCode){
        HttpResponse response = new HttpResponse();
        response.statusCode = statusCode;
        return response;
    }
    //============GET SET 方法 ===============

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    //==============================================

    /**
     * 请求结果是否正常
     * @return
     */
    public boolean isRequestOK(){
        return statusCode == HTTP_RESPONSE_OK;
    }

    /**
     * 期望结果是String类型的结果
     * @return
     */
    public String exceptedStringResult(){
        String result = new String(this.data);
        return result;
    }

    /**
     * 期望结果是byte[]型结果
     * @return
     */
    public byte[] exceptedData(){
        return this.data;
    }

    /**
     * 期望结果是byte[]型结果
     * @return
     */
    public Map<String,Object>  exceptedMapResult(){
        String result = new String(this.data);
        Type type = new TypeToken<Map<String, Object>>(){}.getType();
        Gson gson = new Gson();
        Map<String, Object> myMap = gson.fromJson(result, type);
        return myMap;
    }


}
