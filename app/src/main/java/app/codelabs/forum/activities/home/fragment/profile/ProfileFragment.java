package app.codelabs.forum.activities.home.fragment.profile;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;
import com.squareup.picasso.Picasso;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import org.greenrobot.eventbus.EventBus;

import app.codelabs.forum.R;
import app.codelabs.forum.activities.home.adapter.TabLayoutAdapter;
import app.codelabs.forum.activities.home.fragment.profile.fragment.BookmarkFragment;
import app.codelabs.forum.activities.home.fragment.profile.fragment.ProfileUserFragment;
import app.codelabs.forum.activities.profile.EditProfileActivity;
import app.codelabs.forum.activities.setting.SettingActivity;
import app.codelabs.forum.helpers.ConnectionApi;
import app.codelabs.forum.helpers.Session;
import app.codelabs.forum.models.EventBusClass;
import app.codelabs.forum.models.ResponseMyProfile;
import app.codelabs.forum.models.ResponseLogin;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.app.Activity.RESULT_OK;

public class ProfileFragment extends Fragment {
    private static final int REQ_EDIT_PROFILE = 1001;
    private ImageView ivSettingApp, ivPhoto;
    private TextView tvEditProfile, tvCountFollowing, tvCountFollower, tvHeaderName, tvCountPost;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private TabLayoutAdapter tabLayoutAdapter;
    private Session session;
    private Context context;

    public ProfileFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        context = getContext();
        session = Session.init(context);
        setView(view);
        setEvent();
        setTabAndPager();
        setProfileToView();
        getProfile();
    }

    private void setTabAndPager() {
        tabLayoutAdapter = new TabLayoutAdapter(getChildFragmentManager());
        tabLayoutAdapter.addFragment(new ProfileUserFragment(), "My Profile");
        tabLayoutAdapter.addFragment(new BookmarkFragment(), "Saved");
        viewPager.setAdapter(tabLayoutAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    private void getProfile() {
        ConnectionApi.apiService(context).getProfile().enqueue(new Callback<ResponseMyProfile>() {
            @Override
            public void onResponse(Call<ResponseMyProfile> call, Response<ResponseMyProfile> response) {
                if (response.body() != null) {
                    if (response.isSuccessful() && response.body().getSuccess()) {
                        setProfile(response.body().getData());
                    } else {
                        Toast.makeText(context, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseMyProfile> call, Throwable t) {
                if (t.getMessage() != null) {
                    Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void setProfile(ResponseMyProfile.DataEntity data) {
        ResponseLogin.Data user = session.getUser();
        user.setName(data.getName());
        user.setUsername(data.getUsername());
        user.setCity(data.getCity());
        user.setDate_birth(data.getDate_birth());
        user.setEmail(data.getEmail());
        user.setPhoto(data.getPhoto());

        session.setUser(user);
        tvCountPost.setText("-");
        tvCountFollower.setText(String.valueOf(data.getFollowers()));
        tvCountFollowing.setText(String.valueOf(data.getFollowing()));
        setProfileToView();
        EventBus.getDefault().post(new EventBusClass.Profile());
    }

    private void setProfileToView() {
        ResponseLogin.Data user = session.getUser();
        tvHeaderName.setText(user.getName());

        Picasso.with(context).load(user.getPhoto())
                .placeholder(R.drawable.default_photo)
                .fit().centerCrop().into(ivPhoto);
    }


    private void setEvent() {
        ivSettingApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), SettingActivity.class);
                startActivity(intent);
            }
        });

        tvEditProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), EditProfileActivity.class);
                startActivityForResult(intent, REQ_EDIT_PROFILE);
            }
        });
    }

    private void setView(View view) {
        tvEditProfile = view.findViewById(R.id.tv_edit_profile);
        ivSettingApp = view.findViewById(R.id.iv_setting_app);
        ivPhoto = view.findViewById(R.id.iv_photo);
        tvHeaderName = view.findViewById(R.id.tv_header_name);

        tvCountFollower = view.findViewById(R.id.tv_count_follower);
        tvCountFollowing = view.findViewById(R.id.tv_count_following);
        tvCountPost = view.findViewById(R.id.tv_count_post);

        tabLayout = view.findViewById(R.id.tab_layout);
        viewPager = view.findViewById(R.id.viewpager);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK && requestCode == REQ_EDIT_PROFILE) {
            setProfileToView();
            getProfile();
        }
    }
}
