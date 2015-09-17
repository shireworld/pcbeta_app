package com.yuikya.pcbeta.app.parser;

import android.util.Log;

import com.yuikya.pcbeta.app.model.Post;
import com.yuikya.pcbeta.app.util.OkHttpUtil;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by fan on 2015/9/16.
 */
public class PostListParser {

    public List<Post> getPostList(String url) {
        List<Post> postList = new ArrayList<>();
        try {
            Document doc = Jsoup.parse(OkHttpUtil.getStringFromServer(url));
            Elements elements = doc.select("[id^=normalthread]");
            for (Element element : elements) {
                String id = elements.attr("id").split("_")[1];
                String type = element.select("em > a").html();
                String postUrl = element.select("a.xst").attr("href");
                String title = element.select("a.xst").html();
                String author = element.select("td:nth-child(3) a").html();
                String pubTime = element.select("td:nth-child(3) span").html();
                String lastReplyUser = element.select("td:nth-child(5) cite > a").html();
                String lastReplyTime = element.select("td:nth-child(5) em > a").html();
                String replyCount = element.select("td:nth-child(4) a").html();
                String viewCount = element.select("td:nth-child(4) em").html();

                Post post = new Post();
                post.setId(id);
                post.setUrl(postUrl);
                post.setType(type);
                post.setTitle(title);
                post.setAuthor(author);
                post.setPubTime(pubTime);
                post.setLastReplyMan(lastReplyUser);
                post.setLastReplyTime(lastReplyTime);
                post.setReplyCount(Integer.valueOf(replyCount));
                post.setViewCount(Integer.valueOf(viewCount));

                postList.add(post);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return postList;
    }
}
