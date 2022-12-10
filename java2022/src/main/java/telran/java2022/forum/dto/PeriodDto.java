package telran.java2022.forum.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder
@NoArgsConstructor
@Getter
public class PeriodDto {
  LocalDate dateFrom;
  LocalDate dateTo;
}
