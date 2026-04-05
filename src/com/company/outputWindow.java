package com.company;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * Creates the output window where the user can copy the output.
 *
 * @author Peter Bedford
 * @version 1.0.0
 */
public class outputWindow extends JFrame {

    outputWindow(String[] input){

        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        this.setSize(1250,850);
        this.setVisible(true);
        this.setLayout(new BorderLayout(10,10));
        ImageIcon appIcon = new ImageIcon(new ImageIcon("assets/images/appIcon.jpg").getImage());
        this.setIconImage(appIcon.getImage());

        JTextPane outputTextPane = new JTextPane();
        outputTextPane.setContentType("text/html");
        outputTextPane.setEditable(false);
        outputTextPane.setBackground(null);
        outputTextPane.setBorder(new EmptyBorder(20, 20, 10, 10));
        outputTextPane.setBackground(Color.white);

        JPanel footContainer = new JPanel();
        footContainer.setPreferredSize(new Dimension(1250,100));

        JButton closeWindow = new JButton("Close");
        closeWindow.addActionListener(e -> this.dispose());


        String Out = buildOutput(input);
        outputTextPane.setText(Out);
        System.out.println("Reference Engine HTML OUT START ==============");
        System.out.println(Out);
        System.out.println("Reference Engine HTML OUT END ================");
        footContainer.add(closeWindow);

        this.add(outputTextPane, BorderLayout.CENTER);
        this.add(footContainer,BorderLayout.SOUTH);

    }

    /**
     * Formats the reference strings with absolute numbers and encapsulates the output in html tags to allow the use of linebreaks.
     * @param input Array of refference strings.
     * @return Formatted string of HTML ready for the GUI.
     */
        public String buildOutput(String[] input){

        String buildOutput = "<html>";
            for(int i=0;i<input.length;i++){
                if(input[i] != null) {
                    buildOutput += input[i] + "<br>";
                }
            }
        buildOutput+="</html>";
        return buildOutput;
        }
}
