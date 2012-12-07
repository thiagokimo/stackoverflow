public class MainActivity extends ListActivity implements OnItemClickListener{

ArrayList<String> values;
ArrayAdapter<String> adapter;

@Override
public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    values = generateMockData(50);

    adapter = new ArrayAdapter<String>(
            this, android.R.layout.simple_list_item_1, values);

    setContentView(R.layout.activity_main);

    getListView().setAdapter(adapter);
    getListView().setOnItemClickListener(this);
}

private ArrayList<String> generateMockData(int number) {

    ArrayList<String> result = new ArrayList<String>();

    for(int i = 0; i < number; i++)
        result.add(""+i+" "+ (int)Math.random() * 13);

    return result;
}

private void removeListItem(View rowView, final int positon) {

    Animation anim = AnimationUtils.loadAnimation(this,
            android.R.anim.slide_out_right);
    anim.setDuration(500);
    rowView.startAnimation(anim);

    new Handler().postDelayed(new Runnable() {

        public void run() {

            values.remove(positon);//remove the current content from the array

            adapter.notifyDataSetChanged();//refresh you list

        }

    }, anim.getDuration());

}

public void onItemClick(AdapterView<?> arg0, View row, int position, long arg3) {

       if(position == YOUR_INDEX) //apply your conditions here!
          removeListItem(row,position);
}