package dbr.antoine.pixviewer;

import android.app.Application;
import android.content.Context;
import android.support.annotation.NonNull;

import dbr.antoine.pixviewer.modules.ApplicationComponent;
import dbr.antoine.pixviewer.modules.ApplicationModule;
import dbr.antoine.pixviewer.modules.DaggerApplicationComponent;
import dbr.antoine.pixviewer.modules.RepositoryModule;

/**
 * Created by antoine on 7/7/17.
 */

public class PixViewerApplication extends Application {

    private ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        installDagger();
    }

    private void installDagger() {
        applicationComponent = DaggerApplicationComponent.builder()
            .applicationModule(new ApplicationModule(this))
            .repositoryModule(new RepositoryModule())
            .build();
        applicationComponent.inject(this);
    }

    public static ApplicationComponent get(@NonNull Context context) {
        return ((PixViewerApplication) context.getApplicationContext()).applicationComponent;
    }
}
