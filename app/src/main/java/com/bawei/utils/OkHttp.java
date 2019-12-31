package com.bawei.utils;

import android.os.Handler;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * <p>文件描述：<p>
 * <p>作者：冷瞳<p>
 * <p>创建时间：2019/12/31<p>
 * <p>更改时间：2019/12/31<p>
 * <p>版本号：1<p>
 */
public class OkHttp {
   Handler handler =  new Handler();

    private static OkHttp okHttp;
    private OkHttpClient okHttpClient;

   private OkHttp(){
        okHttpClient = new OkHttpClient.Builder()
                .readTimeout(5, TimeUnit.SECONDS)
                .connectTimeout(5,TimeUnit.SECONDS)
                .writeTimeout(5,TimeUnit.SECONDS)
                .build();
   }

   public static OkHttp getInstance(){
        if (okHttp == null){
            synchronized (OkHttp.class){
                if (okHttp == null){
                    okHttp = new OkHttp();
                }
            }
        }
        return okHttp;
   }

    public void doGet(String url,OkHttpCallback callback){
        Request request = new Request.Builder()
                .url(url)
                .get()
                .build();

        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                if (callback != null){
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            callback.error(e);
                        }
                    });
                }
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                if (callback != null){
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                callback.success(response.body().string());
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }
            }
        });
    }


    public void doPost(String url, HashMap<String,String> map, OkHttpCallback okHttpCallback){
        FormBody.Builder builder = new FormBody.Builder();

        for (Map.Entry<String,String> map1 : map.entrySet()){
            builder.add(map1.getKey(),map1.getValue());
        }

        Request request = new Request.Builder()
                .url(url)
                .post(builder.build())
                .build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                if (okHttpCallback != null){
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            okHttpCallback.error(e);
                        }
                    });
                }
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                if (okHttpCallback != null){
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                okHttpCallback.success(response.body().string());
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }
            }
        });
    }


    public interface OkHttpCallback{
       void success(String json);
       void error(Throwable throwable);
    }
}
