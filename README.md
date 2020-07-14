## Springboot2 - FASHION FEED 
패션피드 웹 사이트를 구현한다.

### build
~~~
Build tool: Gradle(6.2.2 ver)
JDK: 1.8
Framework: Spring Boot2, MVC, JPA (2.2.5.RELEASE ver)
DB: H2
etc: 
1. lombok : (getter, setter 및 build 방식 설정을 위해서 적용)
2. ModelMapper : (엔티티를 Dto로 변환시키는데에 사용하기 위해 적용, ex) Page<Feed> --> Page<FeedResponseDto>)
3. jackson : 데이터 바인딩을 위해서 추가
4. mustache 템플릿 : View와 Controller 단의 더 명확한 구분을 위해 사용 (jsp보다 더 쉬운 사용환경이라 생각)
~~~
<br>

### DataBase 테이블 스키마

(데이터 구조 변경사항 있음, 테이블 스키마는 수정하지 못함. <br>
(대표적으로 Feed에 feedContent 추가되었고, Chat, Likefull, FreeShare에 userId 컬럼이 추가됌)
![image](https://user-images.githubusercontent.com/47850258/77083817-6e0d3800-6a41-11ea-9549-da41345695c7.png)
<br>


### 개선해야할 사항 

>> 1. 페이징 기능이 완전하지 못함. <br>
이전, 다음 버튼에 대한 기능 추가하지 못함 개선중<br>
>> 2. 댓글의 업데이트 기능 구현 못함 <br>
RestController 까지는 완성한 상태이다. <br>
UI부분을 어떻게 할지가 고민이다. <br>

#### test 데이터를 입력하는 기능을 추가했습니다. ( /test uri를 입력하면 20개의 Feed 데이터가 저장됩니다.) 

### 현재 진행사항 
소셜 로그인 기능 추가작업 진행중 (OAuth2.0 설정 적용) - 2020.07.14 

## 실제 구동 화면 

### 1. test를 위한 데이터 추가 
<br>

![test 데이터추가](https://github.com/rok93/TIL/raw/master/gif_folder/1.test%20생성.mov.gif)

### 2. 로그인 기능
<br>

![로그인 기능](https://github.com/rok93/TIL/raw/master/gif_folder/2.로그인기능.mov.gif)

### 3. 피드상세화면기능
<br>

![피드 상세화면 기능](https://github.com/rok93/TIL/raw/master/gif_folder/3.피드상세화면기능.mov.gif)

### 4. 피드 좋아요 기능 
<br>

![피드 좋아요 기능](https://github.com/rok93/TIL/raw/master/gif_folder/4.피드%20좋아요기능.mov.gif)

### 5. 공유하기 기능
<br>

![공유하기 기능](https://github.com/rok93/TIL/raw/master/gif_folder/5.공유하기기능.mov.gif)

### 6. 댓글 저장 기능
<br>

![댓글 저장 기능](https://github.com/rok93/TIL/raw/master/gif_folder/6.댓글%20저장기능.mov.gif)

### 7. 댓글 삭제 기능
<br>

![댓글 삭제 기능](https://github.com/rok93/TIL/raw/master/gif_folder/7.댓글%20삭제%20기능.mov.gif)

### 8. 페이지 기능 
<br>

![페이지 기능](https://github.com/rok93/TIL/raw/master/gif_folder/8.페이지%20기능%20.mov.gif)



