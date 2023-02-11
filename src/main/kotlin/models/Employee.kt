/*
Created on : February 10, 2022
Author     : Ayo Oguntuyi
*/

package models

import ie.setu.ConsoleColours
import ie.setu.ConsoleColours.CYAN
import ie.setu.ConsoleColours.GREEN
import ie.setu.ConsoleColours.PURPLE_UNDERLINED
import ie.setu.ConsoleColours.RESET
import kotlin.math.round

class Employee(
    var firstName: String,
    var surname: String,
    var gender: Char,
    var employeeID: Int,
    var grossSalary: Double,
    var payePercentage: Double,
    var prsiPercentage: Double,
    var annualBonus: Double,
    var cycleToWorkMonthlyDeduction: Double)
{
    fun getFullName() = when (gender) {
        'm', 'M' -> "Mr. ${firstName} $surname}"
        'f', 'F' -> "Ms.  ${firstName} ${surname}"
        else -> "${firstName} ${surname}"
    }



    fun roundTwoDecimals(number: Double) = round(number * 100) / 100

    fun getMonthlySalary() = roundTwoDecimals(grossSalary / 12)
    fun getMonthlyPRSI() = roundTwoDecimals(getMonthlySalary() * (prsiPercentage / 100))
    fun getMonthlyPAYE() = roundTwoDecimals(getMonthlySalary() * (payePercentage / 100))
    fun getGrossMonthlyPay() = roundTwoDecimals(getMonthlySalary() + (annualBonus / 12))
    fun getTotalMonthlyDeductions() = roundTwoDecimals((getMonthlyPRSI() + getMonthlyPAYE() + cycleToWorkMonthlyDeduction))
    fun getNetMonthlyPay() = roundTwoDecimals(roundTwoDecimals(getGrossMonthlyPay() - getTotalMonthlyDeductions()))


    fun getPayslip() =
        CYAN + """
+-----------------------------------------------------------------------+
| $PURPLE_UNDERLINED Monthly Payslip$RESET               |
+-----------------------------------------------------------------------+
| $PURPLE_UNDERLINED Employee Information$RESET          |
| $GREEN Name:$RESET ${getFullName()}           |
| $GREEN ID:$RESET ${employeeID}                |
+-----------------------------------------------------------------------+
| $PURPLE_UNDERLINED Payment Details$RESET              |
| $GREEN Gross Pay:$RESET} $${getGrossMonthlyPay()} |
| $GREEN Salary:$RESET $${getMonthlySalary()}   |
| $GREEN Bonus:$RESET $${roundTwoDecimals(annualBonus / 12)} |
+-----------------------------------------------------------------------+
| $PURPLE_UNDERLINED}Deduction Details$RESET            |
| $GREEN Total Deductions:$RESET $${getTotalMonthlyDeductions()} |
| $GREEN PAYE:$RESET $${getMonthlyPAYE()}      |
| $GREEN PRSI:$RESET $${getMonthlyPRSI()}      |
| $GREEN Cycle To Work:$RESET $${cycleToWorkMonthlyDeduction} |
+-----------------------------------------------------------------------+
| $PURPLE_UNDERLINED Net Pay:$RESET $${getNetMonthlyPay()} |
+-----------------------------------------------------------------------+
""" + RESET




    override fun toString(): String {
        return """
             Employee
             ______________________________________________________________
                |   Name: '${getFullName()}'               ID: $employeeID
                |   Gender: $gender                             
                |   Gross Salary: $grossSalary
                |   Paye Percentage: $payePercentage           
                |   Prsi Percentage: $prsiPercentage
                |   Annual Bonus: $annualBonus                              
                |   Cycle To Work Monthly Deduction: $cycleToWorkMonthlyDeduction                     
             ‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾  """
    }
}








