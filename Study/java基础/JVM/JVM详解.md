# JVM

# JVM内存模型

Java1.8的内存模型详解](https://www.processon.com/view/5ea7a1b9e401fd21c196eb17)

## 1.JVM的定义

1. 基本概念

   Java虚拟机可以看做是一台抽象的计算机，如同真实的计算机那样，它有自己的指令集以及各种运行时内存区域，它与Java语言没有必然的联系，只与特定的二进制文件——class 文件格式关联（字节码文件），可以通过Java语言或者其他语言编写的程序编译成class文件，然后在Java虚拟机上运行。

2. Java虚拟机有以下两个特点：

   1. 语言无关

      Java虚拟机只和class文件关联，所以只要你编写程序的语言能够编译成class文件，那么都能够在Java虚拟机上运行。

   2. 平台无关

      Java从诞生之初就宣传的一个口号：一次编写，到处运行。

      也就是说Java是一个跨平台的语言，那么Java是如何实现跨平台的呢？

      其实Java之所以跨平台是因为Java虚拟机的适配，不同的系统实现不同的Java虚拟机。Java虚拟机就相当于操作系统和应用程序之间的中介，每种平台安装适应该平台的Java虚拟机，那么我们编写的程序当然能够在任意平台运行

    

## 2.运行时数据区域

通过一张图来查看java程序的执行流程

![image-20210222155349824](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\image-20210222155349824.png)

### 2.1 程序计数器（Program Counter Register）

**程序计数是一块较小的内存空间，它可以看作是当前线程所执行字节码的行号指示器**。字节码解释器工作时就是通过改变这个计数器的值来选取下一条需要执行的字节码指令，它是程序控制流的指示器，分支、循环、跳转、异常处理、线程恢复等基础功能都需要依赖这个计数器来完成。

线程私有，即各条线程之间计数器互不影响，独立存储。同时此内存区域是唯一一个在《Java虚拟机规范》中没有规定任何OutOfMemoryError情况的区域。

### 2.2 Java虚拟机栈（Java Virtual Machine Stack）

线程私有，生命周期与线程相同。-Xss

虚拟机栈描述的是Java方法执行的线程内存模型：每个方法被执行的时候，Java虚拟机都会同步创建一个栈帧（Stack Frame）用于存储***局部变量表、操作数栈、动态连接、方法出口***等信息。每一个方法被调用直至执行完毕的过程，就对应着一个栈帧在虚拟机栈中从入栈到出栈的过程。

局部变量表存放了编译期可知的各种Java**基本数据类型**（boolean、byte、char、short、int、float、long、double）、**对象引用类型**（reference类型，它并不等同于对象本身，可能是一个指向对象起始地址的引用指针，也可能是指向一个代表对象的句柄或者其他与此对象相关的位置）和**returnAddress**类型（指向了一条字节码指令的地址）。

当线程请求的栈深度大于虚拟机所允许的深度，将抛出StackOverFlowError异常；当Java虚拟机栈容量可以动态扩展，当栈扩展时无法申请到足够的内存会抛出 OutOfMemoryError 异常

### 2.3 本地方法栈 （Native Method Stack）

本地方法栈是为虚拟机使用到的本地方法服务。

同Java虚拟机栈一样，会抛出 StackOverFlowError 异常和 OutOfMemoryError 异常。

HotSpot将**Java虚拟机栈和本地方法栈**合二为一；

### 2.4 Java堆（Heap）

此内存区域唯一目的就是存放对象实例，几乎所有的对象实例都在这里分配内存。

线程共享。也叫GC堆，是垃圾回收器作用的主要区域

内存布局：经典分代设计
新生代：**Eden空间、From Survivor空间、to Survivor空间**
老年代

分配内存的角度可以从java堆划分出多个线程私有的缓存区（Thread Local Allocation Buffer，TLAB）以提升对象分配的效率。

无论堆内存怎样划分，其目的都是为了更好地回收内存，或者更快的分配内存

Java堆可以处在物理上不连续的内存空间中，但在逻辑上它应该被视为连续的，这点就像我们用磁盘空间去存储文件一样，并不要求每个文件都连续存放。但对于大对象，如数组，多数虚拟机实现处于实现简单、存储高效的考虑，很可能会要求连续的内存空间。

Java堆即可以被实现为固定大小的，也可以是可拓展的，可以通过-Xmx和-Xms设定，如果Java堆中没有内存完成实例分配，并且堆也无法在拓展是，Java虚拟机将会抛出 OutOfMemoryError异常。

### 2.5 方法区 （Method Area）

线程共享，用于存储已被虚拟机加载的**类型信息、常量、静态变量、即时编译器编译后的代码缓存等数据**

永久代：
内存布局跟堆一样居于经典分代设计，使用永久代来实现方法区，是为了能让垃圾回收期能够像管理Java堆一样管理方法区。
设置上限：-XX：MaxPermSize 
JDK7将永久代的**字符串常量池、静态变量**等移出，到了JDK8彻底废弃永久代，将永久代剩余的内容，主要是**类型信息**全部移动到元空间

该地方存在垃圾回收，是针对常量池的回收以及类型的卸载，回收效果不理想。

方法区无法满足新的内存分配需求时，会抛出OutOfmemoryError异常

**方法区在JDK8被元空间取代，元空间不在虚拟机中，而存在于直接内存中**

### 2.6 运行时常量池 （Runtime Constant Pool）

是方法区的一部分。Class文件中除了有**类的版本、字段、方法、接口、等描述信息外**，还有一项信息是**常量池表**（Constant Pool Table）,**用于存放编译期生成的各种字面量与符号引用**，这部分内容将在类加载后存放到方法区的运行时常量池中。

总之就是存放字面量、符号引用以及符号引用翻译出来的直接引用。

　通常来说，该区域除了保存Class文件中描述的引用外，还会把翻译出来的直接引用也存储在运行时常量池，并且Java语言并不要求常量一定只能在编译器产生，运行期间也可能将常量放入池中，比如String类的intern()方法，**当调用intern方法时，如果池中已经包含一个与该`String`确定的字符串相同`equals(Object)`的字符串，则返回该字符串。否则，将此`String`对象添加到池中，并``返回此对象的引用**

**字符串常量池在JDK1.7的时候被移到了堆中**

### 2.7 直接内存（Direct Memory）

直接内存（Direct Memory）并不是虚拟机运行时数据区的一部分，它也不是Java虚拟机规范定义的内存区域。我们可以看到在 HotSpot 中，就将方法区移除了，用元数据区来代替，并且将元数据区从虚拟机运行时数据区移除了，转到了本地内存中，也就是说这块区域是受本机物理内存的限制，当申请的内存超过了本机物理内存，才会抛出 OutOfMemoryError 异常。

　　直接内存也是受本机物理内存的限制，在JDK1.4中新加入的 NIO（new input/output）类，引入了一种基于通道（Channel）与缓冲区（Buffer）的 I/O 方式，它可以使用 Native 函数库直接分配堆外内存，然后通过一个存储在Java堆里面的 DirectByteBuffer 对象作为这块内存的引用操作，这样避免了在Java堆和Native堆中来回复制数据，显著提高性能。