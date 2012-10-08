package in.ultraneo.seriousandroiddeveloper;

import java.net.ContentHandler;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.MenuItem;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.Surface;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class About extends SherlockActivity {
	TextView ttitle, link,ttitle2;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		int rotation = getWindowManager().getDefaultDisplay().getOrientation();
		
		if (rotation == Surface.ROTATION_90) {
			setContentView(R.layout.aboutactivity_main2);
			
			
			link = (TextView) findViewById(R.id.textView2);
			SpannableString contentUnderline = new SpannableString(
					"http://www.facebook.com/groups/c2dmdeveloper/");
			contentUnderline.setSpan(new UnderlineSpan(), 0,
					contentUnderline.length(), 0);
			
			link.setText(contentUnderline);
			link.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					Intent i = new Intent(Intent.ACTION_VIEW);
					i.setData(Uri.parse("http://www.facebook.com/groups/c2dmdeveloper/"));
					startActivity(i);
					
				}
			});
			

		}
		ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
		
		if (rotation == Surface.ROTATION_0) {
			setContentView(R.layout.aboutactivity_main);
			

		}
		if (rotation == Surface.ROTATION_270) {
			setContentView(R.layout.aboutactivity_main2);
			link = (TextView) findViewById(R.id.textView2);
			SpannableString contentUnderline = new SpannableString(
					"http://www.facebook.com/groups/c2dmdeveloper/");
			contentUnderline.setSpan(new UnderlineSpan(), 0,
					contentUnderline.length(), 0);
			link.setText(contentUnderline);
			link.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					Intent i = new Intent(Intent.ACTION_VIEW);
					i.setData(Uri.parse("http://www.facebook.com/groups/c2dmdeveloper/"));
					startActivity(i);
					
				}
			});

		}
		ttitle2 = (TextView) findViewById(R.id.facebook);
		ttitle2.setTextSize(40);
		ttitle2.setTypeface(Typeface.createFromAsset(getAssets(),
				"social.ttf"));
		
		ttitle = (TextView) findViewById(R.id.textView1);

		ttitle.setTypeface(Typeface.createFromAsset(getAssets(),
				"font/type.ttf"));

		super.onCreate(savedInstanceState);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		 switch (item.getItemId()) {
	        
		    case android.R.id.home:
	        {
	        	finish();
	        	
	        	break;
	        }      
		        default:
		            return super.onOptionsItemSelected(item);
		    }
			return false;
	}

}
