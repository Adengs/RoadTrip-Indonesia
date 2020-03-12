package app.codelabs.forum.activities.shop.Fragment;


import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import app.codelabs.forum.R;
import app.codelabs.forum.activities.shop.Adapter.AdapterProducts;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentShopProducts extends Fragment {
    RecyclerView recyclerView;
    private AdapterProducts adapter;
    Context context;
    CardView cardviewpro;


    public FragmentShopProducts() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_shop_products, container, false);
    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setView(view);
        setEvent();
        setRecycleView();

    }



    private void setEvent() {


    }

    private void setView(View view) {
        adapter = new AdapterProducts();
        cardviewpro = view.findViewById(R.id.cardShopPro);
        recyclerView = view.findViewById(R.id.recyclerviewShop);
    }
    private void setRecycleView() {
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(adapter);
    }

}
