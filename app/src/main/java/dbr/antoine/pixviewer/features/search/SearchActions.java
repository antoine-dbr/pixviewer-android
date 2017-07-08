package dbr.antoine.pixviewer.features.search;

import com.yheriatovych.reductor.Action;
import com.yheriatovych.reductor.annotations.ActionCreator;

import java.util.List;

import dbr.antoine.pixviewer.core.models.PicturePost;

/**
 * Created by antoine on 7/7/17.
 */
@ActionCreator
interface SearchActions {

    String RESET = "RESET";
    String SEARCH = "SEARCH";
    String LOADING = "LOADING";
    String RESULT = "RESULT";
    String ERROR = "ERROR";

    @ActionCreator.Action(RESET)
    Action reset();

    @ActionCreator.Action(SEARCH)
    Action search(String query);

    @ActionCreator.Action(LOADING)
    Action loading();

    @ActionCreator.Action(RESULT)
    Action result(List<PicturePost> result);

    @ActionCreator.Action(ERROR)
    Action error(Throwable error);
}
