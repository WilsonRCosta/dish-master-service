package wcosta.dishmaster.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import wcosta.dishmaster.dto.IngredientDTO;
import wcosta.dishmaster.dto.MealDTO;
import wcosta.dishmaster.dto.NutrientDTO;
import wcosta.dishmaster.dto.StepDTO;
import wcosta.dishmaster.model.*;

import java.util.List;

import static org.mapstruct.MappingConstants.ComponentModel;

@Mapper(componentModel = ComponentModel.SPRING)
public interface DishMasterMapper {

    @Mapping(target = "id", ignore = true)
    Meal toEntity(MealDTO dto);

    @Mapping(target = "id", ignore = true)
    Ingredient toEntity(IngredientDTO dto);

    Step toEntity(StepDTO dto);

    Nutrient toEntity(NutrientDTO dto);

    List<Ingredient> toIngredientEntityList(List<IngredientDTO> dtos);

    List<Step> toStepEntityList(List<StepDTO> dtos);
}
