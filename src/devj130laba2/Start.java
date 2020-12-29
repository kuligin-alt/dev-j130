/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package devj130laba2;

import java.sql.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Илья
 */
public class Start {

    static final String url = "jdbc:mysql://localhost:3306/DEV_J130_KuliginIlya?serverTimezone=Europe/Moscow";
    
    public static void main(String[] args) throws DocumentException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            System.out.println("Ошибка загрузки класса Driver");
        }
        
        DbServer dbs = new DbServer();
        Authors newAuthor = new Authors(10, "New Autor", "No data");
        Authors newAuthor2 = new Authors(2, "Tom Hawkins", "new author");
        Documents newDoc = new Documents(5, "New report", "Text for new", new Date(2020-11-11), 4);

        //dbs.addAuthor(newAuthor);
        
        //dbs.addDocument(newDoc, newAuthor);

        //dbs.findDocumentByAuthor(newAuthor2); 
        
        //dbs.findDocumentByContent("ul");
        
        //dbs.deleteAuthor(newAuthor);
        
        //dbs.deleteAuthor(5);
    }
    
}
