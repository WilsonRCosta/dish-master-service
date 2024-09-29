package wcosta.dishmaster.service;

import wcosta.dishmaster.dto.FoodDTO;
import wcosta.dishmaster.mappers.FoodMapper;
import wcosta.dishmaster.model.Food;
import wcosta.dishmaster.repository.FoodRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
@Slf4j
public class FoodService {

    private final FoodRepository foodRepository;

    public Mono<Food> createFood(FoodDTO foodDTO) {
        return foodRepository.save(FoodMapper.toFood(foodDTO));
    }

    public Flux<Food> listAllFood() {
        return foodRepository.findAll();
    }
}
