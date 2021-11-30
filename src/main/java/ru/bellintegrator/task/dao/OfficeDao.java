package ru.bellintegrator.task.dao;

import ru.bellintegrator.task.model.Office;
import ru.bellintegrator.task.view.office.OfficeFilterView;

import java.util.List;

public interface OfficeDao {
    List<Office> findAll(OfficeFilterView filterView);
    Office findById(Integer id);
    void save(Office office);
    void update( Office office);
}
