package wcosta.dishmaster.repository;

import wcosta.dishmaster.model.Ingredient;
import org.springframework.data.elasticsearch.repository.ReactiveElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IngredientRepository extends ReactiveElasticsearchRepository<Ingredient, String> {
}
