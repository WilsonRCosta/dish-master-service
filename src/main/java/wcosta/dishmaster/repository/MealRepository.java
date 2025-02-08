package wcosta.dishmaster.repository;

import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ReactiveElasticsearchRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import wcosta.dishmaster.model.Meal;

@Repository
public interface MealRepository extends ReactiveElasticsearchRepository<Meal, String> {

  Flux<Meal> findAllBy(Pageable pageable);

  Flux<Meal> findByIngredientIdsIn(List<String> ingredientIds);
}
