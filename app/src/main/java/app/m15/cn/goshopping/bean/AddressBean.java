package app.m15.cn.goshopping.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2017/5/20 0020.
 */

public class AddressBean {
    /**
     * addressList : [{"area":"金水区","city":"郑州市","id":1,"name":"赵晓林","phone":"15738807981","province":"河南省","type":2,"userid":1001,"zipcode":"400000"}]
     * response : addressList
     */

    private String response;
    private List<AddressListBean> addressList;

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public List<AddressListBean> getAddressList() {
        return addressList;
    }

    public void setAddressList(List<AddressListBean> addressList) {
        this.addressList = addressList;
    }

    public static class AddressListBean implements Serializable {
        /**
         * area : 金水区
         * city : 郑州市
         * id : 1
         * name : 赵晓林
         * phone : 15738807981
         * province : 河南省
         * type : 2
         * userid : 1001
         * zipcode : 400000
         */
        private static final long serialVersionUID = -7060210544600464481L;
        private String area;
        private String city;
        private int id;
        private String name;
        private String phone;
        private String province;
        private int type;
        private int userid;

        public String getDetail() {
            return detail;
        }

        public void setDetail(String detail) {
            this.detail = detail;
        }

        private String zipcode;
        private String detail;

        public String getArea() {
            return area;
        }

        public void setArea(String area) {
            this.area = area;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getProvince() {
            return province;
        }

        public void setProvince(String province) {
            this.province = province;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getUserid() {
            return userid;
        }

        public void setUserid(int userid) {
            this.userid = userid;
        }

        public String getZipcode() {
            return zipcode;
        }

        public void setZipcode(String zipcode) {
            this.zipcode = zipcode;
        }
    }
}
