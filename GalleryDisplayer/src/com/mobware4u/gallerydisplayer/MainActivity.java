package com.mobware4u.gallerydisplayer;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.GridView;
import android.widget.Toast;

import com.mobware4u.gallerydisplayer.adapters.M4UImageAdapter;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		GridView gridView = (GridView)findViewById(R.id.galleryGV);
		
		Cursor cursor = getCursorForGalleryImages();
		if( null != cursor && cursor.getCount() > 0 ) {
			// images present in gallery. display it
			gridView.setAdapter(new M4UImageAdapter(this, cursor, true));
		} else {
			// no images found in gallery. inform user
		    Toast.makeText(
		    			this, 
		    			getString(R.string.no_images_found), 
		    			Toast.LENGTH_SHORT).show();	
		}
	}
	
	/**
	 * Get the Gallery Images Cursor
	 * @return Cursor - cursor pointing to first image in gallery
	 */
	private Cursor getCursorForGalleryImages() {
		String[] proj = { MediaStore.Images.Media.DATA, MediaStore.Images.Media._ID};
		Cursor cursor = getContentResolver().query(
												MediaStore.Images.Media.EXTERNAL_CONTENT_URI, 
												proj, null, null, null);
		
		cursor.moveToFirst();
		return cursor;
	}
}
