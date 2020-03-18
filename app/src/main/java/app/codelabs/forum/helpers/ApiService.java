package app.codelabs.forum.helpers;

import java.util.Map;

import app.codelabs.forum.models.ResponseApi;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiService {

    @POST("api/app_token")
    Call<ResponseApi>apptoken(@Body Map<String,String> body);

    @POST("auth/signin")
    Call<ResponsLogin> login (@Body Map<String, String> body, @Header("app-token")String apptoken);

    @GET("api/member")
    Call<Respons> listMember (@Header ("Authorization")String token , @Header ("app-token")String AppToken, @Query("search") String seacrh);
    @POST("}recovery")
    Call<ResponseForgotPassword> forgotpassword(@Body Map<String, String> body, @Header("app-token") String apptoken);



}
