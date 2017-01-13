package net.tompy.ast.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class ParamDialog extends JDialog implements ActionListener 
{

	private final JPanel contentPanel = new JPanel();
	private JTextField txtName;

	/**
	 * Create the dialog.
	 */
	public ParamDialog() 
	{
		setModal(true);
		setTitle("Parameters");
		setBounds(100, 100, 450, 116);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		JLabel lblName = new JLabel("Name");
		contentPanel.add(lblName);
		
		txtName = new JTextField();
		contentPanel.add(txtName);
		txtName.setColumns(10);

		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.CENTER));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);
		
		JButton closeButton = new JButton("Close");
		closeButton.addActionListener( this );
		closeButton.setActionCommand("Close");
		buttonPane.add(closeButton);
		getRootPane().setDefaultButton(closeButton);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) 
	{
		switch( arg0.getActionCommand() )
		{
		case "Close" :
			this.setVisible( false );
			break;
		}
	}
	
	public String getTxtName()
	{
		return txtName.getText();
	}

}
