package ufc.persistencia;

import org.bson.Document;

import com.mongodb.BasicDBList;
import com.mongodb.Block;
import com.mongodb.MongoClient;
import com.mongodb.client.AggregateIterable;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Aggregates;
import com.mongodb.client.model.Filters;

import modelo.Editora;
import modelo.Livro;

import static com.mongodb.client.model.Filters.*;
import static com.mongodb.client.model.Sorts.ascending;
import static java.util.Arrays.asList;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class Questao2 {

	public static void main(String[] args) {
		MongoClient mongoClient = new MongoClient("localhost", 27017);
		MongoDatabase mongoDatabase = mongoClient.getDatabase("persistencia");

		DecimalFormat decimalFormat = new DecimalFormat("0.##");

		/*
		 * QUESTÃO 2 - a) Obter os títulos e valores dos livros que possuem a
		 * string “Programação” em seu título;
		 * 
		 */

		FindIterable<Document> iterable = mongoDatabase.getCollection("livro").find(regex("titulo", ".*Programação.*"))
				.projection(new Document(Livro.TITULO, true).append(Livro.VALOR, true));
		final List<Livro> livros = new ArrayList<>();
		iterable.forEach(new Block<Document>() {

			@Override
			public void apply(Document document) {
				Livro livro = new Livro();
				livro.setTitulo(document.getString(Livro.TITULO));
				livro.setValor(document.getDouble(Livro.VALOR));

				livros.add(livro);
			}
		});

		System.out.println("######### Questão 2-a #########");
		for (Livro livro : livros) {
			System.out.println(
					"Título : " + livro.getTitulo() + " | " + "Valor :" + decimalFormat.format(livro.getValor()));
		}
		System.out.println("#############################\n\n\n\n");

		/*
		 * QUESTÃO 2 - b) Obter os títulos e valores dos livros com preços acima
		 * de R$ 60,00. Os resultados devem ser ordenados em ordem decrescente
		 * pelo valor do livro;
		 */

		iterable = mongoDatabase.getCollection("livro").find(gt(Livro.VALOR, 60))
				.projection(new Document(Livro.TITULO, true).append(Livro.VALOR, true)).sort(new Document("valor", -1));
		final List<Livro> livros2 = new ArrayList<>();
		iterable.forEach(new Block<Document>() {

			@Override
			public void apply(Document document) {
				Livro livro = new Livro();
				livro.setTitulo(document.getString(Livro.TITULO));
				livro.setValor(document.getDouble(Livro.VALOR));

				livros2.add(livro);
			}
		});

		System.out.println("######### Questão 2-b #########");

		for (Livro livro : livros2) {
			System.out.println(
					"Título : " + livro.getTitulo() + " | " + "Valor :" + decimalFormat.format(livro.getValor()));
		}

		System.out.println("#############################\n\n\n\n");

		/*
		 * QUESTÃO 2 - c) Obter os títulos dos livros e os nomes das suas
		 * respectivas editoras. Os resultados devem ser exibidos em ordem
		 * crescente pelo título do livro. Os livros que não possuem editora
		 * também devem aparecer na listagem;
		 */

		iterable = mongoDatabase.getCollection("livro").find()
				.projection(new Document(Livro.TITULO, true).append(Livro.EDITORA, true))
				.sort(new Document(Livro.TITULO, 1));
		final List<Livro> livros3 = new ArrayList<>();
		iterable.forEach(new Block<Document>() {

			@Override
			public void apply(Document document) {
				Livro livro = new Livro();
				livro.setTitulo(document.getString(Livro.TITULO));

				Editora editora = new Editora();
				Document documentEditora = (Document) document.get(Livro.EDITORA);

				editora.setNome(documentEditora.getString(Editora.NOME));
				livro.setEditora(editora);

				livros3.add(livro);
			}
		});

		System.out.println("######### Questão 2-c #########");

		for (Livro livro : livros3) {
			System.out.println("Título : " + livro.getTitulo() + " | " + "Editora " + livro.getEditora().getNome());
		}

		System.out.println("#############################\n\n\n\n");

		/*
		 * QUESTÃO 2 - d) Obter o nome da editora, a quantidade total de livros
		 * por editora e o valor total (qtd_estoque * valor) dos livros para
		 * cada editora. Somente considerar os livros publicados a partir de
		 * 2010;
		 */

		BasicDBList multiplicacao = new BasicDBList();
		multiplicacao.add("$valor");
		multiplicacao.add("$qtdEstoque");

		AggregateIterable<Document> aggregateIterable = mongoDatabase
				.getCollection(
						"livro")
				.aggregate(asList(
						Aggregates.match(gte(Livro.ANO_PUBLICACAO, 2010)),
						new Document("$group",
								new Document("_id",
										new Document(Editora.ID, "$editora.id").append(Editora.NOME, "$editora.nome"))
												.append("quantidade_livros", new Document("$sum", 1))
												.append("soma_total_livros",
														new Document("$sum",
																new Document("$multiply", multiplicacao)))),
						Aggregates.sort(new Document("quantidade_livros", -1))
				));
		

		System.out.println("######### Questão 2-d #########");
		aggregateIterable.forEach(new Block<Document>() {

			@Override
			public void apply(Document document) {

				Document infoEditora = (Document) document.get("_id");
				System.out.println("Nome da Editora: " + infoEditora.get(Editora.NOME));
				System.out.println("Quantidade total de livros: " + document.getInteger("quantidade_livros"));
				System.out.println("Valor total dos livros: " + document.getDouble("soma_total_livros"));
				System.out.println(" ----- ");
			}

		});
		System.out.println("#############################\n\n\n\n");

		/*
		 * QUESTÃO 2 - e) Obter a quantidade total de livros disponíveis em
		 * estoque com valor unitário abaixo de R$ 100,00;
		 */

		long quantidade = mongoDatabase.getCollection("livro")
				.count(and(lt(Livro.VALOR, 100), gt(Livro.QTD_ESTOQUE, 0)));

		System.out.println("######### Questão 2-e #########");
		System.out.println("Quantidade de livros em estoque com valor unitário abaixo de 100 : " + quantidade);

	}
}
