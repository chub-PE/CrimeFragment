package chub.birb;


import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;
import java.util.UUID;

/**
 * Created by chub on 11/27/2015.
 * Crime MODEL objects are able to store data about instances of crimes.
 */
public class Crime
{
    private static final String JSON_ID = "id";
    private static final String JSON_TITLE = "title";
    private static final String JSON_SOLVED = "solved";
    private static final String JSON_DATE = "date";

	private UUID _ID;
    private String _title;
    private Date _date;
    private boolean _solved;

    public Crime()
    {
        //generate unique ID
        _ID = UUID.randomUUID();
        _date = new Date();
    }

	public Crime(JSONObject json) throws JSONException
	{
		_ID = UUID.fromString(json.getString(JSON_ID));
		if (json.has(JSON_TITLE)) {
			_title = json.getString(JSON_TITLE);
		}
		_solved = json.getBoolean(JSON_SOLVED);
		_date = new Date(json.getLong(JSON_DATE));
	}

    public JSONObject toJSON() throws JSONException
    {
        JSONObject json = new JSONObject();
        json.put(JSON_ID, _ID.toString());
        json.put(JSON_TITLE, _title);
        json.put(JSON_SOLVED, _solved);
        json.put(JSON_DATE, _date.getTime());
        return json;
    }

    public UUID getID()
    {
        return _ID;
    }

    public String getTitle()
    {
        return _title;
    }

    public void setTitle(String title)
    {
        _title = title;
    }

    public Date getDate()
    {
        return _date;
    }

    public void setDate(Date date)
    {
        _date = date;
    }

    public boolean isSolved()
    {
        return _solved;
    }

    public void setSolved(boolean solved)
    {
        _solved = solved;
    }

    @Override
    public String toString()
    {
        return _title;
    }


}

