package dbr.antoine.pixviewer.core.repositories;

import android.support.annotation.NonNull;

import java.util.List;

import dbr.antoine.pixviewer.core.models.PicturePost;
import io.reactivex.Single;

/**
 * Created by antoine on 7/7/17.
 */

public interface PictureRepository {

    Single<List<PicturePost>> searchPictures(@NonNull String search);

    Single<PicturePost> searchPictureById(@NonNull String pictureId);
}
