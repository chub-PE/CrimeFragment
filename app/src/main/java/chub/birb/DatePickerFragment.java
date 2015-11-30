package chub.birb;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by chub on 11/28/2015.
 * Shows a fragment with a DatePicker widget.
 */
public class DatePickerFragment extends DialogFragment
{

	public static final String EXTRA_DATE = "chub.birb.datepickerfragment.date";

	private Date _crimeDate;

	@Override
	public Dialog onCreateDialog(Bundle b)
	{
		_crimeDate = (Date) getArguments().getSerializable(EXTRA_DATE);

		//Create a calendar to get year, month and day
		final Calendar calendar = Calendar.getInstance();
		calendar.setTime(_crimeDate);
		final int year = calendar.get(Calendar.YEAR);
		final int month = calendar.get(Calendar.MONTH);
		final int day = calendar.get(Calendar.DAY_OF_MONTH);

		View v = getActivity().getLayoutInflater().inflate(R.layout.dialog_date, null);

		final DatePicker datePicker = (DatePicker) v.findViewById(R.id.datePickerDialog);
		datePicker.init(year, month, day, new DatePicker.OnDateChangedListener()
		{
			@Override
			public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth)
			{
				//translate data into date
				_crimeDate = new GregorianCalendar(year, monthOfYear, dayOfMonth).getTime();

				//update argument to preserve value on rotation
				getArguments().putSerializable(EXTRA_DATE, _crimeDate);
			}
		});


		return new AlertDialog.Builder(getActivity())
				.setView(v)
				.setTitle(R.string.date_picker_title)
				.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener()
				{
					@Override
					public void onClick(DialogInterface dialog, int which)
					{
						sendResult(Activity.RESULT_OK);
					}
				})
				.create();
	}

	public static DatePickerFragment newInstance(Date date)
	{
		Bundle args = new Bundle();
		args.putSerializable(EXTRA_DATE, date);

		DatePickerFragment fragment = new DatePickerFragment();
		fragment.setArguments(args);

		return fragment;
	}


	private void sendResult(int resultCode)
	{
		if (getTargetFragment() == null) return;

		Intent i = new Intent();
		i.putExtra(EXTRA_DATE, _crimeDate);

		getTargetFragment().onActivityResult(getTargetRequestCode(), resultCode, i);
	}


}
