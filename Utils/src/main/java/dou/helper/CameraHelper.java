package dou.helper;

import android.app.Activity;
import android.content.Context;
import android.graphics.ImageFormat;
import android.graphics.SurfaceTexture;
import android.hardware.Camera;
import android.util.Log;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.Iterator;
import java.util.List;

import dou.utils.DisplayUtil;

import static android.hardware.Camera.CameraInfo;
import static android.hardware.Camera.PreviewCallback;
import static android.hardware.Camera.getCameraInfo;
import static android.hardware.Camera.getNumberOfCameras;
import static android.hardware.Camera.open;

@SuppressWarnings("deprecation")
public class CameraHelper implements PreviewCallback {

    private Camera camera = null;
    private SurfaceView surfaceView;
    private SurfaceHolder surfaceHolder = null;
    private SurfaceTexture mSurfaceTexture;
    private PreviewFrameListener previewFrameListener;
    private Context context;
    private Camera.Size previewSize;

    private int cameraFacing = CameraInfo.CAMERA_FACING_FRONT;
    private int sw, sh;
    private int camera_max_width = 0;
    private int rotate = 0;

    public CameraHelper(Context context, CameraParams params) {
        this.context = context;
        assert params != null;
        this.surfaceView = params.surfaceView;
        this.camera_max_width = params.max_width;
        this.previewFrameListener = params.previewFrameListener;
        this.cameraFacing = params.firstCameraId;
        this.rotate = params.camera_ori;
        this.sw = DisplayUtil.getScreenWidthPixels(context);
        this.sh = DisplayUtil.getScreenHeightPixels(context);

        if (surfaceView != null) {
            surfaceHolder = surfaceView.getHolder();
            surfaceHolder.addCallback(new SurfaceHolder.Callback() {
                @Override
                public void surfaceCreated(SurfaceHolder holder) {
                    openCamera();
                }

                @Override
                public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
                    initCamera();
                }

                @Override
                public void surfaceDestroyed(SurfaceHolder holder) {
                    stopCamera();
                }
            });
        } else {
            openCamera();
        }
    }


    private void openCamera() {
        try {
            try {
                camera = open(cameraFacing);
            } catch (Exception e) {
                //某些设备摄像头无法开启,使用open(back),设置当前摄像头为后置模式
                cameraFacing = (cameraFacing == CameraInfo.CAMERA_FACING_FRONT ?
                        CameraInfo.CAMERA_FACING_BACK : CameraInfo.CAMERA_FACING_FRONT);
                if (camera == null) {
                    camera = open(CameraInfo.CAMERA_FACING_BACK);
                    cameraFacing = CameraInfo.CAMERA_FACING_BACK;
                }
            }

            if (surfaceView != null)
                camera.setPreviewDisplay(surfaceHolder);
            else {
                if (mSurfaceTexture == null) {
                    mSurfaceTexture = new SurfaceTexture(-1);
                }
                camera.setPreviewTexture(mSurfaceTexture);
            }
            initCamera();
        } catch (Exception e) {
            e.printStackTrace();
            if (null != camera) {
                camera.release();
                camera = null;
            }
        }
    }

    private void initCamera() {
        if (null != camera) {
            try {
                Camera.Parameters parameters = camera.getParameters();
                parameters.setPreviewFormat(ImageFormat.NV21);
                setOptimalPreviewSize(parameters, camera_max_width);

                setCameraDisplayOrientation((Activity) context, getCameraId(), camera);
                camera.setParameters(parameters);
                camera.cancelAutoFocus();
                startPreview();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void setOptimalPreviewSize(Camera.Parameters cameraParams,
                                       int targetWidth) {
        List<Camera.Size> supportedPreviewSizes = cameraParams.getSupportedPreviewSizes();

        if (null == supportedPreviewSizes) {
        } else {
            Camera.Size optimalSize = null;
            if (targetWidth == -1) {
                //大于640,且跟屏幕分辨率最接近的分辨率
                double minDiff = 1.7976931348623157E308D;
                Iterator mIterator = supportedPreviewSizes.iterator();
                while (mIterator.hasNext()) {
                    Camera.Size size = (Camera.Size) mIterator.next();
                    if ((double) Math.abs(size.width - 1000) < minDiff
                            && (size.width * sh == size.height * sw
                            || size.width * sw == size.height * sh)) {
                        optimalSize = size;
                        minDiff = (double) Math.abs(size.width - 1000);
                    }
                }
            }

            if (optimalSize == null) {
                targetWidth = targetWidth == -1 ? 640 : targetWidth;
                double minDiff = 1.7976931348623157E308D;
                Iterator mIterator = supportedPreviewSizes.iterator();
                while (mIterator.hasNext()) {
                    Camera.Size size = (Camera.Size) mIterator.next();
                    if ((double) Math.abs(size.width - targetWidth) < minDiff) {
                        optimalSize = size;
                        minDiff = (double) Math.abs(size.width - targetWidth);
                    }
                }
            }
            setPreviewSize(optimalSize);
            int iw = optimalSize.width;
            int ih = optimalSize.height;

            Log.d("CameraHelper", iw + ":" + ih + ":" + sh + ":" + sw);

            if (surfaceView != null) {
                if (iw * sh <= ih * sw) {
                    surfaceView.getLayoutParams().width = sh * iw / ih;
                    surfaceView.getLayoutParams().height = sh;
                } else {
                    surfaceView.getLayoutParams().width = sw;
                    surfaceView.getLayoutParams().height = sw * iw / ih;
                }

                surfaceView.requestLayout();
            }
            cameraParams.setPreviewSize(iw, ih);
        }
    }


    public Camera.Size getPreviewSize() {
        return previewSize;
    }

    public void stopCamera() {
        if (null != camera) {

            if (mSurfaceTexture != null) {
                mSurfaceTexture.release();
                mSurfaceTexture = null;
            }

            camera.setPreviewCallbackWithBuffer(null);
            camera.stopPreview();
            camera.release();
            camera = null;
        }
    }

    private void setPreviewSize(Camera.Size previewSize) {
        this.previewSize = previewSize;
    }

    public int switchCameraId() {
        if (!hasFacing(CameraInfo.CAMERA_FACING_FRONT)) return cameraFacing;
        cameraFacing = (cameraFacing == CameraInfo.CAMERA_FACING_FRONT ?
                CameraInfo.CAMERA_FACING_BACK : CameraInfo.CAMERA_FACING_FRONT);
        stopCamera();
        openCamera();
        return cameraFacing;
    }

    public int getCameraId() {
        return cameraFacing;
    }

    public void startPreview() {
        camera.startPreview();
        camera.setPreviewCallbackWithBuffer(this);
        camera.addCallbackBuffer(new byte[((previewSize.width * previewSize.height) * ImageFormat.getBitsPerPixel(ImageFormat.NV21)) / 8]);
    }

    public void stopPreview() {
        if (camera != null)
            camera.stopPreview();
    }

    public int getRotate() {
        return rotate;
    }

    public void setRotate(int rotate) {
        this.rotate = rotate;
    }

    public interface PreviewFrameListener {
        void onPreviewFrame(byte[] data, Camera camera);
    }

    @Override
    public void onPreviewFrame(byte[] data, Camera camera) {
        camera.addCallbackBuffer(data);
        if (previewFrameListener != null)
            previewFrameListener.onPreviewFrame(data, camera);
    }


    private boolean hasFacing(int facing) {
        CameraInfo info = new CameraInfo();
        for (int i = 0; i < getNumberOfCameras(); i++) {
            getCameraInfo(i, info);

            if (info.facing == facing) {
                return true;
            }
        }
        return false;
    }

    private void setCameraDisplayOrientation(Activity activity,
                                             int cameraId, android.hardware.Camera camera) {
        android.hardware.Camera.CameraInfo info =
                new android.hardware.Camera.CameraInfo();
        android.hardware.Camera.getCameraInfo(cameraId, info);
        int rotation = activity.getWindowManager().getDefaultDisplay()
                .getRotation();
        int degrees = 0;
        switch (rotation) {
            case Surface.ROTATION_0:
                degrees = 0;
                break;
            case Surface.ROTATION_90:
                degrees = 90;
                break;
            case Surface.ROTATION_180:
                degrees = 180;
                break;
            case Surface.ROTATION_270:
                degrees = 270;
                break;
        }

        int result;
        if (info.facing == Camera.CameraInfo.CAMERA_FACING_FRONT) {
            result = (info.orientation + degrees) % 360;
            result = (360 - result) % 360;  // compensate the mirror
        } else {  // back-facing
            result = (info.orientation - degrees + 360) % 360;
        }
        if (getRotate() == -1) {
            setRotate(result);
        } else {
            result = getRotate();
        }
        camera.setDisplayOrientation(result);
    }
}
