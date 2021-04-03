//package com.example.sr
//
//import io.r2dbc.spi.ConnectionFactory
//import org.springframework.beans.factory.annotation.Qualifier
//import org.springframework.context.annotation.Bean
//import org.springframework.context.annotation.Configuration
//import org.springframework.data.r2dbc.config.AbstractR2dbcConfiguration
//import org.springframework.data.r2dbc.connectionfactory.R2dbcTransactionManager
import io.r2dbc.spi.ConnectionFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.r2dbc.config.AbstractR2dbcConfiguration
import org.springframework.r2dbc.connection.R2dbcTransactionManager
import org.springframework.transaction.ReactiveTransactionManager
import org.springframework.transaction.reactive.TransactionalOperator


@Configuration
class R2dbcConfiguration(
    private val connectionFactory: ConnectionFactory
) : AbstractR2dbcConfiguration() {

    override fun connectionFactory(): ConnectionFactory = connectionFactory

    override fun getCustomConverters(): MutableList<Any> = mutableListOf()

    @Bean
    fun reactiveTransactionManager(): ReactiveTransactionManager = R2dbcTransactionManager(connectionFactory)

    @Bean
    fun transactionalOperator(reactiveTransactionManager: ReactiveTransactionManager
    ) = TransactionalOperator.create(reactiveTransactionManager)
}
