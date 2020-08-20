package app.codelabs.forum.activities.vote;

import android.content.Context;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;

import app.codelabs.forum.R;
import app.codelabs.forum.activities.custom.ProgressDialogFragment;
import app.codelabs.forum.activities.vote.adapter.AdapterVote;
import app.codelabs.forum.activities.vote.bottom_sheet.BottomSheetVote;
import app.codelabs.forum.helpers.ConnectionApi;
import app.codelabs.forum.helpers.DateTimeHelper;
import app.codelabs.forum.models.ResponseDefault;
import app.codelabs.forum.models.ResponseVote;
import app.codelabs.forum.models.ResponseVoting;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class VoteActivity extends AppCompatActivity {
    private TextView tvTitle, tvTimeLeft, tvNoData;
    private Button btnVoting;
    private Context context;
    private RecyclerView recyclerView;
    private AdapterVote adapter;
    private Toolbar toolbar;
    private ProgressBar progressBar;
    private LinearLayout container;
    private ResponseVote.DataEntity data;
    private ProgressDialogFragment progressDialogFragment = new ProgressDialogFragment();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vote);

        context = getApplicationContext();

        setView();
        setEvent();
        setToolbar();
        setRecycleView();
        getVoteData();
    }

    private void getVoteData() {
        progressBar.setVisibility(View.VISIBLE);
        container.setVisibility(View.INVISIBLE);
        ConnectionApi.apiService(context).getVote().enqueue(new Callback<ResponseVote>() {
            @Override
            public void onResponse(Call<ResponseVote> call, Response<ResponseVote> response) {
                progressBar.setVisibility(View.GONE);
                if (response.body() != null) {
                    if (response.isSuccessful()) {
                        setData(response.body().getData());
                        container.setVisibility(View.VISIBLE);
                    }
                } else {
                    ResponseDefault responseDefault = new Gson().fromJson(response.errorBody().charStream(), ResponseDefault.class);
                    if (!responseDefault.getSuccess() && response.code() == 404) {
                        tvNoData.setVisibility(View.VISIBLE);
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseVote> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                container.setVisibility(View.VISIBLE);
                if (t.getMessage() != null) {
                    Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void setData(ResponseVote.DataEntity data) {
        this.data = data;
        tvTitle.setText(data.getTitle());
        tvTimeLeft.setText(DateTimeHelper.instance(data.getEnd_vote()).getTimeLeft());
        adapter.setItems(data.getCandidate(), data.getIsAlreadyVote());
        if (data.getIsAlreadyVote()) {
            btnVoting.setText("You Have Participated");
            btnVoting.setBackgroundResource(R.drawable.shape_button_disable);
            btnVoting.setTextColor(ContextCompat.getColor(context, R.color.colorBlack));
        } else {
            btnVoting.setText("Voting");
            btnVoting.setBackgroundResource(R.drawable.shape_button_login);
            btnVoting.setTextColor(ContextCompat.getColor(context, R.color.colorWhite));

        }
    }

    private void setRecycleView() {
        adapter = new AdapterVote();
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(adapter);
    }

    private void setEvent() {
        btnVoting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (data.getIsAlreadyVote()) {
                    Toast.makeText(context, "You Have Participated.", Toast.LENGTH_SHORT).show();
                    return;
                }
                votingCandidate();
            }
        });

    }

    private void votingCandidate() {
        if (adapter.getSelectedCandidate() == null) {
            Toast.makeText(context, "Harus memilih salah satu kandidat.", Toast.LENGTH_SHORT).show();
            return;
        }
        progressDialogFragment.show(getSupportFragmentManager(), "do-voting");
        ConnectionApi.apiService(context).doVote(data.getId(), adapter.getSelectedCandidate().getUser_id()).enqueue(new Callback<ResponseVoting>() {
            @Override
            public void onResponse(Call<ResponseVoting> call, Response<ResponseVoting> response) {
                progressDialogFragment.dismiss();
                if (response.body() != null) {
                    if (response.isSuccessful() && response.body().getSuccess()) {
                        BottomSheetVote bottomSheetVote = new BottomSheetVote();
                        bottomSheetVote.setVote(data.getId());
                        bottomSheetVote.setHasQuestion(response.body().getData().isQuestion());
                        bottomSheetVote.show(getSupportFragmentManager(), "bottom_vote");
                        getVoteData();
                    } else {
                        Toast.makeText(context, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseVoting> call, Throwable t) {
                progressDialogFragment.dismiss();
                if (t.getMessage() != null) {
                    Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

    private void setToolbar() {
        setSupportActionBar(toolbar);
        setTitle("Vote");
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    private void setView() {
        toolbar = findViewById(R.id.toolbar);
        btnVoting = findViewById(R.id.btn_vote);
        recyclerView = findViewById(R.id.recyclerview);
        container = findViewById(R.id.container);
        progressBar = findViewById(R.id.progressbar);
        tvTimeLeft = findViewById(R.id.tv_time_left);
        tvNoData = findViewById(R.id.tv_no_data);
        tvTitle = findViewById(R.id.tv_title);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }
}
