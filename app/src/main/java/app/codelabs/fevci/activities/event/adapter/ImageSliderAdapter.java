package app.codelabs.fevci.activities.event.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.smarteist.autoimageslider.SliderViewAdapter;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import app.codelabs.forum.R;

/**
 * Created by edinofri
 * tukangbasic@gmail.com
 * 22 Apr 2020, 11:25
 */
public class ImageSliderAdapter extends
        SliderViewAdapter<ImageSliderAdapter.SliderAdapterVH> {

    private Context context;
    private List<String> items = new ArrayList<>();


    @Override
    public SliderAdapterVH onCreateViewHolder(ViewGroup parent) {
        context = parent.getContext();
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_card_slider_layout_item, null);
        return new SliderAdapterVH(inflate);
    }

    @Override
    public void onBindViewHolder(final SliderAdapterVH viewHolder, final int position) {

        String item = items.get(position);

        Picasso.with(context)
                .load(item)
                .placeholder(R.drawable.default_image)
                .error(R.drawable.default_no_image)
                .centerCrop()
                .fit()
                .into(viewHolder.imageViewBackground);

    }

    @Override
    public int getCount() {
        //slider view count could be dynamic size
        return items.size();
    }

    public void setItems(List<String> data) {
        this.items = data;
        notifyDataSetChanged();
    }


    static class SliderAdapterVH extends SliderViewAdapter.ViewHolder {
        ImageView imageViewBackground;
        SliderAdapterVH(View itemView) {
            super(itemView);
            imageViewBackground = itemView.findViewById(R.id.iv_auto_image_slider);
        }
    }

}
