package dbr.antoine.pixviewer.core.models;

/**
 * Created by antoine on 7/7/17.
 */

public interface Post {

    String postId();

    String author();

    int totalFavorites();

    int totalLikes();

    int totalComments();
}
