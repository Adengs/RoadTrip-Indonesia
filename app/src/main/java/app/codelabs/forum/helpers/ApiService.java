package app.codelabs.forum.helpers;

import java.util.Map;

import app.codelabs.forum.models.ResponMyProfile;
import app.codelabs.forum.models.ResponWalkThrough;
import app.codelabs.forum.models.ResponsArticleLatest;
import app.codelabs.forum.models.ResponsFollow;
import app.codelabs.forum.models.ResponsListMemberCompany;
import app.codelabs.forum.models.ResponsLogin;
import app.codelabs.forum.models.ResponsUnFollow;
import app.codelabs.forum.models.ResponseApi;
import app.codelabs.forum.models.ResponseRegister;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {

    @POST("api/app_token")
    Call<ResponseApi>apptoken(@Body Map<String,String> body);

    @POST ("auth/register")
    Call<ResponseRegister> register(@Body Map<String, String> body , @Header("app-token") String AppToken);

    @POST("auth/signin")
    Call<ResponsLogin> login (@Body Map<String, String> body, @Header("app-token")String apptoken);


    @GET("api/walk_through")
    Call<ResponWalkThrough> Walkthrough(@Header("app-token") String AppToken);

    @GET("api/member")
    Call<ResponsListMemberCompany> listMember (@Header ("Authorization")String token , @Header ("app-token")String AppToken, @Query("search") String search);

    @GET("api/profile")
    Call<ResponMyProfile> myprofile (@Header ("Authorization")String token , @Header("app-token") String AppToken);

    @GET("api/article/list")
    Call<ResponsArticleLatest> articlelatest (@Header("app-token") String AppToken , @Header ("Authorization")String token);

    @POST("api/follow")
    Call<ResponsFollow> follow (@Body Map<String, String> body , @Header("app-token") String AppToken , @Header ("Authorization")String token);

    @POST("api/unfollow")
    Call<ResponsUnFollow> unfollow (@Body Map<String, String> body, @Header("app-token") String AppToken , @Header ("Authorization")String token);
}

