package wcosta.dishmaster.dto;

import java.util.List;
import wcosta.dishmaster.model.Unit;

public record IngredientDTO(
    String name, int serving, Unit unit, List<String> imagesUrls, List<NutrientDTO> nutrients) {}
