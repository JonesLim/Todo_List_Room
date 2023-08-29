package com.jones.todo_list_room.ui.utils

object Validation {
    fun validate(vararg vs: FieldAndReg): Pair<Boolean, String> {
        vs.forEach {
            if (it.value == "") {
                return Pair(false, "${it.name} cannot be empty!")
            }
            if (it.reg != null) {
                if (!Regex(it.reg).matches(it.value)) {
                    return Pair(false, "Please enter a valid ${it.name}!")
                }
            }
        }
        return Pair(true, "")
    }
}

data class FieldAndReg(
    val name: String,
    val value: String,
    val reg: String? = null
)