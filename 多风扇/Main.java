import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class Fan extends JPanel{
    private JButton start = new JButton("start");
    private JButton stop = new JButton("stop");
    private JButton reset = new JButton("reset");
    private int start_ang = 0;
    private boolean status = false;
    private boolean isRunning = true;
    Thread t = new Thread();

    public void stop_run(){
        status = false;
        isRunning = false;
    }

    public void start_run(){
        if(status == false){
            status = true;
            isRunning = true;
            t = new Thread(() -> {
                while(isRunning){
                    start_ang+=1;
                    try{
                        Thread.sleep(2);
                    }catch (InterruptedException ee){
                        ee.printStackTrace();
                    }
                    repaint();
                }
            }); 
            t.start();
        }
    }

    public Fan(){
        this.add(start);
        this.add(stop);
        this.add(reset);

        start.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                start_run();
            }
        });

        stop.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                stop_run();
            }
        });

        reset.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                start_ang = 0;
                repaint();
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int centerX = getWidth() / 2;
        int centerY = getHeight() / 2;

        int radius = 50;

        // 画风扇的圆形部分
        g.setColor(Color.RED);
        g.fillArc(centerX - radius, centerY - radius, 2 * radius, 2 * radius, start_ang, 30);
        g.fillArc(centerX - radius, centerY - radius, 2 * radius, 2 * radius, start_ang+90, 30);
        g.fillArc(centerX - radius, centerY - radius, 2 * radius, 2 * radius, start_ang+180, 30);
        g.fillArc(centerX - radius, centerY - radius, 2 * radius, 2 * radius, start_ang+270, 30);
    }
}

class window extends JFrame{
    private JButton start_all = new JButton("satrt_all");
    private JButton stop_all = new JButton("stop_all");

    public window(){
        //设置大小位置
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int screenw = screenSize.width;
        int screenh = screenSize.height;
        int windowsWidth = 800;
        int windowsHeight = 300;
        int x = (screenw - windowsWidth)/2;
        int y = (screenh - windowsHeight)/2;

        Container contentPane = this.getContentPane();
        JPanel genControl = new JPanel();
        genControl.setLayout(new FlowLayout());
        genControl.add(start_all);
        genControl.add(stop_all);

        Fan fan1 = new Fan();  
        Fan fan2 = new Fan();  
        Fan fan3 = new Fan();  

        start_all.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                fan1.start_run();
                fan2.start_run();
                fan3.start_run();
            }
        });

        stop_all.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                fan1.stop_run();
                fan2.stop_run();
                fan3.stop_run();
            }
        });

        contentPane.setLayout(new BorderLayout());
        contentPane.add(fan1,BorderLayout.WEST);
        contentPane.add(fan2,BorderLayout.CENTER);
        contentPane.add(fan3,BorderLayout.EAST);
        contentPane.add(genControl,BorderLayout.SOUTH);
        this.setBounds(x,y,windowsWidth,windowsHeight);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
public class Main{
    public static void main(String [] args){
        new window();
    }
}
