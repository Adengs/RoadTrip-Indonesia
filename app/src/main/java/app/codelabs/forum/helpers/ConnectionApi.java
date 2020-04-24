package app.codelabs.forum.helpers;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import java.io.IOException;

import app.codelabs.forum.activities.login.LoginActivity;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ConnectionApi {
    public static ApiService apiService(final Context context) {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request()
                        .newBuilder().addHeader("app-token", Session.init(context).getAppToken())
                        .build();

                if (Session.init(context).isLogin()) {
                    request = request.newBuilder().addHeader("Authorization", Session.init(context).getToken()).build();
                }


                Response response = chain.proceed(request);
                if (response.code() == 401) {
                    Session.init(context).setLogout();
                    Intent intent = new Intent(context, LoginActivity.class);
                    Toast.makeText(context, "Session expired", Toast.LENGTH_LONG).show();
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);

                    return response;
                }
                return response;
            }
        });

        httpClient.addInterceptor(logging);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constant.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .build();
        return retrofit.create(ApiService.class);
    }
}
