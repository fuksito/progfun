package recfun
import common._

object Main {
  def main(args: Array[String]) {
    println("Pascal's Triangle")
    for (row <- 0 to 10) {
      for (col <- 0 to row)
        print(pascal(col, row) + " ")
      println()
    }
  }

  /**
   * Exercise 1
   */
  def pascal(c: Int, r: Int): Int = {
    if (c == 0 || c == r) 1 else pascal(c - 1, r - 1) + pascal(c, r - 1)
  }

  /**
   * Exercise 2
   */
  def balance(chars: List[Char]): Boolean = {
    def openedCount(c: Char) = if (c == '(') 1 else if (c == ')') -1 else 0
    def inBalance(c: List[Char], opened: Int): Boolean = {
      if (c.isEmpty || opened < 0) opened == 0 else inBalance(c.tail, opened + openedCount(c.head))
    }
    inBalance(chars, 0)
  }

  /**
   * Exercise 3
   */
  def countChange(money: Int, coins: List[Int]): Int = {
    if (money == 0) 1 else if (money < 0 || coins.isEmpty) 0 
    else countChange(money - coins.head, coins) + countChange(money, coins.tail)
  }
}
