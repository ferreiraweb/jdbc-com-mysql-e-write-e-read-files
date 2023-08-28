package daos;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import domain.Book;
import connections.Conexao;

import java.io.*;

public class BookDao {

    public static boolean save(Book book) throws SQLException {

        String sql = "INSERT INTO Book (titulo, autor, paginas) VALUE (?, ?, ?)";

        PreparedStatement ps = null;
        Connection conn = Conexao.getConnection();
        ps = conn.prepareStatement(sql);
       
        ps.setString(1, book.getTitulo());
        ps.setString(2, book.getAutor());
        ps.setInt(3, book.getPaginas());
        
        return ps.execute();
       // ps.close();
    }

    public static List<Book> findAll() throws SQLException {

        List<Book> books = new ArrayList<Book>();
        String sql = "SELECT * FROM Livraria.book";
        PreparedStatement ps = Conexao.getConnection().prepareStatement(sql);
        ResultSet resultSet = ps.executeQuery();

        while(resultSet.next()) {
            Book nextBook =  new Book(
                    resultSet.getString("titulo"),
                    resultSet.getString("autor"),
                    resultSet.getInt("paginas")
                );
                nextBook.setId( resultSet.getInt("titulo"));

            books.add(nextBook);
        }

        return books;
    }

    public static void writeFileCSV(String pathFile, List<Book> books) throws IOException {
        File file = new File(pathFile);
        FileWriter fw = new FileWriter(file);
        PrintWriter pw = new PrintWriter(fw);

        books.forEach(book -> pw.println(book.getTitulo() + ";" + book.getAutor() + ";" + book.getPaginas()));

        fw.close();
        pw.close();
    }

    public static void writeDataBase(String pathFile) throws Exception {

        FileReader reader = new FileReader(pathFile);
        BufferedReader buffer = new BufferedReader(reader);

        String row = buffer.readLine();
        while(row != null) {
            
            String[] items = row.split(";");
            Book book = new Book(items[0],items[1], Integer.parseInt(items[2]));
            System.out.println(book.getTitulo());

            try {
                save(book);    
            } catch (Exception ex) {
                throw new Exception(ex.getMessage());
            }

            row = buffer.readLine();
        }

        buffer.close();
        reader.close();
    }

}
