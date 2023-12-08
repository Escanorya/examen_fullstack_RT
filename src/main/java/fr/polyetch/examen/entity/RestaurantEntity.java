package fr.polyetch.examen.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity(name = "restaurants")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RestaurantEntity {

    @Id
    @GeneratedValue()
    private Integer id;

    @Column(name = "nom", columnDefinition = "varchar(90)", nullable = false)
    private String nom;

    @Column(name = "adresse", columnDefinition = "varchar(255)", nullable = false)
    private String adresse;

    @OneToMany(mappedBy = "restaurant")
    private List<EvaluationEntity> evaluations;

    @ManyToMany
    @JoinTable(name = "tags_in_restaurant",
            joinColumns = @JoinColumn(name = "restaurant"),
            inverseJoinColumns = @JoinColumn(name = "tag"))
    private List<TagEntity> tags;

}
