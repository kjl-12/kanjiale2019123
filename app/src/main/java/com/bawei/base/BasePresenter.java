package com.bawei.base;

import java.lang.ref.WeakReference;

/**
 * <p>文件描述：<p>
 * <p>作者：冷瞳<p>
 * <p>创建时间：2019/12/31<p>
 * <p>更改时间：2019/12/31<p>
 * <p>版本号：1<p>
 */
public abstract class BasePresenter<M extends IBaseModel,V extends IBaseView> {
    public M model;
    private WeakReference<V> mWeak;

    public BasePresenter() {
        model = initModel();
    }

    protected abstract M initModel();

    public void attach(V v){
        mWeak = new WeakReference(v);
    }

    public void detach(){
        if (mWeak != null){
            mWeak.clear();
            mWeak = null;
        }
    }
    public V getView(){
        return mWeak.get();
    }
}
