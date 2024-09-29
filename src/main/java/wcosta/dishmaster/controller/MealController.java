package wcosta.dishmaster.controller;

import wcosta.dishmaster.dto.MealDTO;
import wcosta.dishmaster.model.Meal;
import wcosta.dishmaster.service.MealService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/meals")
@RequiredArgsConstructor
public class MealController {

    private final MealService mealService;

    @PostMapping
    public Mono<Meal> createMeal(@RequestBody MealDTO dto) {
        return mealService.saveMealAndIngredients(dto);
    }

    @GetMapping("/search/ingredient")
    public Flux<Meal> searchMealByIngredient(@RequestParam("ingredient") String ingredientName) {
        return mealService.searchMealsByIngredient(ingredientName);
    }

    @PostMapping("/{id}/ingredients")
    public Mono<Meal> addIngredientToMeal() {
        return Mono.just(null);
    }

    @DeleteMapping("/{id}/ingredients/{ingId}")
    public Mono<Meal> removeIngredientFromMeal() {
        return Mono.just(null);
    }

    @PostMapping("/bulk")
    public Mono<Boolean> createMealsBulk(@RequestBody List<MealDTO> dtos) {
        return mealService.createMealsBulk(dtos);
    }

    @GetMapping
    public Flux<Meal> getMeals(@RequestParam(value = "numOfMeals", required = false) Integer portion) {
        return mealService.getMeals(Optional.ofNullable(portion).orElse(1));
    }

    @DeleteMapping("/{id}")
    public void deleteMeal(String id) {

    }
}
