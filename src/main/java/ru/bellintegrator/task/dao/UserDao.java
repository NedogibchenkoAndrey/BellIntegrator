package ru.bellintegrator.task.dao;

import ru.bellintegrator.task.model.User;
import ru.bellintegrator.task.view.user.UserFilterView;

import java.util.List;

public interface UserDao {
    List<User> findAll(UserFilterView filterView);
    User findById(Integer id);
    void update(User user);
    void save(User user);
}
