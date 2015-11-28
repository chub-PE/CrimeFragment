package chub.birb;

import android.app.ListFragment;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by chub on 11/27/2015.
 * It shows data from multiple Crime instances in a scrollable list view.
 */
public class CrimeListFragment extends ListFragment
{
	private static final String TAG = "CrimeListFragment";
	private ArrayList<Crime> _crimeList;

	@Override
	public void onCreate(Bundle b)
	{
		super.onCreate(b);
		getActivity().setTitle(R.string.crimes_title);
		_crimeList = CrimeLab.get(getActivity()).getCrimeList();

		CrimeAdapter adapter =
				new CrimeAdapter(_crimeList);
		setListAdapter(adapter);
	}

	@Override
	public void onListItemClick(ListView l, View v, int position, long id)
	{
		Crime c = ((CrimeAdapter)getListAdapter()).getItem(position);
		Log.d(TAG, c.getCrimeTitle() + " was clicked");
	}

	private class CrimeAdapter extends ArrayAdapter<Crime>
	{
		public CrimeAdapter (ArrayList<Crime> crimes)
		{
			super(getActivity(), 0, crimes);
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent)
		{
			//if converview null inflate a new
			if (convertView == null)
			{
				convertView =
						getActivity().getLayoutInflater().inflate(R.layout.list_item_crime, null);
			}

			//configure the view
			Crime c = getItem(position);

			TextView titleTextView =
					(TextView) convertView.findViewById(R.id.listCrimeTitleTextView);
				titleTextView.setText(c.getCrimeTitle());

			TextView dateTextView =
					(TextView) convertView.findViewById(R.id.listCrimeDateTextView);
				dateTextView.setText(c.getCrimeDate().toString());

			CheckBox solvedCheckBox =
					(CheckBox) convertView.findViewById(R.id.listCrimeSolvedCheckBox);
			solvedCheckBox.setChecked(c.isCrimeSolved());

			return convertView;
		}
	}


}
