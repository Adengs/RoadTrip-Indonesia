package app.codelabs.roadtrip.activities.explore.maps;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import app.codelabs.roadtrip.R;
import app.codelabs.roadtrip.activities.explore.list.AdapterListLocation;
import app.codelabs.roadtrip.activities.explore.list.ListSearchActivity;
import app.codelabs.roadtrip.helpers.ActivityUtils;
import app.codelabs.roadtrip.helpers.ConnectionApi;
import app.codelabs.roadtrip.helpers.Validator;
import app.codelabs.roadtrip.models.ResponseListLocation;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListMapsSearchActivity extends AppCompatActivity {
    private Context context;
    private EditText etSearch;
    private ImageView ivBack;
    private RecyclerView recyclerView;
    private AdapterMapsListLocation adapter;
    private String search = "";
    private ProgressBar progressBar;
    public final static int CATEGORIES = 0;
//    int id = 1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_list);
        context = getApplicationContext();

        getData();
        setView();
        setEvent();
        setRecyclerView();
    }

    private void getData() {
//        progressBar.setVisibility(View.VISIBLE);
        int id = getIntent().getIntExtra("id-maps", 0);
        ConnectionApi.apiService(context).getListByCategories(id, search).enqueue(new Callback<ResponseListLocation>() {
            @Override
            public void onResponse(Call<ResponseListLocation> call, Response<ResponseListLocation> response) {
                if (response.body() != null) {
                    if (response.isSuccessful() & response.code() == 200) {
                        setListLocation(response.body().getData());
                        progressBar.setVisibility(View.GONE);
//                        adapter.notifyDataSetChanged();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseListLocation> call, Throwable t) {
                if (t.getMessage() != null) {
                    Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void setListLocation(List<ResponseListLocation.DataEntity> data) {
        adapter.setItems(data);
    }

    private void setRecyclerView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(adapter);
    }

    private void setView() {
        etSearch = findViewById(R.id.et_search);
        ivBack = findViewById(R.id.iv_back);
        progressBar = (ProgressBar) findViewById(R.id.progressbar);

        recyclerView = findViewById(R.id.recyclerview_list);
        adapter = new AdapterMapsListLocation(context);
    }

    private void setEvent() {
        setEventSearch();
        setEventBtnBack();
    }

    //    @SuppressLint("ClickableViewAccessibility")
    private void setEventSearch() {
        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                search = s.toString();
                progressBar.setVisibility(View.VISIBLE);
                getData();
//                EventBus.getDefault().post(new EventBusClass.SearchShop(s.toString()));
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        etSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    ActivityUtils.hideKeyboard(ListMapsSearchActivity.this, etSearch);
                    etSearch.clearFocus();
//                    search();
                    return true;
                }

                return false;
            }
        });
//        etSearch.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View view, MotionEvent motionEvent) {
//                view.performClick();
//                final int DRAWABLE_LEFT = 0;
//                final int DRAWABLE_TOP = 1;
//                final int DRAWABLE_RIGHT = 2;
//                final int DRAWABLE_BOTTOM = 3;
//
//                if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
//                    if (motionEvent.getRawX() >= (etSearch.getRight() - etSearch.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
//                        // your action here
//                        search();
//                        return true;
//                    }
//                }
//                return false;
//            }
//        });
    }

    private void search() {
        if (Validator.isFieldEmpty(etSearch)) {
            etSearch.clearFocus();
        }
//        EventBus.getDefault().post(new EventBusClass.SearchShop(etSearch.getText().toString()));
//        search = etSearch.getText().toString();
//        getData();
        ActivityUtils.hideKeyboard(this, etSearch);

    }

    private void setEventBtnBack() {
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }
}
