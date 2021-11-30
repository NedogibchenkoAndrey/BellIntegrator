package ru.bellintegrator.task.mapper;

import ru.bellintegrator.task.model.Office;
import ru.bellintegrator.task.view.office.OfficeToUpdateView;

import java.util.List;

public interface MapperFacade {

    <S, D> List<D> mapAsList(Iterable<S> source, Class<D> destinationClass);

    <S, D> D map(S sourceObject, Class<D> destinationClass);

    <S, D> void map(S sourceObject, D destinationObject);


}
