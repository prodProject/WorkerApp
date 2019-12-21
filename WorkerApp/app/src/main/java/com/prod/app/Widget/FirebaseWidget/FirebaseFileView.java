package com.prod.app.Widget.FirebaseWidget;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.webkit.MimeTypeMap;

import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.prod.app.Interfaces.IContext;
import com.prod.app.SessionsManger.WorkerSession;

import java.io.FileNotFoundException;
import java.io.IOException;

class FirebaseFileView implements IContext {

    private Context m_context;
    private final FirebaseStorage m_storage;
    private final StorageReference m_storageRef;
    private final WorkerSession m_workerSession;
    private Uri m_uriImage;

    private static final int SELECTED = 100;

    public FirebaseFileView() {
        m_storage = FirebaseStorage.getInstance();
        m_storageRef = m_storage.getReference();
        m_workerSession = new WorkerSession();
    }

    @Override
    public Context getActivityContext() {
        return m_context;
    }

    @Override
    public void setActivityContext(Context context) {
        m_context = context;
    }

    public Uri getUriImage() {
        return m_uriImage;
    }

    public void setUriImage(Uri uri) {
        m_uriImage = uri;
    }

    public FirebaseStorage getFirebaseStorage() {
        return m_storage;
    }

    public StorageReference getStorageRef() {
        return m_storageRef;
    }

    public WorkerSession getWorkerSession() {
        return m_workerSession;
    }

    public void getImag1ePicker() {
        Intent photoPicker = new Intent(Intent.ACTION_PICK);
        photoPicker.setType("image/*");
        Activity activity = (Activity) m_context;
        activity.startActivityForResult(photoPicker, SELECTED);
    }

    public String getExtentionFromImage(Uri uri) {
        ContentResolver contentResolver = m_context.getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri));
    }

}
