package wcosta.dishmaster.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Nutrient {

    private NutrientType type;

    private int percentage;
}
