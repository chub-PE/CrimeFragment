package chub.birb;

import android.app.ListFragment;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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
	private boolean _subtitleVisible;

	@Override
	public void onCreate(Bundle b)
	{
		super.onCreate(b);

		setHasOptionsMenu(true);
		setRetainInstance(true);
		_subtitleVisible = true;

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

		//Start CrimeActivity
		Intent i = new Intent(getActivity(), CrimePagerActivity.class);
		i.putExtra(CrimeFragment.EXTRA_CRIME_ID, c.getCrimeID());
		startActivity(i);
	}


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup parent,
							 Bundle savedInstanceState) {
		View v = super.onCreateView(inflater, parent, savedInstanceState);
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
			if (_subtitleVisible) {
				getActivity().getActionBar().setSubtitle(R.string.subtitle);
			}
		}
		return v;
	}


	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater)
	{
		super.onCreateOptionsMenu(menu, inflater);
		inflater.inflate(R.menu.fragment_crime_list, menu);
		MenuItem showSubtitle = menu.findItem(R.id.menu_item_show_subtitle);
		if (_subtitleVisible && showSubtitle != null)
		{
			showSubtitle.setTitle(R.string.hide_subtitle);
		}
	}


	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		switch(item.getItemId())
		{
			case R.id.menu_item_new_crime :
				Crime crime = new Crime();
				CrimeLab.get(getActivity()).addCrime(crime);
				Intent i = new Intent(getActivity(), CrimePagerActivity.class);
				i.putExtra(CrimeFragment.EXTRA_CRIME_ID, crime.getCrimeID());
				startActivityForResult(i, 0);
				return true;
			case R.id.menu_item_show_subtitle:
				if (getActivity().getActionBar().getSubtitle() == null)
				{
					getActivity().getActionBar().setSubtitle(R.string.subtitle);
					item.setTitle(R.string.hide_subtitle);
					_subtitleVisible = true;
				}
				else
				{
					getActivity().getActionBar().setSubtitle(null);
					item.setTitle(R.string.show_subtitle);
					_subtitleVisible = false;
				}
				return true;
			default :
				return super.onOptionsItemSelected(item);
		}
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

	@Override
	public void onResume()
	{
		super.onResume();
		((CrimeAdapter) getListAdapter()).notifyDataSetChanged();
	}


}
