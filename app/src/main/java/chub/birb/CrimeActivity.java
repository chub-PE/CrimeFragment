package chub.birb;

import android.os.Bundle;
import android.app.Fragment;
import android.app.Activity;
import android.app.FragmentManager;

import java.util.UUID;

/**
 * Created by chub on 11/27/2015.
 * CrimeActivity is a SingleFragmentActivity, that contains a CrimeFragment.
 * It shows data from a single instance of crime.
 */
@Deprecated
public class CrimeActivity extends SingleFragmentActivity
{
	@Override
	protected Fragment createFragment()
	{
		final UUID crimeID = (UUID) getIntent().getSerializableExtra(CrimeFragment.EXTRA_CRIME_ID);
		return CrimeFragment.newInstance(crimeID);
	}
}
