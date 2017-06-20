package pl.edu.agh.controllers;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.SwingUtilities;
import pl.edu.agh.AutomatonView;
import pl.edu.agh.view.ElementarySetup;

public class ElementaryListener implements ActionListener
{
	private AutomatonView theView;
	public ElementaryListener(AutomatonView theView) 
	{
		super();
		this.theView = theView;
	}
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		SwingUtilities.invokeLater(new Runnable() 
        {
            @Override
            public void run() 
            {
        		theView.getGameOfLife().setVisible(false);
        		theView.getWireWorld().setVisible(false);
        		theView.getLangtonAnt().setVisible(false);
        		theView.getElementary().setVisible(false);
        		theView.setSetupMenu(new ElementarySetup(theView,new Dimension(600,100)));
        		StartElementaryListener start=new StartElementaryListener(theView);
            	((ElementarySetup)theView.getSetupMenu()).addStartListener(start); 
            }
        });
	}
}