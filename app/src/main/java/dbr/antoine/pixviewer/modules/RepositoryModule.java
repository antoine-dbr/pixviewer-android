package dbr.antoine.pixviewer.modules;

import com.yheriatovych.reductor.Store;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dbr.antoine.pixviewer.core.repositories.PictureRepository;
import dbr.antoine.pixviewer.core.repositories.PixabayPictureRepository;
import dbr.antoine.pixviewer.reducers.search.SearchState;

/**
 * Created by antoine on 7/8/17.
 */
@Module
public class RepositoryModule {

    @Provides @Singleton
    PictureRepository providePictureRepository(Store<SearchState> searchStateStore) {
        return new PixabayPictureRepository(searchStateStore);
    }
}
