package com.example.app_supportpolywork.data.model.support_model;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

public interface FilterField {
    String getValue();

    DiffUtil.ItemCallback<FilterField> diffCallback = new DiffUtil.ItemCallback<FilterField>() {
        @Override
        public boolean areItemsTheSame(@NonNull FilterField oldItem, @NonNull FilterField newItem) {
            return oldItem.getValue().equals(newItem.getValue());
        }

        @Override
        public boolean areContentsTheSame(@NonNull FilterField oldItem, @NonNull FilterField newItem) {
            return oldItem.getValue().equals(newItem.getValue());
        }
    };
}
