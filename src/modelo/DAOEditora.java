package modelo;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.mongodb.Block;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoDatabase;

public class DAOEditora {
	
	private static final String COLLECTION_EDITORA = "editora";
	private MongoClient mongoClient;
	private MongoDatabase mongoDatabase;
	
	public DAOEditora(){
		mongoClient = new MongoClient("localhost" , 27017);
		mongoDatabase = mongoClient.getDatabase("persistencia");
	}
	
	public void adicionarEditora(Editora editora){
		mongoDatabase.getCollection(COLLECTION_EDITORA).insertOne(
			new Document()
			.append(Editora.NOME, editora.getNome())
			.append(Editora.ID, editora.getId())
		);
	}
	
	public List<Editora> getEditoras(){
		
		FindIterable<Document> iterableEditoras = mongoDatabase.getCollection(COLLECTION_EDITORA).find();
		final List<Editora> editoras = new ArrayList<>();
		iterableEditoras.forEach(new Block<Document>() {
			
			@Override
			public void apply(Document document) {
				Editora editora = new Editora();
				
				editora.setId(document.getInteger(Editora.ID));
				editora.setNome(document.getString(Editora.NOME));
				
				editoras.add(editora);
			}
		});
		
		return editoras;
	}
	
}
