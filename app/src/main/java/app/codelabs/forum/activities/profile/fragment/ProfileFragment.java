package app.codelabs.forum.activities.profile.fragment;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import app.codelabs.forum.R;
import app.codelabs.forum.activities.profile.EditProfileActivity;
import app.codelabs.forum.activities.profile.SettingProfile;
import app.codelabs.forum.helpers.ConnectionApi;
import app.codelabs.forum.helpers.Session;
import app.codelabs.forum.models.ResponMyProfile;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment {
    private ImageView imgSettingPro , fotoProfile;
    private TextView txtEditProfile , following ,follower,nameataspro ,namepro ,emailpro,datepro,citypro ,postpro;
    private Session session;
    private String token;
    private String apptoken;
    private Context context;

    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        context = getContext();
        session = Session.init(context);
        apptoken = session.getAppToken();
        token = session.getToken();
        setView(view);
        setEvent();
        loadData();
    }

    private void loadData() {

        ConnectionApi.apiService().myprofile(token,apptoken).enqueue(new Callback<ResponMyProfile>() {
            @Override
            public void onResponse(Call<ResponMyProfile> call, Response<ResponMyProfile> response) {
                if (response.isSuccessful() && response.body().getSuccess()){
                  setProfile(response.body().getData());

                }else{
                    Toast.makeText(context,response.body().getMessage(),Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<ResponMyProfile> call, Throwable t) {
                Toast.makeText(context,t.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setProfile(ResponMyProfile.DataEntity data) {
        nameataspro.setText(data.getName());
        namepro.setText(data.getName());
        emailpro.setText(data.getEmail());
        Picasso.with(context).load(data.getPhoto());
        citypro.setText(data.getCity());
        datepro.setText(data.getDate_birth());
        follower.setText(String.valueOf(data.getFollowers()));
        following.setText(String.valueOf(data.getFollowing()));
    }


    private void setEvent() {
        imgSettingPro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), SettingProfile.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });

        txtEditProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), EditProfileActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });
    }

    private void setView(View view) {
        txtEditProfile = view.findViewById(R.id.txtEditProfile);
        imgSettingPro = view.findViewById(R.id.imgSetting);
        fotoProfile = view.findViewById(R.id.foto_profile);
        nameataspro = view.findViewById(R.id.nameatas_profile);
        namepro = view.findViewById(R.id.nameprofile);
        emailpro = view.findViewById(R.id.emailprofile);
        datepro = view.findViewById(R.id.dateprofile);
        citypro = view.findViewById(R.id.cityprofile);
        follower = view.findViewById(R.id.follower_profile);
        following = view.findViewById(R.id.following_profile);
        postpro = view.findViewById(R.id.post_profile);
    }

}
