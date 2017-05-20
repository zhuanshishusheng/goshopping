package app.m15.cn.goshopping.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/5/18 0018.
 */

public class OrderBean {
    /**
     * orderList : [{"orderid":"112690","price":485,"time":"1494818112972","userid":1001},{"orderid":"190815","price":485,"time":"1494818192026","userid":1001},{"orderid":"364868","price":199,"time":"1494818364929","userid":1001},{"orderid":"395026","price":65,"time":"1494818395074","userid":1001},{"orderid":"523990","price":420,"time":"1494818524023","userid":1001},{"orderid":"718119","price":485,"time":"1494816719382","userid":1001},{"orderid":"752002","price":485,"time":"1494816753547","userid":1001},{"orderid":"853917","price":485,"time":"1494816855493","userid":1001},{"orderid":"858535","price":485,"time":"1494816858550","userid":1001}]
     * response : orderList
     */

    private String response;
    private List<OrderListBean> orderList;

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public List<OrderListBean> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<OrderListBean> orderList) {
        this.orderList = orderList;
    }

    public static class OrderListBean {
        /**
         * orderid : 112690
         * price : 485
         * time : 1494818112972
         * userid : 1001
         */

        private String orderid;
        private int price;
        private String time;
        private int userid;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        private String type;

        public String getOrderid() {
            return orderid;
        }

        public void setOrderid(String orderid) {
            this.orderid = orderid;
        }

        public int getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public int getUserid() {
            return userid;
        }

        public void setUserid(int userid) {
            this.userid = userid;
        }
    }
}
