package com.company;

import javax.swing.*;
import java.awt.*;

/**
 * Main creates the blank master GUI Window ready for inputRows to be added.
 *
 * @author Peter Bedford
 * @version 1.0.0
 */
public class Main {
    static JPanel bodyContainer;
    static int rowCounter;
    static JComboBox referenceTypeSelection;
    static inputRow[] rowArray = new inputRow[99];

    final static String version = "1.0.0";
    final static String[] referenceTypes = {"Book","Book Section","Journal Article","Periodical Article","Website","Conference Proceedings","Film"};

    public static void main(String[] args) {

        JLabel versionLabel = new JLabel();
        versionLabel.setText("Version: " + version);
        versionLabel.setVerticalAlignment(JLabel.TOP);
        JFrame inputframe = new JFrame();
        inputframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        inputframe.setSize(1250,850);
        inputframe.setLayout(new BorderLayout(10,10));
        ImageIcon appIcon = new ImageIcon(new ImageIcon("assets/images/appIcon.jpg").getImage());
        inputframe.setIconImage(appIcon.getImage());

        JPanel headContainer = new JPanel();
        JPanel headerInfoBar = new JPanel();
        JPanel footContainer = new JPanel();
        bodyContainer = new JPanel();
        JScrollPane scroll = new JScrollPane();

        headContainer.setPreferredSize(new Dimension(1250,150));
        footContainer.setPreferredSize(new Dimension(1250,100));

        headerInfoBar.setBackground(Color.lightGray);
        headerInfoBar.setPreferredSize(new Dimension(50,50));

        headContainer.setLayout(new BorderLayout());
        bodyContainer.setLayout(new BoxLayout(bodyContainer,BoxLayout.PAGE_AXIS));

        JButton newRowButton = new JButton();
        newRowButton.setText("New Row");
        newRowButton.addActionListener(e ->newInputRow());

        JButton updatePreviewButton = new JButton();
        updatePreviewButton.setText("Preview");
        updatePreviewButton.addActionListener(e -> updatePreview());

        JButton generateReferencesButton = new JButton();
        generateReferencesButton.setText("Generate References");
        generateReferencesButton.addActionListener(e -> outputReferences());

        referenceTypeSelection = new JComboBox(referenceTypes);

        JLabel headerLabel = new JLabel();
        headerLabel.setIcon(new ImageIcon("assets/images/header.png"));

        scroll.setViewportView(bodyContainer);
        scroll.getVerticalScrollBar().setUnitIncrement(40);

        headContainer.add(headerLabel, BorderLayout.WEST);
        headContainer.add(versionLabel, BorderLayout.EAST);
        headContainer.add(headerInfoBar,BorderLayout.SOUTH);

        footContainer.add(newRowButton);
        footContainer.add(referenceTypeSelection);
        footContainer.add(generateReferencesButton);
        footContainer.add(updatePreviewButton);

        inputframe.add(headContainer,BorderLayout.NORTH);
        inputframe.add(scroll,BorderLayout.CENTER);
        inputframe.add(footContainer,BorderLayout.SOUTH);

        inputframe.setVisible(true);
    }

    /**
     * newInputRow uses the index of the referenceTypeSelection comboBox to create new input rows and adds them to rowArray sequentially Is called by the new row button.
     */
    public static void newInputRow(){

        switch (referenceTypeSelection.getSelectedIndex()) {
            case 0 -> rowArray[rowCounter] = new inputRowBook(rowCounter);
            case 1 -> rowArray[rowCounter] = new inputRowBookSection(rowCounter);
            case 2 -> rowArray[rowCounter] = new inputRowJournal(rowCounter);
            case 3 -> rowArray[rowCounter] = new inputRowPeriodical(rowCounter);
            case 4 -> rowArray[rowCounter] = new inputRowWebsite(rowCounter);
            case 5 -> rowArray[rowCounter] = new inputRowConference(rowCounter);
            case 6 -> rowArray[rowCounter] = new inputRowFilm(rowCounter);
        }
        bodyContainer.add(rowArray[rowCounter]);
        rowCounter += 1;
        bodyContainer.repaint();
        bodyContainer.revalidate();

    }

    /**
     * updatePreview itterates over all inputRows that haven't been deleted and calls updatePreview() for that object. It is called by the update preview button.
     */
    public static void updatePreview(){
        for(inputRow i : rowArray){
            if(i != null) {
                i.updatePreview();
            }
        }
    }

    /**
     * outputReferences itterates over all inputRows that haven't been delete. Calculates their absolute ID, prints them to the console and builds the output Array. Is called by the generate reference button.
     * @return Returns an ordered array of all reference strings.
     */
    public static String[] outputReferences(){
        String[] output = new String[99];
        int absoluteRowID = 0;

        updatePreview();

            System.out.println("Reference Engine START ==============");
            for (inputRow i : rowArray) {
                if (i != null) {
                    String ref = i.generateReferences();
                    output[absoluteRowID] = "<sup>" + (absoluteRowID + 1) + "</sup>" + ref;
                    System.out.println((absoluteRowID + 1) + ": " + ref);
                    absoluteRowID++;
                }
            }

            new outputWindow(output);
            System.out.println("Reference Engine END ===============");

        return output;
    }
}
