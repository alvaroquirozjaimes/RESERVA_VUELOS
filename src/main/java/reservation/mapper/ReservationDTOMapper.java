package reservation.mapper;

import org.mapstruct.Mapper;  // Importa la anotación Mapper de MapStruct
import org.springframework.core.convert.converter.Converter;  // Importa la interfaz Converter de Spring
import reservation.dto.ReservationDTO;
import reservation.model.Reservation;

@Mapper(componentModel = "spring")  // Indica que este mapper es un componente de Spring
public interface ReservationDTOMapper extends Converter<ReservationDTO, Reservation> {

    @Override
    Reservation convert(ReservationDTO source);  // Método que convierte ReservationDTO a Reservation
}
