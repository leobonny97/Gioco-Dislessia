package model;

public class Giocatore {
	private String username;
    private String password;
    private int progresso;

    public Giocatore(String username, String password, int progresso)
    {
        this.username = username;
        this.password = password;
        this.progresso = progresso;
    }

    public Giocatore(String username, String password)
    {
        this.username = username;
        this.password = password;
    }
    
    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
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
