package in.ultraneo.seriousandroiddeveloper;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.MenuItem;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ActivityUpdateItem extends SherlockActivity{
ImageView image;
TextView subjecttext,msgtext,rlinktext;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	     setContentView(R.layout.activityupdateitem);
	     ActionBar actionBar = getSupportActionBar();
	        actionBar.setDisplayHomeAsUpEnabled(true);
	     image = (ImageView)findViewById(R.id.icon);
	     subjecttext = (TextView)findViewById(R.id.subject);
	     msgtext = (TextView)findViewById(R.id.message);
	     rlinktext = (TextView)findViewById(R.id.reference);
	     
	     	Bundle bundle = getIntent().getExtras();
	        String subject = bundle.getString("head1");
	        String message = bundle.getString("head2");
	        String picture = bundle.getString("head3");
	        final String video = bundle.getString("head4");
	        final String rlink = bundle.getString("head5");
	        
	        setTitle(subject);
	        subjecttext.setText(subject);
	        
	        msgtext.setText(message);
	        
	     BigImageLoader imageLoader=new BigImageLoader(this);
	     imageLoader.DisplayImage(picture, image);
	     
	   
	     
	     
	     if(rlink.equals("0")){
	    	 rlinktext.setVisibility(View.GONE);
	     }else {
	     SpannableString contentUnderline = new SpannableString(
			"Reference: "+rlink);
	contentUnderline.setSpan(new UnderlineSpan(), 11,contentUnderline.length(),0);
	
	     
	     rlinktext.setText(contentUnderline);
	     rlinktext.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				String url = rlink;
				Intent i = new Intent(Intent.ACTION_VIEW);
				i.setData(Uri.parse(url));
				startActivity(i);
				
			}
		});
	     }
	     
	     if(video.equals("0"))
	     {
	    	 
	    	 
	    	 
	     }else
	     {
	    	 image.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					String url = video;
					Intent i = new Intent(Intent.ACTION_VIEW);
					i.setData(Uri.parse(url));
					startActivity(i);
					
				}
			});
	     }
	     
	     
	     
	     
	     
	     /*SpannableString contentUnderline = new SpannableString(
			"Reference: http://www.facebook.com/groups/c2dmdeveloper/");
	contentUnderline.setSpan(new UnderlineSpan(), 11,contentUnderline.length(),0);
	to uderline the text
	*/
	     
	     
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
