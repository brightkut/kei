## Kei Framework

this is the project that provided library and utility function for develop rest API easier
such as `Execption`, `Model Response` etc.


### Enable Configuration in you application
1. You can use `@EnableKei` to allow using `kei` configuration

```java
import com.brightkut.kei.lib.EnableKei;

@EnableKei
@SpringBootApplication
public class KeiApplication {

    public static void main(String[] args) {
        SpringApplication.run(KeiApplication.class, args);
    }
}
```

### Usage Util
1. You are abe to import this dependency via `pom.xml`

### Usage Exception Handler
1. Need to enable this configuration in `application.yml`

```yaml
kei:
  exception:
    handler:
      enabled: true  
```

### Usage Email feature 
1. Need to enable this configuration in `application.yml`

```yaml
kei:
  mail:
    enabled: true  
```
