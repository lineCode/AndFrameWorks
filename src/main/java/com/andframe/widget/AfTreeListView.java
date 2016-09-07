package com.andframe.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.ListView;

import com.andframe.api.page.Pager;
import com.andframe.widget.pulltorefresh.PullRefreshFooterImpl;
import com.andframe.widget.pulltorefresh.PullRefreshHeaderImpl;

/**
 * 可下拉刷新的 上啦更多的 listview 
 * @author 树朾
 *
 */
@SuppressWarnings("unused")
public class AfTreeListView extends com.andframe.widget.treeview.AfTreeListView {

	private static ListView mlistView = null;

	public AfTreeListView(ListView listView) {
		super((mlistView=listView).getContext());
		setPullFooterLayout(new PullRefreshFooterImpl(listView.getContext()));
		setPullHeaderLayout(new PullRefreshHeaderImpl(listView.getContext()));
	}

	public AfTreeListView(Pager viewable, int res) {
		super((mlistView=viewable.findViewByID(res)).getContext());
		setPullFooterLayout(new PullRefreshFooterImpl(viewable.getContext()));
		setPullHeaderLayout(new PullRefreshHeaderImpl(viewable.getContext()));
	}

	public AfTreeListView(Context context) {
		super(context);
		setPullFooterLayout(new PullRefreshFooterImpl(context));
		setPullHeaderLayout(new PullRefreshHeaderImpl(context));
	}

	public AfTreeListView(Context context, AttributeSet attrs) {
		super(context, attrs);
		setPullFooterLayout(new PullRefreshFooterImpl(context));
		setPullHeaderLayout(new PullRefreshHeaderImpl(context));
	}

	public AfTreeListView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		setPullFooterLayout(new PullRefreshFooterImpl(context));
		setPullHeaderLayout(new PullRefreshHeaderImpl(context));
	}

	@Override
	protected ListView onCreateTargetView(Context context, AttributeSet attrs) {
		if (mlistView != null) {
			if (getParent() == null && mlistView.getParent() instanceof ViewGroup) {
				ViewGroup parent = ViewGroup.class.cast(mlistView.getParent());
				int index = parent.indexOfChild(mlistView);
				parent.removeView(mlistView);
				parent.addView(this, index,mlistView.getLayoutParams());
				mTargetView = mlistView;
				mlistView = null;
			}
			return mTargetView;
		}
		return new ListView(context);
	}

	@Override
	protected ListView onCreateRefreshableView(Context context, AttributeSet attrs) {
		mAbsListView = onCreateTargetView(context, attrs);//new ListView(context)
		// 解决listview在拖动的时候背景图片消失变成黑色背景
		mAbsListView.setCacheColorHint(0);
		mAbsListView.setScrollingCacheEnabled(false);
		// 解决listview的上边和下边有黑色的阴影
		mAbsListView.setFadingEdgeLength(0);
		return mAbsListView;
	}
}
