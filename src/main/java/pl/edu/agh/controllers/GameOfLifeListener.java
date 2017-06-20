package pl.edu.agh.controllers;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.SwingUtilities;

import pl.edu.agh.AutomatonView;
import pl.edu.agh.view.GameOfLifeSetup;


public class GameOfLifeListener implements ActionListener
{
	private AutomatonView theView;
	public GameOfLifeListener(AutomatonView theView) 
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
        		theView.setSetupMenu(new GameOfLifeSetup(theView,new Dimension(600,100)));
        		StartGameOfLifeListener start=new StartGameOfLifeListener(theView);
            	((GameOfLifeSetup)theView.getSetupMenu()).addStartListener(start); 
            	StopGameOfLifeListener stop=new StopGameOfLifeListener();
            	stop.setListener(start,theView);
            	((GameOfLifeSetup)theView.getSetupMenu()).addStopListener(stop);
            }
        });
	}
}