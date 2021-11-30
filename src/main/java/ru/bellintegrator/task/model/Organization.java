package ru.bellintegrator.task.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "organization")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Organization {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "name",  nullable = false, length = 50)
    private String name;

    @Column(name = "full_name",  nullable = false, length = 100)
    private String fullName;

    @Column(name = "inn",  nullable = false, length = 12)
    private String inn;

    @Column(name = "kpp",  nullable = false, length = 9)
    private String kpp;

    @Column(name = "address",  nullable = false, length = 200)
    private String address;

    @Column(name = "phone", length = 20)
    private String phone;

    @Column(name = "is_active", nullable = false)
    private Boolean isActive;

    @Version
    private Integer version;

}
