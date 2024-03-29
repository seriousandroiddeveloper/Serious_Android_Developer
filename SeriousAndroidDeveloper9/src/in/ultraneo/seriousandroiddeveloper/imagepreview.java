package in.ultraneo.seriousandroiddeveloper;

import java.io.IOException;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.hardware.Camera;
import android.util.Log;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

@SuppressLint({ "NewApi", "NewApi", "NewApi", "NewApi", "NewApi", "NewApi", "NewApi", "NewApi", "NewApi", "NewApi", "NewApi", "NewApi" })
public class imagepreview extends SurfaceView implements SurfaceHolder.Callback {
	private SurfaceHolder mHolder;
    private Camera mCamera;
    String TAG = "yo";
    Context context;
    private int rotation;
	public imagepreview(Context context,Camera camera, int rotation) {
		super(context);
		mCamera = camera;
		this.context= context;
		this.rotation = rotation;

        // Install a SurfaceHolder.Callback so we get notified when the
        // underlying surface is created and destroyed.
        mHolder = getHolder();
        
        mHolder.addCallback(this);
        // deprecated setting, but required on Android versions prior to 3.0
        mHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
		// If your preview can change or rotate, take care of those events here.
        // Make sure to stop the preview before resizing or reformatting it.

        if (mHolder.getSurface() == null){
          // preview surface does not exist
          return;
        }

        // stop preview before making changes
        try {
            mCamera.stopPreview();
        } catch (Exception e){
          // ignore: tried to stop a non-existent preview
        }

        
        // set preview size and make any resize, rotate or
        // reformatting changes here
        setCameraDisplayOrientation(rotation, 0, mCamera);
        // start preview with new settings
        try {
            mCamera.setPreviewDisplay(mHolder);
            mCamera.startPreview();

        } catch (Exception e){
            Log.d(TAG, "Error starting camera preview: " + e.getMessage());
        }
		
        
		
		
		
		
	}
	
	
	
	public static void setCameraDisplayOrientation(int rotationx,
	         int cameraId, android.hardware.Camera camera) {
	     android.hardware.Camera.CameraInfo info = new android.hardware.Camera.CameraInfo();
	     android.hardware.Camera.getCameraInfo(cameraId, info);
	    // int rotation = activity.getWindowManager().getDefaultDisplay().getRotation();
	     int degrees = 0;
	     switch (rotationx) {
	         case Surface.ROTATION_0: degrees = 270; break;
	         case Surface.ROTATION_90: degrees = 0; break;
	         case Surface.ROTATION_180: degrees = 90; break;
	         case Surface.ROTATION_270: degrees = 180; break;
	     }

	     int result;
	     if (info.facing == Camera.CameraInfo.CAMERA_FACING_FRONT) {
	         result = (info.orientation + degrees) % 360;
	         result = (360 - result) % 360;  // compensate the mirror
	     } else {  // back-facing
	         result = (info.orientation - degrees + 360) % 360;
	     }
	     camera.setDisplayOrientation(result);
	 }

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		 try {
			 
	            mCamera.setPreviewDisplay(holder);
	            mCamera.startPreview();
	            
	        } catch (IOException e) {
	            Log.d(TAG, "Error setting camera preview: " + e.getMessage());
	        }
		
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		// TODO Auto-generated method stub
		
	}
	

}
