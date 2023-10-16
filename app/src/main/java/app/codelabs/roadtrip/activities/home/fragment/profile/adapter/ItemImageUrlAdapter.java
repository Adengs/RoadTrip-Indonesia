package app.codelabs.roadtrip.activities.home.fragment.profile.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.imageview.ShapeableImageView;
import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import app.codelabs.roadtrip.R;
import app.codelabs.roadtrip.activities.shop.DetailProductActivity;
import app.codelabs.roadtrip.helpers.ConnectionApi;
import app.codelabs.roadtrip.models.DeleteImage;
import app.codelabs.roadtrip.models.ResponseDetailShopItem;
import app.codelabs.roadtrip.models.ResponseUpdateProfile;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ItemImageUrlAdapter extends RecyclerView.Adapter<ItemImageUrlAdapter.MyHolder> {
    public static final int REQ_REFRESH_PRODUCT_LIST = 2001;
    private Context context;
    private List<Uri> item;
    private Fragment fragment;

    public ItemImageUrlAdapter() {
//        this.fragment = fragment;
        this.item = new ArrayList<>();
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.item_shop_image_item, parent, false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
//        Uri items = item.get(position);
//        if (items.getImage().isEmpty()){
//
//        }else{
//            Picasso.with(context).load(items.getImage())
//                    .placeholder(R.drawable.default_image)
//                    .error(R.drawable.default_no_image)
//                    .fit().centerCrop().into(holder.imageItem);
//        }

        holder.imageItem.setImageURI(item.get(position));

        holder.imageDlt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                item.remove(position);
//                notifyItemRemoved(position);
                EventBus.getDefault().post(new DeleteImage(position));
//                notifyDataSetChanged();

//                ConnectionApi.apiService(context).removeItem(Integer.parseInt(item.get(position).toString())).enqueue(new Callback<ResponseUpdateProfile>() {
//                    @Override
//                    public void onResponse(Call<ResponseUpdateProfile> call, Response<ResponseUpdateProfile> response) {
//                        if (response.body() != null) {
//                            if (response.isSuccessful() && response.body().getSuccess()) {
//                                Intent intent = new Intent(context, DetailProductActivity.class);
//                                intent.putExtra("data", new Gson().toJson(response.body().getData()));
//                                fragment.startActivityForResult(intent, REQ_REFRESH_PRODUCT_LIST);
//                            }
//
//                        }
//                    }
//
//                    @Override
//                    public void onFailure(Call<ResponseUpdateProfile> call, Throwable t) {
//
//                    }
//                });
            }
        });
    }

    @Override
    public int getItemCount() {
        return item.size();
    }

    public void setItems(List<Uri> items) {
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
