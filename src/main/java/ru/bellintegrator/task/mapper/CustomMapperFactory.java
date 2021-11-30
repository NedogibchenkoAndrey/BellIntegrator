package ru.bellintegrator.task.mapper;

import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Service;
import ru.bellintegrator.task.model.User;
import ru.bellintegrator.task.view.user.UserToByIdView;

@Service
public class CustomMapperFactory implements FactoryBean<MapperFactory> {

    @Override
    public MapperFactory getObject()  {
        DefaultMapperFactory build = new DefaultMapperFactory.Builder()
                .constructorResolverStrategy(null)
                .build();
        build.classMap(User.class, UserToByIdView.class)
                .field("doc.docType.code", "citizenshipCode")
                .field("doc.docType.name", "docName")
                .field("doc.number", "docNumber")
                .field("doc.date", "docDate")
                .byDefault()
                .register();

        return build;
    }

    @Override
    public Class<?> getObjectType() {
        return MapperFactory.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
