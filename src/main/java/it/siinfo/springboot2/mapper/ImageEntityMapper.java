package it.siinfo.springboot2.mapper;

import org.mapstruct.Mapper;

import it.siinfo.springboot2.dto.ImageDTO;
import it.siinfo.springboot2.entity.ImageEntity;

@Mapper(componentModel = "spring")
public class ImageEntityMapper {
    // Conversione da Entità a DTO
public ImageDTO toDTO(ImageEntity entity) {
    ImageDTO dto = new ImageDTO();
    dto.setId(entity.getId());
    dto.setName(entity.getName());
    dto.setBase64Image(entity.getBase64Image());
    return dto;
}

// Conversione da DTO a Entità
public ImageEntity toEntity(ImageDTO dto) {
    ImageEntity entity = new ImageEntity();
    entity.setId(dto.getId());
    entity.setName(dto.getName());
    entity.setBase64Image(dto.getBase64Image());
    return entity;
}
    
}
