package wcosta.dishmaster.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import wcosta.dishmaster.dto.MealDTO;
import wcosta.dishmaster.mappers.DishMasterMapper;
import wcosta.dishmaster.model.Meal;
import wcosta.dishmaster.model.Ingredient;
import wcosta.dishmaster.repository.MealRepository;
import wcosta.dishmaster.repository.IngredientRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class MealService {

    private final MealRepository mealRepository;

    private final IngredientRepository ingredientRepository;

    private final DishMasterMapper mappers;

    public Flux<Meal> getMeals(int portion) {
        return mealRepository.findAll();
    }

    public Flux<Meal> searchMealsByIngredient(String name) {
        return mealRepository.findByIngredientName(name);
    }

    public Mono<Boolean> createMealsBulk(@RequestBody List<MealDTO> mealDTO) {
        return Flux.fromIterable(mealDTO)
                .flatMap(this::saveMealAndIngredients)
                .then(Mono.just(true))
                .onErrorResume(error -> {
                    log.error("Error occurred during bulk save: {}", error.getMessage());
                    return Mono.just(false);
                });
    }

    public Mono<Meal> saveMealAndIngredients(MealDTO mealDTO) {
        final Meal meal = mappers.toEntity(mealDTO);

        if (meal.getIngredients().isEmpty()) {
            return mealRepository.save(meal);
        }

        return Flux.fromIterable(meal.getIngredients())
                .flatMap(this::saveOrUpdateIngredient)
                .collectList()
                .flatMap(savedIngredients -> {
                    if (savedIngredients.size() == meal.getIngredients().size()) {
                        return mealRepository.save(meal);
                    } else {
                        return Mono.error(new RuntimeException("Not all ingredients items were saved"));
                    }
                });
    }

    private Mono<Ingredient> saveOrUpdateIngredient(Ingredient ingredient) {
        return ingredientRepository.findById(ingredient.getId()).switchIfEmpty(ingredientRepository.save(ingredient));
    }
}
