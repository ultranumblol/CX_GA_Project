package wgz.com.cx_ga_project.base;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import rx.Observable;
import wgz.com.cx_ga_project.bean.UserBean;

/**
 * Created by wgz on 2016/8/1.
 */

public interface APIservice  {
    @FormUrlEncoded
    @POST("http://wuliu.chinaant.com/AppDespacthingInfo.aspx")
    Call<ResponseBody> getWorkXML(@Field("username") String username, @Field("state") String state,
                                  @Field("sign") String sign);
    /*@FormUrlEncoded
    @POST("http://192.168.1.88/demojob/getAllSch")
    Call<String> getZhiBan();
    */
    @GET("http://192.168.1.88/demojob/getAppAllSch")
    Call<String> getZhiBan();


    @FormUrlEncoded
    @POST("ceshi/denglu")
    Observable<UserBean> UserLogin(@Field("username") String username,
                                   @Field("password") String password);


}
