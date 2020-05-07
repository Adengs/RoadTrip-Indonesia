package app.codelabs.forum.activities.home.fragment.community.fragment;


import android.content.Context;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import app.codelabs.forum.R;
import app.codelabs.forum.activities.about.adapter.SecretariatAdapter;
import app.codelabs.forum.helpers.ConnectionApi;
import app.codelabs.forum.helpers.Session;
import app.codelabs.forum.models.ResponseAbout;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class AboutFragment extends Fragment {
    private TextView tvHistory, tvSecretarian, tvMpas;
    private Session session;
    private Context context;
    private RecyclerView rvSecretariat;
    private SecretariatAdapter adapter = new SecretariatAdapter();


    public AboutFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_about, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        context = getContext();
        session = Session.init(context);

        setView(view);
        setRecyclerView();
        loadData();
    }

    private void setRecyclerView() {
        rvSecretariat.setHasFixedSize(true);
        rvSecretariat.setLayoutManager(new LinearLayoutManager(context));
        rvSecretariat.setAdapter(adapter);
    }

    private void loadData() {
        ConnectionApi.apiService(context).getAboutCompany().enqueue(new Callback<ResponseAbout>() {
            @Override
            public void onResponse(Call<ResponseAbout> call, Response<ResponseAbout> response) {
                if (response.body() != null) {
                    if (response.isSuccessful() && response.body().getSuccess()){
                        setAbout(response.body().getData());
                        adapter.setItems(response.body().getData().getSecretariat());
                    }
                }
            }
            @Override
            public void onFailure(Call<ResponseAbout> call, Throwable t) {
                if (t.getMessage() != null) {
                    Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void setAbout(final ResponseAbout.DataEntity data) {
        tvHistory.setText(Html.fromHtml(data.getHistory()));
    }

    private void setView(View view) {
        tvHistory = view.findViewById(R.id.tv_History);
        tvSecretarian = view.findViewById(R.id.tv_Secretariat);
        tvMpas = view.findViewById(R.id.tv_map);
        rvSecretariat = view.findViewById(R.id.rv_secretariat);

    }
}
