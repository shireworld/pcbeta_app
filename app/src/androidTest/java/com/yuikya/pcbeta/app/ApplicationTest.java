package com.yuikya.pcbeta.app;

import android.app.Application;
import android.os.AsyncTask;
import android.test.ApplicationTestCase;
import android.util.Log;

import com.yuikya.pcbeta.app.model.Post;
import com.yuikya.pcbeta.app.parser.PostListParser;

import java.util.List;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class ApplicationTest extends ApplicationTestCase<Application> {
    public ApplicationTest() {
        super(Application.class);

        new ParserAsyncTask().execute("http://bbs.pcbeta.com/forum-win10-1.html");

    }

    public void test(){
        new ParserAsyncTask().execute("http://bbs.pcbeta.com/forum-win10-1.html");
    }

    class ParserAsyncTask extends AsyncTask<String, Void, List<Post>> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Log.d("pcbeta", "start parse html");
        }

        @Override
        protected List<Post> doInBackground(String... params) {
            PostListParser parser = new PostListParser();
            return parser.getPostList(params[0]);

        }

        @Override
        protected void onPostExecute(List<Post> posts) {
            super.onPostExecute(posts);
            Log.d("pcbeta",posts.size()+"");
        }
    }
}