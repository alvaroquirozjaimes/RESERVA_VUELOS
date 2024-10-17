package reservation.mapper;

import org.mapstruct.Mapper; // Importa la anotación Mapper de MapStruct
import org.springframework.core.convert.converter.Converter; // Importa Converter para realizar conversiones
import reservation.dto.ReservationDTO;
import reservation.model.Reservation;

import java.util.List; // Importa List para manejar colecciones

@Mapper(componentModel = "spring") // Indica que este mapper será un componente de Spring
public interface ReservationsMapper extends Converter<List<Reservation>, List<ReservationDTO>> { // Interfaz para el mapeo de listas

    @Override
    List<ReservationDTO> convert(List<Reservation> source); // Método para convertir una lista de Reservation a ReservationDTO

}
