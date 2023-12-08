package fr.polyetch.examen.controller;

import fr.polyetch.examen.dto.response.RestaurantDto;
import fr.polyetch.examen.dto.response.UrlDto;
import fr.polyetch.examen.service.RestaurantService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@CrossOrigin(origins = "*")
public class RestaurantController {

    private final RestaurantService restaurantService;

    @GetMapping("/restaurants")
    public List<RestaurantDto> getRestaurants(@RequestParam(value = "id", required = false) ArrayList<Integer> idsToFetch) {
        if (idsToFetch == null || idsToFetch.isEmpty()) {
            return this.restaurantService.getRestaurants().stream().map(restaurantEntity -> RestaurantDto.buildFromEntity(restaurantEntity)).toList();
        } else {
            return this.restaurantService.getRestaurants(idsToFetch).stream().map(restaurantEntity -> RestaurantDto.buildFromEntity(restaurantEntity)).toList();
        }
    }

    @GetMapping("/restaurants/{id}")
    public RestaurantDto getRestaurantById(@PathVariable Integer id) {
        return RestaurantDto.buildFromEntity(this.restaurantService.getRestaurantById(id));
    }

    @GetMapping("/restaurants/{id}/image")
    public UrlDto getRestaurantImageById(@PathVariable Integer id) {
        return UrlDto.builder().url(this.restaurantService.getImageDownloadUrl(id)).build();
    }

    @PutMapping("/restaurants/{id}/image")
    public UrlDto putRestaurantImageById(@PathVariable Integer id) {
        return UrlDto.builder().url(this.restaurantService.putImageDownloadUrl(id)).build();
    }

    @PostMapping("/restaurants")
    public RestaurantDto addRestaurant(@Valid @RequestBody RestaurantDto restaurantDto) {
        return RestaurantDto.buildFromEntity(this.restaurantService.addRestaurant(restaurantDto));
    }

    @DeleteMapping("/restaurants/{id}")
    public void deleteRestaurant(@PathVariable Integer id) {
        this.restaurantService.deleteRestaurant(id);
    }
}
