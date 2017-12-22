package com.like.recyclerviewdemo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.felipecsl.asymmetricgridview.AGVRecyclerViewAdapter;
import com.felipecsl.asymmetricgridview.AsymmetricItem;
import com.felipecsl.asymmetricgridview.Utils;

import java.util.List;

/**
 * Created by like on 2017/12/22.
 */

class RecyclerViewAdapter extends AGVRecyclerViewAdapter<RecyclerViewAdapter.ViewHolder> {
    private final List<DemoItem> items;

    private Context mContext;

    private List<ConfigBean> configBeanList;

    private int width = 0;

    private int height = 0;

    RecyclerViewAdapter(List<DemoItem> items,Context mContext,List<ConfigBean> configBeanList) {
        this.items = items;
        this.mContext =mContext;
        this.configBeanList=configBeanList;
        width = ScreenUtil.getScreenWidth(mContext);
        height = ScreenUtil.getScreenHeight(mContext);
    }

    @Override public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.d("RecyclerViewActivity", "onCreateView");
        return new ViewHolder(parent, viewType);
    }

    @Override public void onBindViewHolder(ViewHolder holder, int position) {
        Log.d("RecyclerViewActivity", "onBindView position=" + position);
        holder.bind(items.get(position),configBeanList.get(position));
    }

    @Override public int getItemCount() {
        return items.size();
    }

    @Override public AsymmetricItem getItem(int position) {
        return items.get(position);
    }

    @Override public int getItemViewType(int position) {
        return position % 2 == 0 ? 1 : 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private  ImageView imageView;

        private RelativeLayout layout;

        ViewHolder(ViewGroup parent, int viewType) {
            super(LayoutInflater.from(parent.getContext()).inflate(R.layout.layout, parent, false));
            imageView = itemView.findViewById(R.id.img);
            layout = itemView.findViewById(R.id.parent);
        }

        void bind(DemoItem item,ConfigBean config) {
            init(config,layout,imageView);
            Glide.with(mContext)
                    .load(item.getUrl())
                    .crossFade()
                    .into(imageView);
        }

        private void init(ConfigBean bean, RelativeLayout layout, ImageView imageView) {
            int layoutWidth = (int) (width * bean.getWidth())- Utils.dpToPx(mContext, 1);
            int layoutHeight = (int) (height * bean.getHeight())-Utils.dpToPx(mContext, 1);
            Log.d("测试宽高", "layoutWidth=" + layoutWidth + ",layoutHeight=" + layoutHeight);
            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(layoutWidth, layoutHeight);
            imageView.setLayoutParams(params);
            layout.setLayoutParams(params);
        }
    }
}