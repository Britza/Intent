package com.example.intent

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class paciente(val nombre: String? = null, val apellido: String? = null)
