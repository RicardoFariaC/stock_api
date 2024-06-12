package com.stockapp.mainapi.controllers;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@RestController
public class UtilController {
    @GetMapping(value = "/uploads/{dir}/{img}", produces = MediaType.IMAGE_JPEG_VALUE)
    public @ResponseBody byte[] getImage(@PathVariable int dir, @PathVariable String img) throws IOException {
        return Files.readAllBytes(Paths.get("/home/ricardo/Desktop/projeto_engenharia/stock_api_java/mainApi/uploads/" + dir + "/" + img));
    }
}
