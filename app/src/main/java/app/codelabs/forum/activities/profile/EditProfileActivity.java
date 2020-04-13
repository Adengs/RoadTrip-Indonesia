package app.codelabs.forum.activities.profile;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.InputType;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

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
import app.codelabs.forum.activities.login.ProgresDialogFragment;
import app.codelabs.forum.activities.profile.bottom_sheet.BottomSheetImagePicker;
import app.codelabs.forum.helpers.CompressImage;
import app.codelabs.forum.helpers.ConnectionApi;
import app.codelabs.forum.helpers.RealPath;
import app.codelabs.forum.helpers.Session;
import app.codelabs.forum.helpers.Validator;
import app.codelabs.forum.models.ResponsLogin;
import app.codelabs.forum.models.ResponseUpdateProfile;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditProfileActivity extends AppCompatActivity {
    public Uri imageUri = null;
    private Toolbar toolbar;
    private ImageView ivPhoto;
    private EditText etName, etEmail, etDob, etCity;
    private Button btnSaveEdit;
    private Context context;
    private BottomSheetImagePicker bottomSheetPickImage = new BottomSheetImagePicker();
    private Bitmap bitmap;
    private ByteArrayOutputStream byteArrayStream;
    private ProgresDialogFragment progresDialogFragment = new ProgresDialogFragment();
    private Session session;
    private Calendar calendar = Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        context = getApplicationContext();
        session = new Session(context);
        setView();
        setToolbar();
        setEvent();
        setData();
    }

    private void setData() {
        ResponsLogin.Data user = Session.init(context).getUser();
        etName.setText(user.getName());
        etEmail.setText(user.getEmail());
        etDob.setText(user.getDate_birth());
        etCity.setText(user.getCity());
        Picasso.with(context).load(user.getPhoto())
                .placeholder(R.drawable.default_photo)
                .fit().centerCrop().into(ivPhoto);
    }

    private void setEvent() {
        ivPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomSheetPickImage.show(getSupportFragmentManager(), "get-image");
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
                        SimpleDateFormat sdf = new SimpleDateFormat(patternDate);
                        etDob.setText(sdf.format(calendar.getTime()));
                    }
                },
                        calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.getDatePicker().setMaxDate(new Date().getTime());
                datePickerDialog.show();
            }
        });
    }

    private void saveProfile() {
        if (!isFormValid()) {
            return;
        }

        MultipartBody.Part fileImagePart = null;
        if (byteArrayStream != null && byteArrayStream.size() > 0) {
            File file = RealPath.with(context).getTempFileImage(byteArrayStream, "image");
            RequestBody requestBodyFileImage = RequestBody.create(MediaType.parse("image/jpeg"), file);
            fileImagePart = MultipartBody.Part.createFormData("photo", file.getName(), requestBodyFileImage);
        }

        Map<String, RequestBody> data = new HashMap<>();
        data.put("name", RequestBody.create(MediaType.parse("text/plain"), etName.getText().toString()));
        data.put("email", RequestBody.create(MediaType.parse("text/plain"), etEmail.getText().toString()));
        data.put("city", RequestBody.create(MediaType.parse("text/plain"), etCity.getText().toString()));
        data.put("date_birth", RequestBody.create(MediaType.parse("text/plain"), etDob.getText().toString()));

        if (!progresDialogFragment.isAdded()) {
            progresDialogFragment.show(getSupportFragmentManager(), "loading");
        }

        ConnectionApi.apiService(context).updateProfile(data, fileImagePart).enqueue(new Callback<ResponseUpdateProfile>() {
            @Override
            public void onResponse(Call<ResponseUpdateProfile> call, Response<ResponseUpdateProfile> response) {
                progresDialogFragment.dismiss();
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
                progresDialogFragment.dismiss();
                if (t.getMessage() != null) {
                    Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void setProfile(ResponseUpdateProfile.User data) {
        ResponsLogin.Data user = session.getUser();
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
                byteArrayStream = null;
                bitmap = null;
                Bitmap tempBitmap;
                Uri uri = imageUri;
                tempBitmap = RealPath.with(context).adjustBitmap(RealPath.with(context).resizeBitmap(uri), uri);
                bitmap = tempBitmap;

                if (!progresDialogFragment.isAdded()) {
                    progresDialogFragment.show(getSupportFragmentManager(), "loading");
                }
                new CompressImage(bitmap, new CompressImage.OnImageCompressed() {
                    @Override
                    public void onCompressed(ByteArrayOutputStream byteArrayOutputStream, Bitmap bitmap) {
                        byteArrayStream = byteArrayOutputStream;
                        ivPhoto.setImageBitmap(bitmap);
                        progresDialogFragment.dismiss();
                    }
                }).execute();
            } else if (requestCode == BottomSheetImagePicker.REQ_GALLERY) {
                byteArrayStream = null;
                bitmap = null;
                Uri uri = data.getData();
                try {
                    bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                if (!progresDialogFragment.isAdded()) {
                    progresDialogFragment.show(getSupportFragmentManager(), "loading");
                }
                new CompressImage(bitmap, new CompressImage.OnImageCompressed() {
                    @Override
                    public void onCompressed(ByteArrayOutputStream byteArrayOutputStream, Bitmap bitmap) {
                        byteArrayStream = byteArrayOutputStream;
                        ivPhoto.setImageBitmap(bitmap);
                        progresDialogFragment.dismiss();
                    }
                }).execute();
            }
        }
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
