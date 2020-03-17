package app.codelabs.forum.helpers;

import java.util.Map;

import app.codelabs.forum.models.ResponsLogin;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiService {

    @POST("auth/signin")
    Call<ResponsLogin> login (@Body Map<String, String> body);

}
