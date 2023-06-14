package app.codelabs.fevci.helpers;

import java.util.HashMap;
import java.util.Map;

import app.codelabs.fevci.models.MemberProfile;
import app.codelabs.fevci.models.ResponseAbout;
import app.codelabs.fevci.models.ResponseAnswerQuestioner;
import app.codelabs.fevci.models.ResponseApi;
import app.codelabs.fevci.models.ResponseArticleCategory;
import app.codelabs.fevci.models.ResponseBookmarkShop;
import app.codelabs.fevci.models.ResponseCheckNra;
import app.codelabs.fevci.models.ResponseDefault;
import app.codelabs.fevci.models.ResponseDetailShopItem;
import app.codelabs.fevci.models.ResponseDoBookmark;
import app.codelabs.fevci.models.ResponseEventDetail;
import app.codelabs.fevci.models.ResponseFinishPassword;
import app.codelabs.fevci.models.ResponseFollowsModel;
import app.codelabs.fevci.models.ResponseForgotPassword;
import app.codelabs.fevci.models.ResponseGallery;
import app.codelabs.fevci.models.ResponseHighlight;
import app.codelabs.fevci.models.ResponseListChatInRoom;
import app.codelabs.fevci.models.ResponseListShopByCategories;
import app.codelabs.fevci.models.ResponseLogin;
import app.codelabs.fevci.models.ResponseParticipantEvent;
import app.codelabs.fevci.models.ResponseQuestion;
import app.codelabs.fevci.models.ResponseRoomChatDetail;
import app.codelabs.fevci.models.ResponseSchedule;
import app.codelabs.fevci.models.ResponseShareArticle;
import app.codelabs.fevci.models.ResponseShopCategory;
import app.codelabs.fevci.models.ResponseUnFollow;
import app.codelabs.fevci.models.ResponseUnjoinEvent;
import app.codelabs.fevci.models.ResponseVote;
import app.codelabs.fevci.models.ResponseArticleDetail;
import app.codelabs.fevci.models.ResponseBookmarkArticle;
import app.codelabs.fevci.models.ResponseBookmarkEvent;
import app.codelabs.fevci.models.ResponseChatRoomList;
import app.codelabs.fevci.models.ResponseFollow;
import app.codelabs.fevci.models.ResponseJoinEvent;
import app.codelabs.fevci.models.ResponseListArticle;
import app.codelabs.fevci.models.ResponseListEventCommunity;
import app.codelabs.fevci.models.ResponseListMemberCompany;
import app.codelabs.fevci.models.ResponseMyProfile;
import app.codelabs.fevci.models.ResponseSubmitPassword;
import app.codelabs.fevci.models.ResponseUpdateProfile;
import app.codelabs.fevci.models.ResponseVoting;
import app.codelabs.fevci.models.ResponseWalkThrough;
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
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {

    @POST("api/app_token")
    Call<ResponseApi> getAppToken(@Body Map<String, String> body);

    @POST("auth/signin")
    Call<ResponseLogin> login(@Body Map<String, String> body);

    @POST("api/register")
    Call<ResponseDefault> register(@Body Map<String, String> body);

    @GET("api/walk_through")
    Call<ResponseWalkThrough> getWalkTrough();

    @GET("api/member")
    Call<ResponseListMemberCompany> listMember(@Query("search") String search);

    @GET("api/member/{id}")
    Call<MemberProfile> getDetailMember(@Path("id") int id);

    @GET("api/profile")
    Call<ResponseMyProfile> getProfile();

    @GET("api/article/list")
    Call<ResponseListArticle> getLatestArticle();

    @GET("api/article/list?sort=popular")
    Call<ResponseListArticle> getPopularArticle();

    @POST("api/follow")
    Call<ResponseFollow> follow(@Body Map<String, String> body);

    @POST("api/unfollow")
    Call<ResponseUnFollow> unFollow(@Body Map<String, String> body);

    @POST("recovery")
    Call<ResponseForgotPassword> requestResetPassword(@Body Map<String, String> body);

    @POST("verify_code")
    Call<ResponseSubmitPassword> verifyCodeResetPassword(@Body Map<String, String> body);

    @POST("set_new_password")
    Call<ResponseFinishPassword> resetPassword(@Body Map<String, String> body, @Header("X-Reset-Token") String xReset);

    @GET("api/article/detail")
    Call<ResponseArticleDetail> getArticle(@Query("article_id") int id);

    @GET("api/event/list")
    Call<ResponseListEventCommunity> getListEvent();

    @POST("api/event/join")
    Call<ResponseJoinEvent> joinEvent(@Body Map<String, String> body);

    @POST("api/event/unjoin")
    Call<ResponseUnjoinEvent> unJoin(@Body Map<String, String> body);

    @GET("api/event/my_event")
    Call<ResponseListEventCommunity> getListMyEvent();

    @GET("api/event/detail")
    Call<ResponseEventDetail> getEvent(@Query("event_id") int id);

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
    Call<ResponseUpdateProfile> updateProfile(
            @PartMap Map<String, RequestBody> data,
            @Part MultipartBody.Part fileImagePart);

    @Multipart
    @POST("api/update_profile")
    Call<ResponseUpdateProfile> updateProfileKtp(
            @Part MultipartBody.Part fileImagePartKtp);

    @Multipart
    @POST("api/update_profile")
    Call<ResponseUpdateProfile> updateProfileSim(
            @Part MultipartBody.Part fileImagePartSim);

    @GET("api/shop/category")
    Call<ResponseShopCategory> getShopCategories();

    @GET("api/shop/list")
    Call<ResponseListShopByCategories> getShopByCategories(@Query("category_id") int referenceId, @Query("search") String search);

    @GET("api/shop/detail")
    Call<ResponseDetailShopItem> getShopDetailItem(@Query("item_id") int itemId);

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

    @GET("api/bookmark/shop")
    Call<ResponseBookmarkShop> getBookmarkShop();

    @GET("api/event/schedule")
    Call<ResponseSchedule> getSchedule(@Query("event_id") int id);

    @FormUrlEncoded
    @POST("api/bookmark/wishlist")
    Call<ResponseDoBookmark> doBookmarkShop(@Field("module_id") int id, @Field("module_name") String moduleName);

    @GET("api/chat/room_list_member/{user_id}")
    Call<ResponseChatRoomList> getRoomList(@Path("user_id") int userId);

    @GET("api/chat/room_detail/{room_id}")
    Call<ResponseRoomChatDetail> getRoomDetail(@Path("room_id") int roomId);

    @GET("api/chat/chat_in_room/{room_id}")
    Call<ResponseListChatInRoom> getListChatInRoom(@Path("room_id") int roomId, @Query("limit") int limit, @Query("offset") int offset);

    @GET("api/questionnaire/question")
    Call<ResponseQuestion> getQuestions(@Query("voteId")int voteId);

    @POST("api/questionnaire/response")
    Call<ResponseAnswerQuestioner> answerQuestioner(@Body HashMap<String,Integer> data);

    @GET("api/followed")
    Call<ResponseFollowsModel> getFollowed(@Query("search") String search);
    @GET("api/follower")
    Call<ResponseFollowsModel> getFollower(@Query("search") String search);

    @POST("api/check/nra")
    Call<ResponseCheckNra> checkNra(@Query("nra") String nra);

}
