package com.example.retrofitdesign;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.retrofitdesign.adapter.CommentAdapter;
import com.example.retrofitdesign.api.Api;
import com.example.retrofitdesign.model.Comment;
import com.example.retrofitdesign.service.Service;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CommentActivity extends AppCompatActivity {

    private List<Comment> commentList;
    private CommentAdapter commentAdapter;
    private RecyclerView recyclerView;
    private Api api;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);
        init();
        callCommentList();
    }


    public void init(){
        recyclerView=findViewById(R.id.recyclerViewComment);
        recyclerView.setHasFixedSize(true);
    }
    private void setAdapter() {

        commentAdapter=new CommentAdapter(getApplicationContext(),commentList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(commentAdapter);

    }
    private void callCommentList() {

        api = Service.get().getJsonUrlRetrofit().create(Api.class);
        Call<List<Comment>> callback= api.getComments(getIntent().getIntExtra("userId",0));
        callback.enqueue(new Callback<List<Comment>>() {
            @Override
            public void onResponse(Call<List<Comment>> call, Response<List<Comment>> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(getApplicationContext(),"Code :"+response.code(),Toast.LENGTH_SHORT).show();
                }else{
                    commentList=response.body();
                    setAdapter();
                }
            }

            @Override
            public void onFailure(Call<List<Comment>> call, Throwable t) {
                Toast.makeText(getApplicationContext(),t.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });

    }
}