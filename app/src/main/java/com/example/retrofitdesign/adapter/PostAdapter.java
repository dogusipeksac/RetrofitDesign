package com.example.retrofitdesign.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.retrofitdesign.CommentActivity;
import com.example.retrofitdesign.R;
import com.example.retrofitdesign.model.Post;

import java.util.List;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.ViewHolder> {


    private Context context;
    private List<Post> postList;

    public PostAdapter(Context context, List<Post> postList) {
        this.context = context;
        this.postList = postList;
    }

    @NonNull
    @Override
    public PostAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater layoutInflater= LayoutInflater.from(context);
        view=layoutInflater.inflate(R.layout.item,parent,false);
        return new ViewHolder(view);

    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull PostAdapter.ViewHolder holder, int position) {
        Post post=postList.get(position);
        holder.idText.setText("id:"+post.getId());
        holder.bodyText.setText("body:"+post.getBody());
        holder.userIdText.setText("user id:"+post.getUserId());
        holder.titleText.setText("title:"+post.getTitle());
        holder.goToComments.setOnClickListener(v -> {
            Intent intent=new Intent(context, CommentActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.putExtra("userId",post.getUserId());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return postList.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView titleText;
        TextView idText;
        TextView userIdText;
        TextView bodyText;
        TextView goToComments;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            titleText=itemView.findViewById(R.id.titleItemTextview);
            idText=itemView.findViewById(R.id.idItemTextview);
            userIdText=itemView.findViewById(R.id.userIdItemTextview);
            bodyText=itemView.findViewById(R.id.bodyItemTextview);
            goToComments=itemView.findViewById(R.id.goToComment);
        }
    }
}
