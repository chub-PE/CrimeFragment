package chub.birb;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Created by chub on 11/27/2015.
 * CrimeLab is a single instance class that contains all the Crime instances.
 */
public class CrimeLab
{
	private static final String TAG = "CrimeLab";
	private static final String FILENAME = "crimes.json";

	private static CrimeLab _crimeLab;

	private CriminalIntentJSONSerializer _serializer;
	private ArrayList<Crime> _crimeList;
	private Context _appContext;

	private CrimeLab(Context appContext)
	{
		this._appContext = appContext;
		_serializer = new CriminalIntentJSONSerializer(_appContext, FILENAME);
		try
		{
			_crimeList = _serializer.loadCrimes();
			Log.d(TAG, "Loaded crimes.");
		}
		catch (Exception e)
		{
			_crimeList = new ArrayList<Crime>();
			Log.e(TAG, "Error loading crimes: ", e);
		}
	}

	public void addCrime(Crime crime)
	{
		_crimeList.add(crime);
	}

	public void deleteCrime(Crime c)
	{
		_crimeList.remove(c);
	}

	public ArrayList<Crime> getCrimeList()
	{
		return _crimeList;
	}

	public Crime getCrime(UUID id)
	{
		for (Crime c : _crimeList)
		{
			if (c.getID().equals(id)) return c;
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

	public boolean saveCrimes()
	{
		try
		{
			_serializer.saveCrimes(_crimeList);
			Log.d(TAG, "crimes saved to file");
			return true;
		}
		catch (Exception ex)
		{
			Log.e(TAG, "Error saving crimes: ", ex);
			return false;
		}
	}

}
