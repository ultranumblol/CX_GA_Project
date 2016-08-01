package wgz.com.cx_ga_project.base;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by wgz on 2016/8/1.
 */

public interface APIservice  {
    @FormUrlEncoded
    @POST("http://wuliu.chinaant.com/AppDespacthingInfo.aspx")
    Call<ResponseBody> getWorkXML(@Field("username") String username, @Field("state") String state,
                                  @Field("sign") String sign);

}
