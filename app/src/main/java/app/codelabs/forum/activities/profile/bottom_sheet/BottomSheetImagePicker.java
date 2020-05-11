package app.codelabs.forum.activities.profile.bottom_sheet;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;

import android.os.Environment;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.io.File;

import app.codelabs.forum.R;
import app.codelabs.forum.activities.home.HomeActivity;
import app.codelabs.forum.activities.profile.EditProfileActivity;

public class BottomSheetImagePicker extends BottomSheetDialogFragment {
    public final static int REQ_GALLERY = 1000;
    public final static int REQ_CAMERA = 1001;
    private TextView txtCamera, txtGallery;
    private Context context;

    public BottomSheetImagePicker() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.bottom_sheet_image_picker, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        context = getContext();
        setView(view);
        setEvent();
    }

    private void setView(View view) {
        txtCamera = view.findViewById(R.id.tv_camera);
        txtGallery = view.findViewById(R.id.tv_gallery);
    }

    private void setEvent() {
        txtGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadImageByGallery();
            }
        });

        txtCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadImageByCamera();
            }
        });
    }

    private void loadImageByCamera() {
        if (ContextCompat.checkSelfPermission(context, Manifest.permission.CAMERA) ==
                PackageManager.PERMISSION_DENIED ||
                ContextCompat.checkSelfPermission(context
                        , Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED ||
                ContextCompat.checkSelfPermission(context
                        , Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {

            requestPermissions(
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,
                            Manifest.permission.CAMERA,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    2000);
        } else {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            ((EditProfileActivity) getActivity()).imageUri = FileProvider.getUriForFile(context,
                    context.
                            getApplicationContext().getPackageName() + ".app.codelabs.forum.provider",
                    new File(Environment.getExternalStorageDirectory(), "fname_" +
                            System.currentTimeMillis() + ".jpg"));
            intent.putExtra(android.provider.MediaStore.EXTRA_OUTPUT, ((EditProfileActivity) getActivity()).imageUri);
            getActivity().startActivityForResult(intent, REQ_CAMERA);
        }
    }


    private void loadImageByGallery() {
        if (ContextCompat.checkSelfPermission(context
                , Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED ||
                ContextCompat.checkSelfPermission(context
                        , Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
            requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE}, 2000);
        } else {
            Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            intent.setType("image/*");
            if (intent.resolveActivity(getActivity().getPackageManager()) != null) {
                getActivity().startActivityForResult(intent, REQ_GALLERY);
            }
        }
    }

}
