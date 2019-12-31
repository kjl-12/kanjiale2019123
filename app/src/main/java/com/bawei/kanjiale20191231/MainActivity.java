package com.bawei.kanjiale20191231;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.OrientationHelper;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import okhttp3.internal.Version;

import android.os.Bundle;

import com.bawei.adapter.MyAdapter;
import com.bawei.api.Api;
import com.bawei.base.BaseActivity;
import com.bawei.contract.Contract;
import com.bawei.entity.StudentEntity;
import com.bawei.presenter.Presenter;


public class MainActivity extends BaseActivity<Presenter> implements Contract.IView {


    @BindView(R.id.rv)
    RecyclerView rv;


    @Override
    protected void initData() {
        mPresenter.getStudent(Api.BASE_URL);
    }

    @Override
    protected void initView() {
        rv.setLayoutManager(new GridLayoutManager(this,2));
    }

    @Override
    protected Presenter initPresenter() {
        return new Presenter();
    }

    @Override
    protected int layoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void success(StudentEntity studentEntity) {
        MyAdapter myAdapter = new MyAdapter(this,studentEntity.getRanking());
        rv.setAdapter(myAdapter);
    }

    @Override
    public void failure(Throwable throwable) {

    }


    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }
}
