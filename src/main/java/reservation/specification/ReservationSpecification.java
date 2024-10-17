package reservation.specification;
import jakarta.persistence.criteria.Predicate;  // Importa la clase Predicate para condiciones en consultas
import org.springframework.data.jpa.domain.Specification;  // Importa la interfaz Specification para construir consultas
import reservation.dto.SearchReservationCriteriaDTO;
import reservation.model.Reservation;

import java.util.ArrayList;  // Importa la clase ArrayList para manejar listas
import java.util.List;  // Importa la clase List para trabajar con listas

public class ReservationSpecification {

    // Método estático que crea una Specification para filtrar reservas según criterios de búsqueda
    public static Specification<Reservation> withSearchCriteria(SearchReservationCriteriaDTO criteria) {
        return (root, query, criteriaBuilder) -> {
            // Lista para almacenar los predicados (condiciones) de la consulta
            List<Predicate> predicates = new ArrayList<>();

            // Si se proporciona el id del itinerario, agrega una condición a la lista de predicados
            if (criteria.getItineraryId() != null) {
                predicates.add(criteriaBuilder.equal(root.get("itinerary").get("id"), criteria.getItineraryId()));
            }

            // Si se proporciona el nombre del pasajero, agrega una condición a la lista de predicados
            if (criteria.getFirstName() != null) {
                predicates.add(criteriaBuilder.equal(root.join("passengers").get("firstName"), criteria.getFirstName()));
            }

            // Si se proporciona el apellido del pasajero, agrega una condición a la lista de predicados
            if (criteria.getLastName() != null) {
                predicates.add(criteriaBuilder.equal(root.join("passengers").get("lastName"), criteria.getLastName()));
            }

            // Si se proporciona la fecha de la reserva, agrega una condición a la lista de predicados
            if (criteria.getReservationDate() != null) {
                predicates.add(criteriaBuilder.equal(root.get("creationDate"), criteria.getReservationDate()));
            }

            // Ordenamiento de la consulta según el criterio proporcionado
            if (criteria.getSortingDirection() != null && criteria.getSortField() != null) {
                // Verifica la dirección de ordenamiento (ascendente o descendente)
                if(criteria.getSortingDirection().equals("desc")) {
                    query.orderBy(criteriaBuilder.desc(root.get(criteria.getSortField())));
                } else {
                    query.orderBy(criteriaBuilder.asc(root.get(criteria.getSortField())));  // Cambiado a ascendente
                }
            }

            // Combina todos los predicados en una sola condición con "AND"
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
