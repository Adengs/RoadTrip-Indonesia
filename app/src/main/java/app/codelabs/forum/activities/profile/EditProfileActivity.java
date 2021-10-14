package app.codelabs.forum.activities.profile;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.InputType;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import app.codelabs.forum.R;
import app.codelabs.forum.activities.custom.ProgressDialogFragment;
import app.codelabs.forum.activities.profile.bottom_sheet.BottomSheetImagePicker;
import app.codelabs.forum.databinding.ActivityEditProfileBinding;
import app.codelabs.forum.helpers.CompressImage;
import app.codelabs.forum.helpers.ConnectionApi;
import app.codelabs.forum.helpers.RealPath;
import app.codelabs.forum.helpers.Session;
import app.codelabs.forum.helpers.Validator;
import app.codelabs.forum.models.ResponseLogin;
import app.codelabs.forum.models.ResponseMyProfile;
import app.codelabs.forum.models.ResponseUpdateProfile;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditProfileActivity extends AppCompatActivity {
    public Uri imageUri = null, imageKtp = null, imageSIM = null;
    private Toolbar toolbar;
    private ImageView ivPhoto;
    private EditText etName, etEmail, etDob, etCity;
    private Button btnSaveEdit;
    private Context context;
    private BottomSheetImagePicker bottomSheetPickImage = new BottomSheetImagePicker();
    private Bitmap bitmap, bitmapKtp, bitmapSim;
    private ByteArrayOutputStream byteArrayStream, byteArrayStreamKTP, byteArrayStreamSIM;
    private ProgressDialogFragment progressDialogFragment = new ProgressDialogFragment();
    private Session session;
    private Calendar calendar = Calendar.getInstance();
    private ActivityEditProfileBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityEditProfileBinding.inflate(getLayoutInflater());
        //setContentView(R.layout.activity_edit_profile);
        setContentView(binding.getRoot());
        context = getApplicationContext();
        session = new Session(context);
        setView();
        setToolbar();
        setEvent();
        setData();
    }

    private void setData() {
        ResponseLogin.Data user = Session.init(context).getUser();
       /* etName.setText(user.getName());
        etEmail.setText(user.getEmail());
        etDob.setText(user.getDate_birth());
        etCity.setText(user.getCity());
        Picasso.with(context).load(user.getPhoto())
                .placeholder(R.drawable.default_photo)
                .fit().centerCrop().into(ivPhoto);*/
        ResponseMyProfile.DataEntity dataProfile = new Gson().fromJson(getIntent().getExtras().getString("data"), ResponseMyProfile.DataEntity.class);
        etName.setText(dataProfile.getName());
        etEmail.setText(dataProfile.getEmail());
        etDob.setText(dataProfile.getDate_birth());
        etCity.setText(dataProfile.getCity());
        Picasso.with(context).load(dataProfile.getPhoto())
                .placeholder(R.drawable.default_photo)
                .fit().centerCrop().into(ivPhoto);
        binding.etNra.setText(dataProfile.getNra());
        binding.etNamePanggilan.setText(dataProfile.getNickname());
        /*binding.etJenisKelamin.setText(dataProfile.getGender());*/
        if (!dataProfile.getGender().isEmpty()) {
            if ("Laki-Laki".equals(dataProfile.getGender())) {
                binding.radioLakiLaki.setChecked(true);
            } else if ("Perempuan".equals(dataProfile.getGender())) {
                binding.radioPerempuan.setChecked(true);
            } else {
                binding.radioPerempuan.setChecked(false);
                binding.radioLakiLaki.setChecked(false);
            }

        }
        binding.etNomorWa.setText(dataProfile.getPhone());
        binding.etAlamat.setText(dataProfile.getAlamat());
        binding.etRT.setText(dataProfile.getRt());
        binding.etRW.setText(dataProfile.getRw());
        binding.etDesa.setText(dataProfile.getDesaKelurahan());
        binding.etKecamatan.setText(dataProfile.getKecamatan());
        binding.etCity.setText(dataProfile.getKabupatenKota());
        binding.etProv.setText(dataProfile.getProvinsi());
        binding.etChapter.setText(dataProfile.getChapter());
        if (!dataProfile.getFotoKtp().isEmpty()) {
            Picasso.with(context).load(dataProfile.getFotoKtp())
                    .fit().centerCrop().into(binding.imageKtp);
            binding.cardImageKtp.setVisibility(View.VISIBLE);
        }
        if (!dataProfile.getFotoSim().isEmpty()) {
            Picasso.with(context).load(dataProfile.getFotoSim())
                    .fit().centerCrop().into(binding.imageSim);
            binding.cardImageSim.setVisibility(View.VISIBLE);
        }


    }

    private void setEvent() {
        ivPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomSheetPickImage.show(getSupportFragmentManager(), "get-image");
            }
        });

        binding.viewKtp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bottomSheetPickImage.show(getSupportFragmentManager(), "get-image-ktp");
            }
        });
        binding.viewSim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bottomSheetPickImage.show(getSupportFragmentManager(), "get-image-sim");
            }
        });

        btnSaveEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveProfile();
            }
        });

        etDob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(EditProfileActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        calendar.set(Calendar.YEAR, year);
                        calendar.set(Calendar.MONTH, month);
                        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                        String patternDate = "yyyy-MM-dd";
                        SimpleDateFormat format = new SimpleDateFormat(patternDate);
                        etDob.setText(format.format(calendar.getTime()));
                    }
                },
                        calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.getDatePicker().setMaxDate(new Date().getTime());
                datePickerDialog.show();
            }
        });
        binding.radioLakiLaki.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                binding.radioLakiLaki.setChecked(b);
                binding.radioPerempuan.setChecked(!b);
            }
        });
        binding.radioPerempuan.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                binding.radioLakiLaki.setChecked(!b);
                binding.radioPerempuan.setChecked(b);
            }
        });
    }

    private void saveProfile() {
        if (!isFormValid()) {
            return;
        }
        String gender = "";
        if (binding.radioLakiLaki.isChecked()) {
            gender = "Laki-Laki";
        } else if (binding.radioPerempuan.isChecked()) {
            gender = "Perempuan";
        } else {
            gender = "";
        }

        MultipartBody.Part fileImagePart = null;

        if (byteArrayStream != null && byteArrayStream.size() > 0) {
            File file = RealPath.with(context).getTempFileImage(byteArrayStream, "image");
            RequestBody requestBodyFileImage = RequestBody.create(MediaType.parse("image/jpeg"), file);
            fileImagePart = MultipartBody.Part.createFormData("photo", file.getName(), requestBodyFileImage);
        }
      /*
        MultipartBody.Part fileImagePartKtp = null;
        try {
            if (byteArrayStreamKTP != null && byteArrayStreamKTP.size() > 0 ){
                File file = RealPath.with(context).getTempFileImage(byteArrayStreamKTP, "image");
                RequestBody requestBodyFileImageKtp = RequestBody.create(MediaType.parse("image/jpeg"), file);
                fileImagePartKtp = MultipartBody.Part.createFormData("foto_ktp", file.getName()+"ktp", requestBodyFileImageKtp);
            }
        } catch (Exception e) {
            Log.e("profile", "Multipart image ktp as exception occurs" );
        }
        MultipartBody.Part fileImagePartSim = null;
        try {
            if (byteArrayStreamSIM != null && byteArrayStreamSIM.size() > 0 ){
                File file = RealPath.with(context).getTempFileImage(byteArrayStreamSIM, "image");
                RequestBody requestBodyFileImageSim = RequestBody.create(MediaType.parse("image/jpeg"), file);
                fileImagePartSim = MultipartBody.Part.createFormData("foto_sim", file.getName()+"sim", requestBodyFileImageSim);
            }
        } catch (Exception e) {
            Log.e("profile", "Multipart image sim as exception occurs" );
        }*/

        Map<String, RequestBody> data = new HashMap<>();
        data.put("name", RequestBody.create(MediaType.parse("text/plain"), etName.getText().toString()));
        data.put("email", RequestBody.create(MediaType.parse("text/plain"), etEmail.getText().toString()));
        data.put("city", RequestBody.create(MediaType.parse("text/plain"), etCity.getText().toString()));
        data.put("date_birth", RequestBody.create(MediaType.parse("text/plain"), etDob.getText().toString()));
        data.put("nra", RequestBody.create(MediaType.parse("text/plain"), binding.etNra.getText().toString()));
        data.put("nickname", RequestBody.create(MediaType.parse("text/plain"), binding.etNamePanggilan.getText().toString()));
        data.put("gender", RequestBody.create(MediaType.parse("text/plain"), gender));
        data.put("phone", RequestBody.create(MediaType.parse("text/plain"), binding.etNomorWa.getText().toString()));
        data.put("chapter", RequestBody.create(MediaType.parse("text/plain"), binding.etChapter.getText().toString()));
        data.put("alamat", RequestBody.create(MediaType.parse("text/plain"), binding.etAlamat.getText().toString()));
        data.put("rt", RequestBody.create(MediaType.parse("text/plain"), binding.etRT.getText().toString()));
        data.put("rw", RequestBody.create(MediaType.parse("text/plain"), binding.etRW.getText().toString()));
        data.put("desa_kelurahan", RequestBody.create(MediaType.parse("text/plain"), binding.etDesa.getText().toString()));
        data.put("kecamatan", RequestBody.create(MediaType.parse("text/plain"), binding.etKecamatan.getText().toString()));
        data.put("kabupaten_kota", RequestBody.create(MediaType.parse("text/plain"), binding.etCity.getText().toString()));
        data.put("provinsi", RequestBody.create(MediaType.parse("text/plain"), binding.etProv.getText().toString()));
        data.put("keterangan", RequestBody.create(MediaType.parse("text/plain"), binding.etKeterangan.getText().toString()));


        if (!progressDialogFragment.isAdded()) {
            progressDialogFragment.show(getSupportFragmentManager(), "loading");
        }

        ConnectionApi.apiService(context).updateProfile(data, fileImagePart).enqueue(new Callback<ResponseUpdateProfile>() {
            @Override
            public void onResponse(Call<ResponseUpdateProfile> call, Response<ResponseUpdateProfile> response) {
                progressDialogFragment.dismiss();
                if (response.body() != null) {
                    if (response.isSuccessful() && response.body().getSuccess()) {
                        setProfile(response.body().getData());
                        setResult(RESULT_OK);
                        onBackPressed();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseUpdateProfile> call, Throwable t) {
                progressDialogFragment.dismiss();
                if (t.getMessage() != null) {
                    Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void setProfile(ResponseUpdateProfile.User data) {
        ResponseLogin.Data user = session.getUser();
        user.setName(data.getName());
        user.setUsername(data.getUsername());
        user.setCity(data.getCity());
        user.setDate_birth(data.getDate_birth());
        user.setEmail(data.getEmail());
        user.setPhoto(data.getPhoto());

        session.setUser(user);
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
        setTitle("Edit Profile");
    }

    private void setView() {
        toolbar = findViewById(R.id.toolbar);
        btnSaveEdit = findViewById(R.id.btn_save);
        ivPhoto = findViewById(R.id.iv_photo);
        etName = findViewById(R.id.et_name);
        etEmail = findViewById(R.id.et_email);
        etDob = findViewById(R.id.et_dob);
        etCity = findViewById(R.id.et_city);

        etDob.setInputType(InputType.TYPE_NULL);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (bottomSheetPickImage.isVisible()) {
            bottomSheetPickImage.dismiss();
        }

        if (resultCode == RESULT_OK) {
            if (requestCode == BottomSheetImagePicker.REQ_CAMERA) {
                if (bottomSheetPickImage.getTag().equals("get-image-ktp")) {
                    byteArrayStreamKTP = null;
                    bitmapKtp = null;
                    Bitmap tempBitmap;
                    Uri uri = imageKtp;
                    tempBitmap = RealPath.with(context).adjustBitmap(RealPath.with(context).resizeBitmap(uri), uri);
                    bitmapKtp = tempBitmap;

                    if (!progressDialogFragment.isAdded()) {
                        progressDialogFragment.show(getSupportFragmentManager(), "loading");
                    }
                    new CompressImage(bitmapKtp, new CompressImage.OnImageCompressed() {
                        @Override
                        public void onCompressed(ByteArrayOutputStream byteArrayOutputStream, Bitmap bitmap) {
                            byteArrayStreamKTP = byteArrayOutputStream;
                            binding.imageKtp.setImageBitmap(bitmap);
                            binding.imageKtp.setScaleType(ImageView.ScaleType.CENTER_CROP);
                            progressDialogFragment.dismiss();
                            uploadKtp(byteArrayOutputStream);
                        }
                    }).execute();
                } else if (bottomSheetPickImage.getTag().equals("get-image-sim")) {
                    byteArrayStreamSIM = null;
                    bitmapSim = null;
                    Bitmap tempBitmap;
                    Uri uri = imageSIM;
                    tempBitmap = RealPath.with(context).adjustBitmap(RealPath.with(context).resizeBitmap(uri), uri);
                    bitmapSim = tempBitmap;

                    if (!progressDialogFragment.isAdded()) {
                        progressDialogFragment.show(getSupportFragmentManager(), "loading");
                    }
                    new CompressImage(bitmapSim, new CompressImage.OnImageCompressed() {
                        @Override
                        public void onCompressed(ByteArrayOutputStream byteArrayOutputStream, Bitmap bitmap) {
                            byteArrayStreamSIM = byteArrayOutputStream;
                            binding.imageSim.setImageBitmap(bitmap);
                            binding.imageSim.setScaleType(ImageView.ScaleType.CENTER_CROP);
                            progressDialogFragment.dismiss();
                            uploadSim(byteArrayOutputStream);
                        }
                    }).execute();
                } else {
                    byteArrayStream = null;
                    bitmap = null;
                    Bitmap tempBitmap;
                    Uri uri = imageUri;
                    tempBitmap = RealPath.with(context).adjustBitmap(RealPath.with(context).resizeBitmap(uri), uri);
                    bitmap = tempBitmap;

                    if (!progressDialogFragment.isAdded()) {
                        progressDialogFragment.show(getSupportFragmentManager(), "loading");
                    }
                    new CompressImage(bitmap, new CompressImage.OnImageCompressed() {
                        @Override
                        public void onCompressed(ByteArrayOutputStream byteArrayOutputStream, Bitmap bitmap) {
                            byteArrayStream = byteArrayOutputStream;
                            ivPhoto.setImageBitmap(bitmap);
                            progressDialogFragment.dismiss();
                        }
                    }).execute();
                }
            } else if (requestCode == BottomSheetImagePicker.REQ_GALLERY) {
                if (bottomSheetPickImage.getTag().equals("get-image-ktp")) {
                    byteArrayStreamKTP = null;
                    bitmapKtp = null;
                    Uri uri = data.getData();
                    try {
                        bitmapKtp = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    if (!progressDialogFragment.isAdded()) {
                        progressDialogFragment.show(getSupportFragmentManager(), "loading");
                    }
                    new CompressImage(bitmapKtp, new CompressImage.OnImageCompressed() {
                        @Override
                        public void onCompressed(ByteArrayOutputStream byteArrayOutputStream, Bitmap bitmap) {
                            byteArrayStreamKTP = byteArrayOutputStream;
                            binding.imageKtp.setImageBitmap(bitmap);
                            binding.imageKtp.setScaleType(ImageView.ScaleType.CENTER_CROP);
                            progressDialogFragment.dismiss();
                            uploadKtp(byteArrayOutputStream);
                        }
                    }).execute();
                } else if (bottomSheetPickImage.getTag().equals("get-image-sim")) {
                    byteArrayStreamSIM = null;
                    bitmapSim = null;
                    Uri uri = data.getData();
                    try {
                        bitmapSim = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    if (!progressDialogFragment.isAdded()) {
                        progressDialogFragment.show(getSupportFragmentManager(), "loading");
                    }
                    new CompressImage(bitmapSim, new CompressImage.OnImageCompressed() {
                        @Override
                        public void onCompressed(ByteArrayOutputStream byteArrayOutputStream, Bitmap bitmap) {
                            byteArrayStreamSIM = byteArrayOutputStream;
                            binding.imageSim.setImageBitmap(bitmap);
                            binding.imageSim.setScaleType(ImageView.ScaleType.CENTER_CROP);
                            progressDialogFragment.dismiss();
                            uploadSim(byteArrayOutputStream);
                        }
                    }).execute();
                } else {
                    byteArrayStream = null;
                    bitmap = null;
                    Uri uri = data.getData();
                    try {
                        bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    if (!progressDialogFragment.isAdded()) {
                        progressDialogFragment.show(getSupportFragmentManager(), "loading");
                    }
                    new CompressImage(bitmap, new CompressImage.OnImageCompressed() {
                        @Override
                        public void onCompressed(ByteArrayOutputStream byteArrayOutputStream, Bitmap bitmap) {
                            byteArrayStream = byteArrayOutputStream;
                            ivPhoto.setImageBitmap(bitmap);
                            progressDialogFragment.dismiss();
                        }
                    }).execute();
                }
            }
        }
    }

    private void uploadKtp(ByteArrayOutputStream byteArrayOutputStream) {
        MultipartBody.Part fileImagePartKtp = null;
        try {
            if (byteArrayOutputStream != null && byteArrayOutputStream.size() > 0) {
                File file = RealPath.with(context).getTempFileImage(byteArrayOutputStream, "image");
                RequestBody requestBodyFileImageKtp = RequestBody.create(MediaType.parse("image/jpeg"), file);
                fileImagePartKtp = MultipartBody.Part.createFormData("foto_ktp", file.getName(), requestBodyFileImageKtp);
            }
        } catch (Exception e) {
            Log.e("profile", "Multipart image ktp as exception occurs");
        }
        ConnectionApi.apiService(context).updateProfileKtp(fileImagePartKtp).enqueue(new Callback<ResponseUpdateProfile>() {
            @Override
            public void onResponse(Call<ResponseUpdateProfile> call, Response<ResponseUpdateProfile> response) {
               // Toast.makeText(context, response.body().getMessage(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<ResponseUpdateProfile> call, Throwable t) {
                Log.e("TAG", "onFailure: " + t.getMessage());
            }
        });
    }

    private void uploadSim(ByteArrayOutputStream byteArrayOutputStream) {
        MultipartBody.Part fileImagePartSim = null;
        try {
            if (byteArrayOutputStream != null && byteArrayOutputStream.size() > 0) {
                File file = RealPath.with(context).getTempFileImage(byteArrayOutputStream, "image");
                RequestBody requestBodyFileImageSim = RequestBody.create(MediaType.parse("image/jpeg"), file);
                fileImagePartSim = MultipartBody.Part.createFormData("foto_sim", file.getName(), requestBodyFileImageSim);
            }
        } catch (Exception e) {
            Log.e("profile", "Multipart image sim as exception occurs");
        }
        ConnectionApi.apiService(context).updateProfileSim(fileImagePartSim).enqueue(new Callback<ResponseUpdateProfile>() {
            @Override
            public void onResponse(Call<ResponseUpdateProfile> call, Response<ResponseUpdateProfile> response) {
              //  Toast.makeText(context, response.body().getMessage(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<ResponseUpdateProfile> call, Throwable t) {
                Log.e("TAG", "onFailure: " + t.getMessage());
            }
        });
    }

    private boolean isFormValid() {
        if (Validator.isFieldEmpty(etName)) {
            Toast.makeText(context, "Name is required", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (Validator.isFieldEmpty(etEmail)) {
            Toast.makeText(context, "Email is required", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (!Validator.isEmailValid(etEmail.getText().toString())) {
            Toast.makeText(context, "Email is not valid", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (Validator.isFieldEmpty(etDob)) {
            Toast.makeText(context, "Date of Birth is required", Toast.LENGTH_SHORT).show();
            return false;
        }


        return true;
    }
}
