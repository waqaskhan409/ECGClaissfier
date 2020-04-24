package com.mlclassifier.ecgclaissfier.ui.home;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.mlclassifier.ecgclaissfier.MainActivity;
import com.mlclassifier.ecgclaissfier.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    Unbinder unbinder;
    public static final int PICK_IMAGE = 1;
    Bitmap bitmap;
    private int READ_STORAGE_PERMISSION_REQUEST_CODE = 1000;
    private String[] galleryPermissions = {Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE};






    @BindView(R.id.imageClassify)
    ImageView imageClassify;

      @BindView(R.id.fab)
    ImageView fab;

      @BindView(R.id.fabSend)
    ImageView fabSend;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        if(unbinder == null){
            unbinder = ButterKnife.bind(this, root);
        }


        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
            }

        });

        setButtonHide();



        return root;
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE) {
            //TODO: action
            Uri selectedImage = data.getData();
            String[] filePathColumn = { MediaStore.Images.Media.DATA };

            Cursor cursor = getActivity().getContentResolver().query(selectedImage,
                    filePathColumn, null, null, null);
            cursor.moveToFirst();

            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);
            cursor.close();

            bitmap = BitmapFactory.decodeFile(picturePath);
//            imageClassify.setImageURI(selectedImage);
            imageClassify.setImageBitmap(bitmap);
            setButtonHide();
        }

    }

    @OnClick(R.id.fabSend)
    public void sendImage(){
        ((MainActivity)getActivity()).showSuccessSnackBar("To be implemented");
    }

    @OnClick(R.id.fab)
    public void selectImage(){
        checkPermissionForReadExtertalStorage();

    }


    public boolean checkPermissionForReadExtertalStorage() {
        if (!checkIfAlreadyhavePermission()) {
            ActivityCompat.requestPermissions( getActivity(),galleryPermissions,1);
        } else {
            startYourCameraIntent();
        }
        return false;
    }



    private boolean checkIfAlreadyhavePermission() {
        int result = ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE);
        return result == PackageManager.PERMISSION_GRANTED;
    }
//    Handle Permission Dialog "Allow" and "Deny" button action in onRequestPermission():

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 1: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    startYourCameraIntent();
                } else {
                    Toast.makeText(getActivity(), "Please give your permission.", Toast.LENGTH_LONG).show();
                }
                break;
            }
        }
    }


    void startYourCameraIntent(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE);
    }

    void setButtonHide(){
        if(bitmap == null){
            fabSend.setVisibility(View.GONE);
        }else {
            fabSend.setVisibility(View.VISIBLE);
        }
    }

}
