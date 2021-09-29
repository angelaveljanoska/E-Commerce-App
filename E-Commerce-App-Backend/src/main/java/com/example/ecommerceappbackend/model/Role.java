package com.example.ecommerceappbackend.model;

import com.example.ecommerceappbackend.model.enumerations.URole;

import javax.persistence.*;


@Entity
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private URole name;


    public Role() {
    }

    public Role(URole name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public URole getName() {
        return name;
    }

    public void setName(URole name) {
        this.name = name;
    }
}
