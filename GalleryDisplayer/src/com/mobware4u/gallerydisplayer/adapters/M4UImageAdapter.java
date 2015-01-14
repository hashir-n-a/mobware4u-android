package com.mobware4u.gallerydisplayer.adapters;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;

import com.mobware4u.gallerydisplayer.R;
import com.mobware4u.gallerydisplayer.views.M4UImageView;
import com.squareup.picasso.Picasso;

/**
 * The Adapter for displaying pictures from gallery in a gridview
 */
public class M4UImageAdapter extends CursorAdapter {

	public M4UImageAdapter(Context context, Cursor c, boolean autoRequery) {
		super(context, c, autoRequery);
	}

	@Override
	public View newView(Context context, Cursor cursor, ViewGroup parent) {
	    LayoutInflater layoutInflater = LayoutInflater.from(context);
	    View contentView =  layoutInflater.inflate(R.layout.grid_item,  parent, false);
	    return contentView.findViewById(R.id.pictureM4UIV);
	}

	@Override
	public void bindView(View convertView, Context context, Cursor cursor) {
		M4UImageView imageView = (M4UImageView) convertView;
		int index = cursor.getColumnIndex(MediaStore.Images.Media._ID);
		long id = cursor.getLong(index);
		
		
		Picasso.with(context)
				.load(Uri.withAppendedPath(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, ""+ id))
				.resize(200, 200)
				.placeholder(R.drawable.ic_launcher)
				.error(R.drawable.ic_launcher)
				.centerCrop()
				.into(imageView);
	}
}
