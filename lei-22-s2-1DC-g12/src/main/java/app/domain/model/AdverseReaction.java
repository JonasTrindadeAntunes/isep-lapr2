package app.domain.model;

import app.domain.shared.MessageBundle;

import java.io.Serializable;

/**
 * The Adverse reaction.
 */
public class AdverseReaction implements Serializable {

    /**
     * The designation.
     */
    private String designation;

    /**
     * The vaccine administration.
     */
    private VaccineAdministration vaccineAdministration;

    /**
     * Instantiates a new Adverse reaction.
     *
     * @param description           the description
     * @param vaccineAdministration the vaccine administration
     */
    public AdverseReaction(String description, VaccineAdministration vaccineAdministration) {
        if( description.isEmpty() || description == null)
            throw new IllegalArgumentException(MessageBundle.getString("None of the arguments can be null"));
        else if(description.length() > 300)
            throw new IllegalArgumentException(MessageBundle.getString("Description can't have more than 300 characters"));

        this.designation = description;
        this.vaccineAdministration = vaccineAdministration;
    }

    /**
     * Gets designation.
     *
     * @return the designation
     */
    public String getDesignation() {
        return designation;
    }



    public String toString(){
        return "Adverse reaction on :" + vaccineAdministration.getDate() + " to: " + vaccineAdministration.getVaccine().getId() + "\nDescription:" + getDesignation();
    }
}
