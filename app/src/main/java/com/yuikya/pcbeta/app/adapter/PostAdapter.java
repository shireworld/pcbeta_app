package com.yuikya.pcbeta.app.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.yuikya.pcbeta.app.R;
import com.yuikya.pcbeta.app.model.Post;

import java.util.List;

/**
 * Created by shire on 2015/9/16.
 */
public class PostAdapter extends BaseAdapter {

    private List<Post> mList;
    private Context mContext;

    public PostAdapter(Context context,List<Post> list){
        this.mList = list;
        this.mContext = context;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView == null){
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.post_view,null);
            viewHolder.title_tv = (TextView) convertView.findViewById(R.id.post_title_tv);
            viewHolder.reply_count_tv = (TextView) convertView.findViewById(R.id.post_commentcount_tv);
            viewHolder.author_tv = (TextView) convertView.findViewById(R.id.post_author_tv);
            viewHolder.replytime_tv = (TextView) convertView.findViewById(R.id.post_replytime_tv);
            viewHolder.pubtime_tv = (TextView) convertView.findViewById(R.id.post_pubtime_tv);
            viewHolder.replyman_tv = (TextView) convertView.findViewById(R.id.post_replyman_tv);

            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.author_tv.setText(mList.get(position).getAuthor());
        viewHolder.title_tv.setText(mList.get(position).getTitle());
        viewHolder.replytime_tv.setText(mList.get(position).getLastReplyTime());
        viewHolder.reply_count_tv.setText(mList.get(position).getReplyCount()+"");
        viewHolder.pubtime_tv.setText(mList.get(position).getPubTime());
        viewHolder.replyman_tv.setText(mList.get(position).getLastReplyMan());
        return convertView;
    }

    class ViewHolder{
        public TextView title_tv,author_tv, replytime_tv,reply_count_tv,pubtime_tv,replyman_tv;
    }
}
