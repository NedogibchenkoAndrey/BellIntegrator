package ru.bellintegrator.task.service;

import ru.bellintegrator.task.view.organization.*;

import java.util.List;

public interface OrganizationService {
    List<OrganizationToListView> findAll(OrganizationFilterView filterView);
    OrganizationToByIdView findById(Integer id);
    void save(OrganizationToSaveView orgToSave);
    void update(OrganizationToUpdateView org);
}
