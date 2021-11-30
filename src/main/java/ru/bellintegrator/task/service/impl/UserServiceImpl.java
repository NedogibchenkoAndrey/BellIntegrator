package ru.bellintegrator.task.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bellintegrator.task.dao.CountryDao;
import ru.bellintegrator.task.dao.DocTypeDao;
import ru.bellintegrator.task.dao.OfficeDao;
import ru.bellintegrator.task.dao.UserDao;
import ru.bellintegrator.task.mapper.MapperFacade;
import ru.bellintegrator.task.model.*;
import ru.bellintegrator.task.response.exception.DataNotFoundException;
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

        if (user == null) {
            throw new DataNotFoundException("User with this id " + id + " not found!");
        } else {
            var userToByIdView = mapperFacade.map(user, UserToByIdView.class);
            return userToByIdView;
        }
    }

    @Override
    @Transactional
    public void save(UserToSaveView userToSaveView) {
        var userSave = mapperFacade.map(userToSaveView, User.class);

        if (userToSaveView.getOfficeId() != null) {
            try {
                userSave.setOffice(officeDao.findById(userToSaveView.getOfficeId()));
            } catch (EmptyResultDataAccessException ex) {
                throw new DataNotFoundException("Office with this id " + userToSaveView.getOfficeId() + " not found!");
            }
        }

        Doc doc = new Doc();
        doc.setNumber(userToSaveView.getDocNumber());
        doc.setDate(userToSaveView.getDocDate());

        DocType docType;
        if (userToSaveView.getDocName() != null && userToSaveView.getDocCode() != null) {
            docType = docTypeDao.findDocTypeByNameAndCode(userToSaveView.getDocName(), userToSaveView.getDocCode());

            if (docType != null) {
                doc.setDocType(docType);
            } else {
                throw new DataNotFoundException("Document type not found!");
            }
        }


        doc.setUser(userSave);
        userSave.setDoc(doc);

        Country countryByCode = countryDao.findCountryByCode(userToSaveView.getCitizenshipCode());

        if (countryByCode != null) {
            userSave.setCountry(countryByCode);
            userDao.save(userSave);
        } else {
            throw new DataNotFoundException("Country with this code not found!");
        }
    }

    @Override
    @Transactional
    public void update(UserToUpdateView userToUpdateView) {
        User userUpdate = userDao.findById(userToUpdateView.getId());
        if (userUpdate == null) {
            throw new DataNotFoundException("User with this id not found!");
        }
        mapperFacade.map(userToUpdateView, userUpdate);

        Doc doc = userUpdate.getDoc();
        if (userToUpdateView.getDocNumber() != null) {
            doc.setNumber(userToUpdateView.getDocNumber());
        } else {
            throw new DataNotFoundException("Enter the Document number correctly!");
        }

        if (userToUpdateView.getDocDate() != null) {
            doc.setDate(userToUpdateView.getDocDate());
        } else {
            throw new DataNotFoundException("Enter the date \"yyyy-MM-dd\" correctly!");
        }

        if (userToUpdateView.getOfficeId() != null) {
            try {
                userUpdate.setOffice(officeDao.findById(userToUpdateView.getOfficeId()));
            } catch (EmptyResultDataAccessException ex) {
                throw new DataNotFoundException("Office with this id not found!");
            }
        }
        if (userToUpdateView.getCitizenshipCode() != null) {
            Country country = countryDao.findCountryByCode(userToUpdateView.getCitizenshipCode());

            if (country == null) {
                throw new DataNotFoundException("Enter the country code correctly!");
            } else {
                userUpdate.setCountry(country);
                userDao.update(userUpdate);
            }
        }
    }
}
