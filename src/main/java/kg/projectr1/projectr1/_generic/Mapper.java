package kg.projectr1.projectr1._generic;

public interface Mapper <Entity, ResponseDto, SaveDto, UpdateDto>{

    Entity fromSaveDto(SaveDto saveDto);

    Entity fromUpdateDto(UpdateDto updateDto);

    ResponseDto toResponseDto(Entity entity);
}
