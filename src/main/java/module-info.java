module com.artere.shop {
    requires spring.boot;
    requires spring.boot.autoconfigure;
    requires spring.context;
    requires spring.web;
    requires spring.data.jpa;
    requires spring.tx;
    requires spring.beans;
    
    requires jakarta.persistence;
    requires jakarta.validation;
    requires jakarta.annotation;
    
    requires org.jooq;
    requires org.jmolecules.ddd;
    requires org.springframework.modulith.api;
    requires org.mapstruct;
    
    requires java.sql;
    
    exports com.artere.shop;
    exports com.artere.shop.catalogue.domain.model;
    exports com.artere.shop.catalogue.domain.port.in;
    exports com.artere.shop.catalogue.infrastructure.adapter.in.web.dto;
    exports com.artere.shop.cart.domain.model;
    exports com.artere.shop.cart.domain.port.in;
    exports com.artere.shop.cart.infrastructure.adapter.in.web.dto;
    exports com.artere.shop.shared.domain.model;
    
    opens com.artere.shop to spring.core, spring.beans, spring.context;
    opens com.artere.shop.catalogue.infrastructure.adapter.in.web to spring.beans, spring.web, spring.core;
    opens com.artere.shop.catalogue.infrastructure.adapter.out.persistence to spring.beans, spring.core;
    opens com.artere.shop.catalogue.infrastructure.adapter.out.persistence.entity to spring.core, org.hibernate.orm.core;
    opens com.artere.shop.catalogue.infrastructure.adapter.out.persistence.query to spring.beans, spring.core;
    opens com.artere.shop.catalogue.application.service to spring.beans, spring.core;
    
    opens com.artere.shop.cart.infrastructure.adapter.in.web to spring.beans, spring.web, spring.core;
    opens com.artere.shop.cart.infrastructure.adapter.out.persistence to spring.beans, spring.core;
    opens com.artere.shop.cart.infrastructure.adapter.out.persistence.entity to spring.core, org.hibernate.orm.core;
    opens com.artere.shop.cart.infrastructure.adapter.out.catalogue to spring.beans, spring.core;
    opens com.artere.shop.cart.application.service to spring.beans, spring.core;
    
    opens com.artere.shop.shared.infrastructure.adapter.in.web to spring.beans, spring.web, spring.core;
}
