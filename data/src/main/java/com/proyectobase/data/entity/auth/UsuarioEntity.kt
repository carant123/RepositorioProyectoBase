package com.proyectobase.data.entity.auth

import biz.belcorp.ffvv.data.db.AppDatabase
import com.google.gson.annotations.SerializedName
import com.raizlabs.android.dbflow.annotation.Column
import com.raizlabs.android.dbflow.annotation.PrimaryKey
import com.raizlabs.android.dbflow.annotation.Table
import com.raizlabs.android.dbflow.structure.BaseModel

@Table(database = AppDatabase::class, name = "Usuario")
class UsuarioEntity : BaseModel() {

    @PrimaryKey
    @SerializedName("codUsuario")
    @Column(name = "codUsuario")
    var codUsuario: String? = null

    @SerializedName("nombre")
    @Column(name = "nombre")
    var nombre: String? = null

    @SerializedName("apellido")
    @Column(name = "apellido")
    var apellido: String? = null

}