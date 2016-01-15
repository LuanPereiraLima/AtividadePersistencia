package modelo;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;

public class DAOLivro {
	
	private MongoClient mongoClient;
	private MongoDatabase mongoDatabase;
	
	public DAOLivro(){
		mongoClient = new MongoClient();
		mongoDatabase = mongoClient.getDatabase("persistencia");
	}
	
	public void adicionarLivro(Livro livro){
		mongoDatabase.getCollection("livro").insertOne(
			new Document()
			.append("titulo", livro.getTitulo())
			.append("anoPublic", livro.getAnoPublic())
			.append("isbn", livro.getIsbn())
			.append("qtdEstoque", livro.getQtdEstoque())
			.append("valor", livro.getValor())
			.append("editora", 
				new Document()
				.append("id", livro.getEditora().getId())
				.append("nome", livro.getEditora().getNome())
			)
		);
	}
}
