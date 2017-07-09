package dbr.antoine.pixviewer.features.search;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.OnTextChanged;
import dbr.antoine.pixviewer.R;
import dbr.antoine.pixviewer.core.models.PicturePost;
import dbr.antoine.pixviewer.features.common.ActivityBase;
import dbr.antoine.pixviewer.features.viewer.ViewerActivity;
import dbr.antoine.pixviewer.modules.ApplicationComponent;

public class SearchActivity extends ActivityBase implements SearchView {

    @BindView(R.id.search_layout) View searchLayout;
    @BindView(R.id.loading_layout) View loadingLayout;
    @BindView(R.id.error_layout) View errorLayout;
    @BindView(R.id.search_result_layout) View resultLayout;

    @BindView(R.id.search_edittext) EditText searchEditText;
    @BindView(R.id.search_button) Button searchButton;

    @BindView(R.id.grid_view) RecyclerView gridView;

    @Inject SearchPresenter presenter;
    @Inject InputMethodManager imm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activivity_search);

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
            searchEditText.setText(""); // TODO: manage this EditText via Redux
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
        imm.hideSoftInputFromWindow(searchEditText.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
    }

    @Override
    public void showQuery(String query) {
        searchButton.setClickable(!TextUtils.isEmpty(query));
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

        renderGrid(result);
    }

    private void renderGrid(List<PicturePost> result) {
        final SearchPictureAdapter.Listener listener = pictureId -> startActivity(ViewerActivity.create(this, pictureId));

        gridView.setLayoutManager(new GridLayoutManager(this, 3));
        gridView.setAdapter(new SearchPictureAdapter(this, result, listener));
    }

    @Override
    protected void setupComponent(ApplicationComponent applicationComponent) {
        applicationComponent.plus(new SearchModule(this)).inject(this);
    }
}
