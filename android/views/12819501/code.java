public class ListenActivity extends ListActivity {

  @Override
  public void onCreate(Bundle savedInstanceState) {
    //...your stuff

    String planName = getIntent().getExtras().getString(ListViewMessage);

    //...your stuff
  }
}