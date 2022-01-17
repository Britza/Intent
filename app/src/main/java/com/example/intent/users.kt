package com.example.intent

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class users( val lt: Double? = null, val lg: Double? = null, val nombre: String? = null )
