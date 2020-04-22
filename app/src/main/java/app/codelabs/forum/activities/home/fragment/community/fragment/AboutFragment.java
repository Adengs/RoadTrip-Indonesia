package app.codelabs.forum.activities.home.fragment.community.fragment;


import android.content.Context;
import android.content.Intent;
import android.net.Uri;
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
import app.codelabs.forum.R;
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
        loadData();
    }

    private void loadData() {
        ConnectionApi.apiService(context).getAboutCompany().enqueue(new Callback<ResponseAbout>() {
            @Override
            public void onResponse(Call<ResponseAbout> call, Response<ResponseAbout> response) {
                if (response.body() != null) {
                    if (response.isSuccessful() && response.body().getSuccess()){
                        setAbout(response.body().getData());
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
        tvSecretarian.setText(Html.fromHtml(data.getSecretariat()));
        tvMpas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setMaps(data);
            }
        });
    }

    private void setMaps(ResponseAbout.DataEntity data) {
        String url_map = "https://www.google.com/maps/search/?api=1&query=";
        float zoomLevel = 16.0f; //This goes up to 21
        Uri gmmIntentUri = Uri.parse( url_map +data.getLatitude()+","+data.getLongitude());
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        //mapIntent.setPackage("com.google.android.apps.maps");
        startActivity(mapIntent);
    }

    private void setView(View view) {
        tvHistory = view.findViewById(R.id.tv_History);
        tvSecretarian = view.findViewById(R.id.tv_Secretariat);
        tvMpas = view.findViewById(R.id.tv_map);

    }
}
