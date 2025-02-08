package wcosta.dishmaster.dto;

import java.util.List;
import wcosta.dishmaster.model.Category;
import wcosta.dishmaster.model.Cuisine;

public record MealDTO(
    String name,
    List<IngredientDTO> ingredients,
    int preparationTime,
    int cookTime,
    int totalTime,
    int reviews,
    float rating,
    String description,
    List<String> imagesUrls,
    List<StepDTO> steps,
    List<Category> categories,
    Cuisine cuisine) {}
