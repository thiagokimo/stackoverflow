How I can fill my ListView in Android
=====================================

[Source](http://stackoverflow.com/questions/12819501/how-i-can-fill-my-listview-in-android/ "Source")

I new in the Android Programming and I want built a digital Schoolplan. For this I use a few Activities. I want create a plan and list them in a ListView that the User can click in the ListView on this Control and see all Homeworks and so.

Here is my Idea:

![img](http://i.stack.imgur.com/Xfy0l.jpg "img")

I build a Activity for the CreateMain:

    public void createPlan(View view) {
            String PlanName;

            Intent intent = new Intent(this,ListenActivity.class);

            EditText planName = (EditText)findViewById(R.id.editText1);

            PlanName = planName.getText().toString();

            intent.putExtra(ListViewMessage, PlanName); 

            startActivity(intent);

    }
    
And a Activity for show the create data in a ListView

    import android.os.Bundle;
    import android.view.Menu;
    import java.util.List;
    import android.app.ListActivity;
    import android.widget.ArrayAdapter;

    public class ListenActivity extends ListActivity {

        private CommentsDataSource datasource;

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_open);

            datasource = new CommentsDataSource(this);
            datasource.open();

            List<Comment> values = datasource.getAllComments();

            // Use the SimpleCursorAdapter to show the
            // elements in a ListView
            ArrayAdapter<Comment> adapter = new ArrayAdapter<Comment>(this,
                android.R.layout.simple_list_item_1, values);
            setListAdapter(adapter);

            Comment comment = null;

              String[] comments = new String[] { "t1", "t2", "t3" };

              comment = datasource.createComment(comments[1]); 

              adapter.add(comment);

            adapter.notifyDataSetChanged();

        }

        @Override
        public boolean onCreateOptionsMenu(Menu menu) {
            getMenuInflater().inflate(R.menu.activity_list, menu);
            return true;
        }
    }
    
But I don't know how I can use the data from the activity before for this activity in my ListView. I want to use a SQLite database but it don't work :(

## Answer ##

There are few ways to pass data through Activities. Since you stored your information in a Bundle I will answer following that same logic.

If you didn't notice, you stored your information in the Intent's Extra Bundle in your createPlan method. After that, you started that Intent.

According to the official documentation: "...extras -- This is a Bundle of any additional information. This can be used to provide extended information to the component. For example, if we have a action to send an e-mail message, we could also include extra pieces of data here to supply a subject, body, etc..."

So, to get that information you might use getIntent().getExtras() inside your ListenActivity to take back your Bundle and then you pass the key you used to store the plan's name using getIntent().getExtras().getString(your_key).

For example:

    public class ListenActivity extends ListActivity {

      @Override
      public void onCreate(Bundle savedInstanceState) {
        //...your stuff

        String planName = getIntent().getExtras().getString(ListViewMessage);

        //...your stuff
      }
    }
    
I don't recommend the use of SQLite to store small amount of data. If you really want to use it, there are very nice examples [here](http://www.vogella.com/articles/AndroidSQLite/article.html#databasetutorial "link").