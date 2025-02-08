package wcosta.dishmaster.repository;

import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Flux;
import wcosta.dishmaster.model.Meal;
import org.springframework.data.elasticsearch.repository.ReactiveElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MealRepository extends ReactiveElasticsearchRepository<Meal, String> {

    Flux<Meal> findAllBy(Pageable pageable);

    Flux<Meal> findByIngredientIdsIn(List<String> ingredientIds);
}
