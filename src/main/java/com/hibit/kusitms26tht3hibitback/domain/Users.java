package com.hibit.kusitms26tht3hibitback.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "users")
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idx;

    @Column(nullable = false, length = 15)
    private String id;

    @Column(nullable = false, length = 20)
    private String password;

    @Column(nullable = false, length = 20)
    private String nickname;

    @Column(nullable = false, length = 11)
    private String phone_number;

    @Column(nullable = false, length = 2)
    private int age;

    @Column(nullable = false)
    private boolean gender;

    @Column(nullable = true)
    private double temperature;

    @Column(nullable = false, length = 50)
    private String home;

    @Column(nullable = false, length = 100)
    private String introduce;
}
