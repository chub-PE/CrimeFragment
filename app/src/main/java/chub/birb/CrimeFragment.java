package chub.birb;

import android.os.Bundle;
import android.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

/**
 * Created by chub on 11/27/2015.
 * FUCK YOU LINT I WRITE HEADERS WHENEVER I PLEASE
 */
public class CrimeFragment extends Fragment
{
	private Crime _crimeObject;
	private EditText _titleField;
	private CheckBox _solvedCheckBox;
	private Button _dateButton;

	@Override
	public void onCreate(Bundle b)
	{
		super.onCreate(b);
		_crimeObject = new Crime();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle b)
	{
		View view = inflater.inflate(R.layout.fragment_crime, parent, false);

		_solvedCheckBox = (CheckBox) view.findViewById(R.id.crime_solved_checkbox);
		_solvedCheckBox.setEnabled(_crimeObject.isCrimeSolved());
		_solvedCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
		{
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
			{
				_crimeObject.setCrimeSolved(isChecked);
			}
		});

		_dateButton = (Button) view.findViewById(R.id.crime_date_button);
		_dateButton.setText(_crimeObject.getCrimeDate().toString());
		_dateButton.setEnabled(false);

		_titleField = (EditText) view.findViewById(R.id.crime_title);
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

		return view;
	}
}
