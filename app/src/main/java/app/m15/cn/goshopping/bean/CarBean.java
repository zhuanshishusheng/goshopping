package app.m15.cn.goshopping.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/5/13 0013.
 */

public class CarBean {

    /**
     * data : [{"goodid":10001,"num":1,"userid":1001},{"goodid":10002,"num":1,"userid":1001}]
     * response : carlist
     */

    private String response;
    private List<DataBean> data;

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * goodid : 10001
         * num : 1
         * userid : 1001
         */

        private int goodid;
        private int num;
        private int userid;

        public int getGoodid() {
            return goodid;
        }

        public void setGoodid(int goodid) {
            this.goodid = goodid;
        }

        public int getNum() {
            return num;
        }

        public void setNum(int num) {
            this.num = num;
        }

        public int getUserid() {
            return userid;
        }

        public void setUserid(int userid) {
            this.userid = userid;
        }
    }
}
