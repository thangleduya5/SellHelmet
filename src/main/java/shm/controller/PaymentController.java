//package shm.controller;
//
//import javax.servlet.http.HttpServletRequest;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RequestParam;
//
//import com.paypal.api.payments.Links;
//import com.paypal.api.payments.Payment;
//import com.paypal.base.rest.PayPalRESTException;
//
//import shm.paypalPayment.PaypalPaymentIntent;
//import shm.paypalPayment.PaypalPaymentMethod;
//import shm.paypalPayment.PaypalService;
//import shm.paypalPayment.Utils;
//
//@Controller
//@RequestMapping("Paypal/")
//public class PaymentController {
//	
//	public static final String URL_PAYPAL_SUCCESS = "pay/success";
//	public static final String URL_PAYPAL_CANCEL = "pay/cancel";
//	
//	private Logger log = LoggerFactory.getLogger(getClass());
//	
//	
//	private PaypalService paypalService = new PaypalService();
//	
//	
//	@RequestMapping(value = "payment")
//	public String pay(HttpServletRequest request){
//		String cancelUrl = Utils.getBaseURL(request) + "/" + URL_PAYPAL_CANCEL;
//		String successUrl = Utils.getBaseURL(request) + "/" + URL_PAYPAL_SUCCESS;
//		try {
//			Payment payment = paypalService.createPayment(
//					100D, 
//					"USD", 
//					PaypalPaymentMethod.paypal, 
//					PaypalPaymentIntent.sale,
//					"payment description", 
//					cancelUrl, 
//					successUrl);
//			for(Links links : payment.getLinks()){
//				if(links.getRel().equals("approval_url")){
//					return "redirect:" + links.getHref();
//				}
//			}
//		} catch (PayPalRESTException e) {
//			log.error(e.getMessage());
//		}
//		return "redirect:/User/home.htm";
//	}
//
//	@RequestMapping(URL_PAYPAL_CANCEL)
//	public String cancelPay(){
//		return "cancel";
//	}
//
//	@RequestMapping(URL_PAYPAL_SUCCESS)
//	public String successPay(@RequestParam("paymentId") String paymentId, @RequestParam("PayerID") String payerId){
//		try {
//			Payment payment = paypalService.executePayment(paymentId, payerId);
//			if(payment.getState().equals("approved")){
//				return "success";
//			}
//		} catch (PayPalRESTException e) {
//			log.error(e.getMessage());
//		}
//		return "redirect:/";
//	}
//	
//}
