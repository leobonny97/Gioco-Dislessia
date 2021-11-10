package model;

public class Distrattore {
	private int id;
	private String stringa;
	
	public Distrattore(String stringa)
	{
		this.stringa=stringa;
	}
	
	public Distrattore(int id, String stringa)
	{
		this.id=id;
		this.stringa=stringa;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getStringa() {
		return stringa;
	}
	
	public void setStringa(String stringa) {
		this.stringa = stringa;
	}


}
