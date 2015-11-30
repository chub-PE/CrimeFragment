package chub.birb;

import android.app.ListFragment;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
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
		i.putExtra(CrimeFragment.EXTRA_CRIME_ID, c.getID());
		startActivity(i);
	}


	@Override
	public View
	onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState)
	{
		View v = super.onCreateView(inflater, parent, savedInstanceState);
		if (_subtitleVisible)
		{
			getActivity().getActionBar().setSubtitle(R.string.subtitle);
		}

		ListView listView = (ListView) v.findViewById(android.R.id.list);
		registerForContextMenu(listView);

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
	public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo)
	{
		getActivity().getMenuInflater().inflate(R.menu.crime_list_item_context, menu);
	}

	@Override
	public boolean onContextItemSelected(MenuItem item)
	{
		AdapterView.AdapterContextMenuInfo info =
				(AdapterView.AdapterContextMenuInfo)item.getMenuInfo();
		int position = info.position;
		CrimeAdapter adapter = (CrimeAdapter)getListAdapter();
		Crime crime = adapter.getItem(position);
		switch (item.getItemId())
		{
			case R.id.menu_item_delete_crime:
				CrimeLab.get(getActivity()).deleteCrime(crime);
				adapter.notifyDataSetChanged();
				return true;
			default:
				return super.onContextItemSelected(item);
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
				i.putExtra(CrimeFragment.EXTRA_CRIME_ID, crime.getID());
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
				titleTextView.setText(c.getTitle());

			TextView dateTextView =
					(TextView) convertView.findViewById(R.id.listCrimeDateTextView);
				dateTextView.setText(c.getDate().toString());

			CheckBox solvedCheckBox =
					(CheckBox) convertView.findViewById(R.id.listCrimeSolvedCheckBox);
			solvedCheckBox.setChecked(c.isSolved());

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
