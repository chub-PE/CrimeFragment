package chub.birb;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by chub on 11/27/2015.
 */
public class CrimeFragment extends Fragment
{
	private Crime _crimeObject;
	@Override
	public void onCreate(Bundle b)
	{
		super.onCreate(b);
		_crimeObject = new Crime();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle b)
	{
		View result = inflater.inflate(R.layout.fragment_crime, parent, false);

		return result;
	}
}
