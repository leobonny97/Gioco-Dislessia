package model;

public class Errore {
	private int frase;
	private int parola;
	private String parola_stringa;
	private int distrattore;
	private String distrattore_stringa;
	private String giocatore;
	
	
	public Errore(int frase, String parola, String distrattore, String giocatore)
	{
		this.frase=frase;
		this.parola_stringa=parola;
		this.distrattore_stringa=distrattore;
		this.giocatore=giocatore;
	}
	
	public Errore(int frase, int parola, int distrattore, String giocatore)
	{
		this.frase=frase;
		this.parola=parola;
		this.distrattore=distrattore;
		this.giocatore=giocatore;
	}

	public int getFrase() {
		return frase;
	}

	public void setFrase(int frase) {
		this.frase = frase;
	}

	public int getParola() {
		return parola;
	}

	public void setParola(int parola) {
		this.parola = parola;
	}

	public int getDistrattore() {
		return distrattore;
	}

	public void setDistrattore(int distrattore) {
		this.distrattore = distrattore;
	}

	public String getGiocatore() {
		return giocatore;
	}

	public void setGiocatore(String giocatore) {
		this.giocatore = giocatore;
	}

	public String getDistrattore_stringa() {
		return distrattore_stringa;
	}

	public void setDistrattore_stringa(String distrattore_stringa) {
		this.distrattore_stringa = distrattore_stringa;
	}

	public String getParola_stringa() {
		return parola_stringa;
	}

	public void setParola_stringa(String parola_stringa) {
		this.parola_stringa = parola_stringa;
	}
	
	
}
