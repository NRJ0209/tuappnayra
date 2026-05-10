package org.tuappnayra.service;

import org.tuappnayra.model.Ave;
import org.tuappnayra.repository.AveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AveService {

    @Autowired
    private AveRepository repository;

    // Crear un ave en base de datos
    public Ave guardarAve(Ave ave) {
        return repository.save(ave);
    }

    // Obtener todas las aves de la base de datos
    public List<Ave> obtenerTodas() {
        return repository.findAll();
    }
}