package ru.bellintegrator.task.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.bellintegrator.task.dao.OrganizationDao;
import ru.bellintegrator.task.model.Organization;
import ru.bellintegrator.task.view.organization.OrganizationFilterView;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class OrganizationDaoImpl implements OrganizationDao {

    private final EntityManager em;

    @Autowired
    public OrganizationDaoImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public List<Organization> findAll(OrganizationFilterView filterView) {
        String name = filterView.getName();
        String inn = filterView.getInn();
        Boolean active = filterView.getIsActive();

        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Organization> criteriaQuery = builder.createQuery(Organization.class);
        Root<Organization> root = criteriaQuery.from(Organization.class);

        Predicate filter = builder.equal(root.get("name"), name);
        if (inn != null) {
            filter = builder.and(filter, builder.equal(root.get("inn"), inn));
        }

        if (active != null) {
            filter = builder.and(filter, builder.equal(root.get("isActive"), active));
        }

        criteriaQuery.select(root).where(filter);

        TypedQuery<Organization> query = em.createQuery(criteriaQuery);
        return query.getResultList();
    }

    @Override
    public Organization findById(Integer id) {
//        TypedQuery<Organization> query = em.createQuery("SELECT o FROM Organization o WHERE o.id =:id", Organization.class);
        //        query.setParameter("id", id);
//        return query.getSingleResult();
       Organization organization = em.getReference(Organization.class, id);
       organization.getId();
       return organization;
    }

    @Override
    public void save(Organization organization) {
        em.persist(organization);
    }

    @Override
    public void update(Organization orgUpdate) {
        em.persist(orgUpdate);
    }

}
