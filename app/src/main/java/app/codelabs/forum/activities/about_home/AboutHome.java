package app.codelabs.forum.activities.about_home;

import androidx.appcompat.app.AppCompatActivity;
import app.codelabs.forum.R;
import app.codelabs.forum.activities.home.HomeActivity;
import app.codelabs.forum.helpers.ConnectionApi;
import app.codelabs.forum.helpers.Session;
import app.codelabs.forum.models.ResponMyProfile;
import app.codelabs.forum.models.ResponsAbout;
import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

public class AboutHome extends AppCompatActivity {
    private CircleImageView ivLogo;
    private TextView tvBackAbout,tvCompany_name,tvhistory,tvSecretariat;
    private Session session;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_home);

        context = getApplicationContext();
        session = Session.init(context);

        setView();
        setEvent();
        loadData();
    }

    private void loadData() {

        ConnectionApi.apiService(context).getAboutCompany().enqueue(new Callback<ResponsAbout>() {
            @Override
            public void onResponse(Call<ResponsAbout> call, Response<ResponsAbout> response) {
                if (response.isSuccessful() && response.body().getSuccess()){
                        setAbout(response.body().getData());
                }else {
                    Toast.makeText(context, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponsAbout> call, Throwable t) {
                Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setAbout(ResponsAbout.DataEntity data) {
        Picasso.with(context).load(data.getLogo()).fit().centerCrop().into(ivLogo);
        tvCompany_name.setText(data.getCompany_name());
        tvhistory.setText(data.getHistory());
        tvSecretariat.setText(data.getSecretariat());

    }

    private void setEvent() {
        tvBackAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    private void setView() {
        ivLogo = findViewById(R.id.logo);
        tvCompany_name = findViewById(R.id.tvcompany_name);
        tvhistory = findViewById(R.id.tvHistory);
        tvSecretariat = findViewById(R.id.tvSecretariat);
        tvBackAbout = findViewById(R.id.tvBackAbout);
    }
}
