package model;

import java.util.ArrayList;

public class Parola {
	private int frase;
	private int parola;
	private int ordine;
	private String stringa;
	private ArrayList<Distrattore> distrattori;
	
	public Parola(int ordine, String stringa, ArrayList<Distrattore> distrattori)
	{
		this.ordine=ordine;
		this.stringa=stringa;
		this.distrattori=distrattori;
	}
	
	public Parola(int frase, int parola, int ordine, String stringa, ArrayList<Distrattore> distrattori)
	{
		this.frase=frase;
		this.parola=parola;
		this.ordine=ordine;
		this.stringa=stringa;
		this.distrattori=distrattori;
	}
	
	public Parola(int frase, int parola, int ordine, String stringa)
	{
		this.frase=frase;
		this.parola=parola;
		this.ordine=ordine;
		this.stringa=stringa;
		this.distrattori=null;
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
	
	public int getOrdine() {
		return ordine;
	}
	
	public void setOrdine(int ordine) {
		this.ordine = ordine;
	}
	
	public String getStringa() {
		return stringa;
	}
	
	public void setStringa(String stringa) {
		this.stringa = stringa;
	}
	
	public void setDistrattori(ArrayList<Distrattore> distrattori)
	{
		this.distrattori=distrattori;
	}
	
	public ArrayList<Distrattore> getDistrattori()
	{
		return distrattori;
	}
	
}
