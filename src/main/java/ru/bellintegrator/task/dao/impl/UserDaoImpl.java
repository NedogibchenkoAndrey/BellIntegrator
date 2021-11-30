package ru.bellintegrator.task.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.bellintegrator.task.dao.OfficeDao;
import ru.bellintegrator.task.dao.UserDao;
import ru.bellintegrator.task.model.User;
import ru.bellintegrator.task.view.user.UserFilterView;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    private final EntityManager em;
    private final OfficeDao officeDao;


    @Autowired
    public UserDaoImpl(EntityManager em, OfficeDao officeDao) {
        this.em = em;
        this.officeDao = officeDao;
    }

    @Override
    public List<User> findAll(UserFilterView userFilterView) {
        officeDao.findById(userFilterView.getOfficeId());

        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
        Root<User> userRoot = criteriaQuery.from(User.class);

        Predicate filter = criteriaBuilder.equal(userRoot.get("office").get("id"), userFilterView.getOfficeId());

        if (userFilterView.getFirstName() != null) {
            filter = criteriaBuilder.and(filter, criteriaBuilder.equal(userRoot.get("firstName"), userFilterView.getFirstName()));
        }

        if (userFilterView.getMiddleName() != null) {
            filter = criteriaBuilder.and(filter, criteriaBuilder.equal(userRoot.get("middleName"), userFilterView.getMiddleName()));
        }

        if (userFilterView.getPosition() != null) {
            filter = criteriaBuilder.and(filter, criteriaBuilder.equal(userRoot.get("position"), userFilterView.getPosition()));
        }

        if (userFilterView.getDocCode() != null) {
            filter = criteriaBuilder.and(filter, criteriaBuilder.equal(userRoot.get("doc").get("docType").get("code"), userFilterView.getDocCode()));
        }

        if (userFilterView.getCitizenshipCode() != null) {
            filter = criteriaBuilder.and(filter, criteriaBuilder.equal(userRoot.get("country").get("code"), userFilterView.getCitizenshipCode()));
        }

        criteriaQuery.select(userRoot).where(filter);
        TypedQuery<User> query = em.createQuery(criteriaQuery);
        return query.getResultList();
    }

    @Override
    public User findById(Integer id) {
        return em.find(User.class, id);
    }

    @Override
    public void update(User user) {
        em.merge(user);
    }

    @Override
    public void save(User user) {
        em.persist(user);
    }
}
