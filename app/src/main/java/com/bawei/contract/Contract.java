package com.bawei.contract;

import com.bawei.base.IBaseModel;
import com.bawei.base.IBaseView;
import com.bawei.entity.StudentEntity;

/**
 * <p>文件描述：<p>
 * <p>作者：冷瞳<p>
 * <p>创建时间：2019/12/31<p>
 * <p>更改时间：2019/12/31<p>
 * <p>版本号：1<p>
 */
public interface Contract {
    interface IModel extends IBaseModel {

        void getStudent(String url,ModelCallback callback);

        interface ModelCallback{
            void success(StudentEntity studentEntity);
            void failure(Throwable throwable);
        }

    }


    interface IView extends IBaseView{

        void success(StudentEntity studentEntity);
        void failure(Throwable throwable);

    }

    interface IPresenter{

        void getStudent(String url);

    }
}
