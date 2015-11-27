package chub.birb;

import android.os.Bundle;
import android.app.Fragment;
import android.app.Activity;
import android.app.FragmentManager;


public class CrimeActivity extends SingleFragmentActivity
{
	@Override
	protected Fragment createFragment()
	{
		return new CrimeFragment();
	}
}
