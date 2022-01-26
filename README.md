## 패스트 캠퍼스 강의 학습 repository


#### 강의 자료 원본 : https://github.com/steve-developer/fastcampus-springboot-introduction

#### 강의 내용 정리
https://github.com/JyuRon/SpringBootExample/blob/master/Ch04%20Rest%20Api%2C%20ObjectMapper.txt
https://github.com/JyuRon/SpringBootExample/blob/master/Ch05%20Aop%2C%20Di.txt
https://github.com/JyuRon/SpringBootExample/blob/master/Ch06%20Fiter%2C%20Interceptor.txt
https://github.com/JyuRon/SpringBootExample/blob/master/Ch06%20Validation%2C%20Exception.txt
https://github.com/JyuRon/SpringBootExample/blob/master/Ch09%20Swagger.txt
https://github.com/JyuRon/SpringBootExample/commits/master


#### * 학습내용
<pre><code>
REST API에 대한 개념과 이를 직접 구현해 보는 실습을 진행하였다.
Controller를 구현하면서 @PathVariable, @RequestBody, @RequestParam 등 clinet가 요청하는 값을 
어떤 방법으로 받는지를 주로 알아보았다.

또한 Controller에서 다양한 Annotation으로 JSON <-> Object 로 convert하는 과정을 알아서 해주는 모습을 볼 수 있는데
ObjectMapper를 활용하여 직접 json을 파싱하는 방법 또한 실습을 진행

Ioc, Di 를 학습함으로써 Spring의 기본 동작 원리를 학습
Aop를 활용하여 관점 지향 즉 로깅, request 정보의 사전 복호화 작업등을 어떠한 방법으로 처리하는 지 학습하였다.

Validation를 활용하여 request 정보 등을 controller, service layer에서 직접 구현 하는 것이 아닌
Annotaion 등으로 처리하여 특정 layer 개발에만 집중 할 있도록 개발을 진행

Validation 등을 처리하면서 다향한 Exception 이 발생하였는데 이를 ExceptionHandler를 활용하여
다양한 에러를 처리 하는 방법까지 실습을 진행하였다.


Filter에 대한 개념을 학습하면서 가장 큰 특징은 doFilter를 통해 controller에서 작업을 처리하기 전 후의 모든 과정을
처리 할 수 있다.
request, response 의 body 내용제공이 stream 형식으로 한번 읽으면
controller, client가 받는 body 는 null 인 현상을 ContentCachingRequestWrapper 등으로 해결



Interceptor를 학습하면서 이전 Filter에서 학습한 request body 내용을 caching 을 하는 과정을 진행하였다.
이를 활용하여 Interceptor 에서 body 내용을 찍어보는 작업을 진행해 보았고 
annoation, URI 별로 선택적으로 동작할 수 있게 설정하는 작업을 진행



RestTemplate를 활용하여 타 서버에서 제공하는 API 정보를 가져와 활용하는 방법을 알아보았다.
그 후 위의 배운 모든 내용을 활용하여 네이버 지역검색 API 를 이용한 맛집리스트 서비스를 
구현하는 과정을 진행
</code></pre>
