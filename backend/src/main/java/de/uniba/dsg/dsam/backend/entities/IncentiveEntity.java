package de.uniba.dsg.dsam.backend.entities;

import java.io.Serializable;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.Version;

import de.uniba.dsg.dsam.model.ValidationResult;
import de.uniba.dsg.dsam.model.ValidationResult.ResultType;

/**
 * @author Haroon
 * @Email haroon.gul@stud.uni-bamberg.de
 */

@Entity
@Inheritance
@DiscriminatorColumn(name = "incentiveType")
public abstract class IncentiveEntity implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;

    @Version
    private int version;

    private String incentiveType;
    private String name;

    public IncentiveEntity() {
        // TODO Auto-generated constructor stub
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public IncentiveEntity(String type, String name) {
        this.incentiveType = type;
    }

    public String getIncentiveType() {
        return incentiveType;
    }

    public void setIncentiveType(String incentiveType) {
        this.incentiveType = incentiveType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + id;
        return result;
    }

    /**
     * Checks validity of the given String (name or incentiveType)
     *
     * @param name name
     * @return validation result
     */
    public static ValidationResult validateString(String name) {
        ValidationResult vr = new ValidationResult();
        if (name == null || name.length() == 0) {
            vr.setType(ResultType.NOTOK);
            vr.setErrMsg("No null or empty strings allowed for incentive names");
        } else {
            vr.setType(ResultType.OK);
        }
        return vr;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!(obj instanceof IncentiveEntity))
            return false;
        IncentiveEntity other = (IncentiveEntity) obj;
        return id == other.id;
    }
}
