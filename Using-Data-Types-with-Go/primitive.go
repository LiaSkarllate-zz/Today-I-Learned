package main

//Imports:
import (
    "fmt"
    "unsafe"
)

func main() {
    //Variables:
        //Bool type:
    a := true
    var b bool = false

        //Signed integers types:
    var number1 int = 89    //The int type is 32 or 64 bits depending on the underlying platform.
    var number2 int64 = 88
    var number3 int32 = 87
    var number4 int16 = 86
    var number5 int8 = 85
    var number6 rune = 87    //This is an alias of int32

        //Unsigned integers types:
    var number11 uint = 89      //The uint type is 32 or 64 bits depending on the underlying platform.
    var number12 uint64 = 88
    var number13 uint32 = 87
    var number14 uint16 = 86
    var number15 uint8 = 85
    var number16 uintptr        //This type is dedicated for storing memory address pointers.
    var number17 byte = 85      //This is an alias of uint8.

        //Floating point types:
    var number21 float32 = 5.99
    var number22 float64 = 7.89e+1

        //Complex types:
    number31 := complex(6, 7)
    var number32 complex64 = 6 + 7i
    var number33 complex128 = 6 + 7i

        //String types:
            //A string is a collection of bytes.
    var string1 string = "Hello!"
    var string2 string = "Today is 32\u00B0C outside!"
    string3 := `Today is 32\u00B0C outside!`             //This is a multi-line unencoded string.

        //Array types:
            //The size of an array is not dynamic.
            //Each size of an array is a data type.
    var array1 [2]int
    var array2 [3]string
    var array3 [2]string = [2]string{"one", "two"}
    var array4 = [2]string{"this", "that"}
    array5 := [3]string{"1", "2", "3"}

        //Slice types:
            //Slices represents all sets of an array data type.
    array6 := []string{"william", "fernandes", "dorante"}
    array7 := make([]int, 2)
        array7[0] = 5
        array7[1] = 10

        //Map types:
    map1 :=  map[string][]int {
        "men": []int{1, 2, 3, 4, 5},
        "women": []int{6, 7, 8, 9, 10},
    }
    map2 := make(map[string][]int)
        map2["men"] = []int{1, 2, 3, 4, 5}
        map2["women"] = []int{6, 7, 8, 9, 10} 

        //Struct types:
    type node struct {  //This is a named struct.
        next *node;
        weight int
    }

    var struct1 node = node {
        next: nil,
        weight: 0,
    }

    struct2 := node{nil, 0}
    struct3 := struct{year, model string; code int} {   //This is an unnamed struct.
        year: "2020",
        model: "BMW 320i",
        code: 50,
    }

    //Logic:
    c := a && b
    d := a || b

    //number12 = number13 //This is a type mismatch.

    var numberA uint32 = 0xFEFEFE   //The "0x" is the hex prefix.
    var numberB = 0466              //The "0" is the octal prefix.
    var numberC = 125               //There is no decimal prefix.

    fmt.Println("a =", a)
    fmt.Println("b =", b)
    fmt.Println("c =", c)
    fmt.Println("d =", d)

    fmt.Printf("type of number1 =%T\n", number1)
    fmt.Printf("size of number1 =%d\n", unsafe.Sizeof(number1))

    fmt.Println("numberA = ", numberA)
    fmt.Println("numberB = ", numberB)
    fmt.Println("numberC = ", numberC)
    fmt.Println("number2 =", number2)
    fmt.Println("number3 =", number3)
    fmt.Println("number4 =", number4)
    fmt.Println("number5 =", number5)
    fmt.Println("number6 =", number6)
    fmt.Println("number11 =", number11)
    fmt.Println("number12 =", number12)
    fmt.Println("number13 =", number13)
    fmt.Println("number14 =", number14)
    fmt.Println("number15 =", number15)
    fmt.Println("number16 =", number16)
    fmt.Println("number17 =", number17)
    fmt.Println("number21 =", number21)
    fmt.Println("number22 =", number22)
    fmt.Println("number31 =", number31)
    fmt.Println("number32 =", number32)
    fmt.Println("number33 =", number33)

    fmt.Println("string1 =", string1)
    fmt.Println("string2 =", string2)
    fmt.Println("string3 =", string3)

    fmt.Println(array1)
    fmt.Println(array2)
    fmt.Println(array3)
    fmt.Println(array4)
    fmt.Println(array5)
    fmt.Println(array6)
    fmt.Println(array7)

    fmt.Println(map1)
    fmt.Println(map2)

    fmt.Println(struct1)
    fmt.Println(struct2)
    fmt.Println(struct3)
}
