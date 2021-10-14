package app.codelabs.forum.activities.home.fragment.profile.activity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.HashMap;
import java.util.Map;

import app.codelabs.forum.activities.home.fragment.profile.adapter.MemberFollowItemAdapter;
import app.codelabs.forum.databinding.ActivityFollowViewBinding;
import app.codelabs.forum.helpers.ConnectionApi;
import app.codelabs.forum.models.ResponseFollow;
import app.codelabs.forum.models.ResponseFollowsModel;
import app.codelabs.forum.models.ResponseUnFollow;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FollowViewActivity extends AppCompatActivity {
    private ActivityFollowViewBinding binding;
    private MemberFollowItemAdapter memberFollowItemAdapter;
    private String searchValue = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFollowViewBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setToolbarAndView();
        getData();
        setEvent();
    }

    private void setEvent() {
        memberFollowItemAdapter.setListener(new MemberFollowItemAdapter.OnItemSelected() {
            @Override
            public void onFollow(ResponseFollowsModel.Data data) {
                if (data.isIsFollowing()) {
                    unfollow(data);
                } else {
                    follow(data);
                }
            }
        });
        binding.etSearchMember.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
               searchValue = editable.toString();
               getData();
            }
        });
    }

    private void follow(ResponseFollowsModel.Data data) {
        Map<String, String> dataFollow = new HashMap<>();
        dataFollow.put("followed_id", String.valueOf(data.getFollowerId()));
        ConnectionApi.apiService(this).follow(dataFollow).enqueue(new Callback<ResponseFollow>() {
            @Override
            public void onResponse(Call<ResponseFollow> call, Response<ResponseFollow> response) {
                if (response.body() != null) {
                    if (response.isSuccessful() && response.body().getSuccess()) {
                        Toast.makeText(FollowViewActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        getData();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseFollow> call, Throwable t) {
                if (t.getMessage() != null) {
                    Toast.makeText(FollowViewActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

    private void unfollow(ResponseFollowsModel.Data data) {
        Map<String, String> dataUnFollow = new HashMap<>();
        int type = getIntent().getIntExtra("type", -1);
        if (type == 1) {
            dataUnFollow.put("followed_id", String.valueOf(data.getFollowerId()));
        }else {
            dataUnFollow.put("followed_id", String.valueOf(data.getFollowedId()));
        }

        ConnectionApi.apiService(FollowViewActivity.this).unFollow(dataUnFollow).enqueue(new Callback<ResponseUnFollow>() {
            @Override
            public void onResponse(Call<ResponseUnFollow> call, Response<ResponseUnFollow> response) {
                if (response.body() != null) {
                    if (response.isSuccessful() && response.body().getSuccess()){
                        Toast.makeText(FollowViewActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        getData();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseUnFollow> call, Throwable t) {
                if (t.getMessage() != null) {
                    Toast.makeText(FollowViewActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    private void setToolbarAndView() {
        binding.toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            }
        });
        setSupportActionBar(binding.toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        memberFollowItemAdapter = new MemberFollowItemAdapter();
        binding.rvMember.setHasFixedSize(true);
        binding.rvMember.setAdapter(memberFollowItemAdapter);
    }

    private void getData() {
        int type = getIntent().getIntExtra("type", -1);
        if (type == 1) {
            setTitle("Followers");
            loadFollowersData();
        } else {
            setTitle("Following");
            loadFollowingData();
        }
    }

    private void loadFollowersData() {
        ConnectionApi.apiService(this).getFollower(searchValue).enqueue(new Callback<ResponseFollowsModel>() {
            @Override
            public void onResponse(Call<ResponseFollowsModel> call, Response<ResponseFollowsModel> response) {
                memberFollowItemAdapter.setItems(response.body().getData());
            }

            @Override
            public void onFailure(Call<ResponseFollowsModel> call, Throwable t) {

            }
        });

    }

    private void loadFollowingData() {
        ConnectionApi.apiService(this).getFollowed(searchValue).enqueue(new Callback<ResponseFollowsModel>() {
            @Override
            public void onResponse(Call<ResponseFollowsModel> call, Response<ResponseFollowsModel> response) {
                memberFollowItemAdapter.setItems(response.body().getData());
            }

            @Override
            public void onFailure(Call<ResponseFollowsModel> call, Throwable t) {

            }
        });

    }
}