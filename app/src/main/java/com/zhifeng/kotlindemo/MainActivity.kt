package com.zhifeng.kotlindemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import java.lang.Integer.parseInt

class MainActivity : AppCompatActivity() {
    val logKey = "lgh_kotlin"

    var a = 1
    // 模板中的简单名称：
    val s1 = "a is $a"
    val oneMillion = 1_000_000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var textview: TextView = findViewById(R.id.textview)
        a = 2
        // 模板中的任意表达式：
        val s2 = "${s1.replace("is", "was")}, but now is $a"
        textview.setText(s2)

        Main3()

    }

    /**
     * 基础语法  与 基本数据类型
     */
    fun Main1(){
        main(arrayOf("1", "2", "3"))//输出2
        Log.e("lgh_kotlin", "getStringLength  string  = " + getStringLength("123").toString()) //输出3
        Log.e("lgh_kotlin", "getStringLength  int  = " + getStringLength(1).toString())//输出0

        printString()

        Log.e(logKey, "oneMillion  = " + oneMillion) //输出 1000000

        main2()
        Log.e(logKey, "decimalDigitValue   =" + decimalDigitValue('2'))
        main3()
        main4(arrayOf("1", "2", "3"))
        main5()
    }

    /**
     *  条件控制  与  循环控制
     */
    fun Main2(){
        main6()
        mainWhen(arrayOf(4,6,10))
        mainFor()
        mainWhileOrDoWhile()
    }

    /**
     * 类和对象
     */
    fun Main3(){
        main7()
    }

    /********************************基础语法  与 基本数据类型*******************************************/
    fun main(args: Array<String>) {
        if (args.size < 2) {
            print("Two integers expected")
            return
        }
        val x = parseInt(args[0])
        val y = parseInt(args[1])
        // 直接使用 `x * y` 会导致错误, 因为它们可能为 null.
        if (x != null && y != null) {
            // 在进行过 null 值检查之后, x 和 y 的类型会被自动转换为非 null 变量
            Log.e("lgh_kotlin", "main  = " + (x * y).toString())
        }
    }

    fun getStringLength(obj: Any): Int? {
        if (obj is String) {
            // 做过类型判断以后，obj会被系统自动转换为String类型
            return obj.length
        }

        //在这里还有一种方法，与Java中instanceof不同，使用!is
        if (obj !is String) {
            return 0
        }

        // 这里的obj仍然是Any类型的引用
        return null
    }

    fun printString() {
        for (i in 1..4)
            Log.e(logKey, "printString  A  = " + i) // 输出“1234”

        for (i in 4..1)
            Log.e(logKey, "printString  B  = " + i) // 什么都不输出

        for (i in 4 downTo 1)
            Log.e(logKey, "printString  G  = " + i) // 输出“4321”

        var i = 5
        if (i in 1..10) { // 等同于 1 <= i && i <= 10
            Log.e(logKey, "printString  C  = " + i)
        }

// 使用 step 指定步长
        for (i in 1..4 step 2) Log.e(logKey, "printString  D  = " + i) // 输出“13”

        for (i in 4 downTo 1 step 2) Log.e(logKey, "printString  E  = " + i) // 输出“42”


// 使用 until 函数排除结束元素
        for (i in 1 until 10) {   // i in [1, 10) 排除了 10
            Log.e(logKey, "printString  F  = " + i)
        }
    }

    /**
     * 比较
     */
    fun main2() {
        val a: Int = 10000
        Log.e(logKey, "main2  a === a  = " + (a === a))// true，值相等，对象地址相等

        //经过了装箱，创建了两个不同的对象
        val boxedA: Int? = a
        val anotherBoxedA: Int? = a

        //虽然经过了装箱，但是值是相等的，都是10000
        Log.e(
            logKey,
            "main2  boxedA === anotherBoxedA  = " + (boxedA === anotherBoxedA)
        )//  false，值相等，对象地址不一样
        Log.e(logKey, "main2  boxedA == anotherBoxedA  = " + (boxedA == anotherBoxedA))// true，值相等

    }

    /**
     * 把字符转换为 Int 数字
     */
    fun decimalDigitValue(c: Char): Int {
        if (c !in '0'..'9') {
            Log.e(logKey, "decimalDigitValue   Out of range")
            throw IllegalArgumentException("Out of range")
        }
        return c.toInt() - '0'.toInt() // 显式转换为数字
    }

    /**
     * 数组
     */
    fun main3() {
        //[1,2,3]
        val a = arrayOf(1, 2, 3)
        //[0,2,4]
        val b = Array(3, { i -> (i * 2) })

        //读取数组内容
        Log.e(logKey, "main3   a[0]  = " + a[0]) // 输出结果：1
        Log.e(logKey, "main3   b[1]  = " + b[1]) // 输出结果：2
    }

    /**
     * 字符串
     */
    fun main4(str: Array<String>) {
        val text = """
    多行字符串
    多行字符串
    """
        Log.e(logKey, "main4  text  = " + text)   // 输出有一些前置空格
        val text2 = """
    |多行字符串
    |菜鸟教程
    |多行字符串
    |Runoob
    """.trimMargin()

        Log.e(logKey, "main4  text2  = " + text2)  // 前置空格删除了

        val text3 = """
    >多行字符串
    >菜鸟教程
    >多行字符串
    >Runoob
    """.trimMargin(">")

        Log.e(logKey, "main4  text3  = " + text3)  // 前置空格删除了


        for (c in str) {
            Log.e(logKey, "main4 c = " + c)
        }
    }

    /**
     * 字符串模板
     */
    fun main5(){
        val i = 10
        val s = "i = $i" // 求值结果为 "i = 10"
        Log.e(logKey, "main5 s = " + s)

        val s2 = "runoob"
        val str = "$s2.length is ${s2.length}" // 求值结果为 "runoob.length is 6"
        Log.e(logKey, "main5 str = " + str)

        val price = """
    ${'$'}9.99
    """
        Log.e(logKey, "main5 price = " + price) // 求值结果为 $9.99
    }

    /************************************* end ********************************************/

    /********************************** 条件控制  与  循环控制******************************/

    /**
     * if 条件判断
     */
    fun main6(){
        val a = 1
        val b = 2
        val max = if (a > b) a else b
        Log.e(logKey,"main6   max  = "+max)

        val x = 5
        val y = 9
        if (x in 1..8) {
            Log.e(logKey,"main6   x 在区间内")
        }

        if (y in 1..8) {
            Log.e(logKey,"main6   y 不在区间内")
        }
    }

    /**
     *  when 表达式
     */
    fun mainWhen(x:Array<Int>){
        when (x[0]) {
            1 -> Log.e(logKey," mainWhen  x == 1")
            2 -> Log.e(logKey," mainWhen  x == 2")
            3 -> Log.e(logKey," mainWhen  x == 3")
            4 -> Log.e(logKey," mainWhen  x == 4")
            else -> { // 注意这个块
                Log.e(logKey," mainWhen  x === "+x)
            }
        }

        when (x[1]) {
            0, 1 -> Log.e(logKey," mainWhen  x == 0 or x == 1")
            else -> Log.e(logKey,"mainWhen  otherwise")
        }

        val validNumbers = setOf(1,2,3)

        when (x[2]) {
            in 1..10 -> Log.e(logKey," mainWhen  x is in the range")
            in validNumbers -> Log.e(logKey," mainWhen  x is valid")
            !in 10..20 -> Log.e(logKey," mainWhen  x is outside the range")
            else -> Log.e(logKey," mainWhen  none of the above")
        }
    }

    /**
     * for 循环
     */
    fun mainFor() {
        val items = listOf("apple", "banana", "kiwi")
        for (item in items) {
            Log.e(logKey,"mainFor item  = "+item)
        }

        for (index in items.indices) {
            Log.e(logKey,"mainFor item at $index is ${items[index]}")
        }

        for ((index, value) in items.withIndex()) {
            Log.e(logKey,"mainFor  the element at $index is $value")
        }
    }

    /**
     * while 循环 与 do...while 循环
     */
    fun mainWhileOrDoWhile() {
        Log.e(logKey,"mainWhileOrDoWhile   ----while 使用-----")
        var x = 5
        while (x > 0) {
            Log.e(logKey,"mainWhileOrDoWhile  x ="+ x--)
        }
        Log.e(logKey,"mainWhileOrDoWhile  ----do...while 使用-----")
        var y = 5
        do {
            Log.e(logKey,"mainWhileOrDoWhile  y ="+y--)
        } while(y>0)

    }
    /************************************* end ********************************************/

    /**********************************  类和对象  ****************************************/

    fun main7(){
        var mainClass = MainClass("aa","a")
        Log.e(logKey,"main7   mainClass.toString() = "+mainClass.toString())

        var mainClass2 = MainClass()
        mainClass2.name = null
        mainClass2.sex  = "b"
        Log.e(logKey,"main7   mainClass2.toString() = "+mainClass2.toString())
        Log.e(logKey,"main7   mainClass2.name = "+mainClass2.name)
    }
}
