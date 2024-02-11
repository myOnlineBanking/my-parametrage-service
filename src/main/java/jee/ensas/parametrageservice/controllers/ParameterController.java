package jee.ensas.parametrageservice.controllers;

import jee.ensas.parametrageservice.daos.Parameter;
import jee.ensas.parametrageservice.services.ParameterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@RequestMapping("/Parameter")
@CrossOrigin(origins = "*")
public class ParameterController {

    @Autowired
    ParameterService parameterService;

    @CrossOrigin(origins = "*")
    @GetMapping("/get")
    public Parameter getCard (final HttpServletRequest request){
        return parameterService.getParameters(request);
    }




    @CrossOrigin(origins = "*")
    @PutMapping("/update")
    public Parameter updateParameters (@RequestBody Parameter parameter, final HttpServletRequest request){
        return parameterService.updateParameters(parameter, request);
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/calculateCost")
    public Map<String,String> calculateCost(@RequestBody Map<String,String> transferInfo, final HttpServletRequest request){
        return parameterService.calculateCost(transferInfo, request);
    }



}
