package com.company;

/**
 * Extends inputRow. Manually adds input lables and fields. Builds reference. Validates Data.
 *
 * @author Peter Bedford
 * @version 1.0.0
 */
public class inputRowConference  extends inputRow {
    String[] attributesNames = {"Title","Author","Publisher","City","Pages","Conference Name","Year"};
    public inputRowConference(int rowCount) {
        super(rowCount);
        buildInputs(attributesNames);
    }

    @Override
    public String generateReferences() {
        return formatName(this.getAttributeData(1))+". ("+this.getAttributeData(6)+"). "+this.getAttributeData(0)+". <i>"+this.getAttributeData(5)+"</i> (pp. "+this.getAttributeData(4)+"). "+this.getAttributeData(3)+": "+this.getAttributeData(2)+".";
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

        if(attributeInput[5].getText().length() > 3){
            validationSuccess(5);
        }else{
            validationFailure = validationFailure(5);
            validationErrorMessage += validationGetErrorString(attributesNames[5]);
        }

        if(attributeInput[6].getText().length() > 3){
            validationSuccess(6);
        }else{
            validationFailure = validationFailure(6);
            validationErrorMessage += validationGetErrorString(attributesNames[6]);
        }

        if(validationFailure) {
            referencePreviewLabel.setText("<html>"+validationErrorMessage+"</html>");
            return false;
        }else{
            return true;
        }

    }
}
