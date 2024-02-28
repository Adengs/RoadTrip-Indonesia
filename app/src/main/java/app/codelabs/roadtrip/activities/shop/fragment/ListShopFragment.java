package app.codelabs.roadtrip.activities.shop.fragment;

import android.content.Context;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
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

import app.codelabs.roadtrip.activities.shop.adapter.AdapterListShop;
import app.codelabs.roadtrip.activities.shop.adapter.AdsLoader;
import app.codelabs.roadtrip.activities.shop.adapter.NewAdapterListShop;
import app.codelabs.roadtrip.models.EventBusClass;
import app.codelabs.roadtrip.models.ResponseListShopByCategories;
import app.codelabs.roadtrip.R;
import app.codelabs.roadtrip.helpers.ConnectionApi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class ListShopFragment extends Fragment {
    private RecyclerView recyclerView;
    private AdapterListShop adapter;
    private NewAdapterListShop newAdapter;
    private Context context;
    private String search;
    public final static int CATEGORIES = 0;

    public ListShopFragment() {
        // Required empty public constructor
    }

    private ArrayList<ResponseListShopByCategories.DataEntity> list;
    private List<NativeAd> nativeAdList;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list_shop, container, false);
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

//                                NativeTemplateStyle styles = new
//                                        NativeTemplateStyle.Builder().build();
//                                TemplateView template = view.findViewById(R.id.ads);
//                                template.setStyles(styles);
//                                template.setNativeAd(nativeAd);
                            }
                        })

                        .withAdListener(new AdListener() {
                            @Override
                            public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
//                                super.onAdFailedToLoad(loadAdError);
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
            if (type == CATEGORIES) {
                getShopByCategory();
            }
        }

    }

    private void getShopByCategory() {
        if (getArguments() != null) {
            int referenceId = getArguments().getInt("reference_id", 0);
            ConnectionApi.apiService(context).getShopByCategories(referenceId,search).enqueue(new Callback<ResponseListShopByCategories>() {
                @Override
                public void onResponse(Call<ResponseListShopByCategories> call, Response<ResponseListShopByCategories> response) {
                    if (response.body() != null) {
                        if (response.isSuccessful() & response.body().getSuccess()) {
//                            setListShop(response.body().getData());
                            list.addAll(response.body().getData());
                            newAdapter.setList(list);
                        }
                    }
                }

                @Override
                public void onFailure(Call<ResponseListShopByCategories> call, Throwable t) {
                    if (t.getMessage() != null) {
                        Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    private void setListShop(List<ResponseListShopByCategories.DataEntity> data) {
        adapter.setItems(data);
    }


    private void setRecyclerView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
//        recyclerView.setAdapter(adapter);
        recyclerView.setAdapter(newAdapter);
    }

    private void setView(View view) {
        recyclerView = view.findViewById(R.id.recyclerview);
//        adapter = new AdapterListShop(this);
        list = new ArrayList<>();
        newAdapter = new NewAdapterListShop(this);

        nativeAdList = new ArrayList<>();

    }

    public ListShopFragment setTypeAndReferenceId(int type, int referenceId) {
        Bundle args = new Bundle();
        args.putInt("type", type);
        args.putInt("reference_id", referenceId);
        setArguments(args);
        return this;
    }
    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onSearch(EventBusClass.SearchShop eventBusSearch){
        search = eventBusSearch.getQuery();
        getShopByCategory();
    }
}
