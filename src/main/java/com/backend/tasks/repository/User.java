package com.backend.tasks.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.persistence.*;

/**
 * Implement entity:
 * 1. Map to organization
 * 2. Add equals and hashCode methods
 */
@Entity
@Repository
public class User {
    @Autowired
    JdbcTemplate jdbcTemplate;
    @Id
    private Long id;
    private String username;
    private String password;

    /**
     * Map user with organization by org_id field.
     * Use ManyToOne association.
     */
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="org_id")
    private Organization organization;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
