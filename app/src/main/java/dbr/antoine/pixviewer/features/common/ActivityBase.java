package dbr.antoine.pixviewer.features.common;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;
import dbr.antoine.pixviewer.PixViewerApplication;
import dbr.antoine.pixviewer.modules.ApplicationComponent;

/**
 * Created by antoine on 7/8/17.
 */

public abstract class ActivityBase extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setupComponent(PixViewerApplication.get(this));
        super.onCreate(savedInstanceState);
    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        ButterKnife.bind(this);
    }

    protected abstract void setupComponent(ApplicationComponent applicationComponent);
}
