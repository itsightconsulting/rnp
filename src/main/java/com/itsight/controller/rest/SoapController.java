package com.itsight.controller.rest;

import com.itsight.ws.BLZServiceStub;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletContext;

@RestController
@RequestMapping("/soap")
public class SoapController {

    @Autowired
    private ServletContext context;

    @GetMapping("/bank/{codeBank}")//38070059|21530080|more... https://blz.bankingdb.com/search?bank_name=A&swift_code=&blz_code=&search=Search
    public BLZServiceStub.DetailsType obtenerBancoAleman(@PathVariable(value = "codeBank") String codeBank){
        try {
            BLZServiceStub blz = new BLZServiceStub();
            //SoapSecurityHeader.add(blz._getServiceClient(), context.getAttribute("WS_CLARO_USERNAME").toString(), context.getAttribute("WS_CLARO_PASSWORD").toString(), context.getAttribute("WS_CLARO_SEC_TOKEN").toString());

            BLZServiceStub.GetBank bank = new BLZServiceStub.GetBank();
            BLZServiceStub.GetBankType gbt = new BLZServiceStub.GetBankType();
            gbt.setBlz(codeBank);
            bank.setGetBank(gbt);
            return blz.getBank(bank).getGetBankResponse().getDetails();
        }catch (Exception e){
            e.printStackTrace();
        }
        return new BLZServiceStub.DetailsType();
    }

    /*@GetMapping("/account/create")
    public AccountServiceStub.SuccessfulResponse obtenerSoap(){
        try {

            //Instanciando la clase Stub del servicio SOAP
            AccountServiceStub accountService = new AccountServiceStub();
            SoapSecurityHeader.add(accountService._getServiceClient());

            AccountServiceStub.AcquireMobileNumberE acquireMobileNumber = new AccountServiceStub.AcquireMobileNumberE();
            AccountServiceStub.AcquireMobileNumber mobileNumber = new AccountServiceStub.AcquireMobileNumber();

            //CRMRequestHeader
            AccountServiceStub.CRMRequestHeader crmRequestHeader = new AccountServiceStub.CRMRequestHeader();
            crmRequestHeader.setUsername("rkadm");
            crmRequestHeader.setPassword("rkadm");

            //Setting headers request
            mobileNumber.setHeader(crmRequestHeader);

            //Preparing dto
            acquireMobileNumber.setAcquireMobileNumber(mobileNumber);

            return accountService.acquireMobileNumber(acquireMobileNumber).getSuccessfulResponse();
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }*/

}
