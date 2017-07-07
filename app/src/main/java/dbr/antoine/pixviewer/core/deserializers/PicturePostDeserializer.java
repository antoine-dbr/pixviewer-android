package dbr.antoine.pixviewer.core.deserializers;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import dbr.antoine.pixviewer.core.models.PicturePost;
import dbr.antoine.pixviewer.core.models.PicturePostImpl;

/**
 * Created by antoine on 7/7/17.
 */

public final class PicturePostDeserializer {

    PicturePostDeserializer() {
    }

    public static List<PicturePost> from(JSONArray json) throws JSONException {
        final List<PicturePost> picturePosts = new ArrayList<>(json.length());
        for (int idx = 0; idx < json.length(); idx++) {
            final JSONObject entry = json.getJSONObject(idx);
            picturePosts.add(PicturePostImpl.builder()
                .setPostId(entry.getString("id"))
                .setAuthor(entry.getString("user"))
                .setSmallPictureUrl(entry.getString("previewURL"))
                .setLargePictureUrl(entry.getString("webformatURL"))
                .setTotalComments(entry.getInt("comments"))
                .setTotalFavorites(entry.getInt("favorites"))
                .setTotalLikes(entry.getInt("likes"))
                .build());
        }
        return picturePosts;
    }
}
