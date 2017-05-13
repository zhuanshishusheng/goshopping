package app.m15.cn.goshopping.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2017/5/10 0010.
 */

public class GoodBean {
    /**
     * data : [{"id":10001,"imageUrl1":"/images/goods/id10001_image1.jpg","imageUrl2":"/images/goods/id10001_image2.jpg","love":45,"marketPrice":420,"monthSales":0,"price":420,"seller":"欧尚巴黎风","type":"日韩风"}]
     * response : sort
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

    public static class DataBean implements Serializable{
        /**
         * id : 10001
         * imageUrl1 : /images/goods/id10001_image1.jpg
         * imageUrl2 : /images/goods/id10001_image2.jpg
         * love : 45
         * marketPrice : 420
         * monthSales : 0
         * price : 420
         * seller : 欧尚巴黎风
         * type : 日韩风
         */
        private static final long serialVersionUID = -7060210544600464481L;
        private int id;
        private String imageUrl1;
        private String imageUrl2;
        private int love;
        private int marketPrice;
        private int monthSales;
        private int price;
        private String seller;
        private String type;
        private String describe;

        public String getDescribe(){
            return describe;
        }
        public void setDescribe(String describe){
            this.describe=describe;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getImageUrl1() {
            return imageUrl1;
        }

        public void setImageUrl1(String imageUrl1) {
            this.imageUrl1 = imageUrl1;
        }

        public String getImageUrl2() {
            return imageUrl2;
        }

        public void setImageUrl2(String imageUrl2) {
            this.imageUrl2 = imageUrl2;
        }

        public int getLove() {
            return love;
        }

        public void setLove(int love) {
            this.love = love;
        }

        public int getMarketPrice() {
            return marketPrice;
        }

        public void setMarketPrice(int marketPrice) {
            this.marketPrice = marketPrice;
        }

        public int getMonthSales() {
            return monthSales;
        }

        public void setMonthSales(int monthSales) {
            this.monthSales = monthSales;
        }

        public int getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
        }

        public String getSeller() {
            return seller;
        }

        public void setSeller(String seller) {
            this.seller = seller;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }
}
