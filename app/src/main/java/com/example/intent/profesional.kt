package com.example.intent

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class profesional(val nombre: String? = null, val apellido: String? = null, val email: String? = null )