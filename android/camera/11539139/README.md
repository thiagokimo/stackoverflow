Example of Camera preview using SurfaceTexture in Android
=========================================================

I am trying to render camera preview using SurfaceTexture. I read the document but unable to understand how it works.

Can anyone provide one sample example(very basic one) or link which uses SurfaceTexture to preview camera. I googled this but not found what I am looking for.

Thanks in advance.

[Source](http://stackoverflow.com/questions/11539139/example-of-camera-preview-using-surfacetexture-in-android/ "Source")

## Answer: ##

If you want to use the Camera with TextureSurface you can implement SurfaceTextureListener interface. You'll have to implement 4 methods:

1) onSurfaceTextureAvailable - Here you setup your camera

2)onSurfaceTextureSizeChanged - In your case, the Android's camera will handle this method

3)onSurfaceTextureDestroyed - Here you destroy all camera stuff.

4) onSurfaceTextureUpdated- Update your texture here when you have something to change!

Check the example below:

    public class MainActivity extends Activity implements SurfaceTextureListener{

      private Camera mCamera;
      private TextureView mTextureView;

      @Override
      protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mTextureView = new TextureView(this);
        mTextureView.setSurfaceTextureListener(this);

        setContentView(mTextureView);
      }

      @Override
      public void onSurfaceTextureAvailable(SurfaceTexture surface, int width, int height) {
        mCamera = Camera.open();

        Camera.Size previewSize = mCamera.getParameters().getPreviewSize();
        mTextureView.setLayoutParams(new FrameLayout.LayoutParams(
                previewSize.width, previewSize.height, Gravity.CENTER));

        try {
            mCamera.setPreviewTexture(surface);
        } catch (IOException t) {}

        mCamera.startPreview();

      }

      @Override
      public void onSurfaceTextureSizeChanged(SurfaceTexture surface, int width, int height) {
        // Ignored, the Camera does all the work for us
      }

      @Override
      public boolean onSurfaceTextureDestroyed(SurfaceTexture surface) {
        mCamera.stopPreview();
        mCamera.release();
        return true;
      }

      @Override
      public void onSurfaceTextureUpdated(SurfaceTexture surface) {
        // Update your view here!
      }
    }
    
One more thing: SurfaceTexture is available from API 14.