package com.formatta.rc.dpapi.service;

//Imports:
import org.springframework.stereotype.Service;

@Service
public class DpService {
    //Methods:
    /** 
     * @param grossSalary
     * @param dependents
     * @return double
     */
    public double getNetSalary(double grossSalary, int dependents) {
        //Constants:
        double DEDUCTION_VALUE_PER_DEPENDENT = 189.59;
        double MAXIMUM_INSS_VALUE = 713.10;

        //Variables:
        double netSalary = grossSalary;
        double calculatingDependentsSalary;

        double inss = 0;
        double incomeTax;

        //It calculates the INSS value:
        if(grossSalary <= 1100.0){
            //1st salary range:
            inss += grossSalary * (7.5/100);
        }else if(grossSalary <= 2203.48d){
            //2nd salary band:
            inss += (1100.0 * (7.5/100));

            inss += (grossSalary - 1100.0d) * (9.0/100);
        }else if(grossSalary <= 3305.22){
            //3rd salary band:
            inss += (1100.0 * (7.5/100));
            inss += ((2203.48 - 1100.0)  * (9.0/100));

            inss += (grossSalary - 2203.48) * (12.0/100);
        }else if(grossSalary <= 6101.06){
            //4th salary band:
            inss += (1100.0 * (7.5/100));
            inss += ((2203.48 - 1100.0)  * (9.0/100));
            inss += ((3305.22  - 2203.49)  * (12.0/100));

            inss += (grossSalary - 3305.22) * (14.0/100);
        }else{
            //5th salary band:
            inss += (1100.0 * (7.5/100));
            inss += ((2203.48 - 1100.0)  * (9.0/100));
            inss += ((3305.22  - 2203.49)  * (12.0/100));
            inss += ((6433.57 - 3305.23)  * (14.0/100));

            inss += MAXIMUM_INSS_VALUE;
        }

        //It deducts INSS from gross salary:
        netSalary -= inss;
        
        //It calculates the income tax value:
        calculatingDependentsSalary = netSalary - (dependents * DEDUCTION_VALUE_PER_DEPENDENT);

        if(calculatingDependentsSalary <= 1903.98){
            //1st salary range:
            incomeTax = 0;
        }else if(calculatingDependentsSalary <= 2826.65){
            //2nd salary band:
            incomeTax = (calculatingDependentsSalary * (7.5/100)) - 142.80;
        }else if(calculatingDependentsSalary <= 3751.05){
            //3rd salary band:
            incomeTax = (calculatingDependentsSalary * (15.0/100)) - 354.80;
        }else if(calculatingDependentsSalary <= 4664.68){
            //4th salary bBand:
            incomeTax = (calculatingDependentsSalary * (22.5/100)) - 636.13;
        }else{
            //5th salary band:
            incomeTax = (calculatingDependentsSalary * (27.5/100)) - 869.36;
        }

        //It deducts INSS from gross salary:
        incomeTax = (incomeTax < 0)? 0 : incomeTax;
        netSalary -= incomeTax;

        return netSalary;
    }
}