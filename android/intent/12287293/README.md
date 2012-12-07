stop an intent from starting a service
======================================

I have an Intent which starts a MusicService which connects to the internet and plays a stream:

    final Intent i = new Intent(MusicService.ACTION_URL);
    Uri uri = Uri.parse("http://31.3.242.244:8058");

    i.setData(uri);
    startService(i);

    progressDialog = ProgressDialog.show(fmActivity.this, "", "connecting...");
    progressDialog.setCancelable(true);
    
When the connection is established I have a Handler close the dialog box:

    static Handler closeHandler = new Handler() {
        public void handleMessage(Message msg) {
            if (progressDialog !=null) {
                progressDialog.dismiss();
            }
        }
    };    
    fmActivity.closeHandler.handleMessage(null); //from the MusicService
    
This all works great unless the connection hangs or the server is slow to connect. I need to be able to cancel the Intent from trying to start the MusicService with the back button. Checking for internet connection won't help because there is always a connection...the user needs to be able to cancel the call to connect because if the connection to the server fails for whatever reason it prevents the user from being able to load a different stream. I have searched all over and can't find out how to do this. Thanks.

[Source](http://stackoverflow.com/questions/12287293/stop-an-intent-from-starting-a-service/ "Source")

## Answer ##

You can stop the music service before dismissing the ProgressDialog inside your cancel's OnClickListener.

For instance:

    progressDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancel", new DialogInterface.OnClickListener() { 
                public void onClick(DialogInterface dialog, int which) {
                    stopService(i); // Stop the music service here
                    dialog.dismiss();
                }
    });
