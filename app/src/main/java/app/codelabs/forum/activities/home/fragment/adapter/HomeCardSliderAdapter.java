package app.codelabs.forum.activities.home.fragment.adapter;

import android.content.Context;
import android.util.DisplayMetrics;
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
import app.codelabs.forum.models.ResponseHighlight;


public class HomeCardSliderAdapter extends
        SliderViewAdapter<HomeCardSliderAdapter.SliderAdapterVH> {

    private Context context;
    private List<ResponseHighlight.DataEntity> data = new ArrayList<>();


    //private List<SliderItem> items = new ArrayList<>();

    public HomeCardSliderAdapter(Context context) {
        this.context = context;
    }

    //public void addItem(SliderItem sliderItem) {
       // this.items.add(sliderItem);
        //notifyDataSetChanged();
    //}

    @Override
    public SliderAdapterVH onCreateViewHolder(ViewGroup parent) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_card_slider_layout_item, null);
        return new SliderAdapterVH(inflate);
    }

    @Override
    public void onBindViewHolder(final SliderAdapterVH viewHolder, final int position) {


//        SliderItem sliderItem = items.get(position);
        ResponseHighlight.DataEntity datas = data.get(position);

        viewHolder.textViewDescription.setText(datas.getModule_title());
        viewHolder.textViewDescription.setTextSize(16);
        Picasso.with(context)
                .load(datas.getModule_image())
                .centerCrop()
                .fit()
                .into(viewHolder.imageViewBackground);

    }

    @Override
    public int getCount() {
        //slider view count could be dynamic size
        return data.size();
    }
    public void setItems(List<ResponseHighlight.DataEntity> datas) {
        this.data = datas;
        notifyDataSetChanged();
    }


    static class SliderAdapterVH extends SliderViewAdapter.ViewHolder {

        View itemView;
        ImageView imageViewBackground;
        TextView textViewDescription;

        SliderAdapterVH(View itemView) {
            super(itemView);
            imageViewBackground = itemView.findViewById(R.id.iv_auto_image_slider);
            textViewDescription = itemView.findViewById(R.id.tv_auto_image_slider);
            this.itemView = itemView;
        }
    }

    private int toDP(int size, Context context) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        return Math.round(size * (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));
    }
}
