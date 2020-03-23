package app.codelabs.forum.activities.shop.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import app.codelabs.forum.R;
import app.codelabs.forum.activities.shop.ActivityShop;

public class AdapterProductsAllRecycler extends RecyclerView.Adapter<AdapterProductsAllRecycler.MyHolder> {
    private static Context context;

    @NonNull
    @Override
    public AdapterProductsAllRecycler.MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view= LayoutInflater.from(context).inflate(R.layout.item_products_all,parent,false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterProductsAllRecycler.MyHolder holder, int position) {
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ActivityShop.class);
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
        public MyHolder(@NonNull View itemView) {

            super(itemView);
            cardViewPro = itemView.findViewById(R.id.cardShopPro);

        }
    }
}
