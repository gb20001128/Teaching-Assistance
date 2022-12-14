# Java正则表达式规则⼤全



⼀、校验数字的表达式
--------------------------------------------------------
1 数字：^[0-9]*$
2 n位的数字：^\d{n}$
3 ⾄少n位的数字：^\d{n,}$
4 m-n位的数字：^\d{m,n}$
5 零和⾮零开头的数字：^(0|[1-9][0-9]*)$
6 ⾮零开头的最多带两位⼩数的数字：^([1-9][0-9]*)+(.[0-9]{1,2})?$
7 带1-2位⼩数的正数或负数：^(\-)?\d+(\.\d{1,2})?$
8 正数、负数、和⼩数：^(\-|\+)?\d+(\.\d+)?$
9 有两位⼩数的正实数：^[0-9]+(.[0-9]{2})?$
10 有1~3位⼩数的正实数：^[0-9]+(.[0-9]{1,3})?$
11 ⾮零的正整数：^[1-9]\d*$ 或 ^([1-9][0-9]*){1,3}$ 或 ^\+?[1-9][0-9]*$
12 ⾮零的负整数：^\-[1-9][]0-9"*$ 或 ^-[1-9]\d*$
13 ⾮负整数：^\d+$ 或 ^[1-9]\d*|0$
14 ⾮正整数：^-[1-9]\d*|0$ 或 ^((-\d+)|(0+))$
15 ⾮负浮点数：^\d+(\.\d+)?$ 或 ^[1-9]\d*\.\d*|0\.\d*[1-9]\d*|0?\.0+|0$
16 ⾮正浮点数：^((-\d+(\.\d+)?)|(0+(\.0+)?))$ 或 ^(-([1-9]\d*\.\d*|0\.\d*[1-9]\d*))|0?\.0+|0$
17 正浮点数：^[1-9]\d*\.\d*|0\.\d*[1-9]\d*$ 或 ^(([0-9]+\.[0-9]*[1-9][0-9]*)|([0-9]*[1-9][0-9]*\.[0-9]+)|([0-9]*[1-9][0-9]*))$
18 负浮点数：^-([1-9]\d*\.\d*|0\.\d*[1-9]\d*)$ 或 ^(-(([0-9]+\.[0-9]*[1-9][0-9]*)|([0-9]*[1-9][0-9]*\.[0-9]+)|([0-9]*[1-9][0-9]*)))$
19 浮点数：^(-?\d+)(\.\d+)?$ 或 ^-?([1-9]\d*\.\d*|0\.\d*[1-9]\d*|0?\.0+|0)$

## ⼆、校验字符的表达式

1 汉字：^[\u4e00-\u9fa5]{0,}$
2 英⽂和数字：^[A-Za-z0-9]+$ 或 ^[A-Za-z0-9]{4,40}$
3 长度为3-20的所有字符：^.{3,20}$
4 由26个英⽂字母组成的字符串：^[A-Za-z]+$
5 由26个⼤写英⽂字母组成的字符串：^[A-Z]+$
6 由26个⼩写英⽂字母组成的字符串：^[a-z]+$
7 由数字和26个英⽂字母组成的字符串：^[A-Za-z0-9]+$
8 由数字、26个英⽂字母或者下划线组成的字符串：^\w+$ 或 ^\w{3,20}$
9 中⽂、英⽂、数字包括下划线：^[\u4E00-\u9FA5A-Za-z0-9_]+$
10 中⽂、英⽂、数字但不包括下划线等符号：^[\u4E00-\u9FA5A-Za-z0-9]+$ 或 ^[\u4E00-\u9FA5A-Za-z0-9]{2,20}$
11 可以输⼊含有^%&',;=?$\"等字符：[^%&',;=?$\x22]+
12 禁⽌输⼊含有~的字符：[^~\x22]+

## 三、特殊需求表达式

1 Email地址：^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$
2 域名：[a-zA-Z0-9][-a-zA-Z0-9]{0,62}(/.[a-zA-Z0-9][-a-zA-Z0-9]{0,62})+/.?
3 InternetURL：[a-zA-z]+://[^\s]* 或 ^https://([\w-]+\.)+[\w-]+(/[\w-./?%&=]*)?$
4 ⼿机号码：^(13[0-9]|14[5|7]|15[0|1|2|3|5|6|7|8|9]|18[0|1|2|3|5|6|7|8|9])\d{8}$
5 电话号码("XXX-XXXXXXX"、"XXXX-XXXXXXXX"、"XXX-XXXXXXX"、"XXX-XXXXXXXX"、"XXXXXXX"和"XXXXXXXX)：^(\(\d{3,4}-
                                                                                                                                                                      )|\d{3.4}-)?\d{7,8}$ 
6 国内电话号码(0511-4405222、021-87888822)：\d{3}-\d{8}|\d{4}-\d{7}
7 ⾝份证号：
        15或18位⾝份证：^\d{15}|\d{18}$
        15位⾝份证：^[1-9]\d{7}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}$
        18位⾝份证：^[1-9]\d{5}[1-9]\d{3}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{4}$
8 短⾝份证号码(数字、字母x结尾)：^([0-9]){7,18}(x|X)?$ 或 ^\d{8,18}|[0-9x]{8,18}|[0-9X]{8,18}?$
9 帐号是否合法(字母开头，允许5-16字节，允许字母数字下划线)：^[a-zA-Z][a-zA-Z0-9_]{4,15}$
10 密码(以字母开头，长度在6~18之间，只能包含字母、数字和下划线)：^[a-zA-Z]\w{5,17}$
11 强密码(必须包含⼤⼩写字母和数字的组合，不能使⽤特殊字符，长度在8-10之间)：^(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,10}$ 
12 ⽇期格式：^\d{4}-\d{1,2}-\d{1,2}
13 ⼀年的12个⽉(01～09和1～12)：^(0?[1-9]|1[0-2])$
14 ⼀个⽉的31天(01～09和1～31)：^((0?[1-9])|((1|2)[0-9])|30|31)$ 
15 钱的输⼊格式：
16 1.有四种钱的表⽰形式我们可以接受:"10000.00" 和 "10,000.00", 和没有 "分" 的 "10000" 和 "10,000"：^[1-9][0-9]*$ 
17 2.这表⽰任意⼀个不以0开头的数字,但是,这也意味着⼀个字符"0"不通过,所以我们采⽤下⾯的形式：^(0|[1-9][0-9]*)$ 
18 3.⼀个0或者⼀个不以0开头的数字.我们还可以允许开头有⼀个负号：^(0|-?[1-9][0-9]*)$ 
19 4.这表⽰⼀个0或者⼀个可能为负的开头不为0的数字.让⽤户以0开头好了.把负号的也去掉,因为钱总不能是负的吧.下⾯我们要加的是说明
可能的⼩数部分：^[0-9]+(.[0-9]+)?$ 
20 5.必须说明的是,⼩数点后⾯⾄少应该有1位数,所以"10."是不通过的,但是 "10" 和 "10.2" 是通过的：^[0-9]+(.[0-9]{2})?$ 
21 6.这样我们规定⼩数点后⾯必须有两位,如果你认为太苛刻了,可以这样：^[0-9]+(.[0-9]{1,2})?$ 
22 7.这样就允许⽤户只写⼀位⼩数.下⾯我们该考虑数字中的逗号了,我们可以这样：^[0-9]{1,3}(,[0-9]{3})*(.[0-9]{1,2})?$ 
23 8.1到3个数字,后⾯跟着任意个逗号+3个数字,逗号成为可选,⽽不是必须：^([0-9]+|[0-9]{1,3}(,[0-9]{3})*)(.[0-9]{1,2})?$ 
24 备注：这就是最终结果了,别忘了"+"可以⽤"*"替代如果你觉得空字符串也可以接受的话(奇怪,为什么?)最后,别忘了在⽤函数时去掉去掉那
个反斜杠,⼀般的错误都在这⾥
25 xml⽂件：^([a-zA-Z]+-?)+[a-zA-Z0-9]+\\.[x|X][m|M][l|L]$
26 中⽂字符的正则表达式：[\u4e00-\u9fa5]
27 双字节字符：[^\x00-\xff] (包括汉字在内，可以⽤来计算字符串的长度(⼀个双字节字符长度计2，ASCII字符计1))
28 空⽩⾏的正则表达式：\n\s*\r (可以⽤来删除空⽩⾏)
29 HTML标记的正则表达式：<(\S*?)[^>]*>.*?|<.*? /> (⽹上流传的版本太糟糕，上⾯这个也仅仅能部分，对于复杂的嵌套标记依旧⽆能为⼒)
30 ⾸尾空⽩字符的正则表达式：^\s*|\s*$或(^\s*)|(\s*$) (可以⽤来删除⾏⾸⾏尾的空⽩字符(包括空格、制表符、换页符等等)，⾮常有⽤的表达式)
31 腾讯QQ号：[1-9][0-9]{4,} (腾讯QQ号从10000开始)
32 中国邮政编码：[1-9]\d{5}(?!\d)  (中国邮政编码为6位数字)

33 IP地址：\d+\.\d+\.\d+\.\d+ (提取IP地址时有⽤)

###### 




###### 
