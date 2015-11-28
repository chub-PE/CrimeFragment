package chub.birb;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v13.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;

import java.util.ArrayList;
import java.util.UUID;

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
		super.onCreate(b);
		_viewPager = new ViewPager(this);
		_viewPager.setId(R.id.viewPager);
		_crimeList = CrimeLab.get(this).getCrimeList();
		setContentView(_viewPager);

		final FragmentManager fm = getFragmentManager();
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

		final UUID crimeId =
				(UUID)getIntent().getSerializableExtra(CrimeFragment.EXTRA_CRIME_ID);
		for (int i = 0; i < _crimeList.size(); i++)
		{
			if (_crimeList.get(i).getCrimeID().equals(crimeId))
			{
				_viewPager.setCurrentItem(i);
				break;
			}
		}

		//sets the titlebar to the description of the current crime
		//does nothing visible, since for some reason the app doesn't have a titlebar.
		_viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener()
		{

			@Override
			public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels){}
			@Override
			public void onPageScrollStateChanged(int state)	{}

			@Override
			public void onPageSelected(int position)
			{
				Crime crime = _crimeList.get(position);
				if (crime.getCrimeTitle() != null) setTitle(crime.getCrimeTitle());
			}
		});
	}
}
