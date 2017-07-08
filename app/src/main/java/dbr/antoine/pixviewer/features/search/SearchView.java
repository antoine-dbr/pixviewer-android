package dbr.antoine.pixviewer.features.search;

import java.util.List;

import dbr.antoine.pixviewer.core.models.PicturePost;
import dbr.antoine.pixviewer.features.common.ScreenView;

/**
 * Created by antoine on 7/7/17.
 */

public interface SearchView extends ScreenView<List<PicturePost>> {

    void allowSearch(boolean allow);

    void showSearch();
}
