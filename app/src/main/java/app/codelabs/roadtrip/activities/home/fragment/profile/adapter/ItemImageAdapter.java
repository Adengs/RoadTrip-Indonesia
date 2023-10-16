package app.codelabs.roadtrip.activities.home.fragment.profile.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.imageview.ShapeableImageView;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.EventBus;

import java.net.URI;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import app.codelabs.roadtrip.R;
import app.codelabs.roadtrip.activities.shop.DetailProductActivity;
import app.codelabs.roadtrip.helpers.ConnectionApi;
import app.codelabs.roadtrip.models.DeleteItemImage;
import app.codelabs.roadtrip.models.ResponseDetailShopItem;
import app.codelabs.roadtrip.models.ResponseMyProfile;
import app.codelabs.roadtrip.models.ResponseUpdateProfile;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ItemImageAdapter extends RecyclerView.Adapter<ItemImageAdapter.MyHolder> {
    public static final int REQ_REFRESH_PRODUCT_LIST = 2001;
    private Context context;
    private List<ResponseDetailShopItem.Data.Photo> item;
    private Fragment fragment;

    public ItemImageAdapter() {
//        this.fragment = fragment;
        this.item = new ArrayList<>();
    }

    @NonNull
    @Override
    public ItemImageAdapter.MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.item_shop_image_item, parent, false);
        return new ItemImageAdapter.MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemImageAdapter.MyHolder holder, int position) {
        ResponseDetailShopItem.Data.Photo items = item.get(position);
        if (items.getImage().isEmpty()){

        }else{
            Picasso.with(context).load(items.getImage())
                    .placeholder(R.drawable.default_image)
                    .error(R.drawable.default_no_image)
                    .fit().centerCrop().into(holder.imageItem);
        }

        holder.imageDlt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EventBus.getDefault().post(new DeleteItemImage(item.get(position).getUserStoreItemImageId() , position));
            }
        });

    }

    @Override
    public int getItemCount() {
        return item.size();
    }

    public void setItems(List<ResponseDetailShopItem.Data.Photo> items) {
        this.item = items;
        notifyDataSetChanged();
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        private ShapeableImageView imageItem;
        private ImageView imageDlt;

        public MyHolder(@NonNull View view) {

            super(view);

            setView(view);

        }

        private void setView(View view) {
            imageItem = view.findViewById(R.id.image_item);
            imageDlt = view.findViewById(R.id.image_delete);
        }
    }
}

