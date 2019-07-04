package biz.belcorp.ffvv.presentation.di.koin

import android.app.Application
import biz.belcorp.ffvv.domain.di.koin.domainModule
import com.proyectobase.data.di.koin.dataModule
import com.proyectobase.data.executor.JobExecutor
import com.proyectobase.domain.executor.PostExecutionThread
import com.proyectobase.domain.executor.ThreadExecutor
import com.proyectobase.presentation.UIThread
import com.proyectobase.presentation.di.koin.commonsModule
import com.proyectobase.presentation.di.koin.loginSoporteModule
import org.koin.android.ext.android.startKoin
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module.module

fun injectModules(app: Application) {
    app.startKoin(app, modules)
}

private val modules by lazy {
    listOf(
            threadsModule,
            presentationModule,
            domainModule,
            dataModule
    )
}

val presentationModule = module {

    factory { androidApplication() }

    featureModules.mapTo(subModules) {
        it.invoke(koinContext)
    }

}

val threadsModule = module {
    factory { UIThread() as PostExecutionThread }
    factory { JobExecutor() as ThreadExecutor }
}

val featureModules by lazy {
    listOf(
            commonsModule,
            loginSoporteModule
    )
}
