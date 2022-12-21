package beerdb.application.beer;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class AppConfig {

	public static final String BREWERY_CACHE = "brewery-cache";

	@Value("${spring.redis.host}")
	private String redisHost;

	@Value("${spring.redis.port}")
	private Integer redisPort;

	@Value("${spring.redis.database}")
	private Integer redisDatabase;

	@Value("${spring.redis.username}")
    private String redisUsername;

    @Value("${REDIS_PASSWORD}")
    private String redisPassword;

	@Bean(BREWERY_CACHE)
	public RedisTemplate<String, String> createRedisTemplate() {
		final RedisStandaloneConfiguration config = new RedisStandaloneConfiguration();
		config.setHostName(redisHost);
		config.setPort(redisPort);
		config.setDatabase(redisDatabase);
		config.setUsername(redisUsername);
		config.setPassword(redisPassword);

		final JedisClientConfiguration jedisClient = JedisClientConfiguration
				.builder().build();
		final JedisConnectionFactory jedisFac = new JedisConnectionFactory(config, jedisClient);
		jedisFac.afterPropertiesSet();

		final RedisTemplate<String, String> redisTemplate = new RedisTemplate<>();
		redisTemplate.setConnectionFactory(jedisFac);
		redisTemplate.setKeySerializer(new StringRedisSerializer());
		redisTemplate.setValueSerializer(new StringRedisSerializer());
		System.out.println(">>>> running in RedisTemplate");
		return redisTemplate;
	}


}