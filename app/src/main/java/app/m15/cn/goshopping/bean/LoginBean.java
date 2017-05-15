package app.m15.cn.goshopping.bean;

/**
 * Created by Administrator on 2017/5/12 0012.
 */

public class LoginBean {
    /**
     * data : {"password":"123456","phone":"15738807981","userid":1001,"username":"zxl"}
     * response : sort
     */

    private DataBean data;
    private String response;
    /**
     * error : 用户名不存在或密码错误
     * error_code : 1530
     */

    private String error;
    private String error_code;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

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

    public static class DataBean {
        /**
         * password : 123456
         * phone : 15738807981
         * userid : 1001
         * username : zxl
         */

        private String password;
        private String phone;
        private int userid;
        private String username;
        private int area;

        public void setArea(int area){
            this.area=area;
        }
        public int getArea(){
            return area;
        }


        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public int getUserid() {
            return userid;
        }

        public void setUserid(int userid) {
            this.userid = userid;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }
    }
}
