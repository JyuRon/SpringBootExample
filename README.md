ch04. 스프링 부트 시작하기

@Controller : html파일의 정보를 반환한다.
@ResponseBody : Controller에서 json형태로 반환하기 위해 주로 사용(ResponseEntity도 사용가능)
@RestController : 대게 api 즉 json파일로 반환할때 사용한다.
            Controller인데 @ResponseBody 자동으로달아주겠다.