package fr.polyetch.examen.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "evaluations_finales")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EvaluationFinaleEntity {

    @Id
    @GeneratedValue()
    private Integer id;

    @Column(name = "decideur", columnDefinition = "varchar(90)", nullable = false)
    private String decideur;

    @Column(name = "note", nullable = false)
    private Integer note;

    @Column(name = "description", nullable = false)
    private String description;

}
