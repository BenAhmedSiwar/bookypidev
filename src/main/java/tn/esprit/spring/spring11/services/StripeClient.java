package tn.esprit.spring.spring11.services;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import tn.esprit.spring.spring11.interfaces.IPaymentServive;

import java.util.HashMap;
import java.util.Map;

@Component
public class StripeClient {

    @Value("${stripe.apikey}")
    String stripeKey;

//        @Autowired
//        StripeClient()  {
//            Stripe.apiKey = "sk_test_51N4qUiKHlpLaUTbB5oUF1btKAd1FS9WRtWkMNYfBnRLvnjcDuDj28eko6wZNCfXImjLarCf1LWeeflrzim5seJan00AUWQ1BkT";
//        }
//
//        public Charge chargeCreditCard(String token, double amount) throws Exception {
//            Map<String, Object> chargeParams = new HashMap<>();
//            chargeParams.put("currency", "USD");
//            chargeParams.put("source", token);
//            Charge charge = Charge.create(chargeParams);
//            return charge;
//        }

    public void AddStripeCommand()
    {
        Stripe.apiKey= stripeKey;

        // charge creation
        Map<String, Object> params = new HashMap<>();
        // get price of the chosen course/subscription/voucher

        params.put("amount",7000);
        params.put("currency", "usd");
        params.put("customer", "cus_Nrh74qhhVlgwuD");
        params.put("description","hedi commande"); // to change later (type + price or smth)
        Charge charge = null;
        try {
            charge = Charge.create(params);
        } catch (StripeException e) {
            throw new RuntimeException(e);
        }
        System.out.println(charge);
    }
    }

