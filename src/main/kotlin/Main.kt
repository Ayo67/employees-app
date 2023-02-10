/*
Created on : February 10, 2022
Author     : Ayo Oguntuyi
*/

package ie.setu
import controllers.EmployeeAPI
import models.Employee
import mu.KotlinLogging


val logger = KotlinLogging.logger {}

fun main(args: Array<String>){
    logger.info { "Launching Employee App" }
    logger.info { "Launching Employee App" }
    logger.info { "Launching Employee App" }
    logger.info { "Launching Employee App" }
    start()
}

var employees = EmployeeAPI()

fun menu() : Int {
    print(""" 
         |Employee Menu
         |   1. Add Employee
         |   2. List All Employees
         |   3. Search Employees 
         |   4. Print Payslip for Employee
         |   5. Delete an Employee
         |   6. Update Employee details
         |   7. List all employees earning under a certain amount
         |   8. Sort by gender
         |   0. Exit
         |       
         |Enter Option : """.trimMargin())
    return readLine()!!.toInt()
}

fun start() {
    var input: Int

    do {
        input = menu()
        when (input) {
            1 -> add()
            2 -> list()
            3 -> search()
            4 -> paySlip()
            5 -> delete()
            6 -> update()
            7 -> listEmployeesUnderAmount()
            8 -> sortByGender()
            0 -> println("Exiting App")
            else -> println("Invalid Option")
        }
        println()
    } while (input != -1)
}
fun list() {
        val employees = employees.findAll()
        if (employees.isEmpty()) {
            println("No employees found.")
        } else {
            employees.forEach { println(it.toString()) }
        }
}

fun search() {
    val employee = getEmployeeById()
    if (employee == null) {
        println("No employee found")
    }else {
        println(employee)
    }
}

fun delete(){
    val employee = getEmployeeById()
    if (employee != null) {
        employees.delete(employee)
        println("Employee ID: ${employee.employeeID} was deleted")
    } else {
        println("No employee was found with the selected id")
    }
}

internal fun getEmployeeById(): Employee? {
    print("Enter the employee id to search by: ")
    val employeeID = readLine()!!.toInt()
    return employees.findOne(employeeID)
}

fun paySlip(){
    val employee = getEmployeeById()
    if (employee != null)
        println(employee.getPayslip())
}

fun add(){
    print("Enter first name: ")
    val firstName = readLine().toString()
    print("Enter surname: ")
    val surname = readLine().toString()
    print("Enter gender (m/f): ")
    val gender = readLine()!!.toCharArray()[0]
    print("Enter employee ID: ")
    val employeeID = readLine()!!.toInt()
    print("Enter gross salary: ")
    val grossSalary = readLine()!!.toDouble()
    print("Enter PAYE %: ")
    val payePercentage = readLine()!!.toDouble()
    print("Enter PRSI %: ")
    val prsiPercentage = readLine()!!.toDouble()
    print("Enter Annual Bonus: ")
    val annualBonus= readLine()!!.toDouble()
    print("Enter Cycle to Work Deduction: ")
    val cycleToWorkMonthlyDeduction= readLine()!!.toDouble()

    employees.create(Employee(firstName, surname, gender, employeeID, grossSalary, payePercentage, prsiPercentage, annualBonus, cycleToWorkMonthlyDeduction))
}

fun update() {
    val employee = getEmployeeById()
    if (employee != null) {
        print("Enter first name: ")
        val firstName = readLine().toString()
        print("Enter surname: ")
        val surname = readLine().toString()
        print("Enter gender (m/f): ")
        val gender = readLine()!!.toCharArray()[0]
        print("Enter employee ID: ")
        val employeeID = readLine()!!.toInt()
        print("Enter gross salary: ")
        val grossSalary = readLine()!!.toDouble()
        print("Enter PAYE %: ")
        val payePercentage = readLine()!!.toDouble()
        print("Enter PRSI %: ")
        val prsiPercentage = readLine()!!.toDouble()
        print("Enter Annual Bonus: ")
        val annualBonus= readLine()!!.toDouble()
        print("Enter Cycle to Work Deduction: ")
        val cycleToWorkMonthlyDeduction= readLine()!!.toDouble()

        val updatedEmployee = Employee(firstName, surname, gender, employeeID, grossSalary, payePercentage, prsiPercentage, annualBonus, cycleToWorkMonthlyDeduction)
        employees.update(employee, updatedEmployee)
        println("Employee ID:${employee.employeeID} was updated")
    } else {
        println("No employee was found with the selected ID")
    }
}

fun sortByGender() {
    employees.findAll().sortedBy { it.gender }
        .forEach { println(it.toString()) }
}

fun listEmployeesUnderAmount() {
    print("Enter the amount: ")
    val amount = readLine()!!.toDouble()
    val employeesUnderAmount = employees.findAll().filter { it.grossSalary < amount }
    if (employeesUnderAmount.isNotEmpty()) {
        employeesUnderAmount.forEach { println(it.toString()) }
    } else {
        println("No employees found earning under the entered amount.")
    }
}

















