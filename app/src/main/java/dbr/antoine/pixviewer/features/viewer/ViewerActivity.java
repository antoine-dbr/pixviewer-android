package dbr.antoine.pixviewer.features.viewer;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import javax.inject.Inject;

import butterknife.BindView;
import dbr.antoine.pixviewer.R;
import dbr.antoine.pixviewer.features.common.ActivityBase;
import dbr.antoine.pixviewer.modules.ApplicationComponent;

public class ViewerActivity extends ActivityBase implements ViewerView {

    public static final String EXTRA_PICTURE_ID = "picture_id";

    @BindView(R.id.loading_layout) View loadingLayout;
    @BindView(R.id.error_layout) View errorLayout;
    @BindView(R.id.viewer_layout) View viewerLayout;

    @BindView(R.id.image) ImageView image;
    @BindView(R.id.author) TextView author;
    @BindView(R.id.favorites) TextView favorites;
    @BindView(R.id.likes) TextView likes;
    @BindView(R.id.comments) TextView comments;

    @Inject ViewerPresenter presenter;

    public static Intent create(@NonNull Context context, String pictureId) {
        Intent intent = new Intent(context, ViewerActivity.class);
        intent.putExtra(EXTRA_PICTURE_ID, pictureId);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewer);

        presenter.register();
        presenter.loadPicture(getIntent().getStringExtra(EXTRA_PICTURE_ID));
    }

    @Override
    protected void onDestroy() {
        presenter.unregister();
        super.onDestroy();
    }

    @Override
    public void showLoading() {
        loadingLayout.setVisibility(View.VISIBLE);
        errorLayout.setVisibility(View.GONE);
        viewerLayout.setVisibility(View.GONE);
    }

    @Override
    public void showError(String message) {
        loadingLayout.setVisibility(View.GONE);
        errorLayout.setVisibility(View.VISIBLE);
        viewerLayout.setVisibility(View.GONE);
    }

    @Override
    public void showResult(String pictureUrl) {
        loadingLayout.setVisibility(View.GONE);
        errorLayout.setVisibility(View.GONE);
        viewerLayout.setVisibility(View.VISIBLE);

        Picasso.with(this).load(pictureUrl).into(image);
    }

    @Override
    public void showAuthor(String author) {
        this.author.setText(author);
    }

    @Override
    public void showFavorites(String favorites) {
        this.favorites.setText(favorites);
    }

    @Override
    public void showLikes(String likes) {
        this.likes.setText(likes);
    }

    @Override
    public void showComments(String comments) {
        this.comments.setText(comments);
    }

    @Override
    protected void setupComponent(ApplicationComponent applicationComponent) {
        applicationComponent.plus(new ViewerModule(this)).inject(this);
    }
}
