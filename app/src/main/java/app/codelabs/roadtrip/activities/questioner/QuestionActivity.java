package app.codelabs.roadtrip.activities.questioner;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import app.codelabs.roadtrip.models.ResponseAnswerQuestioner;
import app.codelabs.roadtrip.models.ResponseDefault;
import app.codelabs.roadtrip.models.ResponseQuestion;
import app.codelabs.roadtrip.R;
import app.codelabs.roadtrip.activities.custom.ProgressDialogFragment;
import app.codelabs.roadtrip.activities.questioner.adapter.QuestionAnswerAdapter;
import app.codelabs.roadtrip.activities.questioner.bottom_sheet.BottomSheetQuestioner;
import app.codelabs.roadtrip.helpers.ConnectionApi;
import app.codelabs.roadtrip.helpers.Session;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class QuestionActivity extends AppCompatActivity {
    private Context context;
    private int voteId;
    private int indexQuestion = -1;
    private boolean isSubmit = false;
    private QuestionAnswerAdapter adapter = new QuestionAnswerAdapter();
    private List<ResponseQuestion.Question> data = new ArrayList<>();
    private HashMap<String, Integer> answer = new HashMap<>();
    private ProgressDialogFragment progressDialogFragment = new ProgressDialogFragment();
    private ResponseAnswerQuestioner answerResponse;

    private Button btnAction;
    private TextView tvQuestionNumber, tvTotalQuestion, tvTitle;
    private RecyclerView recyclerView;
    private FrameLayout container;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        context = getApplicationContext();

        getPreviousData();
        setView();
        setEvent();
        setRecyclerview();
        getQuestions();
    }

    private void setRecyclerview() {
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(adapter);
    }

    private void getQuestions() {
        ConnectionApi.apiService(context).getQuestions(voteId).enqueue(new Callback<ResponseQuestion>() {
            @Override
            public void onResponse(Call<ResponseQuestion> call, Response<ResponseQuestion> response) {
                progressBar.setVisibility(View.GONE);
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        if (response.body().getSuccess()) {
                            container.setVisibility(View.VISIBLE);
                            setData(response.body().getData());
                        } else {
                            Toast.makeText(context, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        ResponseDefault responseDefault = new Gson().fromJson(response.errorBody().charStream(), ResponseDefault.class);
                        Toast.makeText(context, responseDefault.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseQuestion> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setData(List<ResponseQuestion.Question> data) {
        this.data = data;
        answer.put("member_id", Session.init(context).getUser().getId());
        answer.put("vote_id", voteId);
        setNextQuestion();
    }

    private void setNextQuestion() {
        indexQuestion += 1;
        tvQuestionNumber.setText(new StringBuilder().append("Question ").append(indexQuestion + 1).toString());
        tvTotalQuestion.setText("/" + data.size());
        tvTitle.setText(data.get(indexQuestion).getQuestion());
        adapter.setItems(data.get(indexQuestion).getQuestion_choice(), indexQuestion);
        adapter.setCallback(new QuestionAnswerAdapter.OnAnswerSelected() {
            @Override
            public void onSelected(ResponseQuestion.QuestionAnswerEntity item, int questionIndex) {
                answer.put("question_id[" + questionIndex + "]", data.get(indexQuestion).getQuestion_id());
                answer.put("choice_id[" + questionIndex + "]", item.getChoice_id());
            }
        });
    }

    private void getPreviousData() {
        if(getIntent() != null && getIntent().getIntExtra("vote_id", -1) != -1) {
            voteId = getIntent().getIntExtra("vote_id", -1);
        }
//        voteId = 1;
    }

    private void setEvent() {
        setEventBtnAction();
    }

    private void setEventBtnAction() {
        btnAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((indexQuestion + 1) * 2) != answer.size() - 2) {
                    Toast.makeText(context, "Pilih jawaban terlebih dahulu", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (indexQuestion == data.size() - 1) {
                    submit();
                } else {
                    setNextQuestion();
                }
            }
        });
    }

    private void submit() {
        progressDialogFragment.show(getSupportFragmentManager(), "submit");
        ConnectionApi.apiService(context).answerQuestioner(answer).enqueue(new Callback<ResponseAnswerQuestioner>() {

            @Override
            public void onResponse(Call<ResponseAnswerQuestioner> call, Response<ResponseAnswerQuestioner> response) {
                progressDialogFragment.dismiss();
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        if (response.body().getSuccess()) {
                            isSubmit = true;
                            answerResponse = response.body();
                            BottomSheetQuestioner bottomSheetQuestioner = new BottomSheetQuestioner();
                            bottomSheetQuestioner.setData(answerResponse)
                                    .show(getSupportFragmentManager(), "notification");

                        } else {
                            Toast.makeText(context, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        ResponseDefault responseDefault = new Gson().fromJson(response.errorBody().charStream(), ResponseDefault.class);
                        Toast.makeText(context, responseDefault.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseAnswerQuestioner> call, Throwable t) {
                progressDialogFragment.dismiss();
                Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setView() {
        btnAction = findViewById(R.id.btn_action);
        tvQuestionNumber = findViewById(R.id.tv_question_number);
        tvTotalQuestion = findViewById(R.id.tv_total_question);
        tvTitle = findViewById(R.id.tv_title);
        recyclerView = findViewById(R.id.recyclerview);
        progressBar = findViewById(R.id.progress);
        container = findViewById(R.id.container);
    }

    @Override
    public void onBackPressed() {
        if (isSubmit) {
            Intent intent = new Intent(context, QuestionResultActivity.class);
            intent.putExtra("data", answerResponse.toJson());
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        } else {
            super.onBackPressed();
        }
    }
}
