package fr.unice.polytech.devint.dinfowritor.controlers;

import java.awt.Dimension;

import javax.swing.JFrame;

import fr.unice.polytech.devint.dinfowritor.views.FillInfosView;

@SuppressWarnings("serial")
public class WriteInfoController extends JFrame {
    
    private FillInfosView fiw;
    
    public WriteInfoController() {
        this.fiw = new FillInfosView(this);
        this.init();
    }
    
    public void init() {
        this.setContentPane(fiw);
        
        Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        
        this.setMinimumSize(new Dimension(1024,768));
        this.setPreferredSize(new Dimension(1024,768));
        this.setMaximumSize(screenSize);
        
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        

        this.setLocation((screenSize.width-this.getWidth())/2,(screenSize.height-this.getHeight())/2);
        
        this.setVisible(true);
    }
}
