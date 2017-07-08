package dbr.antoine.pixviewer.core.repositories;

import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.yheriatovych.reductor.Store;

import org.json.JSONObject;

import java.util.List;

import dbr.antoine.pixviewer.core.deserializers.PicturePostDeserializer;
import dbr.antoine.pixviewer.core.models.PicturePost;
import dbr.antoine.pixviewer.core.services.PixabayImageService;
import dbr.antoine.pixviewer.reducers.search.SearchState;
import io.reactivex.Observable;
import io.reactivex.Single;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

/**
 * Created by antoine on 7/7/17.
 */

public class PixabayPictureRepository implements PictureRepository {

    private static final String API_KEY = "5782593-a9dbfeb6120e117b7e6efd04f";

    private final PixabayImageService pixabay;
    private final Store<SearchState> searchStateStore;

    public PixabayPictureRepository(@NonNull Store<SearchState> searchStateStore) {
        final Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://pixabay.com/")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        this.pixabay = retrofit.create(PixabayImageService.class);
        this.searchStateStore = searchStateStore;
    }

    @Override
    public Single<List<PicturePost>> searchPictures(@NonNull String search) {
        return pixabay.searchPictures(API_KEY, search)
            .map(body -> body.string())
            .map(JSONObject::new)
            .map(json -> json.getJSONArray("hits"))
            .map(PicturePostDeserializer::from);
    }

    @Override
    public Single<PicturePost> searchPictureById(@NonNull String pictureId) {
        return Observable.fromIterable(searchStateStore.getState().result())
            .filter(picture -> TextUtils.equals(pictureId, picture.postId()))
            .singleOrError();
    }
}
