using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Frase
{
    private int id;
    private List<Parole> parole;

    public Frase(int id)
    {
        this.id = id;
        parole = null;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public void setParole(List<Parole> parole)
    {
        this.parole = parole;
    }

    public List<Parole> getParole()
    {
        return parole;
    }

    public void addParole(Parole parola)
    {
        if(parole == null)
        {
            parole = new List<Parole>();
        }
        parole.Add(parola);
    }
}
