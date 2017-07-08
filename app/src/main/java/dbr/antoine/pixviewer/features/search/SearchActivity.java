package dbr.antoine.pixviewer.features.search;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.view.View;
import android.widget.Button;

import com.yheriatovych.reductor.Store;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTextChanged;
import dbr.antoine.pixviewer.R;
import dbr.antoine.pixviewer.core.models.PicturePost;
import dbr.antoine.pixviewer.core.repositories.PixabayPictureRepository;

public class SearchActivity extends AppCompatActivity implements SearchView {

    private static final String TAG = "SearchActivity";

    @BindView(R.id.search_layout) View searchLayout;
    @BindView(R.id.loading_layout) View loadingLayout;
    @BindView(R.id.error_layout) View errorLayout;
    @BindView(R.id.search_result_layout) View resultLayout;

    @BindView(R.id.search_button) Button searchButton;

    private SearchPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activivity_search);
        ButterKnife.bind(this);

        presenter = new SearchPresenter(this, Store.create(SearchReducer.create()), new PixabayPictureRepository());
        presenter.register();
    }

    @Override
    protected void onDestroy() {
        presenter.unregister();
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
        if (searchLayout.getVisibility() == View.GONE) {
            presenter.reset();
        }
        else {
            super.onBackPressed();
        }
    }

    @OnTextChanged(value = R.id.search_edittext, callback = OnTextChanged.Callback.AFTER_TEXT_CHANGED)
    void onSearchEdited(Editable editable) {
        presenter.searching(editable.toString());
    }

    @OnClick(R.id.search_button)
    void onSearchClicked(View view) {
        presenter.search();
    }

    @Override
    public void allowSearch(boolean allow) {
        searchButton.setClickable(allow);
    }

    @Override
    public void showSearch() {
        searchLayout.setVisibility(View.VISIBLE);
        loadingLayout.setVisibility(View.GONE);
        errorLayout.setVisibility(View.GONE);
        resultLayout.setVisibility(View.GONE);
    }

    @Override
    public void showLoading() {
        searchLayout.setVisibility(View.GONE);
        loadingLayout.setVisibility(View.VISIBLE);
        errorLayout.setVisibility(View.GONE);
        resultLayout.setVisibility(View.GONE);
    }

    @Override
    public void showError(String message) {
        searchLayout.setVisibility(View.GONE);
        loadingLayout.setVisibility(View.GONE);
        errorLayout.setVisibility(View.VISIBLE);
        resultLayout.setVisibility(View.GONE);
    }

    @Override
    public void showResult(List<PicturePost> result) {
        searchLayout.setVisibility(View.GONE);
        loadingLayout.setVisibility(View.GONE);
        errorLayout.setVisibility(View.GONE);
        resultLayout.setVisibility(View.VISIBLE);
    }
}
