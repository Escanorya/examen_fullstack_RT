package fr.polyetch.examen.service;

import fr.polyetch.examen.dto.response.RestaurantDto;
import fr.polyetch.examen.entity.RestaurantEntity;
import fr.polyetch.examen.exception.ResourceNotFoundException;
import fr.polyetch.examen.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;
    private final S3Service s3Service;

    public List<RestaurantEntity> getRestaurants() {
        return this.restaurantRepository.findAll();
    }

    public List<RestaurantEntity> getRestaurants(final List<Integer> idsToFetch) {
        return this.restaurantRepository.findAllById(idsToFetch);
    }

    public RestaurantEntity getRestaurantById(final Integer id) {
        return this.restaurantRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Restaurant with id " + id + " + not found"));
    }

    public RestaurantEntity addRestaurant(final RestaurantDto restaurantDto) {
        final RestaurantEntity restaurantToInsert = RestaurantEntity.builder()
                .nom(restaurantDto.getNom())
                .adresse(restaurantDto.getAdresse())
                .evaluations(new ArrayList<>())
                .build();

        return this.restaurantRepository.save(restaurantToInsert);
    }

    public void deleteRestaurant(final Integer id) {
        this.restaurantRepository.deleteById(id);
    }

    public String getImageDownloadUrl(final Integer restaurantId) {
        this.getRestaurantById(restaurantId);

        return this.s3Service.getDownloadUrl("RESTO_DE_LA_MAMA" + restaurantId);
    }

    public String putImageDownloadUrl(final Integer restaurantId) {
        this.getRestaurantById(restaurantId);

        return this.s3Service.putDownloadUrl("RESTO_DE_LA_MAMA" + restaurantId);
    }

}
