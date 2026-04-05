package com.company;

import java.awt.*;

/**
 * Extends inputRow. Manually adds input lables and fields. Builds reference. Validates Data.
 *
 * @author Peter Bedford
 * @version 1.0.0
 */
public class inputRowJournal extends inputRow{

    String[] attributesNames = {"Title","Author","Pages","Journal Name","Year"};

    public inputRowJournal(int rowCount) {
        super(rowCount);
        buildInputs(attributesNames);

    }

    @Override
    public String generateReferences() {
        return formatName(this.getAttributeData(1))+". ("+this.getAttributeData(4)+"). "+this.getAttributeData(0)+". <i>"+this.getAttributeData(3)+"</i>, "+this.getAttributeData(2)+".";
    }

    @Override
    public boolean validateData() {

        String validationErrorMessage = "";

        boolean validationFailure = false;

        if(attributeInput[0].getText().length() > 2){
            validationSuccess(0);
        }else{
            validationFailure = validationFailure(0);
            validationErrorMessage += validationGetErrorString(attributesNames[0]);
        }

        if(attributeInput[1].getText().length() > 2){
            validationSuccess(1);
            String[] splitNames = attributeInput[1].getText().split(" ");
            this.setAttributeData(21, splitNames[0]);
            this.setAttributeData(22, splitNames[1]);
        }else{
            validationFailure = validationFailure(1);
            validationErrorMessage += validationGetErrorString(attributesNames[1]);
        }

        if(attributeInput[2].getText().length() > 2){
            validationSuccess(2);
        }else{
            validationFailure = validationFailure(2);
            validationErrorMessage += validationGetErrorString(attributesNames[2]);
        }

        if(attributeInput[3].getText().length() > 2){
            validationSuccess(3);
        }else{
            validationFailure = validationFailure(3);
            validationErrorMessage += validationGetErrorString(attributesNames[3]);
        }

        if(attributeInput[4].getText().length() > 3){
            validationSuccess(4);
        }else{
            validationFailure = validationFailure(4);
            validationErrorMessage += validationGetErrorString(attributesNames[4]);
        }

        if(validationFailure) {
            referencePreviewLabel.setText("<html>"+validationErrorMessage+"</html>");
            return false;
        }else{
            return true;
        }
    }
}
