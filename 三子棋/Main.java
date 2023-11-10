import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class Game extends JFrame{
    private JButton sendBtn;
    private JButton backBtn;
    private JTextField inputField;
    char[][][] map = new char[10][][];
    int step = 0;

    public Game(){
        Random rd = new Random();
        sendBtn = new JButton("确定");
        sendBtn.setBorderPainted(true);
        backBtn = new JButton("撤销");
        backBtn.setBorderPainted(true);
        inputField = new JTextField(10);
        map[0] = new char[][] { {'0', '1', '2'}, {'3', '4', '5'}, {'6', '7', '8'} };
        JPanel boardPanel = new JPanel();

        JPanel inputPanel = new JPanel();
        inputPanel.add(inputField);
        inputPanel.add(sendBtn);
        inputPanel.add(backBtn);
        inputPanel.setBackground(new Color(10,10,10));
        String s = "";
        s+="<html> -------------- <br>";
        s+="|  " + map[step][0][0] + "  |  " + map[step][0][1] + "  |  " + map[step][0][2] + "  |" + "<br>";
        s+=" -------------- <br>";
        s+="|  " + map[step][1][0] + "  |  " + map[step][1][1] + "  |  " + map[step][1][2] + "  |" + "<br>";
        s+=" -------------- <br>";
        s+="|  " + map[step][2][0] + "  |  " + map[step][2][1] + "  |  " + map[step][2][2] + "  |" + "<br>";
        s+=" -------------- <br>";

        JLabel label = new JLabel(s);
        boardPanel.add(label);

        sendBtn.addActionListener(new ActionListener() {

            //判断对局情况
            public int judge(){
                //colum
                for(int i=0;i<=2;i++){
                    if(map[step][i][0] == '*' && map[step][i][1] == '*' && map[step][i][2] == '*'){
                        return 1;
                    }
                    if(map[step][i][0] == 'O' && map[step][i][1] == 'O' && map[step][i][2] == 'O'){
                        return -1;
                    }
                    if(map[step][0][i] == '*' && map[step][1][i] == '*' && map[step][2][i] == '*'){
                        return 1;
                    }
                    if(map[step][0][i] == 'O' && map[step][1][i] == 'O' && map[step][2][i] == 'O'){
                        return -1;
                    }
                }
                if(map[step][0][0] == map[step][1][1] && map[step][2][2] == map[step][1][1] && map[step][1][1] == '*'){
                    return 1;
                }
                if(map[step][0][0] == map[step][1][1] && map[step][2][2] == map[step][1][1] && map[step][1][1] == 'O'){
                    return -1;
                }
                if(map[step][0][2] == map[step][1][1] && map[step][2][0] == map[step][1][1] && map[step][1][1] == '*'){
                    return 1;
                }
                if(map[step][0][2] == map[step][1][1] && map[step][2][0] == map[step][1][1] && map[step][1][1] == 'O'){
                    return -1;
                }
                return 0;
            }


            //获取新游戏面板
            public String update(){
                String s = "";
                s+="<html> -------------- <br>" ;
                s+="|  " + map[step][0][0] + "  |  " + map[step][0][1] + "  |  " + map[step][0][2] + "  |" + "<br>";
                s+=" -------------- <br>";
                s+="|  " + map[step][1][0] + "  |  " + map[step][1][1] + "  |  " + map[step][1][2] + "  |" + "<br>";
                s+=" -------------- <br>";
                s+="|  " + map[step][2][0] + "  |  " + map[step][2][1] + "  |  " + map[step][2][2] + "  |" + "<br>";
                s+=" -------------- <br>";
                return s;
            }

            @Override
            public void actionPerformed(ActionEvent e) {
                //输入坐标
                int position;
                int x;
                int y;
                try{
                    position = Integer.parseInt(inputField.getText());
                    x = position/3;
                    y = position%3;
                }catch(Exception err){
                    JOptionPane.showMessageDialog(null, "请输入数字");
                    return ;
                }
                

                //检查合法
                if(position >= 9 || position < 0){
                    JOptionPane.showMessageDialog(null, "别乱输入数字");
                    inputField.setText("");
                    return ;
                }
                if(map[step][x][y] == '*' || map[step][x][y] == 'O' ){
                    JOptionPane.showMessageDialog(null, "这里已经下过了嗷");
                    inputField.setText("");
                    return ;
                }  

                //更新游戏数据
                step++;
                map[step] = new char[3][];
                for(int i=0;i<=2;i++){
                    map[step][i] = Arrays.copyOf(map[step-1][i],map[step-1][i].length);
                }
                map[step][x][y] = '*';
                int robt = rd.nextInt(9);
                while(map[step][robt/3][robt%3] == '*' || map[step][robt/3][robt%3] == 'O'){
                    robt++;
                    robt%=9;
                    if(step == 5){
                        break;
                    }
                }
                if(step != 5){
                    map[step][robt/3][robt%3] = 'O';
                }

                //更新游戏面板
                inputField.setText("");
                boardPanel.removeAll();
                JLabel label = new JLabel(update()) ;
                boardPanel.add(label);
                boardPanel.updateUI();

                //判断对局情况 
                if(judge() == 1){
                    JOptionPane.showMessageDialog(null, "大聪明,你赢了");
                    return ;
                }
                if(judge() == -1){
                    JOptionPane.showMessageDialog(null, "机器人都打不过?");
                    return ;
                }
                if(step == 5){
                    JOptionPane.showMessageDialog(null, "平局");
                }
            }
        });


        backBtn.addActionListener(new ActionListener() {
            //获取新游戏面板
            public String update(){
                String s = "";
                s+="<html> -------------- <br>" ;
                s+="|  " + map[step][0][0] + "  |  " + map[step][0][1] + "  |  " + map[step][0][2] + "  |" + "<br>";
                s+=" -------------- <br>";
                s+="|  " + map[step][1][0] + "  |  " + map[step][1][1] + "  |  " + map[step][1][2] + "  |" + "<br>";
                s+=" -------------- <br>";
                s+="|  " + map[step][2][0] + "  |  " + map[step][2][1] + "  |  " + map[step][2][2] + "  |" + "<br>";
                s+=" -------------- <br>";
                return s;
            }

            @Override
            public void actionPerformed(ActionEvent e) {
                if(step == 0){
                    JOptionPane.showMessageDialog(null, "还点??");
                    return;
                }
                --step;  
                boardPanel.removeAll();
                JLabel label = new JLabel(update());
                boardPanel.add(label);
                boardPanel.updateUI();
            }
        });

        this.add(inputPanel,BorderLayout.SOUTH);
        this.add(boardPanel,BorderLayout.NORTH);
        this.setTitle("三子棋");
        this.setSize(280,220);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
}

public class Main{
    public static void main(String[] args){  
        new Game();
    }
}