package com.example.adrianduarte.androidchallenge.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.example.adrianduarte.androidchallenge.R;
import com.example.adrianduarte.androidchallenge.models.Tag;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewTagAdapter
    extends RecyclerView.Adapter<RecyclerViewTagAdapter.CustomViewHolder>
    implements View.OnClickListener, Filterable
{

    // Attributes
    private OnClickListener onClickListener;
    private List<Tag> fullTags;
    private List<Tag> tags;
    private ValueFilter valueFilter;

    // Constructors
    public RecyclerViewTagAdapter(List<Tag> tags, OnClickListener onClickListener) {
        this.fullTags = tags;
        this.tags = tags;
        this.onClickListener = onClickListener;
    }

    // Override methods and callbacks
    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Inflate view
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_tag, parent, false);
        view.setOnClickListener(this);
        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, int position) {
        // Get current item
        Tag tag = tags.get(position);

        // Update UI
        holder.itemView.setTag(position);
        holder.tvDisplayName.setText(tag.getDisplayName());
    }

    @Override
    public void onClick(View view) {
        onClickListener.onClick(view, fullTags.get((int)view.getTag()));
    }

    @Override
    public int getItemCount() {
        return tags.size();
    }

    @Override
    public Filter getFilter() {
        if (valueFilter == null) {
            valueFilter = new ValueFilter();
        }
        return valueFilter;
    }

    // Inner classes or interfaces
    protected class CustomViewHolder extends RecyclerView.ViewHolder {
        // Attributes
        private TextView tvDisplayName;

        // Constructors
        private CustomViewHolder(View view) {
            super(view);
            tvDisplayName = view.findViewById(R.id.tv_display_name);
        }
    }

    public interface OnClickListener {
        void onClick(View view, Tag selectedTag);
    }

    private class ValueFilter extends Filter {

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();
            if (constraint != null && constraint.length() > 0) {
                List<Tag> tags = new ArrayList<>();
                for (Tag tag: fullTags) {
                    if(tag.getDisplayName().toLowerCase().contains(constraint.toString().toLowerCase())) {
                        tags.add(tag);
                    }
                }
                results.count = tags.size();
                results.values = tags;
            } else {
                results.count = fullTags.size();
                results.values = fullTags;
            }
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            tags = (List<Tag>) results.values;
            notifyDataSetChanged();
        }

    }

}