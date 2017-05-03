package app.m15.cn.goshopping.fragment.me;

/**
 * Created by Administrator on 2017/5/3 0003.
 */

public class MePresenter implements MeContact.Presenter {
    private MeContact.View mView;

    public MePresenter(MeContact.View view) {
        mView = view;
    }

    @Override
    public void init() {
        mView.initView();
        mView.initData();
        mView.initListener();
    }

    @Override
    public void destroyView() {
        if(mView!=null){
            mView=null;
        }
    }
}
