package com.example.adrianduarte.androidchallenge.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.adrianduarte.androidchallenge.R;
import com.example.adrianduarte.androidchallenge.models.Image;
import com.example.adrianduarte.androidchallenge.models.Item;
import com.example.adrianduarte.androidchallenge.utils.DateUtils;
import com.example.adrianduarte.androidchallenge.utils.ImageUtils;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewImageAdapter
    extends RecyclerView.Adapter<RecyclerViewImageAdapter.CustomViewHolder>
    implements View.OnClickListener, Filterable
{
    // Attributes
    private Context context;
    private List<Item> fullItems;
    private RecyclerViewImageAdapter.OnClickListener onClickListener;
    private List<Item> items;
    private ValueFilter valueFilter;

    // Constructors
    public RecyclerViewImageAdapter(Context context, List<Item> items, RecyclerViewImageAdapter.OnClickListener onClickListener) {
        this.context = context;
        this.fullItems = items;
        this.items = items;
        this.onClickListener = onClickListener;
    }

    // Override methods and callbacks
    @Override
    public RecyclerViewImageAdapter.CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Inflate view
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_image_tag, parent, false);
        view.setOnClickListener(this);
        return new RecyclerViewImageAdapter.CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerViewImageAdapter.CustomViewHolder holder, int position) {
        // Get current item
        Item tag = items.get(position);

        // Update UI
        holder.itemView.setTag(position);
        holder.tvDate.setText(DateUtils.formatDate(tag.getDatetime()));
        if(tag.getDescription()!=null) {
            holder.tvDescription.setText(tag.getDescription());
        } else {
            holder.tvDescription.setVisibility(View.GONE);
        }
        holder.tvTitle.setText(tag.getTitle());
        List<Image> images = tag.getImages();
        if(images!=null) {
            ImageUtils.loadGlideImage(context, images.get(0).getLink(), holder.ivImage);
        }

    }

    @Override
    public void onClick(View view) {
        onClickListener.onClick(view, items.get((int)view.getTag()));
    }

    @Override
    public int getItemCount() {
        return items.size();
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
        private ImageView ivImage;
        private TextView tvDate;
        private TextView tvDescription;
        private TextView tvTitle;

        // Constructors
        private CustomViewHolder(View view) {
            super(view);
            ivImage = view.findViewById(R.id.iv_image);
            tvDate = view.findViewById(R.id.tv_date);
            tvDescription = view.findViewById(R.id.tv_description);
            tvTitle = view.findViewById(R.id.tv_title);
        }
    }

    public interface OnClickListener {
        void onClick(View view, Item selectedItem);
    }

    private class ValueFilter extends Filter {

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();
            if (constraint != null && constraint.length() > 0) {
                List<Item> items = new ArrayList<>();
                for (Item item: fullItems) {
                    if(item.getTitle().toLowerCase().contains(constraint.toString().toLowerCase())) {
                        items.add(item);
                    }
                }
                results.count = items.size();
                results.values = items;
            } else {
                results.count = fullItems.size();
                results.values = fullItems;
            }
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            items = (List<Item>) results.values;
            notifyDataSetChanged();
        }

    }

}
