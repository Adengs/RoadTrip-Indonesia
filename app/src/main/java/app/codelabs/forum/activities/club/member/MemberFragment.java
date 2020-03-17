package app.codelabs.forum.activities.club.member;


import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import app.codelabs.forum.R;
import app.codelabs.forum.helpers.ConnectionApi;
import app.codelabs.forum.helpers.Session;
import app.codelabs.forum.models.Respons;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class MemberFragment extends Fragment {

    RecyclerView recyclerView;
    private MemberAdapter adapter;
    Context context;
    private Session session;//definisi variabel session dengan tipe data session
    private String token;
    private String apptoken;
    private EditText et_sreach_member;

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
        apptoken = session.getAppToken();
        token = session.getToken();

        setView(view);
        setEvent();
        setRecycleView();
        loadData();
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
                    loadData();

            }
        });
    }

    private void loadData() {
        ConnectionApi.apiService().listMember(token,apptoken,search).enqueue(new Callback<Respons>(){

            @Override
            public void onResponse(Call<Respons> call, Response<Respons> response) {

            }

            @Override
            public void onFailure(Call<Respons> call, Throwable t) {


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
    }
}
