package patmat

import org.scalatest.FunSuite

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

import patmat.Huffman._

@RunWith(classOf[JUnitRunner])
class HuffmanSuite extends FunSuite {
  trait TestTrees {
    val t1 = Fork(Leaf('a',2), Leaf('b',3), List('a','b'), 5)
    val t2 = Fork(Fork(Leaf('a',2), Leaf('b',3), List('a','b'), 5), Leaf('d',4), List('a','b','d'), 9)
  }

  test("weight of a larger tree") {
    new TestTrees {
      assert(weight(t1) === 5)
    }
  }

  test("chars of a larger tree") {
    new TestTrees {
      assert(chars(t2) === List('a','b','d'))
    }
  }

  test("string2chars(\"hello, world\")") {
    assert(string2Chars("hello, world") === List('h', 'e', 'l', 'l', 'o', ',', ' ', 'w', 'o', 'r', 'l', 'd'))
  }
  
  test("findPair finds pair by char"){
    val pair = findPair('b', List(('a', 3), ('b', 2), ('c', 1)))
    assert(pair === ('b', 2))
  }
  
  test("times 'hello, world' would count every letter") {
    val result = times(string2Chars("hello, world"))
    assert(findPair('h', result)._2 === 1, "h is 1")
    assert(findPair('e', result)._2 === 1, "e is 1")
    assert(findPair('l', result)._2 === 3, "l is 3")
    assert(findPair('o', result)._2 === 2, "o is 2")
    assert(findPair(',', result)._2 === 1, ", is 1")
    assert(findPair(' ', result)._2 === 1, "' ' is 1")
    assert(findPair('w', result)._2 === 1, "'w' is 1")
    assert(findPair('r', result)._2 === 1, "'r' is 1")
    assert(findPair('d', result)._2 === 1, "'d' is 1")
  }

  test("makeOrderedLeafList for some frequency table") {
    assert(makeOrderedLeafList(List(('t', 2), ('e', 1), ('x', 3))) === List(Leaf('e',1), Leaf('t',2), Leaf('x',3)))
  }

  test("combine of some leaf list") {
    val leaflist = List(Leaf('e', 1), Leaf('t', 2), Leaf('x', 4))
    assert(combine(leaflist) === List(Fork(Leaf('e',1),Leaf('t',2),List('e', 't'),3), Leaf('x',4)))
  }

  test("decode and encode a very short text should be identity") {
    new TestTrees {
      assert(decode(t1, encode(t1)("ab".toList)) === "ab".toList)
    }
  }
}
