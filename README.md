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
You are abe to import this dependency via `pom.xml`. 
we provide a lot of util class including
- Email (Send email -> send email sync mode , send email async mode)
- File (Manage file -> get file , download file , save file)
- Token (generate salt, extract jwt token)
- Uuid (generate uuidv7)


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
