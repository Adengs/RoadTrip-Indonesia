package app.codelabs.forum.helpers;

import java.util.Map;

import app.codelabs.forum.models.ResponWalkThrough;
import app.codelabs.forum.models.ResponsListMemberCompany;
import app.codelabs.forum.models.ResponsLogin;
import app.codelabs.forum.models.ResponseApi;
import app.codelabs.forum.models.ResponseForgotPassword;
import app.codelabs.forum.models.ResponseSubmitPassword;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiService {

    @POST("api/app_token")
    Call<ResponseApi>apptoken(@Body Map<String,String> body);

    @POST("auth/signin")
    Call<ResponsLogin> login (@Body Map<String, String> body, @Header("app-token")String apptoken);



    @GET("api/walk_through")
    Call<ResponWalkThrough> Walkthrough(@Header("app-token") String AppToken);

    @GET("api/member")
    Call<ResponsListMemberCompany> listMember (@Header ("Authorization")String token , @Header ("app-token")String AppToken, @Query("search") String seacrh);

    @POST("recovery")
    Call<ResponseForgotPassword> forgotpassword(@Body Map<String, String> body, @Header("app-token") String apptoken);

    @POST("verify_code")
    Call<ResponseSubmitPassword> submitpassword(@Body Map<String, String> body, @Header("app-token") String apptoken);





}
