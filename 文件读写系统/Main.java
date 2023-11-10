import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.lang.*;
import java.nio.file.*;

class myFile extends JFrame{
    private JMenuBar JMB=new JMenuBar();
    private JMenu menu=new JMenu("File"); 
    private JMenuItem menu_new = new JMenuItem("New");
    private JMenuItem menu_open = new JMenuItem("Open");
    private JMenuItem menu_save = new JMenuItem("Save");
    private JMenuItem menu_exit = new JMenuItem("Exit");
    private JTextArea text_area = new JTextArea("#input here......");
    JScrollPane text_panel = new JScrollPane(text_area);
    JPanel panel = new JPanel();

    public myFile(){
        //获取窗口大小
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int screenw = screenSize.width;//屏幕的宽
        int screenh = screenSize.height;//屏幕的高
        //设置屏幕的宽和高
        int windowsWidth = 800;
        int windowsHeight = 450;
        //设置剧中
        //坐标x
        int x = (screenw - windowsWidth)/2;
        //坐标y
        int y = (screenh -  windowsHeight)/2;

        menu.add(menu_new);
        menu.add(menu_open);
        menu.add(menu_save);
        menu.add(menu_exit);
        JMB.add(menu);

        menu_new.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                text_panel = new JScrollPane(text_area);
                panel.removeAll();
                text_panel.setPreferredSize(new Dimension(windowsWidth,windowsHeight-60));
                panel.add(text_panel);
                panel.revalidate(); 
                panel.repaint();
            }
        });


        menu_open.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                JFileChooser fc = new JFileChooser();
                fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
                int result = fc.showOpenDialog(null);
                if(result == JFileChooser.APPROVE_OPTION){
                    try{
                        File selectedFile = fc.getSelectedFile();
                        BufferedReader br = new BufferedReader(new FileReader(selectedFile));
                        String line;
                        text_area.setText("");
                        while((line = br.readLine()) != null){
                            text_area.append(line);
                            text_area.append("\n");
                        }
                    } catch(Exception ee){
                        System.out.println(ee);
                    }
                    text_panel = new JScrollPane(text_area);
                    text_panel.setPreferredSize(new Dimension(windowsWidth,windowsHeight-60));
                    panel.removeAll();
                    panel.add(text_panel);
                    panel.revalidate();
                    panel.repaint();
                }
                else if (result == JFileChooser.CANCEL_OPTION){
                    System.out.println("no file selected!");

                }
            }
        });

        menu_save.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                String s = text_area.getText();
                //System.out.println(s);
                JFileChooser fc = new JFileChooser();
                int result = fc.showSaveDialog(null);
                if(result == JFileChooser.APPROVE_OPTION){
                    try{
                        File newFile = fc.getSelectedFile();
                        if(!newFile.exists()){
                            newFile.createNewFile();
                        }
                        byte bytes[] = s.getBytes();
                        FileOutputStream fos = new FileOutputStream(newFile);
                        fos.write(bytes);
                        fos.close();
                    } catch (Exception ee){
                        System.out.println(ee);
                    }
                }
            }
        });

        menu_exit.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                System.exit(0);
            }
        });


        this.setJMenuBar(JMB);
        this.add(panel,BorderLayout.WEST);
        this.setBounds(x,y,windowsWidth,windowsHeight);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}



public class Main{
    public static void main(String [] args){
        new myFile();
    } 
}