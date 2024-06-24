package bot.model;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
public class ViadaPetrolPriceModel {
    private int id;
    private String country;
    private BigDecimal petrolEcto;
    private BigDecimal petrolEctoPlus;
    private BigDecimal petrolPro;
    private BigDecimal petrolEco;
    private BigDecimal diesel;
    private BigDecimal dieselEcto;
    private BigDecimal gas;
    private String petrolEctoBestPriceAddress;
    private String petrolEctoPlusBestPriceAddress;
    private String petrolProBestPriceAddress;
    private String petrolEcoBestPriceAddress;
    private String dieselBestPriceAddress;
    private String dieselEctoBestPriceAddress;
    private String gasBestPriceAddress;
    private String errorMessage;
}
