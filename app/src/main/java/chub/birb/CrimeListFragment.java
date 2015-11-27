package chub.birb;

import android.app.ListFragment;
import android.os.Bundle;

import java.util.ArrayList;

/**
 * Created by chub on 11/27/2015.
 */
public class CrimeListFragment extends ListFragment
{

	private ArrayList<Crime> _crimeList;
	@Override
	public void onCreate(Bundle b)
	{
		super.onCreate(b);
		getActivity().setTitle(R.string.crimes_title);
		_crimeList = CrimeLab.get(getActivity()).getCrimeList();
	}
}
