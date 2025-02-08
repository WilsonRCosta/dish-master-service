package wcosta.dishmaster.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ReactiveElasticsearchRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import wcosta.dishmaster.model.Ingredient;

@Repository
public interface IngredientRepository extends ReactiveElasticsearchRepository<Ingredient, String> {
  Flux<Ingredient> findAllBy(Pageable pageable);

  @Query("{\"prefix\": { \"name\": \"?0\" }}")
  Flux<Ingredient> findByNameStartingWith(String prefix);
}
