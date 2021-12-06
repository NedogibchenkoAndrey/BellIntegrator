package ru.bellintegrator.task.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bellintegrator.task.dao.OrganizationDao;
import ru.bellintegrator.task.mapper.MapperFacade;
import ru.bellintegrator.task.model.Organization;
import ru.bellintegrator.task.response.exception.DataNotFoundException;
import ru.bellintegrator.task.service.OrganizationService;
import ru.bellintegrator.task.view.organization.*;

import javax.persistence.NoResultException;
import java.util.List;

@Service
public class OrganizationServiceImpl implements OrganizationService {

    private final OrganizationDao organizationDao;
    private final MapperFacade mapperFacade;

    @Autowired
    public OrganizationServiceImpl(OrganizationDao organizationDao, MapperFacade mapperFacade) {
        this.organizationDao = organizationDao;
        this.mapperFacade = mapperFacade;
    }

    @Override
    public List<OrganizationToListView> findAll(OrganizationFilterView filterView) {
        List<Organization> organizationList = organizationDao.findAll(filterView);
        return mapperFacade.mapAsList(organizationList, OrganizationToListView.class);
    }

    @Override
    public OrganizationToByIdView findById(Integer id) {
        return mapperFacade.map(organizationDao.findById(id), OrganizationToByIdView.class);
    }

    @Override
    @Transactional
    public void save(OrganizationToSaveView orgToSave) {
        organizationDao.save(mapperFacade.map(orgToSave, Organization.class));
    }

    @Override
    @Transactional
    public void update(OrganizationToUpdateView orgToUpdateView) {
        try {
        Organization updateOrg = organizationDao.findById(orgToUpdateView.getId());
        mapperFacade.map(orgToUpdateView, updateOrg);

        } catch (NoResultException e){
            throw new DataNotFoundException("Organization with this id" + orgToUpdateView.getId() + " not found", e);
        }
    }
}
