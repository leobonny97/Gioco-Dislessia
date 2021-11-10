using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Giocatore
{
    private string username;
    private string password;
    private int progresso;

    public Giocatore(string username, string password, int progresso)
    {
        this.username = username;
        this.password = password;
        this.progresso = progresso;
    }

    public string getUsername()
    {
        return username;
    }

    public void setUsername(string username)
    {
        this.username = username;
    }

    public string getPassword()
    {
        return password;
    }

    public void setPassword(string password)
    {
        this.password = password;
    }

    public int getProgresso()
    {
        return progresso;
    }

    public void setProgresso(int progresso)
    {
        this.progresso = progresso;
    }
}
