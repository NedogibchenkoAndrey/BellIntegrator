package ru.bellintegrator.task.service.impl;

import org.dom4j.DocumentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bellintegrator.task.dao.DocTypeDao;
import ru.bellintegrator.task.mapper.MapperFacade;
import ru.bellintegrator.task.model.DocType;
import ru.bellintegrator.task.service.DocService;
import ru.bellintegrator.task.view.doc.DocView;

import java.util.List;

@Service
public class DocServiceImpl implements DocService {

    private final DocTypeDao docTypeDao;
    private final MapperFacade mapperFacade;

    @Autowired
    public DocServiceImpl(DocTypeDao docTypeDao, MapperFacade mapperFacade) {
        this.docTypeDao = docTypeDao;
        this.mapperFacade = mapperFacade;
    }

    @Override
    public List<DocView> findAll() {
        List<DocType> docs = (List<DocType>) docTypeDao.findAll();
        return mapperFacade.mapAsList(docs, DocView.class);
    }
}
