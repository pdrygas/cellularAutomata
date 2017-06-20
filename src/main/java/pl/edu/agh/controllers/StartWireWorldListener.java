package pl.edu.agh.controllers;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.concurrent.ThreadLocalRandom;
import javax.swing.SwingUtilities;
import pl.edu.agh.AutomatonView;
import pl.edu.agh.SimulationGrid;
import pl.edu.agh.automatons.WireWorld;
import pl.edu.agh.coords.Coords2D;
import pl.edu.agh.factories.GeneralStateFactory;
import pl.edu.agh.hood.MoorNeighborhood;
import pl.edu.agh.hood.VonNeumanNeighborhood;
import pl.edu.agh.logic.Automaton;
import pl.edu.agh.logic.CellCoordinates;
import pl.edu.agh.logic.CellNeighborhood;
import pl.edu.agh.logic.CellState;
import pl.edu.agh.states.BinaryState;
import pl.edu.agh.states.QuadState;
import pl.edu.agh.states.WireElectronState;
import pl.edu.agh.view.WireWorldSetup;

public class StartWireWorldListener implements ActionListener
{
	private AutomatonView theView;
	private Automaton automaton;
	private Simulation simulation;
	
	public StartWireWorldListener(AutomatonView theView) 
	{
		super();
		this.theView = theView;
	}
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		if(simulation!=null)
			return;
		if(!theView.gameGridIsNull())
		{
			simulation=new Simulation();
			Thread t=new Thread(simulation);
			t.start();
			return;
		}
		int width;
		int height;
		try 
		{
			boolean wrap=setupMenuView().getWrappingCheck();
			height = setupMenuView().getWidthField();
			width = setupMenuView().getHeightField();
			int flag=0;
			if(setupMenuView().getMooreCheck()==setupMenuView().getNeumanCheck())
			{
				throw new NumberFormatException();
			}
			else if(setupMenuView().getNeumanCheck())
			{
				flag=1;
			}
			else
				flag=0;
			automaton=startingStructure(wrap,flag,height,width);
			simulation=new Simulation();
			SwingUtilities.invokeLater(new Runnable() 
	        {
	            @Override
	            public void run() 
	            {
	    			theView.setGameGrid(new SimulationGrid(automaton,
	    					new Dimension((height*4)+1,(width*4)+1)));

	            }
	        });
		}
		catch (NumberFormatException ex)
		{
			setupMenuView().displayErrorMessage("Wrong parameters");
		}
		Thread t=new Thread(simulation);
		t.start();
	}
	private WireWorldSetup setupMenuView() 
	{
		return (WireWorldSetup) theView.getSetupMenu();
	} 
	public void stopSimulation()
	{
		if(this.simulation==null)
			return;
		this.simulation.stop();
		this.simulation=null;
	}
	private Automaton startingStructure(boolean wrap,int flag,int width ,int height)
	{
		HashMap<CellCoordinates,CellState> map=new HashMap<CellCoordinates,CellState>();
		int count=8;
		for(int i=0;i<count;i++)
		{
			int row=0;
			int column=(width/count+1)*i+1;
			while(row<height)
			{
				int loss=ThreadLocalRandom.current().nextInt(0,60);
				if ((loss%2)==0)
				{
					if (column>0)
						column--;
					else column=width;
				}
				else
				{
					if (column<width-1)
						column++;
					else
						column=0;
				}
				map.put(new Coords2D(column,row),WireElectronState.WIRE);
				if(loss==59)
					map.put(new Coords2D(column,row),WireElectronState.ELECTRON_HEAD);
				row++;
					
			}
		}
		for(int i=0;i<height;i++)
			map.put(new Coords2D(i,0),WireElectronState.WIRE);
		for(int i=0;i<width;i++)
			map.put(new Coords2D(0,i),WireElectronState.WIRE);
		for(int i=0;i<height;i++)
			map.put(new Coords2D(i,width-1),WireElectronState.WIRE);
		for(int i=0;i<width;i++)
			map.put(new Coords2D(height-1,i),WireElectronState.WIRE);
	    CellNeighborhood hood;
	    if (flag==1)
	    {
	    	hood= new VonNeumanNeighborhood(1,width,height,wrap);
	    }
	    else
	    	hood= new MoorNeighborhood(1,width,height,wrap);
		GeneralStateFactory factory=new GeneralStateFactory(map,WireElectronState.VOID);
		Automaton automaton=new WireWorld(hood,factory,width,height);
		return automaton;
	}
	class Simulation implements Runnable
	{
		private volatile boolean running;
		@Override
		public void run() 
		{
			this.running=true;
			while (running) 
			{
            	try 
            	{
					repaint();
					automaton=automaton.nextState();
					((SimulationGrid) theView.getGameGrid()).setAutomaton(automaton);
					Thread.sleep(100);
				}
            	catch (InterruptedException e) 
            	{
				}
            }
        }
		public synchronized void stop()
		{
			this.running=false;
		}
        private void repaint() 
        {
        	SwingUtilities.invokeLater(new Runnable()
        	{
        		@Override
        		public void run() 
        		{
        			theView.getGameGrid().repaint();
        		}
        	});
        }		
	}
}