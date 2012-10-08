package in.ultraneo.seriousandroiddeveloper;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.MenuItem;


import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class CamerabyIntent extends SherlockActivity {
Button btn1;
ImageView img1;
String mCurrentPhotoPath;
Bitmap bitmap;

private AlbumStorageDirFactory mAlbumStorageDirFactory = null;
static final String BITMAP_STORAGE_KEY = "viewbitmap";

static final String IMAGEVIEW_VISIBILITY_STORAGE_KEY = "imageviewvisibility";

///
private String getAlbumName() {
	return "album";
	
}


private File getAlbumDir() {
	File storageDir = null;

	if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
		
		storageDir = mAlbumStorageDirFactory.getAlbumStorageDir("album");

		if (storageDir != null) {
			if (! storageDir.mkdirs()) {
				if (! storageDir.exists()){
					Log.d("CameraSample", "failed to create directory");
					return null;
				}
			}
		}
		
	} else {
		Log.v("APP", "External storage is not mounted READ/WRITE.");
	}
	
	return storageDir;
}

private File createImageFile() throws IOException {
	// Create an image file name
	String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
	String imageFileName = "temp";
	File albumF = getAlbumDir();
	File imageF = File.createTempFile(imageFileName,".jpg", albumF);
	Log.i("uo", imageF.getAbsolutePath());
	
	return imageF;
}

private File setUpPhotoFile() throws IOException {
	
	File f = createImageFile();
	mCurrentPhotoPath = f.getAbsolutePath();
	
	return f;
}



////
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
        setContentView(R.layout.camerabyintent);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        btn1 = (Button)findViewById(R.id.button1);
        img1 = (ImageView)findViewById(R.id.imageView1);
        
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.FROYO) {
			mAlbumStorageDirFactory = new FroyoAlbumDirFactory();
		} else {
			mAlbumStorageDirFactory = new BaseAlbumDirFactory();
		}
        
        
        btn1.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
				File f = null;
				
				try {
					
					
					
					f = setUpPhotoFile();
					mCurrentPhotoPath = f.getAbsolutePath();
					takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(f));
				} catch (IOException e) {
					e.printStackTrace();
					f = null;
					mCurrentPhotoPath = null;
				}
				

		
							
		

			startActivityForResult(takePictureIntent,1);
				
			}
		});
        
    }
    
    

    @Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    	if (resultCode == RESULT_OK) {
			handleSmallCameraPhoto(data);
		}
	}
    private void handleSmallCameraPhoto(Intent intent) {
    	/*Bitmap bmp = BitmapFactory.decodeFile(mCurrentPhotoPath);
    	img1.setImageBitmap(bmp);*/
    	setPic();
		
		
	}

    private void setPic() {

				int targetW = img1.getWidth();
		int targetH = img1.getHeight();

		
		
		BitmapFactory.Options bmOptions = new BitmapFactory.Options();
		bmOptions.inJustDecodeBounds = true;
		BitmapFactory.decodeFile(mCurrentPhotoPath, bmOptions);
		int photoW = bmOptions.outWidth;
		int photoH = bmOptions.outHeight;
		
		
		int scaleFactor = 1;
		if ((targetW > 0) || (targetH > 0)) {
			scaleFactor = Math.min(photoW/targetW, photoH/targetH);	
		}

		
		bmOptions.inJustDecodeBounds = false;
		bmOptions.inSampleSize = scaleFactor;
		bmOptions.inPurgeable = true;

		
		bitmap = BitmapFactory.decodeFile(mCurrentPhotoPath, bmOptions);
		
		img1.setImageBitmap(bitmap);
		img1.setVisibility(View.VISIBLE);
		
	}


	@Override
	protected void onRestoreInstanceState(Bundle outState) {
		try {
			bitmap = outState.getParcelable(BITMAP_STORAGE_KEY);
			
			img1.setImageBitmap(bitmap);
			img1.setVisibility(
					outState.getBoolean(IMAGEVIEW_VISIBILITY_STORAGE_KEY) ? 
							ImageView.VISIBLE : ImageView.GONE
			);
			
			
			}catch(Exception e)
			{
				
			}
		
		super.onSaveInstanceState(outState);
	}


	@Override
	protected void onSaveInstanceState(Bundle outState) {
		outState.putParcelable(BITMAP_STORAGE_KEY, bitmap);
		outState.putBoolean(IMAGEVIEW_VISIBILITY_STORAGE_KEY, (bitmap != null) );
		
		super.onSaveInstanceState(outState);
	}
	
    
}
