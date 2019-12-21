package com.prod.app.Widget.FirebaseWidget;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.prod.app.Interfaces.IView;
import com.prod.app.R;
import com.prod.app.SessionsManger.WorkerSession;
import com.prod.app.Utility.AndroidUtility;
import com.prod.app.protobuff.Worker;

import java.io.FileNotFoundException;
import java.io.IOException;

public class FirebaseFileWidget extends LinearLayout implements IView<FirebaseFileView> {


    private Context m_context;
    private Button m_chooseButton;
    private Button m_uplaodButton;
    private EditText m_imageName;
    private ImageView m_resultImage;
    private TextView m_url;
    private FirebaseFileView m_firebaseFileView;
    private Uri m_uriImage;
    private FirebaseStorage m_storage;
    private StorageReference m_storageRef, imgRef;
    private ProgressDialog m_progressDailog;
    private UploadTask m_uploadTask;
    private WorkerSession m_workerSession;

    String filePath = "";

    private static final int SELECTED = 100;

    public FirebaseFileWidget(Context context, AttributeSet attrs) {
        super(context, attrs);
        m_context = context;
        getView().setActivityContext(context);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        inflate(context, R.layout.firebase_file_widget, this);
        inflateLayout();
        if (!isInEditMode()) {
            initWidget();
        }
    }

    private void inflateLayout() {
        inflate(getContext(), R.layout.otp_verification_layout, this);
        m_chooseButton = findViewById(R.id.chooseButton);
        m_uplaodButton = findViewById(R.id.uploadButton);
        m_imageName = findViewById(R.id.imageNmae);
        m_resultImage = findViewById(R.id.image);
        m_url = findViewById(R.id.url);
        m_firebaseFileView = new FirebaseFileView();
        m_storage = FirebaseStorage.getInstance();
        m_storageRef = m_storage.getReference();
        m_workerSession = new WorkerSession();
    }

    private void initWidget() {
        m_chooseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getView().getImag1ePicker();
            }
        });

        m_uplaodButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadImage();
            }
        });
    }


    private void uploadImage() {
        filePath = "Images/" + m_imageName.getText() + getExtentionFromImage(m_uriImage);
        imgRef = m_storageRef.child(filePath);
        m_progressDailog = new ProgressDialog(m_context);
        m_progressDailog.setMax(100);
        m_progressDailog.setMessage("Uploading...");
        m_progressDailog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        m_progressDailog.show();
        m_progressDailog.setCancelable(false);

        m_uploadTask = imgRef.putFile(m_uriImage);

        m_uploadTask.addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(@NonNull UploadTask.TaskSnapshot taskSnapshot) {
                m_progressDailog.incrementProgressBy((int) ((int) (100 * taskSnapshot.getBytesTransferred()) / taskSnapshot.getTotalByteCount()));
            }
        });
        m_uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(m_context, "Failed !! Something Went wrong", Toast.LENGTH_LONG).show();
                m_progressDailog.dismiss();
            }
        });
        m_uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Toast.makeText(m_context, "Sucessfully Uploaded !!", Toast.LENGTH_LONG).show();
                AndroidUtility.getMakeTextToastLong(m_context, "Sucessfully Uploaded !!");
                m_progressDailog.dismiss();
            }
        });
        m_uploadTask.addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                m_storageRef.child(filePath).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        m_url.setText(uri.toString());
                        Log.e("url", uri.toString());
                        Worker.WorkerPb.Builder workerPb = m_workerSession.getSession().toBuilder();
                        workerPb.getProfilePicsBuilder().addImageUrl(uri.toString());
                        m_workerSession.updateSession(workerPb.build());
                    }
                });
            }
        });

    }

    private String getExtentionFromImage(Uri uri) {
        ContentResolver contentResolver = m_context.getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri));
    }

    private void getImag1ePicker() {
        Intent photoPicker = new Intent(Intent.ACTION_PICK);
        photoPicker.setType("image/*");
        Activity activity = (Activity) m_context;
        activity.startActivityForResult(photoPicker, SELECTED);
    }


    @Override
    public FirebaseFileView getView() {
        return m_firebaseFileView;
    }

    /***
     * use this method in Activity for get opreated
     * @param requestCode
     * @param resultCode
     * @param data
     */
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case SELECTED:
                if (resultCode == Activity.RESULT_OK) {
                    getView().setUriImage(data.getData());
                    try {
                        Bitmap bitmap = MediaStore.Images.Media.getBitmap(m_context.getContentResolver(), getView().getUriImage());
                        m_resultImage.setImageBitmap(bitmap);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
        }

    }
}
