package wcosta.dishmaster.service;

import wcosta.dishmaster.dto.IngredientDTO;
import wcosta.dishmaster.mappers.IngredientMapper;
import wcosta.dishmaster.model.Ingredient;
import wcosta.dishmaster.repository.IngredientRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
@Slf4j
public class IngredientService {

    private final IngredientRepository ingredientRepository;

    public Mono<Ingredient> createIngredient(IngredientDTO dto) {
        return ingredientRepository.save(IngredientMapper.toIngredient(dto));
    }

    public Flux<Ingredient> listAllIngredients() {
        return ingredientRepository.findAll();
    }
}
