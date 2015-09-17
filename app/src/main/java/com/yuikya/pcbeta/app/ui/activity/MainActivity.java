package com.yuikya.pcbeta.app.ui.activity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.yuikya.pcbeta.app.R;
import com.yuikya.pcbeta.app.adapter.PostRecyclerAdapter;
import com.yuikya.pcbeta.app.model.Post;
import com.yuikya.pcbeta.app.parser.PostListParser;
import com.yuikya.pcbeta.app.ui.widget.DividerItemDecoration;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {

    private RecyclerView mRecyclerView;
    private PostRecyclerAdapter mAdapter;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private boolean isLoading = false;
    private int mPage = 1;
    private String url = "http://bbs.pcbeta.com/forum-win10-"+mPage+".html";
    private List<Post> mList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = (RecyclerView) findViewById(R.id.post_recyclerview);
        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.post_swipe);
        mRecyclerView.setHasFixedSize(false);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL_LIST));
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new PostRecyclerAdapter(MainActivity.this,mList);
        mRecyclerView.setAdapter(mAdapter);
        new ParseAsyncTask().execute(url);
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                LinearLayoutManager llm = (LinearLayoutManager) recyclerView.getLayoutManager();
                if (recyclerView.getAdapter().getItemCount() - llm.findLastVisibleItemPosition() == 1 && !isLoading) {
                    Log.d("tag",mPage+"");
                    new ParseAsyncTask().execute(url);
                }
            }
        });
        mSwipeRefreshLayout.setOnRefreshListener(this);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch (id){
            case R.id.action_refresh:
                onRefresh();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onRefresh() {
        mPage = 1;
        mList.clear();
        new ParseAsyncTask().execute(url);
    }

    class ParseAsyncTask extends AsyncTask<String,Void,List<Post>>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mSwipeRefreshLayout.setRefreshing(true);
        }

        @Override
        protected List<Post> doInBackground(String... params) {
            isLoading = true;
            PostListParser parser = new PostListParser();
            return parser.getPostList(params[0]);
        }

        @Override
        protected void onPostExecute(List<Post> posts) {
            super.onPostExecute(posts);
            mList.addAll(posts);
            mAdapter.notifyDataSetChanged();
            isLoading = false;
            mPage++;
            mSwipeRefreshLayout.setRefreshing(false);
        }
    }



}
