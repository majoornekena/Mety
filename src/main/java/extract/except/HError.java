package extract.except;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class HError {
    int code;
    String message;
}
