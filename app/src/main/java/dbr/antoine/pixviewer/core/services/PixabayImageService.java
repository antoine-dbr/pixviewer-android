package dbr.antoine.pixviewer.core.services;

import io.reactivex.Single;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by antoine on 7/7/17.
 */

public interface PixabayImageService {

    @GET("api?per_page=200")
    Single<ResponseBody> searchPictures(@Query("key") String apiKey, @Query("q") String query);
}
