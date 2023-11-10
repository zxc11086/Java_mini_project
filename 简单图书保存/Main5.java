import java.io.*;
import java.util.*;




class Book {
    String name;
    String author;
    String press;
    int no;
    double price;

    public Book(String _name, String _author, String _press, int _no, double _price) {
        this.name = _name;
        this.author = _author;
        this.press = _press;
        this.no = _no;
        this.price = _price;
    } 

    public String toString() {
        return name+" "+author+" "+press +" "+no+" "+price;
    } 
}


class Library{
    Book[] books=new Book[99999999];
    int nums=0;

    public void addBook(Book book) {
        books[nums]=book;
        nums++;
    }

    public void persist() throws IOException {
        File file=new File("./books.dat");
        if(!file.exists()){
            file.createNewFile();
        }
        FileWriter fw=new FileWriter(file);
        for(int i=0;i<nums;i++){
            fw.write(books[i].toString());
            fw.write("\n");
        }
        fw.flush();
        fw.close();
    }

    public Book[] restore() throws IOException {
        Library library = new Library();
        File file=new File("./books.dat");
        if(!file.exists()) {
            file.createNewFile();
        }
        BufferedReader input=null;
        input=new BufferedReader(new InputStreamReader(new FileInputStream("./books.dat")));
        String s=input.readLine();
        while(s!=null){
            String[] s1=s.split(" ");
            Book book=new Book(s1[0],s1[1],s1[2],Integer.parseInt(s1[3]),Double.parseDouble(s1[4]));
            library.addBook(book);
            s=input.readLine(); 
        }
        input.close();
        return library.books;
    }
}


public class Main5 {
    public static void main(String[] args) throws IOException  {
        Library library = new Library();
        library.addBook(new Book("大秦帝国1", "czk", "百花出版社", 10001, 38.88));
        library.addBook(new Book("大秦帝国2", "czk", "百花出版社", 10002, 38.88));
        library.addBook(new Book("大秦帝国3", "czk", "百花出版社", 10003, 38.88));
        library.persist();
        Book books[] = library.restore();
        for(int i=0;i<library.nums;i++){
            System.out.println(books[i].toString());
        }
    }
}
