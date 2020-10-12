package abuladze.levan.homework2

data class ItemToDoList(
    val id: Long = -1,
    val title: String = "",
    val description: String? = null,
    var isCompleted: Boolean = false
)