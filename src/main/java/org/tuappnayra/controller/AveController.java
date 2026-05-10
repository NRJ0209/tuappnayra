package org.tuappnayra.controller;

import org.tuappnayra.model.Ave;
import org.tuappnayra.service.AveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/aves")
public class AveController {

    @Autowired
    private AveService service;

    // Endpoint para guardar (POST: http://localhost:8080/api/aves)

    @PostMapping
    public Ave crear(@RequestBody Ave ave) {
        return service.guardarAve(ave);
    }

    // Endpoint para listar (GET: http://localhost:8080/api/aves)

    @GetMapping
    public List<Ave> listarTodos() {
        return service.obtenerTodas();
    }
}