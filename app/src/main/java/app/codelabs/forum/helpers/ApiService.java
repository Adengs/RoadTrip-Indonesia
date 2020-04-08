package app.codelabs.forum.helpers;

import java.util.List;
import java.util.Map;

import app.codelabs.forum.models.ResponArticlePopular;
import app.codelabs.forum.models.ResponMyProfile;
import app.codelabs.forum.models.ResponWalkThrough;
import app.codelabs.forum.models.ResponsAbout;
import app.codelabs.forum.models.ResponseArticleCategory;
import app.codelabs.forum.models.ResponseGallery;
import app.codelabs.forum.models.ResponseListArticle;
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
import retrofit2.http.Query;

public interface ApiService {

    @POST("api/app_token")
    Call<ResponseApi> getAppToken(@Body Map<String, String> body);

    @POST("auth/signin")
    Call<ResponsLogin> login(@Body Map<String, String> body);

    @POST("auth/register")
    Call<ResponseRegister> register(@Body Map<String, String> body);

    @GET("api/walk_through")
    Call<ResponWalkThrough> getWalkTrough();

    @GET("api/member")
    Call<ResponsListMemberCompany> listMember( @Query("search") String search);

    @GET("api/profile")
    Call<ResponMyProfile> getProfile();

    @GET("api/article/list")
    Call<ResponseListArticle> getLatestArticle();

    @GET("api/article/list?sort=popular")
    Call<ResponseListArticle> getPopularArticle();

    @POST("api/follow")
    Call<ResponsFollow> follow(@Body Map<String, String> body);

    @POST("api/unfollow")
    Call<ResponsUnFollow> unfollow(@Body Map<String, String> body);

    @POST("recovery")
    Call<ResponseForgotPassword> requestResetPassword(@Body Map<String, String> body);

    @POST("verify_code")
    Call<ResponseSubmitPassword> verifyCodeResetPassword(@Body Map<String, String> body);

    @POST("set_new_password")
    Call<ResponseFinishPassword> resetPassword(@Body Map<String, String> body, @Header("X-Reset-Token") String xReset);

    @GET("api/article/detail")
    Call<ResponseArticleDetail> articledetail(@Header("Authorization") String token, @Header("app-token") String AppToken, @Query("article_id") int article_id);

    @GET("api/article/list")
    Call<ResponsListArticelbyCategory> listarticelbycategory(@Query("tag") List<String> tag, @Header("Authorization") String token, @Header("app-token") String AppToken);

    @GET("api/article/list")
    Call<ResponsDetailList> detailArticel(@Query("acticel_id") int id);

    @GET("api/event/list")
    Call<ResponsListEventCommunity> listEvent();

    @POST("api/event/join")
    Call<ResponsJoinEvent> joinEvent(@Body Map<String, String> body);

    @GET("api/event/my_event")
    Call<ResponsMyEvent> myEvent();

    @GET("api/article/list")
    Call<ResponsListArticelbyCategory> listArticle(@Query("category_id") Integer article_id);

    @GET("api/event/participant")
    Call<ResponsParticipantEvent> eventParticipant(@Query("event_id") Integer id);

    @GET("api/highlight")
    Call<ResponsHighlight> getHighlight();

    @GET("api/about")
    Call<ResponsAbout> getAboutCompany();

    @GET("api/article_category/list")
    Call<ResponseArticleCategory> getArticleCategories();

    @GET("api/article/list")
    Call<ResponseListArticle> getArticleByCategory(@Query("category_id") int referenceId);

    @GET("api/event/gallery")
    Call<ResponseGallery> getGallery();
}

