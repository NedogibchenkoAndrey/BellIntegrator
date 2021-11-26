package ru.bellintegrator.task.dao;

import ru.bellintegrator.task.model.Organization;
import ru.bellintegrator.task.view.organization.OrganizationFilterView;

import java.util.List;

public interface OrganizationDao {
    List<Organization> findAll(OrganizationFilterView filterView);
    Organization findById(Integer id);
    void save(Organization organization);
    void update(Organization updateOrg);
}
