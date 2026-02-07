package restaurantVote.error;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ErrorRestaurant {
    String field;
    String message;
}
