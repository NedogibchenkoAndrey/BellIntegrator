package ru.bellintegrator.task.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "doc_type")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DocType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "name",  nullable = false, length = 50)
    private String name;

    @Column(name = "code",  nullable = false, length = 20)
    private String code;

    @Version
    private Integer version;

}
