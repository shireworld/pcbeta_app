package com.yuikya.pcbeta.app.ui.activity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.yuikya.pcbeta.app.R;
import com.yuikya.pcbeta.app.adapter.PostDetailRecyclerAdapter;
import com.yuikya.pcbeta.app.model.Post;
import com.yuikya.pcbeta.app.model.PostDetail;
import com.yuikya.pcbeta.app.parser.PostDetailParser;
import com.yuikya.pcbeta.app.ui.widget.DividerItemDecoration;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fan on 2015/9/17.
 */
public class PostDetailActivity extends AppCompatActivity {

    private RecyclerView mRecyclerview;
    private PostDetailRecyclerAdapter mAdapter;
    private List<PostDetail> mList = new ArrayList<>();
    private Post mPost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_postdetail);
        mPost = (Post) getIntent().getExtras().getSerializable("post");

        mRecyclerview = (RecyclerView) findViewById(R.id.postdetail_recyclerview);
        mRecyclerview.setHasFixedSize(false);
        mRecyclerview.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new PostDetailRecyclerAdapter(this,mList);
        mRecyclerview.setAdapter(mAdapter);
        mRecyclerview.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));
        Log.d("pcbeta",mPost.getUrl());
        new PostDetailAsyncTask().execute(mPost.getUrl());
    }

    class PostDetailAsyncTask extends AsyncTask<String,Void,List<PostDetail>>{

        @Override
        protected List<PostDetail> doInBackground(String... params) {
            PostDetailParser parser = new PostDetailParser();
            List<PostDetail> list = parser.getPostDetails(params[0]);
            return list;
        }

        @Override
        protected void onPostExecute(List<PostDetail> postDetails) {
            super.onPostExecute(postDetails);
            mPost.setDetails(postDetails);
            mList.addAll(postDetails);
            mAdapter.notifyDataSetChanged();
        }
    }
}
