package com.company;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;

import static com.company.Main.*;

/**
 * inputRow is an abstract class which means it's never used directly. It is template used by child classes. It creates an empty input row ready for unique fields to be added.
 *
 * @author Peter Bedford
 * @version 1.0.0
 */
public abstract class inputRow extends JPanel {
    //Declaring variables.
    int row;
    JPanel inputRowBodycol1;
    JLabel referencePreviewLabel;
    JTextField[] attributeInput = new JTextField[10]; //Array to store attribute inputs
    JLabel[] attributeLabel = new JLabel[10]; //Array to store attribute labels
    String[] attributeData = new String[25]; //Array to store attribute data


     public inputRow(int rowCount){
        row = rowCount;
        this.setPreferredSize(new Dimension(1000,300)); //may not be needed
        this.setMaximumSize(new Dimension(1000,300));
        this.setLayout(new BorderLayout());
        this.setBorder(BorderFactory.createCompoundBorder(new EmptyBorder(10, 10, 10, 10),new LineBorder(Color.black)));

        JPanel inputRowInner = new JPanel();
        inputRowInner.setPreferredSize(new Dimension(950,250));

        JPanel inputRowHead = new JPanel();
        inputRowHead.setPreferredSize(new Dimension(950,40));
        inputRowHead.setLayout(new BorderLayout());

        JPanel inputRowBody = new JPanel();
        inputRowBody.setLayout( new GridLayout());

        inputRowBodycol1 = new JPanel();
        JPanel inputRowBodycol2 = new JPanel();

        inputRowBodycol1.setLayout(null);
        inputRowBodycol2.setLayout(null);

        inputRowBodycol1.setPreferredSize(new Dimension(475, 210));
        inputRowBodycol2.setPreferredSize(new Dimension(475, 210));
        inputRowBody.add(inputRowBodycol1);
        inputRowBody.add(inputRowBodycol2);
         JLabel inputRowTitle = new JLabel();
        inputRowTitle.setText("Reference Type: "+referenceTypeSelection.getSelectedItem());

        referencePreviewLabel = new JLabel();
        referencePreviewLabel.setBounds(10,10,465,200);

        inputRowBodycol2.add(referencePreviewLabel);

        inputRowHead.add(inputRowTitle, BorderLayout.WEST);

        JButton removeRowButton = new JButton();
        removeRowButton.setText("Close Row");
        removeRowButton.addActionListener(e ->closeRow());
        inputRowHead.add(removeRowButton, BorderLayout.EAST);

        inputRowInner.add(inputRowHead);
        inputRowInner.add(inputRowBody);

        this.add(inputRowInner, BorderLayout.CENTER);
        this.setVisible(true);
    }
    /**
     * Closes the input row, deletes it from rowArray and updates the GUI. Called by the close button.
     */
    private void closeRow(){
        this.setVisible(false);
        bodyContainer.remove(this);
        repaint();
        rowArray[row] = null;
    }

    /**
     * Itterates over the strings in attributeInput adding input JLabels and JTextFields to inputRowBodycol1
     * @param attributeInput is an array of input field names.
     */
    public void buildInputs(String[] attributeInput){
        for(int i=0;i<attributeInput.length;i++){
            attributeLabel[i] = new JLabel();
            attributeLabel[i].setBounds(10,(i*20)+10,130,20);
            attributeLabel[i].setText(attributeInput[i]);

            this.attributeInput[i] = new JTextField();
            this.attributeInput[i].setBounds(150,(i*20)+10,200,20);

            inputRowBodycol1.add(attributeLabel[i]);
            inputRowBodycol1.add(this.attributeInput[i]);
        }
    }

    /**
     * If the data is valid a preview of the referene is added to referencePreviewLabel.
     */
    public void updatePreview() { if(validateData()) referencePreviewLabel.setText("<html>"+generateReferences()+"</html>"); }

    /**
     * Used to get the row ID.
     * @return row ID set when creating the row.
     */
    public int getRowID(){ return row; }

    /**
     * Stores attribute string data in the attributeData array at an index of attributeID.
     * @param attributeContent Attribute text data.
     * @param attributeID Attribute ID used for identification.
     */
    public void setAttributeData(int attributeID, String attributeContent){ attributeData[attributeID] = attributeContent; }

    /**
     * Retrieves attribute string data from the attributeData array at an index of attributeID.
     * @param attributeID Attribute ID used for identification.
     * @return Attribute text data.
     */
    public String getAttributeData(int attributeID) {return attributeData[attributeID];}

    /**
     * Resets label text from red to black after validation has succeded and stores the data.
     * @param i the ID of the input label.
     */
    public void validationSuccess(int i){
        attributeLabel[i].setForeground(Color.BLACK);
        this.setAttributeData(i, attributeInput[i].getText());
    }

    /**
     * Sets label text to red after validation has failed.
     * @param i the ID of the input label.
     * @return always returns true.
     */
    public boolean validationFailure(int i){
        attributeLabel[i].setForeground(Color.RED);
        return true;
    }

    /**
     * If a field fails validation this returns the relevent error message.
     * @param attributeTitle Used to look up which error message is needed.
     * @return The error string.
     */
    public String validationGetErrorString(String attributeTitle){
         switch(attributeTitle){
             case "Title":              return "Title Error<br/>";
             case "Author":             return "Author Error<br/>";
             case "Publisher":          return "Publisher Error<br/>";
             case "City":               return "City Error<br/>";
             case "Pages":              return "Pages Error<br/>";
             case "Journal Name":       return "Journal Error<br/>";
             case "Periodical Title":   return "Periodical Title Error<br/>";
             case "Web Page":           return "Web Page Error<br/>";
             case "Web Site":           return "Web Site Error<br/>";
             case "URL":                return "URL Error<br/>";
             case "Conference Name":    return "Conference Name Error<br/>";
             case "Director":           return "Director Error<br/>";
             case "Year":               return "Year Error<br/>";
             case "Month":              return "Month Error<br/>";
             case "Day":                return "Day Error<br/>";
             default:                   return "UNKNOWN ERROR<br/>";
             }
    }

    /**
     * Formats first and last names if both names are supplied, otherwise uses single name.
     * @param nameIn Name data from the input field.
     * @return Formatted name string.
     */
    public String formatName(String nameIn){
        String[] splitNames = nameIn.split(" ");

        if(splitNames.length == 2){
            return splitNames[1]+", "+splitNames[0].toUpperCase().charAt(0);
        }
        else{
            return nameIn;
        }
    }

    /**
     * Checks if all data in the row is valid.
     * @return returns boolean for validation success or failure.
     */
    public abstract boolean validateData();

    /**
     * Extracts attribute data and formats it into a reference.
     * @return The final formatted reference.
     */
    public abstract String generateReferences();

}