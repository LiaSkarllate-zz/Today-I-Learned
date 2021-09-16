package br.demo.rc.hexagonal.configuration;

//Imports:
import br.demo.rc.hexagonal.UsingHexagonalArchitectureWithSpringFrameworkApplication;
import br.demo.rc.hexagonal.logic.BankAccountService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackageClasses = UsingHexagonalArchitectureWithSpringFrameworkApplication.class)
public class BeanConfiguration {
    //Methods:
    @Bean
    BankAccountService bankAccountService() {
        return new BankAccountService();
    }
}