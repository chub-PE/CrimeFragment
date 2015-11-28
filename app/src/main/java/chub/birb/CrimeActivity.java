package chub.birb;

import android.os.Bundle;
import android.app.Fragment;
import android.app.Activity;
import android.app.FragmentManager;

/**
 * Created by chub on 11/27/2015.
 * CrimeActivity is a SingleFragmentActivity, that contains a CrimeFragment.
 * It shows data from a single instance of crime.
 */
public class CrimeActivity extends SingleFragmentActivity
{
	@Override
	protected Fragment createFragment()
	{
		return new CrimeFragment();
	}
}
