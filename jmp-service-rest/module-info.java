module com.tananushka.javaadvanced.jmp.service.rest {
    requires com.tananushka.javaadvanced.jmp.cloud.service.impl;
    requires spring.web;
    requires spring.boot.starter.web;
    exports com.tananushka.javaadvanced.jmp.service.rest;
}
