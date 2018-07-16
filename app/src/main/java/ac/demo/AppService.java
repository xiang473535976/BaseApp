package ac.demo;


import bm.baseapp.http.retrofit.bean.Dto;
import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by ac on 2017/3/25.
 */

public interface AppService {

    /**
     * 用户注册接口  验证验证码
     *
     * @param phonenum 手机号
     * @param msgcode  验证码 （测试期间验证码是123456）
     * @return
     */

    @GET("app_regist_first")
    Observable<Dto<String>> app_regist_first(@Query("phonenum") String phonenum, @Query("msgcode") String msgcode);

    /**
     * @param markid 其他用户id
     * @return 用户查看其他用户信息
     */
    @FormUrlEncoded
    @POST("app_user_detail_aut")
    Observable<Dto<String>> app_user_detail_aut(@Field("markid") String markid);

}
