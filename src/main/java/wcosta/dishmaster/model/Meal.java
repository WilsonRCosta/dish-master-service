package wcosta.dishmaster.model;

import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Getter
@Setter
@RequiredArgsConstructor
@Document(indexName = "meal")
public class Meal {

  @Id private String id;

  private final String name;

  private final int preparationTime;

  private final int cookTime;

  private final int totalTime;

  private final int reviews;

  private final float rating;

  private final String description;

  private final List<String> imagesUrls;

  private final List<String> ingredientIds;

  @Field(type = FieldType.Nested)
  private final List<Step> steps;

  private final List<Category> categories;

  private final Cuisine cuisine;
}
