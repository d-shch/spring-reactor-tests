import io.r2dbc.spi.ConnectionFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.r2dbc.config.AbstractR2dbcConfiguration
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate
import org.springframework.r2dbc.connection.R2dbcTransactionManager
import org.springframework.transaction.ReactiveTransactionManager
import org.springframework.transaction.reactive.TransactionalOperator


@Configuration
class R2dbcConfiguration(
    private val connectionFactory: ConnectionFactory
) : AbstractR2dbcConfiguration() {

    override fun connectionFactory(): ConnectionFactory = connectionFactory

    @Bean
    fun r2dbcEntityTemplate() = R2dbcEntityTemplate(connectionFactory)
}
