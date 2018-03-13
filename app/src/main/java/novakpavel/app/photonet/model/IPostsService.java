package novakpavel.app.photonet.model;

import novakpavel.app.photonet.model.entity.Posts;
import retrofit2.http.GET;
import rx.Observable;

public interface IPostsService {
    @GET("posts/")
    Observable<Posts> getPostData();
}
