package wcosta.dishmaster.mappers;

import wcosta.dishmaster.dto.IngredientDTO;
import wcosta.dishmaster.exceptions.BadRequestException;
import wcosta.dishmaster.model.Ingredient;
import wcosta.dishmaster.model.Nutrient;
import wcosta.dishmaster.model.NutrientType;
import lombok.experimental.UtilityClass;

@UtilityClass
public class IngredientMapper {

    public Ingredient toIngredient(IngredientDTO dto) {
        return new Ingredient(
                dto.name(),
                dto.grams(),
                dto.nutrients().stream()
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
