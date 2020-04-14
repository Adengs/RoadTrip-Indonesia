package app.codelabs.forum.activities.shop.Adapter;

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
import app.codelabs.forum.models.ResponseListShopByCategories;

public class AdapterListShop extends RecyclerView.Adapter<AdapterListShop.MyHolder> {
    private  Context context;
    private List<ResponseListShopByCategories.DataEntity> item ;

    public AdapterListShop() {
        this.item = new ArrayList<>();
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
        ResponseListShopByCategories.DataEntity items = item.get(position);
        holder.tvProductName.setText(Html.fromHtml(items.getName()));
        holder.tvLocation.setText(Html.fromHtml(items.getLocation()));
        holder.tvPrice.setText(Html.fromHtml(String.valueOf(items.getPrice())));
        holder.tvCategory.setText(Html.fromHtml(items.getCategory()));
        Picasso.with(context).load(items.getPhoto())
                .placeholder(R.drawable.default_image)
                .error(R.drawable.default_no_image)
                .fit().centerCrop().into(holder.ivImage);

    }

    @Override
    public int getItemCount() {
        return item.size();
    }

    public void setItems(List<ResponseListShopByCategories.DataEntity> items) {
        this.item= items;
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
                    ResponseListShopByCategories.DataEntity items = item.get(getAdapterPosition());
                    Intent intent = new Intent(context, DetailProductActivity.class);
                    intent.putExtra("data", new Gson().toJson(items));
                    context.startActivity(intent);
                }
            });

        }

        private void setView(View view) {
            tvProductName = view.findViewById(R.id.tv_Product_Name);
            tvLocation = view.findViewById(R.id.tv_Location);
            tvPrice = view.findViewById(R.id.tv_Price);
            tvCategory = view.findViewById(R.id.tv_Category);
            cardViewPro = itemView.findViewById(R.id.cardShopPro);
            ivImage = view.findViewById(R.id.iv_Image);

        }
    }
}
