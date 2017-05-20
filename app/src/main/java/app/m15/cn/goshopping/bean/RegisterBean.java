package app.m15.cn.goshopping.bean;

/**
 * Created by Administrator on 2017/5/20 0020.
 */

public class RegisterBean {

    /**
     * error : 该用户名已经被注册过了
     * error_code : 1532
     * response : error
     */

    private String error;
    private String error_code;
    private String response;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getError_code() {
        return error_code;
    }

    public void setError_code(String error_code) {
        this.error_code = error_code;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }
}
