package wcosta.dishmaster.repository;

import org.springframework.data.elasticsearch.annotations.Query;
import reactor.core.publisher.Flux;
import wcosta.dishmaster.model.Meal;
import org.springframework.data.elasticsearch.repository.ReactiveElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MealRepository extends ReactiveElasticsearchRepository<Meal, String> {
    @Query("{\"nested\": {\"path\": \"ingredients\", \"query\": {\"match\": {\"ingredient.name\": \"?0\"}}}}")
    Flux<Meal> findByIngredientName(String ingredientName);
}
