package com.example.mvvmtodo.di

import android.app.Application
import androidx.room.Room
import com.example.mvvmtodo.data.TaskDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import javax.inject.Qualifier
import javax.inject.Singleton


@Module  //when we need this object dagger will automaticly provide it
@InstallIn(ApplicationComponent::class)
object AppModule {


    @Provides  //tell dagger its instruction function
    @Singleton
    fun provideDatabase(

        app: Application,
        callback: TaskDatabase.Callback

    ) = Room.databaseBuilder(app, TaskDatabase::class.java, "task_database")
        .fallbackToDestructiveMigration()
        .addCallback(callback)
        .build()

    @Provides
    fun provideTaskDao(db: TaskDatabase)=db.taskDao()
    //coroutine scope for suspend actions : it used to suspend excutions and let the program continue with something else
    //excute in background
    @ApplicationScope
    @Provides
    @Singleton
    fun provideApplicationScope()= CoroutineScope(/* if any of Coroutine
    childes fails keep the other childes running -->*/ SupervisorJob())

    @Retention(AnnotationRetention.RUNTIME)
    @Qualifier
    annotation class ApplicationScope


}