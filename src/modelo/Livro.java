package modelo;

public class Livro {
	
	public static final String ISBN = "isbn";
	public static final String TITULO = "titulo";
	public static final String ANO_PUBLICACAO = "anoPublicacao";
	public static final String QTD_ESTOQUE = "qtdEstoque";
	public static final String VALOR = "valor";
	public static final String EDITORA = "editora";
	
	private int isbn;
	private String titulo;
	private int anoPublic;
	private int qtdEstoque;
	private double valor;
	private Editora editora;
	
	public Livro(){}
	
	public Livro(int isbn, String titulo, int anoPublic, int qtdEstoque, double valor, Editora editora) {
		this.isbn = isbn;
		this.titulo = titulo;
		this.anoPublic = anoPublic;
		this.qtdEstoque = qtdEstoque;
		this.valor = valor;
		this.editora = editora;
	}

	public int getIsbn() {
		return isbn;
	}

	public void setIsbn(int isbn) {
		this.isbn = isbn;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public int getAnoPublic() {
		return anoPublic;
	}

	public void setAnoPublic(int anoPublic) {
		this.anoPublic = anoPublic;
	}

	public int getQtdEstoque() {
		return qtdEstoque;
	}

	public void setQtdEstoque(int qtdEstoque) {
		this.qtdEstoque = qtdEstoque;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public Editora getEditora() {
		return editora;
	}

	public void setEditora(Editora editora) {
		this.editora = editora;
	}

}
