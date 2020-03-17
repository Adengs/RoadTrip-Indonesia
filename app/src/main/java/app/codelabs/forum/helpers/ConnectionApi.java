package app.codelabs.forum.helpers;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ConnectionApi {
    public static ApiService apiService(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://104.248.149.236/qwerty/forum/service/gateway/public/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(ApiService.class);
    }
}
