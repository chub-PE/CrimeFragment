package chub.birb;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.View;

/**
 * Created by chub on 11/28/2015.
 * Shows a fragment with a DatePicker widget.
 */
public class DatePickerFragment extends DialogFragment
{
	@Override
	public Dialog onCreateDialog(Bundle b)
	{
		View v = getActivity().getLayoutInflater().inflate(R.layout.dialog_date, null);

		return new AlertDialog.Builder(getActivity())
				.setView(v)
				.setTitle(R.string.date_picker_title)
				.setPositiveButton(android.R.string.ok, null)
				.create();
	}

}
