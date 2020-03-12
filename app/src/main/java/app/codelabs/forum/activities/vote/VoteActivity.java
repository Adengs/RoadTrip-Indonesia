package app.codelabs.forum.activities.vote;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import app.codelabs.forum.R;
import app.codelabs.forum.activities.home.HomeActivity;


public class VoteActivity extends AppCompatActivity {
    TextView backVote;
    Button btnVoting;
    Context context;
    RecyclerView recyclerView;
    AdapterVote adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vote);

        context = getApplicationContext();

        setView();
        setEvent();
        setRecycleView();
    }

    private void setRecycleView() {
        adapter= new AdapterVote();
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(adapter);
    }

    private void setEvent() {
        backVote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(VoteActivity.this , HomeActivity.class));
            }
        });

        btnVoting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BottomSheetVote bottomSheetVote = new BottomSheetVote();
                bottomSheetVote.show(getSupportFragmentManager(),"bottomvote");
            }
        });

    }

    private void setView() {
        backVote=findViewById(R.id.back_vote);
        btnVoting = findViewById(R.id.btn_Voting_Vote);
        recyclerView = findViewById(R.id.recyclerview_vote);
    }


}
