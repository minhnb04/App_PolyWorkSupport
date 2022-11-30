package com.example.app_supportpolywork.view.main_activity.job;

import static com.example.app_supportpolywork.util.AdapterUtil.getJobExpiry;
import static com.example.app_supportpolywork.util.AdapterUtil.getJobSalary;
import static com.example.app_supportpolywork.util.AdapterUtil.getNeededPeople;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app_supportpolywork.R;
import com.example.app_supportpolywork.data.model.Job;
import com.example.app_supportpolywork.databinding.LayoutJobBinding;
import com.squareup.picasso.Picasso;

public class JobAdapter extends ListAdapter<Job, JobAdapter.JobViewHolder> {

    private final JobAdapterListener mJobAdapterListener;

    protected JobAdapter(JobAdapterListener listener) {
        super(Job.sDiffCallback);
        mJobAdapterListener = listener;
    }

    @NonNull
    @Override
    public JobAdapter.JobViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutJobBinding binding = LayoutJobBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new JobViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull JobAdapter.JobViewHolder holder, int position) {
        Job job = getItem(position);
        if (job != null) {
            Picasso.get().load(job.getAvatar()).placeholder(R.drawable.img_sample).error(R.drawable.img_sample)
                    .into(holder.mBinding.imvJobAvatar);

            holder.mBinding.tvJobTitle.setText(job.getTitle());
            holder.mBinding.tvSalary.setText(getJobSalary(job.getStartSalary(), job.getEndSalary()));
            holder.mBinding.tvNumberOfPeople.setText(job.getSlot());
            holder.mBinding.tvWorkPlace.setText(job.getWorkPlace());
            holder.mBinding.tvWorkForm.setText(job.getWorkForm());
            holder.mBinding.tvExpiry.setText(getJobExpiry(job.getExpiryApply()));
            holder.mBinding.root.setOnClickListener(v -> mJobAdapterListener.onClickJobItem(job));
        }
    }

    public static class JobViewHolder extends RecyclerView.ViewHolder {
        private final LayoutJobBinding mBinding;

        public JobViewHolder(@NonNull LayoutJobBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }
    }

    public interface JobAdapterListener {
        void onClickJobItem(Job job);
    }
}
