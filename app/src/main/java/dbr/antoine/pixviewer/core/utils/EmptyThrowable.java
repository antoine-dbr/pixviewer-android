package dbr.antoine.pixviewer.core.utils;

/**
 * Created by antoine on 7/7/17.
 */

public class EmptyThrowable extends Throwable {

    public static boolean isEmpty(Throwable throwable) {
        return throwable instanceof EmptyThrowable;
    }
}
