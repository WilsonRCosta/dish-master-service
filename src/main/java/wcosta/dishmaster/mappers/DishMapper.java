package wcosta.dishmaster.mappers;

import wcosta.dishmaster.dto.DishDTO;
import wcosta.dishmaster.model.Dish;
import lombok.experimental.UtilityClass;

@UtilityClass
public class DishMapper {

    public Dish toDish(DishDTO dishDTO) {
        return new Dish(
                dishDTO.name(),
                dishDTO.food().stream().map(FoodMapper::toFood).toList()
        );
    }
}
