package ru.bellintegrator.task.service;

import ru.bellintegrator.task.view.doc.DocView;

import java.util.List;

public interface DocService {
    List<DocView> findAll();
}
