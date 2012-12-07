package com.kimo.liststackoverflow;

import java.util.Random;

import android.app.ListActivity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends ListActivity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setListAdapter(new ColorAdapter(this, R.layout.row, mockData));
	}

	String[] mockData = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5",
			"Item 6", "Item 7", "Item 8", "Item 9", "Item 10", "Item 11",
			"Item 12" };

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
			desc.setText("Description: " + mockData[position]);

			TextView value = (TextView) row.findViewById(R.id.comments_count);

			// Here is the object you need to change the colors.
			LinearLayout background = (LinearLayout) value.getParent();

			Random random_number = new Random();
			int comments_count = random_number.nextInt(256);

			value.setText(comments_count + "");

			// Calculates a random color
			int newBackgroundColor = colorFunction(comments_count);

			// Set the new background color on the comments_count parent
			background.setBackgroundColor(newBackgroundColor);

			return row;
		}

		private int colorFunction(int commentsNumber) {
			if (commentsNumber == 0) {
				return Color.WHITE;
			} else if (commentsNumber < 0) {
				return new Random().nextInt(256);
			} else {
				int color;
				Random rnd = new Random();
				color = Color.argb(commentsNumber, rnd.nextInt(commentsNumber),
						rnd.nextInt(256), rnd.nextInt(commentsNumber));

				return color;
			}
		}
	}
}
