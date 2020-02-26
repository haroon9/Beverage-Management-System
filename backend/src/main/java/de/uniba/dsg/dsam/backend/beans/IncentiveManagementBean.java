package de.uniba.dsg.dsam.backend.beans;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

import de.uniba.dsg.dsam.backend.entities.BeverageEntity;
import de.uniba.dsg.dsam.backend.entities.CustomerOrderEntity;
import de.uniba.dsg.dsam.backend.entities.IncentiveEntity;
import de.uniba.dsg.dsam.backend.entities.PromotionalGiftEntity;
import de.uniba.dsg.dsam.backend.entities.TrialPackageEntity;
import de.uniba.dsg.dsam.model.IncentiveDTO;
import de.uniba.dsg.dsam.model.ValidationResult;
import de.uniba.dsg.dsam.model.ValidationResult.ResultType;
import de.uniba.dsg.dsam.persistence.IncentiveManagement;
import de.uniba.dsg.dsam.persistence.SalesManagement;
import de.uniba.dsg.dsam.persistence.exceptions.PersistenceExceptions;

/**
 * @author Haroon
 * @Email haroon.gul@stud.uni-bamberg.de
 */

@Stateless
@Remote(IncentiveManagement.class)
public class IncentiveManagementBean implements IncentiveManagement {

    @PersistenceContext(type = PersistenceContextType.TRANSACTION)
    EntityManager em;

    @EJB
    SalesManagement sMng;

    /**
     * This method creates an incentive entity of either Promotional Gift or Trial Package and persist it in the database.
     *
     * @param type Incentivetype
     * @param name Incentivename
     */
    @Override
    public void create(String type, String name) throws de.uniba.dsg.dsam.persistence.exceptions.PersistenceExceptions {
        IncentiveEntity incentiveEntity = null;
        ValidationResult vr = IncentiveEntity.validateString(name);
        if (vr.getType() == ResultType.NOTOK) {
            throw new de.uniba.dsg.dsam.persistence.exceptions.PersistenceExceptions(vr.getErrMsg());
        } else if (IncentiveEntity.validateString(type).getType() == ResultType.NOTOK) {
            throw new de.uniba.dsg.dsam.persistence.exceptions.PersistenceExceptions(vr.getErrMsg());
        } else {
            if (type.equals("Promotional Gift")) {
                incentiveEntity = new PromotionalGiftEntity();
                incentiveEntity.setIncentiveType(type);
                incentiveEntity.setName(name);
            } else if (type.equals("Trial Package")) {
                incentiveEntity = new TrialPackageEntity();
                incentiveEntity.setIncentiveType(type);
                incentiveEntity.setName(name);
            } else {
                // something went wrong: throw error
                throw new PersistenceExceptions("other Error");
            }
        }
        em.persist(incentiveEntity);
    }

    /**
     * This method returns a list of incentive data transfer object which is needed in the incentives.jsp
     * Because the all the available incentives will be shown on the frontend
     *
     * @return List of all IncentiveDTOs
     */
    @Override
    public List<IncentiveDTO> getAllIncentives() {
        @SuppressWarnings("unchecked")
        List<IncentiveEntity> incentiveEntities = em.createNamedQuery("queryAllIncentives").getResultList();

        List<IncentiveDTO> incentivesList = new ArrayList<>();

        incentiveEntities.forEach(incentive -> {
            IncentiveDTO incDTO = new IncentiveDTO(incentive.getId(), incentive.getIncentiveType(), incentive.getName());
            List<BeverageEntity> allBeverageEntities = getAttachedBeverages(incentive.getId());
            incDTO.setAttached(allBeverageEntities.size() > 0 ? true : false);
            incentivesList.add(incDTO);
        });

        if (incentiveEntities == null || incentivesList == null) {
            return new ArrayList<IncentiveDTO>();
        }
        return incentivesList;
    }


    /**
     * This method returns an incentive data transfer object which is needed in the editincentive.jsp for updated a specific incentive
     *
     * @param id the incentive ID
     * @return IncentiveDTO
     */
    @Override
    public IncentiveDTO getIncentive(int id) {
        IncentiveEntity incentiveEntity = em.find(IncentiveEntity.class, id);

        if (incentiveEntity == null) {
            return null;
        } else {
            return new IncentiveDTO(incentiveEntity.getId(), incentiveEntity.getIncentiveType(), incentiveEntity.getName());
        }
    }

    /**
     * This method updates an incentive entity but lock the entity to check if the version
     * it obtained previously is the same as now otherwise an exception is thrown
     *
     * @param id   the incentive ID
     * @param type the incentive type
     * @param name the incentive name
     */
    @Override
    public void updateIncentive(int id, String type, String name) {
        IncentiveEntity incentiveEntity = selectIncentive(id);

        if (incentiveEntity != null) {
            em.lock(incentiveEntity, LockModeType.OPTIMISTIC); // JPA use this by default with @version but I am explicitly using it here
            incentiveEntity.setId(id);
            incentiveEntity.setIncentiveType(type);
            incentiveEntity.setName(name);
        }
    }

    /**
     * This method will delete the incentive if it is attached to a beverage or if it it is not.
     * The method sets the incentive entity to null for each beverage entity if the method getAttchedBeverages returns beverages.
     * If some incentive is not attached to any beverage it will be removed in the else statement.
     *
     * @param id the id of the incentive
     */
    @Override
    public void deleteIncentive(int id) throws PersistenceExceptions {
        IncentiveEntity incentiveEntity = selectIncentive(id);
        List<BeverageEntity> allBeverageEntities = getAttachedBeverages(id);
        @SuppressWarnings("unchecked")
        List<CustomerOrderEntity> customerOrderEntities = em.createNamedQuery("getOrderWithIncentive").setParameter("inc_id", id).getResultList();
        if (customerOrderEntities != null && customerOrderEntities.size() > 0) {
            for (CustomerOrderEntity customerOrderEntity : customerOrderEntities) {
                if (customerOrderEntity.isUsedIncentive()) {
                    throw new PersistenceExceptions("Can not be deleted, because it is attached to at least one order");
                }
            }
        } else {
            if (allBeverageEntities != null && allBeverageEntities.size() > 0) {
                allBeverageEntities.forEach(bEntity -> {
                    bEntity.setIncentiveEntity(null);
                    em.remove(incentiveEntity);
                });
            } else {
                em.remove(incentiveEntity);
            }
        }
    }

    /**
     * returns a specific incentive entity based on id
     *
     * @param id the ID
     * @return the IncentiveEntity
     */
    private IncentiveEntity selectIncentive(int id) {

        return em.find(IncentiveEntity.class, id);
    }

    /**
     * This method returns the list of beverage entities which has an incentive attached.
     *
     * @param inc_id the incentive ID
     * @return A list of all attached BeverageEntities
     */
    private List<BeverageEntity> getAttachedBeverages(int inc_id) {
        @SuppressWarnings("unchecked")
        List<BeverageEntity> beverageEntities = em.createNamedQuery("queryGetBeveragesWithIncentive").setParameter("inc_id", inc_id).getResultList();
        return beverageEntities;
    }
}
