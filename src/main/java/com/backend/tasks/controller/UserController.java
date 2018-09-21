package com.backend.tasks.controller;

import com.backend.tasks.repository.Organization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Implement create, read, update, delete  rest controller endpoints for user.
 * Map endpoints to /orgs/{orgId}/users path
 * 1. Post to /orgs/{orgId}/users endpoint should create and return user for organization with id=orgId. Response status should be 201.
 * 2. Put to /orgs/{orgId}/users/{userId} endpoint should update, save and return user with id=userId for organization with id=orgId.
 * 3. Get to /orgs/{orgId}/users/{userId} endpoint should fetch and return user with id=userId for organization with id=orgId.
 * 4. Delete to /orgs/{orgId}/users/{userId} endpoint should delete user with id=userId for organization with id=orgId. Response status should be 204.
 * 5. Get to /orgs/{orgId}/users endpoint should return list of all users for organization with id=orgId
 */
@RestController
@Repository
public class UserController {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @ResponseStatus(reason = "201")
    @RequestMapping("/orgs/{orgId}/users ")
    public Organization post(long orgId) {
        List<Organization> orgList = jdbcTemplate.query(" INSERT into users (id, name) where id=" + orgId
                        + " values (1, ?)",
                new BeanPropertyRowMapper<Organization>(Organization.class));
        if (orgList.isEmpty()) {
            return null;
        } else {
            return orgList.get(0);
        }
    }

    @RequestMapping("/orgs/{orgId}/users/{userId} ")
    public Organization put(long orgId, long userId) {
        List<Organization> orgList = jdbcTemplate.query("UPDATE * from users where id=" + userId + "and orgId = " + orgId,
                new BeanPropertyRowMapper<Organization>(Organization.class));
        if (orgList.isEmpty()) {
            return null;
        } else {
            return orgList.get(0);
        }
    }

    @RequestMapping("/orgs/{orgId}/users/{userId}")
    public Organization get(long orgId, long userId) {
        List<Organization> orgList = jdbcTemplate.query("SELECT * from users where id=" + userId + "and orgId = " + orgId,
                new BeanPropertyRowMapper<Organization>(Organization.class));
        if (orgList.isEmpty()) {
            return null;
        } else {
            return orgList.get(0);
        }
    }

    @ResponseStatus(reason = "204")
    @RequestMapping(" /orgs/{orgId}/users/{userId")
    public void delete(long orgId, long userId) {
        List<Organization> orgList = jdbcTemplate.query("DELETE * from users where orgId=" + orgId + "and id="+userId,
                new BeanPropertyRowMapper<Organization>(Organization.class));
    }

    @RequestMapping("/orgs/{orgId}/users")
    public List<Organization> get() {
        List<Organization> orgList = jdbcTemplate.query("SELECT * from users",
                new BeanPropertyRowMapper<Organization>(Organization.class));
        if (orgList.isEmpty()) {
            return null;
        } else {
            return orgList;
        }
    }

}
