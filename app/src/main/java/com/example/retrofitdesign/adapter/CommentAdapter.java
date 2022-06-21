package com.example.retrofitdesign.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.retrofitdesign.R;
import com.example.retrofitdesign.model.Comment;
import com.example.retrofitdesign.model.Post;

import java.util.List;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.ViewHolder> {


    private Context context;
    private List<Comment> commentList;

    public CommentAdapter(Context context, List<Comment> commentList) {
        this.context = context;
        this.commentList = commentList;
    }

    @NonNull
    @Override
    public CommentAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater layoutInflater= LayoutInflater.from(context);
        view=layoutInflater.inflate(R.layout.comment_item,parent,false);
        return new ViewHolder(view);

    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull CommentAdapter.ViewHolder holder, int position) {
        Comment comment=commentList.get(position);
        holder.idText.setText("id:"+comment.getId());
        holder.postIdText.setText("user id:"+comment.getPostId());
        holder.bodyText.setText("body:"+comment.getBody());
        holder.nameText.setText("name:"+comment.getName());
        holder.emailText.setText("email:"+comment.getEmail());

    }

    @Override
    public int getItemCount() {
        return commentList.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView postIdText;
        TextView idText;
        TextView nameText;
        TextView emailText;
        TextView bodyText;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nameText=itemView.findViewById(R.id.nameCommentTextview);
            postIdText=itemView.findViewById(R.id.postIdCommentTextview);
            idText=itemView.findViewById(R.id.idCommentTextview);
            emailText=itemView.findViewById(R.id.emailTextView);
            bodyText=itemView.findViewById(R.id.bodyCommentTextView);
        }
    }
}
