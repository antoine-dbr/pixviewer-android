package dbr.antoine.pixviewer.features.search;

import com.yheriatovych.reductor.Reducer;
import com.yheriatovych.reductor.annotations.AutoReducer;

import java.util.ArrayList;
import java.util.List;

import dbr.antoine.pixviewer.core.models.PicturePost;
import dbr.antoine.pixviewer.core.utils.EmptyThrowable;

/**
 * Created by antoine on 7/7/17.
 */
@AutoReducer
abstract class SearchReducer implements Reducer<SearchState> {

    @AutoReducer.InitialState
    SearchState initialState() {
        return SearchState.builder()
            .setLoading(false)
            .setQuery("")
            .setError(new EmptyThrowable()) // workaround as autovalue doesn't deal with @Nullable Java8
            .setResult(new ArrayList<>()) // workaround as autovalue doesn't deal with @Nullable Java8
            .build();
    }

    @AutoReducer.Action(value = SearchActions.RESET, from = SearchActions.class)
    SearchState reset(SearchState state) {
        return initialState();
    }

    @AutoReducer.Action(value = SearchActions.SEARCH, from = SearchActions.class)
    SearchState search(SearchState state, String query) {
        return SearchState.builder()
                .setLoading(state.loading())
                .setQuery(query)
                .setError(state.error())
                .setResult(state.result())
                .build();
    }

    @AutoReducer.Action(value = SearchActions.LOADING, from = SearchActions.class)
    SearchState loading(SearchState state) {
        return SearchState.builder()
                .setLoading(true)
                .setQuery(state.query())
                .setError(state.error())
                .setResult(state.result())
                .build();
    }

    @AutoReducer.Action(value = SearchActions.RESULT, from = SearchActions.class)
    SearchState result(SearchState state, List<PicturePost> result) {
        return SearchState.builder()
                .setLoading(false)
                .setQuery(state.query())
                .setError(state.error())
                .setResult(result)
                .build();
    }

    @AutoReducer.Action(value = SearchActions.ERROR, from = SearchActions.class)
    SearchState error(SearchState state, Throwable error) {
        return SearchState.builder()
                .setLoading(false)
                .setQuery(state.query())
                .setError(error)
                .setResult(state.result())
                .build();
    }

    public static SearchReducer create() {
        return new SearchReducerImpl();
    }
}
