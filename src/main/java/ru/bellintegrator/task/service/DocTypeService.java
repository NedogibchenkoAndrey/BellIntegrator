package ru.bellintegrator.task.service;

import ru.bellintegrator.task.view.doctype.DocTypeView;

import java.util.List;

public interface DocTypeService {
    List<DocTypeView> findAll();
}
