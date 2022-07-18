package restaurantVote.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Sort;

@Getter
@Setter
public class SortDto {
    private Sort.Order order;
    private String fieldName;
}
