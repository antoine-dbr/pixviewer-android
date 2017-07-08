package dbr.antoine.pixviewer.features.viewer;

import dagger.Subcomponent;

/**
 * Created by antoine on 7/8/17.
 */
@Subcomponent(modules = ViewerModule.class)
public interface ViewerComponent {
    void inject(ViewerActivity activity);
}