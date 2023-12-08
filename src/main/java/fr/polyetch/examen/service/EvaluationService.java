package fr.polyetch.examen.service;

import fr.polyetch.examen.dto.request.AddEvaluationDto;
import fr.polyetch.examen.entity.EvaluationEntity;
import fr.polyetch.examen.entity.RestaurantEntity;
import fr.polyetch.examen.repository.EvaluationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;

@Service
@Slf4j
@RequiredArgsConstructor
public class EvaluationService {

    private final EvaluationRepository evaluationRepository;
    private final RestaurantService restaurantService;

    public EvaluationEntity addEvaluationToRestaurant(final Integer restaurantId, final AddEvaluationDto dto) {
        final RestaurantEntity restaurant = this.restaurantService.getRestaurantById(restaurantId);

        final EvaluationEntity evaluation = EvaluationEntity.builder()
                .evaluateur(dto.getEvaluateur())
                .commentaire(dto.getCommentaire())
                .note(dto.getNote())
                .date_creation(Date.valueOf(LocalDate.now()))
                .restaurant(restaurant)
                .build();

        return this.evaluationRepository.save(evaluation);
    }

}
