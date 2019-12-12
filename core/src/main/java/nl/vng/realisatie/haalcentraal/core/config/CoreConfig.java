package nl.vng.realisatie.haalcentraal.core.config;

import nl.vng.realisatie.haalcentraal.core.model.Persoon;
import nl.vng.realisatie.haalcentraal.core.model.QPersoon;
import nl.vng.realisatie.haalcentraal.core.repository.PersoonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.annotation.PostConstruct;
import java.io.File;

@Configuration
@EnableJpaRepositories(basePackageClasses = PersoonRepository.class)
@EntityScan(basePackageClasses = Persoon.class)
public class CoreConfig {

    @Autowired
    private AutowireCapableBeanFactory beanFactory;

    @Autowired
    private PersoonRepository persoonRepository;

    @PostConstruct
    public void postConstruct() throws Exception {
        if (!persoonRepository.exists(QPersoon.persoon.bsn.eq(999990639L))) {
            final RivgLoader rivgLoader = new RivgLoader();
            beanFactory.autowireBean(rivgLoader);
            rivgLoader.loadRivg(File.separatorChar + "testset.csv");
        }
    }

}
