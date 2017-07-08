package dbr.antoine.pixviewer.features.search;

import com.yheriatovych.reductor.Store;

import dagger.Module;
import dagger.Provides;
import dbr.antoine.pixviewer.core.repositories.PictureRepository;
import dbr.antoine.pixviewer.reducers.search.SearchState;

/**
 * Created by antoine on 7/8/17.
 */
@Module
public class SearchModule {

    private SearchView view;

    public SearchModule(SearchView view) {
        this.view = view;
    }

    @Provides
    SearchView provideView() {
        return view;
    }

    @Provides
    SearchPresenter providePresenter(SearchView searchView,
                                     Store<SearchState> searchStateStore,
                                     PictureRepository pictureRepository) {
        return new SearchPresenter(searchView, searchStateStore, pictureRepository);
    }
}
