package ru.bellintegrator.task.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.bellintegrator.task.service.UserService;
import ru.bellintegrator.task.view.user.*;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;


@RestController
@RequestMapping(value = "/api/user", produces = APPLICATION_JSON_VALUE)
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


    @PostMapping("/list")
    @ApiOperation(value = "Get a list of users by filter", httpMethod = "POST")
    public List<UserToListView> findAll(@Valid @RequestBody UserFilterView userFilterView) {
        return userService.findAll(userFilterView);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Get a find by id of users by filter", httpMethod = "GET")
    public UserToByIdView findById(@PathVariable Integer id) {
        return userService.findById(id);
    }

    @PostMapping("/save")
    @ApiOperation(value = "Get a save of user", httpMethod = "POST")
    public void add(@Valid @RequestBody UserToSaveView userToSaveView) {
        userService.save(userToSaveView);
    }

    @PostMapping("/update")
    @ApiOperation(value = "Get a update of user", httpMethod = "POST")
    public void update(@Valid @RequestBody UserToUpdateView userToUpdateView) {
        userService.update(userToUpdateView);
    }
}
