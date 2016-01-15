package ufc.persistencia;

import org.bson.Document;

import com.mongodb.Block;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoDatabase;
import static com.mongodb.client.model.Filters.*;
import static com.mongodb.client.model.Sorts.ascending;
import static java.util.Arrays.asList;

public class Questao2 {
	
	public static void main(String[] args) {
		MongoClient mongoClient = new MongoClient();
		MongoDatabase mongoDatabase = mongoClient.getDatabase("persistencia");
		
		FindIterable<Document> iterable = mongoDatabase.getCollection("livro").find(regex("titulo", ".*Programação.*"));
		
		iterable.forEach(new Block<Document>() {
			
			@Override
			public void apply(Document document) {
				System.out.println(document);	
			}
			
		});
		
	}
}
