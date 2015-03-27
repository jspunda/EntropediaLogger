
package gui;

import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JFrame;

/**
 * @author Laurens van den Bercken
 */
public class MainWindow extends JFrame {
    
    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;
    
    public MainWindow () {
        super();
        setSize(WIDTH, HEIGHT);
        setTitle("Entropia Logger");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());
        JButton exit = new JButton("Exit");
        JButton start = new JButton("Start");
        JButton finish = new JButton("Finish");
        Listener listen = new Listener();
        exit.addActionListener(listen);
        start.addActionListener(listen);
        finish.addActionListener(listen);
        add(exit);
        add(start);
        add(finish);
    }
    
}
