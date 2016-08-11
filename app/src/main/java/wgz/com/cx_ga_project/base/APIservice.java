package wgz.com.cx_ga_project.base;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import rx.Observable;
import wgz.com.cx_ga_project.bean.JiaBan;
import wgz.com.cx_ga_project.bean.UserBean;
import wgz.com.cx_ga_project.entity.Apply;

/**
 * Created by wgz on 2016/8/1.
 */

public interface APIservice  {
    /**
     * 提交加班申请
     *
     */
    @FormUrlEncoded
    @POST("{type}")
    Observable<String> upOverTime(@Path("type") String type,
                                  @Field("starttime") String starttime,
                                  @Field("endtime") String endtime,
                                  @Field("content") String content,
                                  @Field("policeid")String policeid,
                                  @Field("applytime") String applytime,
                                  @Field("upperid") String upperid
                                  ) ;

    /**
     * 提交请假申请
     * @param type
     * @param starttime
     * @param endtime
     * @param content
     * @param policeid
     * @param applytime
     * @param upperid
     * @return
     */
    @FormUrlEncoded
    @POST("{type}")
    Observable<String> upLoadLeave(@Path("type") String type,
                                   @Field("starttime") String starttime,
                                   @Field("endtime") String endtime,
                                   @Field("content") String content,
                                   @Field("policeid")String policeid,
                                   @Field("applytime") String applytime,
                                   @Field("upperid") String upperid,
                                   @Field("reasontype") String reasontype,
                                   @Field("days") String days
                                   );




    /**
     * 获取加班请假申请信息
     * @param type
     * @return
     */
    @GET("{type}")
    Observable<Apply> getBeanData(@Path("type") String type);

    /**
     * 登陆
     * @param username
     * @param password
     * @return
     */
    @FormUrlEncoded
    @POST("ceshi/denglu")
    Observable<UserBean> UserLogin(@Field("username") String username,
                                   @Field("password") String password);




}
