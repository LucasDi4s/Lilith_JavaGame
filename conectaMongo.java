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

    public void insertValues(String usuario, int Senha) {
        System.out.println("Método insertValues()");
        //conexão mongo
        MongoClient mongo = new MongoClient("localhost", 27017);
        MongoDatabase db = mongo.getDatabase("jogo");
        MongoCollection<Document> docs = db.getCollection("jogo");
        Entrada user = createUser(usuario, Senha);
//cria um user obj da classe conectar, 
//chamando o método createUser() - logo abaixo
        Document doc = createDocument(user);
//cria um doc que referencia o conteúdo de user do createDocument()
//obs, o Banco só entende objetos do tipo Document, 
        docs.insertOne(doc);//insere no mongo o conteúdo de doc
        getValues();
        System.out.println("insertValues ok");
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
    public Entrada createUser(String usuario, int senha) {
        //esse método deve ser uma entrada 
        //externa (interface, scanner ou JOptionPanel
        Entrada u = new Entrada();
        u.setUsuario(usuario);
        u.setSenha(senha);
        return u;
    }

    public Document createDocument(Entrada user) {
        Document docBuilder = new Document();
        docBuilder.append("_usuario", user.getUsuario());
        docBuilder.append("nome", user.getSenha());

        return docBuilder;
    }

    public void updateValues() {

        System.out.println("updateValues");
        //Entrada user = createUser();
        MongoClient mongo = new MongoClient("localhost", 27017);

        MongoDatabase db = mongo.getDatabase("jogo");
        MongoCollection<Document> docs = db.getCollection("jogo");

        docs.updateOne(Filters.eq("nome", "Crishna"), Updates.set("cidadenasc", "Santa Maria - RS"));
        System.out.println("Documento teve sucesso no update...");
        for (Document doc : docs.find()) {
            System.out.println("item update: " + doc);
        }

    }

    public void deleteValues() {
        System.out.println("deleteValues");
        //Entrada user = createUser();
        MongoClient mongo = new MongoClient("localhost", 27017);

        MongoDatabase db = mongo.getDatabase("turmaB");
        MongoCollection<Document> docs = db.getCollection("turmaB");

        docs.deleteOne(Filters.eq("nome", "Maria"));
        System.out.println("Documento teve sucesso no delete...");
        for (Document doc : docs.find()) {
            System.out.println("item update: " + doc);
        }

    }

}
