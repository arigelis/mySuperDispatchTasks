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
 * Implement create, read, update, delete  rest controller endpoints for organization.
 * Map endpoints to /orgs path.
 * 1. Post to /orgs endpoint should create and return organization. Response status should be 201.
 * 2. Put to /orgs/{orgId} endpoint should update, save and return organization with id=orgId.
 * 3. Get to /orgs/{orgId} endpoint should fetch and return organization with id=orgId.
 * 4. Delete to /orgs/{orgId} endpoint should delete organization with id=orgId. Response status should be 204.
 * 5. Get to /orgs endpoint should return list of all organizations
 */
@RestController
@Repository
public class OrganizationController {
    @Autowired
    JdbcTemplate jdbcTemplate;
    @ResponseStatus(reason = "201")
    @RequestMapping("/orgs")
    public Organization post(long orgId) {
        List<Organization> orgList = jdbcTemplate.query( " INSERT into organization (id, name)"
                        + " values (orgId, ?)",
                new BeanPropertyRowMapper<Organization>(Organization.class));
        if (orgList.isEmpty()) {
            return null;
        } else {
            return orgList.get(0);
        }
    }
    @RequestMapping("/orgs/orgId")
    public Organization put(long orgId) {
        List<Organization> orgList = jdbcTemplate.query("UPDATE * from organization where id=" + orgId,
                new BeanPropertyRowMapper<Organization>(Organization.class));
        if (orgList.isEmpty()) {
            return null;
        } else {
            return orgList.get(0);
        }
    }

    @RequestMapping("/orgs/orgId")
    public Organization get(long orgId) {
        List<Organization> orgList = jdbcTemplate.query("SELECT * from organization where id=" + orgId,
                new BeanPropertyRowMapper<Organization>(Organization.class));
        if (orgList.isEmpty()) {
            return null;
        } else {
            return orgList.get(0);
        }
    }

    @ResponseStatus(reason = "204")
    @RequestMapping("/orgs/orgId")
    public void delete(long orgId) {
        List<Organization> orgList = jdbcTemplate.query("DELETE * from organization where id=" + orgId,
                new BeanPropertyRowMapper<Organization>(Organization.class));
    }

    @RequestMapping("/orgs")
    public List<Organization>get() {
        List<Organization> orgList = jdbcTemplate.query("SELECT * from organization",
                new BeanPropertyRowMapper<Organization>(Organization.class));
        if (orgList.isEmpty()) {
            return null;
        } else {
            return orgList;
        }
    }
}
