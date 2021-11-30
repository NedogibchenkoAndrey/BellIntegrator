package ru.bellintegrator.task.service;

import ru.bellintegrator.task.view.office.OfficeFilterView;
import ru.bellintegrator.task.view.office.OfficeToListView;
import ru.bellintegrator.task.view.office.OfficeToSaveView;
import ru.bellintegrator.task.view.office.OfficeToUpdateView;

import java.util.List;

public interface OfficeService {
    List<OfficeToListView> findAll(OfficeFilterView officeFilterView);
    OfficeToUpdateView findById(Integer officeId);
    void save(OfficeToSaveView officeToSaveView);
    void update(OfficeToUpdateView officeToUpdateView);


}
