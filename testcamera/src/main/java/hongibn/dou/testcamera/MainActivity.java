package hongibn.dou.testcamera;

import android.hardware.Camera;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;

import camera.CameraAllCallback;
import camera.CameraView;
import camera.CameraWrapper;
import camera.ICameraView;


public class MainActivity extends AppCompatActivity implements CameraAllCallback {
    private FrameLayout mSurfaceView;
    ICameraView mCameraView;
    CameraWrapper mCamera;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
        mSurfaceView = new FrameLayout(this);
        setContentView(mSurfaceView);

        mCameraView = new CameraView(this);

        mSurfaceView.addView((View) mCameraView);
        mCamera = mCameraView.getCamera();
        mCamera.setPreviewSize(1640, 1480);
        mCamera.setPictureSize(1640, 1480);
        mCamera.setCameraAllCallback(this);
        mCamera.openCamera(1);

    }

    @Override
    public void onScreenOrientationChanged(int orientation, int pictureDegree, float fromDegree, float toDegree) {

    }

    @Override
    public void onAutoFocus(boolean success, Camera camera) {

    }

    @Override
    public void onError(int error, Camera camera) {

    }

    @Override
    public void onPictureTaken(byte[] data, Camera camera) {

    }

    @Override
    public void onPreviewFrame(byte[] data, Camera camera) {

    }

    @Override
    public void onShutter() {

    }
}
