package app.codelabs.roadtrip.helpers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import app.codelabs.roadtrip.models.MemberProfile;
import app.codelabs.roadtrip.models.ResponseAbout;
import app.codelabs.roadtrip.models.ResponseAboutUs;
import app.codelabs.roadtrip.models.ResponseAddItem;
import app.codelabs.roadtrip.models.ResponseAnswerQuestioner;
import app.codelabs.roadtrip.models.ResponseApi;
import app.codelabs.roadtrip.models.ResponseArticleCategory;
import app.codelabs.roadtrip.models.ResponseBookmarkShop;
import app.codelabs.roadtrip.models.ResponseCategoryExplore;
import app.codelabs.roadtrip.models.ResponseCheckNra;
import app.codelabs.roadtrip.models.ResponseDefault;
import app.codelabs.roadtrip.models.ResponseDetailShopItem;
import app.codelabs.roadtrip.models.ResponseDoBookmark;
import app.codelabs.roadtrip.models.ResponseEventDetail;
import app.codelabs.roadtrip.models.ResponseFinishPassword;
import app.codelabs.roadtrip.models.ResponseFollowsModel;
import app.codelabs.roadtrip.models.ResponseForgotPassword;
import app.codelabs.roadtrip.models.ResponseGallery;
import app.codelabs.roadtrip.models.ResponseHighlight;
import app.codelabs.roadtrip.models.ResponseListChatInRoom;
import app.codelabs.roadtrip.models.ResponseListLocation;
import app.codelabs.roadtrip.models.ResponseListShopByCategories;
import app.codelabs.roadtrip.models.ResponseLogin;
import app.codelabs.roadtrip.models.ResponseParticipantEvent;
import app.codelabs.roadtrip.models.ResponseQuestion;
import app.codelabs.roadtrip.models.ResponseRoomChatDetail;
import app.codelabs.roadtrip.models.ResponseSchedule;
import app.codelabs.roadtrip.models.ResponseShareArticle;
import app.codelabs.roadtrip.models.ResponseShopCategory;
import app.codelabs.roadtrip.models.ResponseUnFollow;
import app.codelabs.roadtrip.models.ResponseUnjoinEvent;
import app.codelabs.roadtrip.models.ResponseVote;
import app.codelabs.roadtrip.models.ResponseArticleDetail;
import app.codelabs.roadtrip.models.ResponseBookmarkArticle;
import app.codelabs.roadtrip.models.ResponseBookmarkEvent;
import app.codelabs.roadtrip.models.ResponseChatRoomList;
import app.codelabs.roadtrip.models.ResponseFollow;
import app.codelabs.roadtrip.models.ResponseJoinEvent;
import app.codelabs.roadtrip.models.ResponseListArticle;
import app.codelabs.roadtrip.models.ResponseListEventCommunity;
import app.codelabs.roadtrip.models.ResponseListMemberCompany;
import app.codelabs.roadtrip.models.ResponseMyProfile;
import app.codelabs.roadtrip.models.ResponseSubmitPassword;
import app.codelabs.roadtrip.models.ResponseUpdateProfile;
import app.codelabs.roadtrip.models.ResponseVoting;
import app.codelabs.roadtrip.models.ResponseWalkThrough;
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

    @POST("auth/register")
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

    @GET("api/about-us")
    Call<ResponseAboutUs> getAboutUs();

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
    @POST("api/add-item")
    Call<ResponseAddItem> addItemShop(
            @PartMap Map<String, RequestBody> data,
            @Part List<MultipartBody.Part> fileImagePart);

    @Multipart
    @POST("api/edit-item")
    Call<ResponseAddItem> editItemShop(
            @PartMap Map<String, RequestBody> data,
            @Part List<MultipartBody.Part> fileImagePart);

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

    @POST("api/remove-item")
    Call<ResponseUpdateProfile> removeItem(@Query("user_store_item_id") int itemId);

    @POST("api/remove-item-image")
    Call<ResponseUpdateProfile> removeItemImage(@Query("user_store_item_image_id") int itemId);

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

    @GET("api/place/category")
    Call<ResponseCategoryExplore> getExploreByCategories();

    @GET("api/place")
    Call<ResponseListLocation> getListByCategories(@Query("place_category_id") int referenceId, @Query("keyword") String search);

    @GET("api/place/detail/{id}")
    Call<ResponseDetailExplore> getDetailExplore(@Path("id") int id);

}

