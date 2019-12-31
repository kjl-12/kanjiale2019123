package com.bawei.model;

import com.bawei.contract.Contract;
import com.bawei.entity.StudentEntity;
import com.bawei.utils.OkHttp;
import com.google.gson.Gson;

/**
 * <p>文件描述：<p>
 * <p>作者：冷瞳<p>
 * <p>创建时间：2019/12/31<p>
 * <p>更改时间：2019/12/31<p>
 * <p>版本号：1<p>
 */
public class Model implements Contract.IModel {

    @Override
    public void getStudent(String url, ModelCallback callback) {
        OkHttp.getInstance().doGet(url, new OkHttp.OkHttpCallback() {
            @Override
            public void success(String json) {
                StudentEntity productEntity = new Gson().fromJson(json, StudentEntity.class);
                callback.success(productEntity);
            }

            @Override
            public void error(Throwable throwable) {
                callback.failure(throwable);
            }
        });
    }
}
