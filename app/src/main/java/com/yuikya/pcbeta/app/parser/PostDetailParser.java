package com.yuikya.pcbeta.app.parser;

import android.util.Log;

import com.yuikya.pcbeta.app.model.PostDetail;
import com.yuikya.pcbeta.app.util.OkHttpUtil;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by fan on 2015/9/17.
 */
public class PostDetailParser {

    public List<PostDetail> getPostDetails(String url){
        List<PostDetail> list = new ArrayList<>();
        try {
            Document doc = Jsoup.parse(OkHttpUtil.getStringFromServer(url));
            Elements elements = doc.select("div[id^=post_]");
            for(Element element:elements){
                PostDetail pd = new PostDetail();
                Log.d("tag",element.html());
//                String username = element.select("div.authi > a").get(0).html();
//                String time = element.select("em[id^=authorposton]").get(0).html();
//                String uid = element.select("dl.cl").get(0).select("dd:nth-child(12)").html();
//                String avatar = element.select("div.avatar img").attr("src");
//                String floor = element.select("div.pi > strong > a").html();
//                String content = element.select("[id^=postmessage_]").html();
//                pd.setUsername(username);
//                pd.setTime(time);
//                pd.setUid(uid);
//                pd.setAvatar(avatar);
//                pd.setFloor(floor);
//                pd.setContent(content);
                list.add(pd);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }
}
