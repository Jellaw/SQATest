package vn.compedia.website.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Calculation {

    private String period;
    private Date paymentDate;
    private double remain;
    private double moneyPerMonth;
    private double profitPerMonth;
    private double amountPerMonth;



}
