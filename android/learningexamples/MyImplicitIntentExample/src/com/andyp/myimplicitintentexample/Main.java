package com.andyp.myimplicitintentexample;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.widget.ImageView;

public class Main extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		ImageView iv = (ImageView) super.findViewById(R.id.imageView1);

		// checks to see if there is an image stream intent stream 
		// and will set imaveView to the image stream if so
		if(super.getIntent().getExtras().get(Intent.EXTRA_STREAM) != null){
			iv.setImageURI((Uri) super.getIntent().getExtras().get(Intent.EXTRA_STREAM));	
		}
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
