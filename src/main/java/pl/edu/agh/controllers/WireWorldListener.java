package pl.edu.agh.controllers;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.SwingUtilities;
import pl.edu.agh.AutomatonView;
import pl.edu.agh.view.WireWorldSetup;

public class WireWorldListener implements ActionListener
{
	private AutomatonView theView;
	public WireWorldListener(AutomatonView theView) 
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
        		theView.setSetupMenu(new WireWorldSetup(theView,new Dimension(600,100)));
        		StartWireWorldListener start=new StartWireWorldListener(theView);
            	((WireWorldSetup)theView.getSetupMenu()).addStartListener(start); 
            	StopWireWorldListener stop=new StopWireWorldListener();
            	stop.setListener(start,theView);
            	((WireWorldSetup)theView.getSetupMenu()).addStopListener(stop);
            }
        });
	}
}