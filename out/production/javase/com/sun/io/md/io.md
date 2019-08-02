[toc]
# Java I/O流

<img src="https://github.com/SGEEKioi/Review/blob/master/imgs/JavaIO.png?raw=true">
## 一、 Java IO 原理
- 输入 input：读取外部数据(磁盘、光盘等存储设备的数据)到程序(内存)中。
- 输出 output：将程序(内存)数据输出到磁盘、光盘等存储设备中。

    
## 二、 流的分类
- 按操作**数据单位**不同分为：字节流(8 bit)，字符流(16 bit)
- 按数据流的**流向**不同分为：输入流，输出流
- 按流的**角色**的不同分为：节点流，处理流
 
[抽象基类] | 字节流 | 字符流
|--- | ---| --- |
输入流 | InputStream | Reader
输出流 | OutputSreeam | Writer

Java的IO流共设计40多个类，实际上非常规则，都是从以上4个抽象基类派生的。

由这四个类派生出来的子类名称都是以其父类名作为子类名后缀。 

**InputStream 和 Reader 是所有输入流的基类**
### 2.1 InputStream (典型实现：**FileInpugStream**)

- int read()
在输入流中读取数据的下一个字节。返回0-255范围内的int字节值。如果因为已经到达流末尾而没有可用的字节，则返回-1.
- **int read(byte[])**
从此输入流中将最多b.length个字节的数据读入一个byte数组中。如果因为已经到达流末尾而没有可用的字节，则返回值为-1。否则**以整数形式返回时机读取的字节数** 
- int read(byte[],int off,int len) 
将输入流中最多len个数据字节读入byte数组。尝试读取len个字节，但读取的字节也可能小于该值。以整数形式返回实际读取的字节数。如果因为流位于文件末尾而没有可用的字节，则返回值为-1.   
- FileInputStream 从文件系统中的某个文件中获得输入字节。FileInputStream用于读取非文本数据之类的原始字节流。要读取字符流，需要使用FileReader。

### 2.2 Reader (典型实现：**FileReader**)
- int read()
读取单个字符。作为整数读取的字符，范围在0-65535之间(0x00-0xffff)(2个字节的Unicode码),如果已到达流的末尾，则返回-1
- **int read(char[] c)**
将字符读入数组。如果已到达流的末尾，则返回-1.否则返回本次读取的字符数
- int read(char[] c,int off,int len)
将字符读入数组的某一部分。存到数组cbuf中，从off处开始存储，最多读len个字符。如果已到达流的末尾，则返回-1。否则饭hi本次读取的字符数。

**OutputStream和Writer 是所有输出流的基类**
### 2.3 OutPutStream
- void write(int b)
将指定的字节写入此输出流。write 的常规协定是：向输出流写入一个字节。要写入的字节是参数 b 的八个低位。b 的 24 个高位将被忽略。即写入0~255范围的。
- void write(byte[] b) 
将 b.length 个字节从指定的 byte 数组写入此输出流。write(b)的常规协定是：应该 与调用 write(b, 0, b.length) 的效果完全相同。
- void write(byte[] b,int off,int len)
 将指定 byte 数组中从偏移量 off 开始的 len 个字节写入此输出流。 

### 2.4 Writer
 - void write(int c)
写入单个字符。要写入的字符包含在给定整数值的 16 个低位中，16 高位被忽略。即 写入0 到 65535 之间的Unicode码。
- void write(char[] cbuf) 
写入字符数组。
- void write(char[] cbuf,int off,int len)
 写入字符数组的某一部分。从off开始，写入len个字符
- void write(String str) 
写入字符串。
- void write(String str,int off,int len) 
写入字符串的某一部分。
- void flush() 
刷新该流的缓冲，则立即将它们写入预期目标。 


程序中打开的文件IO资源不属于内存里的资源，垃圾回收机制无法回收该资源，所以应该**显式关闭文件IO资源**

## 三、节点流(或文件流)
直接从数据源或目的地读写数据

#### 3.1读取文件
```java
//1.建立一个流对象，将已存在的一个我呢见加载进流
FiileReader fr = new FileReader(new File("Test.txt"));

//2.创建有一个临时存放数据的数组
char[] ch = new char[1024];

//3.调用流对象的读取方法将流中的数据读入到数组中。
fr.read(ch);

//4.关闭资源
fr.close();

```

```java
FileReader fr = null;
try { 
    fr = new FileReader(new File("test.txt"));
    char[] buf = new char[1024]; 
    int len; 
    while ((len = fr.read(buf)) != -1) {
        System.out.print(new String(buf, 0, len)); 
    }
} catch (IOException e) { 
    System.out.println("read-Exception :" + e.getMessage());
} finally { 
    if (fr != null) { 
        try { 
            fr.close(); 
        } catch (IOException e) { 
            System.out.println("close-Exception :" + e.getMessage();       } 
    }
}
``` 
#### 3.2 写入文件
```java

//1. 创建流对象，建立数据存放文件
FileWriter fw = new FileWriter(new File("Test.txt"));

//2.调用流对象的写入方法，将数据写入流
fw.write("I hava a dream");

//3.关闭流资源
fw.close();
```

```java
FileWriter fw = null;
try{
    fw = new FileWrtier(new File("Test.txt"));
    fw.write("I hava a dream");
} catch (IOException e){
    e.printStackTrace();
} finally {
    if (fw != null){
        try{
            fw.close();
        }catch (IOException e){
            e.printStackTrace();
        }    
    }
}
```

#### 3.3 注意事项
- 定义文件路径时，注意：可以用“/”或者“\\”
- 在**写入**一个文件时，如果使用构造器FileOutputStream(file),则**目录下有同名文件将被覆盖**
- 如果使用构造器FileOutputStream(file,true),则目录下的同迷宫文件不会被覆盖，**在文件内容末尾追加内容**
- 在**读取**文件时，必须保证该文件已存在，否则报异常
- 字节流操作字节，比如：.mp3、.avi、.mp4、.jpg、.doc等
- 字符流操作字符，只能操作普通文本文件。最常见的文本文件：txt、java、c、cpp等语言的额源代码。尤其注意doc、excel、ppt这些不是文本文件


## 四、处理流
不直接连接到数据源或目的地，而是“连接”在已存在的流(节点流或处理流)之上，通过对数据的处理为程序 提供更为强大的读写功能。
### 4.1 缓冲流(处理流之一)
- **为了提高数据读写的速度**，Java API提供了带缓冲功能的流类，在使用这些流类时，会创建一个内部缓冲区数组，缺省使用 ==8192个字节(8kb)的缓冲区==

![buffer](https://github.com/SGEEKioi/Review/blob/master/imgs/buffer.png?raw=true)
```java
public
class BufferedInputStream extends FilterInputStream {

    private static int DEFAULT_BUFFER_SIZE = 8192;
```  
- 缓冲流要“套接”在相应的节点流之上，根据数据操作单位可以把缓冲流分为
    - BufferedInputStream 和 BufferedOutputStream
    - BufferedReader 和 BufferedWriter
- 当读取数据时，数据按块读入缓冲区，其后的读操作直接访问缓冲区
- 当使用BufferedInputStream读取字节文件时，BufferedInputStream会一次性从文件读取8192(8kb),存在缓冲区中，直到缓冲区装满了，才重新从文件中读取下一个8192个字节数组。
- 向流中写入字节时，不会直接写道文件，先写道缓冲区中直到缓冲区写满，BufferedOutputStream才会把缓冲区中的数据一次性写到文件里。使用方法**flush()**可以强制将缓冲区的内容全部写入输入流
- 关闭流的顺序和打开流的顺序相反。只要关闭最外层流即可，关闭最外城流也会相应关闭内层节点流
- flush()方法的使用：手动将buffer中内容写入文件
- 如果是带缓冲区的流对象close()方法，不但会关闭流，还会在关闭流之前刷新缓冲区，关闭不能再写出

```java

BufferedReader br = null;
BufferedWriter bw = null;
try {
      // 创建缓冲流对象：它是处理流，是对节点流的包装
      br = new BufferedReader(new FileReader("d:\\IOTest\\source.txt")); 
      bw = new BufferedWriter(new FileWriter("d:\\IOTest\\d est.txt"));
      String str;
      while ((str = br.readLine()) != null) { // 一次读取字符文本文件的一行字符
        bw.write(str); // 一次写入一行字符串
        bw.newLine(); // 写入行分隔符
      } 
      bw.flush(); // 刷新缓冲区
} catch (IOException e) {
        e.printStackTrace(); 
    } finally {
    // 关闭IO流对象
       try {
           if(bw != null) {
               bw.close(); // 关闭过滤流时,会自动关闭它所包装的底层节点流
           }
       } catch (IOException e) {
           e.printStackTrace();
       } 
       
       try {
           if (br != null) { 
               br.close();
           } 
       } catch (IOException e) { 
           e.printStackTrace();
       } 
}
```
 
### 4.2 转换流(处理流之二）
转换流提供了字节流和字符流之间的转换
![zhuanhuan](https://github.com/SGEEKioi/Review/blob/master/imgs/%E8%BD%AC%E6%8D%A2%E6%B5%81.png?raw=true)
Java API提供了两个转换流：
    - InputStreamReader：将InputStream转换为Reader
        **实现将字节的的输入流按指定字符集转换为字符的输入流。**
        - 需要和InputStream“套接”
        - 构造器
            - public InputStreamReader(InputStream in)
            - public InputStreamReader(InputStream in,String charsetName)
    - OutputStreamWriter：将Writer转换为OutputStream
        **实现将字符的输出流按指定字符集转换为字节的输出流**
        - 需要和OutputStream“套接”
        - 构造器
            - public OutputStreamWriter(OutputStream out)
            - public OutputStreamWriter(OutputStream out,String charsetName)

- 字节流中的数据都是字符时，转成字符流操作更高效。
- 很多时候我们使用转换流来处理文件乱码问题。实现编码和节码的功能。

### 4.3 打印流(标准流之三）
实现将**基本数据类型**的数据格式转换为**字符串**
打印流：PrintStream和PrintWriter
- 提供了一系列重载的print()和pringln()方法，用于多种数据类型的输出
- PrintStream和PrintWriter的输出不会抛出IOException异常
- PrintStream和PrintWriter有自动flush功能
- PrintStream打印的所有字符都使用平台的默认字符编码转换为字节。在需要写入字符而不是写入字节的情况下，应使用PrintWriter类。
- System.out返回的是PrintStream的实例


### 4.4 对象流
ObjectInputStream和ObjectOutputStream
- 用于存储和读取**基本数据类型**数据或**对象**的处理流。它的强大之处就是可以把Java中的对象写入到数据源中，也能把对象从数据源中还原回来。

- 序列化：用ObjectOutputStream类**保存**基本类型数据或对象的机制
- 反序列化：用ObjectInputStream类**读取**基本数据类型或对象的机制

- ObjectOutStream和ObjectInputStream不能序列化**static**和**transient**修饰的成员变量

### 4.5对象的序列化
- **对象的序列化机制**允许把内存中的Java对象转化称平台无关的二进制流，从而把允许这种二进制流持久的保存在磁盘上，或通过网络将这中二进制流传输到另一个网络节点。当其他程序获取了这种二进制流，就可以恢复成原来的Java对象
- 序列化的好处在于可将任何实现了Serializable接口的对象转化为字节数据，使其在保存和传输时可被还原
- 序列化时RMI(Remote Method Invoke-远程方法调用)过程的参数和返回值都必须实现的机制，而RMI是JavaEE的基础。因此序列化机制是JavaEE平台的基础
- 如果需要让两个对象支持序列化机制，则必须让对象所属的类及其属性是可序列化的，为了让某个类是可序列化的，则该类必须实现如下两个接口之一
    - **Serializable**
    - Externlizable
- 凡是实现Serializable接口的类都有一个表示序列化版本标识符的静态变量：
    - private static final long serialVersionUID;
    - serialVersionUID 用来表明类的不同版本间的兼容性。简言之，其目的是以序列化对象进行版本控制，有关各版本反序列化时是否兼容。
    - 如果类没有显示定义这个静态常量，它的值是Java运行时环境根据类的内部细节自动生成的。**若类的实例变量做了修改，serialVersionUID可能发生变化。**故建议，显示声明。
- 简单来说，Java的序列化机制是通过在运行是判断类的serialVersionUID来验证版本一致性的。在进行反序列化时，JVM会把传来的字节流中serialVersionUID与本地相应实体类的serialVersionUID进行比较，如果相同就认为是一致的，可以进行反序列化，否则就会出现序列化版本不一致的异常(InvalidcastException)

##### 4.5.1 使用对象流序列化对象
- 若某个类实现了Serializable接口，该类的对象就是可序列化的：
    - 创建一个ObjectOututStream
    - 调用ObjectoutputStream对象的writeObject(对象)方法输出可序列化对象
    - 注意写一次，操作一次flush()
- 反序列化
    - 创建一个ObjectInputStream
    - 调用readObject()方法读取流中的对象
> 如果某个类的属性不是基本数据类型或String类型，而是另一个引用类型，那么这个引用类型必须是可序列化的，否则拥有该类型的Field的类也不能序列化   

序列化：将对象写入到磁盘或者进行网络传输。要求对象必须实现序列化
```java
ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("data.txt"));
Persion p = new Per("张三",18,"北京",new Pet());
oos.writeObject(p);
oos.flush();
oos.close();
``` 

反序列化：将磁盘中的对象数据源读出。
```java
ObjectInputStream ois = new ObjectInputStream(new FileInputStream("data.txt");
Persion p1 = (Persion)ois.readObject();
System.out.println(p1.toString());
ois.close();
```

> 面试题
> **谈谈你对java.io.Serializable接口的理解，我们知道它用于序列化，是空方法接口，还有其他人是吗？

> - 实现了Serializable接口的对象，可将它们转换成一系列字节，并可在以后完全恢复回原来的样子。**这一过程亦可通过网络进行。这意味着序列化机制能自动补偿操作系统间的差异**。换句话说，可以在Windows机器上创建一个对象，对其序列化 ，然后通过网络发给一台Unix机器，然后在那里准确无误地重新装配。不必关心数据在不同机器上如何表示，也不必关心字节地顺序或者其他任何细节。
> - 由于大部分作为参数地类如String、Integer等都实现了Serializable的接口，也可以利用多态的性质，作为参数使接口更灵活。

#### 4.5.2随机存取文件流 RadnomAccessFile类
我们可以用RandomAccessFile这个类，来实现一个**多线程断点下载**的功能，用过下载工具的朋友们都知道，下载前都会建立**两个临时文件**，一个是与被下载文件大小相同的空文件，另一个是记录文件指针的位置文件，每次暂停的时候，都会保存上一次的指针，然后断点下载的时候，会继续从上次的地方下载，从而是按断点下载或上传的功能。
- 读取文件内容
```java
RandomAccessFile raf = new RandomAccessFile(“test.txt”, “rw”);
raf.seek(5); 
byte [] b = new byte[1024];
int off = 0;
int len = 5;
raf.read(b, off, len);
String str = new String(b, 0, len); System.out.println(str);
raf.close();

```    

- 写入文件内容
```java
RandomAccessFile raf = new RandomAccessFile("test.txt", "rw"); raf.seek(5);
//先读出来 String temp = raf.readLine();
raf.seek(5); raf.write("xyz".getBytes()); 
raf.write(temp.getBytes());
raf.close();

```

