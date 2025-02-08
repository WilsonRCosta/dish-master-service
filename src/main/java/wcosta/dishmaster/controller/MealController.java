package wcosta.dishmaster.controller;

import org.springframework.http.HttpStatus;
import wcosta.dishmaster.dto.IngredientDTO;
import wcosta.dishmaster.dto.MealDTO;
import wcosta.dishmaster.model.Meal;
import wcosta.dishmaster.service.MealService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

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
    public Flux<Meal> searchMealByIngredient(@RequestParam("name") String name) {
        return mealService.searchMealsByIngredient(name);
    }

    @PostMapping("/{id}/ingredients")
    @ResponseStatus(HttpStatus.OK)
    public Mono<Meal> addIngredientToMeal(@PathVariable("id") String id, @RequestBody IngredientDTO dto) {
        return mealService.addIngredientToMeal(id, dto);
    }

    @DeleteMapping("/{id}/ingredients/{ingId}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<Meal> removeIngredientFromMeal(@PathVariable("id") String id, @PathVariable("ingId") String ingId) {
        return mealService.removeIngredientFromMeal(id, ingId);
    }

    @PostMapping("/bulk")
    public Mono<Boolean> createMealsBulk(@RequestBody List<MealDTO> dtos) {
        return mealService.createMealsBulk(dtos);
    }

    @GetMapping
    public Flux<Meal> getMeals(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        return mealService.getMeals(page, size);
    }

    @DeleteMapping("/{id}")
    public void deleteMeal(String id) {
        mealService.deleteMeal(id);
    }
}
