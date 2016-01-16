package ufc.persistencia;

import modelo.DAOEditora;
import modelo.DAOLivro;
import modelo.Editora;
import modelo.Livro;

public class Questao1 {
	public static void main(String[] args) {
		
		DAOEditora daoEditora = new DAOEditora();
		DAOLivro daoLivro = new DAOLivro();
		
		Editora atica = new Editora(1, "Ática");
		Editora melhoramentos = new Editora(3, "Melhoramentos");
		Editora ftd = new Editora(2, "FTD");
		Editora novatec = new Editora(4, "Novatec");
		Editora bookman = new Editora(5, "Bookman");
		
		daoEditora.adicionarEditora(atica);
		daoEditora.adicionarEditora(melhoramentos);
		daoEditora.adicionarEditora(ftd);
		daoEditora.adicionarEditora(novatec);
		daoEditora.adicionarEditora(bookman);
		
		daoLivro.adicionarLivro(new Livro(213, "Banco de Dados", 2011, 2, 50, novatec));
		daoLivro.adicionarLivro(new Livro(425, "Sistemas Operacionais", 2010, 3, 80, melhoramentos));
		daoLivro.adicionarLivro(new Livro(732, "Lógica de Programação", 2009, 1, 60, ftd));
		daoLivro.adicionarLivro(new Livro(441, "Prog. Orientada a Objetos", 2012, 1, 70, atica));
		daoLivro.adicionarLivro(new Livro(659, "Java para Nerds", 2010, 3, 90, atica));
		daoLivro.adicionarLivro(new Livro(863, "Engenharia de Software", 2010, 2, 40, ftd));
		daoLivro.adicionarLivro(new Livro(376, "Redes de Computadores", 2008, 1, 100, melhoramentos));
		daoLivro.adicionarLivro(new Livro(446, "No_SQL", 2013, 6, 55, novatec));

	}
}
