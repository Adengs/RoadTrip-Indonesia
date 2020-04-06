package app.codelabs.forum.helpers;

import java.util.List;
import java.util.Map;

import app.codelabs.forum.models.ArticelCategory;
import app.codelabs.forum.models.ResponArticlePopular;
import app.codelabs.forum.models.ResponMyProfile;
import app.codelabs.forum.models.ResponWalkThrough;
import app.codelabs.forum.models.ResponsAbout;
import app.codelabs.forum.models.ResponsArticleLatest;
import app.codelabs.forum.models.ResponsDetailList;
import app.codelabs.forum.models.ResponsFollow;
import app.codelabs.forum.models.ResponsHighlight;
import app.codelabs.forum.models.ResponsJoinEvent;
import app.codelabs.forum.models.ResponsListArticelbyCategory;
import app.codelabs.forum.models.ResponsListEventCommunity;
import app.codelabs.forum.models.ResponsListMemberCompany;
import app.codelabs.forum.models.ResponsLogin;
import app.codelabs.forum.models.ResponsMyEvent;
import app.codelabs.forum.models.ResponsParticipantEvent;
import app.codelabs.forum.models.ResponsUnFollow;
import app.codelabs.forum.models.ResponseApi;
import app.codelabs.forum.models.ResponseArticleDetail;
import app.codelabs.forum.models.ResponseFinishPassword;
import app.codelabs.forum.models.ResponseForgotPassword;
import app.codelabs.forum.models.ResponseRegister;
import app.codelabs.forum.models.ResponseSubmitPassword;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {

    @POST("api/app_token")
    Call<ResponseApi> getAppToken(@Body Map<String, String> body);

    @POST("auth/signin")
    Call<ResponsLogin> login(@Body Map<String, String> body, @Header("app-token") String apptoken);

    @POST("auth/register")
    Call<ResponseRegister> register(@Body Map<String, String> body, @Header("app-token") String AppToken);

    @GET("api/walk_through")
    Call<ResponWalkThrough> getWalkTrough(@Header("app-token") String AppToken);

    @GET("api/article/popular")
    Call<ResponArticlePopular> popular(@Header("Authorization") String token, @Header("app-token") String AppToken, @Query("short") int sort);

    @GET("api/member")
    Call<ResponsListMemberCompany> listMember(@Header("Authorization") String token, @Header("app-token") String AppToken, @Query("search") String search);

    @GET("api/profile")
    Call<ResponMyProfile> myProfile(@Header("Authorization") String token, @Header("app-token") String AppToken);

    @GET("api/article/list")
    Call<ResponsArticleLatest> articlelatest(@Header("app-token") String AppToken, @Header("Authorization") String token);

    @POST("api/follow")
    Call<ResponsFollow> follow(@Body Map<String, String> body, @Header("app-token") String AppToken, @Header("Authorization") String token);

    @POST("api/unfollow")
    Call<ResponsUnFollow> unfollow(@Body Map<String, String> body, @Header("app-token") String AppToken, @Header("Authorization") String token);

    @POST("recovery")
    Call<ResponseForgotPassword> requestResetPassword(@Body Map<String, String> body, @Header("app-token") String apptoken);

    @POST("verify_code")
    Call<ResponseSubmitPassword> verifyCodeResetPassword(@Body Map<String, String> body, @Header("app-token") String apptoken);

    @POST("set_new_password")
    Call<ResponseFinishPassword> resetPassword(@Body Map<String, String> body, @Header("app-token") String apptoken, @Header("X-Reset-Token") String xReset);

    @GET("api/article/detail")
    Call<ResponseArticleDetail> articledetail(@Header("Authorization") String token, @Header("app-token") String AppToken, @Query("article_id") int article_id);

    @GET("api/article/list")
    Call<ResponsListArticelbyCategory> listarticelbycategory(@Query("tag") List<String> tag, @Header("Authorization") String token, @Header("app-token") String AppToken);

    @GET("api/article/list")
    Call<ResponsDetailList> detailArticel(@Query("acticel_id") int id, @Header("Authorization") String token, @Header("app-token") String AppToken);

    @GET("api/event/list")
    Call<ResponsListEventCommunity> listEvent(@Header("Authorization") String token, @Header("app-token") String AppToken);

    @POST("api/event/join")
    Call<ResponsJoinEvent> joinEvent(@Body Map<String, String> boby, @Header("Authorization") String token, @Header("app-token") String AppToken);

    @GET("api/event/my_event")
    Call<ResponsMyEvent> Myevent(@Header("Authorization") String token, @Header("app-token") String AppToken);

    @GET("api/article/list")
    Call<ResponsListArticelbyCategory> listarticel(@Query("category_id") Integer article_id, @Header("Authorization") String token, @Header("app-token") String AppToken);

    @GET("api/event/participant")
    Call<ResponsParticipantEvent> partisipanevent(@Query("event_id") Integer id, @Header("Authorization") String token, @Header("app-token") String AppToken);

    @GET("api/highlight")
    Call<ResponsHighlight> Highlight( @Header("Authorization") String token, @Header("app-token") String AppToken);

    @GET("api/about")
    Call<ResponsAbout> aboutCompany(@Header("Authorization") String token, @Header("app-token") String AppToken);

    @GET("api/article_category/list")
    Call<ArticelCategory> dataCategory(@Header("Authorization") String token, @Header("app-token") String AppToken);
}

