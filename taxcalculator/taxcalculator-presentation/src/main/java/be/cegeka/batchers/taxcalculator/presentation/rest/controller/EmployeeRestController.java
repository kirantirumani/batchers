package be.cegeka.batchers.taxcalculator.presentation.rest.controller;

import be.cegeka.batchers.taxcalculator.application.domain.*;
import be.cegeka.batchers.taxcalculator.presentation.rest.model.EmployeeTaxTo;
import be.cegeka.batchers.taxcalculator.to.EmployeeTo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.Comparator.comparing;

@Controller
@RequestMapping(value = "/employees")
public class EmployeeRestController {
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private EmployeeMapper employeeMapper;
    @Autowired
    private MonthlyTaxForEmployeeRepository monthlyTaxForEmployeeRepository;
    private Function<EmployeeTaxTo, Integer> onMonth = employeeTaxTo -> employeeTaxTo.getMonth();

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public List<EmployeeTo> getEmployees(@RequestParam int page, @RequestParam int pageSize) {
        return employeeService.getEmployees(page, pageSize);
    }

    @RequestMapping(value = "/count", method = RequestMethod.GET)
    @ResponseBody
    public long getEmployeeCount() {
        return employeeService.getEmployeeCount();
    }

    @RequestMapping(value = "/{employeeId}/details", method = RequestMethod.GET)
    @ResponseBody
    public EmployeeTo getEmployeeDetails(@PathVariable(value = "employeeId") Long employeeId) {
        return employeeMapper.toTo(employeeService.getEmployee(employeeId));
    }

    @RequestMapping(value = "/{employeeId}/taxes", method = RequestMethod.GET)
    @ResponseBody
    public List<EmployeeTaxTo> getEmployeeTaxes(@PathVariable(value = "employeeId") Long employeeId) {
        Employee employee = employeeService.getEmployee(employeeId);
        List<TaxCalculation> employeeTaxes = employeeService.getEmployeeTaxes(employeeId);
        List<EmployeeTaxTo> employeeTaxTos = employeeTaxes
                .stream()
                .map(mapTaxCalculationToEmployeeTaxTo(employee))
                .sorted(comparing(onMonth).reversed())
                .collect(Collectors.toList());

        return employeeTaxTos;
    }

    private Function<TaxCalculation, EmployeeTaxTo> mapTaxCalculationToEmployeeTaxTo(Employee employee) {
        return taxCalculation -> {
            MonthlyTaxForEmployee monthlyTaxForEmployee = monthlyTaxForEmployeeRepository.find(employee, taxCalculation.getYear(), taxCalculation.getMonth());
            String status = monthlyTaxForEmployee == null ? "IN PROGRESS" : monthlyTaxForEmployee.getMonthlyReportPdf() == null ? "FAILURE" : "SUCCESS";
            return new EmployeeTaxTo(taxCalculation.getYear(), taxCalculation.getMonth(), taxCalculation.getTax(), taxCalculation.getCalculationDate(), status);
        };
    }
}
