package wcosta.dishmaster.dto;

import wcosta.dishmaster.model.Unit;

import java.util.List;

public record IngredientDTO(
        String name,
        int serving,
        Unit unit,
        List<String> imagesUrls,
        List<NutrientDTO> nutrients
) { }
