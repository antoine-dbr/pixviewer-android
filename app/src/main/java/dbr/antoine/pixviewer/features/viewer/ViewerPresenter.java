package dbr.antoine.pixviewer.features.viewer;

import dbr.antoine.pixviewer.core.models.PicturePost;
import dbr.antoine.pixviewer.core.repositories.PictureRepository;
import dbr.antoine.pixviewer.features.common.Presenter;
import io.reactivex.disposables.Disposable;

/**
 * Created by antoine on 7/8/17.
 */

public class ViewerPresenter implements Presenter {

    private final ViewerView viewerView;
    private final PictureRepository pictureRepository;

    private Disposable currentSubscription;

    ViewerPresenter(ViewerView viewerView, PictureRepository pictureRepository) {
        this.viewerView = viewerView;
        this.pictureRepository = pictureRepository;
    }

    @Override
    public void register() {
    }

    @Override
    public void unregister() {
        if (currentSubscription != null) {
            currentSubscription.dispose();
            currentSubscription = null;
        }
    }

    void loadPicture(String pictureId) {
        // Safe to call on the main thread
        currentSubscription = pictureRepository.searchPictureById(pictureId)
            .subscribe(ViewerPresenter.this::render,
                throwable -> viewerView.showError(throwable.getLocalizedMessage()));
    }

    private void render(PicturePost picture) {
        viewerView.showResult(picture.largePictureUrl());
        viewerView.showAuthor(picture.author());
        viewerView.showFavorites(String.valueOf(picture.totalFavorites()));
        viewerView.showLikes(String.valueOf(picture.totalLikes()));
        viewerView.showComments(String.valueOf(picture.totalComments()));
    }
}
