package chub.birb;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;

/**
 * Created by chub on 11/30/2015.
 * Converts an ArrayList of Crime objects into JSON, and saves it in the sandbox.
 */
public class CriminalIntentJSONSerializer
{
	private Context _context;
	private String _fileName;
	public CriminalIntentJSONSerializer(Context c, String f)
	{
		_context = c;
		_fileName = f;
	}

	public void saveCrimes(ArrayList<Crime> crimes)
			throws JSONException, IOException
	{
		// Build an array in JSON
		JSONArray array = new JSONArray();
		for (Crime c : crimes) array.put(c.toJSON());
		// Write the file to disk
		Writer writer = null;
		try
		{
			OutputStream out =
					_context.openFileOutput(_fileName, Context.MODE_PRIVATE);
			writer = new OutputStreamWriter(out);
			writer.write(array.toString());
		}
		finally
		{
			if (writer != null)
				writer.close();
		}
	}
}