package com.company;

/**
 * Extends inputRow. Manually adds input lables and fields. Builds reference. Validates Data.
 *
 * @author Peter Bedford
 * @version 1.0.0
 */
public class inputRowFilm  extends inputRow {
    String[] attributesNames = {"Title","Director","Year"};
    public inputRowFilm(int rowCount) {
        super(rowCount);
        buildInputs(attributesNames);
    }

    @Override
    public String generateReferences() {
        return this.getAttributeData(1)+". (Director). ("+"<html>"+this.getAttributeData(2)+"). <i>"+"<html>"+this.getAttributeData(0)+"</i> [Motion Picture].";
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

        if(validationFailure) {
            referencePreviewLabel.setText("<html>"+validationErrorMessage+"</html>");
            return false;
        }else{
            return true;
        }

    }
}
