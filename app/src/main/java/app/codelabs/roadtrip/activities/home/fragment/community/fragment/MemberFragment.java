package app.codelabs.roadtrip.activities.home.fragment.community.fragment;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.HashMap;
import java.util.Map;

import app.codelabs.roadtrip.R;
import app.codelabs.roadtrip.activities.home.adapter.MemberAdapter;
import app.codelabs.roadtrip.helpers.ConnectionApi;
import app.codelabs.roadtrip.models.ResponseFollow;
import app.codelabs.roadtrip.models.ResponseListMemberCompany;
import app.codelabs.roadtrip.models.ResponseUnFollow;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MemberFragment extends Fragment {

    private RecyclerView recyclerView;
    private MemberAdapter adapter;
    private Context context;
    private EditText etSearchMember;
    private ProgressBar progressBar;
    private boolean isLoading;

    private String search = "";

    public MemberFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_member, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        context = getContext();

        setView(view);
        setEvent();
        setRecycleView();
        setLoading(true, true);
        loadData();

    }

    private void setLoading(boolean isLoading, boolean isRefresh) {
        this.isLoading = isLoading;
        if (isLoading == true && isRefresh == false) {
            progressBar.setVisibility(View.VISIBLE);
        } else {
            progressBar.setVisibility(View.GONE);
        }

    }


    private void loadData() {
        ConnectionApi.apiService(context).listMember(search).enqueue(new Callback<ResponseListMemberCompany>() {

            @Override
            public void onResponse(Call<ResponseListMemberCompany> call, Response<ResponseListMemberCompany> response) {
                if (response.body() != null) {
                    if (response.isSuccessful() && response.body().getSuccess()) {
                        adapter.setItems(response.body().getData());
                    }
                    else {
                        Toast.makeText(context, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseListMemberCompany> call, Throwable t) {
                if (t.getMessage() != null) {
                    Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void setEvent() {
        etSearchMember.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
//                search = s.toString();
                search = etSearchMember.getText().toString().trim();
                setLoading(true, true);
                loadData();
                }
            }
        });

        etSearchMember.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH){
//                    etSearch.hideKeyboard();
                    hideKeyboard();
                    etSearchMember.clearFocus();
                    return true;
                }
                return false;
            }
        });

//        etSearchMember.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//                search = s.toString();
//                setLoading(true, true);
//                loadData();
//
//            }
//        });
        adapter.setListener(new MemberAdapter.OnItemSelected() {
            @Override
            public void onFollow(final ResponseListMemberCompany.Data data) {
                if (data.getIs_following() == false) {
                    follow(data);
                } else {
                    unFollow(data);
                }
            }
        });
    }

    private void unFollow(ResponseListMemberCompany.Data data) {
        setLoading(true, false);
        Map<String, String> dataUnFollow = new HashMap<>();
        dataUnFollow.put("followed_id", String.valueOf(data.getId()));
        ConnectionApi.apiService(context).unFollow(dataUnFollow).enqueue(new Callback<ResponseUnFollow>() {
            @Override
            public void onResponse(Call<ResponseUnFollow> call, Response<ResponseUnFollow> response) {
                setLoading(false, false);
                if (response.body() != null) {
                    if (response.isSuccessful() && response.body().getSuccess()){
                        Toast.makeText(getContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        loadData();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseUnFollow> call, Throwable t) {
                if (t.getMessage() != null) {
                    Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void follow(ResponseListMemberCompany.Data data) {
        setLoading(true, false);
        Map<String, String> dataFollow = new HashMap<>();
        dataFollow.put("followed_id", String.valueOf(data.getId()));
        ConnectionApi.apiService(context).follow(dataFollow).enqueue(new Callback<ResponseFollow>() {
            @Override
            public void onResponse(Call<ResponseFollow> call, Response<ResponseFollow> response) {
                setLoading(false, false);
                if (response.body() != null) {
                    if (response.isSuccessful() && response.body().getSuccess()) {
                        Toast.makeText(getContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        loadData();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseFollow> call, Throwable t) {
                if (t.getMessage() != null) {
                    Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    private void setRecycleView() {
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(adapter);
    }

    private void setView(View view) {
        adapter = new MemberAdapter();
        recyclerView = view.findViewById(R.id.rv_member);
        etSearchMember = view.findViewById(R.id.et_search_member);
        progressBar = view.findViewById(R.id.progress);
    }

    public void hideKeyboard() {
        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Activity.INPUT_METHOD_SERVICE);
        if (imm != null) {
            imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);
        }
    }
}
