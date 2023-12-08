package fr.polyetch.examen.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import fr.polyetch.examen.entity.RestaurantEntity;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RestaurantDto {

    @JsonProperty("id")
    private Integer id;

    @JsonProperty("nom")
    @Size(max = 90)
    private String nom;

    @JsonProperty("adresse")
    @Size(max = 255)
    private String adresse;

    @JsonProperty("evaluations")
    private List<EvaluationDto> evaluations;

    @JsonProperty("evaluation_finale")
    @Nullable
    private EvaluationFinaleDto evaluation_finale;

    @JsonProperty("tags")
    @Nullable
    private List<TagDto> tags;

    public static RestaurantDto buildFromEntity(RestaurantEntity restaurantEntity) {
        if(!restaurantEntity.getEvaluations().isEmpty()) {
            return RestaurantDto.builder()
                    .id(restaurantEntity.getId())
                    .nom(restaurantEntity.getNom())
                    .adresse(restaurantEntity.getAdresse())
                    .evaluations(restaurantEntity.getEvaluations().stream().map(evaluationEntity -> EvaluationDto.buildFromEntity(evaluationEntity)).toList())
                    .build();
        } else {
            return RestaurantDto.builder()
                    .id(restaurantEntity.getId())
                    .nom(restaurantEntity.getNom())
                    .adresse(restaurantEntity.getAdresse())
                    .build();
        }
    }

}
