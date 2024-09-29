package wcosta.dishmaster.dto;

public record FoodDTO(String name, int grams, java.util.List<NutrientDTO> nutrients) { }
