//package shm.paypalPayment;
//
//import java.util.HashMap;
//import java.util.Map;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import com.paypal.base.rest.APIContext;
//import com.paypal.base.rest.OAuthTokenCredential;
//import com.paypal.base.rest.PayPalRESTException;
//
//
//
//
//public class PaypalConfig {
//
//	@Value("AapC_ijQ9g_QTHoGObndq2X1BM1a7W3hMCmfOwT1e9iDpLaw4Dw_KfmhOROzaBQ3ZgC146kK-Ng49zS2")
//    private String clientId;
//	@Value("EAevRX_LMpRYJdiWNrtEpySKyyUnZHXICXGL63scRv5VWDZMitsDVAbGZCmYV_rqAe9UGxI1MYso2eHH")
//    private String clientSecret;
//	@Value("sandbox")
//    private String mode;
//    
//	
//	public Map<String, String> paypalSdkConfig(){
//		Map<String, String> sdkConfig = new HashMap<>();
//		sdkConfig.put("mode", mode);
//		return sdkConfig;
//	}
//	
//	
//	public OAuthTokenCredential authTokenCredential(){
//		return new OAuthTokenCredential(clientId, clientSecret, paypalSdkConfig());
//	}
//	
//	
//	public APIContext apiContext(){
//		APIContext apiContext;
//		try {
//			apiContext = new APIContext(authTokenCredential().getAccessToken());
//			apiContext.setConfigurationMap(paypalSdkConfig());
//			return apiContext;
//		} catch (PayPalRESTException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return null;
//	}
//}
