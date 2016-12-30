package com.studio.roy.pololuandroid.Fragment;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;
import com.studio.roy.pololuandroid.Class.ListeTour.PhotoDatabase;
import com.studio.roy.pololuandroid.Class.ListeTour.TourDatabase;
import com.studio.roy.pololuandroid.Class.Notification.DatabaseNotification;
import com.studio.roy.pololuandroid.Class.Notification.Notification;
import com.studio.roy.pololuandroid.Class.Utils.DbBitmapUtility;
import com.studio.roy.pololuandroid.R;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


public class InfoFragment extends Fragment {
    String TAG ="firebase";
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    FirebaseDatabase database;
    PhotoDatabase photoDatabase;
    TourDatabase tourDatabase;
    DatabaseNotification databaseNotification;
    DatabaseReference myRef;
    private ProgressBar mProgressBar;
    private Button mButton;
    Button syncro;
    File localFile;
    private StorageReference mStorageRef;
    Bitmap myBitmap;
    String idkey;
    List<String> nameList;
    List<Integer> idkeyList;
    int listIncrement = 0;
     int wait = 0;
    public InfoFragment() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_info, container, false);
        ActionBar actionBar = ((AppCompatActivity)getActivity()).getSupportActionBar();
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle("Panel");
        syncro = (Button)view.findViewById(R.id.buttonGetValue);
        TextView textViewPhoto = (TextView)view.findViewById(R.id.textViewNbPhoto);
        TextView textViewTour = (TextView)view.findViewById(R.id.textViewNbtour);
        TextView textViewTourdetect = (TextView)view.findViewById(R.id.textViewNbIntru);
        TextView textViewService = (TextView)view.findViewById(R.id.textViewStatus);




        database = FirebaseDatabase.getInstance();
        photoDatabase = new PhotoDatabase(getActivity());
        tourDatabase = new TourDatabase(getActivity());
        databaseNotification = new DatabaseNotification(getActivity());

        textViewPhoto.setText(Integer.toString(photoDatabase.getPhotoCount()));
        textViewTour.setText(Integer.toString(tourDatabase.getTourCount()));
        textViewTourdetect.setText(Integer.toString(tourDatabase.getTourDetectionCount()));



        mStorageRef = FirebaseStorage.getInstance().getReference();
        myRef = database.getReference("Service");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = dataSnapshot.getValue(String.class);
                Log.d(TAG, "Value is: " + value);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });


        syncro.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                BigCalcul calcul=new BigCalcul();
                calcul.execute();

            }

        });
        return view;
    }


    private class BigCalcul extends AsyncTask<Void, Integer, Void>
    {
        int progress;
        AlertDialog.Builder builder;
        AlertDialog dialog;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Toast.makeText(getActivity(), "Début du traitement asynchrone", Toast.LENGTH_LONG).show();
            builder = new AlertDialog.Builder(getActivity());

            builder.setMessage("value:")
                    .setTitle("test");

            dialog = builder.create();
            dialog.show();

        }

        @Override
        protected void onProgressUpdate(Integer... values){
            super.onProgressUpdate(values);


        }

        @Override
        protected Void doInBackground(Void... arg0) {

            progress = 0;
            idkeyList =  new ArrayList<Integer>();
            nameList = new ArrayList<String>();
            listIncrement = 0;

            final TourDatabase tourDatabase = new TourDatabase(getActivity());
            final PhotoDatabase photoDatabase = new PhotoDatabase(getActivity());
            tourDatabase.deleteTable();
            photoDatabase.deleteTable();
            mStorageRef = FirebaseStorage.getInstance().getReference();
            myRef = database.getReference("ListeTour");
            myRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    String value = dataSnapshot.getValue().toString();

                    for (DataSnapshot alert: dataSnapshot.getChildren()) {
                      //  System.out.println("Id : "+alert.getKey());
                     //   System.out.println("Etat: " + alert.child("Etat").getValue());
                        String etat ="";
                        String id = alert.getKey();
                        if(alert.child("Etat").getValue() == null){
                            etat = "1";
                        }
                        else{
                            etat = alert.child("Etat").getValue().toString();
                        }
                        tourDatabase.insertTour(alert.getKey(),alert.getKey(),etat);
                        idkey = alert.getKey();


                        for (final DataSnapshot recipient: alert.child("ListePhoto").getChildren()) {

                            nameList.add(idkey);
                        //    System.out.println("Photo :" + recipient.child("Name").getValue());
                            String name = recipient.child("Name").getValue().toString();
                            StorageReference riversRef = mStorageRef.child("images/"+id+"/"+name+".jpg");

/*

                            riversRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                    // Got the download URL for 'users/me/profile.png'
                                    Log.d("url2:",uri.toString());
                                    photoDatabase.insertPhoto(Integer.toString(idkey), uri.toString(), null);


                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception exception) {
                                    // Handle any errors
                                }
                            });
                            */
                           final long ONE_MEGABYTE = 1024 * 1024;
                            riversRef.getBytes(ONE_MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
                                @Override
                                public void onSuccess(byte[] bytes) {
                                    // Data for "images/island.jpg" is returns, use this as needed
                                    photoDatabase.insertPhoto(nameList.get(listIncrement), recipient.child("Name").getValue().toString(), bytes);
                                    Log.d("ok",nameList.get(listIncrement));
                                    listIncrement++;
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception exception) {
                                    // Handle any errors
                                }
                            });


                        /*    localFile = null;
                                    try {
                                        localFile = File.createTempFile( recipient.child("Name").getValue().toString(), "jpeg");
                                        riversRef.getFile(localFile)
                                                .addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                                                    @Override
                                                    public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                                                        // Successfully downloaded data to local file
                                                        // ...
                                                        Toast.makeText(getActivity(), "Good Download :)", Toast.LENGTH_SHORT).show();
                                                        if (localFile.exists()) {

                                                            myBitmap = BitmapFactory.decodeFile(localFile.getAbsolutePath());
                                                            System.out.println("Photo fuck " + recipient.child("Name").getValue().toString() );
                                                            if (myBitmap != null) {
                                                                photoDatabase.insertPhoto(Integer.toString(idkey), recipient.child("Name").getValue().toString(), DbBitmapUtility.getBytes(myBitmap));
                                                                System.out.println("Photo fuck");
                                                            }
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
        */
                            }

                        publishProgress(progress);
                        progress++;
                    }
                }

                @Override
                public void onCancelled(DatabaseError error) {
                    // Failed to read value
                    Log.w(TAG, "Failed to read value.", error.toException());
                }
            });



            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            Toast.makeText(getActivity(), "Le traitement asynchrone est terminé", Toast.LENGTH_LONG).show();
            dialog.cancel();
        }
    }


}
