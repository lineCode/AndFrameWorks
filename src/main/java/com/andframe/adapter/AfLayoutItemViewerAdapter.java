package com.andframe.adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.andframe.annotation.interpreter.LayoutBinder;

import java.util.List;

/**
 * 自带布局的适配器
 * Created by SCWANG on 2016/9/2.
 */
@SuppressWarnings("unused")
public abstract class AfLayoutItemViewerAdapter<T> extends AfItemViewerAdapter<T> {

    int mLayoutId = -1;

    public AfLayoutItemViewerAdapter(Context context, List<T> ltdata) {
        super(context, ltdata);
        initLayout(context);
    }

    public AfLayoutItemViewerAdapter(Context context, List<T> ltdata, boolean dataSync) {
        super(context, ltdata, dataSync);
        initLayout(context);
    }

    public AfLayoutItemViewerAdapter(@LayoutRes int layoutId, Context context, List<T> ltdata) {
        super(context, ltdata);
        mLayoutId = layoutId;
    }

    public AfLayoutItemViewerAdapter(@LayoutRes int layoutId, Context context, List<T> ltdata, boolean dataSync) {
        super(context, ltdata, dataSync);
        mLayoutId = layoutId;
    }

    protected void initLayout(Context context) {
        int layoutId = LayoutBinder.getBindLayoutId(this, context);
        if (layoutId > 0) {
            mLayoutId = layoutId;
        } else {
            throw new RuntimeException("请使用BindLayout注解标记你的适配器！");
        }
    }

    @Override
    protected View onCreateItemView(Context context, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(mLayoutId, parent, false);
    }

}
