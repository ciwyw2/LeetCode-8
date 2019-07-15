# C串与C++串比较 #

在C++中，有两种字符串，一种是从C沿袭过来的，称为C-字符串，简称C-串，别称为ASCII串，C-串是一个全0位（整数0）作为结束符的字符序列，该全0字节既是8位的整数0，也是ASCII码的0——'\0'.  在C++可以通过c_str()函数将C++中的string转化成c串。

C-串的空间长度为字符串长度加1，例如：


	char buffer[7] = "Hello!";//若为char buffer[6] = "Hello!";则为错误！

C-串的类型是什么呢？C-串的类型是char*（字符指针）, 说的更精确一些，是const char* 。它与字符数组虽然类型不同，但操作上都是一样的，都表示C-串的起始地址

	char* str = "Hello";  
	cout << *str <<endl; //显示H  
	cout << str << endl; //显示Hello

str是字符指针变量，`*str`是字符指针变量的间接引用，输出字符指针的间接引用，就是输出单个字符。

str指向"Hello"的首地址，因而输出*str表示该地址代表的空间上的值—"H"。

输出字符指针就是输出C-串，所以当输出str时，就是从‘H’字符的地址开始，逐个输出所有字符直到遇到0；由于C-串类型为字符指针，因此比较两个C-串的时候，会因空间位置的不同而不同，在库函数中，专门设计了对C-串进行一系列的操作的函数：

1.	`char* strcpy(char* x1, char* x2)`;//拷贝函数，它表示将x2字串拷贝到x1所在的位置，将x1原先的内容覆盖，该函数调用后返回x1的首地址，目的是让调用结果可以直接参加之后的字串操作,需要注意的是，由于x2字串的长度可能比x1字串空间要长，所以strcpy的使用并不安全，如果a字符数组长度为3(小于s1的长度)，则执行strcpy(a, s1)会让紧挨a数组的邻近内存空间中的数据也被修改，导致不可预料的错误
2.	`int strcmp(char* x1, char* x2)`;//比较函数，它表示将x2串与x1串进行字典序列比较，如果x1小则返回值为负数，x1大则返回值为正数，相等则返回0
3.	`char* strcat(char* x1, char* x2)`;//连接函数，它将x2的字串连接到x1字串后面，显然这会加长x1字串，或者说，结束符0后移，当x1字串之后所余的自身空间不足以接纳x2字串时，调用该操作将不安全
4.	`char* strrev(char* x)`;//倒置函数，它将x字串倒过来，并改变原来的存储
5.	`char* strset(char* x, char b)`;//设置函数，它将x字串的每个字符用b字符来代替
6.	`char* strstr(char* x, char* s)`;//查找函数，在x字串中查找s字串，若找到，返回1，否则返回0
7.	`char* strchr(char* x, char b)`;//查找函数，在x字串中查找b字符，若找到，返回1，否则返回0
这些就是一些长用的C-串库函数...，这些库函数的操作默认在string.h的头文件中
# char* 与char[ ] #
这两种形式都是C串。

char *p是个指针，指向字符串常量区域 ，p的指向是可以改变的;但是字符串常量的值是不可以被修改的。

char a[ ]  是在栈区开辟的一段空间，可以被修改的。此时，a既代表有字符串a,又代表a[0]。
# stdlib.h中的转化函数 #

包含常用的c_str 到 基本数据类型的转化。 atoi  atof  atol