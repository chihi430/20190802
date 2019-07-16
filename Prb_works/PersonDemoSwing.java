package lineage;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * Tests the Person class using the PersonDemo class and
 * shows the test result in a window using the Swing toolkit.
 * 
 * @author Taewoong Jeon
 * @version 2.0 2013-02-18
 */
public class PersonDemoSwing extends JPanel implements ActionListener {
	private JButton startButton;
    private JTextArea textArea;

    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event dispatch thread.
     */
    private static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("PersonDemoSwing");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Add contents to the window.
        frame.add(new PersonDemoSwing());

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }

    /**
     * Run tests of the Person class using the PersonDemo class.
     * @return a string of text lines showing the test result
     */
	private String testPerson() {
		PersonDemo myFamilyMemberTest = new PersonDemo();
			
		myFamilyMemberTest.setUp();
		myFamilyMemberTest.testChildOf();
		myFamilyMemberTest.testSiblingOf();
		myFamilyMemberTest.testBrotherOf();
		myFamilyMemberTest.testSisterOf();

		return myFamilyMemberTest.getTestOutput();
	}

    public static void main(String[] args) {
        //Schedule a job for the event dispatch thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }

	public PersonDemoSwing() {
        super(new GridBagLayout());

        startButton = new JButton("Press here to start test-running");
        startButton.setForeground(Color.blue);
        startButton.addActionListener(this);

        textArea = new JTextArea(10, 30);
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);

        //Add Components to this panel.
        GridBagConstraints c = new GridBagConstraints();
        c.gridwidth = GridBagConstraints.REMAINDER;

        c.fill = GridBagConstraints.HORIZONTAL;
        add(startButton, c);

        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1.0;
        c.weighty = 1.0;
        add(scrollPane, c);      
	}

    public void actionPerformed(ActionEvent evt) {
        textArea.append(testPerson());
        startButton.removeActionListener(this);
        startButton.setVisible(false);
        textArea.setCaretPosition(0);
    }
}