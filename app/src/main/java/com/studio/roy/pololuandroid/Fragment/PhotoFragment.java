package com.studio.roy.pololuandroid.Fragment;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.studio.roy.pololuandroid.R;

import java.io.File;
import java.io.IOException;


public class PhotoFragment extends Fragment {


    public PhotoFragment() {
        // Required empty public constructor
    }

    private StorageReference mStorageRef;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    ImageView myImage;
    File localFile;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_photo, container, false);
        mStorageRef = FirebaseStorage.getInstance().getReference();

        StorageReference riversRef = mStorageRef.child("images/20161125145536.jpg");
         myImage = (ImageView) view.findViewById(R.id.imageView3);
         localFile = null;
        try {
            localFile = File.createTempFile("images", "jpg");
            riversRef.getFile(localFile)
                    .addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                            // Successfully downloaded data to local file
                            // ...
                            Toast.makeText(getActivity(), "Good Download :)", Toast.LENGTH_SHORT).show();
                            if(localFile.exists()){

                                Bitmap myBitmap = BitmapFactory.decodeFile(localFile.getAbsolutePath());



                                myImage.setImageBitmap(myBitmap);

                            }

                        }
                    }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception exception) {
                    // Handle failed download
                    // ...
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }


        return view;
    }





    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
