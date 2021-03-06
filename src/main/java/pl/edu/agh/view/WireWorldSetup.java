package pl.edu.agh.view;


import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import pl.edu.agh.AutomatonView;
import pl.edu.agh.logic.Automaton;
 
 
public class WireWorldSetup extends Setup 
{
	private JLabel widthLabel = new JLabel("Width");
	private JTextField widthField = new JTextField(10);
	private JLabel heightLabel = new JLabel("Height");
	private JTextField heightField = new JTextField(10);
	private JCheckBox neuman=new JCheckBox("VonNeumann");
	private JCheckBox moore=new JCheckBox("Moor");
	private JCheckBox wrap=new JCheckBox("Wrapping");
	private JButton start=new JButton("Start");
	private JButton stop=new JButton("Stop");
	private JButton insert=new JButton("Insert Structure");
	private static final long serialVersionUID = 1L;
	private AutomatonView automatonView;

	public WireWorldSetup(AutomatonView theView,Dimension d) 
    {
        this.automatonView=theView;
        this.setPreferredSize(d);
        this.setLayout(new FlowLayout());
        this.add(widthLabel);
        this.add(widthField);
        this.add(heightLabel);
        this.add(heightField);
        this.add(neuman);
        this.add(moore);
        this.add(wrap);
        this.add(start);
        this.add(stop);
        this.setVisible(true);
    }    
	public void addStartListener(ActionListener listener)
	{
		start.addActionListener(listener);		
	}
	public void addStopListener(ActionListener listener)
	{
		stop.addActionListener(listener);		
	}
	public void displayErrorMessage(String errorMessage)
	{
		
		JOptionPane.showMessageDialog(this, errorMessage);
	}
	public int getWidthField()
	{
		return Integer.parseInt(widthField.getText());	
	}
	public int getHeightField()
	{
		return Integer.parseInt(heightField.getText());	
	}
	public boolean getMooreCheck()
	{
		return moore.isSelected()?true:false;
	}
	public boolean getNeumanCheck()
	{
		return neuman.isSelected()?true:false;
	}
	public boolean getWrappingCheck()
	{
		return wrap.isSelected()?true:false;
	}
}
    
