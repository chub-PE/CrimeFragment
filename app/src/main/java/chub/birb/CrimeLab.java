package chub.birb;

import android.content.Context;

import java.util.ArrayList;
import java.util.Random;
import java.util.UUID;

/**
 * Created by chub on 11/27/2015.
 */
public class CrimeLab
{
	private static CrimeLab _crimeLab;

	private ArrayList<Crime> _crimeList;
	private Context _appContext;


	private CrimeLab(Context appContext)
	{
		this._appContext = appContext;
		this._crimeList = new ArrayList<Crime>();

		final Random fun = new Random();

		final String[] start =
				{
						"Obama likes to ", "Why do you always ", "When I was 11 I thought I could",
						"Why the fuck are you ", "I think cats are "
				};

		final String[] mid =
				{
					" eat raddish sandwitch with ", " sit on the toilet backwards, ", " turn into" +
						" a unicorn ", " talking shit about me ", " fucking stink "
				};

		final String[] end =
				{
					"Donald Trump!", "you lil piece of shit!", " panda.", "bastard?", "poophole!!!"
				};

		for (int i = 0; i < 100; i++)
		{
			final Crime c = new Crime();
			final String newTitle = start[fun.nextInt(start.length)] + mid[fun.nextInt(mid
					.length)] + end[fun.nextInt(end.length)];
			c.setCrimeTitle(newTitle);
			c.setCrimeSolved(i % 2 == 0);
			_crimeList.add(c);
		}

	}

	public ArrayList<Crime> getCrimes()
	{
		return _crimeList;
	}

	public Crime getCrime(UUID id)
	{
		for (Crime c : _crimeList)
		{
			if (c.getCrimeID().equals(id)) return c;
		}
		return null;
	}


	public static CrimeLab get(Context c)
	{
		if (_crimeLab == null)
		{
			_crimeLab = new CrimeLab(c.getApplicationContext());
		}
		return _crimeLab;
	}



}
