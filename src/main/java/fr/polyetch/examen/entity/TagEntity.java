package fr.polyetch.examen.entity;

import fr.polyetch.examen.dto.response.TagDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity(name = "tags")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TagEntity {

    @Id
    @GeneratedValue()
    private Integer id;

    @Enumerated(EnumType.STRING)
    private TagDto tagDto;

    @ManyToMany(mappedBy = "tags")
    private List<RestaurantEntity> restaurants;

}
