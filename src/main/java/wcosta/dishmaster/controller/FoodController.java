package wcosta.dishmaster.controller;

import wcosta.dishmaster.dto.FoodDTO;
import wcosta.dishmaster.model.Food;
import wcosta.dishmaster.service.FoodService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/food")
@RequiredArgsConstructor
public class FoodController {

    private final FoodService foodService;

    @PostMapping
    public Mono<Food> createFood(@RequestBody FoodDTO food) {
        return foodService.createFood(food);
    }

    @GetMapping
    public Flux<Food> listAllFood() {
        return foodService.listAllFood();
    }
//
//    @PostMapping("/search")
//    public Flux<Food> searchFood(String name) {
//        return Flux.just();
//    }
}
