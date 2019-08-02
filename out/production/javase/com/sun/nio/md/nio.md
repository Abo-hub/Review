[toc]

# NIO

## 1. Java NIO简介
Java NIO(New IO Non-Blocking IO(非阻塞式))是从Java1.4版本开始引入的新的IO API，可以替代标准的Java IO API。NIO与原来的IO有同样的作用和目的，但是使用的方式完全不同，NIO支持面向缓冲区的、基于通道的IO操作。NIO将以更加高效的方式进行文件的读写操作。

## 2. Java NIO 与 IO 的主要区别
IO  |   NIO
-- | --
面向流(Stream Oriented) | 面向缓冲区(Buffer Oriendted)
阻塞IO(Blocking IO) | 非阻塞IO(Non blocking IO)
无 | 选择器(Selectors)

## 3. 缓冲区(buffer)和通道(Channel)
Java NIO系统的核心在于：通道(Channel)和缓冲区(Buffer)。通道表示打开到IO设备(例如：文件、套接字)的连接。若需要使用NIO系统，需要获取用于连接设备的通道以及用于容纳数据的缓冲区。然后操作缓冲区，对数据进行处理。总的来说，Channel负责传输，Buffer负责存储.

### 3.1 缓冲区
在Java NIO中负责数据的读取，缓冲区就是数组，用于存储不同类型的数据
根据数据类型的不同(boolean除外),提供了相应类型的缓冲区：
![nio](../../../../../../imgs/nio.png) 


### 3.2 缓冲区中的四个核心属性
- mark(标记)：记录当前position位置，可以通过reset()恢复到mark的位置
- position(位置)：表示缓冲区正在操作数据的位置
- limit(界限)：表示缓冲区中可以以操作数据的大小。(limit 后数据不能进行读写)
- capacity(容量)：表示缓冲区中最大存储数据的容量，一旦声明不能改变。

Invariants: mark <= position <= limit <= capacity

上述缓冲你去的管理方式几乎一致，通过allocate()获取缓冲区。

### 3.3 方法使用
![niobuffer](../../../../../../imgs/niobuffer.png)

### 直接缓冲区与非直接缓冲区
- 非直接缓冲区：通过allocate()方法分配缓冲区，将缓冲区简历在JVM的内存中
- 直接缓冲区：通过allocateDirect() 方法分配在直接缓冲区，将缓冲区简历在屋里内存中，可以提高效率。

- 字节缓冲区要么是直接的，要么是非直接的。如果为直接字节缓冲区，则Java虚拟机会尽最大努力在此缓冲区上执行本机I/O操作。也就是说，在每次调用基础操作系统的一个I/O操作之前(或之后)，虚拟机都会尽量避免将缓冲区的内容复制到中间缓冲区中(或从中间缓冲区中复制内容)。
- 直接字节缓冲区可以通过调用此类的**allocateDirect()工厂方法**来创建。此方法返回**缓冲区进行分配和取消分配所需成本通常高于非直接缓冲区**。直接缓冲区的内容可以驻留在常规的垃圾回收堆之外，因此，它们堆应用程序的内存需求造成的影响可能并不明显。所以，建议将直接缓冲区主要分配给那些易受基础系统本机I/O操作影响的大型、持久的缓冲区。一般情况下，最好仅在直接缓冲区能在程序性方面带来明显好处时分配它们。
- 直接字节缓冲区还可以通过FileChannel的map()方法将我呢见区域直接映射到内存中来创建。该方法返回**MappedByteBuffer**。java平台的实现有助于通过JNI从本机代码创建直接字节缓冲区。如果以上这些缓冲区中的某个缓冲区实例指的是不可以访问的内存区域，则驶入访问该区域不会更改该缓冲区的内容，并且将会在访问期间或稍后的某个时间导致抛出不确定的异常。
- 字节缓冲区是直接缓冲区还是非直接缓冲区可通过调用器**isDirect()** 方法来确定。提供此方法是为了能够在性能关键型代码中显式缓冲区管理。
#### 非直接缓冲区
![nobuf](../../../../../../imgs/nobuf.png)
##### 源码解析
```java
    public static ByteBuffer allocate(int capacity) {
        if (capacity < 0)
            throw new IllegalArgumentException();
        return new HeapByteBuffer(capacity, capacity);
    }
``` 
这里我们可以看到申请的是堆空间，我们再点进去
```java
    HeapByteBuffer(int cap, int lim) {            // package-private

        super(-1, 0, lim, cap, new byte[cap], 0);
        /*
        hb = new byte[cap];
        offset = 0;
        */
        
    }
```
从这里我们可以看到实际上是 byte数组

#### 直接缓冲区
![nobuf](../../../../../../imgs/zhibuf.png)


##### 源码解析
通过allocateDirect方法 申请直接缓冲去
```java
    public static ByteBuffer allocateDirect(int capacity) {
        return new DirectByteBuffer(capacity);
    }
``` 
我们再看DirectByteBuffer的构造方法
```java
DirectByteBuffer(int cap) {                   // package-private

        super(-1, 0, cap, cap);
        boolean pa = VM.isDirectMemoryPageAligned();
        int ps = Bits.pageSize();
        long size = Math.max(1L, (long)cap + (pa ? ps : 0));
        Bits.reserveMemory(size, cap);

        long base = 0;
        try {
            base = unsafe.allocateMemory(size);
        } catch (OutOfMemoryError x) {
            Bits.unreserveMemory(size, cap);
            throw x;
        }
        unsafe.setMemory(base, size, (byte) 0);
        if (pa && (base % ps != 0)) {
            // Round up to page boundary
            address = base + ps - (base & (ps - 1));
        } else {
            address = base;
        }
        cleaner = Cleaner.create(this, new Deallocator(base, size, cap));
        att = null;
        
    }
```
可以看到unsafe.allocateMemory(size)，已经不能进入源码了，它已经是操作系统层面的jni调用了。

## 4. 文件通道(FileChannel)
由java.nio.channels包定义的。Channel表示IO源与目标打开的连接。Channel类似于传统的“流”。只不过Channel本身不能直接访问数据，Channel只能于buffer进行交互。（类似于铁轨，缓冲区类似于火车） 

### 4.1 主要通道的实现类
- FileChannel
- SocketChannel
- ServerSocketChannel
- DtagramChannel

### 4.2 获取通道
1. Java 针对支持通道的类提供了getChannel()方法
    - 本地IO：
        - FileInputStream / FileOutputStream
        - RandomAccessFile
    - 网络IO：
        - Socket
        - ServerSocket
        - DatagramSocket
2. 在JDK 1.7 中的NIO 2 针对各个通道提供了静态方法open()
3. 在JDK 1.7 中的NIO 2 的Files 工具类的newByteChannel()

### 4.3 通道之间的数据传输
- transferFrom()
- rransferTo()

### 4.4 分散(Scatter)与聚集(Gather)
- 分散读取(Scattering Reads): 将通道中的数据分散到多个缓冲区中
- 聚集写入(Gathering Writers):  将多个缓冲区的数据聚集到通道中

### 4.5 字符集
- 编码：字符串 -> 字符数组
- 解码：字符数组 -> 字符串






## 5. NIO 的非阻塞式网络通讯

###  选择器(Selector)

### SocketChannel、ServerSocketChannel、DatagramChannel

## 6.管道(Pipe)

Java NIO2(Path、Paths与Files)

参考：   
    [1] https://my.oschina.net/oosc/blog/1627362