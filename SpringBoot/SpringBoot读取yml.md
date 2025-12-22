> SpringBoot 把“从 application.yml 里取值”这件事做成了三层 API，对应 3 组注解
1. `@Value` —— 取单个标量
用法：
```java
@Value("${app.task.pool-size:8}")   // 冒号后面是默认值
private int poolSize;

@Value("#{systemProperties['user.region']}") // 还能写 SpEL
private String region;
```

2. `@ConfigurationProperties` —— 把“一坨”配置整棵绑成 Bean. 官方最推荐, 包揽90%业务配置
    用法：
    ```java
    @Data
    @Component
    @ConfigurationProperties(prefix = "app.task")   // 前缀
    @Validated                                      // 支持 JSR-303
    public class TaskProperties {
        private int poolSize = 8;                   // 字段名=后缀
        private Duration timeout = Duration.ofSeconds(30);
        private List<String> whiteList;
    }
    ```
    对应yml
    ```yml
    app:
    task:
        pool-size: 16
        timeout: 60s
        white-list: [foo,bar]
    ```

3. Spring 原生 `@PropertySource` 只认 .properties，想额外加载别的 yml 得自己写 `YamlPropertySourceFactory`

4. `Environment` 接口 —— 代码里动态抠值

    ```java
    @Autowired
    private Environment env;

    public void foo(){
        String key = env.getProperty("app.task.pool-size", Integer.class, 8);
    }
    ```
    属于编程式 API，写库/框架时偶尔用，业务代码基本见不到。


