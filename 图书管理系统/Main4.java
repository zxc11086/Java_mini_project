/*
    **说明
        书籍信息采用空格隔开，请勿在书名、书号之中添加空格


    **运行代码
        首先会在当前目录下检测是否存在books的文件，如果不存在，就创建一个，用来保存书籍信息;
        students 文件同理

        所有的操作都是在内存中完成，只有关闭窗口时会将内存写回文件



*/


import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.lang.*;


class Book{
    private String name;
    private String no;
    private String student_no;

    public void setName(String _name){
        this.name = _name;
    }

    public void setNo(String _no){
        this.no = _no;
    }

    public void setStudent_no(String _student_no){
        this.student_no = _student_no;
    }

    public String getName(){
        return this.name;
    }

    public String getNo(){
        return this.no;
    }

    public String getStudent_no(){
        return this.student_no;
    }


    public void Borrow(){

    }

    public void Return(){

    }
}

class Student{
    private String name;
    private String no;
    private String books_no;

    public void setName(String _name){
        this.name = _name;
    }

    public void setNo(String _no){
        this.no = _no;
    }

    public void setBooks_no(String _books_no){
        this.books_no = _books_no;
    }

    public void Show(){
        System.out.println("Student[name= " + this.name + " Student_no= " + this.no + " books=" + this.books_no + "]");
    }
}

class frame extends JFrame{
    //按钮
    private JButton borrow = new JButton("借书");
    private JButton back = new JButton("还书");
    private JButton append = new JButton("添加书籍");
    private JButton query = new JButton("借阅查询");
    private JButton list = new JButton("书籍查询");
    private ArrayList<Book> books = new ArrayList<>();
    private ArrayList<Student> students = new ArrayList<>();
    //Jpanel
    private JPanel options = new JPanel();

    public frame(){
        //读取文件
            //书籍
        File booksfile = new File("./books");
        if(!booksfile.exists()){
            try{
                booksfile.createNewFile();
            } catch (Exception e){
                System.out.println("书籍文件创建失败哦~");
            }
        }
            //学生
        File studentsfile = new File("./students");
        if(!studentsfile.exists()){
            try{
                studentsfile.createNewFile();
            } catch (Exception e){
                System.out.println("学生文件创建失败哦~");
            }
        }

        //将书籍信息加载到内存
        try {
            BufferedReader reader = new BufferedReader(new FileReader("./books"));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] info = line.split(" ");
                Book book = new Book();
                book.setName(info[0]);
                book.setNo(info[1]);
                try{
                    book.setStudent_no(info[2]);
                } catch (Exception e) {
                    System.out.println(e);
                }
               
                books.add(book);
                System.out.println(line);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //将学生信息加载到内存
        try {
            BufferedReader reader = new BufferedReader(new FileReader("./students"));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] info = line.split(" ");
                Student student = new Student();
                student.setName(info[0]);
                student.setNo(info[1]);
                try{
                    student.setBooks_no(info[2]);
                } catch(Exception e){
                    System.out.println(e);
                }
                students.add(student);
                System.out.println(line);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //获取窗口大小
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int screenw = screenSize.width;//屏幕的宽
        int screenh = screenSize.height;//屏幕的高
        //设置屏幕的宽和高
        int windowsWedth = 800;
        int windowsHeight = 450;
        //设置剧中
        //坐标x
        int x = (screenw - windowsWedth)/2;
        //坐标y
        int y = (screenh -  windowsHeight)/2;

        //Jpanel添加按钮
        options.add(borrow);
        options.add(back);
        options.add(append);
        options.add(query);
        options.add(list);

        //绑定借书功能
        borrow.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                String book_name = JOptionPane.showInputDialog("请输入书名：");
                if(book_name == null){
                    return ;
                }
                boolean find = false;
                Book book = new Book();
                for(Book tmp : books){
                    if(tmp.getName().equals(book_name)){
                        find = true;
                        book = tmp;
                        break;
                    }
                }
                if(find){
                    if(book.getStudent_no() == null){
                        String student_no = JOptionPane.showInputDialog("请输入你的学号：");
                        book.setStudent_no(student_no);
                        JOptionPane.showMessageDialog(null, "借阅成功！");
                    }
                    else{
                        JOptionPane.showMessageDialog(null, "书籍已经被" + book.getStudent_no() + "借走了嗷~");
                    }
                }
                else{
                    JOptionPane.showMessageDialog(null, "未发现书籍");
                }
            }
        });

        //还书
        back.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                String student_no = JOptionPane.showInputDialog("请输入学号: ");
                if(student_no == null){
                    return ;
                }
                ArrayList<Book> borrowed_books = new ArrayList<>();
                boolean find = false;
                for(Book book : books){
                    if(book.getStudent_no() != null){
                        if(book.getStudent_no().equals(student_no)){
                            find = true;
                            borrowed_books.add(book);
                        }
                    }
                }
                if(!find){
                    JOptionPane.showMessageDialog(null, "未找到记录");
                }
                else{
                    JPanel list = new JPanel();
                    JButton sure_back = new JButton("确认归还");
                    JCheckBox[] checkBoxs = new JCheckBox[borrowed_books.size()];
                    for(int i=0; i<borrowed_books.size(); i++){
                        checkBoxs[i] = new JCheckBox(borrowed_books.get(i).getName());
                        list.add(checkBoxs[i]);
                    }


                    JFrame listBooks = new JFrame("还书");

                    //确认归还
                    sure_back.addActionListener(new ActionListener(){
                        public void actionPerformed(ActionEvent e){
                            boolean find = false;
                            for(int i=0; i<borrowed_books.size(); i++){
                                if(checkBoxs[i].isSelected()){
                                    find = true;
                                    for(Book tmp : books){
                                        if(tmp.getNo().equals(borrowed_books.get(i).getNo())){
                                            tmp.setStudent_no(null);
                                        }
                                    }
                                }
                            }
                            if(find){
                                JOptionPane.showMessageDialog(null,"还书完成");
                                listBooks.dispose();
                            }
                            else{
                                JOptionPane.showMessageDialog(null,"请选择要归还的图书");
                            }
                        }
                    });

                    //JFrame listBooks = new JFrame("还书");
                    listBooks.add(list);
                    listBooks.add(sure_back,BorderLayout.SOUTH);
                    listBooks.setVisible(true);
                    listBooks.setBounds(x+200,y+50,300,400);
                    //JOptionPane.showMessageDialog(null, info);
                }
            }
        });
        //添加书籍
        append.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                String book_name = JOptionPane.showInputDialog("请输入书名：");
                if(book_name != null){
                    if(book_name.equals("")){
                        JOptionPane.showMessageDialog(null, "请输入书名！");
                        return ;
                    }
                    else{
                        String book_no = JOptionPane.showInputDialog("请输入书ID: ");
                        if(book_no != null){
                            if(book_no.equals("")){
                                JOptionPane.showMessageDialog(null, "请输入书ID！");
                                return ;
                            }
                            Book book = new Book();
                            book.setNo(book_no);
                            book.setName(book_name);
                            books.add(book);
                            JOptionPane.showMessageDialog(null, "添加成功");
                        }
                    }
                } 
            }
        });

        //借阅查询
        query.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                String student_no = JOptionPane.showInputDialog("请输入学号: ");
                String info = "<html>";
                boolean find = false;
                for(Book book : books){
                    if(book.getStudent_no() != null){
                        if(book.getStudent_no().equals(student_no)){
                            find = true;
                            info += book.getName() + " " + book.getNo() + "<br>";
                        }
                    }
                }
                if(!find){
                    JOptionPane.showMessageDialog(null, "未找到记录");
                }
                else{
                    JOptionPane.showMessageDialog(null, info);
                }
            }
        });

        //展示书籍
        list.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                String info = "<html>";
                boolean find = false;
                for(Book book : books){
                    find = true;
                    info += book.getName() + " " + book.getNo() + "<br>";
                }
                if(!find){
                    JOptionPane.showMessageDialog(null, "未找到记录");
                }
                else{
                    JOptionPane.showMessageDialog(null, info);
                }
            }
        });

        // 程序关闭时将内存写回文件
        Thread shutdownHook = new Thread() {
            @Override
            public void run() {
                try{
                    FileWriter fw = new FileWriter("./books");
                    for(Book book : books){
                        fw.write(book.getName() + " " + book.getNo());
                        if(book.getStudent_no() != null){
                            fw.write(" " + book.getStudent_no() + "\n");
                        }
                        else{
                            fw.write("\n");
                        }
                    }
                    fw.close();
                } catch (Exception e) {
                    System.out.println(e);
                }
                
            }
        };
        
        // 注册关闭钩子
        Runtime.getRuntime().addShutdownHook(shutdownHook);
        
        //整活
        String makelive="<html><body><h2>CSDN关注</h2><a href='qwe' >嗯嗯你说的对</a></body></html>";
        Font font = new Font("宋体",Font.PLAIN,20);
        JLabel makeLive = new JLabel(makelive);
        makeLive.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    Desktop.getDesktop().browse(new java.net.URI("https://blog.csdn.net/weixin_61133168?type=blog"));
                } catch (Exception ex) {
                    ex.printStackTrace();
               }
            }
        });
        makeLive.setFont(font);
        makeLive.setForeground(Color.RED);
        makeLive.setHorizontalAlignment(SwingConstants.CENTER);

        //基础设置
        this.setTitle("图书管理");
        //this.add(editorPane,BorderLayout.CENTER);
        this.add(makeLive,BorderLayout.CENTER);
        this.add(options,BorderLayout.SOUTH);
        this.setVisible(true);
        this.setBounds(x,y,windowsWedth,windowsHeight);
        this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
    }
    
}

public class Main4{
    public static void main(String [] args){
        Scanner in = new Scanner(System.in);
        new frame();
        in.close();
    }
}