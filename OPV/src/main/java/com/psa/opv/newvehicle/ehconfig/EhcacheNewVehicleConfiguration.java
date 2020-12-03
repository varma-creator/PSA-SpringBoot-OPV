/*
 * package com.psa.opv.newvehicle.ehconfig;
 * 
 * import org.springframework.cache.CacheManager; import
 * org.springframework.cache.annotation.EnableCaching; import
 * org.springframework.cache.ehcache.EhCacheCacheManager; import
 * org.springframework.cache.ehcache.EhCacheManagerFactoryBean; import
 * org.springframework.context.annotation.Bean; import
 * org.springframework.context.annotation.Configuration; import
 * org.springframework.core.io.ClassPathResource;
 * 
 *//**
 *This  class is customized ehcache
	 * @author USER
	 *
	 *//*
		 * @EnableCaching
		 * 
		 * @Configuration public class EhcacheNewVehicleConfiguration {
		 * 
		 * @Bean public CacheManager cacheManager() { net.sf.ehcache.CacheManager
		 * ehObject = ehCacheManagerFactory().getObject(); return new
		 * EhCacheCacheManager(ehObject); }
		 * 
		 * @Bean public EhCacheManagerFactoryBean ehCacheManagerFactory() {
		 * EhCacheManagerFactoryBean ehCacheBean = new EhCacheManagerFactoryBean();
		 * ehCacheBean.setConfigLocation(new ClassPathResource("ehcache.xml"));
		 * ehCacheBean.setShared(true); return ehCacheBean; } }
		 */