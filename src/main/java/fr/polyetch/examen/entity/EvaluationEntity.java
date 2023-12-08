package fr.polyetch.examen.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.List;

@Entity(name = "evaluations")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EvaluationEntity {

    @Id
    @GeneratedValue()
    private Integer id;

    @Column(name = "evaluateur", columnDefinition = "varchar(50)", nullable = false)
    private String evaluateur;

    @Column(name = "commentaire", columnDefinition = "varchar(255)", nullable = false)
    private String commentaire;

    @Column(name = "note", nullable = false)
    private Integer note;

    @Column(name = "date_creation", nullable = false)
    private Date date_creation;

    @Column(name = "date_maj")
    private Date date_maj;

    @ManyToOne()
    @JoinColumn(name = "restaurant")
    private RestaurantEntity restaurant;
}
