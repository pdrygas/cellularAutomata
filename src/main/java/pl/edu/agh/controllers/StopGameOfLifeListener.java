package pl.edu.agh.controllers;
 
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import pl.edu.agh.AutomatonView;
 
public class StopGameOfLifeListener implements ActionListener 
{     
    private volatile StartGameOfLifeListener listener;
    private AutomatonView theView;
 
    public void setListener(StartGameOfLifeListener listener,AutomatonView theView) 
    {
        this.listener = listener;
        this.theView=theView;
    }
    @Override
    public void actionPerformed(ActionEvent event) 
    {
    	if (listener!=null)
    	{
    		listener.stopSimulation();		
    	}
    }
 
}