<div align="center">
    <br/>
    <h1>SuBoot</h1>

[![SpringBoot](https://img.shields.io/badge/SpringBoot-v2.6.x-blue)](https://spring.io/projects/spring-boot/)
[![License](https://img.shields.io/badge/License-MIT-grenn.svg)](LICENSE)
</div>

## ð é¡¹ç®ä»ç»

[SuBoot](https://github.com/JsckChin/SuBoot) å®å¨åè´¹ï¼ä¸å¯åç¨ï¼åºäº [SpringBoot](https://spring.io/projects/spring-boot/) çä¸­åå°è§£å³æ¹æ¡ï¼å®ä½¿ç¨äºææ°çååç«¯ææ¯æ ï¼å¹¶æç¼äºå¸åçä¸å¡æ¨¡åï¼é¡µé¢ï¼åæ¬äºæ¬¡å°è£ç»ä»¶ãå¨æèåãæéæ ¡éªãç²å­åæéæ§å¶ç­åè½ï¼å®å¯ä»¥å¸®å©ä½ å¿«éæ­å»ºä¼ä¸çº§ä¸­åå°é¡¹ç®ï¼ ç¸ä¿¡ä¸ç®¡æ¯ä»æ°ææ¯ä½¿ç¨è¿æ¯å¶ä»æ¹é¢ï¼é½è½å¸®å©å°ä½ ã
 
## ðå¼åå·¥å·
*   IntelliJ IDEA
*   Node 12.13.0 +
*   Maven 3.6.3 +
*   Git

## ðè¿è¡ç¯å¢

*   JDK 11 + æ OpenJDK 11 +
*   Redis 4.0.8 +
*   MySql 5.8

## é¡¹ç®æªå¾

![è¾å¥å¾çè¯´æ](doc/static/login.png)

![è¾å¥å¾çè¯´æ](doc/static/image.png)



## é¡¹ç®ä½¿ç¨
```
# å¯å¨é¡¹ç®
mvn compile spring-boot:run

# é¡¹ç®æå
mvn clean package

# é¡¹ç®æåè·³è¿æµè¯ç¯è
mvn clean package -Dmaven.test.skip=true
```

# é¡¹ç®é¨ç½²
```
# ç´æ¥è¿è¡
java -jar xxx.jar

# è¥éè¦è®¾ç½®jvmåå­å¤§å°ç­åæ°:
java -Xmx512m -Xms512m -jar xxx.jar

# å¤ç¯å¢å è½½é®é¢
java -jar xxx.jar --spring.profiles.actvie=dev 

# Linux åå°è¿è¡
nohup  java -Xmx512m -Xms512m -jar xxx.jar >nohup.log&

```

## å¦ä½è´¡ç®

éå¸¸æ¬¢è¿ä½ çå å¥ï¼[æä¸ä¸ª Issue](https://github.com/JsckChin/SuBoot/issues) æèæäº¤ä¸ä¸ª Pull Requestã

**Pull Request:**

1. Fork ä»£ç !
2. åå»ºèªå·±çåæ¯: `git checkout -b feat/xxxx`
3. æäº¤ä½ çä¿®æ¹: `git commit -am 'feat(function): add xxxxx'`
4. æ¨éæ¨çåæ¯: `git push origin feat/xxxx`
5. æäº¤`pull request`

## Git è´¡ç®æäº¤è§è

- åè [vue](https://github.com/vuejs/vue/blob/dev/.github/COMMIT_CONVENTION.md) è§è ([Angular](https://github.com/conventional-changelog/conventional-changelog/tree/master/packages/conventional-changelog-angular))

    - `feat` å¢å æ°åè½
    - `fix` ä¿®å¤é®é¢/BUG
    - `style` ä»£ç é£æ ¼ç¸å³æ å½±åè¿è¡ç»æç
    - `perf` ä¼å/æ§è½æå
    - `refactor` éæ
    - `revert` æ¤éä¿®æ¹
    - `test` æµè¯ç¸å³
    - `docs` ææ¡£/æ³¨é
    - `chore` ä¾èµæ´æ°/èææ¶éç½®ä¿®æ¹ç­
    - `workflow` å·¥ä½æµæ¹è¿
    - `ci` æç»­éæ
    - `types` ç±»åå®ä¹æä»¶æ´æ¹
    - `wip` å¼åä¸­
 
