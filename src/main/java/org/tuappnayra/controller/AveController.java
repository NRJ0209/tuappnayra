package org.tuappnayra.controller;

import org.tuappnayra.model.Ave;
import org.tuappnayra.service.AveService;
import jakarta.validation.Valid;    // Añadido
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;    // Añadido
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;    // Añadido
import java.util.List;    // Añadido
import java.util.Map;

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

    // ========== ENDPOINTS CRUD ==========

    // Obtener por ID
    @GetMapping("/{id}")
    public Ave obtenerPorId(@PathVariable Long id) {
        return service.obtenerPorId(id);
    }
    // Actualizar
    @PutMapping("/{id}")
    public Ave actualizar(@PathVariable Long id, @Valid @RequestBody Ave ave) {
        return service.actualizarAve(id, ave);
    }
    // Eliminar
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id) {
        service.eliminarAve(id);
    }

    // ========== NUEVOS ENDPOINTS DE BÚSQUEDA ==========

    // Buscar por nombre
    @GetMapping("/buscar/nombre")
    public List<Ave> buscarPorNombre(@RequestParam String nombre) {
        return service.buscarPorNombre(nombre);
    }
    // Buscar por tipo
    @GetMapping("/buscar/tipo")
    public List<Ave> buscarPorTipo(@RequestParam String tipo) {
        return service.buscarPorTipo(tipo);
    }
    // Buscar por rango de edad
    @GetMapping("/buscar/edad")
    public List<Ave> buscarPorRangoEdad(@RequestParam int min, @RequestParam int max) {
        return service.buscarPorRangoEdad(min, max);
    }

    // ========== NUEVOS ENDPOINTS DE ESTADÍSTICAS ==========

    // Contar aves
    @GetMapping("/estadisticas/contar")
    public Map<String, Long> contarAves() {
        Map<String, Long> respuesta = new HashMap<>();
        respuesta.put("total", service.contarAves());
        return respuesta;
    }
    // Edad promedio
    @GetMapping("/estadisticas/promedio-edad")
    public Map<String, Double> promedioEdad() {
        Map<String, Double> respuesta = new HashMap<>();
        respuesta.put("promedio", service.calcularEdadPromedio());
        return respuesta;
    }
    // Ave más joven
    @GetMapping("/estadisticas/mas-joven")
    public Ave aveMasJoven() {
        return service.obtenerAveMasJoven();
    }
    // Ave más vieja
    @GetMapping("/estadisticas/mas-viejo")
    public Ave aveMasVieja() {
        return service.obtenerAveMasVieja();
    }

    // ========== NUEVOS ENDPOINTS DE OPERACIONES MASIVAS ==========

    // Aumentar edad de todas las aves de un tipo
    @PutMapping("/aumentar-edad-por-tipo")
    public Map<String, Object> aumentarEdadPorTipo(@RequestParam String tipo) {
        int actualizadas = service.aumentarEdadPorTipo(tipo);
        Map<String, Object> respuesta = new HashMap<>();
        respuesta.put("mensaje", "Se aumentó la edad de " + actualizadas + " aves");
        respuesta.put("cantidad", actualizadas);
        respuesta.put("tipo", tipo);
        return respuesta;
    }
    // Eliminar aves jóvenes
    @DeleteMapping("/eliminar-jovenes")
    public Map<String, Object> eliminarAvesJovenes(@RequestParam int edadMaxima) {
        int eliminadas = service.eliminarAvesJovenes(edadMaxima);
        Map<String, Object> respuesta = new HashMap<>();
        respuesta.put("mensaje", "Se eliminaron " + eliminadas + " aves con edad menor a " + edadMaxima);
        respuesta.put("cantidad", eliminadas);
        respuesta.put("edadMaxima", edadMaxima);
        return respuesta;
    }
}