package novakpavel.app.photonet.model;

import novakpavel.app.photonet.model.entity.Posts;
import rx.Observable;

public interface IPostsAPI {
    Observable<Posts> getPosts();
}
