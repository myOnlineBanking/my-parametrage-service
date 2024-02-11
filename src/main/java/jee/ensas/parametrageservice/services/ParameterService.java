package jee.ensas.parametrageservice.services;

import jee.ensas.parametrageservice.daos.Parameter;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public interface ParameterService{

    Parameter getParameters(HttpServletRequest request);

    Parameter updateParameters(Parameter parameter, HttpServletRequest request);

    Map<String, String> calculateCost(Map<String, String> transferInfo, HttpServletRequest request);
}
