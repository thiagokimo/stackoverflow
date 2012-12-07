How to take and save a picture on Android phone without press the save button?
==============================================================================

I'm developing an android app for the visually-impaired people, so it will be very convenient for them if I can take and save the picture automatically without pressing the save button after taking a picture. I'm looking for API from android developer website, but I didn't find any API which is helpful for me. Anyone who has any idea about how to achieve this goal?

[Source](http://stackoverflow.com/questions/12305609/how-to-take-and-save-a-picture-on-android-phone-without-press-the-save-button "Source")

## Answer: ##

You could add the steps to save the image inside the PictureCallBack.onPictureTaken of your own Camera Activity.

Here's an example:

    @Override
    public void onPictureTaken(byte[] imageData, Camera camera) {
        //Use imageData[] and save wherever you want
        File imagefile = new File(filepath); // set a file path
        FileOutputStream fos = new FileOutputStream(imagefile);
        try {
          fos.write(imageData);
          fos.flush();
        } catch(Exception e) {
           //Something wrong happened
        } finally {
            fos.close();
        }
    }
    
Remember to add in your AndroidManifest the permission:

<code> `<uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />` </code>