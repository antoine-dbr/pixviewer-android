package dbr.antoine.pixviewer.features.search;

import android.support.annotation.Nullable;

import com.google.auto.value.AutoValue;

import java.util.List;

import dbr.antoine.pixviewer.core.models.PicturePost;

/**
 * Created by antoine on 7/7/17.
 */

@AutoValue
public abstract class SearchState {

    abstract String query();

    abstract boolean loading();

    abstract Throwable error();

    abstract List<PicturePost> result();

    public static Builder builder() {
        return new AutoValue_SearchState.Builder();
    }

    @AutoValue.Builder
    public abstract static class Builder {

        public abstract Builder setQuery(String query);

        public abstract Builder setLoading(boolean loading);

        public abstract Builder setError(@Nullable Throwable error);

        public abstract Builder setResult(@Nullable List<PicturePost> result);

        public abstract SearchState build();
    }
}
