package org.tuappnayra.repository;
import org.tuappnayra.model.Ave;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface AveRepository extends JpaRepository<Ave, Long> {
    // Buscar por nombre que contenga (ignorando mayúsculas)
    List<Ave> findByNombreContainingIgnoreCase(String nombre);

    // Buscar por tipo exacto
    List<Ave> findByTipo(String tipo);

    // Buscar por rango de edad
    List<Ave> findByEdadBetween(int edadMin, int edadMax);

    // Contar total de aves (lo hereda de JpaRepository pero lo usamos explícitamente)

    // Calcular edad promedio
    @Query("SELECT AVG(a.edad) FROM Ave a")
    Optional<Double> calcularEdadPromedio();

    // Obtener ave más joven
    @Query("SELECT a FROM Ave a ORDER BY a.edad ASC LIMIT 1")
    Optional<Ave> encontrarMasJoven();

    // Obtener ave más vieja
    @Query("SELECT a FROM Ave a ORDER BY a.edad DESC LIMIT 1")
    Optional<Ave> encontrarMasViejo();

    // Buscar aves por edad menor a (para eliminar jóvenes)
    List<Ave> findByEdadLessThan(int edad);
}