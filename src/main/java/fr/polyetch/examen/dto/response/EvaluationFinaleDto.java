package fr.polyetch.examen.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EvaluationFinaleDto {

    @JsonProperty("decideur")
    @Size(max = 90)
    private String decideur;

    @JsonProperty("note")
    @Min(0)
    @Max(3)
    private Integer note;

    @JsonProperty("description")
    private String description;

}
