# 杂项

## 1.模板字符串

使用``反引号标识，用${}将变量括起来。

```javascript
//JQuery写法
$("#result").append(
        "He is <b>"+person.name+"</b>"+"and we wish to know his"+person.age+".That is all");
//es6写法 省去了双引号与加号，更加简洁
$("#result").append(
        `He is <b>${person.name}</b>and we wish to know his${person.age}.that is all`
        );
//所有的缩进和换行都会保存在输出中

//引用字符串本身
  let str="return"+"`Hello! ${name}`";
    let func=new Function("name",str);
    console.log(func("zzw"));
```

## 2.Vue入门

```javascript
var data = {site:'aaa'}
new Vue({
    el:'#app',
    data:{
        message:"hello world";
    }
});
```

## 2.1常用指令

### 1.v-bind

用于绑定标签属性的值，如：

```html

<div id="d2">
    <p :class="t1">今晚打老虎</p><!--t1='p1' 绑定t1的值，没有冒号在前值就为t1 -->
    <p :class="'p'+t1">今晚打老虎</p><!--t1='1' 正常拼接  -->
    <p v-bind:class="{'p1':use}">今晚打老虎</p> <!-- use为true可启用p1的样式 -->
    <p :class="[t1,t2]">今晚打老虎</p><!-- 绑定多个样式 -->    
    <!-- 根据条件绑定 -->
    <div :class="{ 'active' : isActive, 'text-danger' : hasError }"></div>
     <!-- 内联模式绑定 -->
    <p :style="{color:c1,fontSize:p1+'px'}">今晚打老虎</p>
    <!-- 内联模式绑定2，在methods使用方法返回，return的大括号不能省略 -->
     <p :style="pvss()">今晚打老虎</p>
    pvss(){return {color:this.c1,fontSize:this.p1+'px'};}
    <!-- 内联模式绑定3，在computed计算并返回，return的大括号不能省略 -->
    <p :style="s2">今晚打老虎</p>
    s2(){return {color:this.c1,fontSize:this.p1+'px'}; }
    <p :class="use?t1:''">今晚打老虎</p><!-- 使用三目运算符 -->
</div>
```

### 2.**computed**属性

使用：与一般的vue变量一样用法，编写方式与方法一样，但是方式是实时调用，而computed是计算好放入页面缓存。

```html
<div id="d2">
    <p :class="'p'+t1">今晚打老虎{{returnMessage1}}</p><!--正常用法-->
    <button @click="pvss()">{{m1}}</button><!-- set+get用法 -->
</div>
```

```javascript
var vue = new Vue({
    el: '#app',
    data: {
        message: "hello world",   
    },
    methods:{//方法
      pvss(){
           this.m1 = '点我'
       }
    },
    computed:{//与方法格式一样的编写
        returnMessage1(){
            return this.message+'，computed';
        },
        m1:{//set+get用法
            set:function (nv){
                this.message = nv+'m1额外';
            },
            get:function (){
                return this.message;
            }
        }
    }

});
```

### 3. watch

用于监听属性

```html
<div>
        千米:<input type="number" v-model="kmeter"><i>{{ms}}</i>
        米:<input type="number" v-model="meter">
</div>
```

```javascript
  data: {
         meter:0,
        kmeter:0, 
    },
 methods:{//方法     
        isNumber(val){
            if (parseFloat(val).toString() == "NaN") {
                return false;
            } else {
                return true;
            }
        }
    },
watch :{
    //写法1，监听值:回调函数
    kmeter:function (val) {//监听kmeter，':function'可以省略
       if(this.isNumber(val)){
           this.ms = '';
           this.kmeter = val;
           this.meter = val/1000;
       }else{
           this.ms = '只能接受数字';
       }
    },
    meter(val) {
        this.meter = val;
        this.kmeter = val*1000;
    }
    //写法二,新增一些属性设置后的写法
     demo1: {
            handler:function (nv,ov){//nv:新的值，ov：旧的值
                this.demo1.name = '创建了'
                alert(this.demo1.name)
            },
            deep:true,//监听对象内部变化需要
            immediate:true//为true后会立即执行handler的方法
        }
}
```

```html
<p>{{demo1}}</p><input type="button" value="点我" @click="demo1.name='李四'">
```

```javascript

vue.$watch('demo1',function (nv, ov) {//监听实例写法，写在new Vue()外面
    vue.demo1.name = '创建了'
    alert(vue.demo1.name);
},{deep:true,immediate:true})
```

### 4. v-on

用于在标签里面绑定事件，可简写为@

```html
<!-- 当需要参数的时候需要用$event传递事件-->
<input type="button" :value="b1" @click="cl1($event)">
<!-- 不需要参数，括号都不用写 -->
<input type="button" :value="b1" @click="cl1">
```

```javascript
cl1(event){
   alert(str);
   this.b1++;
   if(event){//事件对象，可获取标签的相关信息
       alert(event.target.tagName);
   }
}
```

#### 4.1 .stop

```html
<div @click="cl2" style="border: 1px solid red">
    <!-- 加上了.stop之后，点击下面的按钮不会触发父类的点击事件-->
    <input type="button" :value="b1" @click.stop="cl1">
</div>
```

#### 4.2.prevent 

```html
 <!-- 阻止a标签跳转-->
<a href="../page/a.html" @click.stop.prevent="b1++">{{b1}}</a>
 <!-- 阻止表单提交-->
<form action="../page/a.html" method="get" @submit.prevent="cl1">
            <input type="text" name="username">
            <input type="text" name="password">
            <input type="submit" value="登录" >  
</form>
 <!-- 阻止表单提交-->
<input type="submit" value="登录" @click.prevent="cl1">
```

#### 4.3 capture

```html
 <!-- 点击3时，2会优先被执行，顺序2》3》1-->
<div @click="cl2" style="border: 1px solid red" >1
    <div @click.capture="cl3">2
        <div @click="cl4">3</div>
    </div>
</div>
```

#### 4.4 .once

```html
 <!-- 事件只会执行一次-->
<div @click.once="cl4">3</div>
```

#### 4.5 .self

```html
 <!-- 只有2被点击时，才会真正触发事件，不被传播触发，点击2时会正常传播事件-->
<div @click.once="cl2" style="border: 1px solid red" >1
    <div @click.self="cl3">2 
        <div @click="cl4">3</div>
    </div>
</div>
```

#### 4.6 .keyup

- `.enter`
- `.tab`
- `.delete` (捕获“删除”和“退格”键)
- `.esc`
- `.space`
- `.up`
- `.down`
- `.left`
- `.right`

```html
<input type="text"  @keyup.left="cl4"> <!-- 在输入框内按方向键左会触发事件-->
```

### 5.v-model

用于双向绑定元素

```html
<!-- 在输入框绑定，会实时监控输入的数据-->
呵呵:<input type="text" v-model="vm1">
 <p style="font-size: 20px;color: red">{{vm1}}</p>
<!-- 与下拉框绑定，绑定的key代表了下拉框选中的value，改变key则改变选中-->
<select v-model="vm1">
                <option value="1">北京</option><!-- 此时添加selected是无效的-->
                <option value="2">上海</option>
                <option value="3">广州</option>
</select>
<p>{{vm1}}</p>
<!-- 单选框绑定，同上-->
<input type="radio" value="man" name="sex" v-model="vm1">男<!-- 添加checked无效-->
<input type="radio" value="woman" name="sex" v-model="vm1">女
<p>{{vm1}}</p>
<!-- 单个复选框绑定，与boolean绑定 选中表示true，未选中表示false-->
<input type="checkbox" v-model="vm1" value="c1">
<!-- 多个复选框绑定数组，checkbox要有value，否则选中一个会全部选中，且值为null
当，数组中存在值跟checkbox的值相同时，则表示checkbox被选中，不存在相同的值时则未被选中
-->
<input type="checkbox" v-model="vm2" value="c1">
<input type="checkbox" v-model="vm2" value="c2">
<input type="checkbox" v-model="vm2" value="c3">
<p>{{vm2}}</p>
<!-- 全选、全不选、反选-->
<input type="checkbox" v-model="c1" value="c1" @click="c11">全选
        <input type="checkbox" v-model="c2" value="c2" @click="vm2.splice(0,vm2.length)">全不选
<input type="checkbox" v-model="c3" value="c3" @click="c31">反选
<ul>
    <li><input type="checkbox" value="1" v-model="vm2"></li>
    <li><input type="checkbox" value="2" v-model="vm2"></li>
    <li><input type="checkbox" value="3" v-model="vm2"></li>
    <li><input type="checkbox" value="4" v-model="vm2"></li>
    <li><input type="checkbox" value="5" v-model="vm2"></li>
</ul>
<p>{{vm2}}</p>
```

```js
c11(){//全选
    for (let i = 0; i < 5; i++) {
        if(this.vm2.indexOf(i+1)===-1){
            this.vm2[i]=i+1+'';
        }
    }
},
c31(){//反选
    for (let i = 0; i < 5; i++) {
        let value = i+1+'';
        let index = this.vm2.indexOf(value);
        if(index===-1){//不存在
            this.vm2.push(value);
        }else{//存在
            this.vm2.splice(index,1);
        }
    }
}
```

#### 5.1 .lazy

```html
<!-- 会在失去焦点时或按回车键时才会更新值-->
<input type="text" v-model.lazy="la1">
```

#### 5.2 .number

```html
<!-- 会自动过滤非数字的东西-->
<input type="text" v-model.number="la1">
```

#### 5.3 .trim

```html
<!-- 会自动过滤前后的空格-->
<input type="text" v-model.trim="la1">
```

## 2.2 判断与循环

```javascript
//判断
<div v-if="enable">
        今晚去哪里玩
    </div>
    <div v-else-if="false">
        v-else-if
    </div>
    <div v-else>
        今晚不去玩
    </div>
//循环，迭代数组
<h1 v-for="p in provinces">
            {{p}}
 </h1>
//迭代数字
 <h1 v-for="p in 10">
            {{p}}
 </h1>
//迭代对象
<h1 v-for="p in user">
            {{p}}
        </h1>
//迭代对象时输出对象的属性名，第一个为value，第二个为属性名，
//遍历的对象为数组时第二个参数为索引
  <h1 v-for="(v,k) in user">
            {{k}}:{{v}}
  </h1>
//第三个为索引
 <h1 v-for="(v,k,i) in user">
            {{k}}:{{v}}:{{i}}
        </h1>
//输出九九乘法表
 <h1 v-for="(v) in 9">
            <b v-for="m in v">
                {{m}}*{{v}}={{m*v}};
            </b>
        </h1>
//数据
var vue = new Vue({
    el: '#app',
    data: {
        message: "hello world",
        enable: false,
        provinces:['aaa','bbb','ccc','ddd'],
        user:
            {'name':'jimmy','password':'123',
                'accounts':['aaa','bbb']} ,
    },
     methods:{//方法
        aaa(){
            alert(`${this.message}`)
        }
    },
    created:function (){//钩子函数
        this.aaa();
    }
});
```

## 2.3 组件

必须写在new Vue的前面，不然报错。

因为组件是可复用的 Vue 实例，所以它们与 `new Vue` 接收相同的选项，例如 `data`、`computed`、`watch`、`methods` 以及生命周期钩子等。仅有的例外是像 `el` 这样根实例特有的选项。

```js
//基础写法
Vue.component('ass', {
    data()  {
        return {count : 0,hehe : 'lisi'}
    },
    template: '<button v-on:click="count++">You clicked me {{ count + hehe }} times.</button>'
});
//与正常的Vue一样的写法，只是data必须是个方法。
Vue.component('ass', {
    data()  {
        return {count : 0,hehe : 'lisi'}
    },
    computed:{
        r1(){
            return '今晚打老虎';
        }
    },
    methods:{
        m1(){
            this.count++;
        }
    },
    watch:{
        count(nv,ov){
            alert(`旧值:${ov},新值:${nv}`)
        }
    },
    props:['title','myName','fuck'],//使用要赋值
    template: '<button @click="m1()">{{count + r1 +title+myName+fuck}}</button>'
});

//Vue组件自定义属性用法
<blog-post
  v-for="post in posts"
  v-bind:key="post.id"
  v-bind:post="post"
></blog-post>
//将post作为attribute接受一个对象
Vue.component('blog-post', {
  props: ['post'],
  template: `
    <div class="blog-post">
      <h3>{{ post.title }}</h3>
      <div v-html="post.content"></div>
    </div>
  `
})
```

#### 1.$emit

作用：通过子组件触发父组件事件

```js
//$emit("FunctionName",[arg1,arg2...]) 或 $emit("FunctionName") 

//自组件
methods:{
        m1(){
            this.count++;
            this.$emit('aaa');//如果需要传参数，直接写在后面
        }
    }
 template: '<button @click="m1">{{count + r1 +title+myName+fuck}}</button>'
//html
<ass title="a" fuck="b" my-name="c" @aaa="cl5"></ass>
//父组件
methods:{
        cl5(){//如果有参数，那么根据上emit传过来的顺序。
            ...
        }
    }
```

##### 1.1 点我变大案例

```html
<div id="app">
    <div :style="{fontSize:fs+'px'}">
        <aaa @bs-text="fs+=1"></aaa>
    </div>
</div>
```

```js
Vue.component('aaa',{
    template:`
    <div>
    <p >fantasy</p>
    <button @click="$emit('bs-text')">点我变大</button>
    </div>
    `
})
new Vue({
   el:'#app' ,
    data:{
       fs:30,
    }
});
```

#### 2.在组件上使用v-model

```html
<input v-model="searchText"><!-- 等价于下面-->
<input
  v-bind:value="searchText"
  v-on:input="searchText = $event.target.value"
>
<!-- 具体实现-->
Vue.component('custom-input', {
  props: ['value'],
  template: `
    <input
      v-bind:value="value"
      v-on:input="$emit('input', $event.target.value)"
    >
  `
})
```

## 2.4 插槽

用于在自定义组件里面插入内容，如下

```html
<div id="app">
    <aaa>dddd</aaa><!--没有定义slot是不允许插入任何东西的-->
</div>
```

```js
Vue.component('aaa',{
    template:`
    <div>
    <slot></slot>
    </div>
   `
})
```

### 1 . 具名插槽

当组件有多个地方需要插槽时，需要对插槽进行命名区分，不命名则默认为default

```js
 Vue.component('aaa',{
        template:`
        <div>
        <header>
        <slot name="header"></slot>
</header>
        <main>
        <slot name="default"></slot>
</main>
        <footer>
        <slot name="footer"></slot>
</footer>
        </div>
       `
    })
```

```html
<aaa><!--使用的时候，需要使用template标签，且指定插槽名称-->
    <template v-slot:header>
            <p>头部</p>
        </template>
        <p >主要内容</p>
        <template v-slot:footer>
            <p>尾部</p>
        </template>
</aaa>
```

### 2. 作用域插槽

插槽内的数据访问，只能访问到父元素，如下：

```html
<aaa>
    <template v-slot:header>
        <p>头部{{user.name}}</p><!--访问的是父级的数据-->
    </template>
</aaa>

<!--首先将要展示的数据当成attribute注入，如下格式-->
<slot name="header" :user="user"></slot>
<!--使用的时候在具名插槽的基础上加个='xxx',名字随意,然后使用如下格式-->
<aaa>
        <template v-slot:header="a">
            <p>头部{{a.user.name}}</p>
        </template>
</aaa>
<!--或者用以下写法-->
<template v-slot:header="{user}">
            <p>头部{{user.name}}</p>
        </template>
<!--或者用以下写法-->
        <template #header="{user:person}"><!--没名字：#default-->
            <p>头部{{user.name+person.name}}</p>
        </template>
```

## 2.5 axios

引入axios.js

```js
<script src="../js/axios.js"></script>
```

#### 1.post

```js
//发送数据，数据拼接在路径后面
axios
    .post(`/ssm/user/login`,u)
    .then(response=>{
        this.message = response.data;
        alert(this.message.code+','+this.message.message);
    }).catch((error)=>{
        alert(error);
})
```