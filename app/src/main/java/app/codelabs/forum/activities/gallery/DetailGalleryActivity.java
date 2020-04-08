package app.codelabs.forum.activities.gallery;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import app.codelabs.forum.R;

import android.content.Context;
import android.os.Bundle;
import android.view.MenuItem;

import com.github.chrisbanes.photoview.PhotoView;
import com.squareup.picasso.Picasso;

public class DetailGalleryActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private PhotoView ivPhoto;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_gallery);
        context = getApplicationContext();

        setView();
        setEvent();
        setToolbar();
        setData();
    }

    private void setData() {
        String image = getIntent().getStringExtra("image");
        Picasso.with(context).load(image).into(ivPhoto);
    }

    private void setEvent() {

    }

    private void setView() {
        toolbar = findViewById(R.id.toolbar);
        ivPhoto = findViewById(R.id.photo_view);
    }

    private void setToolbar() {
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        setTitle("");
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }
}
