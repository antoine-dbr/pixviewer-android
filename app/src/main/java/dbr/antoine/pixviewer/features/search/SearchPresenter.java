package dbr.antoine.pixviewer.features.search;

import android.support.annotation.NonNull;

import com.yheriatovych.reductor.Actions;
import com.yheriatovych.reductor.Store;

import dbr.antoine.pixviewer.core.repositories.PictureRepository;
import dbr.antoine.pixviewer.core.utils.EmptyThrowable;
import dbr.antoine.pixviewer.features.common.Presenter;
import dbr.antoine.pixviewer.reducers.search.SearchActions;
import dbr.antoine.pixviewer.reducers.search.SearchState;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by antoine on 7/7/17.
 */

public class SearchPresenter implements Presenter {

    private static final SearchActions ACTIONS = Actions.from(SearchActions.class);

    private final SearchView searchView;
    private final Store<SearchState> searchStore;
    private final PictureRepository pictureRepository;

    private Disposable currentSubscription;

    SearchPresenter(SearchView searchView,
        Store<SearchState> searchStore,
        PictureRepository pictureRepository) {
        this.searchView = searchView;
        this.searchStore = searchStore;
        this.pictureRepository = pictureRepository;
    }

    @Override
    public void register() {
        render(searchStore.getState());
        searchStore.subscribe(this::render);
    }

    private void render(SearchState state) {
        showQuery(state);
        showSearch(state);
        showLoading(state);
        showError(state);
        showResult(state);
    }

    private void showQuery(SearchState state) {
        searchView.showQuery(state.query());
    }

    private void showSearch(SearchState state) {
        if (!state.loading() && EmptyThrowable.isEmpty(state.error()) && state.result().isEmpty()) {
            searchView.showSearch();
        }
    }

    private void showLoading(SearchState state) {
        if (state.loading()) {
            searchView.showLoading();
        }
    }

    private void showError(SearchState state) {
        if (!EmptyThrowable.isEmpty(state.error())) {
            searchView.showError(state.error().getLocalizedMessage());
        }
    }

    private void showResult(SearchState state) {
        if (state.result().size() > 0) {
            searchView.showResult(state.result());
        }
    }

    void reset() {
        searchStore.dispatch(ACTIONS.reset());
    }

    @Override
    public void unregister() {
        if (currentSubscription != null) {
            currentSubscription.dispose();
            currentSubscription = null;
        }
    }

    void searching(@NonNull String query) {
        searchStore.dispatch(ACTIONS.search(query));
    }

    void search() {
        searchStore.dispatch(ACTIONS.loading());
        currentSubscription = pictureRepository.searchPictures(searchStore.getState().query())
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(result -> searchStore.dispatch(ACTIONS.result(result)),
                throwable -> searchStore.dispatch(ACTIONS.error(throwable)));
    }
}
