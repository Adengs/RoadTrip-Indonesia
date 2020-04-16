package app.codelabs.forum.activities.shop.adapter;

import android.content.Context;
import android.content.Intent;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import app.codelabs.forum.R;
import app.codelabs.forum.activities.shop.DetailProductActivity;
import app.codelabs.forum.models.ResponsListShopByCategories;

public class AdapterListShop extends RecyclerView.Adapter<AdapterListShop.MyHolder> {
    private static Context context;
    private List<ResponsListShopByCategories.DataEntity> data ;

    public AdapterListShop() {
        this.data = new ArrayList<>();
    }

    @NonNull
    @Override
    public AdapterListShop.MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view= LayoutInflater.from(context).inflate(R.layout.item_shop,parent,false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterListShop.MyHolder holder, int position) {
        ResponsListShopByCategories.DataEntity item = data.get(position);
        holder.tvProductName.setText(Html.fromHtml(item.getName()));
        holder.tvLocation.setText(Html.fromHtml(item.getLocation()));
        holder.tvPrice.setText(Html.fromHtml(String.valueOf(item.getPrice())));
        holder.tvCategory.setText(Html.fromHtml(item.getCategory()));
        Picasso.with(context).load(item.getPhoto())
                .placeholder(R.drawable.default_image)
                .error(R.drawable.default_no_image)
                .fit().centerCrop().into(holder.ivImage);

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void setItems(List<ResponsListShopByCategories.DataEntity> datas) {
        this.data= datas;
        notifyDataSetChanged();
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        private TextView tvProductName, tvLocation, tvPrice , tvCategory;
        private ImageView ivImage;
        private CardView cardViewPro;
        public MyHolder(@NonNull View view) {

            super(view);

            setView(view);
            setEvent();


        }

        private void setEvent() {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ResponsListShopByCategories.DataEntity item = data.get(getAdapterPosition());
                    Intent intent = new Intent(context, DetailProductActivity.class);
                    intent.putExtra("data", new Gson().toJson(item));
                    context.startActivity(intent);
                }
            });

        }

        private void setView(View view) {
            tvProductName = view.findViewById(R.id.tvproductName);
            tvLocation = view.findViewById(R.id.tvlocation);
            tvPrice = view.findViewById(R.id.tvPrice);
            tvCategory = view.findViewById(R.id.tvCategory);
            cardViewPro = itemView.findViewById(R.id.cardShopPro);
            ivImage = view.findViewById(R.id.ivimage);

        }
    }
}
