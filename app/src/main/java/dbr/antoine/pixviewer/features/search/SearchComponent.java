package dbr.antoine.pixviewer.features.search;

import dagger.Subcomponent;

/**
 * Created by antoine on 7/8/17.
 */
@Subcomponent(modules = SearchModule.class)
public interface SearchComponent {
    void inject(SearchActivity activity);
}