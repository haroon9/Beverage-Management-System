package de.uniba.dsg.dsam.persistence;

import java.util.List;

import de.uniba.dsg.dsam.model.IncentiveDTO;

public interface IncentiveManagement {
	
	void create(String type, String name) throws de.uniba.dsg.dsam.persistence.exceptions.PersistenceExceptions;

	List<IncentiveDTO> getAllIncentives();

	IncentiveDTO getIncentive(int id);

	void updateIncentive(int id, String type, String name);

	void deleteIncentive(int id) throws de.uniba.dsg.dsam.persistence.exceptions.PersistenceExceptions;
}
