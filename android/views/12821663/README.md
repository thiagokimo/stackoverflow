ListView + CheckBox =?
===============================

[Source](http://stackoverflow.com/questions/12821663/listview-checkbox/ "Source")

I need :

ListView with

items { CheckBox, then TextView } ;
when you press on the CheckBox , item should change it's color.

How can I get it ?

P.S.

In other words I need a ListView with CheckBoxes like in Gmail app

## Answer ##

The answer is quite simple! There's a component called CheckedTextView which is a combination of a TextView and a CheckBox. This component might simplify your logic, leaving you free to do modify anything you want in your list's OnItemClickListener!

I've wrote an example for you:

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
      
