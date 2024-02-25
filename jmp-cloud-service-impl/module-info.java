module com.tananushka.javaadvanced.jmp.cloud.service.impl {
    requires com.tananushka.javaadvanced.jmp.service.api;
    requires spring.boot.starter.data.jpa;
    requires spring.boot;
    requires java.persistence;
    requires h2;
    exports com.tananushka.javaadvanced.jmp.cloud.service.impl;
}
