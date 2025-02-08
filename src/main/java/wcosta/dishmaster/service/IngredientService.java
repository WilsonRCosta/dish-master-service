package wcosta.dishmaster.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import wcosta.dishmaster.dto.IngredientDTO;
import wcosta.dishmaster.mappers.DishMasterMapper;
import wcosta.dishmaster.model.Ingredient;
import wcosta.dishmaster.repository.IngredientRepository;

@Service
@RequiredArgsConstructor
@Slf4j
public class IngredientService {

  private final DishMasterMapper mapper;

  private final IngredientRepository ingredientRepository;

  public Mono<Ingredient> createIngredient(IngredientDTO dto) {
    return ingredientRepository.save(mapper.toEntity(dto));
  }

  public Flux<Ingredient> listAllIngredients(int page, int size) {
    return ingredientRepository.findAllBy(PageRequest.of(page, size));
  }

  public Mono<Ingredient> saveOrUpdateIngredient(Ingredient ingredient) {
    return ingredientRepository
        .findById(ingredient.getId())
        .switchIfEmpty(ingredientRepository.save(ingredient));
  }

  public Flux<Ingredient> searchByName(String prefix) {
    return ingredientRepository.findByNameStartingWith(prefix);
  }

  public void deleteIngredient(String id) {
    ingredientRepository.deleteById(id);
  }
}
