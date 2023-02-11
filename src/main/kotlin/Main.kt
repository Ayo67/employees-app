/*
Created on : February 10, 2022
Author     : Ayo Oguntuyi
*/

package ie.setu
import controllers.EmployeeAPI
import ie.setu.ConsoleColours.BLUE
import ie.setu.ConsoleColours.GREEN
import ie.setu.ConsoleColours.PURPLE
import ie.setu.ConsoleColours.RED
import ie.setu.ConsoleColours.RED_UNDERLINED
import ie.setu.ConsoleColours.RESET
import ie.setu.ConsoleColours.WHITE_UNDERLINED
import ie.setu.ConsoleColours.YELLOW
import models.Employee
import mu.KotlinLogging


val logger = KotlinLogging.logger {}




fun main(args: Array<String>){
    logger.info { "Launching Employee App" }
    start()
}

var employees = EmployeeAPI()





fun menu() : Int {
    print(""" $RED_UNDERLINED
         |Employee Menu $RESET
         
         $BLUE|   1. Add Employee$RESET
         $YELLOW|   2. List All Employees$RESET
         $GREEN|   3. Search Employees $RESET
         $PURPLE|   4. Print Payslip for Employee$RESET
         $BLUE|   5. Delete an Employee$RESET
         $YELLOW|   6. Update Employee details$RESET
         $GREEN|   7. List all employees earning under a certain amount$RESET
         $PURPLE|   8. Sort by gender$RESET
         $RED_UNDERLINED|   0. Exit $RESET
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
            99 -> dummyData()
            0 -> {
                println("Exiting App")
                logger.info("Exited Employee App")
            }
            else -> println("Invalid Option")
        }
        println()
    } while (input != -1)
}
fun list() {
        val employees = employees.findAll()
        if (employees.isEmpty()) {
            println("No employees found.")
            logger.info("No employees found.")
        } else {
            employees.forEach { println(it.toString()) }
        }
}

fun dummyData() {
    employees.create(Employee("Joe", "Soap", 'm', 0, 35655.43, 31.0, 7.5, 2000.0, 25.6))
    employees.create(Employee("Joan", "Murphy", 'f', 1, 54255.13, 32.5, 7.0, 1500.0, 55.3))
    employees.create(Employee("Mary", "Quinn", 'f', 2, 75685.41, 40.0, 8.5, 4500.0, 0.0))
    employees.create(Employee("Robert", "Downey", 'm', 3, 45000.00, 27.0, 6.0, 1000.0, 10.0))
    employees.create(Employee("Jane", "Lavender", 'f', 4, 55000.00, 28.0, 7.0, 2000.0, 20.0))
    employees.create(Employee("Jim", "Smith", 'm', 5, 65000.00, 35.0, 8.0, 3000.0, 30.0))
    employees.create(Employee("Christopher", "Nolan", 'f', 6, 75000.00, 40.0, 9.0, 4000.0, 40.0))
    employees.create(Employee("Jessica", "Brown", 'f', 7, 85000.00, 45.0, 10.0, 5000.0, 50.0))
    logger.info("Dummy data added.")
}


fun search() {
    val employee = getEmployeeById()
    if (employee == null) {
        println("No employee found")
        logger.info {"No employee found"}
    }else {
        println(employee)
    }
}

fun delete(){
    val employee = getEmployeeById()
    if (employee != null) {
        employees.delete(employee)
        println("Employee ID: ${employee.employeeID} was deleted")
        logger.info {"Employee ID: ${employee.employeeID} was deleted"}
    } else {
        println("No employee was found with the selected id")
        logger.info {"No employee was found with the selected id"}
    }
}

internal fun getEmployeeById(): Employee? {
    print("Enter the employee id to search by: ")
    val employeeID = readLine()!!.toInt()
    return employees.findOne(employeeID)
}

fun paySlip(){
    val employee = getEmployeeById()
    if (employee != null) {
        println(employee.getPayslip())
        logger.info { "Pay slip generated for employee ID: ${employee.employeeID}" }
    }

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
    logger.info { "Added New  Employee : ${employeeID}" }
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
        logger.info { "EmployeeID : ${employeeID}" }
    } else {
        println("No employee was found with the selected ID")
        logger.info{"No employee was  found"}
    }
}
fun sortByGender() {
    val sortedListG = employees.sortByGender()
    sortedListG.forEach { println(it.toString()) }
    logger.info{"Employees sorted by gender"}
}

fun listEmployeesUnderAmount() {
    print("Enter the amount: ")
    val amount = readLine()!!.toDouble()
    val employeesUnderAmount = employees.listEmployeesUnderAmount(amount)
    if (employeesUnderAmount.isNotEmpty()) {
        employeesUnderAmount.forEach { println(it.toString())
            logger.info("List of employees earning under the entered amount: $employeesUnderAmount")
        }
    } else {
        println("No employees found earning under the entered amount.")
    }
}


















