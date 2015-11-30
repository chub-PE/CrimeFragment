package chub.birb;

import android.content.Context;

import java.util.ArrayList;
import java.util.Random;
import java.util.UUID;

/**
 * Created by chub on 11/27/2015.
 * CrimeLab is a single instance class that contains all the Crime instances.
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
	}

	public void addCrime(Crime crime)
	{
		_crimeList.add(crime);
	}


	public ArrayList<Crime> getCrimeList()
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
