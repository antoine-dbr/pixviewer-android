package dbr.antoine.pixviewer.core.repositories;

import android.support.annotation.NonNull;

import org.json.JSONArray;

import java.util.List;

import dbr.antoine.pixviewer.core.deserializers.PicturePostDeserializer;
import dbr.antoine.pixviewer.core.models.PicturePost;
import dbr.antoine.pixviewer.core.services.PixabayImageService;
import io.reactivex.Observable;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by antoine on 7/7/17.
 */

public class PixabayPictureRepository implements PictureRepository {

    private static final String API_KEY = "5782593-a9dbfeb6120e117b7e6efd04f";

    private final PixabayImageService pixabay;

    public PixabayPictureRepository() {
        final Retrofit retrofit = new Retrofit.Builder().baseUrl("https://pixabay.com/api").build();
        pixabay = retrofit.create(PixabayImageService.class);
    }

    @Override
    public Observable<List<PicturePost>> searchPictures(@NonNull String search) {
        return transform(pixabay.searchPictures(API_KEY, search));
    }

    @Override
    public Observable<PicturePost> searchPictureById(@NonNull String pictureId) {
        return transform(pixabay.searchPictureById(API_KEY, pictureId))
            .map(pictures -> pictures.get(0));
    }

    private static Observable<List<PicturePost>> transform(Observable<Response> responseObservable) {
        return responseObservable.map(response -> response.raw().body().string())
            .map(JSONArray::new)
            .map(PicturePostDeserializer::from);
    }
}
