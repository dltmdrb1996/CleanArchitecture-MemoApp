package com.example.hiltex

import android.content.Context
import androidx.room.Room
import com.example.hiltex.data.MemoDatabase
import com.example.hiltex.repository.MemoRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Qualifier
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object MemoDataBaseModule {
    @Qualifier
    @Retention(AnnotationRetention.RUNTIME)
    annotation class MemoDataBase

    @Singleton
    @MemoDataBase
    @Provides
    fun provideMemoDatabase(@ApplicationContext context: Context)=
        Room.databaseBuilder(context, MemoDatabase::class.java,"Memo.db").build()
}

@InstallIn(SingletonComponent::class)
@Module
object MemoRepositoryModule {

    @Singleton
    @Provides
    fun provideMemoRepository(@MemoDataBaseModule.MemoDataBase memoDatabase: MemoDatabase)=
        MemoRepository(memoDatabase)
}