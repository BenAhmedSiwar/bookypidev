package tn.esprit.spring.spring11.controllers;

import com.stripe.model.Charge;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import tn.esprit.spring.spring11.services.StripeClient;

import javax.servlet.http.HttpServletRequest;
@CrossOrigin("*")
@RequestMapping("/payment")
public class PaymentController {

//        private StripeClient stripeClient;
//
//        @Autowired
//        PaymentController(StripeClient stripeClient) {
//            this.stripeClient = stripeClient;
//        }
//
//        @PostMapping("/charge")
//        public Charge chargeCard(HttpServletRequest request) throws Exception {
//            String token = request.getHeader("token");
//            Double amount = Double.parseDouble(request.getHeader("amount"));
//            return this.stripeClient.chargeCreditCard(token, amount);
//        }
    }

