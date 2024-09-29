package wcosta.dishmaster.repository;

import org.springframework.data.elasticsearch.annotations.Query;
import reactor.core.publisher.Flux;
import wcosta.dishmaster.model.Dish;
import org.springframework.data.elasticsearch.repository.ReactiveElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DishRepository extends ReactiveElasticsearchRepository<Dish, String> {
    @Query("{\"nested\": {\"path\": \"food\", \"query\": {\"match\": {\"food.name\": \"?0\"}}}}")
    Flux<Dish> findByFoodName(String foodName);
}
