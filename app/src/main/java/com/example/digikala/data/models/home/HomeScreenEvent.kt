package com.example.digikala.data.models.home

sealed interface HomeScreenEvent {
    object OnRetryClicked : HomeScreenEvent
}