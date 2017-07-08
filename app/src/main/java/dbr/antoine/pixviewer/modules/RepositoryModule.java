package dbr.antoine.pixviewer.modules;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dbr.antoine.pixviewer.core.repositories.PictureRepository;
import dbr.antoine.pixviewer.core.repositories.PixabayPictureRepository;

/**
 * Created by antoine on 7/8/17.
 */
@Module
public class RepositoryModule {

    @Provides @Singleton
    PictureRepository providePictureRepository() {
        return new PixabayPictureRepository();
    }
}
