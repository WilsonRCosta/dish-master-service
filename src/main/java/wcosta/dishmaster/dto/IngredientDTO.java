package wcosta.dishmaster.dto;

public record IngredientDTO(String name, int grams, java.util.List<NutrientDTO> nutrients) { }
