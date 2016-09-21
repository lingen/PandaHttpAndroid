package org.openpanda.android.http;

/**
 * Created by lingen on 5/19/16.
 * 请求HTTP的ContentType设置
 */
public enum HttpContentType {

    /**
     * 以JSON的模式请求
     */
    JSON_CONTENT_TYPE {
        @Override
        String getContentType() {
            return "application/json;charset=utf-8";
        }
    },

    /**
     * 请FROM表单的形式请求
     */
    FORM_CONTENT_TYPE {
        @Override
        String getContentType() {
            return "application/x-www-form-urlencoded";
        }
    };

    abstract String getContentType();
}
