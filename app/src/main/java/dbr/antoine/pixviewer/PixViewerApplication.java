package dbr.antoine.pixviewer;

import android.app.Application;
import android.util.Log;

import dbr.antoine.pixviewer.core.repositories.PictureRepository;
import dbr.antoine.pixviewer.core.repositories.PixabayPictureRepository;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by antoine on 7/7/17.
 */

public class PixViewerApplication extends Application {

    private static final String TAG = "PixViewerApplication";

    @Override
    public void onCreate() {
        super.onCreate();

        PictureRepository repository = new PixabayPictureRepository();
        repository.searchPictures("Great Wall Of China")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(pictures -> {
                Log.d(TAG, "result=" + pictures);
            },
            throwable -> {
                Log.e(TAG, "error=", throwable);
            });
    }
}
