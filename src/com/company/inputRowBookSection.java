package com.company;

import javax.swing.*;
import java.awt.*;

/**
 * Extends inputRowBook. Manually adds pages input lables and fields. Builds reference. Validates Data.
 *
 * @author Peter Bedford
 * @version 1.0.0
 */
public class inputRowBookSection extends inputRowBook{

    JTextField pagesInput;
    JLabel pagesInputLabel;

    inputRowBookSection(int rowCount) {
        super(rowCount);

        pagesInput = new JTextField();
        pagesInput.setBounds(100,130,200,20);

        pagesInputLabel = new JLabel();
        pagesInputLabel.setBounds(10,130,80,20);
        pagesInputLabel.setText("Pages");

        inputRowBodycol1.add(pagesInputLabel);
        inputRowBodycol1.add(pagesInput);
    }


    @Override
    public String generateReferences() {

        return super.generateSplitReferences()[0]+" (pp."+this.getAttributeData(5)+ ")"+super.generateSplitReferences()[1];
    }

    public boolean validateData(){
        boolean validationFailure = !super.validateData();

        String pageError = "";

        if(pagesInput.getText().length() > 3){
            pagesInputLabel.setForeground(Color.BLACK);
            this.setAttributeData(5, pagesInput.getText());
        }else{
            pagesInputLabel.setForeground(Color.RED);
            validationFailure = true;
            pageError = "Page Error";
        }

        if(validationFailure) {
            String str = new StringBuilder(referencePreviewLabel.getText()).insert(referencePreviewLabel.getText().length()-7, pageError).toString();
            referencePreviewLabel.setText(str);
            return false;
        }else{
            return true;
        }
    }
}
