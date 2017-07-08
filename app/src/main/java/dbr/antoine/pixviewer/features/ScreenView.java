package dbr.antoine.pixviewer.features;

/**
 * Created by antoine on 7/7/17.
 */

public interface ScreenView<T> {

    void showLoading();

    void showError(String message);

    void showResult(T result);
}
