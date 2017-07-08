package dbr.antoine.pixviewer.features.search;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.List;

import dbr.antoine.pixviewer.R;
import dbr.antoine.pixviewer.core.models.PicturePost;

/**
 * Created by antoine on 7/8/17.
 */

class PictureAdapter extends RecyclerView.Adapter<PictureAdapter.ViewHolder> {

    interface Listener {
        void onClick(String pictureId);
    }

    private final Context context;
    private final LayoutInflater inflater;
    private final List<PicturePost> pictures;
    private final Listener listener;

    PictureAdapter(@NonNull Context context, @NonNull List<PicturePost> pictures, @NonNull Listener listener) {
        this.context = context;
        this.inflater = LayoutInflater.from(context);
        this.pictures = pictures;
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = inflater.inflate(R.layout.item_image, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final PicturePost picture = pictures.get(position);
        holder.image.setTag(picture.postId());
        Picasso.with(context).load(picture.smallPictureUrl()).into(holder.image);
    }

    @Override
    public int getItemCount() {
        return pictures.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView image;

        ViewHolder(View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            listener.onClick((String) image.getTag());
        }
    }
}
