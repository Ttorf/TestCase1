package base.data.ErrorData;

import lombok.Data;

@Data
public class ErrorData {
    private String code;
    private String reason;
    private ErrorsDada[] errors;
}
