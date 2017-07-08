package dbr.antoine.pixviewer.core.models;

import android.support.annotation.IntRange;
import android.support.annotation.NonNull;

import com.google.auto.value.AutoValue;

/**
 * Created by antoine on 7/7/17.
 */
@AutoValue
public abstract class PicturePostImpl implements PicturePost {

    public static Builder builder() {
        return new AutoValue_PicturePostImpl.Builder();
    }

    @AutoValue.Builder
    public abstract static class Builder {

        public abstract Builder setPostId(@NonNull String postId);

        public abstract Builder setAuthor(@NonNull String author);

        public abstract Builder setSmallPictureUrl(@NonNull String smallPictureUrl);

        public abstract Builder setLargePictureUrl(@NonNull String largePictureUrl);

        public abstract Builder setTotalFavorites(@IntRange(from = 0) int totalFavorites);

        public abstract Builder setTotalLikes(@IntRange(from = 0) int totalLikes);

        public abstract Builder setTotalComments(@IntRange(from = 0) int totalComments);

        public abstract PicturePostImpl build();
    }
}
