package com.bawei.base;

import android.os.Bundle;

import com.bawei.contract.Contract;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * <p>文件描述：<p>
 * <p>作者：冷瞳<p>
 * <p>创建时间：2019/12/31<p>
 * <p>更改时间：2019/12/31<p>
 * <p>版本号：1<p>
 */
public abstract class BaseActivity <P extends BasePresenter> extends AppCompatActivity implements Contract.IView {
    public P mPresenter;
    Unbinder unbinder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layoutId());
        unbinder = ButterKnife.bind(this);

        mPresenter = initPresenter();
        if (mPresenter != null){
            mPresenter.attach(this);
        }
        initView();
        initData();
    }

    protected abstract void initData();

    protected abstract void initView();

    protected abstract P initPresenter();

    protected abstract int layoutId();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null){
            mPresenter.detach();
        }
        if (unbinder != null){
            unbinder.unbind();
        }
    }
}
