package com.mobware4u.gallerydisplayer.views;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * Create a squared imageview to display each image
 */
public class M4UImageView extends ImageView {

	public M4UImageView(Context context) {
		super(context);
	}

	public M4UImageView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public M4UImageView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		setMeasuredDimension(getMeasuredWidth(), getMeasuredWidth()); 
	}
}
