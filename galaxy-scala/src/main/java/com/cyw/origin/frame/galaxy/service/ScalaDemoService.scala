package com.cyw.origin.frame.galaxy.service

object ScalaDemoService {

  def hello(name: String): String = {
    s"Hello, ${name}"
  }

  def add(a: Int, b: Int): Int = {
    a + b
  }

  def judge(f: Int): Any = {
    if (f > 0) true
  }

  def loop(l: Int, ls: List[Int]): List[Int] = {
    for {
      s <- ls
      if (l > s)
    } yield (s)
  }

  def exception(): Unit = {
    Integer.parseInt("dog")
    try {
    } catch {
      case _ => 0
    } finally {
      println("always be printed")
    }
  }

  def switch(a: Int): String = {
    val x = 1
    val y = x match {
      case 1 => "One"
      case 2 => "Two"
      case _ => "Others"
    }
    println(y)
    a match {
      case 1 => "one"
      case 2 => "two"
      case _ => "others"
    }
  }

  def test(): Unit ={
    val add = 111

  }

}