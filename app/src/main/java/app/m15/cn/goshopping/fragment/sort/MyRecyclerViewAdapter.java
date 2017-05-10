package app.m15.cn.goshopping.fragment.sort;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.List;

import app.m15.cn.goshopping.R;
import app.m15.cn.goshopping.bean.GoodBean;
import app.m15.cn.goshopping.net.RequestUtil;

/**
 * Created by Administrator on 2017/5/10 0010.
 */

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.OneViewHolder> {
    private List<GoodBean.DataBean> list;
    private Context context;
    public MyRecyclerViewAdapter(Context context,List list){
        this.list=list;
        this.context=context;
    }

    @Override
    public OneViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new OneViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.recycleview_item, parent, false));
    }
    @Override
    public void onBindViewHolder(OneViewHolder holder,int position) {
        //网络加载图片
        Glide.with(context).load(RequestUtil.REQUEST_HEAD+list.get(position).getImageUrl1())
                .into(holder.imageView);
    }
    @Override
    public int getItemCount() {
        return list.size();
    }

    class OneViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;

        public OneViewHolder(View itemView) {
            super(itemView);
            imageView=(ImageView)itemView.findViewById(R.id.ivImage);
        }
    }
}
