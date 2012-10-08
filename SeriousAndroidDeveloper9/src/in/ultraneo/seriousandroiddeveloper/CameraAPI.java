package in.ultraneo.seriousandroiddeveloper;



import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.MenuItem;

import android.hardware.Camera;
import android.hardware.Camera.PictureCallback;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;


import android.app.Activity;
import android.content.Intent;

import android.view.Menu;
import android.view.Surface;
import android.view.View;

import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;


public class CameraAPI extends SherlockActivity {
	Camera camer;
	TextView txt1,txt2;
	private imagepreview mPreview;
	 FrameLayout preview;
	Button btn1;
	 PictureCallback mPicture;
	 
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
	 
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cameraapilayout);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        preview = (FrameLayout) findViewById(R.id.camera_preview);
        int rotation = getWindowManager().getDefaultDisplay().getOrientation();
        /*if(rotation==Surface.ROTATION_90)
        {
        	setContentView(R.layout.horizontal2);
        	preview = (FrameLayout) findViewById(R.id.frameLayout1);
        	
        }
        if(rotation==Surface.ROTATION_0)
        {
        	
        	
        }
        if(rotation==Surface.ROTATION_270)
        {
        	setContentView(R.layout.horizontal2);
         preview = (FrameLayout) findViewById(R.id.frameLayout1);
        	
        }*/
        btn1 =(Button)findViewById(R.id.button1);
       
        
       
        
        camer = getCameraInstance();
        
       
        txt1 =(TextView)findViewById(R.id.textView1);
        txt2 =(TextView)findViewById(R.id.textView2);
        
        
        mPreview = new imagepreview(this, camer,rotation);
        
        preview.addView(mPreview);
        
       btn1.setOnClickListener(new View.OnClickListener() {
		
		@Override
		public void onClick(View v) {
			 camer.takePicture(null, null, mPicture);
			
		}
	});
        
         mPicture = new PictureCallback() {

        @Override
        public void onPictureTaken(byte[] data, Camera camera) {

            File pictureFile = getOutputMediaFile(MediaStore.Files.FileColumns.MEDIA_TYPE_IMAGE);
            if (pictureFile == null){
               // Log.d("s", "Error creating media file, check storage permissions: ");
                return;
            }

            try {
                FileOutputStream fos = new FileOutputStream(pictureFile);
                fos.write(data);
                fos.close();
            } catch (FileNotFoundException e) {
              //  Log.d(TAG, "File not found: " + e.getMessage());
            } catch (IOException e) {
               // Log.d(TAG, "Error accessing file: " + e.getMessage());
            }
        }

		private File getOutputMediaFile(int mediaTypeImage) {
			// To be safe, you should check that the SDCard is mounted
		    // using Environment.getExternalStorageState() before doing this.

		    File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(
		              Environment.DIRECTORY_PICTURES), "MyCameraApp");
		    // This location works best if you want the created images to be shared
		    // between applications and persist after your app has been uninstalled.

		    // Create the storage directory if it does not exist
		    if (! mediaStorageDir.exists()){
		        if (! mediaStorageDir.mkdirs()){
		            //Log.d("MyCameraApp", "failed to create directory");
		            return null;
		        }
		    }

		    // Create a media file name
		    String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
		    File mediaFile;
		    Toast.makeText(getApplicationContext(), mediaStorageDir.getPath(), Toast.LENGTH_SHORT).show();
		        mediaFile = new File(mediaStorageDir.getPath() + File.separator +
		        "IMG_"+ timeStamp + ".jpg");
		    

		    return mediaFile;
		}

		
    };
        
        
 
        
    }

    
    
    
@Override
	protected void onStop() {
		camer.release();
		super.onStop();
	}

	// get camera
    public static Camera getCameraInstance(){
        Camera c = null;
        try {
            c = Camera.open(); // attempt to get a Camera instance
        }
        catch (Exception e){
            
        }
        return c; // returns null if camera is unavailable
    }
    
    
}
