package ru.bellintegrator.task.service;

import ru.bellintegrator.task.model.User;
import ru.bellintegrator.task.view.user.*;

import java.util.List;

public interface UserService {
    List<UserToListView> findAll(UserFilterView userFilterView);
    UserToByIdView findById(Integer id);
    void save(UserToSaveView userToSaveView);
    void update(UserToUpdateView userToUpdateView);
}
