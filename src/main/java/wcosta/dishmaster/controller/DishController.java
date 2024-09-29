package wcosta.dishmaster.controller;

import wcosta.dishmaster.dto.DishDTO;
import wcosta.dishmaster.model.Dish;
import wcosta.dishmaster.service.DishService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/dishes")
@RequiredArgsConstructor
public class DishController {

    private final DishService dishService;

    @PostMapping
    public Mono<Dish> createDish(@RequestBody DishDTO dish) {
        return dishService.saveDishAndAllFood(dish);
    }

    @GetMapping("/search")
    public Flux<Dish> searchDishesByFood(@RequestParam("foodName") String foodName) {
        return dishService.searchDishesByFoodName(foodName);
    }

    @PostMapping("/bulk")
    public Mono<Boolean> createDishBulk(@RequestBody List<DishDTO> dish) {
        return dishService.createDishBulk(dish);
    }

    @GetMapping
    public Flux<Dish> getDishes(@RequestParam(value = "numOfDishes", required = false) Integer portion) {
        return dishService.getDishes(Optional.ofNullable(portion).orElse(1));
    }


}
