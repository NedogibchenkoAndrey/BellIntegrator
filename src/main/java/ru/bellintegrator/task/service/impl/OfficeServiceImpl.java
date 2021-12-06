package ru.bellintegrator.task.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bellintegrator.task.dao.OfficeDao;
import ru.bellintegrator.task.dao.OrganizationDao;
import ru.bellintegrator.task.mapper.MapperFacade;
import ru.bellintegrator.task.model.Office;
import ru.bellintegrator.task.model.Organization;
import ru.bellintegrator.task.response.exception.DataNotFoundException;
import ru.bellintegrator.task.service.OfficeService;
import ru.bellintegrator.task.view.office.OfficeFilterView;
import ru.bellintegrator.task.view.office.OfficeToListView;
import ru.bellintegrator.task.view.office.OfficeToSaveView;
import ru.bellintegrator.task.view.office.OfficeToUpdateView;

import javax.persistence.NoResultException;
import java.util.List;

@Service
public class OfficeServiceImpl implements OfficeService {

    private final OfficeDao officeDao;
    private final OrganizationDao organizationDao;
    private final MapperFacade mapperFacade;


    @Autowired
    public OfficeServiceImpl(@Lazy OfficeDao officeDao, OrganizationDao organizationDao, MapperFacade mapperFacade) {
        this.officeDao = officeDao;
        this.organizationDao = organizationDao;
        this.mapperFacade = mapperFacade;
    }

    @Override
    public List<OfficeToListView> findAll(OfficeFilterView officeFilterView) {
        List<Office> list = officeDao.findAll(officeFilterView);
        return mapperFacade.mapAsList(list, OfficeToListView.class);
    }

    @Override
    public OfficeToUpdateView findById(Integer id) {
        return mapperFacade.map(officeDao.findById(id), OfficeToUpdateView.class);
    }

    @Override
    @Transactional
    public void save(OfficeToSaveView officeToSaveView){
        Office saveOffice = mapperFacade.map(officeToSaveView, Office.class);
        Organization organization = organizationDao.findById(officeToSaveView.getOrgId());
        if (organization != null) {
            saveOffice.setOrganization(organization);
            officeDao.save(saveOffice);
        } else {
            throw new DataNotFoundException("Organization with this id not found");
        }
    }

    @Override
    @Transactional
    public void update(OfficeToUpdateView officeToUpdateView) {
        try {
            Office officeUpdate = officeDao.findById(officeToUpdateView.getId());
            mapperFacade.map(officeToUpdateView, officeUpdate);
        } catch (NoResultException e) {
            throw new DataNotFoundException("Office with this id not found", e);
        }
    }
}
