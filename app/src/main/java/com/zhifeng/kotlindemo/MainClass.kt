package com.zhifeng.kotlindemo

class MainClass {

    constructor(name: String, sex: String) {
        this.name = name
        this.sex = sex
    }

    constructor()

    var name: String? = ""
        get() = field
        set(value) {// 判空
           field = value?.toString() ?: ""
        }

    var sex : String = ""


    override fun toString(): String {
        return "MainClass(name='$name', sex='$sex')"
    }
}