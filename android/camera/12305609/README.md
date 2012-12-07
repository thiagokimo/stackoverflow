How to take and save a picture on Android phone without press the save button?
==============================================================================

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
    
