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

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import app.codelabs.forum.R;
import app.codelabs.forum.activities.profile.EditProfileActivity;
import app.codelabs.forum.activities.profile.SettingProfile;
import app.codelabs.forum.helpers.ConnectionApi;
import app.codelabs.forum.helpers.Session;
import app.codelabs.forum.models.ResponMyProfile;
import app.codelabs.forum.models.ResponsLogin;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment {
    private ImageView ivSettingApp, ivPhoto;
    private TextView tvEditProfile, tvCountFollowing, tvCountFollower, tvHeaderName, tvName, tvEmail, tvDob, tvCity, tvCountPost;
    private Session session;
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
        setView(view);
        setEvent();
        loadData();
    }

    private void loadData() {

        ConnectionApi.apiService(context).getProfile().enqueue(new Callback<ResponMyProfile>() {
            @Override
            public void onResponse(Call<ResponMyProfile> call, Response<ResponMyProfile> response) {
                if (response.isSuccessful() && response.body().getSuccess()) {
                    setProfile(response.body().getData());
                } else {
                    Toast.makeText(context, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<ResponMyProfile> call, Throwable t) {
                Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setProfile(ResponMyProfile.DataEntity data) {
        ResponsLogin.Data user = session.getUser();
        user.setName(data.getName());
        user.setUsername(data.getUsername());
        user.setCity(data.getCity());
        user.setDate_birth(data.getDate_birth());
        user.setEmail(data.getEmail());
        user.setPhoto(data.getPhoto());

        session.setUser(user);
        tvHeaderName.setText(data.getName());
        tvName.setText(data.getName());
        tvEmail.setText(data.getEmail());
        Picasso.with(context).load(data.getPhoto()).fit().centerCrop().into(ivPhoto);
        tvCity.setText(data.getCity());
        tvDob.setText(data.getDate_birth());
        tvCountFollower.setText(String.valueOf(data.getFollowers()));
        tvCountFollowing.setText(String.valueOf(data.getFollowing()));
    }


    private void setEvent() {
        ivSettingApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), SettingProfile.class);
                startActivity(intent);
            }
        });

        tvEditProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), EditProfileActivity.class);
                startActivity(intent);
            }
        });
    }

    private void setView(View view) {
        tvEditProfile = view.findViewById(R.id.tv_edit_profile);
        ivSettingApp = view.findViewById(R.id.iv_setting_app);
        ivPhoto = view.findViewById(R.id.iv_photo);
        tvHeaderName = view.findViewById(R.id.tv_header_name);
        tvName = view.findViewById(R.id.tv_name);
        tvEmail = view.findViewById(R.id.tv_email);
        tvDob = view.findViewById(R.id.tv_dob);
        tvCity = view.findViewById(R.id.tv_city);
        tvCountFollower = view.findViewById(R.id.tv_count_follower);
        tvCountFollowing = view.findViewById(R.id.tv_count_following);
        tvCountPost = view.findViewById(R.id.tv_count_post);
    }

}
