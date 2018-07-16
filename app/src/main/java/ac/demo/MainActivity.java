package ac.demo;

import android.os.Bundle;

import com.trello.rxlifecycle2.components.RxActivity;

import bm.baseapp.http.BaseSubscriber;
import bm.baseapp.http.retrofit.bean.Dto;
import bm.baseapp.util.RxUtil;

public class MainActivity extends RxActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //网路请求的列子
        X.getAppService().app_user_detail_aut("1")
                .compose(RxUtil.io_main())
                .compose(bindToLifecycle())
                .subscribe(new BaseSubscriber<Dto<String>>() {
                    @Override
                    public void onSuccess(Dto<String> stringDto) {

                    }
                });
    }
}
