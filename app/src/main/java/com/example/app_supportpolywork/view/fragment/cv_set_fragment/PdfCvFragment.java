package com.example.app_supportpolywork.view.fragment.cv_set_fragment;

import static androidx.core.content.FileProvider.getUriForFile;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.pdf.PdfDocument;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.FileUtils;
import android.os.ParcelFileDescriptor;
import android.provider.DocumentsContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;

import com.example.app_supportpolywork.R;
import com.example.app_supportpolywork.databinding.FragmentPdfCvBinding;
import com.example.app_supportpolywork.util.CommonUtil;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.charset.StandardCharsets;

public class PdfCvFragment extends CVSetBaseFragment {

    private static final String TAG = "PdfCvFragment";

    private FragmentPdfCvBinding mBinding;

    private Bitmap bitmap;
    private Uri contentUri;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = FragmentPdfCvBinding.inflate(inflater);
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ActivityCompat.requestPermissions(requireActivity(),
                new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                1);

        mBinding.imvAvatar.setOnClickListener(v -> {
            bitmap = loadBitmapFromView();
            createPdf();
        });

    }

    private void createPdf() {
        int pageHeight = 1120;
        int pageWidth = 792;

        PdfDocument pdfDocument = new PdfDocument();
        PdfDocument.PageInfo pageInfo = new PdfDocument.PageInfo.Builder(pageWidth, pageHeight, 1).create();
        PdfDocument.Page page = pdfDocument.startPage(pageInfo);

        Canvas canvas = page.getCanvas();
        Paint paint = new Paint();
        canvas.drawPaint(paint);

        bitmap = Bitmap.createScaledBitmap(bitmap, pageWidth, pageHeight, true);
        canvas.drawBitmap(bitmap, 0, 0, null);
        pdfDocument.finishPage(page);

        File dir = requireContext().getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS);
        File newFile = new File(dir, "default_image.pdf");

        Log.i(TAG, "createPdf: " + newFile.getAbsolutePath());

        try {
            pdfDocument.writeTo(new FileOutputStream(newFile));
        } catch (IOException ioException) {
            ioException.printStackTrace();
            Toast.makeText(requireContext(), "Something ", Toast.LENGTH_SHORT).show();
        }

        pdfDocument.close();
        contentUri = getUriForFile(requireContext(), "com.example.app_supportpolywork.fileprovider", newFile);
        Log.i(TAG, "createPdf: " + contentUri.toString());
        Toast.makeText(requireContext(), "success", Toast.LENGTH_SHORT).show();
    }




    private Bitmap loadBitmapFromView() {
        bitmap = Bitmap.createBitmap(mBinding.page.getWidth(), mBinding.page.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        mBinding.page.draw(canvas);
        return bitmap;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 1: {

                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.
                    Toast.makeText(requireContext(), "Permission access", Toast.LENGTH_SHORT).show();

                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                    Toast.makeText(requireContext(), "Permission denied to read your External storage", Toast.LENGTH_SHORT).show();
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mBinding = null;
    }
}