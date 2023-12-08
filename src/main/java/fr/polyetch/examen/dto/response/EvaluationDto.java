package fr.polyetch.examen.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import fr.polyetch.examen.entity.EvaluationEntity;
import fr.polyetch.examen.entity.RestaurantEntity;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import java.util.Date;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EvaluationDto {

    @JsonProperty("id")
    private Integer id;

    @JsonProperty("evaluateur")
    @Size(max = 50)
    private String evaluateur;

    @JsonProperty("commentaire")
    @Size(max = 255)
    private String commentaire;

    @JsonProperty("note")
    @Min(0)
    @Max(3)
    private Integer note;

    @JsonProperty("date_creation")
    private Date date_creation;

    @JsonProperty("date_maj")
    @Nullable
    private Date date_maj;

    public static EvaluationDto buildFromEntity(EvaluationEntity evaluationEntity) {
        return EvaluationDto.builder()
                .id(evaluationEntity.getId())
                .evaluateur(evaluationEntity.getEvaluateur())
                .commentaire(evaluationEntity.getCommentaire())
                .note(evaluationEntity.getNote())
                .date_creation(evaluationEntity.getDate_creation())
                .build();
    }

}
