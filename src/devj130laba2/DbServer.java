/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package devj130laba2;

import static devj130laba2.Start.url;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Илья
 */
public class DbServer implements IDbService {

    @Override
    public boolean addAuthor(Authors author) throws DocumentException {
        
        try {
            Connection con = DriverManager.getConnection(url, "KuliginIlya", "Vb#ts&61sf");
            PreparedStatement stmt = null;

            try {
                if ((author.getAuthor() == null) && (author.getNotes() != null)) {
                    
                    stmt = con.prepareStatement("UPDATE autors SET note = (?) WHERE autor_id = (?)");

                    stmt.setString(1, author.getNotes());
                    stmt.setInt(2, author.getAuthor_id());
                    
                } else {
                
                    stmt = con.prepareStatement("INSERT INTO autors (autor_id, autor_name, note) VALUES (?,?,?)");

                    stmt.setInt(1, author.getAuthor_id());
                    stmt.setString(2, author.getAuthor());
                    stmt.setString(3, author.getNotes());
                    
                }   
                
                stmt.executeUpdate();
            } catch (SQLException ex) {
                System.out.println("Ошибка при добавлении автора");
                return false;
                
            } finally {
                if(stmt != null) {
                    stmt.close();
                }
                con.close();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean addDocument(Documents doc, Authors author) throws DocumentException {
        try {
            Connection con = DriverManager.getConnection(url, "KuliginIlya", "Vb#ts&61sf");
            PreparedStatement stmt = null;

            try {
                
                stmt = con.prepareStatement("INSERT INTO documents (document_id, document_name, text, creation_date, autor_id) VALUES (?,?,?,?,?)");
                
                stmt.setInt(1, doc.getDocument_id());
                stmt.setString(2, doc.getTitle());
                stmt.setString(3, doc.getText());
                stmt.setDate(4, doc.getDate());
                stmt.setInt(5, doc.getAuthor_id());
                
                
                stmt.executeUpdate();
                
            } finally {
                if(stmt != null) {
                    stmt.close();
                }
                con.close();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return true;
    }

    @Override
    public Documents[] findDocumentByAuthor(Authors author) throws DocumentException {
        
        Documents[] array = null;
        
        try {
            Connection con = DriverManager.getConnection(url, "KuliginIlya", "Vb#ts&61sf");
            PreparedStatement stmt = null;
            //Statement stmt = null;
            ResultSet rs = null;

            try {
                stmt = con.prepareStatement("SELECT * FROM documents WHERE autor_id = ?");
                
                stmt.setInt(1, author.getAuthor_id());
                
                rs = stmt.executeQuery();
                

                List<Documents> result = new ArrayList();

                while (rs.next()) {
                    int document_id = rs.getInt("document_id"); 
                    String title = rs.getString("document_name"); 
                    String text = rs.getString("text"); 
                    Date date = rs.getDate("creation_date"); 
                    int autor_id = rs.getInt("autor_id");

                    Documents doc = new Documents(document_id, title, text, date, autor_id);

                    result.add(doc);
                }
                
                for(Documents docum : result) {
                    System.out.println(docum);
                }
                
                array = new Documents[result.size()];
                result.toArray(array);

            } finally {
                if(rs != null) {
                    rs.close();
                }
                if(stmt != null) {
                    stmt.close();
                }
                con.close();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return array;
    }

    @Override
    public Documents[] findDocumentByContent(String content) throws DocumentException {
        Documents[] array = null;
        String contentDoc;
        contentDoc = ("%" + content + "%");
        
        try {
            Connection con = DriverManager.getConnection(url, "KuliginIlya", "Vb#ts&61sf");
            PreparedStatement stmt = null;
            //Statement stmt = null;
            ResultSet rs = null;

            try {
                stmt = con.prepareStatement("SELECT * FROM documents WHERE document_name LIKE ? OR text LIKE ?");
                
                stmt.setString(1, contentDoc);
                stmt.setString(2, contentDoc);
                
                rs = stmt.executeQuery();
                

                List<Documents> result = new ArrayList();

                while (rs.next()) {
                    int document_id = rs.getInt("document_id"); 
                    String title = rs.getString("document_name"); 
                    String text = rs.getString("text"); 
                    Date date = rs.getDate("creation_date"); 
                    int autor_id = rs.getInt("autor_id");

                    Documents doc = new Documents(document_id, title, text, date, autor_id);

                    result.add(doc);
                }
                
                for(Documents docum : result) {
                    System.out.println(docum);
                }
                
                array = new Documents[result.size()];
                result.toArray(array);

            } finally {
                if(rs != null) {
                    rs.close();
                }
                if(stmt != null) {
                    stmt.close();
                }
                con.close();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return array;
    }

    @Override
    public boolean deleteAuthor(Authors author) throws DocumentException {
        try {
            Connection con = DriverManager.getConnection(url, "KuliginIlya", "Vb#ts&61sf");
            PreparedStatement stmt = null;

            try {
                
                stmt = con.prepareStatement("DELETE FROM documents WHERE autor_id = ?");
                stmt.setInt(1, author.getAuthor_id());
                stmt.executeUpdate();
                
                stmt = con.prepareStatement("DELETE FROM autors WHERE autor_id = ?");
                stmt.setInt(1, author.getAuthor_id());
                stmt.executeUpdate();
                
            } finally {
                if(stmt != null) {
                    stmt.close();
                }
                con.close();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return true;
    }

    @Override
    public boolean deleteAuthor(int id) throws DocumentException {
        try {
            Connection con = DriverManager.getConnection(url, "KuliginIlya", "Vb#ts&61sf");
            PreparedStatement stmt = null;

            try {
                int autorId;
                autorId = id;
                
                stmt = con.prepareStatement("DELETE FROM documents WHERE autor_id = ?");
                stmt.setInt(1, id);
                stmt.executeUpdate();
                
                stmt = con.prepareStatement("DELETE FROM autors WHERE autor_id = ?");
                stmt.setInt(1, id);
                stmt.executeUpdate();
                
            } finally {
                if(stmt != null) {
                    stmt.close();
                }
                con.close();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return true;
    }

    @Override
    public void close() throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
