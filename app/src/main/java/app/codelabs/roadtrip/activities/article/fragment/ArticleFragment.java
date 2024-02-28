package app.codelabs.roadtrip.activities.article.fragment;


import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.CountDownTimer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdLoader;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.ads.nativead.NativeAd;
import com.google.android.gms.ads.nativead.NativeAdOptions;

import java.util.ArrayList;
import java.util.List;

import app.codelabs.roadtrip.R;
import app.codelabs.roadtrip.activities.article.adapter.NewArticleAdapter;
import app.codelabs.roadtrip.activities.home.adapter.ArticleAdapter;
import app.codelabs.roadtrip.activities.shop.adapter.AdsLoader;
import app.codelabs.roadtrip.helpers.ConnectionApi;
import app.codelabs.roadtrip.models.ResponseListArticle;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ArticleFragment extends Fragment {
    private RecyclerView recyclerView;
    private ArticleAdapter adapter;
    private NewArticleAdapter newAdapter;
    private Context context;

    public final static int LATEST = 0;
    public final static int POPULAR = 1;
    public final static int CATEGORY = 2;
    public final static int POST = 3;

    public ArticleFragment() {
        // Required empty public constructor
    }

    private ArrayList<ResponseListArticle.Data> list;
    private List<NativeAd> nativeAdList;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_article, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        context = getContext();

        setView(view);
        setRecyclerView();
        loadData();
        ads(view);
    }

    private void ads(View view) {
        AdsLoader adsLoader = new AdsLoader();
        MobileAds.initialize(context, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(@NonNull InitializationStatus initializationStatus) {
                AdLoader adLoader = new AdLoader.Builder(context, "ca-app-pub-5403593084519358/4862345174")
                        .forNativeAd(new NativeAd.OnNativeAdLoadedListener() {
                            @Override
                            public void onNativeAdLoaded(NativeAd nativeAd) {
                                Log.d("TAG", "Native Ad Loaded");

//                                if (isDestroyed)

                                nativeAdList.add(nativeAd);

                                if (!adsLoader.getAdLoader().isLoading()){
                                    newAdapter.setAd(nativeAdList);
                                }
                            }
                        })

                        .withAdListener(new AdListener() {
                            @Override
                            public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                                Log.d("TAG", "Native Ad Failed to Load");

                                new CountDownTimer(1000, 1000){

                                    @Override
                                    public void onTick(long l) {
                                        Log.d("TAG", "Timer : " + l/1000);
                                    }

                                    @Override
                                    public void onFinish() {
                                        Log.d("TAG", "Reloading Native Ad");
                                        ads(view);
                                    }
                                }.start();
                            }
                        })
                        .withNativeAdOptions(new NativeAdOptions.Builder().build())
                        .build();

                adLoader.loadAds(new AdRequest.Builder().build(), 1);

                adsLoader.setAdLoader(adLoader);
            }
        });
    }

    private void loadData() {
        if (getArguments() != null) {
            int type = getArguments().getInt("type", 0);
            if (type == LATEST) {
                getLatestArticle();
            } else if (type == POPULAR) {
                getPopularArticle();
            } else if (type == CATEGORY) {
                getArticleByCategory();
            } else if (type == POST ){
                getArticleByCategory();
            }
        }
    }

    private void getArticleByCategory() {
        if (getArguments() != null) {
            int referenceId = getArguments().getInt("reference_id", 0);
            ConnectionApi.apiService(context).getArticleByCategory(referenceId).enqueue(new Callback<ResponseListArticle>() {
                @Override
                public void onResponse(Call<ResponseListArticle> call, Response<ResponseListArticle> response) {
                    if (response.body() != null) {
                        if (response.isSuccessful() & response.body().getSuccess()) {
//                            setArticles(response.body().getData());
                            list.addAll(response.body().getData());
                            newAdapter.setList(list);
                        }
                    }
                }

                @Override
                public void onFailure(Call<ResponseListArticle> call, Throwable t) {
                    if (t.getMessage() != null) {
                        Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    private void getPopularArticle() {
        ConnectionApi.apiService(context).getPopularArticle().enqueue(new Callback<ResponseListArticle>() {
            @Override
            public void onResponse(Call<ResponseListArticle> call, Response<ResponseListArticle> response) {
                if (response.body() != null) {
                    if (response.isSuccessful() & response.body().getSuccess()) {
//                        setArticles(response.body().getData());
                        list.addAll(response.body().getData());
                        newAdapter.setList(list);
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseListArticle> call, Throwable t) {
                if (t.getMessage() != null) {
                    Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void getLatestArticle() {
        ConnectionApi.apiService(context).getLatestArticle().enqueue(new Callback<ResponseListArticle>() {
            @Override
            public void onResponse(Call<ResponseListArticle> call, Response<ResponseListArticle> response) {
                if (response.body() != null) {
                    if (response.isSuccessful() & response.body().getSuccess()) {
//                        setArticles(response.body().getData());
                        list.addAll(response.body().getData());
                        newAdapter.setList(list);
                    }else {
                        Toast.makeText(context, response.message(), Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseListArticle> call, Throwable t) {
                if(t.getMessage() != null){
                    Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void setArticles(List<ResponseListArticle.Data> data) {
        adapter.setItems(data);
    }

    private void setRecyclerView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
//        recyclerView.setAdapter(adapter);


        recyclerView.setAdapter(newAdapter);

    }

    private void setView(View view) {
        recyclerView = view.findViewById(R.id.viewtablayout);
//        adapter = new ArticleAdapter(this);

        list = new ArrayList<>();
        newAdapter = new NewArticleAdapter(this);

        nativeAdList = new ArrayList<>();

    }

    public ArticleFragment setType(int type) {
        Bundle args = new Bundle();
        args.putInt("type", type);
        setArguments(args);
        return this;
    }

    public ArticleFragment setTypeAndReferenceId(int type, int referenceId) {
        Bundle args = new Bundle();
        args.putInt("type", type);
        args.putInt("reference_id", referenceId);
        setArguments(args);
        return this;
    }
}
