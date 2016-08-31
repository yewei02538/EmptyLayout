package me.weyye.library;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

/**
 * Created by Administrator on 2016/5/13 0013.
 */
public class EmptyLayout extends FrameLayout {

    private Context mContext;
    private View mEmptyView;
    private View mBindView;
    private View mErrorView;
    private Button mBtnReset;
    private View mLoadingView;
    private TextView mEmptyText;
    private TextView tvLoadingText;

    public EmptyLayout(Context context) {
        this(context, null);
    }

    public EmptyLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public EmptyLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;

        LayoutParams params = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        //居中
        params.gravity = Gravity.CENTER;

        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.EmptyLayout, 0, 0);
        //数据为空时的布局
        int emptyLayout = ta.getResourceId(R.styleable.EmptyLayout_elEmptyLayout, R.layout.layout_empty);
        mEmptyView = View.inflate(context, emptyLayout, null);
        mEmptyText = (TextView) mEmptyView.findViewById(R.id.tvEmptyText);
        addView(mEmptyView, params);

        //加载中的布局
        int loadingLayout = ta.getResourceId(R.styleable.EmptyLayout_elLoadingLayout, R.layout.layout_loading);
        mLoadingView = View.inflate(context, loadingLayout, null);
        tvLoadingText = (TextView) mLoadingView.findViewById(R.id.tvLoadingText);
        addView(mLoadingView, params);

        //错误时的布局
        int errorLayout = ta.getResourceId(R.styleable.EmptyLayout_elErrorLayout, R.layout.layout_error);
        mErrorView = View.inflate(context, errorLayout, null);
        mBtnReset = (Button) mErrorView.findViewById(R.id.btnReset);
        addView(mErrorView, params);

        //全部隐藏
        setGone();
    }

    public void setEmptyView(int resId) {
        setEmptyView(View.inflate(mContext, resId, null));
    }

    public void setEmptyView(View v) {
        if (indexOfChild(mEmptyView) != -1) {
            removeView(mEmptyView);
        }
        mEmptyView = v;
        addView(mEmptyView);
        setGone();
    }

    public void bindView(View view) {
        mBindView = view;
    }

    public void showEmpty(String emptyText) {
        if (mBindView != null) mBindView.setVisibility(View.GONE);
        setGone();
        mEmptyView.setVisibility(View.VISIBLE);
        mEmptyText.setText(emptyText);
    }

    public void showError() {
        showError(null);
    }

    public void showError(String text) {
        if (mBindView != null) mBindView.setVisibility(View.GONE);
        if (!TextUtils.isEmpty(text)) mBtnReset.setText(text);
        setGone();
        mErrorView.setVisibility(View.VISIBLE);
    }

    public void showLoading(String text) {
        if (mBindView != null) mBindView.setVisibility(View.GONE);
        if (!TextUtils.isEmpty(text)) tvLoadingText.setText(text);
        setGone();
        mLoadingView.setVisibility(View.VISIBLE);
    }

    public void showLoading() {
        showLoading(null);
    }

    public void setOnButtonClick(OnClickListener listener) {
        mBtnReset.setOnClickListener(listener);
    }

    /**
     * 全部隐藏
     */
    private void setGone() {
        mEmptyView.setVisibility(View.GONE);
        mErrorView.setVisibility(View.GONE);
        mLoadingView.setVisibility(View.GONE);
    }

    public void showSuccess() {
        if (mBindView != null) mBindView.setVisibility(View.VISIBLE);
        setGone();
    }

}
