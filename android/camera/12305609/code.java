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