package com.yuikya.pcbeta.app.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yuikya.pcbeta.app.R;
import com.yuikya.pcbeta.app.model.Post;
import com.yuikya.pcbeta.app.model.PostDetail;
import com.yuikya.pcbeta.app.ui.activity.PostDetailActivity;

import java.util.List;

/**
 * Created by shire on 2015/9/16.
 */
public class PostRecyclerAdapter extends RecyclerView.Adapter<PostRecyclerAdapter.ViewHolder> {

    private List<Post> mList;
    private Context mContext;

    public PostRecyclerAdapter(Context context,List<Post> list){
        this.mContext = context;
        this.mList = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.post_view,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        viewHolder.author_tv.setText(mList.get(position).getAuthor());
        viewHolder.title_tv.setText(mList.get(position).getTitle());
        viewHolder.replytime_tv.setText(mList.get(position).getLastReplyTime());
        viewHolder.reply_count_tv.setText(mList.get(position).getReplyCount()+"");
        viewHolder.pubtime_tv.setText(mList.get(position).getPubTime());
        viewHolder.replyman_tv.setText(mList.get(position).getLastReplyMan());

        viewHolder.post_rl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, PostDetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("post",mList.get(position));
                intent.putExtras(bundle);
                mContext.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView title_tv,author_tv, replytime_tv,reply_count_tv,pubtime_tv,replyman_tv;
        public RelativeLayout post_rl;
        public ViewHolder(View itemView) {
            super(itemView);
            title_tv = (TextView) itemView.findViewById(R.id.post_title_tv);
            reply_count_tv = (TextView) itemView.findViewById(R.id.post_commentcount_tv);
            author_tv = (TextView) itemView.findViewById(R.id.post_author_tv);
            replytime_tv = (TextView) itemView.findViewById(R.id.post_replytime_tv);
            pubtime_tv = (TextView) itemView.findViewById(R.id.post_pubtime_tv);
            replyman_tv = (TextView) itemView.findViewById(R.id.post_replyman_tv);
            post_rl = (RelativeLayout) itemView.findViewById(R.id.post_rl);
        }
    }
}
