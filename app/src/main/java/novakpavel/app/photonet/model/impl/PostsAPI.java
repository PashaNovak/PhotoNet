package novakpavel.app.photonet.model.impl;

import novakpavel.app.photonet.model.IPostsAPI;
import novakpavel.app.photonet.model.IPostsService;
import novakpavel.app.photonet.model.entity.Posts;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;

public class PostsAPI implements IPostsAPI {
    @Override
    public Observable<Posts> getPosts() {
        Retrofit retrofit = new Retrofit.Builder()
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .build();
        IPostsService postsService = retrofit.create(IPostsService.class);
        return postsService.getPostData();
    }
}
