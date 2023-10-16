package app.codelabs.roadtrip.activities.home.fragment.community.fragment;


import android.content.Context;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import app.codelabs.roadtrip.R;
import app.codelabs.roadtrip.activities.about.adapter.SecretariatAdapter;
import app.codelabs.roadtrip.helpers.ConnectionApi;
import app.codelabs.roadtrip.helpers.Session;
import app.codelabs.roadtrip.models.ResponseAbout;
import app.codelabs.roadtrip.models.ResponseAboutUs;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class AboutFragment extends Fragment {
    private TextView tvHistory, tvSecretarian, tvMpas, tvTitle;
    private ImageView ivBackGround;
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
//        setRecyclerView();
        loadData();
    }

    private void setRecyclerView() {
        rvSecretariat.setHasFixedSize(true);
        rvSecretariat.setLayoutManager(new LinearLayoutManager(context));
        rvSecretariat.setAdapter(adapter);
    }

    private void loadData() {
        ConnectionApi.apiService(context).getAboutUs().enqueue(new Callback<ResponseAboutUs>() {
            @Override
            public void onResponse(Call<ResponseAboutUs> call, Response<ResponseAboutUs> response) {
                if (response.body() != null) {
                    if (response.isSuccessful() && response.body().getSuccess()){
                        setAboutUs(response.body().getData());
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseAboutUs> call, Throwable t) {
                if (t.getMessage() != null) {
                    Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
//        ConnectionApi.apiService(context).getAboutCompany().enqueue(new Callback<ResponseAbout>() {
//            @Override
//            public void onResponse(Call<ResponseAbout> call, Response<ResponseAbout> response) {
//                if (response.body() != null) {
//                    if (response.isSuccessful() && response.body().getSuccess()){
//                        setAbout(response.body().getData());
//                        adapter.setItems(response.body().getData().getSecretariat());
//                    }
//                }
//            }
//            @Override
//            public void onFailure(Call<ResponseAbout> call, Throwable t) {
//                if (t.getMessage() != null) {
//                    Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
    }

    private void setAboutUs(final ResponseAboutUs.Data data) {
        tvTitle.setText(Html.fromHtml(data.getTitle()));
        tvHistory.setText(Html.fromHtml(data.getContent()));
        Picasso.with(context).load(data.getImageUrl()).fit().centerCrop().into(ivBackGround);
    }

//    private void setAbout(final ResponseAbout.DataEntity data) {
//        tvTitle.setText(Html.fromHtml(data.getTitle()));
//        tvHistory.setText(Html.fromHtml(data.getHistory()));
//    }

    private void setView(View view) {
        tvHistory = view.findViewById(R.id.tv_History);
//        tvSecretarian = view.findViewById(R.id.tv_Secretariat);
//        tvMpas = view.findViewById(R.id.tv_map);
//        rvSecretariat = view.findViewById(R.id.rv_secretariat);
        tvTitle = view.findViewById(R.id.tv_title);
        ivBackGround = view.findViewById(R.id.iv_background);

    }
}
