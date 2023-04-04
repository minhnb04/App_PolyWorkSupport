package com.example.app_supportpolywork.view.main_activity.cv;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app_supportpolywork.R;
import com.example.app_supportpolywork.data.model.cv_model.CV;
import com.example.app_supportpolywork.databinding.LayoutCvTemplateBinding;

public class CvTemplateAdapter extends ListAdapter<CV, CvTemplateAdapter.CvTemplateViewHolder> {

    private final CVTemplateListener cvTemplateListener;

    protected CvTemplateAdapter(CVTemplateListener listener) {
        super(CV.sDiffCallback);
        cvTemplateListener = listener;
    }

    @NonNull
    @Override
    public CvTemplateViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutCvTemplateBinding binding = LayoutCvTemplateBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new CvTemplateViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull CvTemplateViewHolder holder, int position) {
        CV cv = getItem(position);
        if (cv != null) {
            if (cv.getCvType() == CV.CVType.FREE) {
                holder.mBinding.tvType.setText("Miễn phí");
                holder.mBinding.tvType.setBackgroundTintList(null);
                holder.mBinding.tvType.setBackground(ContextCompat.getDrawable(holder.mBinding.getRoot().getContext(), R.drawable.bg_boder_solid));
                holder.mBinding.tvType.setBackgroundColor(Color.GREEN);
            } else {
                holder.mBinding.tvType.setText("Cao cấp");
                holder.mBinding.tvType.setBackgroundTintList(null);
                holder.mBinding.tvType.setBackground(ContextCompat.getDrawable(holder.mBinding.getRoot().getContext(), R.drawable.bg_boder_solid));
                holder.mBinding.tvType.setBackgroundColor(Color.RED);
            }
            holder.mBinding.tvName.setText(cv.getName());
            holder.mBinding.imvAvatar.setImageDrawable(ContextCompat.getDrawable(holder.mBinding.getRoot().getContext(), cv.getAvatar()));
            holder.mBinding.btnUse.setOnClickListener(v -> cvTemplateListener.onClickCvTemplateItem(cv));
        }
    }

    public static class CvTemplateViewHolder extends RecyclerView.ViewHolder {
        private final LayoutCvTemplateBinding mBinding;

        public CvTemplateViewHolder(@NonNull LayoutCvTemplateBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }
    }

    public interface CVTemplateListener {
        void onClickCvTemplateItem(CV cv);
    }
}
