package bm.baseapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.trello.rxlifecycle2.components.support.RxFragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public abstract class BaseFragment extends RxFragment {
    protected abstract int layoutResId();

    private View contentView;

    protected abstract void initView(Bundle savedInstanceState);

    /*** 懒加载数据*/
    protected abstract void lazyLoad();

    /**
     * 是否可见状态 为了避免和[Fragment.isVisible]冲突 换个名字
     */
    protected boolean isFragmentVisible;

    /**
     * 第一次加载成功
     * 注意   需要在请求成功后    改
     * isFirstLoadSuccess=true
     * 如果需要强制刷新   也可改变这个参数
     * isFirstLoadSuccess=false   等会Fragment可见时  自动执行刷新
     */
    protected boolean isFirstLoadSuccess;
    protected boolean addToSmartRefreshLayout;

    protected abstract void initListener();

    protected SmartRefreshLayout smartRefreshLayout;
    protected Boolean isPrepared;//是否初始化完成

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        initBeforeSetContentView();
        if (addToSmartRefreshLayout)
            addToSmartRefreshLayout(inflater);
        else
            contentView = inflater.inflate(layoutResId(), container, false);
        return contentView;
    }

    protected void initBeforeSetContentView() {

    }

    /**
     * 实现刷新功能
     */
    private void addToSmartRefreshLayout(LayoutInflater inflater) {
        smartRefreshLayout.setRefreshContent(inflater.inflate(layoutResId(), null));
        contentView = smartRefreshLayout;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(savedInstanceState);
        initListener();
        isPrepared = true;
        lazyLoad();
    }

    /**
     * 如果是通过FragmentTransaction的show和hide的方法来控制显示，调用的是onHiddenChanged.
     * 若是初始就show的Fragment 为了触发该事件 需要先hide再show
     *
     * @param hidden hidden True if the fragment is now hidden, false if it is not visible.
     *               需要先hide再show
     *               需要先hide再show
     *               需要先hide再show
     */
    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
            onVisible();
        } else {
            onInvisible();
        }
    }

    /**
     * 如果是与ViewPager一起使用，调用的是setUserVisibleHint
     *
     * @param isVisibleToUser 是否显示出来了
     */
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            onVisible();
        } else {
            onInvisible();
        }
    }


    /**
     * 要实现延迟加载Fragment内容,需要在 onCreateView
     * isPrepared = true;
     */
    private void loadDate() {
        if (isPrepared && isFragmentVisible) {
            if (!isFirstLoadSuccess) {
                lazyLoad();
            }
        }
    }

    private void onInvisible() {
        isFragmentVisible = false;
    }

    private void onVisible() {
        isFragmentVisible = true;
        loadDate();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        isPrepared = false;
    }
}
