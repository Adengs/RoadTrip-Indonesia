package app.codelabs.forum.helpers;

import java.util.Map;

import app.codelabs.forum.models.ResponWalkThrough;
import app.codelabs.forum.models.Respons;
import app.codelabs.forum.models.ResponsLogin;
import app.codelabs.forum.models.ResponseApi;
import app.codelabs.forum.models.ResponseRegister;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiService {

    @POST("api/app_token")
    Call<ResponseApi>apptoken(@Body Map<String,String> body);

    @POST ("auth/register")
    Call<ResponseRegister> register(@Body Map<String, String> body , @Header("app-token") String AppToken);

    @POST("auth/signin")
    Call<ResponsLogin> login (@Body Map<String, String> body, @Header("app-token")String apptoken);

    @GET("api/member")
    Call<Respons> listMember (@Header ("Authorization")String token , @Header ("app-token")String AppToken, @Query("search") String seacrh);

    @GET("api/walk_through")
    Call<ResponWalkThrough> Walkthrough(@Header("app-token") String AppToken);

}
