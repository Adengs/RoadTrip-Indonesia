package app.codelabs.forum.activities.shop.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import app.codelabs.forum.R;
import app.codelabs.forum.activities.shop.ActivityShop;

public class AdapterListShop extends RecyclerView.Adapter<AdapterListShop.MyHolder> {
    private static Context context;

    @NonNull
    @Override
    public AdapterListShop.MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view= LayoutInflater.from(context).inflate(R.layout.item_shop,parent,false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterListShop.MyHolder holder, int position) {
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
        private TextView tvProductName, tvLocation, tvPrice , tvCategory;
        private ImageView ivImage;
        private CardView cardViewPro;
        public MyHolder(@NonNull View view) {

            super(view);

            setView(view);


        }

        private void setView(View view) {
            tvProductName = view.findViewById(R.id.tvproductName);
            tvLocation = view.findViewById(R.id.tvlocation);
            tvPrice = view.findViewById(R.id.tvPrice);
            tvCategory = view.findViewById(R.id.tvCategory);
            cardViewPro = itemView.findViewById(R.id.cardShopPro);

        }
    }
}
