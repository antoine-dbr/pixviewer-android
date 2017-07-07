package dbr.antoine.pixviewer.core.repositories;

import android.support.annotation.NonNull;

import org.json.JSONObject;

import java.util.List;

import dbr.antoine.pixviewer.core.deserializers.PicturePostDeserializer;
import dbr.antoine.pixviewer.core.models.PicturePost;
import dbr.antoine.pixviewer.core.services.PixabayImageService;
import io.reactivex.Single;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

/**
 * Created by antoine on 7/7/17.
 */

public class PixabayPictureRepository implements PictureRepository {

    private static final String API_KEY = "5782593-a9dbfeb6120e117b7e6efd04f";

    private final PixabayImageService pixabay;

    public PixabayPictureRepository() {
        final Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://pixabay.com/")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        pixabay = retrofit.create(PixabayImageService.class);
    }

    @Override
    public Single<List<PicturePost>> searchPictures(@NonNull String search) {
        return transform(pixabay.searchPictures(API_KEY, search));
    }

    @Override
    public Single<PicturePost> searchPictureById(@NonNull String pictureId) {
        return transform(pixabay.searchPictureById(API_KEY, pictureId))
            .map(pictures -> pictures.get(0));
    }

    private static Single<List<PicturePost>> transform(Single<ResponseBody> responseObservable) {
        return responseObservable.map(body -> body.string())
            .map(JSONObject::new)
            .map(json -> json.getJSONArray("hits"))
            .map(PicturePostDeserializer::from);
    }
}
