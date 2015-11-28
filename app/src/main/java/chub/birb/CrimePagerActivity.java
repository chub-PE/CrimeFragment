package chub.birb;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v13.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;

import java.util.ArrayList;

/**
 * Created by chub on 11/28/2015.
 * CrimeActivity is a FragmentActivity that contains a ViewPager.
 * It shows data from a single instance of crime.
 * Swiping left or right will show data from the next or last crime.
 */
public class CrimePagerActivity extends FragmentActivity
{
	private ViewPager _viewPager;
	private ArrayList<Crime> _crimeList;

	@Override
	public void onCreate(Bundle b)
	{
		FragmentManager fm = getFragmentManager();
		_viewPager.setAdapter(new FragmentPagerAdapter(fm)
		{
			@Override
			public Fragment getItem(int position)
			{
				return CrimeFragment.newInstance(_crimeList.get(position).getCrimeID());
			}

			@Override
			public int getCount()
			{
				return _crimeList.size();
			}
		});
		super.onCreate(b);
		_viewPager = new ViewPager(this);
		_viewPager.setId(R.id.viewPager);
		setContentView(_viewPager);

	}

}
