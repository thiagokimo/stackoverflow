How to change a list item rendering according to its content?
=============================================================

[Source](http://stackoverflow.com/questions/12350711/how-to-change-a-list-item-rendering-according-to-its-content/ "Source")

I am using a ListFragment with a CursorAdapter to display a list of items. The XML layout is pretty simple: it only consists of the title, a description and the number of comments.

![info disposal](http://i.stack.imgur.com/5oTPP.png "pic1")

Instead, I would like to show the number of comments on the right side. If it is possible I would like to add an image or a colored box as a background frame. Further, I would like to change the image/color according to the number of comments.

![listview](http://i.stack.imgur.com/DGBAZ.png "pic2")

This is the layout file I am currently using.

      <?xml version="1.0" encoding="utf-8"?>
      <LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
        android:layout_width="fill_parent" android:layout_height="fill_parent"
        android:orientation="vertical" android:padding="@dimen/padding_small" >

        <TextView
            android:id="@+id/title"
            android:layout_width="fill_parent"
            android:layout_height="wrap_content" >
        </TextView>

        <TextView
            android:id="@+id/description"
            android:layout_width="fill_parent"
            android:layout_height="wrap_content" >
        </TextView>

        <TextView
            android:id="@+id/comments_count"
            android:layout_width="fill_parent"
            android:layout_height="wrap_content" >
        </TextView>

      </LinearLayout>
      
Here the CursorAdapter that I use ...

    public class CustomCursorAdapter extends CursorAdapter {

        private LayoutInflater mInflater;

        public CustomCursorAdapter(Context context, Cursor cursor, int flags) {
            super(context, cursor, flags);
            mInflater = LayoutInflater.from(context);
        }

        public void bindView(View view, Context context, Cursor cursor) {
            ViewHolder holder = (ViewHolder)view.getTag();
            if (holder == null) {
                holder = new ViewHolder();
                holder.title = (TextView)view.findViewById(R.id.title);
                holder.comments_count = (TextView)view.findViewById(R.id.comments_count);
                view.setTag(holder);
            }
            
            String title = cursor.getString(cursor.getColumnIndex(TITLE));
            holder.title.setText(title);
            int comments_count = cursor.getInt(cursor.getColumnIndex(COMMENTS_COUNT));
            holder.comments_count.setText(comments_count + "");
        }

        @Override
        public View newView(Context context, Cursor cursor, ViewGroup parent) {
            return mInflater.inflate(R.layout.list_item, parent, false);
        }

        private static class ViewHolder {
            TextView title;
            TextView comments_count;
        }

    }
    
Here is a color function I prepared according to Thiago Moreira Rocha's example implementation ...

    if (comments_count == 0) {
        holder.comments_count.getParent().setBackgroundColor(Color.WHITE);
    }
    else if (comments_count != 0) {
        float saturation = (comments_count * 15) / 100.f;
        // The value gets pinned if out of range.
        int color = Color.HSVToColor(new float[] {110f , saturation, 1f});
        holder.comments_count.getParent().setBackgroundColor(color);
    }
    
How would you implement the layout and the context sensitive behavior?

Note:

I created a second question to discuss the option to make the comment box clickable. Please refer to the new post if you want to add information to this topic.

## Answer ##

Inside your adapter's getView method(in your case, bindView) get the parent of the view that will be bound and update it's background color with View.setBackGroundColor!

    public void bindView(View view, Context context, Cursor cursor) {
      //usual stuff
      //...
      Color newColor = colorFunction(comments_count);//calculate your new color
      View v = (View)view.getParent();
      v.setBackgroundColor(newColor);
    }
    
In this example, I've created a list using ArrayAdapter, with a NAME followed by a random NUMBER. I wrapped this number inside a LinearLayout and according to it's value I change his parent layout.

Check my example below.

public class MainActivity extends ListActivity {

@Override
public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setListAdapter(new ColorAdapter(this, R.layout.row, mockData));
}

String[] mockData = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5", "Item 6",
        "Item 7", "Item 8", "Item 9", "Item 10", "Item 11", "Item 12" };

    public class ColorAdapter extends ArrayAdapter<String> {

        public ColorAdapter(Context context, int textViewResourceId,
                String[] objects) {
            super(context, textViewResourceId, objects);

        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            View row = convertView;

            if (row == null) {
                LayoutInflater inflater = getLayoutInflater();
                row = inflater.inflate(R.layout.row, parent, false);
            }

            TextView label = (TextView) row.findViewById(R.id.title);
            label.setText(mockData[position]);

            TextView desc = (TextView) row.findViewById(R.id.description);
            desc.setText("Description: "+ mockData[position]);

            TextView value = (TextView) row.findViewById(R.id.comments_count);

            //Here is the object you need to change the colors.
            LinearLayout background = (LinearLayout) value.getParent();

            Random random_number = new Random();
            int comments_count = random_number.nextInt(256);

            value.setText(comments_count+"");

            //Calculates a random color
            int newBackgroundColor = colorFunction(comments_count); 

            //Set the new background color on the comments_count parent
            background.setBackgroundColor(newBackgroundColor);

            return row;
        }

        private int colorFunction(int commentsNumber) {
            if (commentsNumber == 0) {
                return Color.WHITE;
            }
            else if(commentsNumber < 0) {
                return new Random().nextInt(256);
            }
            else {
                int color;
                Random rnd = new Random(); 
                color = Color.argb(commentsNumber, rnd.nextInt(commentsNumber), rnd.nextInt(256), rnd.nextInt(commentsNumber));

                return color;
            }
        }
    }
    
row.xml:

    <?xml version="1.0" encoding="utf-8"?>
        <LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:gravity="center"
        android:orientation="horizontal"
        android:padding="10dip" >

        <RelativeLayout
            android:layout_width="fill_parent"
            android:layout_height="fill_parent"
            android:layout_weight="0.7" >

            <TextView
                android:id="@+id/title"
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:layout_alignParentLeft="true"
                android:layout_centerVertical="true"
                android:text="Large Text"
                android:textAppearance="?android:attr/textAppearanceLarge" />

            <TextView
                android:id="@+id/description"
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:layout_alignParentLeft="true"
                android:layout_below="@+id/title"
                android:ellipsize="end"
                android:lines="1"
                android:text="Small Text"
                android:textAppearance="?android:attr/textAppearanceSmall" />
        </RelativeLayout>

        <LinearLayout
            android:layout_width="fill_parent"
            android:layout_height="wrap_content"
            android:layout_centerHorizontal="true"
            android:layout_centerVertical="true"
            android:layout_weight="1"
            android:background="@drawable/border"
            android:orientation="vertical" >

            <LinearLayout
                android:layout_width="fill_parent"
                android:layout_height="fill_parent"
                android:orientation="vertical" >

                <TextView
                    android:id="@+id/comments_count"
                    android:layout_width="fill_parent"
                    android:layout_height="fill_parent"
                    android:gravity="center"
                    android:padding="10dip"
                    android:text="Large Text"
                    android:textAppearance="?android:attr/textAppearanceLarge" />
            </LinearLayout>
        </LinearLayout>

    </LinearLayout>
    
border.xml

    <?xml version="1.0" encoding="utf-8"?>
        <shape xmlns:android="http://schemas.android.com/apk/res/android">
        <stroke android:width="4dp" android:color="#000000" />
        <solid android:color="#ffffff" />
        <padding android:left="15dp" android:top="15dp"
                android:right="15dp" android:bottom="15dp" />
        <corners android:radius="4dp" /> 
    </shape>
    
The resulting list(portrait and landscape) are below.

![result1](http://i.stack.imgur.com/jef5F.png "Result")
![result2](http://i.stack.imgur.com/SmXso.png "Result")

