package ru.bellintegrator.task.dao;

import org.springframework.data.repository.CrudRepository;
import ru.bellintegrator.task.model.DocType;

public interface DocTypeDao extends CrudRepository<DocType, Integer> {

    DocType findDocTypeByNameAndCode(String name, String code);
}
