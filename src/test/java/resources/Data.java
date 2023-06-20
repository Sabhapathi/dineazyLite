package resources;

import pojo.OTPPayload;
import pojo.userCreds;
import pojo.verifyOTP;

public class Data {
    public userCreds LoginCredentials(String username, String password){
        userCreds creds = new userCreds();
        creds.setEmail(username);
        creds.setPassword(password);
        return(creds);
    }

    public OTPPayload OTPDetails(String firstname, String lastname, String email, String phonenumber, String password){
        OTPPayload otp = new OTPPayload();
        otp.setFirstName(firstname);
        otp.setLastName(lastname);
        otp.setEmail(email);
        otp.setPhone(phonenumber);
        otp.setPassword(password);
        return(otp);
    }
    public verifyOTP verifyOTP( String email, String phonenumber, String otp){
        verifyOTP verifyotp= new verifyOTP();
        verifyotp.setEmail(email);
        verifyotp.setPhone(phonenumber);
        verifyotp.setOtp(otp);
        return(verifyotp);
    }
}
