package com.yuikya.pcbeta.app;

import android.app.Application;
import android.test.ApplicationTestCase;
import android.util.Log;

import com.yuikya.pcbeta.app.util.MD5;
import com.yuikya.pcbeta.app.util.OkHttpClientManager;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class ApplicationTest extends ApplicationTestCase<Application> {
    public ApplicationTest() {
        super(Application.class);


    }

    public void test() {
        String url = "http://bbs.pcbeta.com/member.php?mod=logging&action=login";
        Log.d("tag",MD5.stringToMD5("123456"));

    }


}