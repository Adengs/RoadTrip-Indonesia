package app.codelabs.forum.activities.home.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.smarteist.autoimageslider.SliderViewAdapter;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import app.codelabs.forum.R;
import app.codelabs.forum.activities.article.DetailArticleActivity;
import app.codelabs.forum.activities.event.DetailEventActivity;
import app.codelabs.forum.models.ResponseHighlight;


public class HomeImageSliderAdapter extends
        SliderViewAdapter<HomeImageSliderAdapter.SliderAdapterVH> {

    private Context context;
    private List<ResponseHighlight.DataEntity> items = new ArrayList<>();

    public HomeImageSliderAdapter(Context context) {
        this.context = context;
    }

    @Override
    public SliderAdapterVH onCreateViewHolder(ViewGroup parent) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_card_slider_layout_item, null);
        return new SliderAdapterVH(inflate);
    }

    @Override
    public void onBindViewHolder(final SliderAdapterVH viewHolder, final int position) {

        ResponseHighlight.DataEntity item = items.get(position);

        viewHolder.textViewDescription.setText(item.getModule_title());
        Picasso.with(context)
                .load(item.getModule_image())
                .placeholder(R.drawable.default_image)
                .error(R.drawable.default_no_image)
                .centerCrop()
                .fit()
                .into(viewHolder.imageViewBackground);

        viewHolder.imageViewBackground.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ResponseHighlight.DataEntity item = items.get(position);
                if (item.getModule_type().equals("article")) {
                    Intent intent = new Intent(context, DetailArticleActivity.class);
                    intent.putExtra("article_id", item.getModule_id());
                    context.startActivity(intent);
                } else if (item.getModule_type().equals("event")) {
                    Intent intent = new Intent(context, DetailEventActivity.class);
                    intent.putExtra("event_id", item.getModule_id());
                    context.startActivity(intent);
                }
            }
        });

    }

    @Override
    public int getCount() {
        //slider view count could be dynamic size
        return items.size();
    }

    public void setItems(List<ResponseHighlight.DataEntity> data) {
        this.items = data;
        notifyDataSetChanged();
    }


    class SliderAdapterVH extends SliderViewAdapter.ViewHolder {

        ImageView imageViewBackground;
        TextView textViewDescription;

        SliderAdapterVH(View itemView) {
            super(itemView);
            imageViewBackground = itemView.findViewById(R.id.iv_auto_image_slider);
            textViewDescription = itemView.findViewById(R.id.tv_auto_image_slider);

        }

    }
}
