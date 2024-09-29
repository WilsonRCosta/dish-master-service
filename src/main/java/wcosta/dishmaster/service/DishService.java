package wcosta.dishmaster.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;
import wcosta.dishmaster.dto.DishDTO;
import wcosta.dishmaster.mappers.DishMapper;
import wcosta.dishmaster.model.Dish;
import wcosta.dishmaster.model.Food;
import wcosta.dishmaster.repository.DishRepository;
import wcosta.dishmaster.repository.FoodRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class DishService {

    private final DishRepository dishRepository;

    private final FoodRepository foodRepository;

    public Flux<Dish> getDishes(int portion) {
        return dishRepository.findAll();
    }

    public Flux<Dish> searchDishesByFoodName(String foodName) {
        return dishRepository.findByFoodName(foodName);
    }

    public Mono<Boolean> createDishBulk(@RequestBody List<DishDTO> dishDTO) {
        return Flux.fromIterable(dishDTO)
                .flatMap(this::saveDishAndAllFood)
                .then(Mono.just(true))
                .onErrorResume(error -> {
                    log.error("Error occurred during bulk save: {}", error.getMessage());
                    return Mono.just(false);
                });
    }

    public Mono<Dish> saveDishAndAllFood(DishDTO dishDTO) {
        final var dish = DishMapper.toDish(dishDTO);

        if (dish.getFood().isEmpty()) {
            return dishRepository.save(dish);
        }

        return Flux.fromIterable(dish.getFood())
                .flatMap(this::saveOrUpdateFood)
                .collectList()
                .flatMap(savedFood -> {
                    if (savedFood.size() == dish.getFood().size()) {
                        return dishRepository.save(dish);
                    } else {
                        return Mono.error(new RuntimeException("Not all food items were saved"));
                    }
                });
    }

    private Mono<Food> saveOrUpdateFood(Food food) {
        return foodRepository.findById(food.getId()).switchIfEmpty(foodRepository.save(food));
    }
}
