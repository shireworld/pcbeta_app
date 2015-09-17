package com.yuikya.pcbeta.app.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.yuikya.pcbeta.app.R;
import com.yuikya.pcbeta.app.model.PostDetail;

import java.util.List;

/**
 * Created by fan on 2015/9/17.
 */
public class PostDetailRecyclerAdapter extends RecyclerView.Adapter<PostDetailRecyclerAdapter.ViewHolder> {

    private List<PostDetail> mList;
    private Context mContext;

    public PostDetailRecyclerAdapter(Context context,List<PostDetail> list){
        this.mContext = context;
        this.mList = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.postdetail_view,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

//        holder.username_tv.setText("fdfsd");
//        Glide.with(mContext).load(mList.get(position).getAvatar()).into(holder.avatar_iv);
//        holder.floor_tv.setText(mList.get(position).getFloor());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView username_tv,floor_tv;
        public ImageView avatar_iv;

        public ViewHolder(View itemView) {
            super(itemView);
            username_tv = (TextView) itemView.findViewById(R.id.postdetail_username_tv);
            floor_tv = (TextView) itemView.findViewById(R.id.postdetail_floor_tv);
            avatar_iv = (ImageView) itemView.findViewById(R.id.postdetail_avatar_iv);
        }
    }
}
