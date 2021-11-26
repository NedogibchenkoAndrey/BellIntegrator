package ru.bellintegrator.task.dao;

import org.dom4j.DocumentType;
import org.springframework.data.repository.CrudRepository;
import ru.bellintegrator.task.model.DocType;

public interface DocTypeDao extends CrudRepository<DocType, Integer> {

    // получаем документ по коду
    DocType findDocTypeByCode(Integer code);

    DocType findDocTypeByNameAndCode(String name, String code);
}
