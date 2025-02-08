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
@Document(indexName = "ingredient")
public class Ingredient {

  @Id private String id;

  private final String name;

  private final int serving;

  @Field(type = FieldType.Nested)
  private final List<Nutrient> nutrients;

  private final Unit unit;

  private final List<String> imagesUrls;
}
