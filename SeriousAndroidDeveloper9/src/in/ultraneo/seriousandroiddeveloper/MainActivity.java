package in.ultraneo.seriousandroiddeveloper;




import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;

import android.os.Bundle;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Typeface;
import android.view.Display;
import android.view.View;

import android.view.Surface;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends SherlockActivity {
TextView ttitle;
Animation animation;
ImageView img;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        
     
        int rotation = getWindowManager().getDefaultDisplay().getOrientation();
        if(rotation==Surface.ROTATION_90)
        {
        	setContentView(R.layout.activity_main2);	
        	
        }
        if(rotation==Surface.ROTATION_0)
        {
        	setContentView(R.layout.activity_main);	
        	
        }
        if(rotation==Surface.ROTATION_270)
        {
        	setContentView(R.layout.activity_main2);	
        	
        }
        	 /*setContentView(R.layout.activity_main2);	
        }*/
        ttitle = (TextView)findViewById(R.id.textView1);
        ttitle.setTypeface(Typeface.createFromAsset(getAssets(), "font/type.ttf"));
        img =(ImageView)findViewById(R.id.imageView1);
        animation =AnimationUtils.loadAnimation(this,R.animator.animation);
        String [] filelist = fileList();
		int nooffile = filelist.length;
		if(nooffile==0)
		{
			new UltraFileaccess().Load_Data("GAuth","0",this);
			
			 new UltraFileaccess().Load_Data("POSTID","0",this);
			final CustomizeDialog dialog3 = new CustomizeDialog(this, R.layout.facebookdialog);
			TextView txt1 = (TextView)dialog3.findViewById(R.id.Authentication);
			TextView txt2 = (TextView)dialog3.findViewById(R.id.textView1);
			Button btn1 = (Button)dialog3.findViewById(R.id.button1);
			Button btn2 = (Button)dialog3.findViewById(R.id.button2);
			txt1.setText("App is Underdevelopment");
			txt2.setText("If you want to post query on Serious Android Developer,facebook/suggesstion or advice please go to query section of this application");
			btn1.setVisibility(View.INVISIBLE);
			btn2.setText("    dismiss    ");
			btn2.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					dialog3.dismiss();
					
				}
			});
			dialog3.show();
			
			dialog3.setOnDismissListener(new DialogInterface.OnDismissListener() {
				
				@Override
				public void onDismiss(DialogInterface dialog) {
					img.startAnimation(animation);
			        ttitle.startAnimation(animation);
					
				}
			});
			
		}else{
			 img.startAnimation(animation);
		        ttitle.startAnimation(animation);
			
			
		}
        
        
        
        
       
animation.setAnimationListener(new Animation.AnimationListener() {
			
			@Override
			public void onAnimationStart(Animation animation) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onAnimationRepeat(Animation animation) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onAnimationEnd(Animation animation) {
				try {
					Thread.sleep(500);
					
					finish();
					
					//Toast.makeText(MainActivity.this, "yo", Toast.LENGTH_SHORT).show();
					startActivity(new Intent(MainActivity.this,SoftwarePassionView.class));
					
				} catch (InterruptedException e) {
					
					
				}
				
			}
		});
        
        
        
    }

    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }*/
    
    public int getscrOrientation()
    {
    Display getOrient = getWindowManager().getDefaultDisplay();

    int orientation = getOrient.getOrientation();

    // Sometimes you may get undefined orientation Value is 0
    // simple logic solves the problem compare the screen
    // X,Y Co-ordinates and determine the Orientation in such cases
    if(orientation==Configuration.ORIENTATION_UNDEFINED){

    Configuration config = getResources().getConfiguration();
    orientation = config.orientation;

    
    if(orientation==Configuration.ORIENTATION_UNDEFINED){
    //if height and widht of screen are equal then
    // it is square orientation
    if(getOrient.getWidth()==getOrient.getHeight()){
    orientation = Configuration.ORIENTATION_SQUARE;
    }else{ //if widht is less than height than it is portrait
    if(getOrient.getWidth() < getOrient.getHeight()){
    orientation = Configuration.ORIENTATION_PORTRAIT;
    }else{ // if it is not any of the above it will defineitly be landscape
    orientation = Configuration.ORIENTATION_LANDSCAPE;
    }
    }
    }
    }
    return orientation; // return value 1 is portrait and 2 is Landscape Mode
    }
    
   
    
    
}
