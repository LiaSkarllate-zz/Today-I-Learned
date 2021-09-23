package com.formatta.rc.dpapi.service;

//Imports:
import org.springframework.stereotype.Service;

@Service
public class DpService {
    //Methods:
    /** 
     * Given a gross salary and a number of dependents, it determines and
     * returns the corresponding net salary, which includes both the INSS and 
     * income tax deductions, based on the following references.
     * 
     * https://www.todacarreira.com/calculo-salario-liquido/
     * https://www.contabilizei.com.br/contabilidade-online/tabela-inss/
     * 
     * @param grossSalary A gross salary.
     * @param numberOfDependents A number of dependents.
     * @return double The corresponding net salary.
     */
    public double getNetSalary(double grossSalary, int numberOfDependents) {
        //Variables:
        double netSalary = grossSalary;

        //It deducts INSS from gross salary:
        netSalary -= getInssValue(grossSalary);
        
        //It deducts INSS from gross salary:
        netSalary -= getIncomeTaxValue(netSalary, numberOfDependents);

        return netSalary;
    }
    
    /** 
     * Given a gross salary, it determines and returns the corresponding
     * the inss deduction value, based on the following references.
     * 
     * https://www.todacarreira.com/calculo-salario-liquido/
     * https://www.contabilizei.com.br/contabilidade-online/tabela-inss/
     * 
     * @param grossSalary A gross salary.
     * @return double The inss deduction value
     */
    public double getInssValue(double grossSalary) {
        //Constants:
        double MAXIMUM_INCREASE_INSS_VALUE = 713.10;

        //Variables:
        double inssValue = 0;

        //It calculates the INSS value:
        if(grossSalary <= 1100.0){
            //1st salary range:
            inssValue += grossSalary * (7.5/100);
        }else{
            inssValue += (1100.0 * (7.5/100));

            if(grossSalary <= 2203.48d){
                //2nd salary band:
                inssValue += (grossSalary - 1100.0d) * (9.0/100);
            }else{
                inssValue += ((2203.48 - 1100.0)  * (9.0/100));

                if(grossSalary <= 3305.22){
                    //3rd salary band:
                    inssValue += (grossSalary - 2203.48) * (12.0/100);
                }else{
                    inssValue += ((3305.22  - 2203.49)  * (12.0/100));

                    if(grossSalary <= 6101.06){
                        //4th salary band:
                        inssValue += (grossSalary - 3305.22) * (14.0/100);
                    }else{
                        //5th salary band:
                        inssValue += ((6433.57 - 3305.23)  * (14.0/100));
            
                        inssValue += MAXIMUM_INCREASE_INSS_VALUE;
                    }
                } 
            } 
        }

        return inssValue;
    }
    
    /** 
     * Given a gross salary and a number of dependents, it determines and returns the
     * correspondingthe income tax deduction value, based on the following references.
     * 
     * https://www.todacarreira.com/calculo-salario-liquido/
     * https://www.contabilizei.com.br/contabilidade-online/tabela-inss/
     *  
     * @param grossSalary A gross salary.
     * @param numberOfDependents A number of dependents.
     * @return double The income tax deduction value.
     */
    public double getIncomeTaxValue(double grossSalary, int numberOfDependents) {
        //Constants:
        double DEDUCTION_VALUE_PER_DEPENDENT = 189.59;

        //Variables:
        double incomeTaxValue;
        double calculatingDependentsSalary;

        //It calculates the income tax value:
        calculatingDependentsSalary = grossSalary - (numberOfDependents * DEDUCTION_VALUE_PER_DEPENDENT);

        if(calculatingDependentsSalary <= 1903.98){
            //1st salary range:
            incomeTaxValue = 0;
        }else if(calculatingDependentsSalary <= 2826.65){
            //2nd salary band:
            incomeTaxValue = (calculatingDependentsSalary * (7.5/100)) - 142.80;
        }else if(calculatingDependentsSalary <= 3751.05){
            //3rd salary band:
            incomeTaxValue = (calculatingDependentsSalary * (15.0/100)) - 354.80;
        }else if(calculatingDependentsSalary <= 4664.68){
            //4th salary bBand:
            incomeTaxValue = (calculatingDependentsSalary * (22.5/100)) - 636.13;
        }else{
            //5th salary band:
            incomeTaxValue = (calculatingDependentsSalary * (27.5/100)) - 869.36;
        }

        incomeTaxValue = (incomeTaxValue < 0)? 0 : incomeTaxValue;

        return incomeTaxValue;
    }
}