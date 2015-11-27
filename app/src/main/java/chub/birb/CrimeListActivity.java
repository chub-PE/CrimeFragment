package chub.birb;

import android.app.Fragment;

/**
 * Created by chub on 11/27/2015.
 */
public class CrimeListActivity extends SingleFragmentActivity
{


	@Override
	protected Fragment createFragment()
	{
		return new CrimeListFragment();
	}
}
