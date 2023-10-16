package app.codelabs.roadtrip.activities.home.fragment.profile.activity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import com.github.dhaval2404.imagepicker.ImagePicker;
import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import app.codelabs.roadtrip.R;
import app.codelabs.roadtrip.activities.custom.ProgressDialogFragment;
import app.codelabs.roadtrip.activities.home.fragment.profile.adapter.ItemImageAdapter;
import app.codelabs.roadtrip.activities.home.fragment.profile.adapter.ItemImageUrlAdapter;
import app.codelabs.roadtrip.activities.home.fragment.profile.fragment.BottomSheetCategoryShop;
import app.codelabs.roadtrip.activities.profile.EditProfileActivity;
import app.codelabs.roadtrip.activities.profile.bottom_sheet.BottomSheetImagePicker;
import app.codelabs.roadtrip.activities.shop.DetailProductActivity;
import app.codelabs.roadtrip.databinding.ActivityNewPostProfileBinding;
import app.codelabs.roadtrip.helpers.CompressImage;
import app.codelabs.roadtrip.helpers.ConnectionApi;
import app.codelabs.roadtrip.helpers.RealPath;
import app.codelabs.roadtrip.helpers.Session;
import app.codelabs.roadtrip.helpers.Validator;
import app.codelabs.roadtrip.models.CategorySelected;
import app.codelabs.roadtrip.models.DeleteImage;
import app.codelabs.roadtrip.models.DeleteItemImage;
import app.codelabs.roadtrip.models.ResponseAddItem;
import app.codelabs.roadtrip.models.ResponseDetailShopItem;
import app.codelabs.roadtrip.models.ResponseMyProfile;
import app.codelabs.roadtrip.models.ResponseUpdateProfile;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewPostProfileActivity extends AppCompatActivity {
    public Uri imageUri = null;
    ArrayList<Uri> imageUrl;
    ArrayList<File> listImage;
    private Boolean editMode = false;
    private Toolbar toolbar;
    private Context context;
    private Session session;
    private EditText etTitle, etCategory, etPrice, etStock, etDescription;
    private Button btnSave;
    private ImageView imagePost;
    private RecyclerView rvImage, rvImageUrl;
    private Bitmap bitmap;
    private ByteArrayOutputStream byteArrayStream;
    private ProgressDialogFragment progressDialogFragment = new ProgressDialogFragment();
    private BottomSheetImagePicker bottomSheetPickImage = new BottomSheetImagePicker();
    private BottomSheetCategoryShop bottomSheetCategoryShop = new BottomSheetCategoryShop();

    private ItemImageUrlAdapter adapter;
    private ItemImageAdapter adapterImage;
    private ActivityNewPostProfileBinding binding;
    int id = 0;
    int idEdit = 0;

    public ResponseDetailShopItem.Data data;
    public List<ResponseDetailShopItem.Data.Photo> item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityNewPostProfileBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        context = getApplicationContext();
        session = new Session(context);
        setView();
        setToolbar();
        setEvent();

        checkCondition();

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    private void setToolbar() {
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        if (getIntent().getStringExtra("edit") != null){
            setTitle("Edit Post");
        }else{
            setTitle("New Post");
        }
    }

    private void checkCondition() {
        if (getIntent().getStringExtra("edit") != null){
            editMode = true;
            String strData = getIntent().getStringExtra("edit");
            data = new Gson().fromJson(strData, ResponseDetailShopItem.Data.class);
//            id = data.getId();

            ConnectionApi.apiService(context).getShopDetailItem(data.getId()).enqueue(new Callback<ResponseDetailShopItem>() {
                @Override
                public void onResponse(Call<ResponseDetailShopItem> call, Response<ResponseDetailShopItem> response) {
                    if (response.body() != null) {
                        if (response.isSuccessful() && response.body().getSuccess()) {
                            data = response.body().getData();

                            item = data.getPhoto();
                            adapterImage = new ItemImageAdapter();
                            rvImage.setHasFixedSize(true);
                            adapterImage.setItems(item);
                            Log.e("TAG", "checkCondition: " + item);
                            rvImageUrl.setAdapter(adapterImage);
                            etTitle.setText(data.getName());
                            etCategory.setText(data.getCategory().getCategory());
                            etPrice.setText(String.valueOf(data.getPrice()));
                            etStock.setText(String.valueOf(data.getStock()));
                            etDescription.setText(data.getDescription());
                            if (id == 0){
                                idEdit = data.getCategory().getId();
                            }else{
                                idEdit = id;
                            }
                            rvImage.setAdapter(adapter);
                        }

                    }
                }

                @Override
                public void onFailure(Call<ResponseDetailShopItem> call, Throwable t) {
                    if (t.getMessage() != null) {
                        Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
                    }

                }
            });

//            Type listType = new TypeToken <List<ResponseDetailShopItem.Data.Photo>>(){}.getType();
//            ResponseDetailShopItem.Data.Photo items = new Gson().fromJson(strData, ResponseDetailShopItem.Data.Photo.class);
//            List<ResponseDetailShopItem.Data.Photo> item = new Gson().fromJson(strData, listType);

        }
        else {
            rvImage.setAdapter(adapter);
        }
    }

    private void setView() {
        imageUrl = new ArrayList<>();
        listImage = new ArrayList<>();
        toolbar = findViewById(R.id.toolbar);
        etTitle = findViewById(R.id.editText_product_title);
        etCategory = findViewById(R.id.editText_product_category);
        etPrice = findViewById(R.id.editText_product_price);
        etStock = findViewById(R.id.editText_product_stock);
        etDescription = findViewById(R.id.editText_product_description);
        imagePost = findViewById(R.id.image_new_post);
        btnSave = findViewById(R.id.btn_save);
        rvImage = findViewById(R.id.recylerView_photo);
        rvImageUrl = findViewById(R.id.recyclerView_photo_url);


        adapter = new ItemImageUrlAdapter();
        rvImageUrl.setHasFixedSize(true);
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
//        linearLayoutManager.setReverseLayout(true);
//        rvImageUrl.setLayoutManager(linearLayoutManager);
//        rvImageUrl.setAdapter(adapter);
    }

    private void setEvent() {
        imagePost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImagePicker.with(NewPostProfileActivity.this)
                        .crop()
                        .compress(1024)
                        .maxResultSize(1080,1080)
                        .start();

//                bottomSheetPickImage.show(getSupportFragmentManager(), "get-imagePost");
            }
        });
        etCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bottomSheetCategoryShop.show(getSupportFragmentManager(), "get-category");
            }
        });
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (getIntent().getStringExtra("edit") != null) {
                    getEdit();
                } else {
                    setBtnSave();
                }
            }
        });
    }

    private void getEdit() {
        List<MultipartBody.Part> fileImagePart = new ArrayList();
        Map<String, RequestBody> datas = new HashMap<>();
        datas.put("title", RequestBody.create(MediaType.parse("text/plain"), etTitle.getText().toString()));
        datas.put("category_id", RequestBody.create(MediaType.parse("text/plain"), String.valueOf(idEdit)));
        datas.put("price", RequestBody.create(MediaType.parse("text/plain"), etPrice.getText().toString()));
        datas.put("stock", RequestBody.create(MediaType.parse("text/plain"), etStock.getText().toString()));
        datas.put("description", RequestBody.create(MediaType.parse("text/plain"), etDescription.getText().toString()));
        datas.put("user_store_item_id", RequestBody.create(MediaType.parse("text/plain"), String.valueOf(data.getId())));

        if (listImage.size() > 0) {
            for (int i = 0; i < listImage.size(); i++) {
                RequestBody requestBodyFileImage = RequestBody.create(MediaType.parse("multipart/form-data"), listImage.get(i));

                fileImagePart.add(MultipartBody.Part.createFormData("image"+"["+i+"]", listImage.get(i).getName(), requestBodyFileImage));
            }
            postEdit(datas, fileImagePart);
        }else {
            postEdit(datas, fileImagePart);
        }
    }

    private void postEdit(Map<String, RequestBody> datas, List<MultipartBody.Part> fileImagePart){
        if (!progressDialogFragment.isAdded()) {
            progressDialogFragment.show(getSupportFragmentManager(), "loading");
        }
        ConnectionApi.apiService(context).editItemShop(datas, fileImagePart).enqueue(new Callback<ResponseAddItem>() {
            @Override
            public void onResponse(Call<ResponseAddItem> call, Response<ResponseAddItem> response) {
                progressDialogFragment.dismiss();
                if (response.body() != null) {
                    if (response.isSuccessful() && response.body().getSuccess()) {
                        setResult(RESULT_OK);
                        onBackPressed();
                    }
                }
            }
            @Override
            public void onFailure(Call<ResponseAddItem> call, Throwable t) {
                progressDialogFragment.dismiss();
                if (t.getMessage() != null) {
                    Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private boolean isFormValid() {
        if (Validator.isFieldEmpty(etTitle)) {
            Toast.makeText(context, "Title is required", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (Validator.isFieldEmpty(etCategory)) {
            Toast.makeText(context, "Category is required", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (Validator.isFieldEmpty(etPrice)) {
            Toast.makeText(context, "Price is not valid", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (Validator.isFieldEmpty(etStock)) {
            Toast.makeText(context, "Stock is required", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (Validator.isFieldEmpty(etDescription)) {
            Toast.makeText(context, "Description is required", Toast.LENGTH_SHORT).show();
            return false;
        }


        return true;
    }

    private void setBtnSave(){
        if (!isFormValid()) {
            return;
        }

        ResponseMyProfile.Data user = session.getProfile();

        List<MultipartBody.Part> fileImagePart = new ArrayList();

//        if (byteArrayStream != null && byteArrayStream.size() > 0) {
//            File file = RealPath.with(context).getTempFileImage(byteArrayStream, "image");
//            RequestBody requestBodyFileImage = RequestBody.create(MediaType.parse("image/jpeg"), file);
//            fileImagePart = MultipartBody.Part.createFormData("photo", file.getName(), requestBodyFileImage);
//        }

        if (listImage.size() > 0) {
            for (int i = 0; i < listImage.size(); i++) {
                RequestBody requestBodyFileImage = RequestBody.create(MediaType.parse("multipart/form-data"), listImage.get(i));
//            fileImagePart = MultipartBody.Part.createFormData("photo", file.getName(), requestBodyFileImage);

                fileImagePart.add(MultipartBody.Part.createFormData("image"+"["+i+"]", listImage.get(i).getName(), requestBodyFileImage));}

        }

        Map<String, RequestBody> data = new HashMap<>();
        data.put("title", RequestBody.create(MediaType.parse("text/plain"), etTitle.getText().toString()));
        data.put("category_id", RequestBody.create(MediaType.parse("text/plain"), String.valueOf(id)));
        data.put("price", RequestBody.create(MediaType.parse("text/plain"), etPrice.getText().toString()));
        data.put("stock", RequestBody.create(MediaType.parse("text/plain"), etStock.getText().toString()));
        data.put("description", RequestBody.create(MediaType.parse("text/plain"), etDescription.getText().toString()));
        data.put("user_store_id", RequestBody.create(MediaType.parse("text/plain"), String.valueOf(user.getStore().getUserId())));

        if (!progressDialogFragment.isAdded()) {
            progressDialogFragment.show(getSupportFragmentManager(), "loading");
        }

        ConnectionApi.apiService(context).addItemShop(data, fileImagePart).enqueue(new Callback<ResponseAddItem>() {
            @Override
            public void onResponse(Call<ResponseAddItem> call, Response<ResponseAddItem> response) {
                progressDialogFragment.dismiss();
                if (response.body() != null) {
                    if (response.isSuccessful() && response.body().getSuccess()) {
//                        setProfile(response.body().getData());
                        setResult(RESULT_OK);
                        onBackPressed();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseAddItem> call, Throwable t) {
                progressDialogFragment.dismiss();
                if (t.getMessage() != null) {
                    Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        bitmap = null;
        Bitmap tempBitmap;
        imageUri = data.getData();

        if (imageUri != null){
            File file = new File(imageUri.getPath());

            imageUrl.add(imageUri);
            listImage.add(file);
            checkAdapter();
        }


//        if (bottomSheetPickImage.isVisible()) {
//            bottomSheetPickImage.dismiss();
//        }
//
//        if (resultCode == RESULT_OK) {
//
//            if (requestCode == BottomSheetImagePicker.REQ_CAMERA) {
//                if (bottomSheetPickImage.getTag().equals("get-imagePost")) {
//                    byteArrayStream = null;
//                    bitmap = null;
//                    Bitmap tempBitmap;
//                    Uri uri = imageUri;
//
//                    tempBitmap = RealPath.with(context).adjustBitmap(RealPath.with(context).resizeBitmap(uri), uri);
//                    bitmap = tempBitmap;
//
//                    if (!progressDialogFragment.isAdded()) {
//                        progressDialogFragment.show(getSupportFragmentManager(), "loading");
//                    }
//
//                    new CompressImage(bitmap, new CompressImage.OnImageCompressed() {
//                        @Override
//                        public void onCompressed(ByteArrayOutputStream byteArrayOutputStream, Bitmap bitmap) {
//                            File file = RealPath.with(context).getTempFileImage(byteArrayStream, "image");
//
//                            byteArrayStream = byteArrayOutputStream;
//                            ChooseImage.add(Uri.fromFile(file));
//                            listImage.add(file);
//                            Log.e("TAG", "onCompressed: " + file );
//                            checkAdapter();
//                            progressDialogFragment.dismiss();
//                        }
//                    }).execute();
//                }
////                }
//            } else if (requestCode == BottomSheetImagePicker.REQ_GALLERY) {
//                if (bottomSheetPickImage.getTag().equals("get-imagePost")) {
//                    byteArrayStream = null;
//                    bitmap = null;
//
//                    Uri selectedImageUri = data.getData();
//                    String picturePath = getPath(this.getApplicationContext(), selectedImageUri);
//                    Log.e("Picture Path", picturePath);
//
//                    try {
//                        bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), selectedImageUri);
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//
//                    if (!progressDialogFragment.isAdded()) {
//                        progressDialogFragment.show(getSupportFragmentManager(), "loading");
//                    }
//                    new CompressImage(bitmap, new CompressImage.OnImageCompressed() {
//                        @Override
//                        public void onCompressed(ByteArrayOutputStream byteArrayOutputStream, Bitmap bitmap) {
//                            byteArrayStream = byteArrayOutputStream;
//                            File file = RealPath.with(context).getTempFileImage(byteArrayStream, "image");
//                            ChooseImage.add(Uri.fromFile(file));
//                            listImage.add(file);
//                            Log.e("TAG", "onCompressed: " + new File(String.valueOf(Uri.parse(picturePath).getPath())));
//                            checkAdapter();
//                            progressDialogFragment.dismiss();
//                        }
//                    }).execute();
//                }
//            }
//        }
    }

    public static String getPath( Context context, Uri uri ) {
        String result = null;
        String[] proj = { MediaStore.Images.Media.DATA };
        Cursor cursor = context.getContentResolver( ).query( uri, proj, null, null, null );
        if(cursor != null){
            if ( cursor.moveToFirst( ) ) {
                int column_index = cursor.getColumnIndexOrThrow( proj[0] );
                result = cursor.getString( column_index );
            }
            cursor.close( );
        }
        if(result == null) {
            result = "Not found";
        }
        return result;
    }

    private void checkAdapter(){
        adapter.setItems(imageUrl);
        Log.e("TAG uri", "checkAdapter: " + imageUrl.size());
        Log.e("TAG file", "checkAdapter: " + listImage.size());
    }

    private void refreshData(){
        ConnectionApi.apiService(context).getShopDetailItem(data.getId()).enqueue(new Callback<ResponseDetailShopItem>() {
            @Override
            public void onResponse(Call<ResponseDetailShopItem> call, Response<ResponseDetailShopItem> response) {
                if (response.body() != null) {
                    if (response.isSuccessful() && response.body().getSuccess()) {
                        data = response.body().getData();

                        item = data.getPhoto();
                        adapterImage = new ItemImageAdapter();
                        rvImage.setHasFixedSize(true);
                        adapterImage.setItems(item);
                        Log.e("TAG", "checkCondition: " + item);
                        rvImageUrl.setAdapter(adapterImage);

                        adapterImage.notifyDataSetChanged();
                    }

                }
            }

            @Override
            public void onFailure(Call<ResponseDetailShopItem> call, Throwable t) {
                if (t.getMessage() != null) {
                    Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    @Subscribe
    public void onSelectCategory(CategorySelected categorySelected){
        etCategory.setText(categorySelected.category);
        id = categorySelected.id;
        bottomSheetCategoryShop.dismiss();
    }

    @Subscribe
    public void onDeleteImage(DeleteImage deleteImage){
        imageUrl.remove(deleteImage.position);
        listImage.remove(deleteImage.position);
        checkAdapter();
        adapter.notifyItemRemoved(deleteImage.position);
    }

    @Subscribe
    public void onDeleteItemImage(DeleteItemImage deleteItemImage){
        ConnectionApi.apiService(context).removeItemImage(Integer.parseInt(String.valueOf(deleteItemImage.id))).enqueue(new Callback<ResponseUpdateProfile>() {
            @Override
            public void onResponse(Call<ResponseUpdateProfile> call, Response<ResponseUpdateProfile> response) {
                if (response.body() != null) {
                    if (response.isSuccessful() && response.body().getSuccess()) {
                        Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseUpdateProfile> call, Throwable t) {

            }
        });

        refreshData();
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

}