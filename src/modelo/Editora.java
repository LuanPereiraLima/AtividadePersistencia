package modelo;

public class Editora {
	
	public static final String ID = "id";
	public static final String NOME = "nome";
	
	private int id;
	private String nome;	

	public Editora(){}
	
	public Editora(int id, String nome) {
		this.id = id;
		this.nome = nome;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
}
