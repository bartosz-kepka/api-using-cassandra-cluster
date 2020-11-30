package pl.nbd.api.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReviewDTO {

    @NotBlank(message = "Country cannot be blank or null")
    @ApiModelProperty(notes = "The country that the wine is from", required = true)
    private String country;

    @NotBlank(message = "Province cannot be blank or null")
    @ApiModelProperty(notes = "The province or state that the wine is from", required = true)
    private String province;

    @ApiModelProperty(notes = "The wine growing region/area in a province or state")
    private String region;

    @ApiModelProperty(notes = "The vineyard within the winery where the grapes that made the wine are from")
    private String vineyard;

    @NotBlank(message = "Winery cannot be blank or null")
    @ApiModelProperty(notes = "The winery that made the wine", required = true)
    private String winery;

    @NotBlank(message = "Variety cannot be blank or null")
    @ApiModelProperty(notes = "The type of grapes used to make the wine ", required = true)
    private String variety;

    @NotNull(message = "Price cannot be null")
    @Min(value = 0, message = "Price has to be a positive number")
    @ApiModelProperty(notes = "The cost for a bottle of the wine", allowableValues = "range[0, 99999]", required = true)
    private float price;

    @NotBlank(message = "Description cannot be blank or null")
    @ApiModelProperty(notes = "A few sentences from a sommelier describing the wine's taste, smell, look, feel, etc.",
            required = true)
    private String description;

    @NotNull(message = "Points cannot be null")
    @ApiModelProperty(notes = "The number of points sommelier rated the wine on a scale of 1-100",
            allowableValues = "range[0, 100]", required = true)
    private int points;
}
