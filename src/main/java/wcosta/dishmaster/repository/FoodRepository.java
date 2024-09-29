package wcosta.dishmaster.repository;

import wcosta.dishmaster.model.Food;
import org.springframework.data.elasticsearch.repository.ReactiveElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FoodRepository extends ReactiveElasticsearchRepository<Food, String> {
}
