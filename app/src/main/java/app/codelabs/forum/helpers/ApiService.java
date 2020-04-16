package app.codelabs.forum.helpers;

import java.util.List;
import java.util.Map;

import app.codelabs.forum.models.ResponseBookmarkArticle;
import app.codelabs.forum.models.ResponseBookmarkEvent;
import app.codelabs.forum.models.ResponseBookmarkShop;
import app.codelabs.forum.models.ResponseDoBookmark;
import app.codelabs.forum.models.ResponseMyProfile;
import app.codelabs.forum.models.ResponseShareArticle;
import app.codelabs.forum.models.ResponseVoting;
import app.codelabs.forum.models.ResponseWalkThrough;
import app.codelabs.forum.models.ResponseAbout;
import app.codelabs.forum.models.ResponseListShopByCategories;
import app.codelabs.forum.models.ResponseShopCategory;
import app.codelabs.forum.models.ResponseUnjoinEvent;
import app.codelabs.forum.models.ResponseArticleCategory;
import app.codelabs.forum.models.ResponseGallery;
import app.codelabs.forum.models.ResponseListArticle;
import app.codelabs.forum.models.ResponseDetailList;
import app.codelabs.forum.models.ResponseFollow;
import app.codelabs.forum.models.ResponseHighlight;
import app.codelabs.forum.models.ResponseJoinEvent;
import app.codelabs.forum.models.ResponseListArticelbyCategory;
import app.codelabs.forum.models.ResponseListEventCommunity;
import app.codelabs.forum.models.ResponseListMemberCompany;
import app.codelabs.forum.models.ResponseLogin;
import app.codelabs.forum.models.ResponseParticipantEvent;
import app.codelabs.forum.models.ResponseUnFollow;
import app.codelabs.forum.models.ResponseApi;
import app.codelabs.forum.models.ResponseArticleDetail;
import app.codelabs.forum.models.ResponseFinishPassword;
import app.codelabs.forum.models.ResponseForgotPassword;
import app.codelabs.forum.models.ResponseRegister;
import app.codelabs.forum.models.ResponseSubmitPassword;
import app.codelabs.forum.models.ResponseUpdateProfile;
import app.codelabs.forum.models.ResponseVote;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Query;

public interface ApiService {

    @POST("api/app_token")
    Call<ResponseApi> getAppToken(@Body Map<String, String> body);

    @POST("auth/signin")
    Call<ResponseLogin> login(@Body Map<String, String> body);

    @POST("auth/register")
    Call<ResponseRegister> register(@Body Map<String, String> body);

    @GET("api/walk_through")
    Call<ResponseWalkThrough> getWalkTrough();

    @GET("api/member")
    Call<ResponseListMemberCompany> listMember(@Query("search") String search);

    @GET("api/profile")
    Call<ResponseMyProfile> getProfile();

    @GET("api/article/list")
    Call<ResponseListArticle> getLatestArticle();

    @GET("api/article/list?sort=popular")
    Call<ResponseListArticle> getPopularArticle();

    @POST("api/follow")
    Call<ResponseFollow> follow(@Body Map<String, String> body);

    @POST("api/unfollow")
    Call<ResponseUnFollow> unfollow(@Body Map<String, String> body);

    @POST("recovery")
    Call<ResponseForgotPassword> requestResetPassword(@Body Map<String, String> body);

    @POST("verify_code")
    Call<ResponseSubmitPassword> verifyCodeResetPassword(@Body Map<String, String> body);

    @POST("set_new_password")
    Call<ResponseFinishPassword> resetPassword(@Body Map<String, String> body, @Header("X-Reset-Token") String xReset);

    @GET("api/article/detail")
    Call<ResponseArticleDetail> articledetail(@Header("Authorization") String token, @Header("app-token") String AppToken, @Query("article_id") int article_id);

    @GET("api/article/list")
    Call<ResponseListArticelbyCategory> listarticelbycategory(@Query("tag") List<String> tag, @Header("Authorization") String token, @Header("app-token") String AppToken);

    @GET("api/article/list")
    Call<ResponseDetailList> detailArticel(@Query("acticel_id") int id);

    @GET("api/event/list")
    Call<ResponseListEventCommunity> getListEvent();

    @POST("api/event/join")
    Call<ResponseJoinEvent> joinEvent(@Body Map<String, String> body);

    @POST("api/event/unjoin")
    Call<ResponseUnjoinEvent> unJoin(@Body Map<String, String> body);

    @GET("api/event/my_event")
    Call<ResponseListEventCommunity> getListMyEvent();

    @GET("api/article/list")
    Call<ResponseListArticelbyCategory> listArticle(@Query("category_id") Integer article_id);

    @GET("api/event/participant")
    Call<ResponseParticipantEvent> eventParticipant(@Query("event_id") Integer id);

    @GET("api/highlight")
    Call<ResponseHighlight> getHighlight();

    @GET("api/about")
    Call<ResponseAbout> getAboutCompany();

    @GET("api/article_category/list")
    Call<ResponseArticleCategory> getArticleCategories();

    @GET("api/article/list")
    Call<ResponseListArticle> getArticleByCategory(@Query("category_id") int referenceId);

    @GET("api/event/gallery")
    Call<ResponseGallery> getGallery();

    @Multipart
    @POST("api/update_profile")
    Call<ResponseUpdateProfile> updateProfile(@PartMap Map<String, RequestBody> data, @Part MultipartBody.Part fileImagePart);

    @GET("api/shop/category")
    Call<ResponseShopCategory> getShopCategories();

    @GET("api/shop/list")
    Call<ResponseListShopByCategories> getShopByCategories(@Query("category_id") int referenceId);

    @GET("api/vote")
    Call<ResponseVote> getVote();

    @FormUrlEncoded
    @POST("api/vote/select")
    Call<ResponseVoting> doVote(@Field("vote_id") int id, @Field("candidate_id") int user_id);

    @FormUrlEncoded
    @POST("api/bookmark/saved")
    Call<ResponseDoBookmark> doBookmark(@Field("module_id") int id, @Field("module_name") String moduleName);

    @GET("api/article/share")
    Call<ResponseShareArticle> doShareArticle(@Query("article_id") int id);

    @GET("api/bookmark/article")
    Call<ResponseBookmarkArticle> getBookmarkArticle();

    @GET("api/bookmark/event")
    Call<ResponseBookmarkEvent> getBookmarkEvent();

    @GET("api/bookmark/wishlist")
    Call<ResponseBookmarkShop> getBookmarkShop();
}

