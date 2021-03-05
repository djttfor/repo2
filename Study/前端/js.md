# 杂项

引入js文件

<script src="xxx.js"></script>

严格检查模式：在js文件或在script标签里声明 ’use strict’,必须写在第一行。

## 1.数组的方法

indexOf（var）同string的indexOf，找不到返回-1

slice（）同string 的substring（）

push（）压入到尾部

pop（）弹出尾部的一个元素

unshift（）压入到头部

shift（）弹出头部的一个元素

sort（arr）正序排序

reverse（arr）反转

concat（arr）接受一个数组拼接在后面并返回新的数组，旧数组不影响

join（string）把各个元素用传入的字符拼接起来

## 2.对象

动态的删减属性：delete user.name ;删除成功返回true

动态的添加：user.address = xxx ;

判断属性是否存在这个对象中 ： ‘name’ in user ; 

## 3.循环

```javascript
for (let i = 0; i < arr.length; i++) {//1
    console.log(arr[i]);
}
for (let arrKey in arr) {//2.遍历索引，不是遍历值
    console.log(arrKey);
}
for (let number of arr) {//3
    console.log(number);
}
arr.forEach((k,v)=>{//第一个是值，第二个是索引
    alert(k+v);
});
```

## 4.常用对象

判断类型：typeof 123  --- ‘number’

### 4.1 时间相关：

let now = new Date();

getFullYear() 年

getMonth() 月 0-11

getDate() 日

getDay() 星期几 ，返回数字

getMinutes() 分

getSeconds() 秒

getMilliseconds()毫秒

getTime() 时间戳

### 4.2 JSON

var user = {name:'33',age:5,address:'北京'};

对象转JSON

var a = JSON.stringify(user);

JSON转对象

JSON.parse('{"name":"33","age":5,"address":"北京"}');

## 5.BOM操作

bom: Browser Object Model 浏览器对象模型

window 代表了浏览器串口

navigator 封装了浏览器的信息

screen 代表屏幕尺寸

location 代表当前页面的URL信息

document 代表当前的页面

history ：history.back()退回之前的页面，history.forword()前进

## 6.DOM操作

dom：document Object model 文档对象模型

