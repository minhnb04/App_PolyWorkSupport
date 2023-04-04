package com.example.app_supportpolywork.view.main_activity.job;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app_supportpolywork.data.model.support_model.FilterField;
import com.example.app_supportpolywork.databinding.LayoutFilterFieldBinding;

import java.util.List;

public class JobFilterContentAdapter extends ListAdapter<FilterField, JobFilterContentAdapter.ViewHolder> {

    private List<FilterField> mFilterFields;
    private OnClickFilterFieldListener mListener;

    protected JobFilterContentAdapter(OnClickFilterFieldListener listener) {
        super(FilterField.diffCallback);
        mListener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutFilterFieldBinding binding = LayoutFilterFieldBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        FilterField filterField = getItem(position);
        holder.mBinding.tvName.setText(filterField.getValue());
        holder.mBinding.btnClear.setOnClickListener(v -> {
            mListener.onClearFilterField(filterField);
        });
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        LayoutFilterFieldBinding mBinding;

        public ViewHolder(@NonNull LayoutFilterFieldBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }
    }

    public interface OnClickFilterFieldListener {
        void onClearFilterField(FilterField filterField);
    }
}
