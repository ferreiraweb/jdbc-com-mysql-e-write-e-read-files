import java.util.List;

import daos.BookDao;
import domain.Book;

public class App {
    public static void main(String[] args)  {
        
        Book book = new Book("JavaScript Ninja", "Deitel", 377);

        try {
            /* 
            BookDao.save(book);
            List<Book> saveBooks = BookDao.findAll();
            showBooks(saveBooks);
            BookDao.writeFileCSV("./livros.csv", saveBooks);
            */

           BookDao.writeDataBase("./livros.csv");

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
        //System.out.println("Finalizado");
    }

    static void showBooks(List<Book> books) {
        books.forEach(book -> {
            System.out.println(book.getTitulo() + " - " + book.getAutor());
        });
    }
}




