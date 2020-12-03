package pl.nbd.api.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.math.BigInteger;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table
public class Review {

    @PrimaryKey
    @ApiModelProperty(notes = "Generated review ID", required = true)
    private String id;

    @ApiModelProperty(notes = "The country that the wine is from",
            example = "Spain")
    private String country;

    @ApiModelProperty(notes = "The province or state that the wine is from",
            example = "Northern Spain")
    private String province;

    @ApiModelProperty(notes = "The wine growing region/area in a province or state",
            example = "Toro")
    private String region;

    @ApiModelProperty(notes = "The vineyard within the winery where the grapes that made the wine are from",
            example = "Carodorum Selección Especial Reserva")
    private String vineyard;

    @NotBlank(message = "Winery is required and cannot be blank")
    @ApiModelProperty(notes = "The winery that made the wine",
            required = true, example = "Bodega Carmen Rodríguez")
    private String winery;

    @NotBlank(message = "Variety is required and cannot be blank")
    @ApiModelProperty(notes = "The type of grapes used to make the wine ",
            required = true, example = "Tinta de Toro")
    private String variety;

    @DecimalMin(value = "0.01", message = "Price must be grater or equal to 0.01")
    @DecimalMax(value = "99999.99", message = "Price must be lower or equal to 99999.99")
    @Digits(integer = 5, fraction = 2,
            message = "Price cannot have more than 2 fractional and 5 integral digits")
    @ApiModelProperty(notes = "The cost for a bottle of the wine",
            example = "49.99")
    private BigDecimal price;

    @NotBlank(message = "Description is required and cannot be blank")
    @ApiModelProperty(notes = "A few sentences from a sommelier describing the wine's taste, smell, look, feel, etc.",
            required = true, example = "Ripe aromas of fig, blackberry and cassis are softened and sweetened by a slathering of oaky chocola...")
    private String description;

    @NotNull(message = "Points are required")
    @Min(value = 1, message = "Points must be greater or equal to 1")
    @Max(value = 100, message = "Points must be lower or equal to 100")
    @ApiModelProperty(notes = "The number of points sommelier rated the wine on a scale of 1-100",
            allowableValues = "range[1, 100]", required = true)
    private BigInteger points;
}
