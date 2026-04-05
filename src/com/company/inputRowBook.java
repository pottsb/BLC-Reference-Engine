package com.company;

import javax.swing.*;

import java.awt.*;

/**
 * Extends inputRow. Manually adds input lables and fields. Builds reference. Validates Data.
 *
 * @author Peter Bedford
 * @version 1.0.0
 */
public class inputRowBook extends inputRow{


    private JTextField yearInput;
    private JTextField titleInput;
    private JTextField authorInput;
    private JTextField publisherInput;
    private JTextField cityInput;

    private JLabel titleInputLabel;
    private JLabel yearInputLabel;
    private JLabel authorInputLabel;
    private JLabel publisherInputLabel;
    private JLabel cityInputLabel;

    inputRowBook(int rowCount) {
        super(rowCount);

        yearInput = new JTextField();
        yearInput.setBounds(50,180,40,20);

        yearInputLabel = new JLabel();
        yearInputLabel.setBounds(10,180,40,20);
        yearInputLabel.setText("Year");

        titleInput = new JTextField();
        titleInput.setBounds(100,10,200,20);

        titleInputLabel = new JLabel();
        titleInputLabel.setBounds(10,10,80,20);
        titleInputLabel.setText("Title");

        authorInput = new JTextField();
        authorInput.setBounds(100,40,200,20);

        authorInputLabel = new JLabel();
        authorInputLabel.setBounds(10,40,80,20);
        authorInputLabel.setText("Author");

        publisherInput = new JTextField();
        publisherInput.setBounds(100,70,200,20);

        publisherInputLabel = new JLabel();
        publisherInputLabel.setBounds(10,70,80,20);
        publisherInputLabel.setText("Publisher");

        cityInput = new JTextField();
        cityInput.setBounds(100,100,200,20);

        cityInputLabel = new JLabel();
        cityInputLabel.setBounds(10,100,80,20);
        cityInputLabel.setText("City");

        inputRowBodycol1.add(titleInputLabel);
        inputRowBodycol1.add(titleInput);
        inputRowBodycol1.add(authorInputLabel);
        inputRowBodycol1.add(authorInput);
        inputRowBodycol1.add(publisherInputLabel);
        inputRowBodycol1.add(publisherInput);
        inputRowBodycol1.add(cityInputLabel);
        inputRowBodycol1.add(cityInput);
        inputRowBodycol1.add(yearInputLabel);
        inputRowBodycol1.add(yearInput);
    }

    @Override
    public String generateReferences() {
            return generateSplitReferences()[0] + generateSplitReferences()[1];
    }

    /**
     * Generates the reference in two parts so inputRowBookSection can use this information to insert page count and generate a reference.
     * @return A string array containing the two parts of the reference.
     */
    public String[] generateSplitReferences(){

        String[] str = new String[2];

        str[0] = formatName(this.getAttributeData(1))+". ("+this.getAttributeData(4)+"). <i>"+this.getAttributeData(0)+"</i>";
        str[1] = ". "+this.getAttributeData(3)+":"+this.getAttributeData(2)+".";
        return str;
    }


    @Override
    public boolean validateData() {

        String validationErrorMessage = "";

         boolean validationFailure = false;

        if(titleInput.getText().length() > 2){
            titleInputLabel.setForeground(Color.BLACK);
            this.setAttributeData(0, titleInput.getText());
        }else{
            titleInputLabel.setForeground(Color.RED);
            validationFailure = true;
            validationErrorMessage += "Title Error <br/>";
        }

        if(authorInput.getText().length() > 2){
            authorInputLabel.setForeground(Color.BLACK);
            this.setAttributeData(1, authorInput.getText());

        }else{
            authorInputLabel.setForeground(Color.RED);
            validationFailure = true;
            validationErrorMessage += "Author Error <br/>";
        }

        if(publisherInput.getText().length() > 2){
            publisherInputLabel.setForeground(Color.BLACK);
            this.setAttributeData(2, publisherInput.getText());
        }else{
            publisherInputLabel.setForeground(Color.RED);
            validationFailure = true;
            validationErrorMessage += "Publisher Error <br/>";
        }

        if(cityInput.getText().length() > 2){
            cityInputLabel.setForeground(Color.BLACK);
            this.setAttributeData(3, cityInput.getText());
        }else{
            cityInputLabel.setForeground(Color.RED);
            validationFailure = true;
            validationErrorMessage += "City Error <br/>";
        }

        if(yearInput.getText().length() > 3){
            yearInputLabel.setForeground(Color.BLACK);
            this.setAttributeData(4, yearInput.getText());
        }else{
            yearInputLabel.setForeground(Color.RED);
            validationFailure = true;
            validationErrorMessage += "Year Error <br/>";
        }

        if(validationFailure) {
            referencePreviewLabel.setText("<html>"+validationErrorMessage+"</html>");
            return false;
        }else{
            return true;
        }

    }
}
