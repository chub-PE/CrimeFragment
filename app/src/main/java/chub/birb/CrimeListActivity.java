package chub.birb;

import android.app.Fragment;

/**
 * Created by chub on 11/27/2015.
 * CrimeListActivity is a SingleFragmentActivity, that contains a CrimeListFragment.
 * It shows data from multiple Crime instances in a scrollable list view.
 */
public class CrimeListActivity extends SingleFragmentActivity
{


	@Override
	protected Fragment createFragment()
	{
		return new CrimeListFragment();
	}
}
