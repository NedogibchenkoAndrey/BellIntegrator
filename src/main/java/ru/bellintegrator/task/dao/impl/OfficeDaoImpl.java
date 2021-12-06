package ru.bellintegrator.task.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.bellintegrator.task.dao.OfficeDao;
import ru.bellintegrator.task.model.Office;
import ru.bellintegrator.task.view.office.OfficeFilterView;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class OfficeDaoImpl implements OfficeDao {

    private final EntityManager em;

    @Autowired
    public OfficeDaoImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public List<Office> findAll(OfficeFilterView filterView) {
        String name = filterView.getName();
        String phone = filterView.getPhone();
        Boolean isActive = filterView.getIsActive();

        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Office> criteriaQuery = criteriaBuilder.createQuery(Office.class);
        Root<Office> root = criteriaQuery.from(Office.class);
        Predicate filter = criteriaBuilder.equal(root.get("organization").get("id"), filterView.getOrgId());
        if (name != null) {
            filter = criteriaBuilder.and(filter, criteriaBuilder.equal(root.get("name"), name));
        }

        if (phone != null) {
            filter = criteriaBuilder.and(filter, criteriaBuilder.equal(root.get("phone"), phone));
        }

        if (isActive != null) {
            filter = criteriaBuilder.and(filter, criteriaBuilder.equal(root.get("isActive"), isActive));
        }
        criteriaQuery.select(root).where(filter);

        TypedQuery<Office> query = em.createQuery(criteriaQuery);
        return query.getResultList();

    }

    @Override
    public Office findById(Integer id) {
        Query query = em.createNativeQuery("SELECT o.* FROM Office o WHERE o.id =:id", Office.class)
                .setParameter("id", id);
        return (Office) query.getSingleResult();
    }

    @Override
    public void save(Office office) {
        em.persist(office);
    }

    @Override
    public void update(Office office) {
        em.merge(office);
    }
}
