package spring.edu.ms.app.vehicleregistrationapp.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class MessageDto {
private String message;
private LocalDateTime currentTime;
}
