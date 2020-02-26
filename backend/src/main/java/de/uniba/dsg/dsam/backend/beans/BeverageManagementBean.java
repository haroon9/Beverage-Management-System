package de.uniba.dsg.dsam.backend.beans;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

import de.uniba.dsg.dsam.backend.entities.BeverageEntity;
import de.uniba.dsg.dsam.backend.entities.IncentiveEntity;
import de.uniba.dsg.dsam.model.Beverage;
import de.uniba.dsg.dsam.model.IncentiveDTO;
import de.uniba.dsg.dsam.persistence.BeverageManagement;

/**
 * @author Haroon
 * @Email haroon.gul@stud.uni-bamberg.de
 */

@Stateless
@Remote(BeverageManagement.class)
public class BeverageManagementBean implements BeverageManagement {

    @PersistenceContext(type = PersistenceContextType.TRANSACTION)
    EntityManager em;

    /*
     * The method creates a new beverage with or without an incentive and then persist it in the Beverage Entity
     */
    @Override
    public void create(String name, String manufacturer, int quantity, double price, String inc_id) throws de.uniba.dsg.dsam.persistence.exceptions.PersistenceExceptions {
        if (!name.isEmpty() && !manufacturer.isEmpty() && quantity > 0 && price > 0) {
            if (inc_id == null || inc_id.length() == 0) {
                BeverageEntity beverageEntity = new BeverageEntity(
                        name, manufacturer, quantity, price, null);
                em.persist(beverageEntity);
            } else if (inc_id != null) {
                try {
                    IncentiveEntity entity = em.find(IncentiveEntity.class, Integer.parseInt(inc_id));
                    BeverageEntity beverageEntity = new BeverageEntity(name, manufacturer, quantity, price, entity);
                    em.persist(beverageEntity);
                } catch (NumberFormatException ex) {
                    ex.printStackTrace();
                }
            }
        } else {
            throw new de.uniba.dsg.dsam.persistence.exceptions.PersistenceExceptions("new Beverage could not be created");
        }
    }

    @Override
    public void assignIncentive(String b_id, String inc_id) throws de.uniba.dsg.dsam.persistence.exceptions.PersistenceExceptions {
        BeverageEntity beverageEntity = getBeverageEntityById(Integer.parseInt(b_id));
        if (inc_id != null) {
            IncentiveEntity incentiveEntity = em.find(IncentiveEntity.class, Integer.parseInt(inc_id));
            if (beverageEntity != null && incentiveEntity != null) {
                em.lock(beverageEntity, LockModeType.OPTIMISTIC); // JPA use this by default with @version but it's explicitly used here
                beverageEntity.setIncentiveEntity(incentiveEntity);
            } else {
                throw new de.uniba.dsg.dsam.persistence.exceptions.PersistenceExceptions("Could not assign Incentive to Beverage");
            }
        }
    }

    @Override
    public void updateBeverage(int b_id, String name, String manufacturer, int quantity, Double price, String inc_id) throws de.uniba.dsg.dsam.persistence.exceptions.PersistenceExceptions {
        BeverageEntity beverageEntity = getBeverageEntityById(b_id);
        if (beverageEntity != null && !name.isEmpty() && !manufacturer.isEmpty() && quantity > 0 && price > 0) {
            em.lock(beverageEntity, LockModeType.OPTIMISTIC);
            beverageEntity.setName(name);
            beverageEntity.setManufacturer(manufacturer);
            beverageEntity.setQuantity(quantity);
            beverageEntity.setPrice(price);

            //Check if incentive is not empty or null to assign it
            if (inc_id != null && inc_id.length() > 0) {
                IncentiveEntity incentiveEntity = em.find(IncentiveEntity.class, Integer.parseInt(inc_id));
                if (incentiveEntity != null) {
                    beverageEntity.setIncentiveEntity(incentiveEntity);
                } else {
                    beverageEntity.setIncentiveEntity(null);
                }
            }
        } else {
            throw new de.uniba.dsg.dsam.persistence.exceptions.PersistenceExceptions("Beverage couldn't be updated");
        }
    }

    @Override
    public List<Beverage> getAllBeverages() {
        @SuppressWarnings({"unchecked"})
        List<BeverageEntity> beverages = em.createNamedQuery("queryAllBeverages").getResultList();

        List<Beverage> list = new ArrayList<Beverage>();

        if (beverages != null) {
            beverages.forEach(beverage -> {
                list.add(toBeverage(beverage));

            });
        }

        if (beverages == null || list == null) {
            return new ArrayList<Beverage>();
        } else {
            return list;
        }
    }

    public BeverageEntity getBeverageEntityById(int id) {
        return em.find(BeverageEntity.class, id);
    }

    public Beverage getBeverageById(int id) {
        BeverageEntity beverageEntity = em.find(BeverageEntity.class, id);
        if (beverageEntity == null) {
            return null;
        } else {
            return toBeverage(beverageEntity);
        }

    }

    // convert beverage entity to POJO
    public Beverage toBeverage(BeverageEntity entity) {

        if (entity == null) {
            return null;
        } else {
            return new Beverage(entity.getId(), entity.getName(), entity.getManufacturer(),
                    entity.getQuantity(), entity.getPrice(), toIncentiveDTO(entity.getIncentiveEntity()));
        }
    }

    // convert incentive entity to POJO
    public IncentiveDTO toIncentiveDTO(IncentiveEntity entity) {

        if (entity == null) {
            return null;
        }

        return new IncentiveDTO(entity.getId(), entity.getIncentiveType(), entity.getName());
    }
}
