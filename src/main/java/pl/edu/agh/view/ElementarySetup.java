package pl.edu.agh.view;


import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import pl.edu.agh.AutomatonView;
 
 
public class ElementarySetup extends Setup 
{
	private JLabel sizeLabel = new JLabel("Size");
	private JTextField sizeField = new JTextField(10);
	private JLabel ruleLabel = new JLabel("Rule");
	private JTextField ruleField = new JTextField(10);
	private JButton start=new JButton("Start");
	private JButton insert=new JButton("Insert Structure");
	private static final long serialVersionUID = 1L;
	private AutomatonView automatonView;

	public ElementarySetup(AutomatonView theView,Dimension d) 
    {
        this.automatonView=theView;
        this.setPreferredSize(d);
        this.setLayout(new FlowLayout());
        this.add(sizeLabel);
        this.add(sizeField);
        this.add(ruleLabel);
        this.add(ruleField);
        this.add(start);
        this.setVisible(true);
    }    
	public void addStartListener(ActionListener listener)
	{
		start.addActionListener(listener);		
	}
	public void displayErrorMessage(String errorMessage)
	{
		
		JOptionPane.showMessageDialog(this, errorMessage);
	}
	public int getSizeField()
	{
		return Integer.parseInt(sizeField.getText());	
	}
	public Integer getRule()
	{
		return Integer.parseInt(ruleField.getText());	
	}
}
    
