package chub.birb;

import java.util.UUID;

/**
 * Created by chub on 11/27/2015.
 * NOW THERES GONNA BE TROUBLOE
 */
public class Crime
{
    private UUID _crimeID;
    private String _crimeTitle;

    public Crime()
    {
        //generate unique ID
        _crimeID = UUID.randomUUID();
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

}

