package com.china.china;

import com.mercadopago.MercadoPagoConfig;
import com.mercadopago.client.payment.PaymentClient;
import com.mercadopago.client.payment.PaymentCreateRequest;
import com.mercadopago.client.payment.PaymentPayerRequest;
import com.mercadopago.exceptions.MPApiException;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.payment.Payment;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.math.BigDecimal;

@SpringBootApplication
public class ChinaApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChinaApplication.class, args);

		MercadoPagoConfig.setAccessToken("TEST-4954468232745263-031322-29e589110c548167038c3c1c6bc1198a-193104520");

		PaymentClient client = new PaymentClient();

		PaymentCreateRequest createRequest =
				PaymentCreateRequest.builder()
						.transactionAmount(new BigDecimal(1000))
						.token("your_cardtoken")
						.description("description")
						.installments(1)
						.paymentMethodId("visa")
						.payer(PaymentPayerRequest.builder().email("nico.2011.pr@gmail.com").build())
						.build();

		try {
			Payment payment = client.create(createRequest);
			System.out.println(payment);
		} catch (MPApiException ex) {
			System.out.printf(
					"MercadoPago Error. Status: %s, Content: %s%n",
					ex.getApiResponse().getStatusCode(), ex.getApiResponse().getContent());
		} catch (MPException ex) {
			ex.printStackTrace();
		}
	}

}
