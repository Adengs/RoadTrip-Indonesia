package app.codelabs.roadtrip.activities.home.fragment.community;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.EventBus;

import app.codelabs.roadtrip.activities.custom.ProgressDialogFragment;
import app.codelabs.roadtrip.helpers.ConnectionApi;
import app.codelabs.roadtrip.helpers.Session;
import app.codelabs.roadtrip.models.EventBusClass;
import app.codelabs.roadtrip.models.MemberProfile;
import app.codelabs.roadtrip.R;
import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailMemberActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private static final int REQ_EDIT_PROFILE = 1001;
    private ImageView ivPhoto;
    private TextView tvCountFollowing, tvCountFollower, tvHeaderName, tvCountPost;
    private TextView tvName, tvEmail, tvDob, tvChapter, tvNra, tvNameKta, tvNraKta;
    private LinearLayout viewfollowers,viewFollowing;
    private CircleImageView imageKta;
//    private TabLayout tabLayout;
//    private ViewPager viewPager;
//    private TabLayoutAdapter tabLayoutAdapter;
    private Session session;
    private Context context;
    private MemberProfile.Data dataProfile;
    private  boolean refreshProfile = false;

    private ProgressDialogFragment progressDialogFragment = new ProgressDialogFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_member);

        setView();
        setToolbar();
        context = DetailMemberActivity.this;
        session = Session.init(context);
//        setView(view);
        setEvent();
//        setTabAndPager();

//        if (getIntent().getIntExtra("user_id", -1) != -1) {
//            progressDialogFragment.show(getSupportFragmentManager(), "detail-event");
            setProfileToView();
            getProfile();
//        }
    }

    private void setToolbar() {
        setSupportActionBar(toolbar);
        setTitle("");
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

//    private void setView() {
//        toolbar = findViewById(R.id.toolbar);
//    }

//    private void setTabAndPager() {
//        tabLayoutAdapter = new TabLayoutAdapter(getSupportFragmentManager());
//        tabLayoutAdapter.addFragment(new ProfileUserFragment(), "My Profile");
//        tabLayoutAdapter.addFragment(new BookmarkFragment(), "Saved");
//        viewPager.setAdapter(tabLayoutAdapter);
//        tabLayout.setupWithViewPager(viewPager);
//    }

    private void getProfile() {
        progressDialogFragment.show(getSupportFragmentManager(), "detail-event");
        Intent intent = getIntent();
        Integer id = intent.getIntExtra("user_id", -1);
        ConnectionApi.apiService(context).getDetailMember(id).enqueue(new Callback<MemberProfile>() {
            @Override
            public void onResponse(Call<MemberProfile> call, Response<MemberProfile> response) {
                progressDialogFragment.dismiss();
                if (response.body() != null) {
                    if (response.isSuccessful() && response.body().isSuccess()) {
                        setProfile(response.body().getData());
                    } else {
                        progressDialogFragment.dismiss();
                        Toast.makeText(context, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<MemberProfile> call, Throwable t) {
                progressDialogFragment.dismiss();
                if (t.getMessage() != null) {
                    Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void setProfile(MemberProfile.Data data) {
        dataProfile = data;
        MemberProfile.Data member = session.getMember();
        member.setName(data.getName());
        member.setUsername(data.getUsername());
        member.setNra(data.getNra());
        member.setChapter(data.getChapter());
        member.setCity(data.getCity());
        member.setDateBirth(data.getDateBirth());
        member.setEmail(data.getEmail());
        member.setPhoto(data.getPhoto());
        member.setFollowers(data.getFollowers());
        member.setFollowing(data.getFollowing());

        session.setMember(member);
        tvCountPost.setText("-");
        tvCountFollower.setText(String.valueOf(data.getFollowers()));
        tvCountFollowing.setText(String.valueOf(data.getFollowing()));
        setProfileToView();
        EventBus.getDefault().post(new EventBusClass.Profile());
    }

    private void setProfileToView() {
        MemberProfile.Data member = session.getMember();
        tvHeaderName.setText(member.getName());

        Picasso.with(context).load(member.getPhoto())
                .placeholder(R.drawable.default_photo)
                .fit().centerCrop().into(ivPhoto);

        tvName.setText(member.getName());
        tvEmail.setText(member.getEmail());
        tvDob.setText(member.getDateBirth());
        tvChapter.setText(member.getChapter());
        tvNra.setText(member.getNra());

        tvNameKta.setText(member.getName());
        Log.e("TAG", "setProfileToView: " + member.getNra() );
        tvNraKta.setText(member.getNra());
//        if (member.getNra().isEmpty()){
//            tvNraKta.setText(" xxx xx xxxxx");
//        }else {
//            tvNraKta.setText(member.getNra());
//        }

        Picasso.with(context).load(member.getPhoto())
                .placeholder(R.drawable.default_photo)
                .fit().centerCrop().into(imageKta);
    }


    private void setEvent() {
//        ivSettingApp.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(context, SettingActivity.class);
//                startActivity(intent);
//            }
//        });
//
//        tvEditProfile.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                refreshProfile = true;
//                Intent intent = new Intent(context, EditProfileActivity.class).putExtra("data",new Gson().toJson(dataProfile));
//                /*Intent intent = new Intent(getContext(), EditProfileActivity.class);*/
//                startActivityForResult(intent, REQ_EDIT_PROFILE);
//            }
//        });
        viewfollowers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /**
                 startActivity(new Intent(context, FollowViewActivity.class).putExtra("type",1));
                 */
            }
        });
        viewFollowing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /**
                 startActivity(new Intent(context, FollowViewActivity.class).putExtra("type",2));
                 */
            }
        });
    }

    private void setView() {
        toolbar = findViewById(R.id.toolbar);
//        tvEditProfile = findViewById(R.id.tv_edit_profile);
//        ivSettingApp = findViewById(R.id.iv_setting_app);
        ivPhoto = findViewById(R.id.iv_photo);
        tvHeaderName = findViewById(R.id.tv_header_name);

        tvCountFollower = findViewById(R.id.tv_count_follower);
        tvCountFollowing = findViewById(R.id.tv_count_following);
        tvCountPost = findViewById(R.id.tv_count_post);

//        tabLayout = findViewById(R.id.tab_layout);
//        viewPager = findViewById(R.id.viewpager);
        viewfollowers = findViewById(R.id.view_followers);
        viewFollowing = findViewById(R.id.view_following);

        tvName = findViewById(R.id.tv_name);
        tvEmail = findViewById(R.id.tv_email);
        tvDob = findViewById(R.id.tv_dob);
        tvChapter = findViewById(R.id.tv_chapter);
        tvNra = findViewById(R.id.tv_nra);
        tvNameKta = findViewById(R.id.textView_name);
        tvNraKta = findViewById(R.id.textView_noId);
        imageKta = findViewById(R.id.image_profile);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == REQ_EDIT_PROFILE) {
            setProfileToView();
            getProfile();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (refreshProfile){
            getProfile();
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}