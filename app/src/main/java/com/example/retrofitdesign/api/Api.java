package com.example.retrofitdesign.api;
import com.example.retrofitdesign.model.Comment;
import com.example.retrofitdesign.model.Post;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface Api {

    @GET("posts")
    Call<List<Post>> getPostList();

    @GET("posts/{id}/comments")
    Call<List<Comment>> getComments(@Path("id") int postId);
}
