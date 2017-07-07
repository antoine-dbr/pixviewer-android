package dbr.antoine.pixviewer.core.repositories;

import android.support.annotation.NonNull;

import java.util.List;

import dbr.antoine.pixviewer.core.models.PicturePost;
import io.reactivex.Observable;

/**
 * Created by antoine on 7/7/17.
 */

public interface PictureRepository {

    Observable<List<PicturePost>> searchPictures(@NonNull String search);

    Observable<PicturePost> searchPictureById(@NonNull String pictureId);
}
