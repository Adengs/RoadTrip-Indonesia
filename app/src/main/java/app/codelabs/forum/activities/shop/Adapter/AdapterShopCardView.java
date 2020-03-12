package app.codelabs.forum.activities.shop.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import app.codelabs.forum.R;

public class AdapterShopCardView extends RecyclerView.Adapter<AdapterShopCardView.MyHolder> {

    Context context;
    List<Fragment> items =new ArrayList<>();


    @NonNull
    @Override
    public AdapterShopCardView.MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context=parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.item_products,parent,false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterShopCardView.MyHolder holder, int position) {
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ActivityShopPenjelasan.class);
                v.getContext().startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return 10;
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        CardView cardViewPro;
        public MyHolder(@NonNull View view) {
            super(view);
            cardViewPro = view.findViewById(R.id.cardShopPro);
        }
    }
}
