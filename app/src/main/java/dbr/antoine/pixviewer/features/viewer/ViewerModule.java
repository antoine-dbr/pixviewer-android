package dbr.antoine.pixviewer.features.viewer;

import dagger.Module;
import dagger.Provides;
import dbr.antoine.pixviewer.core.repositories.PictureRepository;

/**
 * Created by antoine on 7/8/17.
 */
@Module
public class ViewerModule {

    private ViewerView view;

    ViewerModule(ViewerView view) {
        this.view = view;
    }

    @Provides
    ViewerView provideView() {
        return view;
    }

    @Provides
    ViewerPresenter providePresenter(ViewerView viewerView, PictureRepository pictureRepository) {
        return new ViewerPresenter(viewerView, pictureRepository);
    }
}
