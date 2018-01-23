package manager.android.simple.example;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class SampleAdapter extends RecyclerView.Adapter<SampleAdapter.SampleHolder> {
    private Context context;
    private List<Sample> list;
    private SampleClickListener clickListener;

    public SampleAdapter(Context context, List<Sample> list) {
        this.context = context;
        this.list = list;
    }

    public SampleAdapter(Context context, List<Sample> list, SampleClickListener clickListener) {
        this.context = context;
        this.list = list;
        this.clickListener = clickListener;
    }

    public SampleClickListener getClickListener() {
        return clickListener;
    }

    public void setClickListener(SampleClickListener clickListener) {
        this.clickListener = clickListener;
    }

    public interface SampleClickListener {
        void onSampleClick(Sample sample);
    }

    @Override
    public SampleHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemSampleView = LayoutInflater.from(context).inflate(R.layout.item_sample, parent, false);
        SampleHolder holder = new SampleHolder(itemSampleView);
        return holder;
    }

    @Override
    public void onBindViewHolder(SampleHolder holder, int position) {
        final Sample sample = list.get(position);
        holder.tvTitle.setText(sample.getTitle());
        holder.tvContent.setText(sample.getContent());

        if (holder.clickListener != null) {
            holder.clickListener = null;
        }

        holder.clickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickListener.onSampleClick(sample);
            }
        };
        holder.itemView.setOnClickListener(holder.clickListener);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class SampleHolder extends RecyclerView.ViewHolder {
        private TextView tvTitle;
        private TextView tvContent;
        private View.OnClickListener clickListener;

        public SampleHolder(View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tv_title);
            tvContent = itemView.findViewById(R.id.tv_content);
        }
    }
}
