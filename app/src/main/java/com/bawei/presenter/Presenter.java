package com.bawei.presenter;

import com.bawei.base.BasePresenter;
import com.bawei.contract.Contract;
import com.bawei.entity.StudentEntity;
import com.bawei.model.Model;

/**
 * <p>文件描述：<p>
 * <p>作者：冷瞳<p>
 * <p>创建时间：2019/12/31<p>
 * <p>更改时间：2019/12/31<p>
 * <p>版本号：1<p>
 */
public class Presenter extends BasePresenter<Model, Contract.IView> implements Contract.IPresenter{
    @Override
    protected Model initModel() {
        return new Model();
    }

    @Override
    public void getStudent(String url) {
        model.getStudent(url, new Contract.IModel.ModelCallback() {
            @Override
            public void success(StudentEntity studentEntity) {
                getView().success(studentEntity);
            }

            @Override
            public void failure(Throwable throwable) {
                getView().failure(throwable);
            }
        });
    }
}
