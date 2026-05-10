package org.tuappnayra.service;

import org.tuappnayra.model.Ave;
import org.tuappnayra.repository.AveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional; //Añadido
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

        // ========== NUEVOS MÉTODOS CRUD ==========

        // Buscar por ID
        public Ave obtenerPorId (Long id){
            return repository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Ave no encontrada con ID: " + id));
        }
        // Actualizar ave
        public Ave actualizarAve (Long id, Ave aveActualizada){
            Ave aveExistente = obtenerPorId(id);
            aveExistente.setNombre(aveActualizada.getNombre());
            aveExistente.setTipo(aveActualizada.getTipo());
            aveExistente.setEdad(aveActualizada.getEdad());
            return repository.save(aveExistente);
        }
        // Eliminar ave
        public void eliminarAve (Long id){
            Ave aveExistente = obtenerPorId(id);
            repository.delete(aveExistente);
        }

        // ========== NUEVOS MÉTODOS DE BÚSQUEDA ==========

        // Buscar por nombre
        public List<Ave> buscarPorNombre (String nombre){
            return repository.findByNombreContainingIgnoreCase(nombre);
        }
        // Buscar por tipo
        public List<Ave> buscarPorTipo (String tipo){
            return repository.findByTipo(tipo);
        }
        // Buscar por rango de edad
        public List<Ave> buscarPorRangoEdad ( int edadMin, int edadMax){
            return repository.findByEdadBetween(edadMin, edadMax);
        }

        // ========== NUEVOS MÉTODOS DE ESTADÍSTICAS ==========

        // Contar aves
        public long contarAves () {
            return repository.count();
        }
        // Edad promedio
        public double calcularEdadPromedio () {
            return repository.calcularEdadPromedio().orElse(0.0);
        }
        // Ave más joven
        public Ave obtenerAveMasJoven () {
            return repository.encontrarMasJoven()
                    .orElseThrow(() -> new RuntimeException("No hay aves registradas"));
        }
        // Ave más vieja
        public Ave obtenerAveMasVieja () {
            return repository.encontrarMasViejo()
                    .orElseThrow(() -> new RuntimeException("No hay aves registradas"));
        }

        // ========== NUEVOS MÉTODOS DE OPERACIONES MASIVAS ==========

        // Aumentar edad de todas las aves de un tipo
        @Transactional
        public int aumentarEdadPorTipo (String tipo){
            List<Ave> aves = repository.findByTipo(tipo);
            for (Ave ave : aves) {
                ave.setEdad(ave.getEdad() + 1);
            }
            repository.saveAll(aves);
            return aves.size();
        }
        // Eliminar aves con edad menor a X
        @Transactional
        public int eliminarAvesJovenes ( int edadMaxima){
            List<Ave> aves = repository.findByEdadLessThan(edadMaxima);
            int cantidad = aves.size();
            repository.deleteAll(aves);
            return cantidad;
    }
}