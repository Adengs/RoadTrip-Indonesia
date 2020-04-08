package app.codelabs.forum.activities.club.member;


import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import app.codelabs.forum.R;
import app.codelabs.forum.helpers.ConnectionApi;
import app.codelabs.forum.helpers.Session;
import app.codelabs.forum.models.ResponsFollow;
import app.codelabs.forum.models.ResponsListMemberCompany;
import app.codelabs.forum.models.ResponsUnFollow;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class MemberFragment extends Fragment {

    private RecyclerView recyclerView;
    private MemberAdapter adapter;
    private Context context;
    private Session session;
    private TextView tvfollow;
    private EditText et_sreach_member;
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
        session = Session.init(context);

        setView(view);
        setEvent();
        setRecycleView();
        setLoading(true,true);
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
        ConnectionApi.apiService(context).listMember(search).enqueue(new Callback<ResponsListMemberCompany>(){

            @Override
            public void onResponse(Call<ResponsListMemberCompany> call, Response<ResponsListMemberCompany> response) {
                if (response.isSuccessful() && response.body().getSuccess()){
                    adapter.setItems(response.body().getData());
                }
                else {

                    Toast.makeText(context, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponsListMemberCompany> call, Throwable t) {
                Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });

    }
    private void setEvent() {
        et_sreach_member.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                search = s.toString();
                setLoading(true,true);
                loadData();

            }
        });
        adapter.setListener(new MemberAdapter.OnItemSelected() {
            @Override
            public void onFollow(final ResponsListMemberCompany.Data data) {
                if(data.getIs_following()== false){
                    setLoading(true, false);
                    Map<String , String > dataFollow = new HashMap<>();
                    dataFollow.put("followed_id", String.valueOf(data.getId()));
                    ConnectionApi.apiService(context).follow(dataFollow).enqueue(new Callback<ResponsFollow>() {
                        @Override
                        public void onResponse(Call<ResponsFollow> call, Response<ResponsFollow> response) {
                            setLoading(false, false);
                            if (response.isSuccessful() && response.body().getSuccess()){
                                Toast.makeText(getContext(),response.body().getMessage(), Toast.LENGTH_SHORT).show();
                               // txtfollow.setText("Following");
                                //txtfollow.setTextColor(Color.parseColor("#FFFFFF"));
                               // txtfollow.setBackgroundResource(R.drawable.shape_button_follow);
                                loadData();
                            }
                            else {
                                Toast.makeText(getContext(),response.body().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<ResponsFollow> call, Throwable t) {
                            Toast.makeText(getContext(),t.getMessage(),Toast.LENGTH_SHORT).show();
                        }
                    });
                }
                else {
                    setLoading(true, false);
                    Map<String , String > dataUnFollow = new HashMap<>();
                    dataUnFollow.put("followed_id", String.valueOf(data.getId()));
                    ConnectionApi.apiService(context).unfollow(dataUnFollow).enqueue(new Callback<ResponsUnFollow>() {
                        @Override
                        public void onResponse(Call<ResponsUnFollow> call, Response<ResponsUnFollow> response) {
                            setLoading(false, false);
                            if (response.isSuccessful() && response.body().getSuccess()){
                                Toast.makeText(getContext(),response.body().getMessage(),  Toast.LENGTH_SHORT).show();
                                //txtfollow.setText("+ Follow");
                                //txtfollow.setTextColor(Color.parseColor("#F62C4C"));
                                //txtfollow.setBackgroundResource(R.drawable.shape_car_club);
                                loadData();
                            }
                            else {
                                Toast.makeText(getContext(),response.body().getMessage(),  Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<ResponsUnFollow> call, Throwable t) {
                            Toast.makeText(getContext(),t.getMessage(),Toast.LENGTH_SHORT).show();
                        }
                    });
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
        et_sreach_member = view.findViewById(R.id.etSreachMember);
        progressBar = view.findViewById(R.id.progres);
        tvfollow = view.findViewById(R.id.txtfollow);
    }
}
