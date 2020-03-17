package app.codelabs.forum.helpers;

import java.util.Map;

import app.codelabs.forum.models.ResponseApi;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiService {

    @POST("api/app_token")
    Call<ResponseApi>apptoken(@Body Map<String,String> body);

}
