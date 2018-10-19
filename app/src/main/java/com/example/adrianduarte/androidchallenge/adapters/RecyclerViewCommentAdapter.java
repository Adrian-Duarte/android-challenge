package com.example.adrianduarte.androidchallenge.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.adrianduarte.androidchallenge.R;
import com.example.adrianduarte.androidchallenge.models.Comment;

import java.util.List;

public class RecyclerViewCommentAdapter
    extends RecyclerView.Adapter<RecyclerViewCommentAdapter.CustomViewHolder>
{
    // Attributes
    private List<Comment> comments;

    // Constructors
    public RecyclerViewCommentAdapter(List<Comment> comments) {
        this.comments = comments;
    }

    // Override methods and callbacks
    @Override
    public RecyclerViewCommentAdapter.CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Inflate view
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_comment, parent, false);
        return new RecyclerViewCommentAdapter.CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerViewCommentAdapter.CustomViewHolder holder, int position) {
        // Get current item
        Comment comment = comments.get(position);

        // Update UI
        holder.tvAuthor.setText(comment.getAuthor());
        holder.tvComment.setText(comment.getComment());
    }

    @Override
    public int getItemCount() {
        return comments.size();
    }

    // Inner classes or interfaces
    protected class CustomViewHolder extends RecyclerView.ViewHolder {
        // Attributes
        private TextView tvAuthor;
        private TextView tvComment;

        // Constructors
        private CustomViewHolder(View view) {
            super(view);
            tvAuthor = view.findViewById(R.id.tv_author);
            tvComment = view.findViewById(R.id.tv_comment);
        }
    }

}
