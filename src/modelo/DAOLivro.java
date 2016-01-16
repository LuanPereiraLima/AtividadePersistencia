package modelo;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;

public class DAOLivro {

	private static final String COLLECTION_LIVRO = "livro";
	private MongoClient mongoClient;
	private MongoDatabase mongoDatabase;

	public DAOLivro() {
		mongoClient = new MongoClient("localhost", 27017);
		mongoDatabase = mongoClient.getDatabase("persistencia");
	}

	public void adicionarLivro(Livro livro) {
		mongoDatabase.getCollection(COLLECTION_LIVRO)
				.insertOne(new Document().append(Livro.TITULO, livro.getTitulo())
						.append(Livro.ANO_PUBLICACAO, livro.getAnoPublic()).append(Livro.ISBN, livro.getIsbn())
						.append(Livro.QTD_ESTOQUE, livro.getQtdEstoque()).append(Livro.VALOR, livro.getValor())
						.append(Livro.EDITORA, new Document().append(Editora.ID, livro.getEditora().getId())
								.append(Editora.NOME, livro.getEditora().getNome())));
	}

}
