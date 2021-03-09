# 杂项

## 1.Restful 风格

```java
@GetMapping("/test1/{a}/{b}")
public void test1(@PathVariable(value = "a") int a, @PathVariable(value = "b",required = false) int b){
    log.info("这是Restful风格的访问参数：{},{}",a,b);
}
```

