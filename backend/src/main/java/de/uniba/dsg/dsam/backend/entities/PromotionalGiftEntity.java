package de.uniba.dsg.dsam.backend.entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Version;

/**
 * @author Haroon
 * @Email haroon.gul@stud.uni-bamberg.de
 */

@Entity
@DiscriminatorValue(value = "Promotional Gift")
public class PromotionalGiftEntity extends IncentiveEntity {
	private static final long serialVersionUID = 1L;
	@Version
	private int version;
	
	 public PromotionalGiftEntity() {
		// TODO Auto-generated constructor stub
	}

}
