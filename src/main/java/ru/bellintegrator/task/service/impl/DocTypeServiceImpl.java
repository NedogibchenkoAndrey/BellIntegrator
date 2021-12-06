package ru.bellintegrator.task.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.bellintegrator.task.dao.DocTypeDao;
import ru.bellintegrator.task.mapper.MapperFacade;
import ru.bellintegrator.task.model.DocType;
import ru.bellintegrator.task.service.DocTypeService;
import ru.bellintegrator.task.view.doctype.DocTypeView;

import java.util.List;

@Service
public class DocTypeServiceImpl implements DocTypeService {

    private final DocTypeDao docTypeDao;
    private final MapperFacade mapperFacade;

    @Autowired
    public DocTypeServiceImpl(DocTypeDao docTypeDao, MapperFacade mapperFacade) {
        this.docTypeDao = docTypeDao;
        this.mapperFacade = mapperFacade;
    }

    @Override
    public List<DocTypeView> findAll() {
        List<DocType> docs = (List<DocType>) docTypeDao.findAll();
        return mapperFacade.mapAsList(docs, DocTypeView.class);
    }
}
