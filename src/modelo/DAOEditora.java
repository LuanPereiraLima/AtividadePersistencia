package modelo;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;

public class DAOEditora {
	
	private MongoClient mongoClient;
	private MongoDatabase mongoDatabase;
	
	public DAOEditora(){
		mongoClient = new MongoClient();
		mongoDatabase = mongoClient.getDatabase("persistencia");
	}
	
	public void adicionarEditora(Editora editora){
		mongoDatabase.getCollection("editora").insertOne(
			new Document()
			.append("nome", editora.getNome())
			.append("id", editora.getId())
		);
	}
}
