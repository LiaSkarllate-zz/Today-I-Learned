package com.formatta.rc.dpapi.service;

//Imports:
import org.springframework.stereotype.Service;

@Service
public class DpService {
    //Methods:
    /** 
     * Given a gross salary and a number of dependents, it determines and
     * returns the corresponding net salary, which includes both the INSS and 
     * income tax deductions, based on the implemented methods of this class.
     * 
     * @param grossSalary A gross salary.
     * @param numberOfDependents A number of dependents.
     * @return double The corresponding net salary.
     */
    public double getNetSalary(double grossSalary, int numberOfDependents) {
        //It deducts INSS from gross salary:
        double inssValue = getInssValue(grossSalary);
        grossSalary -= inssValue;
        
        //It deducts INSS from gross salary:
        double incomeTaxValue = getIncomeTaxValue(grossSalary, numberOfDependents);
        grossSalary -= incomeTaxValue;

        /*
        System.out.println("inssValue = " + inssValue);
        System.out.println("incomeTaxValue = " + incomeTaxValue);
        */

        return grossSalary;
    }
    
    /** 
     * Given a salary, it determines and returns the corresponding
     * the inss deduction value, based on the following references.
     * 
     * https://www.todacarreira.com/calculo-salario-liquido/
     * Table update: https://www.contabilizei.com.br/contabilidade-online/tabela-inss/
     * 
     * @param salary A salary.
     * @return double The inss deduction value
     */
    public double getInssValue(double salary) {
        //Constants:
        double MAXIMUM_INCREASE_INSS_VALUE = 713.10;

        //Variables:
        double inssValue = 0;

        //It calculates the INSS value:
        if(salary <= 1100.0){
            //1st salary range:
            inssValue += salary * (7.5/100);
        }else{
            inssValue += (1100.0 * (7.5/100));

            if(salary <= 2203.48d){
                //2nd salary band:
                inssValue += (salary - 1100.0d) * (9.0/100);
            }else{
                inssValue += ((2203.48 - 1100.0)  * (9.0/100));

                if(salary <= 3305.22){
                    //3rd salary band:
                    inssValue += (salary - 2203.48) * (12.0/100);
                }else{
                    inssValue += ((3305.22  - 2203.49)  * (12.0/100));

                    if(salary <= 6101.06){
                        //4th salary band:
                        inssValue += (salary - 3305.22) * (14.0/100);
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
     * Given a salary and a number of dependents, it determines and returns the
     * correspondingthe income tax deduction value, based on the following references.
     * 
     * https://www.todacarreira.com/calculo-salario-liquido/
     *  
     * @param salary A salary.
     * @param numberOfDependents A number of dependents.
     * @return double The income tax deduction value.
     */
    public double getIncomeTaxValue(double salary, int numberOfDependents) {
        //Constants:
        double DEDUCTION_VALUE_PER_DEPENDENT = 189.59;

        //Variables:
        double incomeTaxValue;
        double calculatingDependentsSalary;

        //It calculates the income tax value:
        calculatingDependentsSalary = salary - (numberOfDependents * DEDUCTION_VALUE_PER_DEPENDENT);

        if(calculatingDependentsSalary <= 1903.98) { 
            //1st salary range:
            incomeTaxValue = 0;
        }else if(calculatingDependentsSalary <= 2826.65) {  
            //2nd salary band:
            incomeTaxValue = (calculatingDependentsSalary * (7.5/100)) - 142.80;
        }else if(calculatingDependentsSalary <= 3751.05) {
            //3rd salary band:
            incomeTaxValue = (calculatingDependentsSalary * (15.0/100)) - 354.80;
        }else if(calculatingDependentsSalary <= 4664.68) {
            //4th salary bBand:
            incomeTaxValue = (calculatingDependentsSalary * (22.5/100)) - 636.13;
        }else{
            //5th salary band:
            incomeTaxValue = (calculatingDependentsSalary * (27.5/100)) - 869.36;
        }

        incomeTaxValue = (incomeTaxValue < 0)? 0 : incomeTaxValue;


        return incomeTaxValue;
    }

    
    /** 
     * Given a gross salary, the average value paid for overtime hours, a number of dependents, 
     * a requested number of vacation days, if 1/3 of the vacation was sold and the first installment 
     * of the 13th salary was advanced, it determines and returns the corresponding vacation value, 
     * based on the following references.
     * 
     * https://blog.nubank.com.br/como-calcular-ferias/
     * https://www.mobills.com.br/blog/calculadoras/calculadora-ferias/
     * https://www.calculadorafacil.com.br/trabalhista/calculo-de-ferias/
     * 
     * @param grossSalary A gross salary.
     * @param averageOvertimeHours The average value paid for overtime hours, if it has done.
     * @param numberaOfDependents A number of dependents.
     * @param numberOfVacationDays A requested number of vacation days .
     * @param sold Was 1/3 of the vacation sold?
     * @param advance13th Advance the first installment of the 13th salary?
     * @return double The vacation value;
     */
    public double getVacationValue(double grossSalary, double averageOvertimeHours, int numberOfDependents,  int numberOfVacationDays, boolean sold, boolean advance13th) {
        //Variables:
        double salaryBase = grossSalary + averageOvertimeHours;

        double vacationValue;
        double vacationValueThird;

        double vacationPremiumValue;
        double vacationPremiumValueThird;
        double extraSalaryAdvanced;

        vacationValue = ((salaryBase / 30) * numberOfVacationDays);
        vacationValueThird = vacationValue * (1d/3);

        extraSalaryAdvanced = advance13th? (grossSalary / 2) : 0; 

        if(sold){
            vacationPremiumValue = salaryBase * (1d/3);
            vacationPremiumValueThird = vacationPremiumValue * (1d/3);
        }else{
            vacationPremiumValue = 0;
            vacationPremiumValueThird = 0;
        }

        /*
        System.out.println("vacationValue = " + vacationValue);
        System.out.println("vacationValueThird = " + vacationValueThird);
        System.out.println("vacationPremiumValue = " + vacationPremiumValue);
        System.out.println("vacationPremiumValueThird = " + vacationPremiumValueThird);
        System.out.println("extraSalaryAdvanced = " + extraSalaryAdvanced);
        */

        return getNetSalary((vacationValue + vacationValueThird), numberOfDependents) + vacationPremiumValue + vacationPremiumValueThird + extraSalaryAdvanced;
    }
}