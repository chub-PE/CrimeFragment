package chub.birb;


import java.util.Date;
import java.util.UUID;

/**
 * Created by chub on 11/27/2015.
 * Crime MODEL objects are able to store data about instances of crimes.
 */
public class Crime
{
	private UUID _crimeID;
    private String _crimeTitle;
    private Date _crimeDate;
    private boolean _crimeSolved;

    public Crime()
    {
        //generate unique ID
        _crimeID = UUID.randomUUID();
        _crimeDate = new Date();
    }

    public UUID getCrimeID()
    {
        return _crimeID;
    }

    public String getCrimeTitle()
    {
        return _crimeTitle;
    }

    public void setCrimeTitle(String crimeTitle)
    {
        _crimeTitle = crimeTitle;
    }

    public Date getCrimeDate()
    {
        return _crimeDate;
    }

    public void setCrimeDate(Date crimeDate)
    {
        _crimeDate = crimeDate;
    }

    public boolean isCrimeSolved()
    {
        return _crimeSolved;
    }

    public void setCrimeSolved(boolean crimeSolved)
    {
        _crimeSolved = crimeSolved;
    }

    @Override
    public String toString()
    {
        return _crimeTitle;
    }


}

