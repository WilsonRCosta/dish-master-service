package wcosta.dishmaster.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
@Document(indexName = "dish")
public class Dish {

    @Id
    private String id;

    private final String name;

    @Field(type = FieldType.Nested)
    private final List<Food> food;
}
