package bot.model;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
public class NestePetrolPriceModel {
    private int id;
    private String country;
    private BigDecimal petrol;
    private BigDecimal petrolPro;
    private BigDecimal diesel;
    private BigDecimal dieselPro;
    private String petrolBestPriceAddress;
    private String petrolProBestPriceAddress;
    private String dieselBestPriceAddress;
    private String dieselProBestPriceAddress;
    private String errorMessage;
}
