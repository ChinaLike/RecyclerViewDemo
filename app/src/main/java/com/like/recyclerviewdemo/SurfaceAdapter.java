package com.like.recyclerviewdemo;

import android.content.Context;
import android.media.Image;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.asdzheng.layoutmanager.SizeCaculator;
import com.asdzheng.layoutmanager.SuitUrlUtil;
import com.bumptech.glide.Glide;
import com.felipecsl.asymmetricgridview.AGVRecyclerViewAdapter;
import com.felipecsl.asymmetricgridview.AsymmetricItem;

import java.util.List;

/**
 * Created by like on 2017/12/21.
 */

public class SurfaceAdapter extends AGVRecyclerViewAdapter<SurfaceAdapter.SurfaceViewHolder> {

    private List<UserBean> userList;

    private List<ConfigBean> configList;

    private Context mContext;

    private int width = 0;

    private int height = 0;

    public SurfaceAdapter(List<UserBean> userList, List<ConfigBean> configList, Context mContext) {
        this.userList = userList;
        this.configList = configList;
        this.mContext = mContext;
        width = ScreenUtil.getScreenWidth(mContext);
        height = ScreenUtil.getScreenHeight(mContext);
    }

    @Override
    public SurfaceViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == 1){
            return new SurfaceViewHolder(LayoutInflater.from(mContext).inflate(R.layout.layout1, parent, false));
        }
        return new SurfaceViewHolder(LayoutInflater.from(mContext).inflate(R.layout.layout, parent, false));
    }

    @Override
    public int getItemViewType(int position) {
        return userList.get(position).getType();
    }

    @Override
    public void onBindViewHolder(SurfaceViewHolder holder, int position) {
       // init(configList.get(position), holder.parent,holder.imageView);
        //    setStaggeredItemSpanCount(position,holder.parent,configList.get(position));
        Glide.with(mContext)
                .load(userList.get(position).getUrl())
                .crossFade()
                .into(holder.imageView);
    }
    private void init(ConfigBean bean, RelativeLayout layout, ImageView imageView) {
        int layoutWidth = (int) (width * bean.getWidth());
        int layoutHeight = (int) (height * bean.getHeight());
        Log.d("测试宽高", "layoutWidth=" + layoutWidth + ",layoutHeight=" + layoutHeight);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(layoutWidth, layoutHeight);
      //  layout.setLayoutParams(params);
        layout.setLayoutParams(params);
    }

    @Override
    public int getItemCount() {
        return userList != null && configList != null && userList.size() == configList.size() ? userList.size() : 0;
    }

    @Override
    public AsymmetricItem getItem(int position) {
        return userList.get(position);
    }

    class SurfaceViewHolder extends RecyclerView.ViewHolder {

        private ImageView imageView;

        private RelativeLayout parent;

        public SurfaceViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.img);
            parent = itemView.findViewById(R.id.parent);
        }
    }
}
