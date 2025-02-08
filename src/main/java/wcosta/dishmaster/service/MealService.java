package wcosta.dishmaster.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import wcosta.dishmaster.dto.IngredientDTO;
import wcosta.dishmaster.dto.MealDTO;
import wcosta.dishmaster.exceptions.NotFoundException;
import wcosta.dishmaster.mappers.DishMasterMapper;
import wcosta.dishmaster.model.Ingredient;
import wcosta.dishmaster.model.Meal;
import wcosta.dishmaster.repository.MealRepository;

@Service
@RequiredArgsConstructor
@Slf4j
public class MealService {

  private final MealRepository mealRepository;

  private final IngredientService ingredientService;

  private final DishMasterMapper mapper;

  public Flux<Meal> getMeals(int page, int size) {
    return mealRepository.findAllBy(PageRequest.of(page, size));
  }

  public Flux<Meal> searchMealsByIngredient(String name) {
    return ingredientService
        .searchByName(name)
        .map(Ingredient::getId)
        .collectList()
        .flatMapMany(mealRepository::findByIngredientIdsIn);
  }

  public Mono<Boolean> createMealsBulk(@RequestBody List<MealDTO> mealDTO) {
    return Flux.fromIterable(mealDTO)
        .flatMap(this::saveMealAndIngredients)
        .then(Mono.just(true))
        .onErrorResume(
            error -> {
              log.error("Error occurred during bulk save: {}", error.getMessage());
              return Mono.just(false);
            });
  }

  public Mono<Meal> saveMealAndIngredients(MealDTO mealDTO) {
    final Meal meal = mapper.toEntity(mealDTO);

    if (mealDTO.ingredients().isEmpty()) {
      return mealRepository.save(meal);
    }

    return Flux.fromIterable(mapper.toIngredientEntityList(mealDTO.ingredients()))
        .flatMap(ingredientService::saveOrUpdateIngredient)
        .collectList()
        .flatMap(_ -> mealRepository.save(meal));
  }

  public void deleteMeal(String id) {
    mealRepository.deleteById(id);
  }

  public Mono<Meal> addIngredientToMeal(String id, IngredientDTO ingredientDTO) {
    return mealRepository
        .findById(id)
        .switchIfEmpty(Mono.error(new NotFoundException("Meal " + id + " not found.")))
        .flatMap(
            meal -> {
              final var ingredient = mapper.toEntity(ingredientDTO);
              meal.getIngredientIds().add(ingredient.getId());
              ingredientService.saveOrUpdateIngredient(ingredient);
              return mealRepository.save(meal);
            });
  }

  public Mono<Meal> removeIngredientFromMeal(String id, String ingId) {
    return mealRepository
        .findById(id)
        .switchIfEmpty(Mono.error(new NotFoundException("Meal " + id + " not found.")))
        .flatMap(
            meal -> {
              meal.getIngredientIds().removeIf(iid -> iid.equals(ingId));
              ingredientService.deleteIngredient(ingId);
              return mealRepository.save(meal);
            });
  }
}
