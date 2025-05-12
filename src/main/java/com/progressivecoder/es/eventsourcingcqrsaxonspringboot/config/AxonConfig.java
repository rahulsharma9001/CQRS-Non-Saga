package com.progressivecoder.es.eventsourcingcqrsaxonspringboot.config;

import com.progressivecoder.es.eventsourcingcqrsaxonspringboot.aggregates.AccountAggregate;
import com.thoughtworks.xstream.XStream;
import org.axonframework.commandhandling.CommandBus;
import org.axonframework.commandhandling.SimpleCommandBus;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.commandhandling.gateway.DefaultCommandGateway;
import org.axonframework.common.jpa.EntityManagerProvider;
import org.axonframework.common.transaction.TransactionManager;
import org.axonframework.eventsourcing.EventSourcingRepository;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.axonframework.serialization.Serializer;
import org.axonframework.serialization.xml.XStreamSerializer;
import org.axonframework.spring.messaging.unitofwork.SpringTransactionManager;
import org.axonframework.springboot.util.jpa.ContainerManagedEntityManagerProvider;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class AxonConfig {


    @Bean
    public EntityManagerProvider entityManagerProvider() {
        return new ContainerManagedEntityManagerProvider();
    }


    @Bean
    public TransactionManager axonTransactionManager(PlatformTransactionManager platformTransactionManager) {
        return new SpringTransactionManager(platformTransactionManager);
    }

    @Bean
    public XStream xStream() {
        XStream xStream = new XStream();
        xStream.allowTypesByWildcard(new String[] {
                "com.progressivecoder.es.eventsourcingcqrsaxonspringboot.commands**",
                "com.progressivecoder.es.eventsourcingcqrsaxonspringboot.events**"
        });
        return xStream;
    }

    @Bean
    @Primary
    @Qualifier("messageSerializer")
    public XStreamSerializer xStreamSerializer(XStream xStream) {
        return XStreamSerializer.builder()
                .xStream(xStream)
                .build();
    }

    @Bean
    @Qualifier("eventSerializer")
    public Serializer eventSerializer(XStream xStream) {
        return XStreamSerializer.builder()
                .xStream(xStream)
                .build();
    }


    @Bean
    @Qualifier("accountAggregateEventSourcingRepository")
    public EventSourcingRepository<AccountAggregate> accountAggregateEventSourcingRepository(EventStore eventStore) {
        return EventSourcingRepository.builder(AccountAggregate.class)
                .eventStore(eventStore)
                .build();
    }

    @Bean
    public CommandGateway commandGateway(CommandBus commandBus) {
        return DefaultCommandGateway.builder().commandBus(commandBus).build();
    }

    @Bean
    @Qualifier("localSegment")
    public CommandBus commandBus() {
        return SimpleCommandBus.builder().build();
    }

    @Bean
    @Qualifier("messageSerializer")  // Add this qualifier
    public Serializer messageSerializer(XStream xStream) {
        return XStreamSerializer.builder()
                .xStream(xStream)
                .build();
    }
}