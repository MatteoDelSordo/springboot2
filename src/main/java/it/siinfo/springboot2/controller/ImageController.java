package it.siinfo.springboot2.controller;

import it.siinfo.springboot2.dto.ImageDTO;
import it.siinfo.springboot2.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/images")
public class ImageController {

    @Autowired
    private ImageService imageService;

    @PostMapping
    public ImageDTO saveImage(@RequestBody ImageDTO imageDTO) {
        return imageService.saveImage(imageDTO);
    }

    @GetMapping
    public List<ImageDTO> getAllImages() {
        return imageService.getAllImages();
    }

    @GetMapping("/{id}")
    public ImageDTO getImageById(@PathVariable Long id) {
        return imageService.getImageById(id);
    }
}