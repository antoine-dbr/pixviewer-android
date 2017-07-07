package dbr.antoine.pixviewer.core.services;

import io.reactivex.Observable;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by antoine on 7/7/17.
 */

public interface PixabayImageService {

    @GET("/")
    Observable<Response> searchPictures(@Query("key") String apiKey, @Query("q") String query);

    @GET("/")
    Observable<Response> searchPictureById(@Query("key") String apiKey, @Query("id") String id);
}
