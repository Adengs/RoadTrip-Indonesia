package app.codelabs.forum.activities.gallery.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import app.codelabs.forum.R;
import app.codelabs.forum.activities.gallery.DetailGalleryActivity;

public class ImageGalleryAdapter extends RecyclerView.Adapter<ImageGalleryAdapter.ImageGalleryVH> {
    private Context context;
    private List<String> items;

    ImageGalleryAdapter(List<String> photos) {
        this.items = photos;
    }

    @NonNull
    @Override
    public ImageGalleryVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.item_image_gallery, parent, false);
        return new ImageGalleryVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageGalleryVH holder, int position) {
        String image = items.get(position);
        Picasso.with(context).load(image).fit().centerCrop().into(holder.ivImage);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class ImageGalleryVH extends RecyclerView.ViewHolder {
        ImageView ivImage;

        ImageGalleryVH(@NonNull View view) {
            super(view);
            setView(view);
            setEvent();
        }

        private void setEvent() {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, DetailGalleryActivity.class);
                    intent.putExtra("image",items.get(getAdapterPosition()));
                    context.startActivity(intent);
                }
            });
        }

        private void setView(View view) {
            ivImage = view.findViewById(R.id.iv_image);
        }
    }
}
