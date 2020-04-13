package com.nowak.demo.controllers

import com.nowak.demo.database.InvoicerDatabase
import com.nowak.demo.models.login.User
import tornadofx.*
import java.lang.Exception
import java.time.LocalDate

class UserController : Controller() {

    val invoicerDatabase = InvoicerDatabase()

    fun findUserById(id: Long): User {
        return invoicerDatabase.findUserById(id)
    }

    fun updateUser(userId: Long, newUsername: String? = null, newEmail: String? = null, newBirthDate: LocalDate? = null, newPassword: String? = null): Boolean {
        return invoicerDatabase.updateUser(userId, newUsername, newEmail, newBirthDate, newPassword)
    }

    fun checkUsernameEmailAvailability(username: String? = null, email: String? = null): Boolean {
        return invoicerDatabase.checkUsernameEmailAvailability(username, email)
    }
}