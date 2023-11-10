import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class Game extends JFrame{
	private JButton sendBtn;
    private JButton restartBtn;
    private JButton quitBtn;
    private JTextField inputField;
    int num;


    public Game(){
    	Random rd = new Random();
    	this.num = rd.nextInt(1000);

    	//options
    	JPanel optionPanel = new JPanel();
    	sendBtn = new JButton("send");
    	restartBtn = new JButton("restart");
    	quitBtn = new JButton("quit");
    	optionPanel.add(sendBtn);
    	optionPanel.add(restartBtn);
    	optionPanel.add(quitBtn);

    	//enter
    	JPanel inputPanel = new JPanel();
    	inputField = new JTextField(10);
    	JLabel label = new JLabel("please enter a num :");
    	inputPanel.add(label);
    	inputPanel.add(inputField);

    	//sendBtn
    	sendBtn.addActionListener(new ActionListener() {
    		@Override
            public void actionPerformed(ActionEvent e) {
            	//enter num
            	int get;
                try{
                    get = Integer.parseInt(inputField.getText());
                }catch(Exception err){
                    JOptionPane.showMessageDialog(null, "please enter Number");
                    return ;
                }

                if(get > num){
                	JOptionPane.showMessageDialog(null, "bigger");
                    return ;
                }
                if(get < num){
                	JOptionPane.showMessageDialog(null, "smaller");
                    return ;
                }
                if(get == num){
                	JOptionPane.showMessageDialog(null, "correct");
                    return ;
                }
            }
    	});

    	restartBtn.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) {
    			num = rd.nextInt(1000);
    			JOptionPane.showMessageDialog(null, "done!");
    		}
    	});

    	quitBtn.addActionListener(new ActionListener(){
    		public void actionPerformed(ActionEvent e) {
    			System.exit(0);
    		}
    	});

    	//system
    	this.add(optionPanel,BorderLayout.SOUTH);
        this.add(inputPanel,BorderLayout.CENTER);
        this.setTitle("guess");
        this.setSize(280,220);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
}

public class Main2{
	public static void main(String [] args){
		new Game();
	}
}