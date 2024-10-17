package reservation.mapper;

import org.springframework.core.convert.converter.Converter;  // Importa la interfaz Converter de Spring
import org.mapstruct.Mapper;  // Importa la anotación Mapper de MapStruct
import reservation.dto.ReservationDTO;
import reservation.model.Reservation;

@Mapper(componentModel = "spring")  // Indica que este mapper es un componente de Spring
public interface ReservationMapper extends Converter<Reservation, ReservationDTO> {

    @Override
    ReservationDTO convert(Reservation source);  // Método que convierte Reservation a ReservationDTO

}
