package chub.birb;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v4.app.NavUtils;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import java.util.Date;
import java.util.UUID;

/**
 * Created by chub on 11/27/2015.
 * CrimeFragment shows data from a Crime object, and provides means to manipulate it.
 */
public class CrimeFragment extends Fragment
{
	public static final String EXTRA_CRIME_ID = "chub.birb.crimefragment.crime_id";
	private static final String DIALOG_DATE = "date";
	private static final int REQUEST_DATE = 0;


	private Crime _crimeObject;
	private EditText _titleField;
	private CheckBox _solvedCheckBox;
	private Button _dateButton;

	@Override
	public void onCreate(Bundle b)
	{
		super.onCreate(b);
		setHasOptionsMenu(true);
		final UUID crimeID = (UUID) getArguments().getSerializable(EXTRA_CRIME_ID);
		_crimeObject = CrimeLab.get(getActivity()).getCrime(crimeID);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle b)
	{
		View view = inflater.inflate(R.layout.fragment_crime, parent, false);

		if (NavUtils.getParentActivityName(getActivity()) != null)
		{
			getActivity().getActionBar().setDisplayHomeAsUpEnabled(true);
		}

		_solvedCheckBox = (CheckBox) view.findViewById(R.id.crime_solved_checkbox);
		_solvedCheckBox.setChecked(_crimeObject.isSolved());
		_solvedCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
		{
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
			{
				_crimeObject.setSolved(isChecked);
			}
		});

		_dateButton = (Button) view.findViewById(R.id.crime_date_button);
		_dateButton.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				FragmentManager fm =
						getActivity().getFragmentManager();
				DatePickerFragment dialog =
						DatePickerFragment.newInstance(_crimeObject.getDate());
				dialog.setTargetFragment(CrimeFragment.this, REQUEST_DATE);
				dialog.show(fm, DIALOG_DATE);
			}
		});
		refreshDate();

		_titleField = (EditText) view.findViewById(R.id.crime_title);
		_titleField.setText(_crimeObject.getTitle());
		_titleField.addTextChangedListener(new TextWatcher()
		{
			public void onTextChanged(CharSequence c, int start, int before, int count)
			{
				_crimeObject.setTitle(c.toString());
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

	@Override
	public void onPause()
	{
		super.onPause();
		CrimeLab.get(getActivity()).saveCrimes();
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		switch (item.getItemId())
		{
			case android.R.id.home :
				if (NavUtils.getParentActivityName(getActivity()) != null)
				{
					NavUtils.navigateUpFromSameTask(getActivity());
				}
				return true;
			default:
				return super.onOptionsItemSelected(item);
		}
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data)
	{
		if (resultCode != Activity.RESULT_OK) return;
		if (requestCode == REQUEST_DATE)
		{
			Date date =
					(Date) data.getSerializableExtra(DatePickerFragment.EXTRA_DATE);
			_crimeObject.setDate(date);
			refreshDate();
		}
	}

	public static CrimeFragment newInstance(UUID crimeId)
	{
		Bundle args = new Bundle();
		args.putSerializable(EXTRA_CRIME_ID, crimeId);
		CrimeFragment fragment = new CrimeFragment();
		fragment.setArguments(args);
		return fragment;
	}

	private void refreshDate()
	{
		_dateButton.setText(_crimeObject.getDate().toString());
	}

}
