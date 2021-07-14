package com.example.hibernatesecondlevelcache;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@EnableCaching
public class HibernateSecondLevelCacheApplication {

    public static void main(String[] args) {
        SpringApplication.run(HibernateSecondLevelCacheApplication.class, args);
    }

    @Autowired
    private ElementRepository elementRepository;

    @GetMapping("/{id}")
    public Element element(@PathVariable Long id) {
        var element = elementRepository.findById(id).get();
        //showCacheStats();

        return element;
    }

    @GetMapping("/")
    public String hello() {
        return "hello";
    }

//    private void showCacheStats() {
//        System.out.println("Cache size: " +
//                .getCache("com.example.hibernatesecondlevelcache.Element").getSize());
//    }

/*    @Bean
    public EhCacheManagerFactoryBean ehCacheManagerFactory() {
        EhCacheManagerFactoryBean cacheManagerFactoryBean = new EhCacheManagerFactoryBean();
        cacheManagerFactoryBean.setConfigLocation(new ClassPathResource("ehcache.xml"));
        cacheManagerFactoryBean.setShared(true);

        return cacheManagerFactoryBean;
    }

    @Bean
    public EhCacheCacheManager ehCacheCacheManager() {
        EhCacheCacheManager cacheManager = new EhCacheCacheManager();
        cacheManager.setCacheManager(ehCacheManagerFactory().getObject());
        cacheManager.setTransactionAware(true);
        return cacheManager;
    }*/
}
