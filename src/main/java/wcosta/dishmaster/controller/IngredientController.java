package wcosta.dishmaster.controller;

import wcosta.dishmaster.dto.IngredientDTO;
import wcosta.dishmaster.model.Ingredient;
import wcosta.dishmaster.service.IngredientService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/ingredients")
@RequiredArgsConstructor
public class IngredientController {

    private final IngredientService ingredientService;

    @PostMapping
    public Mono<Ingredient> createIngredient(@RequestBody IngredientDTO dto) {
        return ingredientService.createIngredient(dto);
    }

    @GetMapping
    public Flux<Ingredient> getIngredients(@RequestParam(defaultValue = "0") int page,
                                               @RequestParam(defaultValue = "10") int size) {
        return ingredientService.listAllIngredients(page, size);
    }
}
