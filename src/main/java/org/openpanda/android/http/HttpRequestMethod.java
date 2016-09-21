package org.openpanda.android.http;

/**
 * Created by lingen on 5/17/16.
 *
 * HTTP的四种请求方式
 */
public enum HttpRequestMethod {

    /**
     * GET方法
     */
    HTTP_GET {
        @Override
        String getMethod() {
            return "GET";
        }
    },

    /**
     * POST方法
     */
    HTTP_POST {
        @Override
        String getMethod() {
            return "POST";
        }
    },

    /**
     * PUT方法
     */
    HTTP_PUT {
        @Override
        String getMethod() {
            return "PUT";
        }
    },

    /**
     * DELETE方法
     */
    HTTP_DELETE {
        @Override
        String getMethod() {
            return "DELETE";
        }
    };

    abstract String getMethod();
}
