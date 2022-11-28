package com.example.app_supportpolywork.view.main_activity.cv;

import android.annotation.SuppressLint;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app_supportpolywork.data.model.CV;
import com.example.app_supportpolywork.databinding.LayoutCvItemBinding;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class CvItemAdapter extends ListAdapter<CV, CvItemAdapter.CvItemViewHolder> {
    private static final String TAG = "CvItemAdapter";
    private static final DiffUtil.ItemCallback<CV> sDiffUtil = new DiffUtil.ItemCallback<CV>() {
        @Override
        public boolean areItemsTheSame(@NonNull CV oldItem, @NonNull CV newItem) {
            return oldItem.getId().equals(newItem.getId());
        }

        @Override
        public boolean areContentsTheSame(@NonNull CV oldItem, @NonNull CV newItem) {
            return oldItem.equals(newItem);
        }
    };

    private final OnClickCVItemListener mListener;

    public CvItemAdapter(OnClickCVItemListener listener) {
        super(sDiffUtil);
        mListener = listener;
    }

    @NonNull
    @Override
    public CvItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutCvItemBinding binding = LayoutCvItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new CvItemViewHolder(binding);
    }

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    public void onBindViewHolder(@NonNull CvItemViewHolder holder, int position) {
        CV cv = getItem(position);
        if (cv != null) {
            holder.mBinding.tvName.setText(cv.getTitle());

            holder.mBinding.imvEdit.setOnClickListener(v -> {
                mListener.onEditItem(cv);
            });

            holder.mBinding.imvDelete.setOnClickListener(v -> {
                mListener.onDeleteItem(cv);
            });

            holder.mBinding.root.setOnClickListener(v -> {
                mListener.onClickItem(cv);
            });

            loadImageFromNetwork(holder.mBinding.imvCV, cv.getImage());
        }
    }

    public static class CvItemViewHolder extends RecyclerView.ViewHolder {
        private final LayoutCvItemBinding mBinding;

        public CvItemViewHolder(@NonNull LayoutCvItemBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }
    }

    interface OnClickCVItemListener {
        void onEditItem(CV cv);

        void onDeleteItem(CV cv);

        void onClickItem(CV cv);
    }

    private void loadImageFromNetwork(ImageView pdfView, String _url) {
        new Thread(() -> {
            InputStream inputStream = null;

            try {
                inputStream = new URL(_url).openConnection().getInputStream();
            } catch (IOException e) {
                e.printStackTrace();
            }

            InputStream finalInputStream = inputStream;
            pdfView.post(() -> pdfView.setImageBitmap(BitmapFactory.decodeStream(finalInputStream)));

        }).start();
    }


}
