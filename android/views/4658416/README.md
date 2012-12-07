How to apply android layout animation only to children above a certain index?
=============================================================================

I have a ListView containing a series of notes.

Currently I use a layout animation to slide all the notes in from the side when the list first loads; this works perfectly.

However, I'm trying to figure out how to apply a layout animation only to list items below a certain point. Say I delete an item on the list: I'd like all items below it to shift up into the deleted note's old spot.

I've tried finding a way to customize the animation delays or interpolators by child index but haven't found anything appropriate for this location. Is there a way to do this using a custom layout animation (such as extending LayoutAnimationController) or would I have to do this low level and animate each view individually?

Any suggestions?

[Source](http://stackoverflow.com/questions/4658416/how-to-apply-android-layout-animation-only-to-children-above-a-certain-index/ "Source")

## Answer ##

Create your animation and call it in your list OnItemClickListener. After that you might use the adapter's notifyDataSetChanged to refresh the list content.

In this example, I created a method called removeListItem with receives the row you want to remove and the position of that row in you list content array.

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
    
