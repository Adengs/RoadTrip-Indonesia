package app.codelabs.forum.activities.vote;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import app.codelabs.forum.R;
import app.codelabs.forum.activities.home.HomeActivity;
import app.codelabs.forum.helpers.ConnectionApi;
import app.codelabs.forum.models.ResponseVote;
import app.codelabs.forum.models.Vote;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class VoteActivity extends AppCompatActivity {
    private Button btnVoting;
    private Context context;
    private RecyclerView recyclerView;
    private AdapterVote adapter;
    private Toolbar toolbar;
    private ProgressBar progressBar;
    private LinearLayout container;


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
                container.setVisibility(View.VISIBLE);
                if(response.body() != null){
                    if(response.isSuccessful()){
                        
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseVote> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                container.setVisibility(View.VISIBLE);
                if (t.getMessage() != null) {
                    Toast.makeText(context,t.getMessage(),Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void setRecycleView() {
        adapter = new AdapterVote();
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(adapter);
        adapter.setItems(GenerateDataDumi());
    }

    private void setEvent() {
        btnVoting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BottomSheetVote bottomSheetVote = new BottomSheetVote();
                bottomSheetVote.show(getSupportFragmentManager(), "bottomvote");
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
    }

    public List<Vote> GenerateDataDumi() {
        List<Vote> items = new ArrayList<>();

        items.add(new Vote("Vanessa", false));
        items.add(new Vote("Nurul", false));
        items.add(new Vote("Adella", false));

        return items;
    }


}
