using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Parole
{
    private int id;
    private string parola;
    private int frase;
    private int ordine;
    private List<Distrattore> distrattori;

    public Parole(int id, string parola, int frase, int ordine)
    {
        this.id = id;
        this.parola = parola;
        this.frase = frase;
        this.ordine = ordine;
        this.distrattori = null;
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

    public int getFrase()
    {
        return frase;
    }

    public void setFrase(int frase)
    {
        this.frase = frase;
    }

    public int getOrdine()
    {
        return ordine;
    }

    public void setOrdine(int ordine)
    {
        this.ordine = ordine;
    }

    public void setDistrattore(List<Distrattore> distrattori)
    {
        this.distrattori = distrattori;
    }

    public void addDistrattore(Distrattore distrattore)
    {
        if(distrattori==null)
        {
            distrattori = new List<Distrattore>();
        }
        distrattori.Add(distrattore);
    }

    public List<Distrattore> getDistrattori()
    {
        return distrattori;
    }

}
