package app.codelabs.forum.helpers;

public interface ApiService {

    @POST("api/app_token")
    Call<ResponseApi>apptoken(@Body Map<String,String> body);

}
