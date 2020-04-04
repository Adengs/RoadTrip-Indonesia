package app.codelabs.forum.activities.club.about;


import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import app.codelabs.forum.R;
import app.codelabs.forum.helpers.ConnectionApi;
import app.codelabs.forum.helpers.Session;
import app.codelabs.forum.models.ResponsAbout;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class AboutFragment extends Fragment {
    private TextView tvHistory, tvSecretarian;
    private Session session;
    private String token;
    private String apptoken;
    private Context context;


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
        apptoken = session.getAppToken();
        token = session.getToken();

        setView(view);
        loadData();
    }

    private void loadData() {
        ConnectionApi.apiService().aboutCompany(token, apptoken).enqueue(new Callback<ResponsAbout>() {
            @Override
            public void onResponse(Call<ResponsAbout> call, Response<ResponsAbout> response) {
                if (response.isSuccessful() && response.body().getSuccess()){
                    setAbout(response.body().getData());
                }else {
                    Toast.makeText(context, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponsAbout> call, Throwable t) {
                Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setAbout(ResponsAbout.DataEntity data) {
        tvHistory.setText(data.getHistory());
        tvSecretarian.setText(data.getSecretariat());
    }

    private void setView(View view) {
        tvHistory = view.findViewById(R.id.tvHistoryAbout);
        tvSecretarian = view.findViewById(R.id.tvSecretariatAbout);
    }
}
