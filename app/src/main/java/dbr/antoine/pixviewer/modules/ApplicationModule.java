package dbr.antoine.pixviewer.modules;

import android.app.Application;
import android.content.Context;

import com.yheriatovych.reductor.Store;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dbr.antoine.pixviewer.reducers.search.SearchReducer;
import dbr.antoine.pixviewer.reducers.search.SearchState;

/**
 * Created by antoine on 7/8/17.
 */
@Module
public class ApplicationModule {

    private final Context context;
    private final Application application;

    public ApplicationModule(Application application) {
        this.application = application;
        context = application.getApplicationContext();
    }

    @Provides @Singleton
    Application provideApplication() {
        return application;
    }


    @Provides @Singleton
    Context provideContext() {
        return context;
    }

    @Provides @Singleton
    Store<SearchState> provideSearchStore() {
        return Store.create(SearchReducer.create());
    }
}
