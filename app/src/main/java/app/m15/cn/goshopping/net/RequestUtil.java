package app.m15.cn.goshopping.net;

/**
 * Created by Administrator on 2017/5/2 0002.
 */

public class RequestUtil {
    public static final String REQUEST_HEAD="http://192.168.0.9:8080//shop";

    public static final String REQUEST_ORDER_LIST=REQUEST_HEAD+"/orderlist";

    public static final String REQUEST_SEARCH=REQUEST_HEAD+"/search?"+"type=";

    public static final String REQUEST_REGISTER=REQUEST_HEAD+"/register";

    public static final String REQUEST_ADDRESS_LIST=REQUEST_HEAD+"/addresslist";

    public static final String REQUEST_ADDRESS_UPDATE=REQUEST_HEAD+"/addressadd";

    public static final String REQUEST_ADDRESS_DELETE=REQUEST_HEAD+"/addressdelete";

}
