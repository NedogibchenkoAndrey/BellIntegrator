package ru.bellintegrator.task.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bellintegrator.task.dao.CountryDao;
import ru.bellintegrator.task.dao.DocTypeDao;
import ru.bellintegrator.task.dao.OfficeDao;
import ru.bellintegrator.task.dao.UserDao;
import ru.bellintegrator.task.mapper.MapperFacade;
import ru.bellintegrator.task.model.*;
import ru.bellintegrator.task.service.UserService;
import ru.bellintegrator.task.view.user.*;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserDao userDao;
    private final OfficeDao officeDao;
    private final MapperFacade mapperFacade;
    private final DocTypeDao docTypeDao;
    private final CountryDao countryDao;


    @Autowired
    public UserServiceImpl(UserDao userDao, OfficeDao officeDao, MapperFacade mapperFacade, DocTypeDao docTypeDao, CountryDao countryDao) {
        this.userDao = userDao;
        this.officeDao = officeDao;
        this.mapperFacade = mapperFacade;
        this.docTypeDao = docTypeDao;
        this.countryDao = countryDao;
    }


    @Override
    public List<UserToListView> findAll(UserFilterView userFilterView) {
        List<User> userList = userDao.findAll(userFilterView);
        return mapperFacade.mapAsList(userList, UserToListView.class);
    }

    @Override
    public UserToByIdView findById(Integer id) {
        var user = userDao.findById(id);
        var userToByIdView = mapperFacade.map(user, UserToByIdView.class);
        return userToByIdView;
    }

    @Override
    @Transactional
    public void save(UserToSaveView userToSaveView) {
        var userSave = mapperFacade.map(userToSaveView, User.class);
        userSave.setOffice(officeDao.findById(userToSaveView.getOfficeId()));

        Doc doc = new Doc();
        doc.setNumber(userToSaveView.getDocNumber());
        doc.setDate(userToSaveView.getDocDate());

        DocType docType = docTypeDao.findDocTypeByNameAndCode(userToSaveView.getDocName(), userToSaveView.getDocCode());
        doc.setDocType(docType);

        doc.setUser(userSave);
        userSave.setDoc(doc);

        Country countryByCode = countryDao.findCountryByCode(userToSaveView.getCitizenshipCode());
        userSave.setCountry(countryByCode);
        userDao.save(userSave);
    }

    @Override
    @Transactional
    public void update(UserToUpdateView userToUpdateView) {
        User userUpdate = userDao.findById(userToUpdateView.getId());
        mapperFacade.map(userToUpdateView, userUpdate);

        Doc doc = userUpdate.getDoc();
        doc.setNumber(userToUpdateView.getDocNumber());
        doc.setDate(userToUpdateView.getDocDate());

        userUpdate.setOffice(officeDao.findById(userToUpdateView.getOfficeId()));

        Country country = countryDao.findCountryByCode(userToUpdateView.getCitizenshipCode());

        userUpdate.setCountry(country);
        userDao.update(userUpdate);
    }
}
