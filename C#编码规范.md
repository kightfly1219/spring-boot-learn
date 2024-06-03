# C#编码规范

[TOC]

## 概要

- 采他山之石以攻玉,纳百家之长以厚己。



## 目的

​	本文档结合MSDN规则和实际项目的经验整理记述了C#编码规范，统一代码外观和提高代码质量。编写出简洁，易读，易维护，高质量的代码。

- 简洁 
​	为代码创建一致的外观，以确保专注于内容而非布局
- 易读 
​	方便更快地理解代码
- 易维护 
​	便于复制、更改和维护代码
- 高质量 
​	展示 C# 最佳做法



## 适用范围

适用于开发（C#）项目中修改评审代码时使用。



## 对象读者

项目团队中的开发者和评审人员。



# 规则严重性级别
所有分析器规则（包括 代码质量 和 代码样式 规则）配置的不同规则严重性。
- 错误 error 冲突显示为生成 错误 并导致生成失败
- 警告 warning 冲突显示为生成 警告 ，但不会导致生成失败 (除非您将选项设置为 "将警告视为错误") 。
- 建议 suggestion 冲突显示为生成 消息 ，在 VISUAL Studio IDE 中显示为建议。
- 仅重构 silent 冲突对用户不可见。
- 禁止规则 none 完全禁止显示规则。
- 默认规则 default 使用规则的默认严重性。 

​    规则严重性的设置在保证统一外观和提高代码质量的基础上，容许保留一些个人开发者裁量的规则。这些规则级别为 仅重构silent。
​    建议级别一般情况下需要准守。
​    警告和错误级别的规则必须要准守。



# 规则选项格式

可在 EditorConfig 文件中指定语言规则的选项

- 值
  ​	对于每个语言规则，指定一个值，该值定义是否喜欢样式。 许多规则都接受 true 值（以此样式为首选项）或 false 值（不以此样式为首选项）。 其他规则接受 when_on_single_line 或 never 等值。
- 严重性
  ​	规则的第二部分指定规则的 严重性级别 。 



格式如下：

```ini
csharp_style_var_elsewhere = false:silent
```


# 代码样式规则



## 概述

代码样式规则划分为以下子类别：
- 语言规则
- 不必要的代码规则
- 杂项规则
- 格式设置规则
- 命名规则



## 语言规则
​	代码样式语言规则会影响 .NET 编程语言的各种构造，例如，如何使用修饰符和括号。 

这些规则分为以下几类：

- .Net 格式规则
- C # 格式设置规则


### "this." 和 "Me." 限定符

####  .Net 格式规则
- 严重性级别 ：建议

##### dotnet_style_qualification_for_field = false
- 字段不以 this. 或 Me. 开头为首选项
##### dotnet_style_qualification_for_property = false
- 属性不以 this. 或 Me. 开头为首选项
##### dotnet_style_qualification_for_method = false
- 方法不以 this. 或 Me. 开头为首选项
##### dotnet_style_qualification_for_event = false
- 事件不以 this. 或 Me. 开头为首选项



- 代码示例：

```c#
// dotnet_style_qualification_for_field = true
// dotnet_style_qualification_for_property = true
// dotnet_style_qualification_for_method = true
// dotnet_style_qualification_for_event = true
this.capacity = 0;
this.ID = 0;
this.Display();
this.Elapsed += Handler;

// dotnet_style_qualification_for_field = false
// dotnet_style_qualification_for_property = false
// dotnet_style_qualification_for_method = false
// dotnet_style_qualification_for_event = false
capacity = 0;
ID = 0;
Display();
Elapsed += Handler;
```



##### .editorconfig 文件示例：
```ini
# 字段不以 this. 或 Me. 开头为首选项
dotnet_style_qualification_for_field = false:suggestion
# 属性不以 this. 或 Me. 开头为首选项
dotnet_style_qualification_for_property = false:suggestion
# 方法不以 this. 或 Me. 开头为首选项
dotnet_style_qualification_for_method = false:suggestion
# 事件不以 this. 或 Me. 开头为首选项
dotnet_style_qualification_for_event = false:suggestion

# IDE0003: 删除限定
dotnet_diagnostic.IDE0003.severity = suggestion
```



### 语言关键字，而非类型引用的框架类型名称

- 严重性级别 ：建议



#### .Net 格式规则



##### dotnet_style_predefined_type_for_locals_parameters_members = true

- 对于类型（其中具有用于表示该类型的关键字），本地变量、方法参数和类成员的语言关键字为首选项，而非类型名称

##### dotnet_style_predefined_type_for_member_access = true

- 对于类型（其中具有用于表示该类型的关键字），成员访问表达式的语言关键字为首选项，而非类型名称



- 代码示例：

```c#
// dotnet_style_predefined_type_for_locals_parameters_members = true
private int _member;
// dotnet_style_predefined_type_for_member_access = true
var local = int.MaxValue;

// dotnet_style_predefined_type_for_locals_parameters_members = false
private Int32 _member;
// dotnet_style_predefined_type_for_member_access = false
var local = Int32.MaxValue;
```



##### .editorconfig 文件示例：
```ini
# 对于类型（其中具有用于表示该类型的关键字），本地变量、方法参数和类成员的语言关键字为首选项，而非类型名称
dotnet_style_predefined_type_for_locals_parameters_members = true:suggestion
# 对于类型（其中具有用于表示该类型的关键字），成员访问表达式的语言关键字为首选项，而非类型名称
dotnet_style_predefined_type_for_member_access = true:suggestion

# IDE0049: 简化名称
dotnet_diagnostic.IDE0049.severity = suggestion
```



### 修饰符首选项

- 严重性级别 ：建议

#### .Net 格式规则

##### csharp_preferred_modifier_order = public,private,protected,internal,static,extern,new,virtual,abstract,sealed,override,readonly,unsafe,volatile,async

- 指定所需的修饰符排序顺序
- 顺序修饰符 (IDE0036) 



- 代码示例：

```c#
// csharp_preferred_modifier_order = public,private,protected,internal,static,extern,new,virtual,abstract,sealed,override,readonly,unsafe,volatile,async
class MyClass
{
    // 正例
    private static readonly int _daysInYear = 365;
    
    // 反例
    private readonly static int _daysInYear = 365;   
}
```



##### dotnet_style_require_accessibility_modifiers = for_non_interface_members

- 指定所需的可访问性修饰符的首选项
- 添加可访问性修饰符 (IDE0040)



- 代码示例：

```c#
// dotnet_style_require_accessibility_modifiers = always
// dotnet_style_require_accessibility_modifiers = for_non_interface_members
class MyClass
{
    private const string thisFieldIsConst = "constant";
}

// dotnet_style_require_accessibility_modifiers = never
class MyClass
{
    const string thisFieldIsConst = "constant";
}
```



##### dotnet_style_readonly_field = true

- 如果字段只是内联分配或者在构造函数中，偏向将它们标记为 readonly (C#) 或 ReadOnly (Visual Basic) 的字段
- 添加 readonly 修饰符 (IDE0044)



- 代码示例：

```c#
// dotnet_style_readonly_field = true
class MyClass
{
    private readonly int _daysInYear = 365;
}
```



##### 使结构字段可写 (IDE0064) 

- 此规则没有关联的代码样式选项
- 此规则检测包含一个或多个 `readonly` 字段且还包含对构造函数之外的赋值的结构 `this` 。 规则建议 `readonly` 将字段转换为非只读，即可写。 将此类结构字段标记为 readonly 可能会导致意外的行为，因为分配给字段的值在 `this` 构造函数之外赋值时可能会更改。
- 使结构字段可写 (IDE0064) 



- 代码示例：

```c#
// Fixed code
// 正例
struct MyStruct
{
    public int Value;

    public MyStruct(int value)
    {
        Value = value;
    }

    public void Test()
    {
        this = new MyStruct(5);
    }
}

// Code with violations
// 反例
struct MyStruct
{
    public readonly int Value;

    public MyStruct(int value)
    {
        Value = value;
    }

    public void Test()
    {
        this = new MyStruct(5);
    }
}
```





##### .editorconfig 文件示例：

```ini
# 指定所需的修饰符排序顺序
csharp_preferred_modifier_order = public,private,protected,internal,static,extern,new,virtual,abstract,sealed,override,readonly,unsafe,volatile,async:suggestion
# 指定所需的可访问性修饰符的首选项
dotnet_style_require_accessibility_modifiers = for_non_interface_members:suggestion
# 如果字段只是内联分配或者在构造函数中，偏向将它们标记为 readonly (C#) 或 ReadOnly (Visual Basic) 的字段
dotnet_style_readonly_field = true:suggestion
# 首选将局部函数标记为 `static`
csharp_prefer_static_local_function = true:suggestion

# IDE0036: 顺序修饰符
dotnet_diagnostic.IDE0036.severity = suggestion
# IDE0040: 添加可访问性修饰符
dotnet_diagnostic.IDE0040.severity = suggestion
# IDE0044: 添加只读修饰符
dotnet_diagnostic.IDE0044.severity = suggestion
# IDE0062: 使本地函数成为静态函数
dotnet_diagnostic.IDE0062.severity = suggestion
# IDE0064: 使 readonly 字段可写
dotnet_diagnostic.IDE0064.severity = suggestion
```





### 括号首选项

- 严重性级别 ：建议

  

#### .Net 格式规则

##### dotnet_style_parentheses_in_arithmetic_binary_operators = always_for_clarity

- 优先使用括号来声明算术运算符（`*`、`/`、`%`、`+`、`-`、`<<`、`>>`、`&`、`^`、`|`）优先级



- 代码示例：

```c#
// dotnet_style_parentheses_in_arithmetic_binary_operators = always_for_clarity
var v = a + (b * c);

// dotnet_style_parentheses_in_arithmetic_binary_operators = never_if_unnecessary
var v = a + b * c;
```



##### dotnet_style_parentheses_in_relational_binary_operators = always_for_clarity

- 优先使用括号来声明其他二元运算符（`&&`、`||`、`??`）优先级



- 代码示例：

```c#
// dotnet_style_parentheses_in_relational_binary_operators = always_for_clarity
var v = (a < b) == (c > d);

// dotnet_style_parentheses_in_relational_binary_operators = never_if_unnecessary
var v = a < b == c > d;
```



##### dotnet_style_parentheses_in_other_binary_operators = always_for_clarity

- 优先使用括号来声明其他二元运算符（`&&`、`||`、`??`）优先级



- 代码示例：

```c#
// dotnet_style_parentheses_in_other_binary_operators = always_for_clarity
var v = a || (b && c);

// dotnet_style_parentheses_in_other_binary_operators = never_if_unnecessary
var v = a || b && c;
```



##### dotnet_style_parentheses_in_other_operators = never_if_unnecessary

-  运算符的优先级显而易见时，最好不要使用括号



- 代码示例：

```c#
// dotnet_style_parentheses_in_other_operators = always_for_clarity
var v = (a.b).Length;

// dotnet_style_parentheses_in_other_operators = never_if_unnecessary
var v = a.b.Length;
```





##### .editorconfig 文件示例：

```ini
# 优先使用括号来声明算术运算符（*、/、%、+、-、<<、>>、&、^、|）优先级
dotnet_style_parentheses_in_arithmetic_binary_operators = always_for_clarity:suggestion
# 优先使用括号来声明关系运算符（>、<、<=、>=、is、as、==、!=）优先级
dotnet_style_parentheses_in_relational_binary_operators = always_for_clarity:suggestion
# 优先使用括号来声明其他二元运算符（`&&`、`||`、`??`）优先级
dotnet_style_parentheses_in_other_binary_operators = always_for_clarity:suggestion
# 运算符的优先级显而易见时，最好不要使用括号
dotnet_style_parentheses_in_other_operators = never_if_unnecessary:suggestion

# IDE0047: 删除不必要的括号
dotnet_diagnostic.IDE0047.severity = suggestion
# IDE0048: 为清楚起见，请添加括号
dotnet_diagnostic.IDE0048.severity = suggestion
```





### 表达式级首选项

- 严重性级别 ：建议

#### .Net 格式规则

##### switch 语句必须含有default分支

- 此规则没有关联的代码样式选项
- 严重性级别 ：无



- 代码示例：

```c#
// 正例
switch (e)
{
    case A:
        // PRO_A
        break;
    case B:
        // PRO_B
        break;
    default:
        // 默认分支
        break;
}

// 反例
switch (e)
{
    case A:
        // PRO_A
        break;
    case B:
        // PRO_B
        break;
}
```



##### dotnet_style_object_initializer = true

- 在可能情况下，更倾向使用对象初始值设定项来初始化对象
- 使用对象初始值设定项 (IDE0017) 



- 代码示例：

```c#
// dotnet_style_object_initializer = true
var c = new Customer
{ 
    Age = 21 
};

// dotnet_style_object_initializer = false
var c = new Customer();
c.Age = 21;
```



##### dotnet_style_collection_initializer = true

- 在可能情况下，更倾向使用集合初始值设定项来初始化集合
- 使用集合初始值设定项 (IDE0028) 



- 代码示例：

```c#
// dotnet_style_collection_initializer = true
var list = new List<int> { 1, 2, 3 };

// dotnet_style_collection_initializer = false
var list = new List<int>();
list.Add(1);
list.Add(2);
list.Add(3);
```



##### dotnet_style_prefer_auto_properties = false

- 通过自动属性使用带有私有支持字段的属性
- 使用 auto 属性 (IDE0032) 
- 严重性级别 ：仅重构



- 代码示例：

```c#
// dotnet_style_prefer_auto_properties = true
private int Age { get; }

// dotnet_style_prefer_auto_properties = false
private int age;

public int Age
{
    get
    {
        return age;
    }
}
```



##### dotnet_style_explicit_tuple_names = true

-  比起 ItemX 属性更倾向元祖名称
- 使用显式提供的元组名称 (IDE0033)



- 代码示例：

```c#
// dotnet_style_explicit_tuple_names = true
(string name, int age) customer = GetCustomer();
var name = customer.name;

// dotnet_style_explicit_tuple_names = false
(string name, int age) customer = GetCustomer();
var name = customer.Item1;
```



##### dotnet_style_prefer_inferred_tuple_names = true

- 首选推断元组元素名称
- 使用推断成员名称 (IDE0037) 



- 代码示例：

```c#
// dotnet_style_prefer_inferred_tuple_names = true
var tuple = (age, name);

// dotnet_style_prefer_inferred_tuple_names = false
var tuple = (age: age, name: name);
```



##### dotnet_style_prefer_inferred_anonymous_type_member_names = true

- 首选推断匿名类型成员名称
- 使用推断成员名称 (IDE0037) 



- 代码示例：

```c#
// dotnet_style_prefer_inferred_anonymous_type_member_names = true
var anon = new { age, name };

// dotnet_style_prefer_inferred_anonymous_type_member_names = false
var anon = new { age = age, name = name };
```



##### dotnet_style_prefer_conditional_expression_over_assignment = true

- 与 if-else 语句相比，首选三元条件进行赋值
- 将条件表达式用于赋值 (IDE0045) 
- 严重性级别 ：仅重构



- 代码示例：

```c#
// dotnet_style_prefer_conditional_expression_over_assignment = true
string s = expr ? "hello" : "world";

// dotnet_style_prefer_conditional_expression_over_assignment = false
string s;
if (expr)
{
    s = "hello";
}
else
{
    s = "world";
}
```



##### dotnet_style_prefer_conditional_expression_over_return = true

- 与 if-else 语句相比，return 语句首选三元条件
- 将条件表达式用于 return (IDE0046)  
- 严重性级别 ：仅重构



- 代码示例：

```c#
// dotnet_style_prefer_conditional_expression_over_return = true
return expr ? "hello" : "world"

// dotnet_style_prefer_conditional_expression_over_return = false
if (expr)
{
    return "hello";
}
else
{
    return "world";
}
```



##### 将匿名类型转换为元组 (IDE0050) 

- 此规则没有关联的代码样式选项
- 将匿名类型转换为元组
- 严重性级别 ：无



- 代码示例：

```c#
// Code with violations
var t1 = new { a = 1, b = 2 };

// Fixed code
var t1 = (a: 1, b: 2);
```



##### dotnet_style_prefer_compound_assignment = true

- 首选复合赋值表达式
- 使用复合赋值 (IDE0054 和 IDE0074) 



- 代码示例：

```c#
// dotnet_style_prefer_compound_assignment = true
x += 1;

// dotnet_style_prefer_compound_assignment = false
x = x + 1;
```



##### IDE0070: 使用 "System.HashCode"

- 使用 `System.HashCode.Combine`方法来计算哈希代码
- 使用 `System.HashCode.Combine` (IDE0070) 



- 代码示例：

```c#
class B
{
    public override int GetHashCode() => 0;
}

class C : B
{
    int j;

    // Code with violations
    public override int GetHashCode()
    {
        // IDE0070: GetHashCode can be simplified.
        var hashCode = 339610899;
        hashCode = hashCode * -1521134295 + base.GetHashCode();
        hashCode = hashCode * -1521134295 + j.GetHashCode();
        return hashCode;
    }

    // Fixed code
    public override int GetHashCode()
    {
        return System.HashCode.Combine(base.GetHashCode(), j);
    }
}
```



##### dotnet_style_prefer_simplified_interpolation = true

- 首选简化内插字符串
- 简化插值 (IDE0071)



- 代码示例：

```c#
// dotnet_style_prefer_simplified_interpolation = true
var str = $"prefix {someValue} suffix";

// dotnet_style_prefer_simplified_interpolation = false
var str = $"prefix {someValue.ToString()} suffix";
```



##### 向 switch 表达式添加缺少的事例 (IDE0072) 

- 此规则没有关联的代码样式选项

- 向 switch 表达式添加缺少的事例 (IDE0072) 
- 严重性级别 ：无



- 代码示例：

```c#
enum E
{
    A,
    B
}

class C
{
    // Code with violations
    int M(E e)
    {
        // IDE0010: Add missing cases
        return e switch
        {
            E.A => 0,
            _ => -1,
        };
    }

    // Fixed code
    int M(E e)
    {
        return e switch
        {
            E.A => 0,
            E.B => 1,
            _ => -1,
        };
    }
}
```



##### dotnet_style_prefer_simplified_boolean_expressions = true

- 首选简化条件表达式

- 简化条件表达式 (IDE0075) 



- 代码示例：

```c#
// dotnet_style_prefer_simplified_boolean_expressions = true
var result1 = M1() && M2();
var result2 = M1() || M2();

// dotnet_style_prefer_simplified_boolean_expressions = false
var result1 = M1() && M2() ? true : false;
var result2 = M1() ? true : M2();
```



##### 转换 `typeof` 为 `nameof` (IDE0082) 

- 将 `typeof` 转换为 `nameof`

- 转换 `typeof` 为 `nameof` (IDE0082) 



- 代码示例：

```c#
// Code with violations
var n1 = typeof(T).Name;
var n2 = typeof(int).Name;

// Fixed code
var n1 = nameof(T);
var n2 = nameof(Int32);
```





##### .editorconfig 文件示例：

```ini
# 在可能情况下，更倾向使用对象初始值设定项来初始化对象
dotnet_style_object_initializer = true:suggestion
# 在可能情况下，更倾向使用集合初始值设定项来初始化集合
dotnet_style_collection_initializer = true:suggestion
# 通过自动属性使用带有私有支持字段的属性
dotnet_style_prefer_auto_properties = false:silent
# 比起 ItemX 属性更倾向元祖名称
dotnet_style_explicit_tuple_names = true:suggestion
# 首选推断元组元素名称
dotnet_style_prefer_inferred_tuple_names = true:suggestion
# 首选推断匿名类型成员名称
dotnet_style_prefer_inferred_anonymous_type_member_names = true:suggestion
# 与 if-else 语句相比，首选三元条件进行赋值
dotnet_style_prefer_conditional_expression_over_assignment = true:silent
# 与 if-else 语句相比，return 语句首选三元条件
dotnet_style_prefer_conditional_expression_over_return = true:silent
# 首选复合赋值表达式
dotnet_style_prefer_compound_assignment = true:suggestion
# 首选简化内插字符串
dotnet_style_prefer_simplified_interpolation = true:suggestion
# 首选简化条件表达式
dotnet_style_prefer_simplified_boolean_expressions = true:suggestion

# IDE0017: 简化对象初始化
dotnet_diagnostic.IDE0017.severity = suggestion
# IDE0028: 简化合初始化
dotnet_diagnostic.IDE0028.severity = suggestion
# IDE0032: 使用自动属性
dotnet_diagnostic.IDE0032.severity = silent
# IDE0033: 使用显式提供的元组名称
dotnet_diagnostic.IDE0033.severity = suggestion
# IDE0037: 使用推断的成员名称
dotnet_diagnostic.IDE0037.severity = suggestion
# IDE0045: 转换为条件表达式
dotnet_diagnostic.IDE0045.severity = silent
# IDE0046: 转换为条件表达式
dotnet_diagnostic.IDE0046.severity = silent
# IDE0050: 将匿名类型转换为元组
dotnet_diagnostic.IDE0050.severity = none
# IDE0054: 使用复合分配
dotnet_diagnostic.IDE0054.severity = suggestion
# IDE0070: 使用 "System.HashCode"
dotnet_diagnostic.IDE0070.severity = suggestion
# IDE0071: 简化内插
dotnet_diagnostic.IDE0071.severity = suggestion
# IDE0072: 向 switch 表达式添加缺少的事例
dotnet_diagnostic.IDE0072.severity = none
# IDE0075: 简化条件表达式
dotnet_diagnostic.IDE0075.severity = suggestion
# IDE0082: "typeof" 可以转换为 "nameof"
dotnet_diagnostic.IDE0082.severity = suggestion
```



#### C # 格式规则

##### csharp_style_inlined_variable_declaration = true

- `out` 变量在方法调用的参数列表中声明为内联为首选项（如可能）
- 内联变量声明 (IDE0018) 



- 代码示例：

```c#
// csharp_style_inlined_variable_declaration = true
if (int.TryParse(value, out int i) {...}

// csharp_style_inlined_variable_declaration = false
int i;
if (int.TryParse(value, out i) {...}
```



##### csharp_prefer_simple_default_expression = true

- 首选 `default` 次选 `default(T)`
- 简化 "default" 表达式 (IDE0034) 



- 代码示例：

```c#
// csharp_prefer_simple_default_expression = true
void DoWork(CancellationToken cancellationToken = default) { ... }

// csharp_prefer_simple_default_expression = false
void DoWork(CancellationToken cancellationToken = default(CancellationToken)) { ... }
```



##### csharp_style_pattern_local_over_anonymous_function = true

- 首选本地函数，而非匿名函数
- 使用本地函数而不是 lambda (IDE0039) 



- 代码示例：

```c#
// csharp_style_pattern_local_over_anonymous_function = true
int fibonacci(int n)
{
    return n <= 1 ? 1 : fibonacci(n-1) + fibonacci(n-2);
}

// csharp_style_pattern_local_over_anonymous_function = false
Func<int, int> fibonacci = null;
fibonacci = (int n) =>
{
    return n <= 1 ? 1 : fibonacci(n - 1) + fibonacci(n - 2);
};
```



##### csharp_style_deconstructed_variable_declaration = true

- 首选析构变量声明
- 析构变量声明 (IDE0042) 



- 代码示例：

```c#
// csharp_style_deconstructed_variable_declaration = true
var (name, age) = GetPersonTuple();
Console.WriteLine($"{name} {age}");

(int x, int y) = GetPointTuple();
Console.WriteLine($"{x} {y}");

// csharp_style_deconstructed_variable_declaration = false
var person = GetPersonTuple();
Console.WriteLine($"{person.name} {person.age}");

(int x, int y) point = GetPointTuple();
Console.WriteLine($"{point.x} {point.y}");
```



##### csharp_style_prefer_index_operator = true

- 在从集合末尾计算索引时，首选使用 `^` 操作符
- 使用索引运算符 (IDE0056) 



- 代码示例：

```c#
// csharp_style_prefer_index_operator = true
string[] names = { "Archimedes", "Pythagoras", "Euclid" };
var index = names[^1];

// csharp_style_prefer_index_operator = false
string[] names = { "Archimedes", "Pythagoras", "Euclid" };
var index = names[names.Length - 1];
```



##### csharp_style_prefer_range_operator = true

- 在从集合末尾计算索引时，首选使用 `^` 操作符
- 使用范围运算符 (IDE0057) 



- 代码示例：

```c#
// csharp_style_prefer_range_operator = true
string sentence = "the quick brown fox";
var sub = sentence[0..^4];

// csharp_style_prefer_range_operator = false
string sentence = "the quick brown fox";
var sub = sentence.Substring(0, sentence.Length - 4);
```



##### csharp_style_implicit_object_creation_when_type_is_apparent = false

- 不首选目标类型 `new` 表达式
- 简化 `new` expression (IDE0090) 
- 严重性级别 ：无



- 代码示例：

```c#
// csharp_style_implicit_object_creation_when_type_is_apparent = true
C c = new();
C c2 = new() { Field = 0 };

// csharp_style_implicit_object_creation_when_type_is_apparent = false
C c = new C();
C c2 = new C() { Field = 0 };
```



##### .editorconfig 文件示例：

```ini
# out 变量在方法调用的参数列表中声明为内联为首选项（如可能）
csharp_style_inlined_variable_declaration = true:suggestion
# 首选 default 次选 default(T)
csharp_prefer_simple_default_expression = true:suggestion
# 首选本地函数，而非匿名函数
csharp_style_pattern_local_over_anonymous_function = true:suggestion
# 首选析构变量声明
csharp_style_deconstructed_variable_declaration = true:suggestion
# 在从集合末尾计算索引时，首选使用 `^` 操作符
csharp_style_prefer_index_operator = true:suggestion
# 在从集合末尾计算索引时，首选使用 `^` 操作符
csharp_style_prefer_range_operator = true:suggestion
# 不首选目标类型 new 表达式
csharp_style_implicit_object_creation_when_type_is_apparent = false:suggestion 

# IDE0018: 内联变量声明
dotnet_diagnostic.IDE0018.severity = suggestion
# IDE0034: 简化 "default" 表达式
dotnet_diagnostic.IDE0034.severity = suggestion
# IDE0039: 使用本地函数
dotnet_diagnostic.IDE0039.severity = suggestion
# IDE0042: 可以析构变量声明
dotnet_diagnostic.IDE0042.severity = suggestion
# IDE0056: 使用索引运算符
dotnet_diagnostic.IDE0056.severity = suggestion
# IDE0057: 使用范围运算符
dotnet_diagnostic.IDE0057.severity = suggestion
```



### Null 检查首选项

- 严重性级别 ：建议

#### .Net 格式规则

##### csharp_style_throw_expression = true

- 比起三元运算符检查更倾向 null 合并表达式
- 使用合并表达式 (IDE0029 和 IDE0030) 



- 代码示例：

```c#
// dotnet_style_coalesce_expression = true
var v = x ?? y;

// dotnet_style_coalesce_expression = false
var v = x != null ? x : y; // or
var v = x == null ? y : x;
```



##### dotnet_style_null_propagation = true

- 如可能，更倾向使用 null 条件运算符
- 使用 null 传播 (IDE0031)



- 代码示例：

```c#
// dotnet_style_null_propagation = true
var v = o?.ToString();

// dotnet_style_null_propagation = false
var v = o == null ? null : o.ToString(); // or
var v = o != null ? o.String() : null;
```



##### dotnet_style_prefer_is_null_check_over_reference_equality_method = true

-  null 检查优于引用相等性方法
- 使用 is null check (IDE0041) 



- 代码示例：

```c#
// dotnet_style_prefer_is_null_check_over_reference_equality_method = true
if (value is null)
    return;

// dotnet_style_prefer_is_null_check_over_reference_equality_method = false
if (object.ReferenceEquals(value, null))
    return;
```



##### .editorconfig 文件示例：

```ini
# 比起三元运算符检查更倾向 null 合并表达式
dotnet_style_coalesce_expression = true:suggestion
# 如可能，更倾向使用 null 条件运算符
dotnet_style_null_propagation = true:suggestion
# null 检查优于引用相等性方法
dotnet_style_prefer_is_null_check_over_reference_equality_method = true:suggestion

# IDE0029: 使用 COALESCE 表达式
dotnet_diagnostic.IDE0029.severity = suggestion
# IDE0031: 使用 null 传播
dotnet_diagnostic.IDE0031.severity = suggestion
# IDE0041: 使用 "is null" 检查
dotnet_diagnostic.IDE0041.severity = suggestion
```



#### C # 格式规则



##### csharp_style_throw_expression = true

- 更倾向使用 `throw` 表达式，而不是 `throw` 语句
- 使用 throw 表达式 (IDE0016) 



- 代码示例：

```c#
// csharp_style_throw_expression = true
this.s = s ?? throw new ArgumentNullException(nameof(s));

// csharp_style_throw_expression = false
if (s == null) { throw new ArgumentNullException(nameof(s)); }
this.s = s;
```



##### csharp_style_conditional_delegate_call = true

- 在调用 lambda 表达式时，更倾向于使用条件合并运算符 (`?.`) ，而不是执行 null 检查
- 使用条件委托调用 (IDE1005) 



- 代码示例：

```c#
// csharp_style_conditional_delegate_call = true
func?.Invoke(args);

// csharp_style_conditional_delegate_call = false
if (func != null) { func(args); }
```



##### .editorconfig 文件示例：

```ini
# 更倾向使用 `throw` 表达式，而不是 `throw` 语句
csharp_style_throw_expression = true:suggestion
# 在调用 lambda 表达式时，更倾向于使用条件合并运算符 (?.) ，而不是执行 null 检查
csharp_style_conditional_delegate_call = true:suggestion

# IDE0016: 使用 "throw" 表达式
dotnet_diagnostic.IDE0016.severity = suggestion
# IDE1005: 可简化委托调用。
dotnet_diagnostic.IDE1005.severity = suggestion
```





### "var" 首选项

- 严重性级别 ：仅重构



#### C # 格式规则



##### csharp_style_var_for_built_in_types = false

- 使用显示类型声明 `int` 等内置系统类型的变量为首选项，而非使用 `var`
- "var" 首选项 (IDE0007 和 IDE0008) 



- 代码示例：

```c#
// csharp_style_var_for_built_in_types = true
var x = 5;

// csharp_style_var_for_built_in_types = false
int x = 5;
```



##### csharp_style_var_when_type_is_apparent = false

- 声明表达式右侧已提到该类型时，使用显式类型为首选项，而非 `var`
- "var" 首选项 (IDE0007 和 IDE0008) 



- 代码示例：

```c#
// csharp_style_var_when_type_is_apparent = true
var obj = new Customer();

// csharp_style_var_when_type_is_apparent = false
Customer obj = new Customer();
```



##### csharp_style_var_elsewhere = false

- 在任何情况下，显式类型为首选项，而非 `var`，除非由另一个代码样式规则替代
- "var" 首选项 (IDE0007 和 IDE0008) 



- 代码示例：

```c#
// csharp_style_var_elsewhere = true
var f = this.Init();

// csharp_style_var_elsewhere = false
bool f = this.Init();
```



##### .editorconfig 文件示例：

```ini
# 使用显示类型声明 `int` 等内置系统类型的变量为首选项，而非使用 `var`
csharp_style_var_for_built_in_types = false:silent
# 声明表达式右侧已提到该类型时，使用显式类型为首选项，而非 `var`
csharp_style_var_when_type_is_apparent = false:silent
# 在任何情况下，显式类型为首选项，而非 `var`，除非由另一个代码样式规则替代
csharp_style_var_elsewhere = false:silent

```



### Expression-Bodied 成员

- 严重性级别 ：仅重构



#### C # 格式规则



##### csharp_style_expression_bodied_constructors = false

- 倾向于使用构造函数的块主体
- 使用构造函数的表达式主体 (IDE0021) 



- 代码示例：

```c#
// csharp_style_expression_bodied_constructors = true
public Customer(int age) => Age = age;

// csharp_style_expression_bodied_constructors = false
public Customer(int age) { Age = age; }
```



##### csharp_style_expression_bodied_methods = false

- 优先选择方法的块主体
- 方法使用表达式主体(IDE0022) 



- 代码示例：

```c#
// csharp_style_expression_bodied_methods = true
public int GetAge() => this.Age;

// csharp_style_expression_bodied_methods = false
public int GetAge() { return this.Age; }
```



##### csharp_style_expression_bodied_operators = false

- 倾向于使用运算符的块主体
- 将表达式主体用于运算符 (IDE0023 和 IDE0024) 



- 代码示例：

```c#
// csharp_style_expression_bodied_operators = true
public static ComplexNumber operator + (ComplexNumber c1, ComplexNumber c2)
    => new ComplexNumber(c1.Real + c2.Real, c1.Imaginary + c2.Imaginary);

// csharp_style_expression_bodied_operators = false
public static ComplexNumber operator + (ComplexNumber c1, ComplexNumber c2)
{ return new ComplexNumber(c1.Real + c2.Real, c1.Imaginary + c2.Imaginary); }
```



##### csharp_style_expression_bodied_properties = true

- 首选属性的表达式主体
- 属性使用表达式主体 (IDE0025) 



- 代码示例：

```c#
// csharp_style_expression_bodied_properties = true
public int Age => _age;

// csharp_style_expression_bodied_properties = false
public int Age { get { return _age; }}
```



##### csharp_style_expression_bodied_indexers = true

- 首选索引的表达式主体
- 将表达式主体用于索引器 (IDE0026) 



- 代码示例：

```c#
// csharp_style_expression_bodied_indexers = true
public T this[int i] => _values[i];

// csharp_style_expression_bodied_indexers = false
public T this[int i] { get { return _values[i]; } }
```



##### csharp_style_expression_bodied_accessors = true

- 首选取值函数的表达式主体
- 使用访问器的表达式主体(IDE0027) 



- 代码示例：

```c#
// csharp_style_expression_bodied_accessors = true
public int Age { get => _age; set => _age = value; }

// csharp_style_expression_bodied_accessors = false
public int Age { get { return _age; } set { _age = value; } }
```



##### csharp_style_expression_bodied_lambdas = true

- 首选 Lambdas 的表达式主体
- 将表达式主体用于 lambda (IDE0053) 



- 代码示例：

```c#
// csharp_style_expression_bodied_lambdas = true
Func<int, int> square = x => x * x;

// csharp_style_expression_bodied_lambdas = false
Func<int, int> square = x => { return x * x; };
```



##### csharp_style_expression_bodied_local_functions = false

- 首选本地函数的块主体
- 将表达式主体用于本地函数 (IDE0061) 



- 代码示例：

```c#
// csharp_style_expression_bodied_local_functions = true
void M()
{
    Hello();
    void Hello() => Console.WriteLine("Hello");
}

// csharp_style_expression_bodied_local_functions = false
void M()
{
    Hello();
    void Hello()
    {
        Console.WriteLine("Hello");
    }
}
```



##### .editorconfig 文件示例：

```ini
# 倾向于使用构造函数的块主体
csharp_style_expression_bodied_constructors = false:silent
# 优先选择方法的块主体
csharp_style_expression_bodied_methods = false:silent
# 倾向于使用运算符的块主体
csharp_style_expression_bodied_operators = false:silent
# 首选属性的表达式主体
csharp_style_expression_bodied_properties = true:silent
# 首选索引的表达式主体
csharp_style_expression_bodied_indexers = true:silent
# 首选取值函数的表达式主体
csharp_style_expression_bodied_accessors = true:silent
# 首选 Lambdas 的表达式主体
csharp_style_expression_bodied_lambdas = true:silent
# 首选本地函数的块主体
csharp_style_expression_bodied_local_functions = false:silent
```





### 模式匹配首选项

- 严重性级别 ：建议

#### 

#### C # 格式规则



##### csharp_style_pattern_matching_over_as_with_null_check = true

- 倾向于使用模式匹配，而不是带 null 检查的 `as` 表达式，来确定内容是否为某个特定类型
- 使用模式匹配以避免 "as" 后跟 "null" 检查 (IDE0019)



- 代码示例：

```c#
// csharp_style_pattern_matching_over_as_with_null_check = true
if (o is string s) {...}

// csharp_style_pattern_matching_over_as_with_null_check = false
var s = o as string;
if (s != null) {...}
```



##### csharp_style_pattern_matching_over_is_with_cast_check = true

- 倾向于使用模式匹配，而不是带类型强制转换的 `is` 表达式
- 使用模式匹配以避免 "is" 检查后跟强制转换 (IDE0020)



- 代码示例：

```c#
// csharp_style_pattern_matching_over_is_with_cast_check = true
if (o is int i) {...}

// csharp_style_pattern_matching_over_is_with_cast_check = false
if (o is int) {var i = (int)o; ... }
```



##### csharp_style_prefer_switch_expression = true

- 严重性级别 ：仅重构
- 首选使用 `switch` 表达式（使用 C# 8.0 引入）
- 使用 switch expression (IDE0066) 



- 代码示例：

```c#
// csharp_style_prefer_switch_expression = true
return x switch
{
    1 => 1 * 1,
    2 => 2 * 2,
    _ => 0,
};

// csharp_style_prefer_switch_expression = false
switch (x)
{
    case 1:
        return 1 * 1;
    case 2:
        return 2 * 2;
    default:
        return 0;
}
```



##### csharp_style_prefer_pattern_matching = true

- 严重性级别 ：仅重构
- 如果可能 (在 c # 9.0 中引入，则更倾向于使用 [模式匹配](https://docs.microsoft.com/zh-cn/dotnet/csharp/whats-new/csharp-9#pattern-matching-enhancements) 构造) 
- 使用模式匹配 (IDE0078) 



- 代码示例：

```c#
// csharp_style_prefer_pattern_matching = true
var x = i is default(int) or > (default(int));
var y = o is not C c;

// csharp_style_prefer_pattern_matching = false
var x = i == default || i > default(int);
var y = !(o is C c);
```



##### csharp_style_prefer_pattern_matching = true

- 如果在 c # 9.0 中引入了可能 (，则更倾向于使用 ["not"](https://docs.microsoft.com/zh-cn/dotnet/csharp/whats-new/csharp-9#pattern-matching-enhancements) 模式) 
- 使用模式匹配 (`not` 运算符)  (IDE0083) 



- 代码示例：

```c#
// csharp_style_prefer_not_pattern = true
var y = o is not C c;

// csharp_style_prefer_not_pattern = false
var y = !(o is C c);
```



##### .editorconfig 文件示例：

```ini
# 倾向于使用模式匹配，而不是带 null 检查的 as 表达式，来确定内容是否为某个特定类型
csharp_style_pattern_matching_over_as_with_null_check = true:suggestion
# 倾向于使用模式匹配，而不是带类型强制转换的 is 表达式
csharp_style_pattern_matching_over_is_with_cast_check = true:suggestion
# 首选使用 `switch` 表达式（使用 C# 8.0 引入）
csharp_style_prefer_switch_expression = true:silent
# 如果可能 (在 c # 9.0 中引入，则更倾向于使用 模式匹配 构造)
csharp_style_prefer_pattern_matching = true:silent
# 如果在 c # 9.0 中引入了可能 (，则更倾向于使用 "not" 模式) 
csharp_style_prefer_not_pattern = true:suggestion

# IDE0019: 使用模式匹配
dotnet_diagnostic.IDE0019.severity = suggestion
# IDE0020: 使用模式匹配
dotnet_diagnostic.IDE0020.severity = suggestion
```



### 代码块首选项

- 严重性级别 ：建议



#### C # 格式规则



##### csharp_prefer_braces = true

- 使用大括号为首选项，即使只有一个代码行，也是如此
- 添加大括号(IDE0011 ) 



- 代码示例：

```c#
// csharp_prefer_braces = true
if (test) { this.Display(); }

// csharp_prefer_braces = false
if (test) this.Display();
```



##### csharp_prefer_simple_using_statement = false

- 使用大括号为首选项，即使只有一个代码行，也是如此
- 使用简单的 "using" 语句(IDE0063) 



- 代码示例：

```c#
// csharp_prefer_simple_using_statement = true
using var a = b;

// csharp_prefer_simple_using_statement = false
using (var a = b) { }
```



##### .editorconfig 文件示例：

```ini
# 使用大括号为首选项，即使只有一个代码行，也是如此
csharp_prefer_braces = true:suggestion
csharp_prefer_simple_using_statement = false:suggestion

# IDE0011: 添加大括号
dotnet_diagnostic.IDE0011.severity = suggestion
```



### "using" 指令首选项

- 严重性级别 ：建议



#### C # 格式规则



##### csharp_using_directive_placement = outside_namespace

-  首选将 `using` 指令放在名称空间之外
-   "using" 指令的放置 (IDE0065) 



-  代码示例：

```c#
// csharp_using_directive_placement = outside_namespace
using System;

namespace Conventions
{

}

// csharp_using_directive_placement = inside_namespace
namespace Conventions
{
    using System;
}
```



##### .editorconfig 文件示例：

```ini
# 首选将 using 指令放在名称空间之外
csharp_using_directive_placement = outside_namespace:suggestion

# IDE0065: 错放了 using 指令
dotnet_diagnostic.IDE0065.severity = suggestion
```






## 不必要的代码规则

- 严重性级别 ：建议



#### .Net # 格式规则

##### 简化名称 (IDE0001) 

- 此规则没有关联的代码样式选项
- 可以删除不必要的名称限制以简化代码和提高可读性
- 简化名称 (IDE0001) 



- 代码示例：

```c#
using System.IO;
class C
{
    // IDE0001: 'System.IO.FileInfo' can be simplified to 'FileInfo'
    System.IO.FileInfo file;

    // Fixed code
    FileInfo file;
}
```



##### 简化成员访问 (IDE0002) 

- 此规则没有关联的代码样式选项
- 简化类型成员访问（如果可能）
- 简化成员访问 (IDE0002)



- 代码示例：

```c#
static void M1() { }
static void M2()
{
    // IDE0002: 'C.M1' can be simplified to 'M1'
    C.M1();

    // Fixed code
    M1();
}
```



##### 删除不必要的强制转换 (IDE0004) 

- 此规则没有关联的代码样式选项
- 如果代码语义与或不存在，则不需要强制转换表达式
- 删除不必要的强制转换 (IDE0004) 



- 代码示例：

```c#
// Code with violations
int v = (int)0;

// Fixed code
int v = 0;
```



##### 删除不必要的导入 (IDE0005) 

- 此规则没有关联的代码样式选项
- 删除不必要的 using 指令
- 删除不必要的导入 (IDE0005) 



- 代码示例：

```c#
// Code with violations
using System;
using System.IO;    // IDE0005: Using directive is unnecessary
class C
{
    public static void M()
    {
        Console.WriteLine("Hello");
    }
}

// Fixed code
using System;
class C
{
    public static void M()
    {
        Console.WriteLine("Hello");
    }
}
```



##### (IDE0035) 删除无法访问的代码

- 此规则没有关联的代码样式选项
- 删除方法和属性内无法访问的可执行代码
- (IDE0035) 删除无法访问的代码



- 代码示例：

```c#
// Code with violations
void M()
{
    throw new System.Exception();

    // IDE0035: Remove unreachable code
    int v = 0;
}

// Fixed code
void M()
{
    throw new System.Exception();
}
```



##### 删除未使用的私有成员 (IDE0051) 

- 此规则没有关联的代码样式选项
- 标记没有读取或写入引用的未使用的私有方法、字段、属性和事件
- 删除未使用的私有成员 (IDE0051) 



- 代码示例：

```c#
// Code with violations
class C
{
    // IDE0051: Remove unused private members
    private readonly int _fieldPrivate;
    private int PropertyPrivate => 1;
    private int GetNumPrivate() => 1;

    // No IDE0051
    internal readonly int FieldInternal;
    private readonly int _fieldPrivateUsed;
    public int PropertyPublic => _fieldPrivateUsed;
    private int GetNumPrivateUsed() => 1;
    internal int GetNumInternal() => GetNumPrivateUsed();
    public int GetNumPublic() => GetNumPrivateUsed();
}

// Fixed code
class C
{
    // No IDE0051
    internal readonly int FieldInternal;
    private readonly int _fieldPrivateUsed;
    public int PropertyPublic => _fieldPrivateUsed;
    private int GetNumPrivateUsed() => 1;
    internal int GetNumInternal() => GetNumPrivateUsed();
    public int GetNumPublic() => GetNumPrivateUsed();
}
```



##### 删除未读的私有成员 (IDE0052) 

- 此规则没有关联的代码样式选项
- 标记具有一个或多个写入引用但没有读取引用的私有字段和属性
- 删除未读的私有成员 (IDE0052) 



- 代码示例：

```c#
// Code with violations
class C
{
    // IDE0052: Remove unread private members
    private readonly int _field1;
    private int _field2;
    private int Property { get; set; }

    public C()
    {
        _field1 = 0;
    }

    public void SetMethod()
    {
        _field2 = 0;
        Property = 0;
    }
}

// Fixed code
class C
{
    public C()
    {
    }

    public void SetMethod()
    {
    }
}
```



##### .editorconfig 文件示例：

```ini
# IDE0001: 可以简化名称
dotnet_diagnostic.IDE0001.severity = suggestion
# IDE0002: 可以简化成员访问
dotnet_diagnostic.IDE0002.severity = suggestion
# IDE0004: 转换是多余的
dotnet_diagnostic.IDE0004.severity = suggestion
# IDE0005: using 指令是不需要的
dotnet_diagnostic.IDE0005.severity = suggestion
# IDE0035: 删除无法访问的可执行代码
dotnet_diagnostic.IDE0035.severity = suggestion
# IDE0051: 删除未使用的私有成员
dotnet_diagnostic.IDE0051.severity = suggestion
# IDE0052: 删除未读的私有成员
dotnet_diagnostic.IDE0052.severity = suggestion
```



#### C # 格式规则



##### csharp_style_unused_value_expression_statement_preference = discard_variable

- 严重性级别 ：仅重构
- 首选将未使用的表达式分配给 discard
- 删除不必要的表达式值 (IDE0058) 
- 如果表达式没有副作用，则删除整个语句。 这样可避免不必要的计算，从而提高性能。
- 如果该表达式有副作用，请将赋值的左侧替换为从未使用过 的或局部变量 。 这可以提高代码的清晰度，并明确地丢弃未使用的值。 此规则的选项涉及使用丢弃和未使用的局部变量。



- 代码示例：

```c#
// Original code:
System.Convert.ToInt32("35");

// After code fix for IDE0058:

// csharp_style_unused_value_expression_statement_preference = discard_variable
_ = System.Convert.ToInt32("35");

// csharp_style_unused_value_expression_statement_preference = unused_local_variable
var unused = Convert.ToInt32("35");
```



##### csharp_style_unused_value_assignment_preference = discard_variable

- 在分配未使用的值时，首选使用 discard
- 删除不必要的值赋值 (IDE0059) 
- 如果表达式没有副作用，则删除整个语句。 这样可避免不必要的计算，从而提高性能。
- 如果该表达式有副作用，请将赋值的左侧替换为从未使用过 的或局部变量 。 这可以提高代码的清晰度，并明确地丢弃未使用的值。 此规则的选项涉及使用丢弃和未使用的局部变量。



- 代码示例：

```c#
// csharp_style_unused_value_assignment_preference = discard_variable
int GetCount(Dictionary<string, int> wordCount, string searchWord)
{
    _ = wordCount.TryGetValue(searchWord, out var count);
    return count;
}

// csharp_style_unused_value_assignment_preference = unused_local_variable
int GetCount(Dictionary<string, int> wordCount, string searchWord)
{
    var unused = wordCount.TryGetValue(searchWord, out var count);
    return count;
}
```



##### dotnet_code_quality_unused_parameters = all

- 标记具有包含未使用的参数的任何可访问性的方法

- 删除未使用的参数 (IDE0060) 

  

- 代码示例：

```c#
// dotnet_code_quality_unused_parameters = all
public int GetNum1(int unusedParam) { return 1; }
internal int GetNum2(int unusedParam) { return 1; }
private int GetNum3(int unusedParam) { return 1; }

// dotnet_code_quality_unused_parameters = non_public
internal int GetNum4(int unusedParam) { return 1; }
private int GetNum5(int unusedParam) { return 1; }
```



##### csharp_style_unused_value_assignment_preference = discard_variable

- 标记具有包含未使用的参数的任何可访问性的方法

- 删除不必要的抑制 (IDE0079) 

  

- 代码示例：

```c#
using System.Diagnostics.CodeAnalysis;

class C1
{
    // 'dotnet_remove_unnecessary_suppression_exclusions = IDE0051'

    // Unnecessary pragma suppression, but not flagged by IDE0079
#pragma warning disable IDE0051 // IDE0051: Remove unused member
    private int UsedMethod() => 0;
#pragma warning restore IDE0051

    public int PublicMethod() => UsedMethod();
}
```



##### 删除不必要的抑制运算符 (IDE0080) 

- 严重性级别 ：建议

- 此规则没有关联的代码样式选项

- 删除不必要的抑制运算符 (IDE0080) 

  

- 代码示例：

```c#
// Code with violations
if (o !is string) { }

// Potential fixes:
// 1.
if (o is not string) { }

// 2.
if (o is string == false) { }
```



##### 删除不必要的相等运算符 (IDE0100)

- 严重性级别 ：仅重构

- 此规则没有关联的代码样式选项

- 因为！容易与字母I 混淆，不建议使用if (!N()) .

-  != 符号可以使用

  

- 代码示例：

```c#
// 正例
if (M()) { }
if (M() == true) { }
if (N() == false) { }
if (i != 100) { }

// 反例
if (M() != false) { }
if (N() != true) { }
// 因为！容易与字母I 混淆，不建议使用if (!N()) 
if (!N()) { }
if (!(i == 100) { }
```



##### 删除不必要的丢弃 (IDE0110) 

- 严重性级别 ：仅重构

- 此规则没有关联的代码样式选项

  

- 代码示例：

```c#
// Code with violations
switch (o)
{
    case int _:
        Console.WriteLine("Value was an int");
        break;
    case string _:
        Console.WriteLine("Value was a string");
        break;
}

// Fixed code
switch (o)
{
    case int:
        Console.WriteLine("Value was an int");
        break;
    case string:
        Console.WriteLine("Value was a string");
        break;
}
```



##### .editorconfig 文件示例：

```ini
# IDE0058: 永远不会使用表达式值
csharp_style_unused_value_expression_statement_preference = discard_variable:silent
# IDE0059: 不需要赋值
csharp_style_unused_value_assignment_preference = discard_variable:suggestion
# IDE0060: 删除未使用的参数
dotnet_code_quality_unused_parameters = all:suggestion
# IDE0079: 删除不必要的忽略
dotnet_remove_unnecessary_suppression_exclusions = none:suggestion
# IDE0080: 删除不必要的抑制运算符
dotnet_diagnostic.IDE0080.severity = suggestion
# IDE0100: 删除不必要的相等运算符
dotnet_diagnostic.IDE0100.severity = silent
# IDE0110: 删除不必要的丢弃
dotnet_diagnostic.IDE0110.severity = silent
```




## 杂项规则

- 严重性级别 ：仅重构

#### .Net # 格式规则



##### 删除无效的全局 "SuppressMessageAttribute" (IDE0076) 

- 严重性级别 ：仅重构

- 此规则没有关联的代码样式选项

  

- 代码示例：

```c#
// IDE0076: Invalid target '~F:N.C.F2' - no matching field named 'F2'
[assembly: System.Diagnostics.CodeAnalysis.SuppressMessage("Category", "Id: Title", Scope = "member", Target = "~F:N.C.F2")]
// IDE0076: Invalid scope 'property'
[assembly: System.Diagnostics.CodeAnalysis.SuppressMessage("Category", "Id: Title", Scope = "property", Target = "~P:N.C.P")]

// Fixed code
[assembly: System.Diagnostics.CodeAnalysis.SuppressMessage("Category", "Id: Title", Scope = "member", Target = "~F:N.C.F")]
[assembly: System.Diagnostics.CodeAnalysis.SuppressMessage("Category", "Id: Title", Scope = "member", Target = "~P:N.C.P")]

namespace N
{
    class C
    {
        public int F;
        public int P { get; }
    }
}
```



##### 避免全局 "SuppressMessageAttribute" 中的旧格式目标 (IDE0077) 

- 严重性级别 ：仅重构

- 此规则没有关联的代码样式选项

  

- 代码示例：

```c#
// IDE0077: Legacy format target 'N.C.#F'
[assembly: System.Diagnostics.CodeAnalysis.SuppressMessage("Category", "Id: Title", Scope = "member", Target = "N.C.#F")]

// Fixed code
[assembly: System.Diagnostics.CodeAnalysis.SuppressMessage("Category", "Id: Title", Scope = "member", Target = "~F:N.C.F")]

namespace N
{
    class C
    {
        public int F;
    }
}
```



`提示 Visual Studio 2019 16.7 或更高版本提供了一个代码修补程序，用于将属性的自动修复 Target 为建议格式。`



##### .editorconfig 文件示例：

```ini
# IDE0076: 全局 "SuppressMessageAttribute" 无效
dotnet_diagnostic.IDE0076.severity = suggestion
# IDE0077: 避免在 "SuppressMessageAttribute" 中使用旧格式目标
dotnet_diagnostic.IDE0077.severity = suggestion
```




## 格式设置规则
格式设置规则会影响 .NET 编程语言构造的缩进、空格和新行的对齐方式。 
这些规则分为以下几类：
- .Net 格式规则
- C # 格式设置规则


### 规则 ID： "IDE0055" (修复格式设置) 
- 严重性级别 ：建议
- 为了统一代码外观。即使没有严重性级别的格式规则也需准守。
```ini
# IDE0055: 修正格式
dotnet_diagnostic.IDE0055.severity = suggestion
```



### 新行首选项



#### .Net 格式规则
- 严重性级别 ：无
```ini
# 换行符
end_of_line = crlf
# 是否使文件以一个空白行结尾
insert_final_newline = false
# 是否将行尾空格自动删除
trim_trailing_whitespace = true
# 文件的charset
charset = utf-8
```



#### C # 格式规则



##### csharp_new_line_before_open_brace = all
- 对于所有表达式，需要将大括号置于新行
- 代码示例：

```c#
// csharp_new_line_before_open_brace = all
void MyMethod()
{
    if (...)
    {
        ...
    }
}

// csharp_new_line_before_open_brace = none
void MyMethod() {
    if (...) {
        ...
    }
}
```



##### sharp_new_line_before_else = true
- 将 else 语句置于新行
- 代码示例：

```c#
// csharp_new_line_before_else = true
if (...) 
{
    ...
}
else 
{
    ...
}

// csharp_new_line_before_else = false
if (...) {
    ...
} else {
    ...
}
```



##### csharp_new_line_before_catch = true
- 将 catch 语句置于新行
##### csharp_new_line_before_finally = true
- 将 finally 语句置于新行
- 代码示例：

```c#
// csharp_new_line_before_catch = true
// csharp_new_line_before_finally = true
try 
{
    ...
}
catch (Exception e) 
{
    ...
}
finally 
{
    ...
}

// sharp_new_line_before_catch = false
// csharp_new_line_before_finally = false
try {
    ...
} catch (Exception e) {
    ...
} finally {
    ...
}
```



##### csharp_new_line_before_members_in_object_initializers = true
- 需要将对象初始值设定项的成员置于单独的行
- 代码示例：

```c#
// csharp_new_line_before_members_in_object_initializers = true
var z = new B()
{
    A = 3,
    B = 4
}

// csharp_new_line_before_members_in_object_initializers = false
var z = new B()
{
    A = 3, B = 4
}
```



##### csharp_new_line_before_members_in_anonymous_types = true
- 需要将匿名类型的成员置于单独的行
- 代码示例：

```c#
// csharp_new_line_before_members_in_anonymous_types = true
var z = new
{
    A = 3,
    B = 4
}

// csharp_new_line_before_members_in_anonymous_types = false
var z = new
{
    A = 3, B = 4
}
```



##### csharp_new_line_between_query_expression_clauses = true
- 要求将查询表达式子句的元素置于单独的行
- 代码示例：

```c#
// csharp_new_line_between_query_expression_clauses = true
var q = from a in e
        from b in e
        select a * b;

// csharp_new_line_between_query_expression_clauses = false
var q = from a in e from b in e
        select a * b;
```



##### .editorconfig 文件示例：
```ini
# 对于所有表达式，需要将大括号置于新行
csharp_new_line_before_open_brace = all
# 将 else 语句置于新行
csharp_new_line_before_else = true
# 将 catch 语句置于新行
csharp_new_line_before_catch = true
# 需要将 finally 语句置于右大括号后的新行
csharp_new_line_before_finally = true
# 需要将对象初始值设定项的成员置于单独的行
csharp_new_line_before_members_in_object_initializers = true
# 需要将匿名类型的成员置于单独的行
csharp_new_line_before_members_in_anonymous_types = true
# 要求将查询表达式子句的元素置于单独的行
csharp_new_line_between_query_expression_clauses = true
```


### 缩进和间距
- 严重性级别 ：无



#### .Net 格式规则
```ini
# 缩进大小
indent_size = 4
# 插入空格
indent_style = space
# Tab符大小
tab_width = 4
```



#### C # 格式规则
​	这些格式设置规则与是否使用缩进设置代码的格式有关。



##### csharp_indent_case_contents = true
- 缩进 switch case 内容
- 代码示例：

```c#
// csharp_indent_case_contents = true
switch(c) 
{
    case Color.Red:
        Console.WriteLine("The color is red");
        break;
    case Color.Blue:
        Console.WriteLine("The color is blue");
        break;
    default:
        Console.WriteLine("The color is unknown.");
        break;
}

// csharp_indent_case_contents = false
switch(c) 
{
    case Color.Red:
    Console.WriteLine("The color is red");
    break;
    case Color.Blue:
    Console.WriteLine("The color is blue");
    break;
    default:
    Console.WriteLine("The color is unknown.");
    break;
}
```



##### csharp_indent_switch_labels = true
- 缩进 switch 标签
- 代码示例：

```c#
// csharp_indent_switch_labels = true
switch(c) 
{
    case Color.Red:
        Console.WriteLine("The color is red");
        break;
    case Color.Blue:
        Console.WriteLine("The color is blue");
        break;
    default:
        Console.WriteLine("The color is unknown.");
        break;
}

// csharp_indent_switch_labels = false
switch(c) 
{
case Color.Red:
    Console.WriteLine("The color is red");
    break;
case Color.Blue:
    Console.WriteLine("The color is blue");
    break;
default:
    Console.WriteLine("The color is unknown.");
    break;
}
```



##### csharp_indent_labels = one_less_than_current
- 将标签置于比当前上下文少一个缩进的位置
- 代码示例：

```c#
// csharp_indent_labels= flush_left
class C
{
    private string MyMethod(...)
    {
        if (...) {
            goto error;
        }
error:
        throw new Exception(...);
    }
}

// csharp_indent_labels = one_less_than_current
class C
{
    private string MyMethod(...)
    {
        if (...) {
            goto error;
        }
    error:
        throw new Exception(...);
    }
}

// csharp_indent_labels= no_change
class C
{
    private string MyMethod(...)
    {
        if (...) {
            goto error;
        }
        error:
        throw new Exception(...);
    }
}

```
- 备注：goto语句可能会使代码可读性变得复杂，多数情况下使用if, switch, catch, return等语句可以达到goto相同的作用。
- 原则上不使用goto语句。除非goto语句比其他方式在效率，安全等方面更好的情况下使用。
- 上面例子中的语句可变为
```c#
class C
{
    private string MyMethod(...)
    {
        if (...) {
            throw new Exception(...);
        }
    }
}
```



##### csharp_indent_block_contents = true
- 将方法内容置于比当前上下文少一个缩进的位置
- 代码示例：

```c#
// csharp_indent_block_contents = true
static void Hello()
{
    Console.WriteLine("Hello");
}

// csharp_indent_block_contents = false
static void Hello()
{
Console.WriteLine("Hello");
}
```



##### csharp_indent_braces = false
- 将方法括号{}置于比当前上下文少一个缩进的位置
- 代码示例：

```c#
// csharp_indent_braces = true
static void Hello()
    {
    Console.WriteLine("Hello");
    }

// csharp_indent_braces = false
static void Hello()
{
    Console.WriteLine("Hello");
}
```



##### csharp_indent_case_contents_when_block = true
- 将case括号{}置于比当前上下文少一个缩进的位置
- 代码示例：

```c#
// csharp_indent_case_contents_when_block = true
case 0:
    {
        Console.WriteLine("Hello");
        break;
    }

// csharp_indent_case_contents_when_block = false
case 0:
{
    Console.WriteLine("Hello");
    break;
}
```
- 备注：当case :括号体的内容不超过10行的情况下，建议简化为如下
```c#
case 0:
    Console.WriteLine("Hello_01");
    Console.WriteLine("Hello_02");
    Console.WriteLine("Hello_03");
        break;
case 1:
    Console.WriteLine("Hello_11");
    Console.WriteLine("Hello_12");
    Console.WriteLine("Hello_13");
        break;
```



##### .editorconfig 文件示例：

```ini
# 缩进 switch case 内容
csharp_indent_case_contents = true
# 缩进 switch 标签
csharp_indent_switch_labels = true
# 将标签置于比当前上下文少一个缩进的位置
csharp_indent_labels = one_less_than_current
# 将方法内容置于比当前上下文少一个缩进的位置
csharp_indent_block_contents = true
# 将方法括号{}置于比当前上下文少一个缩进的位置
csharp_indent_braces = false
# 将case括号{}置于比当前上下文少一个缩进的位置
csharp_indent_case_contents_when_block = true
```


### 空格键首选项
- 严重性级别 ：无
- 这些格式设置规则与是否使用空格字符设置代码的格式有关。



#### C # 格式规则



##### csharp_space_after_cast = false
-  删除转换和值之间的空格
- 代码示例：

```c#
// csharp_space_after_cast = true
int y = (int) x;

// csharp_space_after_cast = false
int y = (int)x;
```



##### csharp_space_after_keywords_in_control_flow_statements = true
-  在控制流语句（如 for 循环）中的关键字后放置空格字符
- 代码示例：

```c#
// csharp_space_after_keywords_in_control_flow_statements = true
for (int i; i < x; i++) { ... }

// csharp_space_after_keywords_in_control_flow_statements = false
for(int i; i < x; i++) { ... }
```



##### csharp_space_between_parentheses = false
-  括号之间不放置空格
-  如果省略此规则或使用 control_flow_statements、expressions 或 type_casts 以外的值，则不会应用该设置。
- 代码示例：

```c#
// csharp_space_between_parentheses = false
for (int i = 0; i < 10; i++) { }
var z = (x * y) - ((y - x) * 3);
int y = (int)x;

// csharp_space_between_parentheses = control_flow_statements
for ( int i = 0; i < 10; i++ ) { }

// csharp_space_between_parentheses = expressions
var z = ( x * y ) - ( ( y - x ) * 3 );

// csharp_space_between_parentheses = type_casts
int y = ( int )x;
```



##### csharp_space_before_colon_in_inheritance_clause = true
-  在类型声明中的基或接口冒号前放置空格字符
- 代码示例：

```c#
interface I
{

}

// csharp_space_before_colon_in_inheritance_clause = true
class C : I
{

}

// csharp_space_before_colon_in_inheritance_clause = false
class C: I
{

}
```



##### csharp_space_after_colon_in_inheritance_clause = true
-  在类型声明中的基或接口冒号后放置空格字符
- 代码示例：

```c#
interface I
{

}

// csharp_space_after_colon_in_inheritance_clause = true
class C : I
{

}

// csharp_space_after_colon_in_inheritance_clause = false
class C :I
{

}
```



##### csharp_space_around_binary_operators = before_and_after
-  在二元运算符前后插入空格
- 代码示例：

```c#
// csharp_space_around_binary_operators = before_and_after
return x * (x - y);

// csharp_space_around_binary_operators = none
return x*(x-y);

// csharp_space_around_binary_operators = ignore
return x  *  (x-y);
```



##### csharp_space_between_method_declaration_parameter_list_parentheses = false
-  删除方法声明参数列表的左括号之后和右括号之前的空格字符
- 代码示例：

```c#
// csharp_space_between_method_declaration_parameter_list_parentheses = true
void Bark( int x ) { ... }

// csharp_space_between_method_declaration_parameter_list_parentheses = false
void Bark(int x) { ... }
```



##### csharp_space_between_method_declaration_empty_parameter_list_parentheses = false
-  删除方法声明的空参数列表括号内的空格
- 代码示例：

```c#
// csharp_space_between_method_declaration_empty_parameter_list_parentheses = true
void Goo( )
{
}

// csharp_space_between_method_declaration_empty_parameter_list_parentheses = false
void Goo()
{
}
```



##### csharp_space_between_method_declaration_name_and_open_parenthesis = false
-  删除方法声明中方法名称和左括号之间的空格字符
- 代码示例：

```c#
// csharp_space_between_method_declaration_name_and_open_parenthesis = true
void M () { }

// csharp_space_between_method_declaration_name_and_open_parenthesis = false
void M() { }
```



##### csharp_space_between_method_call_parameter_list_parentheses = false
-  删除方法调用的左括号之后和右括号之前的空格字符
- 代码示例：

```c#
// csharp_space_between_method_call_parameter_list_parentheses = true
MyMethod( argument );

// csharp_space_between_method_call_parameter_list_parentheses = false
MyMethod(argument);
```



##### csharp_space_between_method_call_empty_parameter_list_parentheses = false
-  删除空参数列表括号内的空格
- 代码示例：

```c#
// csharp_space_between_method_call_empty_parameter_list_parentheses = true
void Goo(int x)
{
    Goo( );
}

// csharp_space_between_method_call_empty_parameter_list_parentheses = false
void Goo(int x)
{
    Goo();
}
```



##### csharp_space_between_method_call_name_and_opening_parenthesis = false
-  删除方法调用名称和左括号之间的空格
- 代码示例：

```c#
// csharp_space_between_method_call_name_and_opening_parenthesis = true
void Goo()
{
    Goo (1);
}

// csharp_space_between_method_call_name_and_opening_parenthesis = false
void Goo()
{
    Goo(1);
}
```



##### csharp_space_after_comma = true
-  在逗号后面插入空格
- 代码示例：

```c#
// csharp_space_after_comma = true
int[] x = new int[] { 1, 2, 3, 4, 5 };

// csharp_space_after_comma = false
int[] x = new int[] { 1,2,3,4,5 }
```



##### csharp_space_before_comma = false
-  删除逗号前的空格
- 代码示例：

```c#
// csharp_space_before_comma = true
int[] x = new int[] { 1 , 2 , 3 , 4 , 5 };

// csharp_space_before_comma = false
int[] x = new int[] { 1, 2, 3, 4, 5 };
```



##### csharp_space_after_dot = false
-  删除点号后面的空格
- 代码示例：

```c#
// csharp_space_after_dot = true
this. Goo();

// csharp_space_after_dot = false
this.Goo();
```



##### csharp_space_before_dot = false
-  删除点前的空格
- 代码示例：

```c#
// csharp_space_before_dot = true
this .Goo();

// csharp_space_before_dot = false
this.Goo();
```



##### csharp_space_after_semicolon_in_for_statement = true
-  在 for 语句中的每个分号后面插入空格
- 代码示例：

```c#
// csharp_space_after_semicolon_in_for_statement = true
for (int i = 0; i < x.Length; i++)

// csharp_space_after_semicolon_in_for_statement = false
for (int i = 0;i < x.Length;i++)
```



##### csharp_space_before_semicolon_in_for_statement = false

-  删除 for 语句中每个分号前的空格
- 代码示例：

```c#
// csharp_space_before_semicolon_in_for_statement = true
for (int i = 0 ; i < x.Length ; i++)

// csharp_space_before_semicolon_in_for_statement = false
for (int i = 0; i < x.Length; i++)
```



##### csharp_space_around_declaration_statements = false

-  删除声明语句中多余的空格字符
-  代码示例：

```c#
// csharp_space_around_declaration_statements = ignore
int    x    =    0   ;

// csharp_space_around_declaration_statements = false
int x = 0;
```



##### csharp_space_before_open_square_brackets = false

-  删除左方括号 `[` 前的空格
-  代码示例：

```c#
// csharp_space_before_open_square_brackets = true
int [] numbers = new int [] { 1, 2, 3, 4, 5 };

// csharp_space_before_open_square_brackets = false
int[] numbers = new int[] { 1, 2, 3, 4, 5 };
```



##### csharp_space_between_empty_square_brackets = false

-  删除空方括号 `[]` 之间的空格
-  代码示例：

```c#
// csharp_space_between_empty_square_brackets = true
int[ ] numbers = new int[ ] { 1, 2, 3, 4, 5 };

// csharp_space_between_empty_square_brackets = false
int[] numbers = new int[] { 1, 2, 3, 4, 5 };
```



##### csharp_space_between_square_brackets = false

-  删除非空方括号 `[0]` 中的空格字符
-  代码示例：

```c#
// csharp_space_between_square_brackets = true
int index = numbers[ 0 ];

// csharp_space_between_square_brackets = false
int index = numbers[0];
```



#####  .editorconfig 文件示例：
```ini
# 删除转换和值之间的空格
csharp_space_after_cast = false
# 在控制流语句（如 for 循环）中的关键字后放置空格字符
csharp_space_after_keywords_in_control_flow_statements = true
# 括号之间不放置空格
csharp_space_between_parentheses = false
# 在类型声明中的基或接口冒号前放置空格字符
csharp_space_before_colon_in_inheritance_clause = true
# 在类型声明中的基或接口冒号后放置空格字符
csharp_space_after_colon_in_inheritance_clause = true
# 在二元运算符前后插入空格
csharp_space_around_binary_operators = before_and_after
# 删除方法声明参数列表的左括号之后和右括号之前的空格字符
csharp_space_between_method_declaration_parameter_list_parentheses = false
# 删除方法声明的空参数列表括号内的空格
csharp_space_between_method_declaration_empty_parameter_list_parentheses = false
# 删除方法声明中方法名称和左括号之间的空格字符
csharp_space_between_method_declaration_name_and_open_parenthesis = false
# 删除方法调用的左括号之后和右括号之前的空格字符
csharp_space_between_method_call_parameter_list_parentheses = false
# 删除空参数列表括号内的空格
csharp_space_between_method_call_empty_parameter_list_parentheses = false
# 删除方法调用名称和左括号之间的空格
csharp_space_between_method_call_name_and_opening_parenthesis = false
# 在逗号后面插入空格
csharp_space_after_comma = true
# 删除逗号前的空格
csharp_space_before_comma = false
# 删除点号后面的空格
csharp_space_after_dot = false
# 删除点号前的空格
csharp_space_before_dot = false
# 在 for 语句中的每个分号后面插入空格
csharp_space_after_semicolon_in_for_statement = true
# 删除 for 语句中每个分号前的空格
csharp_space_before_semicolon_in_for_statement = false
# 删除声明语句中多余的空格字符
csharp_space_around_declaration_statements = false
# 删除左方括号 `[` 前的空格
csharp_space_before_open_square_brackets = false
# 删除空方括号 `[]` 之间的空格
csharp_space_between_empty_square_brackets = false
# 删除非空方括号 [0] 中的空格字符
csharp_space_between_square_brackets = false
```



### 换行选项
- 严重性级别 ：无
- 这些格式设置规则与语句和代码块中单一行以及单独的行的使用有关。



#### C # 格式规则



##### csharp_preserve_single_line_statements = false

-  语句和成员声明保留在不同行上
-  代码示例：

```c#
//csharp_preserve_single_line_statements = true
int i = 0; string name = "John";

//csharp_preserve_single_line_statements = false
int i = 0;
string name = "John";
```





##### csharp_preserve_single_line_blocks = true

-  将代码块保留在单个行上
-  代码示例：

```c#
//csharp_preserve_single_line_blocks = true
public int Foo { get; set; }

//csharp_preserve_single_line_blocks = false
public int Foo
{
    get; set;
}
```



##### .editorconfig 文件示例：

```ini
# 将语句和成员声明保留在不同行上
csharp_preserve_single_line_statements = false
# 将代码块保留在单个行上
csharp_preserve_single_line_blocks = true
```



### using 指令选项

- 严重性级别 ：建议
- 这些格式设置规则与语句和代码块中单一行以及单独的行的使用有关。



#### .Net 格式规则



##### dotnet_sort_system_directives_first = true

-  `System.*` `using` 按字母顺序排序指令，并将其放在其他 using 指令之前
-  代码示例：

```c#
// dotnet_sort_system_directives_first = true
using System.Collections.Generic;
using System.Threading.Tasks;
using Octokit;

// dotnet_sort_system_directives_first = false
using Octokit;
using System.Collections.Generic;
using System.Threading.Tasks;
```



##### dotnet_separate_import_directive_groups = false

-  不在 `using` 指令组之间放置空白行
-  代码示例：

```c#
// dotnet_separate_import_directive_groups = true
using System.Collections.Generic;
using System.Threading.Tasks;

using Octokit;

// dotnet_separate_import_directive_groups = false
using System.Collections.Generic;
using System.Threading.Tasks;
using Octokit;
```



##### .editorconfig 文件示例：

```ini
# .NET formatting rules
# System.* using 按字母顺序排序指令，并将其放在其他 using 指令之前
dotnet_sort_system_directives_first = true
# 不在 using 指令组之间放置空白行
dotnet_separate_import_directive_groups = false
```



### 注释约定



#### 文件头注释



##### Copyright 2021 Pactera Corp. All rights reserved.

- 只有新规文件时，需要指定的文件头

- 注意其中第一行中的2021不要改为其他年份

- 既存代码修改时，有文件的头的不需修改和添加履历。（日期和做成者不可以修改）

  

- 代码示例：

```c#
/// =============================================================================
/// Copyright 2021 Pactera Corp. All rights reserved.
/// =============================================================================
/// Date Name                      Comment
/// =============================================================================
/// 2021.03.05  Yang Bin           初版作成
/// =============================================================================

using System.Threading;
using System;
using System.IO;
```



#### 类和类成员注释

- 类和类成员的注释使用 三斜线+空格 /// <summary>
- <summary> <param> <returns>内需要简洁说明含义或用途
- 不容易说明的内容可以在 <remarks>中补充说明



- 代码示例：

```c#
/// =============================================================================
/// Copyright 2021 Pactera Corp. All rights reserved.
/// =============================================================================
/// Date Name                      Comment
/// =============================================================================
/// 2021.03.05  Yang Bin           初版作成
/// =============================================================================

/// <summary>
/// Class level summary documentation goes here.
/// </summary>
/// <remarks>
/// Longer comments can be associated with a type or member through
/// the remarks tag.
/// </remarks>
public class TestClass : TestInterface
{
    /// <summary>Store for the name property.</summary>
    private string _name = null;

    /// <summary>The class constructor. </summary>
    public TestClass()
    {
        // Add Constructor Logic here.
    }

    /// <summary>Name property. </summary>
    /// 
    /// <value>
    /// A value tag is used to describe the property value.
    /// </value>
    public string Name
    {
        get
        {
            if (_name == null)
            {
                throw new System.Exception("Name is null");
            }
            return _name;
        }
    }

    /// <summary>
    /// Description for SomeMethod.
    /// </summary>
    /// <param name="s"> Parameter description for s goes here.</param>
    /// <seealso cref="System.String">
    /// You can use the cref attribute on any tag to reference a type or member 
    /// and the compiler will check that the reference exists. 
    /// </seealso>
    public void SomeMethod(string s)
    {
    }

    /// <summary>
    /// Some other method. 
    /// </summary>
    /// <returns>Return results are described through the returns tag.</returns>
    /// <seealso cref="SomeMethod(string)">
    /// Notice the use of the cref attribute to reference a specific method. </seealso>
    public int SomeOtherMethod()
    {
        return 0;
    }

    /// <summary>
    /// Documentation that describes the method goes here.
    /// </summary>
    /// <param name="n">
    /// Parameter n requires an integer argument.
    /// </param>
    /// <returns>
    /// The method returns an integer.
    /// </returns>
    public int InterfaceMethod(int n)
    {
        return n * n;
    }

    /// <summary>
    /// The entry point for the application.
    /// </summary>
    /// <param name="args"> A list of command line arguments.</param>
    /// <returns>
    /// The method returns an integer.
    /// </returns>
    static int Main(System.String[] args)
    {
        // Add code to start application here.
        return 0;
    }
}

/// <summary>
/// Documentation that describes the interface goes here.
/// </summary>
/// <remarks>
/// Details about the interface go here.
/// </remarks>
interface TestInterface
{
    /// <summary>
    /// Documentation that describes the method goes here.
    /// </summary>
    /// <param name="n">
    /// Parameter n requires an integer argument.
    /// </param>
    /// <returns>
    /// The method returns an integer.
    /// </returns>
    int InterfaceMethod(int n);
}
```



#### 方法内容注释

方法注释内容注释

- 方法内注释使用 双斜线+空格 //

- 待评审或Review的代码中不得含有 TODO , T.B.D

  

- ❌ 代码示例：

```c#
    /// <summary>Name property. </summary>
    /// 
    /// <value>
    /// A value tag is used to describe the property value.
    /// </value>
    public string Name
    {
        get
        {
            // T.B.D 未定
            if (_name == null)
            {
                // TODO 未定
                throw new System.Exception("Name is null");
            }
            return _name;
        }
    }
```





注释的内容的目的是为了方便理解代码，但不是每一行都需要注销。只在不容易理解和重要的地方需要。

- 分支语句，if, switch等
- 异常语句  catch (Exception e)等
- 方法内调用其他方法，通知，事件时
- 重要的计算，数据转化

  

- ✔️ 代码示例：

```c#
        /// <summary>
        /// 清空文件夹中的内容
        /// </summary>
        /// <param name="dirPath">文件夹路径</param>
        /// <param name="deleteSub">是否删除子文件夹 true:删除子文件夹 false:不删除子文件夹</param>
        /// <returns>错误代码 ERROR_CODE_SUCCESS：成功 其他：失败</returns>
        internal static int ClearOutDir(string dirPath, bool deleteSub = false)
        {
            try
            {
                // 目标文件夹存在的场合
                if (Directory.Exists(dirPath) == true)
                {
                    var dirInfo = new DirectoryInfo(dirPath);
                    // 返回目录中所有文件和子目录
                    var fileInfos = dirInfo.GetFileSystemInfos();
                    // 循环文件夹所有元素（子文件 子文件夹）
                    foreach (var item in fileInfos)
                    {
                        // 子文件的场合
                        if (item is FileInfo == true)
                        {
                            // 删除子文件
                            File.Delete(item.FullName);
                        }

                        // 子文件夹的场合, 并且需要删除子文件夹的场合
                        if (item is DirectoryInfo == true && deleteSub == true)
                        {
                            // 删除子文件夹
                            DirectoryInfo subdir = new DirectoryInfo(item.FullName);
                            subdir.Delete(true);
                        }
                    }
                }
            }
            // 发生异常的场合
            catch(Exception)
            {
                return ErrorCodeMessage.ERROR_CODE_CLEAR_OUT_DIR;
            }

            // 正常结束是，返回成功。
            return ErrorCodeMessage.ERROR_CODE_SUCCESS;
        }
```




## 命名规则



### 命名准则

本章的目的是提供一组一致的命名约定，以使开发人员能够立即了解名称。

命名需要符合以下几个目的

- 命名风格的一致性
- 必须易于理解
- 必须传达每个元素的功能



### 大小写约定

本章中的指导原则介绍了使用大小写的简单方法，即在应用一致的情况下，使类型、成员和参数的标识符易于阅读。

#### 标识符的大小写规则

若要区分标识符中的单词，请将标识符中每个单词的首字母大写。  有两种方法可以根据标识符的使用来大写标识符：



- PascalCasing
- camelCasing
- _camelCasing
- ALL_UPPER



**PascalCasing** 约定用于除了参数名称外的所有标识符，使每个单词的第一个字符大写， (包括长度超过两个字母的首字母缩写) ，如以下示例中所示：

`PropertyDescriptor` `HtmlTag`

这两个字母的首字母缩写词是一种特殊情况，其中两个字母都大写，如以下标识符所示：

`IOStream`



**camelCasing**约定仅用于参数名称，将每个单词的第一个字符（除第一个单词之外）设为大写，如以下示例中所示。 如示例中所示，以字母混合形式表示的两字母首字母缩写形式均为小写。

`propertyDescriptor` `ioStream` `htmlTag`



**_camelCasing**约定用于私有的属性，事件，字段，参数名称, 名称和camelCasing一致，只是前缀多了一个下划线`_`.如以下标识符所示：

`private string _itemName`  `private event EventHandler _exited`

`private static readonly TimeSpan _infiniteTimeout;`



**ALL_UPPER**约定用于常量定义名称，每个单词全部大写，单词之间用下划线`_`分割。例如错误编号，固定的Format等。

`internal const string DATE_FORMAT_YYYYMMDD = "yyyyMMdd";`

`internal const char SPLIT_MARK = '=';`

`internal const int ERROR_CODE_SUCCESS = 0;`



✔️对于包含多个单词的命名空间，类型，接口，方法，枚举值, 委托名称，请使用 PascalCasing。

| 标识符   | 大小写 | 示例                                                         |
| :------- | :----- | :----------------------------------------------------------- |
| 命名空间 | Pascal | `namespace System.Security { ... }`                          |
| 类型     | Pascal | `public class StreamReader { ... }`                          |
| 接口     | Pascal | `public interface IEnumerable { ... }`                       |
| 方法     | Pascal | `public class Object {`    `public virtual string ToString();`   `}` |
| 枚举值   | Pascal | `public enum FileMode {`    `Append,`    `...`   `}`         |
| 委托     | Pascal | `public delegate void TestDelegate(string message);`         |

✔️对于包含多个单词的非私有的属性，事件，字段名称，请使用 PascalCasing。
| 标识符     | 大小写      | 示例                                                         |
| :--------- | :----------------------- | :----------------------------------------------------------- |
| properties | Pascal | `public class String {`    `public int Length { get; }`   `}`<br /> `protected string ItemCode` |
| 事件       | Pascal | `internal class Process {`    `public event EventHandler Exited;`   `}` |
| 字段       | Pascal | `public class MessageQueue {`    `public static readonly TimeSpan`   `InfiniteTimeout;`   `}` |

✔️对于包含多个单词的私有的事件，字段名称，请使用 _camelCasing。

| 标识符 | 大小写 | 示例                                                         |
| :----- | :----- | :----------------------------------------------------------- |
| 事件   | _camel | `private class Process {`    `private event EventHandler _exited;`   `}` |
| 字段   | _camel | `private int _length `;<br />`public class MessageQueue {  private static readonly TimeSpan _infiniteTimeout;`   `}` |

❌ 不要定义私有属性 `private int _length { get; }` 。私有属性没有意义，如果需要可以用私有字段`private int _length `;。



✔️使用 camelCasing 作为参数名称。

| 标识符     | 大小写 | 示例                                                         |
| :--------- | :----- | :----------------------------------------------------------- |
| 参数 | camel | `public class Convert {`    `public static int ToInt32(string value);`   `}` |



✔️使用 ALL_UPPER作为常量(const)名称。

| 标识符 | 大小写    | 示例                                                         |
| :----- | :-------- | :----------------------------------------------------------- |
| 常量   | ALL_UPPER | `public class ErrorCodeMessage {`    `public const int ERROR_CODE_SUCCESS = 0;`   `}` |



##### .editorconfig 文件示例：

```ini
# 命名规则
dotnet_naming_rule.namespace__class__interface__enum__delegate__method_should_be_pascal_case.severity = suggestion
dotnet_naming_rule.namespace__class__interface__enum__delegate__method_should_be_pascal_case.symbols = namespace__class__interface__enum__delegate__method
dotnet_naming_rule.namespace__class__interface__enum__delegate__method_should_be_pascal_case.style = pascal_case

dotnet_naming_rule.const_value_should_be_all_upper.severity = suggestion
dotnet_naming_rule.const_value_should_be_all_upper.symbols = const_value
dotnet_naming_rule.const_value_should_be_all_upper.style = all_upper

dotnet_naming_rule.non_private_property__field__event_should_be_pascal_case.severity = suggestion
dotnet_naming_rule.non_private_property__field__event_should_be_pascal_case.symbols = non_private_property__field__event
dotnet_naming_rule.non_private_property__field__event_should_be_pascal_case.style = pascal_case

dotnet_naming_rule.parameter__type_parameter__local_should_be_camelcasing.severity = suggestion
dotnet_naming_rule.parameter__type_parameter__local_should_be_camelcasing.symbols = parameter__type_parameter__local
dotnet_naming_rule.parameter__type_parameter__local_should_be_camelcasing.style = camelcasing

dotnet_naming_rule.private_field__event_should_be__camelcasing.severity = suggestion
dotnet_naming_rule.private_field__event_should_be__camelcasing.symbols = private_field__event
dotnet_naming_rule.private_field__event_should_be__camelcasing.style = _camelcasing

# 符号规范
dotnet_naming_symbols.namespace__class__interface__enum__delegate__method.applicable_kinds = namespace, class, interface, enum, delegate, method, local_function
dotnet_naming_symbols.namespace__class__interface__enum__delegate__method.applicable_accessibilities = *
dotnet_naming_symbols.namespace__class__interface__enum__delegate__method.required_modifiers = 

dotnet_naming_symbols.non_private_property__field__event.applicable_kinds = property, field, event
dotnet_naming_symbols.non_private_property__field__event.applicable_accessibilities = public, internal, protected, protected_internal, local
dotnet_naming_symbols.non_private_property__field__event.required_modifiers = 

dotnet_naming_symbols.private_field__event.applicable_kinds = field, event
dotnet_naming_symbols.private_field__event.applicable_accessibilities = private, private_protected
dotnet_naming_symbols.private_field__event.required_modifiers = 

dotnet_naming_symbols.parameter__type_parameter__local.applicable_kinds = parameter, type_parameter, local
dotnet_naming_symbols.parameter__type_parameter__local.applicable_accessibilities = *
dotnet_naming_symbols.parameter__type_parameter__local.required_modifiers = 

dotnet_naming_symbols.const_value.applicable_kinds = field
dotnet_naming_symbols.const_value.applicable_accessibilities = *
dotnet_naming_symbols.const_value.required_modifiers = const

# 命名样式
dotnet_naming_style.pascal_case.required_prefix = 
dotnet_naming_style.pascal_case.required_suffix = 
dotnet_naming_style.pascal_case.word_separator = 
dotnet_naming_style.pascal_case.capitalization = pascal_case

dotnet_naming_style.all_upper.required_prefix = 
dotnet_naming_style.all_upper.required_suffix = 
dotnet_naming_style.all_upper.word_separator = _
dotnet_naming_style.all_upper.capitalization = all_upper

dotnet_naming_style._camelcasing.required_prefix = _
dotnet_naming_style._camelcasing.required_suffix = 
dotnet_naming_style._camelcasing.word_separator = 
dotnet_naming_style._camelcasing.capitalization = camel_case

dotnet_naming_style.camelcasing.required_prefix = 
dotnet_naming_style.camelcasing.required_suffix = 
dotnet_naming_style.camelcasing.word_separator = 
dotnet_naming_style.camelcasing.capitalization = camel_case
```



#### 大写复合词和常见字词

出于大写字母的目的，大多数复合词都被视为单个字词。

❌ 不要将所谓的 "封闭格式" 组合词中的每个词大写。

它们是以单个词（如终结点）形式编写的组合词。 为了实现大小写准则，请将关闭窗体的组合词视为单个单词。 使用当前字典来确定是否以闭合形式写入复合单词。

| Pascal        | Camel         | Not❌                 |
| :------------ | :------------ | :------------------- |
| `BitFlag`     | `bitFlag`     | `Bitflag`            |
| `Callback`    | `callback`    | `CallBack`           |
| `Canceled`    | `canceled`    | `Cancelled`          |
| `DoNot`       | `doNot`       | `Don't`              |
| `Email`       | `email`       | `EMail`              |
| `Endpoint`    | `endpoint`    | `EndPoint`           |
| `FileName`    | `fileName`    | `Filename`           |
| `Gridline`    | `gridline`    | `GridLine`           |
| `Hashtable`   | `hashtable`   | `HashTable`          |
| `Id`          | `id`          | `ID`                 |
| `Indexes`     | `indexes`     | `Indices`            |
| `LogOff`      | `logOff`      | `LogOut`             |
| `LogOn`       | `logOn`       | `LogIn`              |
| `Metadata`    | `metadata`    | `MetaData, metaData` |
| `Multipanel`  | `multipanel`  | `MultiPanel`         |
| `Multiview`   | `multiview`   | `MultiView`          |
| `Namespace`   | `namespace`   | `NameSpace`          |
| `Ok`          | `ok`          | `OK`                 |
| `Pi`          | `pi`          | `PI`                 |
| `Placeholder` | `placeholder` | `PlaceHolder`        |
| `SignIn`      | `signIn`      | `SignOn`             |
| `SignOut`     | `signOut`     | `SignOff`            |
| `UserName`    | `userName`    | `Username`           |
| `WhiteSpace`  | `whiteSpace`  | `Whitespace`         |
| `Writable`    | `writable`    | `Writeable`          |



#### 区分大小写

可在 CLR 上运行的语言有些需要支持区分大小写，但有些则不需要。 即使您的语言支持，其他可能访问您的框架的语言也不是这样。 因此，外部可访问的任何 Api 都不能单独依赖于 大小写 来区分同一上下文中的两个名称。

❌ 不要假设所有编程语言都区分大小写。  名称不能通过大小写来单独区分。

- ❌ 代码示例：

```c#
    public string NameA;
    public string NAMEA;
    public string namea;
```



### 通用命名约定

本部分介绍与 word 选项相关的常规命名约定、有关使用缩写和首字母缩写词的准则，以及如何避免使用特定于语言的名称的建议。



#### Word 选项

✔️选择易于阅读的标识符名称。

例如，名为的属性的 `HorizontalAlignment` 可读性比更强 `AlignmentHorizontal` 。

✔️提高可读性比简洁。

属性名称 `CanScrollHorizontally` 比 `ScrollableX` (对 X 轴) 的模糊引用更好。

❌ 不要使用匈牙利表示法。

g_ 全局变量
		c_ 　常量
		m_ 　c++类成员变量
		s_ 　静态变量

❌ 避免使用与广泛使用的编程语言的关键字冲突的标识符。



根据公共语言规范的规则 4 (CLS) ，所有符合语言都必须提供一种机制，以允许访问使用该语言的关键字作为标识符的已命名项。 例如，在这种情况下，c # 使用 @ 符号作为转义机制。 不过，最好是避免使用常见关键字，因为使用转义序列的方法比没有它的方法更难。



#### 使用缩写和首字母缩写

❌ 不要使用缩写或缩写作为标识符名称的一部分。

例如，使用 `GetWindow` 而不是 `GetWin` 。



❌ 不要使用未被广泛接受的任何首字母缩写，甚至在必要时才使用。



#### 避免 Language-Specific 名称

✔️确实使用有语义的名称，而不是类型名称的特定于语言的关键字。✔️ DO use semantically interesting names rather than language-specific keywords for type names.

例如， `GetLength` 是比更好的名称 `GetInt` 。For example, `GetLength` is a better name than `GetInt`.

在极少数情况下，如果标识符没有超出其类型的语义含义，则✔️使用泛型 CLR 类型名称，而不是特定于语言的名称。✔️ DO use a generic CLR type name, rather than a language-specific name, in the rare cases when an identifier has no semantic meaning beyond its type.

例如，转换为的方法 [Int64]() 应命名为 `ToInt64` ，而不是 `ToLong` (，因为 [Int64]() 是特定于 c # 的别名) 的 CLR 名称 `long` 。For example, a method converting to [Int64]() should be named `ToInt64`, not `ToLong` (because [Int64]() is a CLR name for the C#-specific alias `long`). 下表显示了几个使用 CLR 类型名称 (的基本数据类型，以及 c #、Visual Basic 和 c + +) 的相应类型名称。The following table presents several base data types using the CLR type names (as well as the corresponding type names for C#, Visual Basic, and C++).

| C#     | Visual Basic | C++              | CLR     |
| :----- | :----------- | :--------------- | :------ |
| sbyte  | SByte        | char             | SByte   |
| byte   | Byte         | unsigned char    | Byte    |
| short  | Short        | short            | Int16   |
| ushort | UInt16       | unsigned short   | UInt16  |
| int    | Integer      | int              | Int32   |
| uint   | UInt32       | unsigned int     | UInt32  |
| long   | Long         | __int64          | Int64   |
| ulong  | UInt64       | unsigned __int64 | UInt64  |
| float  | Single       | float            | Single  |
| double | Double       | double           | Double  |
| bool   | Boolean      | bool             | Boolean |
| char   | Char         | wchar_t          | Char    |
| string | String       | String           | String  |
| object | Object       | Object           | Object  |

✔️使用公用名（如 `value` 或 `item` ），而不是重复类型名称，在极少数情况下，标识符没有语义含义，并且参数的类型并不重要。

```C#
// 单参数时可用 value
public int ConvertToInt32(string value)
{    
}

// 循环时可用item
foreach (var item in selectedList)
{
}
```



#### 命名现有 Api 的新版本

创建现有 API 的新版本时，✔️使用类似于旧 API 的名称。

这有助于突出显示 Api 之间的关系。



✔️确实更喜欢添加后缀（而非前缀）来指示现有 API 的新版本。

这将有助于在浏览文档或使用 IntelliSense 时发现。 旧版本的 API 将被组织到新的 Api 附近，因为大多数浏览器和 IntelliSense 都按字母顺序显示标识符。

✔️考虑使用全新但有意义的标识符，而不是添加后缀或前缀。

✔️使用数字后缀来指示现有 API 的新版本，尤其是如果 API 的现有名称是有意义的唯一名称 (例如，如果它是行业标准) ，并且 (或更改名称) 添加任何有意义的后缀，则不是合适的选择。

❌ 不要使用标识符的 "Ex" (或类似的) 后缀来区分它与早期版本的同一 API。

在引入在64位整数上操作的 Api 版本 (长整数) 而不是32位整数时，✔️确实使用 "64" 后缀。 仅当存在现有的32位 API 时，才需要采用此方法;请勿使用只有64位版本的新 Api。



```c#
// 版本1.0
namespace CreateCableTool
{
    class CommonHelper
    {
    }
}

// 版本2.0
namespace CreateCableTool
{
    // 添加后缀 V20.后缀的规则可自定义，如64位版本的 可加X64
    class CommonHelperV20
    {
    }
    
    class CommonHelperX64
    {
    }
}

```





### 程序集和 DLL 的名称

程序集是托管代码程序的部署和标识单元。 尽管程序集可跨一个或多个文件，但通常程序集与 DLL 相互映射。 因此，本节仅介绍 DLL 命名约定，然后可以将其映射到程序集命名约定。



✔️为程序集 Dll 选择名称，这些名称建议了大块功能，如 System.object。

程序集和 DLL 的名称不必与命名空间名称对应，但在命名程序集时遵循命名空间名称是合理的。 合理的经验法则是基于程序集中包含的命名空间的公共前缀来命名 DLL。 例如，可以调用具有两个命名空间和的程序集 `MyCompany.MyTechnology.FirstFeature` `MyCompany.MyTechnology.SecondFeature` `MyCompany.MyTechnology.dll` 。



✔️考虑根据以下模式命名 Dll：

```
<Company>.<Component>.dll
```



其中 `<Component>` 包含一个或多个以句点分隔的子句。 例如：

`Litware.Controls.dll`.



### 命名空间的名称

与其他命名准则一样，命名命名空间的目标是为程序员创建足够的清晰度，使其能够立即了解命名空间的内容。 以下模板指定命名空间的一般规则：



```
<Company>.(<Product>|<Technology>)[.<Feature>][.<Subnamespace>]
```



下面是一些示例：



```
Fabrikam.Math` `Litware.Security
```



✔️使用公司名称作为命名空间名称的前缀，以防不同公司的命名空间具有相同的名称。

✔️在命名空间名称的第二级使用稳定的独立于版本的产品名称。

❌ 不要使用组织层次结构作为命名空间层次结构中的名称的基础，因为公司内的组名通常是短期的。 围绕一组相关技术组织命名空间的层次结构。

✔️使用 PascalCasing，并使用句点分隔命名空间组件 (例如， `Microsoft.Office.PowerPoint`) 。 如果品牌采用 nontraditional 大小写，则应遵循品牌定义的大小写，即使它与常规命名空间大小写不符。

✔️考虑在适当的情况下使用复数命名空间名称。

例如，请使用 `System.Collections` 而不是 `System.Collection`。 不过，品牌名称和首字母缩写是此规则的例外情况。 例如，请使用 `System.IO` 而不是 `System.IOs`。

❌ 命名空间和该命名空间中的类型不要使用相同的名称。

例如，不要将 `Debug` 用作命名空间名称，并 `Debug` 在同一命名空间中提供名为的类。 一些编译器需要完全限定此类类型。



#### 命名空间和类型名称冲突

❌ 不要引入泛型类型名称 `Element` ，例如、、 `Node` `Log` 和 `Message` 。

很多情况下，这样做会导致在常见方案中发生类型名称冲突。 应限定泛型类型名称 (`FormElement` 、 `XmlNode` 、 `EventLog` `SoapMessage`) 。



为避免不同类别的命名空间的类型名称冲突，有一些特定的准则。

- **应用程序模型命名空间**

  属于单个应用程序模型的命名空间经常一起使用，但几乎不能与其他应用程序模型的命名空间一起使用。 例如， [System.Windows.Forms](https://docs.microsoft.com/zh-cn/dotnet/api/system.windows.forms) 命名空间非常少与 [System.Web.UI](https://docs.microsoft.com/zh-cn/dotnet/api/system.web.ui) 命名空间一起使用。 下面列出了众所周知的应用程序模型命名空间组：

  `System.Windows*` `System.Web.UI*`

  ❌ 不要为单个应用程序模型中的命名空间中的类型指定相同的名称。

  例如，不要将名为的类型添加 `Page` 到 [System.Web.UI.Adapters](https://docs.microsoft.com/zh-cn/dotnet/api/system.web.ui.adapters) 命名空间，因为该 [System.Web.UI](https://docs.microsoft.com/zh-cn/dotnet/api/system.web.ui) 命名空间已经包含一个名为的类型 `Page` 。

- **基础结构命名空间**

  此组包含在开发常见应用程序期间很少导入的命名空间。 例如， `.Design` 命名空间主要在开发编程工具时使用。 避免与这些命名空间中的类型冲突并不重要。

- **核心命名空间**

  核心命名空间包括所有 `System` 命名空间（不包括应用程序模型的命名空间和基础结构命名空间）。 核心命名空间包括、、、 `System` `System.IO` `System.Xml` 和 `System.Net` 。

  ❌ 不要提供会与核心命名空间中的任何类型冲突的类型名称。

  例如，永远不要将 `Stream` 用作类型名称。 它会与 [System.IO.Stream](https://docs.microsoft.com/zh-cn/dotnet/api/system.io.stream) 非常常用的类型冲突。

- **技术命名空间组**

  此类别包括) 的前两个命名空间节点相同的所有命名空间 `(<Company>.<Technology>*` ，例如 `Microsoft.Build.Utilities` 和 `Microsoft.Build.Tasks` 。 属于单个技术的类型不会相互冲突，这一点非常重要。

  ❌ 不要分配会与单个技术中的其他类型冲突的类型名称。

  ❌ 请勿引入技术命名空间和应用程序模型命名空间中的类型之间的类型名称冲突 (除非该技术不打算用于应用程序模型) 。



### 类、结构和接口的名称

遵循的命名准则适用于常规类型命名。



✔️使用 PascalCasing 将类和结构命名为名词或名词短语。

这区分了方法中的类型名称，这些类型名为带有谓词短语。

✔️利用形容词短语或偶尔用名词或名词短语来命名接口。

应很少使用名词和名词短语，它们可能会指示该类型应为抽象类，而不是接口。

❌ 要为类名称指定前缀 (例如，"C" ) 。

✔️考虑用基类的名称结束派生类的名称。

这是非常可读的，并且清楚地说明了该关系。 代码中的一些示例是：，它是一种 `ArgumentOutOfRangeException` `Exception` ，而 `SerializableAttribute` 是一种类型的 `Attribute` 。 但是，在应用此准则时使用合理的判断非常重要;例如， `Button` 类是一种 `Control` 事件，但 `Control` 它的名称中没有显示。

✔️使用字母 I 来为接口名称加上前缀，以指示该类型是接口。

例如， `IComponent` (描述性名词) ， `ICustomAttributeProvider` (名词短语) ， `IPersistable` (形容词) 是适当的接口名称。 对于其他类型名称，请避免缩写形式。

✔️确保在定义类接口对（其中类是接口的标准实现）时，接口名称上的 "I" 前缀仅有不同的名称。



#### 泛型类型参数的名称

已将泛型添加到 .NET Framework 2.0。 此功能引入了一种称为 *类型参数* 的新标识符。

✔️使用描述性名称命名泛型类型参数，除非单个字母名称完全一目了然，并且描述性名称不会添加值。

✔️考虑使用 `T` 作为具有一个单字母类型参数的类型的类型参数名称。	 				 					 				 			 		

```csharp
public int IComparer<T> { ... }
public delegate bool Predicate<T>(T item);
public struct Nullable<T> where T:struct { ... }
```

✔️在中为描述性类型参数名称加上前缀 `T` 。		 				 					 				 			 		

```csharp
public interface ISessionChannel<TSession> where TSession : ISession {
    TSession Session { get; }
}
```

✔️考虑，指示对参数名称中的类型参数施加的约束。

例如，可以调用约束为的参数 `ISession` `TSession` 。



#### 常见类型的名称

当命名类型派生自或实现某些 .NET Framework 类型时，✔️按照下表中所述的指导原则进行操作。

| 基类型                                                       | 派生/实现类型准则                                            |
| :----------------------------------------------------------- | :----------------------------------------------------------- |
| `System.Attribute`                                           | ✔️将后缀 "Attribute" 添加到自定义特性类的名称中。             |
| `System.Delegate`                                            | ✔️将后缀 "EventHandler" 添加到在事件中使用的委托的名称。   ✔️将后缀 "Callback" 添加到作为事件处理程序使用的委托的名称。   ❌ 不要将后缀 "Delegate" 添加到委托。 |
| `System.EventArgs`                                           | ✔️添加后缀 "EventArgs"。                                      |
| `System.Enum`                                                | ❌ 不要从此类派生;改为使用您的语言支持的关键字;例如，在 c # 中，使用 `enum` 关键字。   ❌ 不要添加后缀 "Enum" 或 "Flag"。 |
| `System.Exception`                                           | ✔️添加后缀 "Exception"。                                      |
| `IDictionary`   `IDictionary<TKey,TValue>`                   | ✔️添加后缀 "Dictionary"。 请注意， `IDictionary` 是一种特定类型的集合，但是此准则优先于下面的更常见的集合原则。 |
| `IEnumerable`   `ICollection`   `IList`   `IEnumerable<T>`   `ICollection<T>`   `IList<T>` | ✔️添加后缀 "Collection"。                                     |
| `System.IO.Stream`                                           | ✔️添加后缀 "Stream"。                                         |
| `CodeAccessPermission IPermission`                           | ✔️添加后缀 "Permission"。                                     |



#### 命名枚举

通常情况下，枚举类型 (名称也称为枚举) 应遵循标准类型命名规则 (PascalCasing  ) 等。 不过，还有其他一些准则专门适用于枚举。

✔️为枚举使用单数类型名称，除非其值为位域。

✔️对将位域作为值的枚举使用复数类型名称，也称为标志枚举。

❌ 不要在枚举类型名称中使用 "Enum" 后缀。

❌ 不要在枚举类型名称中使用 "Flag" 或 "Flags" 后缀。

❌ 不要在枚举值名称上使用前缀 (例如，将 "ad" 用于 ADO 枚举，使用 "rtf" 进行丰富文本枚举等 ) 。



### 类型成员的名称

类型由以下成员构成：方法、属性、事件、构造函数和字段。 以下各节介绍命名类型成员的准则。



#### 方法的名称

方法是执行操作的方式，设计准则要求方法名称为谓词或谓词短语。 遵循此准则，还有利于区分方法名称与属性和类型名称，后者为名词或形容词性短语。



✔️为谓词或谓词短语指定方法名称。	 				 					 				 			 		

```csharp
public class String {
    public int CompareTo(...);
    public string[] Split(...);
    public string Trim();
}
```



#### 属性的名称

与其他成员不同，应向属性给定名词性短语或形容词性名称。 这是因为属性是指数据，属性的名称应反映这一点。 属性名称总是采用帕斯卡大小写。



✔️使用名词、名词短语或形容词名称属性。

❌ 没有与 "Get" 方法的名称相匹配的属性，如以下示例中所示：

```c#
public string TextWriter { get {...} set {...} }` `public string GetTextWriter(int value) { ... }
```

此模式通常意味着该属性事实上是一种方法。

✔️使用一个复数短语来描述集合中的项，而不是使用后跟 "List" 或 "Collection" 的短语来命名集合属性。

✔️使用赞成短语 (， `CanSeek` 而不是) 来命名布尔属性 `CantSeek` 。 或者，还可以在布尔属性前面添加 "Is"、"Can" 或 "has" 前缀，但前提是在它添加值的位置。

✔️考虑为属性提供与其类型相同的名称。

例如，以下属性可正确获取和设置名为 `Color` 的枚举值，因此属性名为 `Color`： 				 					 				 			 		

```csharp
public enum Color {...}
public class Control {
    public Color Color { get {...} set {...} }
}
```



#### 事件的名称

事件始终指操作，可以是即将发生的，也可以是已经发生的。 因此，对于方法，事件用谓词命名，并用谓词时态指示引发事件的时间。



✔️使用动词或动词短语来命名事件。

示例包括`Clicked`、`Painting` 和 `DroppedDown`。

✔️使用现有的和过去的时态为事件名称提供前后的概念。

例如，在窗口关闭前引发的关闭事件可命名为 `Closing`，而在窗口关闭后后引发的关闭事件可命名为 `Closed`。

❌ 不要使用 "Before" 或 "After" 前缀或 postfixes 来指示前和后事件。 请如上所示使用现在时和过去时。

✔️命名事件处理程序 (使用 "EventHandler" 后缀的事件类型) 委托，如下面的示例中所示：

```c#
public delegate void ClickedEventHandler(object sender, ClickedEventArgs e);
```

✔️确实要 `sender` `e` 在事件处理程序中使用两个名为和的参数。

sender 参数表示引发事件的对象。 sender 参数的类型通常是 `object`，即使可以使用更具体的类型。

✔️用 "EventArgs" 后缀命名事件参数类。



#### 字段的名称

字段命名准则适用于静态公开字段和受保护的字段。 原则不涉及内部和专用字段，而[成员设计准则](https://docs.microsoft.com/zh-cn/dotnet/standard/design-guidelines/member)不允许使用公开字段或受保护的实例字段。



✔️在字段名称中使用 PascalCasing。

✔️使用名词、名词短语或形容词来命名字段。

❌ 不要对字段名称使用前缀。

例如，请勿使用“g_”或“s_”来指示静态字段。



### Linq中的成员，属性

✔️成员按照camel规则

✔️属性按照Pascal规则

```c#
var innerJoinQuery =
    from cust in customers
    join dist in distributors on cust.City equals dist.City
    select new { CustomerName = cust.Name, DistributorName = dist.Name };
```



### 命名参数

除了可读性的明显原因外，请务必遵循有关参数名称的准则，因为当可视化设计工具提供 Intellisense 和类浏览功能时，参数将显示在文档和设计器中。



✔️在参数名称中使用 camelCasing。

✔️使用描述性参数名称。

✔️考虑使用基于参数含义而不是参数类型的名称。



#### 命名运算符重载参数

`left` `right` 如果参数没有任何意义，✔️确实要使用和进行二元运算符重载参数名称。

```c#
public static bool operator ==(String left, String right);
```

对于参数，✔️确实使用 `value` 一元运算符重载参数名称。

```c#
public class Box
{
    public int ItemValue;
    public static Box operator ++(Box value)
    {
        value.ItemValue++;
        return value;
    }
}
```



✔️考虑运算符重载参数有意义的名称，如果这样做会增加重要值。

❌ 不要对运算符重载参数名称使用缩写或数值索引。



### 命名资源



由于可以通过某些对象来引用可本地化的资源，就像它们是属性一样，因此资源的命名准则与属性准则类似。



✔️在资源键中使用 PascalCasing。

✔️提供描述性而不是短标识符。

❌ 不要使用主要 CLR 语言的特定于语言的关键字。

✔️在命名资源中仅使用字母数字字符和下划线。

✔️对异常消息资源使用以下命名约定。



资源标识符应为异常类型名称和异常的简短标识符：

```
ArgumentExceptionIllegalCharacters` `ArgumentExceptionInvalidName` `ArgumentExceptionFileNameIsMalformed
```



### 控件UID命名约定

与其他命名准则一样，控件UID命名的目标是为程序员创建足够的清晰度，使其能够立即了解控件的内容。 以下模板指定控件UID的一般规则：



```
<系统名>-<机能ID>-<控件类型>-<序号>[-<子类型>-<子序号>]
例如：
X-XXX-LBL-nn
X-XXX-LST-nn-Cn-BODY-BTN-IMGn
```





| 区分     | 種別      | 控件名                   | UID                           |
| -------- | --------- | ------------------------ | ----------------------------- |
| 通常     | 表示      | Label                    | X-XXX-LBL-nn                  |
|          | 入力      | TextBox                  | X-XXX-TXT-nn                  |
|          |           | PasswordBox              | X-XXX-TXT-PASS-nn             |
|          |           | CheckBox                 | X-XXX-CKB-nn                  |
|          |           | DatePicker               | X-XXX-TXT-DATE-nn             |
|          |           | Calendar                 | X-XXX-CLD-nn                  |
|          | 按钮      | Button                   | X-XXX-BTN-nn                  |
|          |           | RadioButton              | X-XXX-RDB-nn                  |
|          | 选择      | ComboBox                 | X-XXX-DDL-nn                  |
|          |           | ListBox                  | -                             |
|          | 布局      | NamePanel                | -                             |
|          | 进度条    | ProgressBar              | -                             |
| ListView | 表示/入力 | GridView                 | -                             |
|          | 表示      | GridViewColumnHeader     | X-XXX-LST-nn-HEAD-Cn      |
|          |           | GridViewColumn       | X-XXX-LST-nn-BODY-Cn                             |



| UID                  | 説明                                         | 例                    |
| -------------------- | -------------------------------------------- | --------------------- |
| X-XXX                | 机能ID-画面ID                                | S-AN01-LBL-01         |
| nn                   | 从01顺番                                     |                       |
| X-XXX-LST-nn-BODY-Cn | Cn→Column从1顺番                             | S-AN01-LST-01-BODY-C3 |
| X-XXX-LST-nn-HEAD-Cn | LST-nn→从01顺番     Cn→从1顺番（Column順番） | S-AN01-LST-01-HEAD-C1 |






# 代码质量规则

.NET代码分析提供旨在提高代码质量的规则。这些规则分为设计、全球化、性能和安全性等领域。某些规则特定于.NET的API使用，而其他规则则与通用代码质量有关。



## 设计规则

设计规则支持遵循 [.NET Framework 设计准则](https://docs.microsoft.com/zh-cn/dotnet/standard/design-guidelines/)。

- 严重性级别 ：警告

  

### CA1000:不要在泛型类型中声明静态成员

#### 原因

泛型类型包含 `static` `Shared`  (Visual Basic) 成员中的。



#### 规则说明

当 `static` 调用泛型类型的成员时，必须为该类型指定类型参数。 当调用不支持推理的泛型实例成员时，必须指定该成员的类型参数。 在这两种情况下，在这两种情况中指定类型参数的语法不同，并且很容易混淆，如下所示：

```c#
// Static method in a generic type.
GenericType<int>.StaticMethod();

// Generic instance method that does not support inference.
someObject.GenericMethod<int>();
```

通常，应避免上述两个声明，以便在调用成员时不必指定类型参数。 这会导致在泛型中调用成员的语法与非泛型的语法不同。

#### 如何解决冲突

若要修复与此规则的冲突，请删除静态成员或将其更改为实例成员。



### CA1003:使用泛型事件处理程序实例

#### 原因

类型包含一个委托，该委托返回 void，其签名包含 (第一个对象的两个参数，第二个参数包含可分配给 EventArgs) 的类型，并且包含程序集面向 .NET。

#### 规则说明

在 .NET Framework 2.0 之前，为了将自定义信息传递到事件处理程序，必须将新委托声明为指定派生自类的类 [System.EventArgs](https://docs.microsoft.com/zh-cn/dotnet/api/system.eventargs) 。 在 .NET Framework 2.0 及更高版本中，泛型 [System.EventHandler](https://docs.microsoft.com/zh-cn/dotnet/api/system.eventhandler-1) 委托允许将派生自的任何类 [EventArgs](https://docs.microsoft.com/zh-cn/dotnet/api/system.eventargs) 与事件处理程序一起使用。

#### 如何解决冲突

若要修复与此规则的冲突，请删除委托，并使用委托替换其使用 [System.EventHandler](https://docs.microsoft.com/zh-cn/dotnet/api/system.eventhandler-1) 。

##### 反例❌

```c#
// This delegate violates the rule.
public delegate void CustomEventHandler(object sender, CustomEventArgs e);

public class CustomEventArgs : EventArgs
{
    public string info = "data";
}

public class ClassThatRaisesEvent
{
    public event CustomEventHandler SomeEvent;
}
```



##### 正例✔️

```c#
public class CustomEventArgs : EventArgs
{
    public string info = "data";
}

public class ClassThatRaisesEvent
{
    public event EventHandler<CustomEventArgs> SomeEvent;
}
```



### CA1012:抽象类型不应具有构造函数

#### 原因

类型为抽象类型并且具有构造函数。

#### 规则说明

抽象类型的构造函数只能由派生类型调用。 由于公共构造函数创建类型的实例，并且不能创建抽象类型的实例，因此具有公共构造函数的抽象类型的设计是错误的。

#### 如何解决冲突

若要修复与此规则的冲突，请使构造函数成为受保护的，或者不将该类型声明为抽象类型。

##### 反例❌

```c#
// Violates this rule
public abstract class Book
{
    public Book()
    {
    }
}
```



##### 正例✔️

```c#
// Does not violate this rule
public abstract class Book
{
    protected Book()
    {
    }
}
```



### CA1016:用 AssemblyVersionAttribute 标记程序集

#### 原因

程序集没有版本号。

#### 规则说明

程序集的标识由以下信息组成：

- 程序集名称
- 版本号
- 环境
- ) 强名称程序集的公钥 (。

.NET 使用版本号来唯一标识程序集，并绑定到强名称程序集中的类型。 版本号与版本和发行者策略一起使用。

#### 如何解决冲突

若要修复与此规则的冲突，请使用特性将版本号添加到程序集 [System.Reflection.AssemblyVersionAttribute](https://docs.microsoft.com/zh-cn/dotnet/api/system.reflection.assemblyversionattribute) 。

##### 正例✔️

```c#
using System;
using System.Reflection;

[assembly: AssemblyVersionAttribute("4.3.2.1")]
namespace DesignLibrary {}
```



### CA1024:在适用处使用属性

#### 原因

方法具有以开头的名称 `Get` ，不使用任何参数，并且返回的值不是数组。

#### 规则说明

在大多数情况下，属性表示数据并执行操作。 像字段一样访问属性，这样可以更方便地使用它们。 如果存在以下情况之一，则方法非常适合成为属性：

- 方法不采用任何参数，并返回对象的状态信息。
- 方法接受单个参数，以设置对象状态的某些部分。

#### 如何解决冲突

若要修复与此规则的冲突，请将方法更改为属性。

##### 反例❌

```c#
public class Appointment
{
    string customerName;

    public string GetCustomerName()
    {
        return customerName;
    }
}
```



##### 正例✔️

```c#
public class Appointment
{
    string customerName;
    public string CustomerName => customerName;
}
```



### CA1033:接口方法应可由子类型调用

#### 原因

未密封的外部可见类型提供了显式实现公共接口的方法，但没有提供具有相同名称的其他外部可见方法。

#### 规则说明

考虑显式实现公共接口方法的基类型。 派生自基类型的类型只能通过对当前 (实例的引用来访问继承接口方法，该方法 `this` 在 c # ) 将被强制转换为接口。 如果派生类型重新实现 (显式) 继承的接口方法，则无法再访问基实现。 通过当前实例引用的调用将调用派生实现;这将导致递归和最终的堆栈溢出。

[System.IDisposable.Dispose](https://docs.microsoft.com/zh-cn/dotnet/api/system.idisposable.dispose)如果提供了外部可见 `Close()` 或方法，则此规则不会报告的显式实现的冲突 `System.IDisposable.Dispose(Boolean)` 。

#### 如何解决冲突

若要修复与此规则的冲突，请实现新的方法，该方法公开相同的功能，并对派生类型可见或更改为 nonexplicit 实现。 如果可接受重大更改，替代方法是使类型成为密封类型。

##### 反例❌

```c#
public interface ITest
{
    void SomeMethod();
}

public class ViolatingBase : ITest
{
    void ITest.SomeMethod()
    {
        // ...
    }
}
```



##### 正例✔️

```c#
public interface ITest
{
    void SomeMethod();
}

public class FixedBase : ITest
{
    void ITest.SomeMethod()
    {
        SomeMethod();
    }

    protected void SomeMethod()
    {
        // ...
    }
}
```



### CA1040:避免使用空接口

#### 原因

接口不声明任何成员或实现两个或更多个其他接口。

#### 规则说明

接口定义提供某个行为或使用协定的成员。 接口所描述的功能可以被任何类型采用，而不管该类型出现在继承层次结构中的哪个位置。 类型通过实现接口的成员来实现接口。 空接口不定义任何成员。 因此，它不定义可以实现的协定。



如果您的设计包含类型应实现的空接口，则您可能会将接口用作标记或标识一组类型的方式。 如果在运行时进行此标识，则完成此工作的正确方法是使用自定义属性。 使用特性的存在或缺少特性，以标识目标类型。 如果标识必须在编译时出现，则可以使用空接口。

#### 如何解决冲突

删除接口或向其添加成员。 如果使用空接口来标记一组类型，请将接口替换为自定义特性。

##### 反例❌

```c#
// Violates rule
public interface IBadInterface
{
}
```



### CA1047:不要在密封类型中声明受保护的成员

#### 原因

公共类型是 `sealed` `NotInheritable` 在 Visual basic) 中 (，并声明了一个受保护的成员或受保护的嵌套类型。 此规则不报告 [Finalize](https://docs.microsoft.com/zh-cn/dotnet/api/system.object.finalize) 方法冲突，这些方法必须遵循此模式。

#### 规则说明

类型声明受保护的成员，使继承类型可以访问或重写该成员。 按照定义，不能从密封类型继承，这意味着不能调用密封类型上的受保护方法。

#### 如何解决冲突

若要修复与此规则的冲突，请将成员的访问级别更改为 private，或使该类型可继承。

##### 反例❌

```c#
public sealed class SealedClass
{
    protected void ProtectedMethod(){}
}
```



### CA1049:拥有本机资源的类型应是可释放的

#### 原因

类型引用 [System.IntPtr](https://docs.microsoft.com/zh-cn/dotnet/api/system.intptr) 字段、 [System.UIntPtr](https://docs.microsoft.com/zh-cn/dotnet/api/system.uintptr) 字段或 [System.Runtime.InteropServices.HandleRef](https://docs.microsoft.com/zh-cn/dotnet/api/system.runtime.interopservices.handleref) 字段，但不实现 [System.IDisposable](https://docs.microsoft.com/zh-cn/dotnet/api/system.idisposable) 。

#### 规则说明

此规则假定 [IntPtr](https://docs.microsoft.com/zh-cn/dotnet/api/system.intptr) 、 [UIntPtr](https://docs.microsoft.com/zh-cn/dotnet/api/system.uintptr) 和 [HandleRef](https://docs.microsoft.com/zh-cn/dotnet/api/system.runtime.interopservices.handleref) 字段存储指向非托管资源的指针。 分配非托管资源的类型应实现 [IDisposable](https://docs.microsoft.com/zh-cn/dotnet/api/system.idisposable) ，使调用方能够按需释放这些资源，并缩短包含资源的对象的生存期。



用于清理非托管资源的建议设计模式是通过 [System.Object.Finalize](https://docs.microsoft.com/zh-cn/dotnet/api/system.object.finalize) 分别使用方法和方法提供隐式和显式方式来释放这些资源 [System.IDisposable.Dispose](https://docs.microsoft.com/zh-cn/dotnet/api/system.idisposable.dispose) 。 在 [Finalize](https://docs.microsoft.com/zh-cn/dotnet/api/system.object.finalize) 确定无法再访问对象之后，垃圾回收器在某个不确定的时间调用对象的方法。 [Finalize](https://docs.microsoft.com/zh-cn/dotnet/api/system.object.finalize)调用后，需要额外的垃圾回收来释放对象。 此 [Dispose](https://docs.microsoft.com/zh-cn/dotnet/api/system.idisposable.dispose) 方法允许调用方按需显式释放资源，而不是在离开垃圾回收器时释放资源。 清理非托管资源后， [Dispose](https://docs.microsoft.com/zh-cn/dotnet/api/system.idisposable.dispose) 应调用 [System.GC.SuppressFinalize](https://docs.microsoft.com/zh-cn/dotnet/api/system.gc.suppressfinalize) 方法让垃圾回收器知道 [Finalize](https://docs.microsoft.com/zh-cn/dotnet/api/system.object.finalize) 不再需要调用; 这无需进行额外的垃圾回收，缩短了对象的生存期。

#### 如何解决冲突

若要修复与此规则的冲突，请实现 [IDisposable](https://docs.microsoft.com/zh-cn/dotnet/api/system.idisposable) 。

##### 正例✔️

```c#
using System;

namespace DesignLibrary
{
    public class UnmanagedResources : IDisposable
    {
        IntPtr unmanagedResource;
        bool disposed = false;

        public UnmanagedResources() 
        {
            // Allocate the unmanaged resource ...
        }

        public void Dispose() 
        {
            Dispose(true);
            GC.SuppressFinalize(this); 
        }

        protected virtual void Dispose(bool disposing)
        {
            if(!disposed)
            {
                if(disposing)
                {
                    // Release managed resources.
                }

                // Free the unmanaged resource ...

                unmanagedResource = IntPtr.Zero;

                disposed = true;
            }
        }

        ~UnmanagedResources()
        {
            Dispose(false);
        }
    }
}
```



### CA1050:在命名空间中声明类型

#### 原因

在命名命名空间的范围外定义公共或受保护类型。

#### 规则说明

类型是在命名空间中声明的，以防止名称冲突，并作为在对象层次结构中组织相关类型的一种方法。 位于任何命名命名空间之外的类型位于无法在代码中引用的全局命名空间中。

#### 如何解决冲突

若要修复与此规则的冲突，请将类型置于命名空间中。

##### 反例❌

```c#
// Violates rule: DeclareTypesInNamespaces.
using System;

public class Test
{
    public override string ToString()
    {
        return "Test does not live in a namespace!";
    }
}
```



##### 正例✔️

```c#
namespace ca1050
{
    public class Test
    {
        public override string ToString()
        {
            return "Test lives in a namespace!";
        }
    }
}
```





### CA1060：将 P/Invoke 移动到 NativeMethods 类

#### 原因

方法使用平台调用服务来访问非托管代码，而不是 **NativeMethods** 类之一的成员。

#### 规则说明

平台调用方法（如使用特性标记的方法 [System.Runtime.InteropServices.DllImportAttribute](https://docs.microsoft.com/zh-cn/dotnet/api/system.runtime.interopservices.dllimportattribute) ）或使用 Visual Basic 中的关键字定义的方法 `Declare` 访问非托管代码。 这些方法应为以下类之一：



- **NativeMethods** -此类不取消托管代码权限的堆栈行走。 [System.Security.SuppressUnmanagedCodeSecurityAttribute](https://docs.microsoft.com/zh-cn/dotnet/api/system.security.suppressunmanagedcodesecurityattribute)不能将 (应用到此类。 ) 此类用于可在任何位置使用的方法，因为将执行堆栈遍历。
- **SafeNativeMethods** -此类取消托管代码权限的堆栈行走。  ([System.Security.SuppressUnmanagedCodeSecurityAttribute](https://docs.microsoft.com/zh-cn/dotnet/api/system.security.suppressunmanagedcodesecurityattribute) 应用于此类。 ) 此类用于可供任何人都可以调用的安全方法。 这些方法的调用方不需要执行完整安全检查以确保使用是安全的，因为这些方法对于任何调用方是无害的。
- **UnsafeNativeMethods** -此类取消托管代码权限的堆栈行走。  ([System.Security.SuppressUnmanagedCodeSecurityAttribute](https://docs.microsoft.com/zh-cn/dotnet/api/system.security.suppressunmanagedcodesecurityattribute) 应用于此类。 ) 此类适用于有潜在危险的方法。 这些方法的任何调用方都必须执行完整安全检查，以确保使用是安全的，因为不会执行任何堆栈遍历。



这些类 `internal` `Friend` 在 Visual Basic) 中声明为 (，并声明一个私有构造函数来阻止创建新实例。 这些类中的方法应为 `static` ，并 `internal` (`Shared` 和 `Friend` Visual Basic) 中。

#### 如何解决冲突

若要修复与此规则的冲突，请将方法移动到相应的 **NativeMethods** 类中。 对于大多数应用程序，将 P/Invoke 移动到名为 **NativeMethods** 的新类就足够了。

但是，如果要开发在其他应用程序中使用的库，应考虑定义两个称为 **SafeNativeMethods** 和 **UnsafeNativeMethods** 的其他类。 这些类类似于 **NativeMethods** 类;但是，它们使用名为 **SuppressUnmanagedCodeSecurityAttribute** 的特殊属性进行标记。 应用此特性时，运行时不会执行完整的堆栈审核，以确保所有调用方都具有 **UnmanagedCode** 权限。 运行时通常会在启动时检查此权限。 由于未执行检查，因此它可以极大地提高对这些非托管方法的调用的性能，同时还允许具有受限权限的代码调用这些方法。

不过，您应该非常小心地使用此属性。 如果未正确实现，则可能会产生严重的安全隐患。

有关如何实现这些方法的信息，请参阅 **NativeMethods** 示例、 **SafeNativeMethods** 示例和 **UnsafeNativeMethods** 示例。



##### 反例❌

```c#
// Violates rule: MovePInvokesToNativeMethodsClass.
internal class UnmanagedApi
{
    [DllImport("kernel32.dll", CharSet = CharSet.Unicode)]
    internal static extern bool RemoveDirectory(string name);
}
```



##### 正例✔️

```c#
internal static class NativeMethods
{
    [DllImport("user32.dll", CharSet = CharSet.Auto)]
    [return: MarshalAs(UnmanagedType.Bool)]
    internal static extern bool MessageBeep(int uType);
}
```



### CA1061:不要隐藏基类方法

#### 原因

派生类型声明一个方法，该方法具有相同的名称，并且参数的参数数目与它的一个基方法相同;一个或多个参数是基方法中的相应参数的基类型;所有剩余参数的类型都与基方法中的相应参数的类型相同。

#### 规则说明

当派生方法的参数签名只是与基方法的参数签名中的相应类型的派生程度更弱的类型不同时，基类型中的同名方法会隐藏基类型中的方法。

#### 如何解决冲突

若要修复与此规则的冲突，请删除或重命名该方法，或者更改参数签名，使该方法不会隐藏基方法。

##### 反例❌

```c#
class BaseType
{
    internal void MethodOne(string inputOne, string inputTwo)
    {
        Console.WriteLine("Base: {0}, {1}", inputOne, inputTwo);
    }
}

class DerivedType : BaseType
{
    // This method violates the rule.
    internal void MethodOne(string inputOne, object inputTwo)
    {
        Console.WriteLine("Derived: {0}, {1}", inputOne, inputTwo);
    }
}

class Test
{
    static void Main1061()
    {
        DerivedType derived = new DerivedType();
        // Both of these call DerivedType.MethodOne.
        derived.MethodOne("string1", "string2");
        derived.MethodOne("string1", (object)"string2");
    }
}
```



##### 正例✔️

```c#
class BaseType
{
    internal void MethodOne(string inputOne, object inputTwo)
    {
        Console.WriteLine("Base: {0}, {1}", inputOne, inputTwo);
    }
}

class DerivedType : BaseType
{
    internal void MethodOne(string inputOne, string inputTwo)
    {
        Console.WriteLine("Derived: {0}, {1}", inputOne, inputTwo);
    }
}

class Test
{
    static void Main1061()
    {
        DerivedType derived = new DerivedType();

        // Calls DerivedType.MethodOne.
        derived.MethodOne("string1", "string2");

        // Calls BaseType.MethodOne.
        derived.MethodOne("string1", (object)"string2");
    }
}
```



### CA1063:正确实现 IDisposable

#### 原因

[System.IDisposable](https://docs.microsoft.com/zh-cn/dotnet/api/system.idisposable)接口未正确实现。 此情况的可能原因包括：

- [IDisposable](https://docs.microsoft.com/zh-cn/dotnet/api/system.idisposable) 在类中是重新实现。
- `Finalize` 再次重写。
- `Dispose()` 被重写。
- `Dispose()`方法不是公共、[密封](https://docs.microsoft.com/zh-cn/dotnet/csharp/language-reference/keywords/sealed)或名为 **Dispose**。
- `Dispose(bool)` 不受保护、虚拟或未密封。
- 在未密封的类型中， `Dispose()` 必须调用 `Dispose(true)` 。
- 对于未密封类型， `Finalize` 实现不会调用或同时调用 `Dispose(bool)` 或基类终结器。

违反其中任何一种模式都会触发警告 CA1063。

声明和实现接口的每个未密封类型都 [IDisposable](https://docs.microsoft.com/zh-cn/dotnet/api/system.idisposable) 必须提供其自己的 `protected virtual void Dispose(bool)` 方法。 `Dispose()` 应调用 `Dispose(true)` ，并且终结器应调用 `Dispose(false)` 。 如果创建声明和实现接口的非密封类型 [IDisposable](https://docs.microsoft.com/zh-cn/dotnet/api/system.idisposable) ，则必须定义 `Dispose(bool)` 并调用它。 有关详细信息，请参阅 ( .NET 指南) 和[Dispose 模式](https://docs.microsoft.com/zh-cn/dotnet/standard/garbage-collection/implementing-dispose)下的[清理非托管资源](https://docs.microsoft.com/zh-cn/dotnet/standard/garbage-collection/unmanaged)。

#### 规则说明

所有 [IDisposable](https://docs.microsoft.com/zh-cn/dotnet/api/system.idisposable) 类型都应正确实现 [Dispose 模式](https://docs.microsoft.com/zh-cn/dotnet/standard/garbage-collection/implementing-dispose) 。

#### 如何解决冲突

检查你的代码并确定以下哪种解决方法会修复此冲突：

- [IDisposable](https://docs.microsoft.com/zh-cn/dotnet/api/system.idisposable)从类型实现的接口列表中删除，而改为重写基类释放实现。
- 从类型中删除终结器，重写 Dispose (bool 释放) ，并将终止逻辑放在代码路径中，其中 "释放" 为 false。
- 重写 Dispose (bool 释放) ，并将 dispose 逻辑放在 "Dispose" 为 true 的代码路径中。
- 请确保将 Dispose ( # A1 声明为 public 和 [sealed](https://docs.microsoft.com/zh-cn/dotnet/csharp/language-reference/keywords/sealed)。
- 重命名 dispose 方法以 **释放** ，并确保将其声明为 public 和 [sealed](https://docs.microsoft.com/zh-cn/dotnet/csharp/language-reference/keywords/sealed)。
- 请确保将 Dispose (bool) 声明为 protected、virtual 和未密封。
- 修改 Dispose ( # A1，使其调用 Dispose (true) ，然后对 [SuppressFinalize](https://docs.microsoft.com/zh-cn/dotnet/api/system.gc.suppressfinalize) 当前对象实例 (调用 `this` ，或 `Me` 在 Visual Basic) 中调用，然后返回。
- 修改终结器，使其调用 Dispose (false) 然后返回。
- 如果你创建了一个声明并实现接口的非密封类型 [IDisposable](https://docs.microsoft.com/zh-cn/dotnet/api/system.idisposable) ，请确保的实现 [IDisposable](https://docs.microsoft.com/zh-cn/dotnet/api/system.idisposable) 遵循本部分前面所述的模式。



##### 正例✔️

```c#
public class Resource : IDisposable
{
    private bool isDisposed;
    private IntPtr nativeResource = Marshal.AllocHGlobal(100);
    private AnotherResource managedResource = new AnotherResource();

    // Dispose() calls Dispose(true)
    public void Dispose()
    {
        Dispose(true);
        GC.SuppressFinalize(this);
    }

    // The bulk of the clean-up code is implemented in Dispose(bool)
    protected virtual void Dispose(bool disposing)
    {
        if (isDisposed) return;

        if (disposing)
        {
            // free managed resources
            managedResource.Dispose();
        }

        // free native resources if there are any.
        if (nativeResource != IntPtr.Zero)
        {
            Marshal.FreeHGlobal(nativeResource);
            nativeResource = IntPtr.Zero;
        }

        isDisposed = true;
    }

    // NOTE: Leave out the finalizer altogether if this class doesn't
    // own unmanaged resources, but leave the other methods
    // exactly as they are.
    ~Resource()
    {
        // Finalizer calls Dispose(false)
        Dispose(false);
    }
}
```



### CA1065:不要在意外的位置引发异常

#### 原因

不应引发异常的方法引发了异常。

#### 规则说明

不应引发异常的方法可以按如下方式分类：

- 属性获取方法
- 事件访问器方法
- Equals 方法
- GetHashCode 方法
- ToString 方法
- 静态构造函数
- 终结器
- Dispose 方法
- 相等运算符
- 隐式强制转换运算符

以下各节将讨论这些方法类型。

#### 如何解决冲突

对于属性 getter，请更改逻辑，使其不再需要引发异常，或将属性更改为方法。

对于前面列出的所有其他方法类型，请更改逻辑，使其不再必须引发异常。



### CA1069:枚举不得具有重复值

#### 原因

[枚举](https://docs.microsoft.com/zh-cn/dotnet/csharp/language-reference/builtin-types/enum)具有多个显式分配相同常数值的成员。

#### 规则说明

每个枚举成员都应具有唯一的常数值，或者必须使用枚举中的先前成员显式赋值，以指示共享值的显式意图。

此规则有助于捕获在以下方案中引入的功能 bug：

- 意外键入错误，用户在多个成员中意外键入相同的常量值。
- 复制粘贴错误，其中用户复制了现有成员定义，然后重命名了成员但忘记了更改值。
- 合并多个分支中的解决方案，其中使用不同的名称添加新成员，但在不同的分支中存在相同的值。

#### 如何解决冲突

若要解决冲突，请分配新的唯一常数值，或在枚举中为先前成员赋值，以指示共享同一值的显式意图。

##### 反例❌

```c#
enum E
{
   Field1 = 1,
   Field2 = 2,
   Field3 = 2,   // CA1069: This is not fine. Either assign a different constant value or 'Field2' to indicate explicit intent of sharing value.
}
```



##### 正例✔️

```c#
enum E
{
   Field1 = 1,
   Field2 = 2,
   Field3 = 3,   // This is now fine
}

enum E
{
   Field1 = 1,
   AnotherNameForField1 = Field1,   // This is fine
   Field2 = 2,
   Field3 = Field2,   // This is also fine
}
```



###  .editorconfig 文件示例：

```ini
dotnet_diagnostic.CA1001.severity = warning
dotnet_diagnostic.CA1003.severity = warning
dotnet_diagnostic.CA1012.severity = warning
dotnet_diagnostic.CA1016.severity = warning
dotnet_diagnostic.CA1024.severity = warning
dotnet_diagnostic.CA1033.severity = warning
dotnet_diagnostic.CA1040.severity = warning
dotnet_diagnostic.CA1047.severity = warning
dotnet_diagnostic.CA1049.severity = warning
dotnet_diagnostic.CA1050.severity = warning
dotnet_diagnostic.CA1060.severity = warning
dotnet_diagnostic.CA1061.severity = warning
dotnet_diagnostic.CA1063.severity = warning
dotnet_diagnostic.CA1065.severity = warning
dotnet_diagnostic.CA1069.severity = warning
```



## 文档规则

文档规则支持通过对外部可见 Api 使用正确的 [XML 文档注释](https://docs.microsoft.com/zh-cn/dotnet/csharp/codedoc) 来编写正确的库。

- 严重性级别 ：建议

### CA1200:不要使用带前缀的 cref 标记

#### 原因

XML 文档注释中的 [cref](https://docs.microsoft.com/zh-cn/dotnet/csharp/programming-guide/xmldoc/cref-attribute) 标记使用 [前缀](https://docs.microsoft.com/zh-cn/dotnet/csharp/programming-guide/xmldoc/processing-the-xml-file)。

#### 规则说明

XML 文档标记中的 [cref](https://docs.microsoft.com/zh-cn/dotnet/csharp/programming-guide/xmldoc/cref-attribute) 特性表示 "代码引用"。 它指定标记的内部文本是一个代码元素，例如类型、方法或属性。 避免使用 `cref` 带有前缀的标记，因为这会阻止编译器验证引用。 它还可防止 Visual Studio 集成开发环境 (IDE) 在重构期间查找和更新这些符号引用。 建议使用不带前缀的完整语法来引用 cref 标记中的符号名称。

#### 如何解决冲突

若要修复与此规则的冲突，请从标记中删除前缀 `cref` 。

##### 反例❌

```c#
// Violates CA1200
/// <summary>
/// Type <see cref="T:C" /> contains method <see cref="F:C.F" />
/// </summary>
class C
{
    public void F() { }
}
```



##### 正例✔️

```c#
// Does not violate CA1200
/// <summary>
/// Type <see cref="C" /> contains method <see cref="F" />
/// </summary>
class C
{
    public void F() { }
}
```



###  .editorconfig 文件示例：

```ini
dotnet_diagnostic.CA1200.severity = warning
```



## 全球化规则

全球化规则支持世界通用库和应用程序。

- 严重性级别 ：警告



### CA1301:避免快捷键重复

#### 原因

类型扩展 [System.Windows.Forms.Control](https://docs.microsoft.com/zh-cn/dotnet/api/system.windows.forms.control) 并包含两个或多个具有存储在资源文件中的相同访问键的顶级控件。

#### 规则说明

访问键（也称为快捷键）允许通过使用 **Alt** 键对控件进行键盘访问。 如果多个控件具有相同的访问键，则访问键的行为定义不正确。 用户可能不能使用访问键访问预期的控件，并且不能启用所需的控件。

此规则的当前实现忽略菜单项。 但是，同一子菜单中的菜单项不应具有相同的访问键。

#### 如何解决冲突

若要修复与此规则的冲突，请为所有控件定义唯一的访问密钥。



### CA2101：指定对 P/Invoke 字符串参数进行封送处理

#### 原因

平台调用成员允许部分受信任的调用方，具有字符串参数，并不显式封送字符串。

#### 规则说明

从 Unicode 转换为 ANSI 时，可能并非所有 Unicode 字符都可以在特定 ANSI 代码页中表示。 *最佳映射* 尝试通过将不能表示的字符替换为字符来解决此问题。 使用此功能可能会导致潜在的安全漏洞，因为您无法控制所选的字符。 例如，恶意代码可能会有意创建一个 Unicode 字符串，其中包含在特定代码页中找不到的字符，这些字符将转换为文件系统特殊字符，如 ".." 或 "/"。 另请注意，在将字符串转换为 ANSI 之前，通常会检查是否存在特殊字符。



最佳映射是非托管转换的默认值，WChar 到 Mb。 除非显式禁用最佳映射，否则，由于此问题，你的代码可能包含可利用的安全漏洞。

##### 注意

**不应将代码访问安全性(ca) 视为安全边界。**



#### 如何解决冲突

若要修复与此规则的冲突，请显式封送字符串数据类型。



###  .editorconfig 文件示例：

```ini
dotnet_diagnostic.CA1301.severity = warning
dotnet_diagnostic.CA2101.severity = warning
```



## 可移植性和互操作性规则

可移植性规则支持跨不同平台的可移植性。 互操作性规则支持与 COM 客户端的交互。

- 严重性级别 ：警告

### CA1401： P/Invoke 应不可见

#### 原因

公共类型中的公共或受保护方法具有 [System.Runtime.InteropServices.DllImportAttribute](https://docs.microsoft.com/zh-cn/dotnet/api/system.runtime.interopservices.dllimportattribute) 特性 (也由 `Declare` Visual Basic) 中的关键字实现。

#### 规则说明

使用 [DllImportAttribute](https://docs.microsoft.com/zh-cn/dotnet/api/system.runtime.interopservices.dllimportattribute) Visual Basic) 中的关键字定义 (或方法标记的方法 `Declare` 使用平台调用服务来访问非托管代码。 这些方法不能公开。 通过使这些方法保持私有或内部，你可以通过允许调用方访问不能调用的非托管 Api 来确保你的库不能用于破坏安全性。

#### 如何解决冲突

若要修复与此规则的冲突，请更改该方法的访问级别。

##### 反例❌

```c#
// Violates rule: PInvokesShouldNotBeVisible.
public class NativeMethods
{
    [DllImport("kernel32.dll", CharSet = CharSet.Unicode)]
    public static extern bool RemoveDirectory(string name);
}
```



### CA1403:自动布局类型不应对 COM 可见

#### 原因

组件对象模型 (COM) 可见值类型，并将 [System.Runtime.InteropServices.StructLayoutAttribute](https://docs.microsoft.com/zh-cn/dotnet/api/system.runtime.interopservices.structlayoutattribute) 特性设置为 [System.Runtime.InteropServices.LayoutKind.Auto](https://docs.microsoft.com/zh-cn/dotnet/api/system.runtime.interopservices.layoutkind#System_Runtime_InteropServices_LayoutKind_Auto) 。

#### 规则说明

[LayoutKind](https://docs.microsoft.com/zh-cn/dotnet/api/system.runtime.interopservices.layoutkind) 布局类型由公共语言运行时管理。 这些类型的布局可能在 .NET 版本之间更改，这会中断需要特定布局的 COM 客户端。 如果 [StructLayoutAttribute](https://docs.microsoft.com/zh-cn/dotnet/api/system.runtime.interopservices.structlayoutattribute) 未指定属性，则 c #、Visual Basic 和 c + + 编译器将为值类型指定[LayoutKind。](https://docs.microsoft.com/zh-cn/dotnet/api/system.runtime.interopservices.layoutkind#System_Runtime_InteropServices_LayoutKind_Auto)



除非另行标记，否则所有公共、非泛型类型都对 COM 可见，所有非公共和泛型类型对 COM 都不可见。 但是，若要减少误报，此规则需要显式声明类型的 COM 可见性。 必须用设置为的将包含程序集标记为 [System.Runtime.InteropServices.ComVisibleAttribute](https://docs.microsoft.com/zh-cn/dotnet/api/system.runtime.interopservices.comvisibleattribute) `false` ，且类型必须标记为 [ComVisibleAttribute](https://docs.microsoft.com/zh-cn/dotnet/api/system.runtime.interopservices.comvisibleattribute) `true` 。

#### 如何解决冲突

若要修复与此规则的冲突，请将属性的值更改 [StructLayoutAttribute](https://docs.microsoft.com/zh-cn/dotnet/api/system.runtime.interopservices.structlayoutattribute) 为 [LayoutKind](https://docs.microsoft.com/zh-cn/dotnet/api/system.runtime.interopservices.layoutkind#System_Runtime_InteropServices_LayoutKind_Explicit) 或 [LayoutKind](https://docs.microsoft.com/zh-cn/dotnet/api/system.runtime.interopservices.layoutkind#System_Runtime_InteropServices_LayoutKind_Sequential)，或使该类型对 COM 不可见。

##### 反例❌

```c#
using System;
using System.Runtime.InteropServices;

[assembly: ComVisible(false)]
namespace InteroperabilityLibrary
{
   // This violates the rule.
   [StructLayout(LayoutKind.Auto)]
   [ComVisible(true)]
   public struct AutoLayout
   {
      public int ValueOne;
      public int ValueTwo;
   }
}
```



##### 正例✔️

```c#
using System;
using System.Runtime.InteropServices;

[assembly: ComVisible(false)]
namespace InteroperabilityLibrary
{
   // This satisfies the rule.
   [StructLayout(LayoutKind.Explicit)]
   [ComVisible(true)]
   public struct ExplicitLayout
   {
      [FieldOffset(0)]
      public int ValueOne;

      [FieldOffset(4)]
      public  int ValueTwo;
   }
}
```



### CA1404：紧接在 P/Invoke 之后调用 GetLastError

#### 原因

对 [System.Runtime.InteropServices.Marshal.GetLastWin32Error](https://docs.microsoft.com/zh-cn/dotnet/api/system.runtime.interopservices.marshal.getlastwin32error) 方法或等效的 Win32 函数进行调用 `GetLastError` ，并且紧靠在之前的调用不是平台调用方法

#### 规则说明

平台调用方法访问非托管代码，并使用 `Declare` 中的关键字 Visual Basic 或特性来定义 [System.Runtime.InteropServices.DllImportAttribute](https://docs.microsoft.com/zh-cn/dotnet/api/system.runtime.interopservices.dllimportattribute) 。 通常，在失败时，非托管函数会调用 Win32 `SetLastError` 函数来设置与失败关联的错误代码。 Failed 函数的调用方调用 Win32 `GetLastError` 函数来检索错误代码，并确定失败的原因。 错误代码在每个线程的基础上维护，并在下一次调用时被覆盖 `SetLastError` 。 调用失败的平台调用方法后，托管代码可以通过调用方法来检索错误代码 [GetLastWin32Error](https://docs.microsoft.com/zh-cn/dotnet/api/system.runtime.interopservices.marshal.getlastwin32error) 。 由于错误代码可以由其他托管类库方法的内部调用覆盖，因此 `GetLastError` [GetLastWin32Error](https://docs.microsoft.com/zh-cn/dotnet/api/system.runtime.interopservices.marshal.getlastwin32error) 应在平台调用方法调用之后立即调用或方法。

规则将忽略对平台调用方法的调用和对的调用之间发生的对以下托管成员的调用 [GetLastWin32Error](https://docs.microsoft.com/zh-cn/dotnet/api/system.runtime.interopservices.marshal.getlastwin32error) 。 这些成员不会更改错误代码，并有助于确定一些平台调用方法调用是否成功。

- [System.IntPtr.Zero](https://docs.microsoft.com/zh-cn/dotnet/api/system.intptr.zero)
- [System.IntPtr.Equality](https://docs.microsoft.com/zh-cn/dotnet/api/system.intptr.op_equality)
- [System.IntPtr.Inequality](https://docs.microsoft.com/zh-cn/dotnet/api/system.intptr.op_inequality)
- [System.Runtime.InteropServices.SafeHandle.IsInvalid](https://docs.microsoft.com/zh-cn/dotnet/api/system.runtime.interopservices.safehandle.isinvalid)

#### 如何解决冲突

若要修复与此规则的冲突，请将调用移到， [GetLastWin32Error](https://docs.microsoft.com/zh-cn/dotnet/api/system.runtime.interopservices.marshal.getlastwin32error) 以便它紧跟在对平台调用方法的调用之后。



##### 反例❌

```c#
using System;
using System.Runtime.InteropServices;
using System.Text;

namespace InteroperabilityLibrary
{
   internal class NativeMethods
   {
      private NativeMethods() {}

      // Violates rule UseManagedEquivalentsOfWin32Api.
      [DllImport("kernel32.dll", CharSet = CharSet.Auto, 
          SetLastError = true)]
      internal static extern int ExpandEnvironmentStrings(
         string lpSrc, StringBuilder lpDst, int nSize);
   }

   public class UseNativeMethod
   {
      string environmentVariable = "%TEMP%";
      StringBuilder expandedVariable;

      public void ViolateRule()
      {
         expandedVariable = new StringBuilder(100);

         if(NativeMethods.ExpandEnvironmentStrings(
            environmentVariable, 
            expandedVariable, 
            expandedVariable.Capacity) == 0)
         {
            // Violates rule CallGetLastErrorImmediatelyAfterPInvoke.
            Console.Error.WriteLine(Marshal.GetLastWin32Error());
         }
         else
         {
            Console.WriteLine(expandedVariable);
         }
      }
   }
}
```



##### 正例✔️

```c#
using System;
using System.Runtime.InteropServices;
using System.Text;

namespace InteroperabilityLibrary
{
   internal class NativeMethods
   {
      private NativeMethods() {}

      // Violates rule UseManagedEquivalentsOfWin32Api.
      [DllImport("kernel32.dll", CharSet = CharSet.Auto, 
          SetLastError = true)]
      internal static extern int ExpandEnvironmentStrings(
         string lpSrc, StringBuilder lpDst, int nSize);
   }

   public class UseNativeMethod
   {
      string environmentVariable = "%TEMP%";
      StringBuilder expandedVariable;

      public void SatisfyRule()
      {
         expandedVariable = new StringBuilder(100);

         if(NativeMethods.ExpandEnvironmentStrings(
            environmentVariable, 
            expandedVariable, 
            expandedVariable.Capacity) == 0)
         {
            // Satisfies rule CallGetLastErrorImmediatelyAfterPInvoke.
            int lastError = Marshal.GetLastWin32Error();
            Console.Error.WriteLine(lastError);
         }
         else
         {
            Console.WriteLine(expandedVariable);
         }
      }
   }
}
```





### CA1405:COM 可见类型的基类型应对 COM 可见

#### 原因

组件对象模型 (COM) 可见类型派生自非 COM 可见的类型。

#### 规则说明

当 COM 可见类型在新版本中添加成员时，必须遵守严格指导原则，以避免中断绑定到当前版本的 COM 客户端。 对于 COM 不可见的类型，假设在添加新成员时，不需要遵循这些 COM 版本控制规则。 但是，如果 COM 可见类型派生自 COM 不可见类型，并公开了 [System.Runtime.InteropServices.ClassInterfaceType](https://docs.microsoft.com/zh-cn/dotnet/api/system.runtime.interopservices.classinterfacetype) 或 [ClassInterfaceType](https://docs.microsoft.com/zh-cn/dotnet/api/system.runtime.interopservices.classinterfacetype) (默认) 的类接口，则基类型的所有公共成员将 (，除非它们特别标记为 com 不可见，这将是向 com 公开的冗余) 。 如果基类型在后续版本中添加了新成员，则绑定到派生类型的类接口的任何 COM 客户端都可能会中断。 COM 可见类型应仅从 COM 可见类型派生，以减少中断 COM 客户端的可能性。

#### 如何解决冲突

若要修复与此规则的冲突，请使基类型 COM 可见或派生类型 COM 不可见。

##### 反例❌

```c#
using System;
using System.Runtime.InteropServices;

[assembly: ComVisible(false)]
namespace InteroperabilityLibrary
{
   [ComVisible(false)]
   public class BaseClass
   {
      public void SomeMethod(int valueOne) {}
   }

   // This class violates the rule.
   [ComVisible(true)]
   public class DerivedClass : BaseClass
   {
      public void AnotherMethod(int valueOne, int valueTwo) {}
   }
}
```



### CA1410:应对 COM 注册方法进行匹配

#### 原因

类型声明用特性标记的方法 [System.Runtime.InteropServices.ComRegisterFunctionAttribute](https://docs.microsoft.com/zh-cn/dotnet/api/system.runtime.interopservices.comregisterfunctionattribute) ，但不声明用特性标记的方法 [System.Runtime.InteropServices.ComUnregisterFunctionAttribute](https://docs.microsoft.com/zh-cn/dotnet/api/system.runtime.interopservices.comunregisterfunctionattribute) ，反之亦然。

#### 规则说明

对于组件对象模型 (COM) 客户端创建 .NET 类型，必须先注册该类型。 如果可用，则在注册过程中将调用标记为属性的方法， [ComRegisterFunctionAttribute](https://docs.microsoft.com/zh-cn/dotnet/api/system.runtime.interopservices.comregisterfunctionattribute) 以运行用户指定的代码。 在注销过程中，将调用使用特性标记的相应方法， [ComUnregisterFunctionAttribute](https://docs.microsoft.com/zh-cn/dotnet/api/system.runtime.interopservices.comunregisterfunctionattribute) 以撤消注册方法的操作。

#### 如何解决冲突

若要修复与此规则的冲突，请添加相应的注册或注销方法。



##### 反例❌

```c#
using System;
using System.Runtime.InteropServices;

[assembly: ComVisible(true)]
namespace InteroperabilityLibrary
{
   public class ClassToRegister
   {
   }

   public class ComRegistration
   {
      [ComRegisterFunction]
      internal static void RegisterFunction(Type typeToRegister) {}
   }
}
```

##### 正例✔️

```c#
using System;
using System.Runtime.InteropServices;

[assembly: ComVisible(true)]
namespace InteroperabilityLibrary
{
   public class ClassToRegister
   {
   }

   public class ComRegistration
   {
       // ComUnregisterFunction
      [ComUnregisterFunction]
      internal static void RegisterFunction(Type typeToRegister) {}
   }
}
```



### CA1415：正确声明 P/Invoke

#### 原因

平台调用方法的声明不正确。

#### 规则说明

平台调用方法访问非托管代码，并通过使用 `Declare` 或中的关键字定义 Visual Basic [System.Runtime.InteropServices.DllImportAttribute](https://docs.microsoft.com/zh-cn/dotnet/api/system.runtime.interopservices.dllimportattribute) 。 目前，此规则查找目标 Win32 函数的平台调用方法声明，这些函数具有指向重叠结构参数的指针，并且相应的托管参数不是指向结构的指针 [System.Threading.NativeOverlapped](https://docs.microsoft.com/zh-cn/dotnet/api/system.threading.nativeoverlapped) 

#### 如何解决冲突

若要修复与此规则的冲突，请正确声明平台调用方法。



##### 反例❌

```c#
using System;
using System.Runtime.InteropServices;
using System.Threading;

namespace InteroperabilityLibrary
{
    // The platform invoke methods in this class violate the rule.
    [ComVisible(true)]
    internal class NativeMethods
    {
        private NativeMethods() { }

        [DllImport("kernel32.dll", SetLastError = true)]
        internal extern static uint ReadFile(
           IntPtr hFile, IntPtr lpBuffer, int nNumberOfBytesToRead,
           IntPtr lpNumberOfBytesRead, IntPtr overlapped);

        [DllImport("kernel32.dll", SetLastError = true)]
        [return: MarshalAs(UnmanagedType.Bool)]
        internal extern static bool ReadFileEx(
           IntPtr hFile, IntPtr lpBuffer, int nNumberOfBytesToRead,
           NativeOverlapped overlapped, IntPtr lpCompletionRoutine);
    }
}
```

##### 正例✔️

```c#
using System;
using System.Runtime.InteropServices;
using System.Threading;

namespace InteroperabilityLibrary
{
    // The platform invoke methods in this class satisfy the rule.
    [ComVisible(true)]
    internal class UnsafeNativeMethods
    {
        private UnsafeNativeMethods() { }

        //To compile this code, uncomment these lines and compile
        //with "/unsafe".
        [DllImport("kernel32.dll", SetLastError = true)]
        unsafe internal extern static uint ReadFile(
           IntPtr hFile, IntPtr lpBuffer, int nNumberOfBytesToRead,
           IntPtr lpNumberOfBytesRead, NativeOverlapped* overlapped);

        [DllImport("kernel32.dll", SetLastError = true)]
        [return: MarshalAs(UnmanagedType.Bool)]
        unsafe internal extern static bool ReadFileEx(
           IntPtr hFile, IntPtr lpBuffer, int nNumberOfBytesToRead,
           NativeOverlapped* overlapped, IntPtr lpCompletionRoutine);
    }
}

```



###  .editorconfig 文件示例：

```ini
dotnet_diagnostic.CA1401.severity = warning
dotnet_diagnostic.CA1403.severity = warning
dotnet_diagnostic.CA1404.severity = warning
dotnet_diagnostic.CA1405.severity = warning
dotnet_diagnostic.CA1410.severity = warning
dotnet_diagnostic.CA1415.severity = warning
```



## 可维护性规则

可维护性规则支持库和应用程序维护。

- 严重性级别 ：警告



### CA1501:避免过度继承

#### 原因

类型在继承层次结构中的深度超过四级。

#### 规则说明

深度嵌套的类型层次结构可能很难遵循、理解和维护。 此规则将分析限制为同一个模块中的层次结构。

#### 如何解决冲突

若要修复与此规则的冲突，请从继承层次结构中不太深层的基类型派生类型，或消除某些中间基类型。



### CA1502:避免过度复杂

#### 原因

方法具有过多的圈复杂度。

#### 规则说明

*圈复杂度* 通过方法测量线性独立路径的数量，该方法由条件分支的数量和复杂程度决定。 低圈复杂度通常表示一种易于理解、测试和维护的方法。 圈复杂度是通过方法的控制流图形计算得出的，其提供方式如下：

圈复杂度 = 边数-节点数 + 1

*节点* 表示逻辑分支点，*边缘* 表示节点之间的线条。

当圈复杂度大于25时，规则将报告冲突。

可以在 [托管代码的复杂性](https://docs.microsoft.com/zh-cn/visualstudio/code-quality/code-metrics-values)上了解有关代码度量值的详细信息。

#### 如何解决冲突

若要修复与此规则的冲突，请重构方法以降低其圈复杂度。

### CA1505:避免使用无法维护的代码

#### 原因

类型或方法具有较低的可维护性索引值。

#### 规则说明

可维护性索引使用以下度量值进行计算：代码行、程序量和圈复杂度。 程序卷是对基于代码中的运算符和操作数的数目了解类型或方法有难度的度量。 圈复杂度是指类型或方法的结构复杂度的度量。 可以在 [测量托管代码的复杂性和可维护性](https://docs.microsoft.com/zh-cn/visualstudio/code-quality/code-metrics-values)中了解有关代码度量值的详细信息。

低可维护性索引指示类型或方法可能难以维护，并且是重新设计的良好候选项。

#### 如何解决冲突

若要解决此冲突，请重新设计类型或方法，并尝试将其拆分为更小且更有针对性的类型或方法。

### CA1506:避免过度类耦合度

#### 原因

类型或方法与许多其他类型耦合在一起。 编译器生成的类型已从此指标中排除。

#### 规则说明

此规则通过计算类型或方法包含的唯一类型引用的个数来衡量类耦合。

类耦合度较高的类型和方法可能很难维护。 有一个很好的做法，就是使用显示低耦合和高聚合的类型和方法。

#### 如何解决冲突

若要解决此冲突，请尝试重新设计类型或方法，以减少其耦合的类型的数量。

### CA1507:使用 nameof 代替字符串

#### 原因

`string`与包含方法的参数名称或包含类型的属性名称相匹配的文本或常数，用作方法的参数。

#### 规则说明

规则 CA1507 将文本的使用标记 `string` 为方法或构造函数的参数，在该方法或构造函数中， [nameof](https://docs.microsoft.com/zh-cn/dotnet/csharp/language-reference/operators/nameof) (`NameOf` Visual Basic) 表达式将添加可维护性。 如果满足以下所有条件，则会触发规则：



- 参数是一个 `string` 文本或常数。
- 参数对应于方法的 `string` 类型化参数或正在被调用的构造函数 (也就是说，调用站点) 不涉及转换。
- 可以是：
  - 参数的声明名称为 `paramName` ，而文本的常数值与方法 `string` 、lambda 或本地函数的参数的名称匹配，将在该参数中调用方法或构造函数。
  - 参数的声明名称为 `propertyName` ，文本的常数值 `string` 与要在其中调用方法或构造函数的类型的属性的名称匹配。



在将来可以重命名参数的情况下，规则 CA1507 可提高代码可维护性，但 `string` 不会错误地重命名该文本。 通过使用 `nameof` ，当通过重构操作重命名该参数时，将重命名符号。 此外，编译器将捕获参数名称中的任何拼写错误。

#### 如何解决冲突

若要解决冲突，请将 `string` 文本替换为 nameof 。

##### 反例❌

```c#
public Book(string title)
{
    // Violates rule CA1507
    Title = title ?? throw new ArgumentNullException("title", "All books must have a title.");
}
```

##### 正例✔️

```c#
public Book(string title)
{
    // Resolves rule CA1507 violation
    Title = title ?? throw new ArgumentNullException(nameof(title), "All books must have a title.");
}
```



### CA1508:避免死条件代码

#### 原因

方法具有始终计算为 `true` 或 `false` 在运行时的条件代码。 这会导致条件的分支中的代码停滞 `false` 。

#### 规则说明

方法可以具有条件代码，如 if 语句、二进制表达式 (`==` 、 `!=` 、 `<` 、 `>`) 、null 检查等。例如，请考虑以下代码：

```c#
public void M(int i, int j)
{
    if (i != 0)
    {
        return;
    }

    if (j != 0)
    {
        return;
    }

    // Below condition will always evaluate to 'false' as 'i' and 'j' are both '0' here.
    if (i != j)
    {
        // Code in this 'if' branch is dead code.
        // It can either be removed or refactored.
        ...
    }
}
```

C # 和 VB 编译器会分析涉及始终计算为或的编译时 *常数值* 的条件 `true` 检查 `false` 。 此分析器对非常量变量执行数据流分析，以确定涉及 *非常量值* 的冗余条件检查。 在前面的代码中，分析器将确定 `i` 和 `j` 都 `0` 适用于所有到达检查的代码路径 `i != j` 。 因此，在运行时，此检查的计算结果将始终为 `false` 。 If 语句内的代码是死代码，可以删除或重构。 同样，analyzer 还会跟踪变量的非 null，并报告冗余的 null 检查。



#### 如何解决冲突

删除死条件代码或重构



###  .editorconfig 文件示例：

```ini
dotnet_diagnostic.CA1501.severity = warning
dotnet_diagnostic.CA1502.severity = warning
dotnet_diagnostic.CA1505.severity = warning
dotnet_diagnostic.CA1506.severity = warning
dotnet_diagnostic.CA1507.severity = warning
dotnet_diagnostic.CA1508.severity = warning
```





## 命名规则

命名规则支持遵从 [.net 设计准则的命名约定](https://docs.microsoft.com/zh-cn/dotnet/standard/design-guidelines/naming-guidelines)。

- 严重性级别 ：警告

### CA1700:不要命名“Reserved”枚举值

#### 原因

枚举成员的名称包含单词 "reserved"。

#### 规则说明

此规则假定当前不使用名称中包含“reserved”的枚举成员，而是将其作为一个占位符，以在将来的版本中重命名或移除它。 重命名或移除成员是一项重大更改。 不应指望用户只是因为其名称包含 "保留" 而忽略成员，也不能依赖于用户阅读或遵守文档。 此外，由于保留成员显示在对象浏览器和智能集成开发环境中，因此它们可能会导致混淆实际使用的成员。

在将来的版本中，将新成员添加到枚举，而不是使用保留成员。 在大多数情况下，添加新成员不是一项重大更改，只要添加不会导致原始成员的值发生变化。

在有限的情况下，添加成员是一项重大更改，即使原始成员保留其原始值也是如此。 主要是，不能从现有的代码路径返回新成员，而不会中断使用对 `switch` `Select` 包含整个成员列表的返回值使用) Visual Basic (的调用方，并且在默认情况下会引发异常。 第二个问题是：客户端代码可能无法处理反射方法（如）的行为更改 [System.Enum.IsDefined](https://docs.microsoft.com/zh-cn/dotnet/api/system.enum.isdefined) 。 相应地，如果新成员必须从现有方法返回，或者由于反射的使用不正确而发生已知的应用程序不兼容性，则唯一的不间断解决方案是：

1. 添加包含原始成员和新成员的新枚举。

2. 用特性标记原始枚举 [System.ObsoleteAttribute](https://docs.microsoft.com/zh-cn/dotnet/api/system.obsoleteattribute) 。

   对于公开原始枚举的任何外部可见类型或成员，请遵循相同的过程。

#### 如何解决冲突

若要修复与此规则的冲突，请删除或重命名该成员。



### CA1711:标识符应采用正确的后缀

#### 原因

标识符的后缀不正确。

#### 规则说明

按照约定，只有扩展某些基类型或实现某些接口的类型的名称，或从这些类型派生的类型的名称应以特定的保留后缀结尾。 其他类型名称不应使用这些保留的后缀。



下表列出了保留的后缀以及与它们关联的基类型和接口。

| Suffix       | Base type/Interface                                          |
| :----------- | :----------------------------------------------------------- |
| Attribute    | [System.Attribute](https://docs.microsoft.com/en-us/dotnet/api/system.attribute) |
| Collection   | [System.Collections.ICollection](https://docs.microsoft.com/en-us/dotnet/api/system.collections.icollection)  [System.Collections.IEnumerable](https://docs.microsoft.com/en-us/dotnet/api/system.collections.ienumerable)  [System.Collections.Queue](https://docs.microsoft.com/en-us/dotnet/api/system.collections.queue)  [System.Collections.Stack](https://docs.microsoft.com/en-us/dotnet/api/system.collections.stack)  [System.Collections.Generic.ICollection](https://docs.microsoft.com/en-us/dotnet/api/system.collections.generic.icollection-1)  [System.Data.DataSet](https://docs.microsoft.com/en-us/dotnet/api/system.data.dataset)  [System.Data.DataTable](https://docs.microsoft.com/en-us/dotnet/api/system.data.datatable) |
| Dictionary   | [System.Collections.IDictionary](https://docs.microsoft.com/en-us/dotnet/api/system.collections.idictionary)  [System.Collections.Generic.IDictionary](https://docs.microsoft.com/en-us/dotnet/api/system.collections.generic.idictionary-2) |
| EventArgs    | [System.EventArgs](https://docs.microsoft.com/en-us/dotnet/api/system.eventargs) |
| EventHandler | An event-handler delegate                                    |
| Exception    | [System.Exception](https://docs.microsoft.com/en-us/dotnet/api/system.exception) |
| Permission   | [System.Security.IPermission](https://docs.microsoft.com/en-us/dotnet/api/system.security.ipermission) |
| Queue        | [System.Collections.Queue](https://docs.microsoft.com/en-us/dotnet/api/system.collections.queue) |
| Stack        | [System.Collections.Stack](https://docs.microsoft.com/en-us/dotnet/api/system.collections.stack) |
| Stream       | [System.IO.Stream](https://docs.microsoft.com/en-us/dotnet/api/system.io.stream) |

此外， **不应**使用以下后缀：

- `Delegate`
- `Enum`
- `Impl` (改用 `Core`) 
- `Ex` 或类似的后缀，以将其与同一类型的早期版本区分开来
- `Flag` 或 `Flags` 枚举类型

命名约定为面向公共语言运行时的库提供了通用的外观。 这减少了新软件库所需的学习曲线，并使客户可以放心地了解库是由具有开发托管代码的专业技能的人员开发的。 有关详细信息，请参阅 [命名准则：类、结构和接口](https://docs.microsoft.com/zh-cn/dotnet/standard/design-guidelines/names-of-classes-structs-and-interfaces)。

#### 如何解决冲突

从类型名称中删除后缀。



### CA1713:事件不应具有 before 或 after 前缀

#### 原因

事件的名称以 "Before" 或 "After" 开头

#### 规则说明

事件名称应描述引发事件的操作。 若要命名按特定顺序引发的相关事件，请使用现在时或过去时指示一系列操作中的相对位置。 例如，在为关闭资源时引发的事件对进行命名时，可以将其命名为 "关闭" 和 "关闭"，而不是 "BeforeClose" 和 "AfterClose"。

命名约定为面向公共语言运行时的库提供了通用的外观。 这减少了新软件库所需的学习曲线，并使客户可以放心地了解库是由具有开发托管代码的专业技能的人员开发的。

#### 如何解决冲突

从事件名称中删除前缀，并考虑将该名称更改为使用谓词的现在时或过去时。



### CA1715:标识符应具有正确的前缀

#### 原因

接口的名称不以大写的 "I" 开头。

类型或方法的 [泛型类型参数](https://docs.microsoft.com/zh-cn/dotnet/csharp/programming-guide/generics/generic-type-parameters) 的名称不以大写 "t" 开头。

#### 规则说明

按照约定，某些编程元素的名称以特定的前缀开头。

接口名称应以大写的 "I" 开头，后跟另一个大写字母。 此规则报告接口名称（如 "MyInterface" 和 "IsolatedInterface"）的冲突。

泛型类型参数名称应以大写 "t" 开头，可选择后跟另一个大写字母。 此规则报告泛型类型参数名称（如 "V" 和 "Type"）的冲突。

#### 如何解决冲突

重命名标识符，使其具有正确的前缀。



### CA1724:类型名不应与命名空间冲突

#### 原因

类型名称与具有一个或多个外部可见类型的被引用命名空间名称匹配。 名称比较不区分大小写。

#### 规则说明

用户创建的类型名称不应与具有外部可见类型的被引用命名空间的名称相匹配。 违反此规则会降低库的可用性。

#### 如何解决冲突

重命名该类型，使其与具有外部可见类型的引用命名空间的名称不匹配。

##### 反例❌

```c#
namespace MyNamespace
{
    // This class violates the rule
    public class System
    {
    }
}
```

##### 

### CA1725:参数名应与基方法中的声明保持一致

#### 原因

方法重写中的参数名称与方法的基声明中的参数名称或该方法的接口声明中的参数名称不匹配。

#### 规则说明

以一致的方式命名重写层次结构中的参数可以提高方法重写的可用性。 如果派生方法中的参数名与基声明中的名称不同，可能会导致无法区分出该方法是基方法的重写还是该方法的新重载。

#### 如何解决冲突

若要修复与此规则的冲突，请重命名参数以匹配基声明。 此修补程序是对 COM 可见方法的重大更改。

##### 反例❌

```c#
namespace MyNamespace
{
    class BaseType
    {
        internal virtual void MethodOne(string inputOne, object inputTwo)
        {
        }
    }

    class DerivedType : BaseType
    {
        internal override void MethodOne(string input1, object input2)
        {
        }
    }
}
```

##### 正例✔️

```c#
namespace MyNamespace
{
    class BaseType
    {
        internal virtual void MethodOne(string inputOne, object inputTwo)
        {
        }
    }

    class DerivedType : BaseType
    {
        internal override void MethodOne(string inputOne, object inputTwo)
        {
        }
    }
}
```



###  .editorconfig 文件示例：

```ini
dotnet_diagnostic.CA1710.severity = warning
dotnet_diagnostic.CA1711.severity = warning
dotnet_diagnostic.CA1713.severity = warning
dotnet_diagnostic.CA1715.severity = warning
dotnet_diagnostic.CA1724.severity = warning
dotnet_diagnostic.CA1725.severity = warning
dotnet_code_quality.CA1725.api_surface = private, internal
```





## 性能规则

性能规则支持高性能库和应用程序。

- 严重性级别 ：警告



### CA1820:使用字符串长度测试是否有空字符串

#### 原因

使用将字符串与空字符串进行比较 [Object.Equals](https://docs.microsoft.com/zh-cn/dotnet/api/system.object.equals) 。

#### 规则说明

使用 [String.Length](https://docs.microsoft.com/zh-cn/dotnet/api/system.string.length) 属性或方法比较字符串 [String.IsNullOrEmpty](https://docs.microsoft.com/zh-cn/dotnet/api/system.string.isnullorempty) 比使用更快 [Equals](https://docs.microsoft.com/zh-cn/dotnet/api/system.object.equals) 。 这是因为 [Equals](https://docs.microsoft.com/zh-cn/dotnet/api/system.object.equals) 执行的 MSIL 指令比 [IsNullOrEmpty](https://docs.microsoft.com/zh-cn/dotnet/api/system.string.isnullorempty) 用于检索 [Length](https://docs.microsoft.com/zh-cn/dotnet/api/system.string.length) 属性值并将其与零比较的指令数更多。

对于 null 字符串， [Equals](https://docs.microsoft.com/zh-cn/dotnet/api/system.object.equals) 和的 `<string>.Length == 0` 行为不同。 如果尝试获取 [Length](https://docs.microsoft.com/zh-cn/dotnet/api/system.string.length) null 字符串的属性值，则公共语言运行时将引发 [System.NullReferenceException](https://docs.microsoft.com/zh-cn/dotnet/api/system.nullreferenceexception) 。 如果在空字符串和空字符串之间执行比较，则公共语言运行时不会引发异常，并返回 `false` 。 测试 null 不会对这两种方法的相对性能产生显著影响。 面向 .NET Framework 2.0 或更高版本时，请使用 [IsNullOrEmpty](https://docs.microsoft.com/zh-cn/dotnet/api/system.string.isnullorempty) 方法。 否则，请 [Length](https://docs.microsoft.com/zh-cn/dotnet/api/system.string.length) 尽可能使用 = = 0 比较。

#### 如何解决冲突

若要修复与此规则的冲突，请将比较更改为使用 [IsNullOrEmpty](https://docs.microsoft.com/zh-cn/dotnet/api/system.string.isnullorempty) 方法。



### CA1821:移除空终结器

#### 原因

类型实现了一个空的终结器，只调用基类型的终结器或只调用有条件发出的方法。

#### 规则说明

无论何时，都可以避免终结器，因为跟踪对象生存期涉及到额外的性能开销。 垃圾回收器在它收集对象之前运行终结器。 这意味着收集对象至少需要两个集合。 空终结器会产生这种额外的开销，而不会带来任何好处。

#### 如何解决冲突

删除空的终结器。 如果调试需要终结器，请将整个终结器置于 `#if DEBUG / #endif` 指令中。



##### 反例❌

```c#
    public class Class1
    {
        // Violation occurs because the finalizer is empty.
        ~Class1()
        {
        }
    }
```



### CA1823:避免未使用的私有字段

#### 原因

当私有字段存在于代码中但未由任何代码路径使用时，将报告此规则。

#### 规则说明

检测到程序集内有似乎未访问过的私有字段。

#### 如何解决冲突

若要修复与此规则的冲突，请删除字段或添加使用该字段的代码。



###  .editorconfig 文件示例：

```ini
dotnet_diagnostic.CA1820.severity = warning
dotnet_diagnostic.CA1821.severity = warning
dotnet_diagnostic.CA1823.severity = warning
```



## 可靠性规则

可靠性规则支持库和应用程序的可靠性，如正确的内存和线程使用。

- 严重性级别 ：警告



### CA2002:不要锁定具有弱标识的对象

#### 原因

线程尝试在具有弱标识的对象上获取锁。

#### 规则说明

当可以跨应用程序域边界直接进行访问对象时，则认为该对象具有弱标识。 对于尝试获取对具有弱标识的对象的锁的线程，该线程可能会被其他应用程序域中持有对同一对象的锁的另一线程所阻止。

以下类型具有弱标识，并由规则标记：

- [String](https://docs.microsoft.com/zh-cn/dotnet/api/system.string)
- 值类型的数组，包括 [整数类型](https://docs.microsoft.com/zh-cn/dotnet/csharp/language-reference/builtin-types/integral-numeric-types)、 [浮点类型](https://docs.microsoft.com/zh-cn/dotnet/csharp/language-reference/builtin-types/floating-point-numeric-types)和 [Boolean](https://docs.microsoft.com/zh-cn/dotnet/api/system.boolean) 。
- [MarshalByRefObject](https://docs.microsoft.com/zh-cn/dotnet/api/system.marshalbyrefobject)
- [ExecutionEngineException](https://docs.microsoft.com/zh-cn/dotnet/api/system.executionengineexception)
- [OutOfMemoryException](https://docs.microsoft.com/zh-cn/dotnet/api/system.outofmemoryexception)
- [StackOverflowException](https://docs.microsoft.com/zh-cn/dotnet/api/system.stackoverflowexception)
- [MemberInfo](https://docs.microsoft.com/zh-cn/dotnet/api/system.reflection.memberinfo)
- [ParameterInfo](https://docs.microsoft.com/zh-cn/dotnet/api/system.reflection.parameterinfo)
- [Thread](https://docs.microsoft.com/zh-cn/dotnet/api/system.threading.thread)
- [this](https://docs.microsoft.com/zh-cn/dotnet/csharp/language-reference/keywords/this)  的对象

#### 如何解决冲突

若要修复与此规则的冲突，请使用 "说明" 部分中不在列表中的类型的对象。



###  .editorconfig 文件示例：

```ini
dotnet_diagnostic.CA2002.severity = warning
```





## 安全规则

安全规则支持更安全的库和应用程序。 这些规则可帮助防止程序中出现安全漏洞。 如果您禁用这些规则中的任何一种，您应该清楚地标记代码中的原因，并向您的开发项目通知指定的安全官员。

- 严重性级别 ：警告



### CA2100:检查 SQL 查询是否存在安全漏洞

#### 原因

方法 [System.Data.IDbCommand.CommandText](https://docs.microsoft.com/zh-cn/dotnet/api/system.data.idbcommand.commandtext) 通过使用从字符串参数生成的字符串为方法设置属性。

#### 规则说明

此规则假定其值不能在编译时确定的任何字符串可能包含用户输入。 基于用户输入生成的 SQL 命令字符串易于受到 SQL 注入式攻击。 在 SQL 注入式攻击中，恶意用户提供的输入会改变查询的设计，试图破坏或获取对基础数据库的未经授权的访问。 典型方法包括单引号或撇号的注入，这是 SQL 文本字符串分隔符;双短划线，表示 SQL 注释;和一个分号，指示下面的新命令。 如果 "用户输入" 必须是查询的一部分，请使用下列项之一（按有效性顺序列出）来降低遭受攻击的风险。

- 使用存储过程。
- 使用参数化命令字符串。
- 在生成命令字符串之前，验证类型和内容的用户输入。

下面的 .NET 类型实现 [CommandText](https://docs.microsoft.com/zh-cn/dotnet/api/system.data.idbcommand.commandtext) 属性，或提供使用字符串参数设置属性的构造函数。

- [System.Data.Odbc.OdbcCommand](https://docs.microsoft.com/zh-cn/dotnet/api/system.data.odbc.odbccommand) 和 [System.Data.Odbc.OdbcDataAdapter](https://docs.microsoft.com/zh-cn/dotnet/api/system.data.odbc.odbcdataadapter)
- [System.Data.OleDb.OleDbCommand](https://docs.microsoft.com/zh-cn/dotnet/api/system.data.oledb.oledbcommand) 和 [System.Data.OleDb.OleDbDataAdapter](https://docs.microsoft.com/zh-cn/dotnet/api/system.data.oledb.oledbdataadapter)
- [System.Data.OracleClient.OracleCommand](https://docs.microsoft.com/zh-cn/dotnet/api/system.data.oracleclient.oraclecommand) 和 [System.Data.OracleClient.OracleDataAdapter](https://docs.microsoft.com/zh-cn/dotnet/api/system.data.oracleclient.oracledataadapter)
- [System.Data.SqlClient.SqlCommand](https://docs.microsoft.com/zh-cn/dotnet/api/system.data.sqlclient.sqlcommand) 和 [System.Data.SqlClient.SqlDataAdapter](https://docs.microsoft.com/zh-cn/dotnet/api/system.data.sqlclient.sqldataadapter)



在某些情况下，此规则在编译时可能不会确定字符串的值，即使你可以这样做。

#### 如何解决冲突

若要修复与此规则的冲突，请使用参数化查询。



#### 示例

下面的示例演示了一个方法， `UnsafeQuery` 该方法违反了规则和一个方法❌， `SaferQuery` 该方法通过使用参数化命令字符串满足规则✔️。

```c#
public class SqlQueries
{
    public object UnsafeQuery(
       string connection, string name, string password)
    {
        SqlConnection someConnection = new SqlConnection(connection);
        SqlCommand someCommand = new SqlCommand();
        someCommand.Connection = someConnection;

        someCommand.CommandText = "SELECT AccountNumber FROM Users " +
           "WHERE Username='" + name +
           "' AND Password='" + password + "'";

        someConnection.Open();
        object accountNumber = someCommand.ExecuteScalar();
        someConnection.Close();
        return accountNumber;
    }

    public object SaferQuery(
       string connection, string name, string password)
    {
        SqlConnection someConnection = new SqlConnection(connection);
        SqlCommand someCommand = new SqlCommand();
        someCommand.Connection = someConnection;

        someCommand.Parameters.Add(
           "@username", SqlDbType.NChar).Value = name;
        someCommand.Parameters.Add(
           "@password", SqlDbType.NChar).Value = password;
        someCommand.CommandText = "SELECT AccountNumber FROM Users " +
           "WHERE Username=@username AND Password=@password";

        someConnection.Open();
        object accountNumber = someCommand.ExecuteScalar();
        someConnection.Close();
        return accountNumber;
    }
}

class MaliciousCode
{
    static void Main2100(string[] args)
    {
        SqlQueries queries = new SqlQueries();
        queries.UnsafeQuery(args[0], "' OR 1=1 --", "[PLACEHOLDER]");
        // Resultant query (which is always true):
        // SELECT AccountNumber FROM Users WHERE Username='' OR 1=1

        queries.SaferQuery(args[0], "' OR 1=1 --", "[PLACEHOLDER]");
        // Resultant query (notice the additional single quote character):
        // SELECT AccountNumber FROM Users WHERE Username=''' OR 1=1 --'
        //                                   AND Password='[PLACEHOLDER]'
    }
}
```



### CA2109:检查可见的事件处理程序

#### 原因

检测到公共事件处理方法或受保护事件处理方法。

#### 规则说明

外部可见的事件处理方法显示了需要查看的安全问题。

除非绝对必要，否则不要公开事件处理方法。 只要处理程序和事件签名匹配，就可以将调用公开方法的事件处理程序（委托类型）添加到任何事件。 事件可能由任何代码引发，并经常由高度可信的系统代码引发，以响应用户操作（例如单击某个按钮）。 向事件处理方法添加安全检查不会阻止代码注册调用方法的事件处理程序。

请求无法可靠地保护由事件处理程序调用的方法。 安全要求通过检查调用堆栈上的调用方来帮助保护来自不受信任的调用方的代码。 向事件添加事件处理程序的代码在事件处理程序的方法运行时不一定存在于调用堆栈上。 因此，调用堆栈在调用事件处理程序方法时可能只有高度受信任的调用方。 这将导致事件处理程序方法所做的请求成功。 此外，当调用方法时，可能会断言请求的权限。 由于这些原因，在查看事件处理方法后，只能评估不能解决此规则冲突的风险。 检查代码时，请考虑以下问题：

- 你的事件处理程序是否执行任何危险或可利用的操作，如断言权限或禁止非托管代码权限？
- 与代码之间的安全威胁是什么，因为它可以随时在堆栈上只有高度受信任的调用方运行？

#### 如何解决冲突

若要修复与此规则的冲突，请查看方法并评估以下各项：

- 是否可以使事件处理方法成为非公共的？
- 是否可以将所有危险功能移出事件处理程序？
- 如果实施了安全要求，是否可以通过其他方式实现此目的？

##### 反例❌

下面的代码演示了一个可能被恶意代码误用的事件处理方法。

```c#
public class HandleEvents
{
    // Due to the access level and signature, a malicious caller could 
    // add this method to system-triggered events where all code in the call
    // stack has the demanded permission.

    // Also, the demand might be canceled by an asserted permission.

    [SecurityPermissionAttribute(SecurityAction.Demand, UnmanagedCode = true)]

    // Violates rule: ReviewVisibleEventHandlers.
    public static void SomeActionHappened(Object sender, EventArgs e)
    {
        Console.WriteLine("Do something dangerous from unmanaged code.");
    }
}
```



### CA2119:密封满足私有接口的方法

#### 原因

可继承的公共类型为 `internal` 接口中的 提供可重写的方法实现。

#### 规则说明

接口方法具有公共可访问性，实现类型不能对其进行更改。 内部接口创建一个协定，该协定不应在定义接口的程序集的外部实现。 使用 修饰符中的 实现内部接口的方法的公共类型 `virtual` `Overridable` 允许该方法由程序集之外的派生类型重写。 如果定义程序集中的第二个类型调用方法并且需要仅限内部的协定，则在执行外部程序集中重写的方法时，行为可能会受到影响。 这会产生安全漏洞。

#### 如何解决冲突

若要修复与此规则的冲突，请使用以下方法之一阻止在程序集外重写方法：

- 使声明类型 `sealed` (`NotInheritable` Visual Basic) 中。
- 将声明类型的可访问性更改 `internal` 为 `Friend` Visual Basic) 中 (。
- 删除声明类型中的所有公共构造函数。
- 无需使用修饰符即可实现此方法 `virtual` 。
- 显式实现方法。

##### 反例❌

```c#
namespace MyNamespace
{
    // Internal by default.
    interface IValidate
    {
        bool UserIsValidated();
    }

    public class BaseImplementation : IValidate
    {
        public virtual bool UserIsValidated()
        {
            return false;
        }
    }
}
```

##### 正例✔️

```c#
namespace MyNamespace
{
    public class BaseImplementation : IValidate
    {
        public virtual bool UserIsValidated()
        {
            return false;
        }
    }
}
```

```c#
namespace MyNamespace
{
    // Internal by default.
    interface IValidate
    {
        bool UserIsValidated();
    }

    public class BaseImplementation : IValidate
    {
        public bool UserIsValidated()
        {
            return false;
        }
    }
}
```



### CA5350:请勿使用弱加密算法

#### 原因

[TripleDES](https://docs.microsoft.com/zh-cn/dotnet/api/system.security.cryptography.tripledes) 等加密算法和 [SHA1](https://docs.microsoft.com/zh-cn/dotnet/api/system.security.cryptography.sha1) 及 [RIPEMD160](https://docs.microsoft.com/zh-cn/dotnet/api/system.security.cryptography.ripemd160) 等哈希算法被视为弱加密算法。

这些加密算法不能与更现代的对应算法提供同样多的安全保证。 与更现代的哈希算法相比，加密哈希算法 [SHA1](https://docs.microsoft.com/zh-cn/dotnet/api/system.security.cryptography.sha1) 和 [RIPEMD160](https://docs.microsoft.com/zh-cn/dotnet/api/system.security.cryptography.ripemd160) 提供的冲突抗性较低。 与更现代的加密算法相比，加密算法 [TripleDES](https://docs.microsoft.com/zh-cn/dotnet/api/system.security.cryptography.tripledes) 提供的安全位数更少。

#### 规则说明

现今出于多种原因而使用弱加密算法和哈希函数，但不应将其用于保证其所保护数据的保密性。

该规则在代码中发现 3DES、SHA1 或 RIPEMD160 算法时将触发并向用户发送警告。

#### 如何解决冲突

使用更强大的加密选项：

- 对于 TripleDES 加密，请使用 [Aes](https://docs.microsoft.com/zh-cn/dotnet/api/system.security.cryptography.aes) 加密。
- 对于 SHA1 或 RIPEMD160 哈希函数，请使用 [sha-1](https://docs.microsoft.com/zh-cn/windows/desktop/SecCrypto/hash-and-signature-algorithms) 系列中的函数 (例如 [SHA512](https://docs.microsoft.com/zh-cn/dotnet/api/system.security.cryptography.sha512) ， [SHA384](https://docs.microsoft.com/zh-cn/dotnet/api/system.security.cryptography.sha384) [SHA256](https://docs.microsoft.com/zh-cn/dotnet/api/system.security.cryptography.sha256)) 。



### CA5358:请勿使用不安全的密码模式

#### 原因

使用未批准的下列不安全加密模式之一：

- [System.Security.Cryptography.CipherMode.ECB](https://docs.microsoft.com/zh-cn/dotnet/api/system.security.cryptography.ciphermode#System_Security_Cryptography_CipherMode_ECB)
- [System.Security.Cryptography.CipherMode.OFB](https://docs.microsoft.com/zh-cn/dotnet/api/system.security.cryptography.ciphermode#System_Security_Cryptography_CipherMode_OFB)
- [System.Security.Cryptography.CipherMode.CFB](https://docs.microsoft.com/zh-cn/dotnet/api/system.security.cryptography.ciphermode#System_Security_Cryptography_CipherMode_CFB)

#### 规则说明

这些模式容易受到攻击，并可能导致敏感信息泄露。 例如，使用 `ECB` 对纯文本块进行加密将始终生成相同的密码文本，因此它可以轻松地判断两个加密消息是否相同。 使用批准的模式可以避免这些不必要的风险。

#### 如何解决冲突

仅使用批准的模式 [System.Security.Cryptography.CipherMode.CBC](https://docs.microsoft.com/zh-cn/dotnet/api/system.security.cryptography.ciphermode#System_Security_Cryptography_CipherMode_CBC) ([System.Security.Cryptography.CipherMode.CTS](https://docs.microsoft.com/zh-cn/dotnet/api/system.security.cryptography.ciphermode#System_Security_Cryptography_CipherMode_CTS)) 。



### CA5397:不使用已弃用的 SslProtocols 值

#### 原因

如果满足以下任一条件，则会触发此规则：

- 引用了不推荐使用的 [System.Security.Authentication.SslProtocols](https://docs.microsoft.com/zh-cn/dotnet/api/system.security.authentication.sslprotocols) 值。
- 表示不推荐使用的值的整数值被分配给 [SslProtocols](https://docs.microsoft.com/zh-cn/dotnet/api/system.security.authentication.sslprotocols) 变量，用作  [SslProtocols](https://docs.microsoft.com/zh-cn/dotnet/api/system.security.authentication.sslprotocols) 返回值，或用作 [SslProtocols](https://docs.microsoft.com/zh-cn/dotnet/api/system.security.authentication.sslprotocols) 参数。

弃用的值 ：

- ssl2
- Ssl3
- Tls
-  tls10
- Tls11

#### 规则说明

传输层安全 (TLS) 保护计算机之间的通信，最常见的是通过超文本传输协议安全 (HTTPS) 。 较早的 TLS 协议版本不如 TLS 1.2 和 TLS 1.3 安全，更有可能出现新的漏洞。 避免旧协议版本来最大程度地降低风险。 有关标识和删除不推荐使用的协议版本的指导，请参阅 [解决 TLS 1.0 问题第2版](https://docs.microsoft.com/zh-cn/security/solving-tls1-problem)。

#### 如何解决冲突

不要使用不推荐使用的 TLS 协议版本。



###  .editorconfig 文件示例：

```ini
dotnet_diagnostic.CA2100.severity = warning
dotnet_diagnostic.CA2109.severity = warning
dotnet_diagnostic.CA2119.severity = warning
dotnet_diagnostic.CA5350.severity = warning
dotnet_diagnostic.CA5358.severity = warning
dotnet_diagnostic.CA5397.severity = warning
```



## 用法规则

使用规则支持正确使用 .NET。

- 严重性级别 ：警告



### CA2200:再次引发以保留堆栈详细信息

#### 原因

重新引发了异常，并且在语句中显式指定了异常 `throw` 。

#### 规则说明

引发异常后，它所携带的信息的一部分是堆栈跟踪。 堆栈跟踪是一个方法调用层次结构列表，它以引发异常的方法开头，以捕获异常的方法结尾。 如果通过在语句中指定异常来重新引发异常，则将 `throw` 在当前方法处重新启动堆栈跟踪，并将丢失引发异常的原始方法和当前方法之间的方法调用的列表。 若要保留原始堆栈跟踪信息和异常信息，请在未指定异常的情况下使用 `throw` 语句。

#### 如何解决冲突

要修复与此规则的冲突，请再次引发异常，而不显式指定异常。



##### 反例❌

```c#
void CatchAndRethrowExplicitly()
{
    try
    {
        ThrowException();
    }
    catch (ArithmeticException e)
    {
        // Violates the rule.
        throw e;
    }
}
```

##### 正例✔️

```c#
void CatchAndRethrowImplicitly()
{
    try
    {
        ThrowException();
    }
    catch (ArithmeticException)
    {
        // Satisfies the rule.
        throw;
    }
}
```





### CA2207:以内联方式初始化值类型的静态字段

#### 原因

值类型声明显式静态构造函数。

#### 规则说明

当声明值类型时，它会经历默认初始化，其中所有值类型字段均设置为零，所有引用类型字段都设置为 `null` `Nothing` Visual Basic) 中 (。 仅保证在调用类型的实例构造函数或静态成员之前运行显式静态构造函数。 因此，如果创建类型时未调用实例构造函数，则不保证静态构造函数运行。

如果所有静态数据都以内联方式初始化并且未声明显式静态构造函数，则 c # 和 Visual Basic 编译器会将 `beforefieldinit` 标志添加到 MSIL 类定义。 编译器还会添加包含静态初始化代码的私有静态构造函数。 此私有静态构造函数保证在访问类型的任何静态字段之前运行。

#### 如何解决冲突

若要修复与此规则的冲突，请在声明时初始化所有静态数据，并删除静态构造函数。



### CA2213:应释放可释放的字段

#### 原因

一种类型，该类型实现 [System.IDisposable](https://docs.microsoft.com/zh-cn/dotnet/api/system.idisposable) 也实现的类型的字段 [IDisposable](https://docs.microsoft.com/zh-cn/dotnet/api/system.idisposable) 。 该 [Dispose](https://docs.microsoft.com/zh-cn/dotnet/api/system.idisposable.dispose) 字段的方法不由 [Dispose](https://docs.microsoft.com/zh-cn/dotnet/api/system.idisposable.dispose) 声明类型的方法调用。

#### 规则说明

类型负责释放其所有非托管资源。 规则 CA2213 将检查是否可释放类型 (即，实现) 的类型 [IDisposable](https://docs.microsoft.com/zh-cn/dotnet/api/system.idisposable) 声明一个 `T` 字段 `F` ，该字段是可释放类型的实例 `FT` 。 对于为每个 `F` 分配了本地创建的对象的字段，在包含类型的方法或初始值设定项中 `T` ，该规则将尝试查找对的调用 `FT.Dispose` 。 此规则搜索调用的方法 `T.Dispose` 和一个级别下限 (即，由) 调用的方法调用的方法 `T.Dispose` 。

##### 备注

除了 [特殊情况](https://docs.microsoft.com/zh-cn/dotnet/fundamentals/code-analysis/quality-rules/ca2213#special-cases)外，规则 CA2213 仅对在包含类型的方法和初始值设定项中分配了本地创建的可释放对象的字段引发。 如果在类型外创建或分配对象 `T` ，则不会触发规则。 这会减少包含类型不负责释放对象的情况下的干扰。

#### 如何解决冲突

若要修复与此规则的冲突，请 [Dispose](https://docs.microsoft.com/zh-cn/dotnet/api/system.idisposable.dispose) 对实现的类型的字段调用 [IDisposable](https://docs.microsoft.com/zh-cn/dotnet/api/system.idisposable) 。



##### 正例✔️

```c#
public class TypeA : IDisposable
{
    protected virtual void Dispose(bool disposing)
    {
        if (disposing)
        {
            // Dispose managed resources
        }

        // Free native resources
    }

    public void Dispose()
    {
        Dispose(true);
    }

    // Disposable types implement a finalizer.
    ~TypeA()
    {
        Dispose(false);
    }
}
```





### CA2214:不要在构造函数中调用可重写的方法

#### 原因

未密封类型的构造函数将调用在其类中定义的虚方法。

#### 规则说明

调用虚方法时，在运行时之前不会选择执行方法的实际类型。 当构造函数调用虚方法时，调用方法的实例的构造函数可能未执行。 如果重写的虚拟方法依赖于构造函数中的初始化和其他配置，这可能会导致错误或意外行为。

#### 如何解决冲突

若要修复与此规则的冲突，请不要从该类型的构造函数内调用类型的虚拟方法。



##### 反例❌

下面的示例演示违反此规则的效果。 测试应用程序将创建的实例 `DerivedType` ，从而使其基类 (`BadlyConstructedType` 要执行) 构造函数。 `BadlyConstructedType`的构造函数错误地调用了虚方法 `DoSomething` 。 如输出所示， `DerivedType.DoSomething()` 在 `DerivedType` 执行构造函数之前执行。

```c#
public class BadlyConstructedType
{
    protected string initialized = "No";

    public BadlyConstructedType()
    {
        Console.WriteLine("Calling base ctor.");
        // Violates rule: DoNotCallOverridableMethodsInConstructors.
        DoSomething();
    }
    // This will be overridden in the derived type.
    public virtual void DoSomething()
    {
        Console.WriteLine("Base DoSomething");
    }
}

public class DerivedType : BadlyConstructedType
{
    public DerivedType()
    {
        Console.WriteLine("Calling derived ctor.");
        initialized = "Yes";
    }
    public override void DoSomething()
    {
        Console.WriteLine("Derived DoSomething is called - initialized ? {0}", initialized);
    }
}

public class TestBadlyConstructedType
{
    public static void Main2214()
    {
        DerivedType derivedInstance = new DerivedType();
    }
}
```

该示例产生下面的输出：			 					 				 			 		

```txt
Calling base ctor.
Derived DoSomething is called - initialized ? No
Calling derived ctor.
```



### CA2216:可释放类型应声明终结器

#### 原因

一种类型，该类型实现 [System.IDisposable](https://docs.microsoft.com/zh-cn/dotnet/api/system.idisposable) 并具有建议使用非托管资源的字段，并不按描述实现终结器 [System.Object.Finalize](https://docs.microsoft.com/zh-cn/dotnet/api/system.object.finalize) 。

#### 规则说明

如果可释放类型包含以下类型的字段，则会报告此规则的冲突：

- [System.IntPtr](https://docs.microsoft.com/zh-cn/dotnet/api/system.intptr)
- [System.UIntPtr](https://docs.microsoft.com/zh-cn/dotnet/api/system.uintptr)
- [System.Runtime.InteropServices.HandleRef](https://docs.microsoft.com/zh-cn/dotnet/api/system.runtime.interopservices.handleref)

#### 如何解决冲突

若要修复与此规则的冲突，请实现调用方法的终结器 [Dispose](https://docs.microsoft.com/zh-cn/dotnet/api/system.idisposable.dispose) 。



##### 反例❌

下面的示例演示违反此规则的类型。

```c#
public class DisposeMissingFinalize : IDisposable
{
    private bool disposed = false;
    private IntPtr unmanagedResource;

    [DllImport("native.dll")]
    private static extern IntPtr AllocateUnmanagedResource();

    [DllImport("native.dll")]
    private static extern void FreeUnmanagedResource(IntPtr p);

    DisposeMissingFinalize()
    {
        unmanagedResource = AllocateUnmanagedResource();
    }

    protected virtual void Dispose(bool disposing)
    {
        if (!disposed)
        {
            // Dispose of resources held by this instance.
            FreeUnmanagedResource(unmanagedResource);
            disposed = true;

            // Suppress finalization of this disposed instance.
            if (disposing)
            {
                GC.SuppressFinalize(this);
            }
        }
    }

    public void Dispose()
    {
        Dispose(true);
    }

    // Disposable types with unmanaged resources implement a finalizer.
    // Uncomment the following code to satisfy rule:
    //  DisposableTypesShouldDeclareFinalizer
    // ~TypeA()
    // {
    //     Dispose(false);
    // }
}
```





### CA2229:实现序列化构造函数

#### 原因

该类型实现 [System.Runtime.Serialization.ISerializable](https://docs.microsoft.com/zh-cn/dotnet/api/system.runtime.serialization.iserializable) 接口，不是委托或接口，并且以下条件之一成立：

- 该类型没有构造函数，该构造函数将 [SerializationInfo](https://docs.microsoft.com/zh-cn/dotnet/api/system.runtime.serialization.serializationinfo) 对象和 [StreamingContext](https://docs.microsoft.com/zh-cn/dotnet/api/system.runtime.serialization.streamingcontext) 对象 (序列化构造函数的签名) 。
- 该类型是未密封的，并且其序列化构造函数的访问修饰符不受 (系列) 的保护。
- 类型是密封的，并且其序列化构造函数的访问修饰符不是私有的。

#### 规则说明

此规则适用于支持自定义序列化的类型。 如果类型实现接口，则它支持自定义序列化 [ISerializable](https://docs.microsoft.com/zh-cn/dotnet/api/system.runtime.serialization.iserializable) 。 序列化构造函数需要使用方法对已序列化的对象进行反序列化（或重新创建） [ISerializable.GetObjectData](https://docs.microsoft.com/zh-cn/dotnet/api/system.runtime.serialization.iserializable.getobjectdata) 。

#### 如何解决冲突

要修复与该规则的冲突，请实现序列化构造函数。 对于密封类，请使构造函数成为私有；否则，请使构造函数成为受保护。



##### 正例✔️

下面的示例演示一个满足规则的类型。

```c#
[Serializable]
public class SerializationConstructorsRequired : ISerializable
{
    private int n1;

    // This is a regular constructor.
    public SerializationConstructorsRequired()
    {
        n1 = -1;
    }
    // This is the serialization constructor.
    // Satisfies rule: ImplementSerializationConstructors.

    protected SerializationConstructorsRequired(
       SerializationInfo info,
       StreamingContext context)
    {
        n1 = (int)info.GetValue(nameof(n1), typeof(int));
    }

    // The following method serializes the instance.
    [SecurityPermission(SecurityAction.LinkDemand,
        Flags = SecurityPermissionFlag.SerializationFormatter)]
    void ISerializable.GetObjectData(SerializationInfo info,
       StreamingContext context)
    {
        info.AddValue(nameof(n1), n1);
    }
}
```



### CA2231:重写 ValueType.Equals 时应重载相等运算符

#### 原因

值类型会重写 [System.Object.Equals](https://docs.microsoft.com/zh-cn/dotnet/api/system.object.equals) ，但不实现相等运算符。

默认情况下，此规则仅查看外部可见类型，但这是 [可配置](https://docs.microsoft.com/zh-cn/dotnet/fundamentals/code-analysis/quality-rules/ca2231#configure-code-to-analyze)的。

#### 规则说明

在大多数编程语言中，值类型的相等运算符 (= =) 没有默认实现。 如果你的编程语言支持运算符重载，则应考虑实现相等运算符。 它的行为应与的行为相同 [Equals](https://docs.microsoft.com/zh-cn/dotnet/api/system.object.equals) 。

不能在相等运算符的重载实现中使用默认的相等运算符。 这样做将导致堆栈溢出。 若要实现相等运算符，请在实现中使用对象 Equals 方法。 例如：

```c#
if (Object.ReferenceEquals(left, null))
    return Object.ReferenceEquals(right, null);
return left.Equals(right);
```



#### 如何解决冲突

若要修复与此规则的冲突，请实现相等运算符。



##### 反例❌

下面的示例演示违反此规则的类型。

```c#
public struct PointWithoutHash
{
    private int x, y;

    public PointWithoutHash(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    public override string ToString()
    {
        return String.Format("({0},{1})", x, y);
    }

    public int X { get { return x; } }

    public int Y { get { return x; } }

    // Violates rule: OverrideGetHashCodeOnOverridingEquals.
    // Violates rule: OverrideOperatorEqualsOnOverridingValueTypeEquals.
    public override bool Equals(object obj)
    {
        if (obj.GetType() != typeof(PointWithoutHash))
            return false;

        PointWithoutHash p = (PointWithoutHash)obj;
        return ((this.x == p.x) && (this.y == p.y));
    }
}
```



### CA2235:标记所有不可序列化的字段

#### 原因

在可以序列化的类型中声明了类型不可序列化的实例字段。

#### 规则说明

可序列化类型是用特性标记的类型 [System.SerializableAttribute](https://docs.microsoft.com/zh-cn/dotnet/api/system.serializableattribute) 。 序列化类型时， [System.Runtime.Serialization.SerializationException](https://docs.microsoft.com/zh-cn/dotnet/api/system.runtime.serialization.serializationexception) 如果该类型包含无法序列化 *且* 不实现接口的类型的实例字段，则会引发异常 [System.Runtime.Serialization.ISerializable](https://docs.microsoft.com/zh-cn/dotnet/api/system.runtime.serialization.iserializable) 。

#####  提示

对于实现的类型的实例字段，不会激发 CA2235， [ISerializable](https://docs.microsoft.com/zh-cn/dotnet/api/system.runtime.serialization.iserializable) 因为它们提供了自己的序列化逻辑。

#### 如何解决冲突

若要修复与此规则的冲突，请将属性应用于 [System.NonSerializedAttribute](https://docs.microsoft.com/zh-cn/dotnet/api/system.nonserializedattribute) 不可序列化的字段。



##### 反例❌

```c#
[Serializable]
public class InputDevices1
{
    // Violates MarkAllNonSerializableFields.
    Mouse opticalMouse;

    public InputDevices1()
    {
        opticalMouse = new Mouse(5, "optical");
    }
}
```

##### 正例✔️

```c#
[Serializable]
public class InputDevices2
{
    // Satisfies MarkAllNonSerializableFields.
    [NonSerialized]
    Mouse opticalMouse;

    public InputDevices2()
    {
        opticalMouse = new Mouse(5, "optical");
    }
}
```





### CA2237:用 SerializableAttribute 标记 ISerializable 类型

#### 原因

外部可见类型实现 [System.Runtime.Serialization.ISerializable](https://docs.microsoft.com/zh-cn/dotnet/api/system.runtime.serialization.iserializable) 接口，且类型没有用 [System.SerializableAttribute](https://docs.microsoft.com/zh-cn/dotnet/api/system.serializableattribute) 特性标记。 规则将忽略基类型无法序列化的派生类型。

#### 规则说明

要由公共语言运行时识别为可序列化， [SerializableAttribute](https://docs.microsoft.com/zh-cn/dotnet/api/system.serializableattribute) 即使类型通过实现接口使用自定义序列化例程，也必须用特性标记类型 [ISerializable](https://docs.microsoft.com/zh-cn/dotnet/api/system.runtime.serialization.iserializable) 。

#### 如何解决冲突

若要修复与此规则的冲突，请将属性应用于 [SerializableAttribute](https://docs.microsoft.com/zh-cn/dotnet/api/system.serializableattribute) 该类型。



##### 反例❌

```c#
namespace MyNamespace
{
    public class BaseType : ISerializable
    {
        public void GetObjectData(SerializationInfo info, StreamingContext context)
        {
        }
    }
}
```

##### 正例✔️

```c#
namespace MyNamespace
{
    [Serializable]
    public class BaseType : ISerializable
    {
        public void GetObjectData(SerializationInfo info, StreamingContext context)
        {
        }
    }
}
```



### CA2241:为格式化方法提供正确的参数

#### 原因

`format`传递给方法（如、或）的字符串参数 [WriteLine](https://docs.microsoft.com/zh-cn/dotnet/api/system.console.writeline) [Write](https://docs.microsoft.com/zh-cn/dotnet/api/system.console.write) [System.String.Format](https://docs.microsoft.com/zh-cn/dotnet/api/system.string.format) 不包含对应于每个对象参数的格式项，反之亦然。

#### 规则说明

方法（如、和）的参数 [WriteLine](https://docs.microsoft.com/zh-cn/dotnet/api/system.console.writeline) [Write](https://docs.microsoft.com/zh-cn/dotnet/api/system.console.write) [Format](https://docs.microsoft.com/zh-cn/dotnet/api/system.string.format) 包含格式字符串，后跟若干个 [System.Object](https://docs.microsoft.com/zh-cn/dotnet/api/system.object) 实例。 格式字符串包含格式为 {index [，对齐方式] [：格式表]} 的文本和嵌入格式项。 “index”是一个从零开始的整数，用于指示要格式化的对象。 如果对象在格式字符串中没有相应的索引，则忽略该对象。 如果 "index" 指定的对象不存在， [System.FormatException](https://docs.microsoft.com/zh-cn/dotnet/api/system.formatexception) 则会在运行时引发。

#### 如何解决冲突

若要修复与此规则的冲突，请为每个对象参数提供一个格式项，并为每个格式项提供一个对象参数。



##### 反例❌

```c#
class CallsStringFormat
{
    void CallFormat()
    {
        string file = "file name";
        int errors = 13;

        // Violates the rule.
        Console.WriteLine(string.Format("{0}", file, errors));
        // Violates the rule and generates a FormatException at runtime.
        Console.WriteLine(string.Format("{0}: {1}, {2}", file, errors));
    }
}
```

##### 正例✔️

```c#
class CallsStringFormat
{
    void CallFormat()
    {
        string file = "file name";
        int errors = 13;

        Console.WriteLine(string.Format("{0}", file));

        Console.WriteLine(string.Format("{0}: {1}", file, errors));
    }
}
```





### CA2242:正确测试 NaN

#### 原因

表达式根据或测试值 [System.Single.NaN](https://docs.microsoft.com/zh-cn/dotnet/api/system.single.nan) [System.Double.NaN](https://docs.microsoft.com/zh-cn/dotnet/api/system.double.nan) 。

#### 规则说明

[System.Double.NaN](https://docs.microsoft.com/zh-cn/dotnet/api/system.double.nan)如果未定义算术运算，则表示不是数字的结果。 测试值是否相等并始终返回的任何表达式 [System.Double.NaN](https://docs.microsoft.com/zh-cn/dotnet/api/system.double.nan) `false` 。 对值进行不相等测试并始终返回的任何表达式 [System.Double.NaN](https://docs.microsoft.com/zh-cn/dotnet/api/system.double.nan) `true` 。

#### 如何解决冲突

若要修复与此规则的冲突，并准确确定某个值是否表示 [System.Double.NaN](https://docs.microsoft.com/zh-cn/dotnet/api/system.double.nan) ，请使用 [System.Single.IsNaN](https://docs.microsoft.com/zh-cn/dotnet/api/system.single.isnan) 或 [System.Double.IsNaN](https://docs.microsoft.com/zh-cn/dotnet/api/system.double.isnan) 来测试值。



###  .editorconfig 文件示例：

```ini
dotnet_diagnostic.CA2200.severity = warning
dotnet_diagnostic.CA2207.severity = warning
dotnet_diagnostic.CA2213.severity = warning
dotnet_diagnostic.CA2214.severity = warning
dotnet_diagnostic.CA2216.severity = warning
dotnet_diagnostic.CA2229.severity = warning
dotnet_diagnostic.CA2231.severity = warning
dotnet_diagnostic.CA2235.severity = warning
dotnet_diagnostic.CA2237.severity = warning
dotnet_diagnostic.CA2241.severity = warning
dotnet_diagnostic.CA2242.severity = warning
```



## 其他规则

### 逻辑运算符

在逻辑判断运算时，要使用&&,||不要使用&,|

##### 反例❌

```c#
if (item > 0 & item < 100 | item == 200)
{

}
```

##### 正例✔️

```c#
if (item > 0 && item < 100 || item == 200)
{

}
```



### 字符串连接

若要连接字符串变量，可使用 `+` 或 `+=` 运算符、[字符串内插](https://docs.microsoft.com/zh-cn/dotnet/csharp/language-reference/tokens/interpolated)或 [String.Format](https://docs.microsoft.com/zh-cn/dotnet/api/system.string.format)、[String.Concat](https://docs.microsoft.com/zh-cn/dotnet/api/system.string.concat)、[String.Join](https://docs.microsoft.com/zh-cn/dotnet/api/system.string.join)、[StringBuilder.Append](https://docs.microsoft.com/zh-cn/dotnet/api/system.text.stringbuilder.append) 方法。

少量组件字符串生成字符串的场合使用以下方法

- `+` 和 `+=` 运算符串联字符串

  ```c#
  string userName = "<Type your name here>";
  string dateString = DateTime.Today.ToShortDateString();
  
  // Use the + and += operators for one-time concatenations.
  string str = "Hello " + userName + ". Today is " + dateString + ".";
  System.Console.WriteLine(str);
  
  str += " How are you today?";
  System.Console.WriteLine(str);
  ```

- 字符串内插进行字符串串联

  ```c#
  string userName = "<Type your name here>";
  string date = DateTime.Today.ToShortDateString();
  
  // Use string interpolation to concatenate strings.
  string str = $"Hello {userName}. Today is {date}.";
  System.Console.WriteLine(str);
  
  str = $"{str} How are you today?";
  System.Console.WriteLine(str);
  ```

- [String.Format](https://docs.microsoft.com/zh-cn/dotnet/api/system.string.format)字符串串联

  ```c#
  string format = "Hello {0}. Today is {1}.";
  string userName = "<Type your name here>";
  string date = DateTime.Today.ToShortDateString();
  
  // Use string.Format to concatenate strings.
  string str = string.Format(format, userName, date);
  ```

  

在其他情况下，可能需要将字符串合并在循环中，此时不知道要合并的源字符串的数量，而且源字符串的实际数量可能很大。 [StringBuilder](https://docs.microsoft.com/zh-cn/dotnet/api/system.text.stringbuilder) 类专门用于此类方案。 以下代码使用 [StringBuilder](https://docs.microsoft.com/zh-cn/dotnet/api/system.text.stringbuilder) 类的 [Append](https://docs.microsoft.com/zh-cn/dotnet/api/system.text.stringbuilder.append) 方法串联字符串。

-  [StringBuilder](https://docs.microsoft.com/zh-cn/dotnet/api/system.text.stringbuilder) 类的 [Append](https://docs.microsoft.com/zh-cn/dotnet/api/system.text.stringbuilder.append) 方法串联字符串

  ```c#
  // Use StringBuilder for concatenation in tight loops.
  var sb = new System.Text.StringBuilder();
  for (int i = 0; i < 20; i++)
  {
      sb.AppendLine(i.ToString());
  }
  System.Console.WriteLine(sb.ToString());
  ```



最后，可以使用 [LINQ](https://docs.microsoft.com/zh-cn/dotnet/csharp/programming-guide/concepts/linq/) 和 [Enumerable.Aggregate](https://docs.microsoft.com/zh-cn/dotnet/api/system.linq.enumerable.aggregate) 方法联接集合中的字符串。 此方法利用 lambda 表达式合并源字符串。 lambda 表达式用于将所有字符串添加到现有累积。 下面的示例通过在数组中的每两个单词之间添加一个空格来合并一组单词：

- 使用 [LINQ](https://docs.microsoft.com/zh-cn/dotnet/csharp/programming-guide/concepts/linq/) 和 [Enumerable.Aggregate](https://docs.microsoft.com/zh-cn/dotnet/api/system.linq.enumerable.aggregate) 方法联接集合中的字符串

  ```c#
  string[] words = { "The", "quick", "brown", "fox", "jumps", "over", "the", "lazy", "dog." };
  
  var phrase = words.Aggregate((partialPhrase, word) =>$"{partialPhrase} {word}");
  System.Console.WriteLine(phrase);
  ```

  



## 弃用的质量规则

以下 FxCop 旧分析规则已弃用，不能作为分析器实现。 有关详细信息，你可以在 [roslyn-分析器 GitHub 问题页](https://github.com/dotnet/roslyn-analyzers/issues?utf8=✓&q=is:issue+label:FxCop-Port)上按规则 ID (搜索，例如 **CA1009**) 。

- [CA1006](https://docs.microsoft.com/en-us/visualstudio/code-quality/ca1006?view=vs-2019)
- [CA1009](https://docs.microsoft.com/en-us/visualstudio/code-quality/ca1009?view=vs-2019)
- [CA1020](https://docs.microsoft.com/en-us/visualstudio/code-quality/ca1020?view=vs-2019)
- [CA1025](https://docs.microsoft.com/en-us/visualstudio/code-quality/ca1025?view=vs-2019)
- [CA1026](https://docs.microsoft.com/en-us/visualstudio/code-quality/ca1026?view=vs-2019)
- [CA1035](https://docs.microsoft.com/en-us/visualstudio/code-quality/ca1035?view=vs-2019)
- [CA1038](https://docs.microsoft.com/en-us/visualstudio/code-quality/ca1038?view=vs-2019)
- [CA1039](https://docs.microsoft.com/en-us/visualstudio/code-quality/ca1039?view=vs-2019)
- [CA1048](https://docs.microsoft.com/en-us/visualstudio/code-quality/ca1048?view=vs-2019)
- [CA1059](https://docs.microsoft.com/en-us/visualstudio/code-quality/ca1059?view=vs-2019)
- [CA1302](https://docs.microsoft.com/en-us/visualstudio/code-quality/ca1302?view=vs-2019)
- [CA1400](https://docs.microsoft.com/en-us/visualstudio/code-quality/ca1400?view=vs-2019)
- [CA1406](https://docs.microsoft.com/en-us/visualstudio/code-quality/ca1406?view=vs-2019)
- [CA1504](https://docs.microsoft.com/en-us/visualstudio/code-quality/ca1504?view=vs-2019)
- [CA1701](https://docs.microsoft.com/en-us/visualstudio/code-quality/ca1701?view=vs-2019)
- [CA1702](https://docs.microsoft.com/en-us/visualstudio/code-quality/ca1702?view=vs-2019)
- [CA1703](https://docs.microsoft.com/en-us/visualstudio/code-quality/ca1703?view=vs-2019)
- [CA1800](https://docs.microsoft.com/en-us/visualstudio/code-quality/ca1800?view=vs-2019)
- [CA1804](https://docs.microsoft.com/en-us/visualstudio/code-quality/ca1804?view=vs-2019)
- [CA1809](https://docs.microsoft.com/en-us/visualstudio/code-quality/ca1809?view=vs-2019)
- [CA1811](https://docs.microsoft.com/en-us/visualstudio/code-quality/ca1811?view=vs-2019)
- [CA1901](https://docs.microsoft.com/en-us/visualstudio/code-quality/ca1901?view=vs-2019)
- [CA1903](https://docs.microsoft.com/en-us/visualstudio/code-quality/ca1903?view=vs-2019)
- [CA2003](https://docs.microsoft.com/en-us/visualstudio/code-quality/ca2003?view=vs-2019)
- [CA2102](https://docs.microsoft.com/en-us/visualstudio/code-quality/ca2102?view=vs-2019)
- [CA2103](https://docs.microsoft.com/en-us/visualstudio/code-quality/ca2103?view=vs-2019)
- [CA2104](https://docs.microsoft.com/en-us/visualstudio/code-quality/ca2104?view=vs-2019)
- [CA2105](https://docs.microsoft.com/en-us/visualstudio/code-quality/ca2105?view=vs-2019)
- [CA2106](https://docs.microsoft.com/en-us/visualstudio/code-quality/ca2106?view=vs-2019)
- [CA2107](https://docs.microsoft.com/en-us/visualstudio/code-quality/ca2107?view=vs-2019)
- [CA2108](https://docs.microsoft.com/en-us/visualstudio/code-quality/ca2108?view=vs-2019)
- [CA2111](https://docs.microsoft.com/en-us/visualstudio/code-quality/ca2111?view=vs-2019)
- [CA2112](https://docs.microsoft.com/en-us/visualstudio/code-quality/ca2112?view=vs-2019)
- [CA2114](https://docs.microsoft.com/en-us/visualstudio/code-quality/ca2114?view=vs-2019)
- [CA2115](https://docs.microsoft.com/en-us/visualstudio/code-quality/ca2115?view=vs-2019)
- [CA2116](https://docs.microsoft.com/en-us/visualstudio/code-quality/ca2116?view=vs-2019)
- [CA2117](https://docs.microsoft.com/en-us/visualstudio/code-quality/ca2117?view=vs-2019)
- [CA2118](https://docs.microsoft.com/en-us/visualstudio/code-quality/ca2118?view=vs-2019)
- [CA2120](https://docs.microsoft.com/en-us/visualstudio/code-quality/ca2120?view=vs-2019)
- [CA2121](https://docs.microsoft.com/en-us/visualstudio/code-quality/ca2121?view=vs-2019)
- [CA2122](https://docs.microsoft.com/en-us/visualstudio/code-quality/ca2122?view=vs-2019)
- [CA2123](https://docs.microsoft.com/en-us/visualstudio/code-quality/ca2123?view=vs-2019)
- [CA2124](https://docs.microsoft.com/en-us/visualstudio/code-quality/ca2124?view=vs-2019)
- [CA2126](https://docs.microsoft.com/en-us/visualstudio/code-quality/ca2126?view=vs-2019)
- [CA2130](https://docs.microsoft.com/en-us/visualstudio/code-quality/ca2130?view=vs-2019)
- [CA2131](https://docs.microsoft.com/en-us/visualstudio/code-quality/ca2131?view=vs-2019)
- [CA2132](https://docs.microsoft.com/en-us/visualstudio/code-quality/ca2132?view=vs-2019)
- [CA2133](https://docs.microsoft.com/en-us/visualstudio/code-quality/ca2133?view=vs-2019)
- [CA2134](https://docs.microsoft.com/en-us/visualstudio/code-quality/ca2134?view=vs-2019)
- [CA2135](https://docs.microsoft.com/en-us/visualstudio/code-quality/ca2135?view=vs-2019)
- [CA2136](https://docs.microsoft.com/en-us/visualstudio/code-quality/ca2136?view=vs-2019)
- [CA2137](https://docs.microsoft.com/en-us/visualstudio/code-quality/ca2137?view=vs-2019)
- [CA2138](https://docs.microsoft.com/en-us/visualstudio/code-quality/ca2138?view=vs-2019)
- [CA2139](https://docs.microsoft.com/en-us/visualstudio/code-quality/ca2139?view=vs-2019)
- [CA2140](https://docs.microsoft.com/en-us/visualstudio/code-quality/ca2140?view=vs-2019)
- [CA2141](https://docs.microsoft.com/en-us/visualstudio/code-quality/ca2141?view=vs-2019)
- [CA2142](https://docs.microsoft.com/en-us/visualstudio/code-quality/ca2142?view=vs-2019)
- [CA2143](https://docs.microsoft.com/en-us/visualstudio/code-quality/ca2143?view=vs-2019)
- [CA2144](https://docs.microsoft.com/en-us/visualstudio/code-quality/ca2144?view=vs-2019)
- [CA2145](https://docs.microsoft.com/en-us/visualstudio/code-quality/ca2145?view=vs-2019)
- [CA2146](https://docs.microsoft.com/en-us/visualstudio/code-quality/ca2146?view=vs-2019)
- [CA2147](https://docs.microsoft.com/en-us/visualstudio/code-quality/ca2147?view=vs-2019)
- [CA2149](https://docs.microsoft.com/en-us/visualstudio/code-quality/ca2149?view=vs-2019)
- [CA2151](https://docs.microsoft.com/en-us/visualstudio/code-quality/ca2151?view=vs-2019)
- [CA2202](https://docs.microsoft.com/en-us/visualstudio/code-quality/ca2202?view=vs-2019)
- [CA2210](https://docs.microsoft.com/en-us/visualstudio/code-quality/ca2210?view=vs-2019)
- [CA2220](https://docs.microsoft.com/en-us/visualstudio/code-quality/ca2220?view=vs-2019)
- [CA2221](https://docs.microsoft.com/en-us/visualstudio/code-quality/ca2221?view=vs-2019)
- [CA2222](https://docs.microsoft.com/en-us/visualstudio/code-quality/ca2222?view=vs-2019) 
- [CA2223](https://docs.microsoft.com/en-us/visualstudio/code-quality/ca2223?view=vs-2019)
- [CA2228](https://docs.microsoft.com/en-us/visualstudio/code-quality/ca2228?view=vs-2019)
- [CA2230](https://docs.microsoft.com/en-us/visualstudio/code-quality/ca2230?view=vs-2019)
- [CA2233](https://docs.microsoft.com/en-us/visualstudio/code-quality/ca2233?view=vs-2019)
- [CA5122](https://docs.microsoft.com/en-us/visualstudio/code-quality/ca5122?view=vs-2019)





# 参考资料

[代码质量规则](https://docs.microsoft.com/zh-cn/dotnet/fundamentals/code-analysis/quality-rules/)

[代码样式规则](https://docs.microsoft.com/zh-cn/dotnet/fundamentals/code-analysis/style-rules/)

[代码分析规则的配置文件](https://docs.microsoft.com/zh-cn/dotnet/fundamentals/code-analysis/configuration-files)

[使用 EditorConfig 创建可移植的自定义编辑器设置](https://docs.microsoft.com/zh-cn/visualstudio/ide/create-portable-custom-editor-options?view=vs-2019)

[EditorConfig](https://editorconfig.org/)