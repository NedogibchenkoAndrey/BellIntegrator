package ru.bellintegrator.task.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.bellintegrator.task.dao.CountryDao;
import ru.bellintegrator.task.mapper.MapperFacade;
import ru.bellintegrator.task.model.Country;
import ru.bellintegrator.task.service.CountryService;
import ru.bellintegrator.task.view.country.CountryToListView;

import java.util.List;

@Service
public class CountryServiceImpl implements CountryService {

    private final CountryDao countryDao;
    private final MapperFacade mapperFacade;

    @Autowired
    public CountryServiceImpl(CountryDao countryDao, MapperFacade mapperFacade) {
        this.countryDao = countryDao;
        this.mapperFacade = mapperFacade;
    }

    @Override
    public List<CountryToListView> findAll() {
        List<Country> countryList = (List<Country>)countryDao.findAll();
        return mapperFacade.mapAsList(countryList, CountryToListView.class);
    }

}
