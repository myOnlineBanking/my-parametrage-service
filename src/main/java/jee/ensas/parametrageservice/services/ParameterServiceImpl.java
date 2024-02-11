package jee.ensas.parametrageservice.services;

import jee.ensas.parametrageservice.daos.ETransferType;
import jee.ensas.parametrageservice.daos.Parameter;
import jee.ensas.parametrageservice.repositories.ParameterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

@Service
public class ParameterServiceImpl implements ParameterService{

    @Autowired
    ParameterRepository parameterRepository;

    @Override
    public Parameter getParameters(HttpServletRequest request){
        return parameterRepository.findAll().get(0);
    }

    @Override
    public Parameter updateParameters(Parameter parameter, HttpServletRequest request){
        return parameterRepository.save(parameter);
    }

    @Override
    public Map<String, String> calculateCost(Map<String, String> transferInfo, HttpServletRequest request) {
        Parameter parameter = getParameters(request);

        double transferAmount = Double.parseDouble(transferInfo.get("transferAmount"));

        switch (transferInfo.get("transferType")){
            case ETransferType
                    .ACCOUNT_TO_ACCOUNT:{
                transferInfo.put("operationFees" , String.valueOf( transferAmount* (parameter.getTransferPercentageForAccountToAccount()/100)));
                transferInfo.put("holdingTill" , String.valueOf( addHoursToDate(parameter.getMaxHoldingTimeForAccountToAccount())));
                transferInfo.put("validTransfer", transferAmount > parameter.getMaxAmountForAccountToAccount()?"false":"true");
                return transferInfo;
            }
            case ETransferType
                    .ACCOUNT_TO_CASH:{
                transferInfo.put("operationFees" , String.valueOf( transferAmount* (parameter.getTransferPercentageForAccountToCash()/100)));
                transferInfo.put("holdingTill" , String.valueOf( addHoursToDate(parameter.getMaxHoldingTimeForAccountToCash())));
                transferInfo.put("validTransfer", transferAmount > parameter.getMaxAmountForAccountToCash()?"false":"true");
                return transferInfo;
            }
            case ETransferType
                    .CASH_TO_CASH:{
                transferInfo.put("operationFees" , String.valueOf( transferAmount* (parameter.getTransferPercentageForCashToCash()/100)));
                transferInfo.put("holdingTill" , String.valueOf( addHoursToDate(parameter.getMaxHoldingTimeForCashToCash())));
                transferInfo.put("validTransfer", transferAmount > parameter.getMaxAmountForCashToCash()?"false":"true");
                return transferInfo;

            }
            case ETransferType
                    .CASH_TO_ACCOUNT:{

                transferInfo.put("operationFees" , String.valueOf( transferAmount* (parameter.getTransferPercentageForCashToAccount()/100)));
                transferInfo.put("holdingTill" , String.valueOf( addHoursToDate(parameter.getMaxHoldingTimeForCashToAccount())));
                transferInfo.put("validTransfer", transferAmount > parameter.getMaxAmountForCashToAccount()?"false":"true");

                return transferInfo;
            }
        }
        return transferInfo;
    }

    public Date addHoursToDate( int hours) {
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.HOUR_OF_DAY, hours);
        return calendar.getTime();
    }
}
