package Mongo;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import org.bson.Document;

public class conectaMongo {

    public void getValues() {
        System.out.println("Método getValues()");
        MongoClient mongo = new MongoClient("localhost", 27017);
        MongoDatabase db = mongo.getDatabase("jogo");
        MongoCollection<Document> docs = db.getCollection("jogo");
        for (Document doc : docs.find()) {
            System.out.println("item: " + doc);
        }
        System.out.println("getValues() - ok - finalizou");
    }

    public void insertValues(String usuario, int senha, int score) {
        System.out.println("Método insertValues()");
        MongoClient mongo = new MongoClient("localhost", 27017);
        MongoDatabase db = mongo.getDatabase("jogo");
        MongoCollection<Document> docs = db.getCollection("jogo");
        Entrada user = createUser(usuario, senha, score);
        Document doc = createDocument(user);
        docs.insertOne(doc);
        getValues();
        System.out.println("insertValues ok");
    }

    public Entrada createUser(String usuario, int senha, int score) {
        Entrada u = new Entrada(usuario, senha, score);
        return u;
    }

    public boolean findValuesUsuarioeSenha(String usuario, int senha) {

        int x = 0;
        int m = 0;
        boolean t = false;
        MongoClient mongo = new MongoClient("localhost", 27017);
        MongoDatabase db = mongo.getDatabase("jogo");
        MongoCollection<Document> docs = db.getCollection("jogo");

        for (Document doc : docs.find(Filters.eq("usuario", usuario))) {
            System.out.println("Achou pelo nome:" + doc);
            Document z = doc;
            if (z.isEmpty()) {
                x = 1;

            }
        }
        for (Document doc : docs.find(Filters.eq("senha", senha))) {
            System.out.println("Achou pelo id:" + doc);
            Document y = doc;
            if (y.isEmpty()) {
                m = 1;
            }
        }
        if ((x == 0) && (m == 0)) {
            t = true;
        }
        return t;
    }

    public void updateScore(String usuario, int novoScore) {
        System.out.println("Método updateScore()");
        MongoClient mongo = new MongoClient("localhost", 27017);
        MongoDatabase db = mongo.getDatabase("jogo");
        MongoCollection<Document> docs = db.getCollection("jogo");

        // Atualiza o score do usuário
        docs.updateOne(Filters.eq("usuario", usuario), Updates.set("score", novoScore));

        System.out.println("Score atualizado com sucesso!");
        getValues();
    }


    public Document createDocument(Entrada user) {
        Document docBuilder = new Document();
        docBuilder.append("usuario", user.getUsuario());
        docBuilder.append("senha", user.getSenha());
        docBuilder.append("score", user.getScore());  // Adicionei o campo score
        return docBuilder;
    }

    public int getScore(String usuario) {
        MongoClient mongo = new MongoClient("localhost", 27017);
        MongoDatabase db = mongo.getDatabase("jogo");
        MongoCollection<Document> docs = db.getCollection("jogo");

        Document userDocument = docs.find(Filters.eq("usuario", usuario)).first();
        if (userDocument != null) {
            return userDocument.getInteger("score", -1);
        } else {
            return -1; // Retorna -1 se o usuário não for encontrado
        }
    }

}
