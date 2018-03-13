package novakpavel.app.photonet.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import novakpavel.app.photonet.R;
import novakpavel.app.photonet.model.entity.Posts;

public class PostsAdapter extends ArrayAdapter<Posts> {
    public PostsAdapter(Context context, List<Posts> posts) {
        super(context, 0, posts);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Posts post = getItem(position);

        if (convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.post_item, parent, false);
        }

        TextView idPost = (TextView) convertView.findViewById(R.id.id_post);
        TextView title = (TextView) convertView.findViewById(R.id.post_title);
        TextView body = (TextView) convertView.findViewById(R.id.post_body);

        if(post != null) {
            idPost.setText("1");
            title.setText(post.getTitle());
            body.setText(post.getBody());
        }
        return convertView;
    }
}
