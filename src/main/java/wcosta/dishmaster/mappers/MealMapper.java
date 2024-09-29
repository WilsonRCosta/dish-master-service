package wcosta.dishmaster.mappers;

import wcosta.dishmaster.dto.MealDTO;
import wcosta.dishmaster.model.Meal;
import lombok.experimental.UtilityClass;

@UtilityClass
public class MealMapper {

    public Meal toMeal(MealDTO dto) {
        return new Meal(
                dto.name(),
                dto.ingredients().stream().map(IngredientMapper::toIngredient).toList()
        );
    }
}
