package dbr.antoine.pixviewer.features.viewer;

import dbr.antoine.pixviewer.features.common.ScreenView;

/**
 * Created by antoine on 7/8/17.
 */

public interface ViewerView extends ScreenView<String> {

    void showAuthor(String author);

    void showFavorites(String favorites);

    void showLikes(String likes);

    void showComments(String comments);
}
