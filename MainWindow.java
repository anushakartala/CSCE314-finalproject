import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.math.BigInteger;
import javax.swing.JPanel;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.SwingConstants;

public class MainWindow extends JFrame
{
	private static final long serialVersionUID = -3880026026104218593L;
	private Primes m_Primes;
	private JTextField tfPrimeFileName;
	private JTextField tfCrossFileName;
	private JLabel lblPrimeCount;
	private JLabel lblCrossCount;
	private JLabel lblLargestPrime;
	private JLabel lblLargestCross;
	private JLabel lblStatus;
	
	MainWindow(String name, Primes p)
	{
		m_Primes = p;
		JFrame f=new JFrame(Config.Title);
		
		GridBagLayout gridLayout = new GridBagLayout();
		f.getContentPane().setBackground(new Color(52, 0, 0));
		f.getContentPane().setLayout(gridLayout);
		
		GridBagConstraints gbcFrame = new GridBagConstraints();
		gbcFrame.fill = GridBagConstraints.HORIZONTAL;
		gbcFrame.anchor = GridBagConstraints.WEST;
		gbcFrame.ipady = 20;
		gbcFrame.weightx = .5;
		gbcFrame.insets = new Insets(1,2,0,0);
		gbcFrame.gridx = 0;
		gbcFrame.gridy = 0;
		
		GridBagConstraints gbcPanel = new GridBagConstraints();
		gbcPanel.anchor = GridBagConstraints.WEST;
		gbcPanel.weightx = .5;
		gbcPanel.insets = new Insets(1,2,0,0);
		gbcPanel.gridx = 0;
		gbcPanel.gridy = 0;
		
		JPanel pnlGenerate = new JPanel();
		pnlGenerate.setLayout(new GridBagLayout());
		
		tfPrimeFileName = new JTextField(Config.PrimeFileName);
		//lblCount.setLabelFor(tfCount);
		//tfCount.setColumns(180);
		gbcPanel.weightx = 0.9;
		gbcPanel.weighty=0.33;
		gbcPanel.fill = GridBagConstraints.HORIZONTAL;
		pnlGenerate.add(tfPrimeFileName, gbcPanel);
		
		lblPrimeCount = new JLabel("0");
		lblPrimeCount.setFont(new Font("Tahoma", Font.PLAIN, 12));
		gbcPanel.weightx=0.1;
		gbcPanel.gridx = 1;
		gbcPanel.weighty=0.33;
		gbcFrame.anchor = GridBagConstraints.EAST;
		pnlGenerate.add(lblPrimeCount, gbcPanel);
		
		JLabel lblPrimes = new JLabel("Primes File");
		lblPrimes.setFont(new Font("Tahoma", Font.PLAIN, 24));
		gbcPanel.gridx=0;
		gbcPanel.gridy = 1;
		gbcPanel.weightx =0.8;
		gbcPanel.weighty=0.67;
		gbcPanel.anchor = GridBagConstraints.WEST;
		pnlGenerate.add(lblPrimes, gbcPanel);
		
		JButton btnLoadPrimes = new JButton("Load");
		btnLoadPrimes.addActionListener(new ActionListener() { 
		    public void actionPerformed(ActionEvent e) { 
		        FileAccess.loadPrimes(p, tfPrimeFileName.getText());
		        lblStatus.setText("Status: Number of primes loaded is " + p.primeCount() + ".");
		        updateStats();
		    } 
		});
		gbcPanel.gridx = 1;	
		gbcPanel.weightx = 0.1;
		gbcPanel.weighty=0.67;
		gbcPanel.fill = GridBagConstraints.BOTH;
		pnlGenerate.add(btnLoadPrimes, gbcPanel);
		
		JButton btnSavePrimes = new JButton("Save");
		btnSavePrimes.addActionListener(new ActionListener() { 
		    public void actionPerformed(ActionEvent e) { 
		        FileAccess.savePrimes(p, tfPrimeFileName.getText());
		        lblStatus.setText("Status: Number of primes saved to the file " + tfPrimeFileName.getText() + " is " + p.primeCount() );
		        updateStats();
		    } 
		});
		gbcPanel.gridx = 2;	
		gbcPanel.weightx = 0.1;
		gbcPanel.weighty=0.67;
		gbcPanel.anchor = GridBagConstraints.EAST;
		gbcPanel.fill = GridBagConstraints.BOTH;
		pnlGenerate.add(btnSavePrimes, gbcPanel);
		
		f.add(pnlGenerate, gbcFrame);
		
		JPanel pnlCross = new JPanel();
		pnlCross.setLayout(new GridBagLayout());
		tfCrossFileName = new JTextField(Config.CrossFileName);
		gbcPanel.gridx=0;
		gbcPanel.gridy=0;
		gbcPanel.weightx = 0.9;
		gbcPanel.anchor = GridBagConstraints.WEST;
		gbcPanel.fill = GridBagConstraints.HORIZONTAL;
		pnlCross.add(tfCrossFileName, gbcPanel);
		
		lblCrossCount = new JLabel("0");
		lblCrossCount.setFont(new Font("Tahoma", Font.PLAIN, 12));
		gbcPanel.weightx=0.1;
		gbcPanel.gridx = 1;
		//gbcPanel.gridwidth = 2;	
		gbcPanel.anchor = GridBagConstraints.EAST;
		pnlCross.add(lblCrossCount, gbcPanel);
		
		JLabel lblCrossFile = new JLabel("Hexagon Cross File");
		lblCrossFile.setFont(new Font("Tahoma", Font.PLAIN, 24));
		gbcPanel.gridx=0;
		gbcPanel.gridy = 1;
		gbcPanel.weightx =0.8;
		gbcPanel.anchor = GridBagConstraints.WEST;
		pnlCross.add(lblCrossFile, gbcPanel);
		
		JButton btnLoadCross = new JButton("Load");
		btnLoadCross.addActionListener(new ActionListener() { 
		    public void actionPerformed(ActionEvent e) { 
		        FileAccess.loadCrosses(p, tfCrossFileName.getText());
		        lblStatus.setText("Status: Number of crosses loaded is " + p.crossesCount() + ".");
		        updateStats();
		    } 
		});
		gbcPanel.gridx = 1;	
		gbcPanel.weightx = 0.1;
		gbcPanel.fill = GridBagConstraints.BOTH;
		pnlCross.add(btnLoadCross, gbcPanel);
		
		JButton btnSaveCross = new JButton("Save");
		btnSaveCross.addActionListener(new ActionListener() { 
		    public void actionPerformed(ActionEvent e) { 
		        FileAccess.saveCrosses(p, tfCrossFileName.getText());
		        lblStatus.setText("Status: Number of crosses saved to the file " + tfCrossFileName.getText() + " is " + p.crossesCount() );
		        updateStats();
		    } 
		});
		gbcPanel.gridx = 2;	
		gbcPanel.weightx = 0.1;
		gbcPanel.anchor = GridBagConstraints.EAST;
		gbcPanel.fill = GridBagConstraints.BOTH;
		pnlCross.add(btnSaveCross, gbcPanel);
		gbcFrame.gridy = 1;
		f.add(pnlCross, gbcFrame);
		
		JPanel pnlButtons = new JPanel();
		pnlButtons.setLayout(new GridBagLayout());
		JButton btnGeneratePrimes = new JButton("Generate Primes");
		btnGeneratePrimes.addActionListener(new ActionListener() { 
		    public void actionPerformed(ActionEvent e) { 
		        popupGeneratePrimes();
		    } 
		});
		gbcPanel.gridx=0;
		gbcPanel.gridy=0;
		gbcPanel.weightx = 0.25;
		gbcPanel.weighty = 1;
		gbcPanel.gridheight = 2;
		gbcPanel.anchor = GridBagConstraints.WEST;
		gbcPanel.fill = GridBagConstraints.BOTH;
		pnlButtons.add(btnGeneratePrimes, gbcPanel);
		
		lblLargestPrime = new JLabel("The largest prime has 0 digits");
		gbcPanel.gridx=1;
		gbcPanel.weightx = 0.5;
		gbcPanel.weighty = 0.5;
		gbcPanel.gridheight = 1;
		gbcPanel.fill = GridBagConstraints.BOTH;
		pnlButtons.add(lblLargestPrime, gbcPanel);
		
		lblLargestCross = new JLabel("The largest cross has 0 and 0 digits");
		gbcPanel.gridx=1;
		gbcPanel.gridy=1;
		//gbcPanel.ipady = 5;
		gbcPanel.weightx = 0.5;
		gbcPanel.weighty = 0.5;
		gbcPanel.gridheight = 1;
		gbcPanel.fill = GridBagConstraints.CENTER;
		pnlButtons.add(lblLargestCross, gbcPanel);
		
		JButton btnGenerateHexes = new JButton("Generate Crosses");	
		btnGenerateHexes.addActionListener(new ActionListener() {
		      public void actionPerformed(ActionEvent e) {
		       		m_Primes.generateTwinPrimes();
		       		m_Primes.generateHexPrimes();
		       		lblStatus.setText("Status: Excited. Crosses have been generated.");
		       		updateStats();
		      }
	    });
		gbcPanel.gridx=2;
		gbcPanel.gridy=0;
		gbcPanel.weightx = 0.25;
		gbcPanel.weighty = 1;
		gbcPanel.gridheight = 2;
		gbcPanel.anchor = GridBagConstraints.EAST;
		gbcPanel.fill = GridBagConstraints.BOTH;
		pnlButtons.add(btnGenerateHexes, gbcPanel);
			
		gbcFrame.gridy = 2;
		f.add(pnlButtons, gbcFrame);
		
		JPanel pnlMainStatus = new JPanel();
		pnlMainStatus.setLayout(new GridBagLayout());

		gbcPanel.anchor = GridBagConstraints.SOUTHWEST;
		gbcPanel.weightx = .5;
		gbcPanel.insets = new Insets(1,2,0,0);
		gbcPanel.gridx = 0;
		gbcPanel.gridy = 1;

		lblStatus = new JLabel("Status: Bored.");
		lblStatus.setFont(new Font("Tahoma", Font.PLAIN, 12));
		pnlMainStatus.add(lblStatus, gbcPanel);
		
		gbcFrame.gridy = 3;
		f.add(pnlMainStatus, gbcFrame);
		
		
		
		f.setSize(1000, 400);
		f.setLocationRelativeTo(null);
		f.pack();
		f.setVisible(true);//making the frame visible  
		
	}

	protected void popupGeneratePrimes()
	{
		JDialog dPrimes = new JDialog(this, "Prime Number Generation");
		GridBagLayout gridLayout = new GridBagLayout();
		dPrimes.getContentPane().setBackground(new Color(52, 0, 0));
		dPrimes.getContentPane().setLayout(gridLayout);
		
		GridBagConstraints gbcDialog = new GridBagConstraints();
		gbcDialog.fill = GridBagConstraints.HORIZONTAL;
		gbcDialog.anchor = GridBagConstraints.WEST;
		gbcDialog.ipady = 10;
		gbcDialog.weightx = .5;
		gbcDialog.insets = new Insets(1,2,0,0);
		gbcDialog.gridx = 0;
		gbcDialog.gridy = 0;
		
		GridBagConstraints gbcPanel = new GridBagConstraints();
		gbcPanel.anchor = GridBagConstraints.WEST;
		gbcPanel.weightx = .5;
		gbcPanel.insets = new Insets(1,2,0,0);
		gbcPanel.gridx = 0;
		gbcPanel.gridy = 0;
		
		JPanel pnlGenerate = new JPanel();
		pnlGenerate.setLayout(new GridBagLayout());
		
		JLabel lblCount = new JLabel("Number of Primes to Generate");
		lblCount.setFont(new Font("Tahoma", Font.PLAIN, 12));
		pnlGenerate.add(lblCount, gbcPanel);
		
		JTextField tfCount = new JTextField();
		lblCount.setLabelFor(tfCount);
		tfCount.setColumns(30);
		gbcPanel.gridx = 1;
		pnlGenerate.add(tfCount, gbcPanel);
		
		JLabel lblStart = new JLabel("Starting Number (does not have to be prime)");
		lblStart.setFont(new Font("Tahoma", Font.PLAIN, 12));
		gbcPanel.gridx = 0;
		gbcPanel.gridy = 1;
		pnlGenerate.add(lblStart, gbcPanel);
		
		JTextField tfStart = new JTextField();
		lblStart.setLabelFor(tfStart);
		tfStart.setColumns(30);
		gbcPanel.gridx = 1;
		pnlGenerate.add(tfStart, gbcPanel);
		
		dPrimes.add(pnlGenerate, gbcDialog);
		
		JPanel pnlButtons = new JPanel();
		pnlButtons.setLayout(new GridBagLayout());
		
		JButton btnGeneratePrimes = new JButton("Generate Primes");
		btnGeneratePrimes.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
      	try
      	{
      		
      		BigInteger start = new BigInteger(tfStart.getText());
      		int count = Integer.parseInt(tfCount.getText());
       		m_Primes.generatePrimes(start, count);
       		
       		lblStatus.setText("Status: Excited. Primes have been generated.");
       		updateStats();
      		dPrimes.dispose();
      	}
      	catch(NumberFormatException ex)
      	{
      		lblStatus.setText("You failed to type in an integer successfully. You are terrible at math. Shame.");
      		dPrimes.dispose();
      	}
      	
      }
    });
		gbcPanel.gridx = 0;
		gbcPanel.gridy = 0;
		pnlButtons.add(btnGeneratePrimes, gbcPanel);
		
		JButton btnCancel = new JButton("Cancel Generation");
		btnCancel.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
      	dPrimes.dispose();
      }
    });
		gbcPanel.anchor = GridBagConstraints.EAST;
		gbcPanel.gridx = 1;
		pnlButtons.add(btnCancel, gbcPanel);		
		
		gbcDialog.gridy = 1;
		dPrimes.add(pnlButtons, gbcDialog);
		
		JPanel pnlStatus = new JPanel();
		pnlStatus.setLayout(new GridBagLayout());

		gbcPanel.anchor = GridBagConstraints.SOUTHWEST;
		gbcPanel.weightx = .5;
		gbcPanel.insets = new Insets(1,2,0,0);
		gbcPanel.gridx = 0;
		gbcPanel.gridy = 1;

		JLabel lblNotice = new JLabel("Warning: This application is single threaded, and will freeze while generating primes.");
		lblNotice.setFont(new Font("Tahoma", Font.PLAIN, 12));
		pnlStatus.add(lblNotice, gbcPanel);
		
		gbcDialog.gridy = 2;
		dPrimes.add(pnlStatus, gbcDialog);
		
		dPrimes.setSize(200,200);
		dPrimes.pack(); // Knowing what this is and why it is needed is important. You should read the documentation on this function!
		dPrimes.setVisible(true);		
	}

	// This function updates all the GUI statistics. (# of primes, # of crosses, etc)
	private void updateStats()
	{
		if(m_Primes.primeCount()>0)
		{
			lblPrimeCount.setText(Integer.toString(m_Primes.primeCount()));
			lblLargestPrime.setText("The largest prime has " + m_Primes.sizeofLastPrime()  +" digits");
		}
		if(m_Primes.crossesCount()>0)
		{
			lblCrossCount.setText(Integer.toString(m_Primes.crossesCount()));
			lblLargestCross.setText("The largest cross has " + m_Primes.sizeofLastCross().left()  + " and " + m_Primes.sizeofLastCross().right() + " digits");
 	}}

}
