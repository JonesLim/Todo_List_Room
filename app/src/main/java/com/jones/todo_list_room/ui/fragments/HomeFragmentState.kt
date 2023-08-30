package com.jones.todo_list_room.ui.fragments

import kotlinx.coroutines.flow.MutableStateFlow

class HomeFragmentState {
    var counter: MutableStateFlow<Int> = MutableStateFlow(0)

    fun increment() {
        counter.value = counter.value + 1
    }
}
// extra eg for state flow