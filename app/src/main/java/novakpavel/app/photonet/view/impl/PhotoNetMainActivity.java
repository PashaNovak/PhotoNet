package novakpavel.app.photonet.view.impl;

import android.content.Context;
import android.graphics.PorterDuff;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.List;

import novakpavel.app.photonet.R;
import novakpavel.app.photonet.adapter.PostsAdapter;
import novakpavel.app.photonet.model.entity.Posts;
import novakpavel.app.photonet.presenter.IMainPresenter;
import novakpavel.app.photonet.presenter.impl.MainPresenter;
import novakpavel.app.photonet.view.IPhotoNetMainActivity;

public class PhotoNetMainActivity extends AppCompatActivity implements IPhotoNetMainActivity{
    private IMainPresenter _presenter;

    private List<Posts> _posts;

    private ListView _postsListView;
    private TextView _oopsTextView;
    private PostsAdapter _adapter;
    private ProgressBar _loadingIndicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_net_main);

        _postsListView = (ListView) findViewById(R.id.posts_list_view);

        _oopsTextView = (TextView) findViewById(R.id.oopsTextView);
        _postsListView.setEmptyView(_oopsTextView);

        _loadingIndicator = (ProgressBar) findViewById(R.id.loading_spinner);
        _loadingIndicator.getIndeterminateDrawable().setColorFilter(ContextCompat
                .getColor(this, R.color.colorPrimaryDark), PorterDuff.Mode.MULTIPLY);

        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnected()) {
            if (_presenter == null) _presenter = new MainPresenter(this);
            _presenter.getPostsData(false);

        } else {
            showNoConnectionMessage();
        }
    }

    @Override
    public void setPostListViewData(List<Posts> posts) {
        _posts = posts;
        _adapter = new PostsAdapter(getApplicationContext(), _posts);
        _postsListView.setAdapter(_adapter);
    }

    @Override
    public void updatePostsListView(List<Posts> posts) {
        _posts = posts;
        _adapter.notifyDataSetChanged();
    }

    @Override
    public void setEmptyResponseText(String text) {
        _oopsTextView.setText(text);
    }

    @Override
    public void hideLoadingIndicator() {
        _loadingIndicator.setVisibility(View.GONE);
        _oopsTextView.setText(R.string.no_internet_connection);
    }

    @Override
    public void showNoConnectionMessage() {

    }
}
