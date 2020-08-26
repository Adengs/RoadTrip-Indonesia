package app.codelabs.forum.activities.questioner;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;

import java.util.List;

import app.codelabs.forum.R;
import app.codelabs.forum.activities.home.HomeActivity;
import app.codelabs.forum.activities.questioner.adapter.QuestionResultAdapter;
import app.codelabs.forum.models.ResponseAnswerQuestioner;

public class QuestionResultActivity extends AppCompatActivity {
    private QuestionResultAdapter adapter = new QuestionResultAdapter();
    private ResponseAnswerQuestioner data;
    private Context context;

    private ImageView ivClose;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_result);
        context = getApplicationContext();

        getPreviousData();
        setView();
        setEvent();
        setRecyclerview();
    }

    private void getPreviousData() {
        if (getIntent() != null && getIntent().getStringExtra("data") != null) {
            data = new Gson().fromJson(getIntent().getStringExtra("data"), ResponseAnswerQuestioner.class);
            List<ResponseAnswerQuestioner.DetailsEntity> items = data.getData().getDetails();
            int tmp = 0;
            int higherIndex = -1;
            int index = 0;
            for (ResponseAnswerQuestioner.DetailsEntity item : items) {
                if (tmp < item.getPercentageChoice()) {
                    tmp = item.getPercentageChoice();
                    higherIndex = index;
                }
                index++;
            }
            if(higherIndex != -1) {
                items.get(higherIndex).setHigher(true);
            }
            adapter.setItems(items);
        }
    }

    private void setView() {
        ivClose = findViewById(R.id.iv_close);
        recyclerView = findViewById(R.id.recyclerview);
    }

    private void setEvent() {
        setEventBtnClose();
    }

    private void setEventBtnClose() {
        ivClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, HomeActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(context, HomeActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    private void setRecyclerview() {
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(adapter);
    }
}