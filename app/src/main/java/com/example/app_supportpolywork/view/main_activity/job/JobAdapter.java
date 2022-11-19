package com.example.app_supportpolywork.view.main_activity.job;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app_supportpolywork.R;
import com.example.app_supportpolywork.data.model.Job;
import com.example.app_supportpolywork.databinding.LayoutJobBinding;
import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
            holder.mBinding.tvSalary.setText(getJobSalary(5_000_000, 10_000_000));
            holder.mBinding.tvNumberOfPeople.setText(getJobExpiry("18:30 20/11/2022"));
            holder.mBinding.tvWorkForm.setText(job.getWorkPlace());
            holder.mBinding.tvWorkForm.setText(job.getWorkForm());
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


    public String getJobSalary(long startSalary, long endSalary) {
        if (endSalary == -1) {
            return "Thỏa thuận";
        }
        return (startSalary / 1_000_000) + " - " + (endSalary / 1_000_000) + " triệu";
    }

    public String getJobExpiry(String expiry) {
        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh:ss dd/MM/yyyy");
        try {
            Date date = simpleDateFormat.parse(expiry);
            if (date != null) {
                long time = System.currentTimeMillis() - date.getTime();
                int numberOfDate = (int) (time / (1000 * 60 * 60 * 24));
                if (numberOfDate >= 0) {
                    return "Còn " + (numberOfDate + 1) + " ngày để ứng tuyển";
                } else {
                    return "Hết hạn ứng tuyển";
                }
            } else {
                return "Hết hạn ứng tuyển";
            }
        } catch (ParseException e) {
            e.printStackTrace();
            return "Hết hạn ứng tuyển";
        }
    }

    public interface JobAdapterListener {
        void onClickJobItem(Job job);
    }
}
