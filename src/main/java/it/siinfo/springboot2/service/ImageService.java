package it.siinfo.springboot2.service;

import it.siinfo.springboot2.dto.ImageDTO;
import it.siinfo.springboot2.entity.ImageEntity;
import it.siinfo.springboot2.mapper.ImageEntityMapper;
import it.siinfo.springboot2.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ImageService {

    @Autowired
    private ImageRepository imageRepository;

    @Autowired
    private ImageEntityMapper imageEntityMapper;

    // Salva un'immagine
    public ImageDTO saveImage(ImageDTO imageDTO) {
        ImageEntity entity = imageEntityMapper.toEntity(imageDTO);
        ImageEntity savedEntity = imageRepository.save(entity);
        return imageEntityMapper.toDTO(savedEntity);
    }

    // Recupera tutte le immagini
    public List<ImageDTO> getAllImages() {
        return imageRepository.findAll()
                .stream()
                .map(imageEntityMapper::toDTO)
                .collect(Collectors.toList());
    }

    // Recupera un'immagine per ID
    public ImageDTO getImageById(Long id) {
        return imageRepository.findById(id)
                .map(imageEntityMapper::toDTO)
                .orElseThrow(() -> new RuntimeException("Image not found"));
    }
}