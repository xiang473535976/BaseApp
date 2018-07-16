package bm.baseapp.http;

import android.app.ProgressDialog;
import android.content.DialogInterface;

import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ToastUtils;

import bm.baseapp.http.retrofit.ErrorTips;
import bm.baseapp.http.retrofit.bean.Dto;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;


/**
 * author 艾晨
 * Created at 2018/7/13 16:56
 * Update at 2018/7/13 16:56
 * Update people:
 * Version:1.0
 * 说明：
*/


public abstract class BaseSubscriber<T extends Dto> implements Observer<T> {
    private ProgressDialog dialog;
    private Disposable d;

    public BaseSubscriber() {

    }

    public BaseSubscriber(boolean showDialog) {
        if (showDialog) {
            dialog = new ProgressDialog(ActivityUtils.getTopActivity());
            dialog.show();
            //取消弹窗，就取消了网络请求

            dialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                @Override
                public void onCancel(DialogInterface dialogInterface) {
                    if (!d.isDisposed())
                        d.dispose();
                }
            });
        }
    }


    @Override
    public void onComplete() {
        requestEnd();
    }

    @Override
    public void onError(Throwable t) {
        requestEnd();
        ToastUtils.showShort(ErrorTips.handleException(t).message);
        LogUtils.e("异常\n" + t);
    }

    @Override
    public void onNext(T t) {
        if (null != t)
            if (t.getCod() == 1) {
                onSuccess(t);
            } else {
                ToastUtils.showShort(t.getMsg());
            }
    }

    @Override
    public void onSubscribe(Disposable d) {
        this.d = d;
    }

    public abstract void onSuccess(T t);

    private void requestEnd() {
        if (null != dialog)
            dialog.dismiss();
    }
}
