package wcosta.dishmaster.mappers;

import wcosta.dishmaster.dto.FoodDTO;
import wcosta.dishmaster.exceptions.BadRequestException;
import wcosta.dishmaster.model.Food;
import wcosta.dishmaster.model.Nutrient;
import wcosta.dishmaster.model.NutrientType;
import lombok.experimental.UtilityClass;

@UtilityClass
public class FoodMapper {

    public Food toFood(FoodDTO foodDTO) {
        return new Food(
                foodDTO.name(),
                foodDTO.grams(),
                foodDTO.nutrients().stream()
                        .map(n -> new Nutrient(toNutrientType(n.type()), n.percentage()))
                        .toList()
        );
    }

    private NutrientType toNutrientType(String type) {
        try {
            return NutrientType.valueOf(type);
        } catch (IllegalArgumentException e) {
            throw new BadRequestException("Invalid type provided: " + type);
        }
    }
}
