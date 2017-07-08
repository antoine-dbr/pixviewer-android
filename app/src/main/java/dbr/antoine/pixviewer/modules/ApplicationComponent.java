package dbr.antoine.pixviewer.modules;

import javax.inject.Singleton;

import dagger.Component;
import dbr.antoine.pixviewer.PixViewerApplication;
import dbr.antoine.pixviewer.features.search.SearchComponent;
import dbr.antoine.pixviewer.features.search.SearchModule;

/**
 * Created by antoine on 7/8/17.
 */
@Singleton
@Component(modules = { ApplicationModule.class, RepositoryModule.class })
public interface ApplicationComponent {

    void inject(PixViewerApplication application);

    SearchComponent plus(SearchModule module);
}
