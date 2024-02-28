package app.codelabs.roadtrip.activities.article.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.ads.nativead.NativeAd;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import app.codelabs.roadtrip.activities.article.DetailArticleActivity;
import app.codelabs.roadtrip.helpers.DateTimeHelper;
import app.codelabs.roadtrip.models.ResponseListArticle;
import app.codelabs.roadtrip.R;

public class NewArticleAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public static final int REQ_REFRESH_ARTICLE_LIST = 2001;
    private static final int IS_AD = 0;
    private static final int NOT_AD = 1;
    private Context context;
    private Fragment fragment;
    String link = "";

    private final ArrayList<Object> item = new ArrayList<>();

    public NewArticleAdapter(Fragment fragment){
        this.fragment = fragment;
    }

    public void setList(List<ResponseListArticle.Data> list){
        this.item.addAll(list);
    }
    public void setAd(List<NativeAd> nativeAd){
        this.item.addAll(nativeAd);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        if (viewType == IS_AD) {
            View view = LayoutInflater.from(context).inflate(R.layout.item_article_ads, parent, false);
            return new AdViewHolderArticle(view);
        } else {
            View view = LayoutInflater.from(context).inflate(R.layout.item_article, parent, false);
            return new ItemViewHolderArticle(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
//        ResponseListShopByCategories.DataEntity items = item.get(position);

        if (getItemViewType(position) == IS_AD){
            AdViewHolderArticle adv = (AdViewHolderArticle) holder;
            adv.setNativeAd((NativeAd) item.get(position));
        }else{
            ResponseListArticle.Data items = (ResponseListArticle.Data) item.get(position);
            ItemViewHolderArticle ivh = (ItemViewHolderArticle) holder;

            ivh.tvCategory.setText(items.getCategory().getCategory());
            ivh.tvContent.setText(Html.fromHtml(items.getContent()));
            ivh.tvTitle.setText(items.getTitle());
            ivh.tvTime.setText(DateTimeHelper.instance(items.getCreatedAt()).getTimeElapsed());

            if (items.getMediaLink() != null) {
                link = items.getMediaLink();
                getYouTubeId();
            } else {
                link = "";
            }

            if (items.getMediaType() == 1){
                Picasso.with(context).load(items.getImage())
                        .placeholder(R.drawable.default_image)
                        .error(R.drawable.default_no_image)
                        .fit().centerCrop().into(ivh.ivImage);
            }else if (items.getMediaType() == 2){
                String thumbnail = "https://img.youtube.com/vi/" +link + "/hqdefault.jpg";
                Picasso.with(context).load(thumbnail)
                        .placeholder(R.drawable.default_image)
                        .error(R.drawable.default_no_image)
                        .fit().centerCrop().into(ivh.ivImage);
            }

            ivh.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ResponseListArticle.Data items = (ResponseListArticle.Data) item.get(position);
                    Intent intent = new Intent(context, DetailArticleActivity.class);
                    intent.putExtra("data", new Gson().toJson(items));
                    fragment.startActivityForResult(intent, REQ_REFRESH_ARTICLE_LIST);
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        return item.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (item.get(position) instanceof NativeAd){
            return IS_AD;
        }else {
            return NOT_AD;
        }
    }

    private void getYouTubeId () {
        String pattern = "(?<=youtu.be/|watch\\?v=|/videos/|embed\\/)[^#\\&\\?]*";
        Pattern compiledPattern = Pattern.compile(pattern);
        Matcher matcher = compiledPattern.matcher(link);
        if(matcher.find()){
            Log.e("TAG", "getYouTubeId: " + matcher.group() );
            link = matcher.group();
//            return matcher.group();
        } else {
//            return "error";
            Toast.makeText(context, "Url error", Toast.LENGTH_SHORT).show();
        }
    }
}
