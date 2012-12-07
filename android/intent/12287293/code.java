progressDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancel", new DialogInterface.OnClickListener() { 
            public void onClick(DialogInterface dialog, int which) {
                stopService(i); // Stop the music service here
                dialog.dismiss();
            }
});