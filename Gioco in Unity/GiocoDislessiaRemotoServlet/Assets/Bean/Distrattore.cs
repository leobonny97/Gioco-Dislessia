using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Distrattore
{
    private int id;
    private string parola;

    public Distrattore(int id, string parola)
    {
        this.id = id;
        this.parola = parola;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public string getParola()
    {
        return parola;
    }

    public void setParola(string parola)
    {
        this.parola = parola;
    }

    public bool cercaDistrattore(string distrattore)
    {
        if(this.getParola()==distrattore)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}
