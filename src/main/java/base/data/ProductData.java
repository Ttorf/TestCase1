package base.data;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class ProductData {
    private Object name;
    private Object description;
    private Object price;
}
