package com.example.retrofitdesign;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.retrofitdesign.adapter.PostAdapter;
import com.example.retrofitdesign.api.Api;
import com.example.retrofitdesign.model.Post;
import com.example.retrofitdesign.service.Service;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private List<Post> postList;
    private PostAdapter postAdapter;
    private RecyclerView recyclerView;
    private Api api;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        callPostList();

    }




    public void init(){
        recyclerView=findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
    }
    private void setAdapter() {
        postAdapter=new PostAdapter(getApplicationContext(),postList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(postAdapter);
    }


    private void callPostList() {
        api = Service.get().getJsonUrlRetrofit().create(Api.class);
        Call<List<Post>> callback= api.getPostList();
        callback.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(getApplicationContext(),"Code :"+response.code(),Toast.LENGTH_SHORT).show();
                }else{
                    postList=response.body();
                    setAdapter();
                }
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                Toast.makeText(getApplicationContext(),t.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });

    }

}