package novakpavel.app.photonet.view;

import java.util.List;

import novakpavel.app.photonet.model.entity.Posts;

public interface IPhotoNetMainActivity {
    void setPostListViewData(List<Posts> posts);
    void updatePostsListView(List<Posts> posts);
    void setEmptyResponseText(String text);
    void hideLoadingIndicator();
    void showNoConnectionMessage();
}
