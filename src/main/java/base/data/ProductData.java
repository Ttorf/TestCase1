package base.data;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class ProductData {
    private Object name;
    private Object description;
    private Object price;
    private Integer id;

    public static ProductData generationData(Object name, Object description, Object price) {
        return new ProductData()
                .setName(name)
                .setDescription(description)
                .setPrice(price);
    }
}
