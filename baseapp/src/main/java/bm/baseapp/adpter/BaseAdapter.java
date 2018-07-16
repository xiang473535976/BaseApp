package bm.baseapp.adpter;


import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;

/**
 * author 艾晨
 * Created at 2018/7/16 14:39
 * Update at 2018/7/16 14:39
 * Update people:
 * Version:1.0
 * 说明：
*/

public abstract class BaseAdapter<T> extends BaseQuickAdapter<T, BaseViewHolder> {


    public BaseAdapter(int layoutResId, @Nullable List<T> data) {
        super(layoutResId, data);
        openLoadAnimation(ALPHAIN);  //开启列表动画
    }

    public BaseAdapter(int layoutResId) {
        super(layoutResId, new ArrayList<T>());
        openLoadAnimation(ALPHAIN);  //开启列表动画
    }
}
