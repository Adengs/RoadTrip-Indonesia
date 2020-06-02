package app.codelabs.forum.activities.shop;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import app.codelabs.forum.R;
import app.codelabs.forum.activities.shop.adapter.AdapterTabLayoutShop;
import app.codelabs.forum.activities.shop.fragment.ListShopFragment;
import app.codelabs.forum.helpers.ActivityUtils;
import app.codelabs.forum.helpers.ConnectionApi;
import app.codelabs.forum.helpers.StringUtil;
import app.codelabs.forum.helpers.Validator;
import app.codelabs.forum.models.EventBusClass;
import app.codelabs.forum.models.ResponseShopCategory;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductActivity extends AppCompatActivity {
    private Context context;
    private EditText etSearch;
    private ImageView ivBack;
    private ProgressBar progressBar;
    private TabLayout tabLayout;
    private ViewPager viewPagerProducts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        context = getApplicationContext();

        setView();
        setEvent();
        getCategories();
    }

    private void getCategories() {
        ConnectionApi.apiService(context).getShopCategories().enqueue(new Callback<ResponseShopCategory>() {
            @Override
            public void onResponse(Call<ResponseShopCategory> call, Response<ResponseShopCategory> response) {
                if (response.body() != null) {
                    if (response.isSuccessful() && response.body().getSuccess()) {
                        setTabLayout(response.body().getData());
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseShopCategory> call, Throwable t) {
                if (t.getMessage() != null) {
                    Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void setTabLayout(List<ResponseShopCategory.DataEntity> data) {
        tabLayout.setVisibility(View.VISIBLE);
        viewPagerProducts.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.GONE);

        AdapterTabLayoutShop adapterTabLayoutShop = new AdapterTabLayoutShop(getSupportFragmentManager());

        for (ResponseShopCategory.DataEntity item : data) {
            adapterTabLayoutShop.addFragment(
                    new ListShopFragment()
                            .setTypeAndReferenceId(ListShopFragment.CATEGORIES,
                                    item.getId()), StringUtil.toCapitalize(item.getCategory()));
        }
        viewPagerProducts.setAdapter(adapterTabLayoutShop);
        tabLayout.setupWithViewPager(viewPagerProducts);


    }

    private void setView() {
        tabLayout = findViewById(R.id.tab_layout);
        viewPagerProducts = findViewById(R.id.viewpager);
        ivBack = findViewById(R.id.iv_back);
        etSearch = findViewById(R.id.et_search);
        progressBar = findViewById(R.id.progressbar);

    }

    private void setEvent() {
        setEventSearch();
        setEventBtnBack();
    }

    private void setEventSearch() {
        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                EventBus.getDefault().post(new EventBusClass.SearchShop(s.toString()));
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        etSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH || actionId == EditorInfo.IME_ACTION_DONE) {
                    search();
                    return true;
                }

                return false;
            }
        });
        etSearch.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                view.performClick();
                final int DRAWABLE_LEFT = 0;
                final int DRAWABLE_TOP = 1;
                final int DRAWABLE_RIGHT = 2;
                final int DRAWABLE_BOTTOM = 3;

                if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                    if (motionEvent.getRawX() >= (etSearch.getRight() - etSearch.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                        // your action here
                        search();
                        return true;
                    }
                }
                return false;
            }
        });
    }

    private void search() {
        if (Validator.isFieldEmpty(etSearch)) {
            etSearch.clearFocus();
        }
        EventBus.getDefault().post(new EventBusClass.SearchShop(etSearch.getText().toString()));
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
