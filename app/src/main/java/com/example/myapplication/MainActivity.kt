package com.example.myapplication
import androidx.appcompat.app.AppCompatActivity
import java.util.*

class MainActivity : AppCompatActivity() {

    fun hanoi_recurr(n: Int, initial: Char = 'A', middle: Char = 'B', target: Char = 'C') {
        
        //to this method I am giving parameters initial, middle, and target with default values to 
        // make it easier to do recursion
        
        if (n == 1) { //base step for only one disk
            println("*************************************")
            println("We are moving disk number $n from $initial to $target")
            println("*************************************")
        } else {
            hanoi_recurr(n - 1, initial, target, middle) //recursive step to solve for smaller input
            println("*************************************")
            println("We are moving disk number $n from $initial to $target")
            println("*************************************")
            hanoi_recurr(n - 1, middle, initial, target) //recursive step to solve for smaller input
        }
    }

    fun hanoi_stack(n: Int) {
        val initial = Stack<Int>()
        var middle = Stack<Int>()
        var target = Stack<Int>()
        //Three stacks are created to represent the initial, middle, and target pegs
        // in the Towers of Hanoi problem.

       //A for loop to fill the initial stack with integers from n down to 1,
       //representing the disks of increasing sizes.
        for (i in n downTo 1) {
            initial.push(i)
        }

        var totalMoves = 0
        // The parity of n is checked, and if it is even, the middle and target stacks are
        // swapped. This is done to ensure that the largest disk is always moved to the middle
        // peg first.
        if (n % 2 == 0) {
            val temp = middle
            middle = target
            target = temp
        }

        while (target.size < n) {
            //A while loop is used to continue making moves until all the disks have been
            // moved from the initial peg to the target peg.
            if (initial.isEmpty() || (!target.isEmpty() && target.peek() < initial.peek())) {
                initial.push(target.pop())
                totalMoves++
                println("************************************************************************")
                println("We are moving disk number ${initial.peek()} from ${if (target.isEmpty()) "middle" else "target"} to initial")
            } else {
                target.push(initial.pop())
                totalMoves++
                println("************************************************************************")
                println("We are moving disk number ${target.peek()} from initial to target")
            }
            if (middle.isEmpty() || (!target.isEmpty() && target.peek() < middle.peek())) {
                middle.push(target.pop())
                totalMoves++
                println("************************************************************************")
                println("We are moving disk number ${middle.peek()} from target to middle")
            } else {
                target.push(middle.pop())
                totalMoves++
                println("************************************************************************")
                println("We are moving disk number ${target.peek()} from middle to target")
            }
        }
        println("__________________________________________________")
        println("Total moves to solve this puzzle are : $totalMoves")
        println("__________________________________________________")
    }


    fun main() {
        val n = 3
        hanoi_recurr(n, 'A', 'B', 'C')
        hanoi_stack(n)
    }

}