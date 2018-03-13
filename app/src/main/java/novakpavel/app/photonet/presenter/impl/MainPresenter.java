package novakpavel.app.photonet.presenter.impl;

import java.util.ArrayList;
import java.util.List;

import novakpavel.app.photonet.model.IPostsAPI;
import novakpavel.app.photonet.model.entity.Posts;
import novakpavel.app.photonet.model.impl.PostsAPI;
import novakpavel.app.photonet.presenter.IMainPresenter;
import novakpavel.app.photonet.view.IPhotoNetMainActivity;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainPresenter implements IMainPresenter{
    private final IPostsAPI _postsApi;
    private final IPhotoNetMainActivity _view;

    public MainPresenter(IPhotoNetMainActivity view) {
        _postsApi = new PostsAPI();
        _view = view;
    }

    @Override
    public void getPostsData(boolean isUpdate) {
        Observable<Posts> dataObservable = _postsApi.getPosts();

        dataObservable.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(postsData -> {
                    List<Posts> posts = new ArrayList<>();
                    posts.add(postsData);

                    _view.hideLoadingIndicator();

                    if (posts.isEmpty()) _view.setEmptyResponseText("There is no posts");
                    else if (isUpdate) _view.updatePostsListView(posts);
                    else _view.setPostListViewData(posts);
                });
    }
}
