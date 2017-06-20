package pl.edu.agh;

import java.awt.FlowLayout;
import java.awt.event.ActionListener;

import javax.swing.*;

import pl.edu.agh.view.Setup;

public class AutomatonView extends JFrame
{
	private static final long serialVersionUID = 1L;
	private JButton gameOfLife = new JButton("Game Of Life");
	private JButton wireWorld = new JButton("Wire World");
	private JButton langtonAnt = new JButton("Langton's Ant");
	private JButton elementary = new JButton("Elementary Automata");
	private volatile SimulationGrid gameGrid=null;
	private Setup setupMenu=null;
	private JPanel mainPanel;

	public AutomatonView()
	{
		mainPanel = new JPanel();
		mainPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		mainPanel.add(getGameOfLife());
		mainPanel.add(wireWorld);
		mainPanel.add(langtonAnt);
		mainPanel.add(elementary);
	    this.setLayout(new BoxLayout(this.getContentPane(),BoxLayout.Y_AXIS));
        this.add(mainPanel);
        this.pack();
        this.setLocationByPlatform(true);
        this.setSize(600, 100);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
	}
	void addGameOfLifeListener(ActionListener listener)
	{
		getGameOfLife().addActionListener(listener);
    }
	void addWireWorldListener(ActionListener listener)
	{
		wireWorld.addActionListener(listener);
    }
	void addLangtonAntListener(ActionListener listener)
	{
		langtonAnt.addActionListener(listener);
    }
	void addElementaryListener(ActionListener listener)
	{
		elementary.addActionListener(listener);
    }
	public JButton getWireWorld()
	{
		return wireWorld;
	}
	public void setWireWorld(JButton wireWorld) 
	{
		this.wireWorld = wireWorld;
	}
	public JButton getLangtonAnt()
	{
		return langtonAnt;
	}
	public void setLangtonAnt(JButton langtonAnt) 
	{
		this.langtonAnt = langtonAnt;
	}
	public JButton getElementary()
	{
		return elementary;
	}
	public void setElementary(JButton elementary)
	{
		this.elementary = elementary;
	}
	public JButton getGameOfLife() {
		return gameOfLife;
	}
	public void setGameOfLife(JButton gameOfLife) 
	{
		this.gameOfLife = gameOfLife;
	}
	public boolean gameGridIsNull()
	{
		return gameGrid==null?true:false;
	}
	public synchronized SimulationGrid getGameGrid()
	{
		while(gameGrid==null)
			try 
			{
				wait();
			}
			catch (InterruptedException e) 
			{
			}
		return gameGrid;
	}
	public synchronized void setGameGrid(SimulationGrid gameGrid) 
	{
		if(gameGrid==null)
		{
			this.mainPanel.remove(this.gameGrid);
			this.gameGrid=null;
		}
		else
		{
			this.gameGrid = gameGrid;
			this.mainPanel.add(this.gameGrid);
		}
		this.pack();
    	this.setVisible(true);
    	notifyAll();
	}
	public Setup getSetupMenu() 
	{
		return setupMenu;
	}
	public void setSetupMenu(Setup setupMenu) 
	{
		this.setupMenu = setupMenu;
		this.add(this.setupMenu);
		this.pack();
    	this.setVisible(true);
    	this.repaint();
    	this.revalidate();
	}
}