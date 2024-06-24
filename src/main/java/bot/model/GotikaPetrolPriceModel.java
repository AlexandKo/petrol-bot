package bot.model;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
public class GotikaPetrolPriceModel {
    private int id;
    private String country;
    private BigDecimal petrol;
    private BigDecimal diesel;
    private String petrolBestPriceAddress;
    private String dieselBestPriceAddress;
    private String errorMessage;
}
