package chub.birb;

import android.app.ListFragment;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by chub on 11/27/2015.
 * COMMIT
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

		ArrayAdapter<Crime> adapter = new ArrayAdapter<Crime>(
				getActivity(), android.R.layout.simple_list_item_1, _crimeList);
		setListAdapter(adapter);
	}

	@Override
	public void onListItemClick(ListView l, View v, int position, long id)
	{
		Crime c = (Crime)(getListAdapter()).getItem(position);
		Log.d(TAG, c.getCrimeTitle() + " was clicked");
	}
}
