package ru.bellintegrator.task.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "doc")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Doc {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "number", nullable = false, length = 50)
    private String number;

    @Column(name = "date")
    @Temporal(TemporalType.DATE)
    private Date date;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "doc_type_id")
    private DocType docType;

    @Version
    private Integer version;
}
