package chub.birb;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

/**
 * Created by chub on 11/27/2015.
 * FUCK YOU LINT I WRITE HEADERS WHENEVER I PLEASE
 */
public class CrimeFragment extends Fragment
{
	private Crime _crimeObject;
	private EditText _titleField;

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

		_titleField = (EditText)result.findViewById(R.id.crime_title);
		_titleField.addTextChangedListener(new TextWatcher()
		{
			public void onTextChanged(CharSequence c, int start, int before, int count)
			{
				_crimeObject.setCrimeTitle(c.toString());
			}
			public void beforeTextChanged(CharSequence c, int start, int count, int after)
			{
				//blank
			}
			public void afterTextChanged(Editable c)
			{
				//blanks
			}
		});

		return result;
	}
}
