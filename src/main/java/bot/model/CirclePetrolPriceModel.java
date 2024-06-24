package bot.model;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@Builder
@Getter
public class CirclePetrolPriceModel {
    private int id;
    private String country;
    private BigDecimal petrol;
    private BigDecimal petrolPro;
    private BigDecimal diesel;
    private BigDecimal dieselPro;
    private BigDecimal gas;
    private String petrolBestPriceAddress;
    private String petrolProBestPriceAddress;
    private String dieselBestPriceAddress;
    private String dieselProBestPriceAddress;
    private String gasBestPriceAddress;
    private String errorMessage;
    private BigDecimal petrolAutomatic;
    private BigDecimal petrolProAutomatic;
    private BigDecimal dieselAutomatic;
}
