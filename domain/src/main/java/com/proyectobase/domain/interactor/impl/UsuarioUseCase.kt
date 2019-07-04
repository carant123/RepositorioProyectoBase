package com.proyectobase.domain.interactor.impl

import com.proyectobase.domain.entity.Usuario
import com.proyectobase.domain.executor.PostExecutionThread
import com.proyectobase.domain.executor.ThreadExecutor
import com.proyectobase.domain.interactor.BaseSingleObserver
import com.proyectobase.domain.interactor.UseCase
import com.proyectobase.domain.repository.UsuarioRepository
import javax.inject.Inject

class UsuarioUseCase
@Inject constructor(private val usuarioRepository: UsuarioRepository,
                    threadExecutor: ThreadExecutor,
                    postExecutionThread: PostExecutionThread) : UseCase(threadExecutor, postExecutionThread){

    fun obtenerUsuario(codigo: String, password: String, observer: BaseSingleObserver<Usuario>) {
        val observable = usuarioRepository.obtenerUsuario(codigo, password)
        execute(observable,observer)
    }

}