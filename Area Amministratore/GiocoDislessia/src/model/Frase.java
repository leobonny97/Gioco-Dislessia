package model;

public class Frase {
	private int id;
	private int livello;
	
	public Frase(int id, int livello)
	{
		this.id=id;
		this.livello=livello;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getLivello() {
		return livello;
	}

	public void setLivello(int livello) {
		this.livello = livello;
	}

	
	
}
