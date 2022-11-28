package com.example.app_supportpolywork.util;

import android.net.Uri;

import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class UploadImageUtil {

    public static void uploadImage(Uri file, TaskListener listener) {
        FirebaseStorage mStorage = FirebaseStorage.getInstance();
        StorageReference mCvRef = mStorage.getReference().child("cv/" + System.currentTimeMillis()+ ".pdf");
        UploadTask uploadTask = mCvRef.putFile(file);
        uploadTask.continueWithTask(task -> {
            if (!task.isSuccessful()) {
                listener.onError(task.getException());
            }

            return mCvRef.getDownloadUrl();
        }).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                Uri downloadUri = task.getResult();
                listener.onSuccess(downloadUri.toString());
            } else {
                listener.onError(task.getException());
            }
        });
    }


}
