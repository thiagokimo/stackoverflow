public class CheckBoxListView extends ListActivity implements OnItemClickListener{

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Mock data
        String[] values = new String[] { "Android", "iPhone", "WindowsMobile",
                "Blackberry", "WebOS", "Ubuntu", "Windows7", "Max OS X",
                "Linux", "OS/2" };

        //android's simple_list_item_multiple_choice is a CheckedTextView
        //try creating your own later ;)
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_multiple_choice, values);

        getListView().setOnItemClickListener(this);

        setListAdapter(adapter);

    }

    public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {

        CheckedTextView item = (CheckedTextView) arg1;

        //The change color logic is here!
        if(item.isChecked()) {
            item.setTextColor(Color.BLACK);
            item.setChecked(false);
        }
        else {
            item.setTextColor(Color.RED);
            item.setChecked(true);
        }

    }

}