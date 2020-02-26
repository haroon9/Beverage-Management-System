package de.uniba.dsg.dsam.backend.entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Version;

/**
 * @author Haroon
 * @Email haroon.gul@stud.uni-bamberg.de
 */

@Entity
@DiscriminatorValue(value = "Trial Package")
public class TrialPackageEntity extends IncentiveEntity {
	
	private static final long serialVersionUID = 1L;
	
	
	@Version
	private int version;
	
	public TrialPackageEntity() {
		// TODO Auto-generated constructor stub
	}

	
}
