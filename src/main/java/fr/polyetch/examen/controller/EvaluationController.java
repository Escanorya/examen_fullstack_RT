package fr.polyetch.examen.controller;

import fr.polyetch.examen.dto.request.AddEvaluationDto;
import fr.polyetch.examen.dto.response.EvaluationDto;
import fr.polyetch.examen.service.EvaluationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
@CrossOrigin(origins = "*")
public class EvaluationController {

    private final EvaluationService evaluationService;

    @PostMapping("/restaurants/{restaurantId}/evaluation")
    public EvaluationDto addEvaluation(@PathVariable Integer restaurantId, @Valid @RequestBody AddEvaluationDto addEvaluationDto) {
        return EvaluationDto.buildFromEntity(this.evaluationService.addEvaluationToRestaurant(restaurantId, addEvaluationDto));
    }
}
