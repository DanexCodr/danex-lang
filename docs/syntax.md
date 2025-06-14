# DanexLang Method Declaration Syntax

In this language, methods are always declared with parentheses after the name and can be defined either in a block form with braces or in a concise expression form with `=>`. Statements within blocks end with semicolons. Implicit return is triggered by assigning to either the method’s name or a declared result variable.

---

## Declaration Forms (with optional visibility/modifiers)

### Block Form

```danex
methodName(params) {
    // statements ending with ;
    …
}

```
### Expression (Arrow) Form


```danex
methodName(params) => expression;

```

Single-expression methods only, must return a value.

Equivalent to:


```danex
methodName(params) {
    methodName = expression; // or result = expression if declared
}


```

### Result Variable Declaration (Optional)

For explicit typing or documentation:


```danex
(result) foo(params) { ... }
(Int result) bar(params) => expression;

```
### Visibility / Modifiers

If used (e.g., `public`, `private`, `static`, etc.), they must come after any (Type result) clause and before the method name.


```danex
✅ (String result) public sample(String str) { … }

❌ public (String result) sample(String str) { … } #-- modifier before return-declaration --#

```


---

## Implicit Return Semantics

Assigning to the method name or to the declared result variable immediately returns that value. Any statements after such an assignment become unreachable.

Immediate-Return-On-Assignment Warning (“the gotcha”)

Whenever you write:

`methodName = value;`

or

`result = value;`

the method ends immediately and returns that value.

### Invalid Example

```danex
(Int result) factorial(n) {
    result = 1;                        // ❌ immediately returns 1; code below is unreachable
    for (var i = 2; i <= n; i = i + 1;) {
        result = result * i;          // unreachable
    }
}

```


### Valid Initialization + Return Pattern


```danex
(Int result) factorial(n) {
    var acc = 1;                      // normal local variable
    for (var i = 2; i <= n; i = i + 1;) {
        acc = acc * i;
    }
    result = acc;                     // single exit here
}

```

---

### New Rule: Exhaustive Return Paths

If any assignment-to-result (implicit return) is present or the method declares a result, then every possible execution path must assign to the method name or result before the method exits. Failure to assign on all paths is a compile-time error.


---

### Arrow Form Equivalence

`methodName(params) => someExpression;`

is exactly equivalent to:


```danex
methodName(params) {
    methodName = someExpression;     // immediate return
}

```
(or `result = someExpression;` if a result variable is declared).

Return type is inferred from the expression unless `(Type result)` is declared.


---

### Void Methods & Early Exit

If no assignment to the method name or result variable ever occurs in a block, the method is treated as void and runs to completion (unless you exit early via control flow). Introduce a dedicated exit; statement for void methods to allow early exit without returning a value. This behaves like return; in other languages but is only valid in void methods (i.e., methods without a declared result and without any `methodName = ...;` assignments).

Syntax

`exit;`

### Examples

```danex
logInfo(message) {
    if (message == null) {
        exit; // early exit in void method
    }
    print("LOG: " + message);
}

checkUser(user) {
    if (user == null) {
        print("No user."); exit;
    }
    if (!user.active) {
        print("Inactive user."); exit;
    }
    print("User is active.");
}

```
### Invalid Use

```danex
(Int result) test(n) {
    if (n < 0) {
        exit; // ❌ Error: cannot use exit; in result-returning methods
    }
    result = n;
}

```
Arrow form is not valid for void methods, since it must produce a return value.



---

## Type Inference and Explicit Typing

If you don’t declare `(Type result)`, the return type is inferred from the first assignment or from the single expression in an arrow method.

If you declare `(Type result)`, all return assignments must match that type exactly (mixed types cause a compile error). This serves as documentation or to enforce precise types when inference might be ambiguous.



---

## Naming Rules

Method names cannot be reused as local variable names within the same method, because the method name itself acts as a self-typed variable for implicit return.

```danex
sum(a, b) {
    sum = a + b;
    var sum = 10;  // ❌ error: ‘sum’ is the result variable
}

```

---

## Single-Exit Semantics

The first assignment to the method name or result ends the method immediately. All subsequent code in that block is skipped. Remember: “Assign to result and poof—you’re out!” If you need initialization or intermediate work before returning, use locals first and assign to result only once when you’re done.


---

## Control Flow

You may still use loops, conditionals, `break;`, `continue;`, `throw;`, `exit;`, etc., inside block methods. But any assignment to the result short-circuits the remainder of the block. And because of the “all paths must assign” rule, ensure every branch either assigns or there is a fallback assignment afterward.

### Example with Loop and Fallback


```danex
(String result) findName(list) {
    for (name in list) {
        if (matches(name)) {
            result = name;             // immediate return if found
        }
    }
    result = "Unknown";               // fallback if never returned in loop
}

```

---

## Integration with Other Features

When integrating with generics, modules, classes, etc., the same rules apply: always use `methodName(params) { ... }` or `methodName(params) => expr;`, and assignments to the method name or declared result trigger an immediate return—and must appear on all paths if used.

### Example in a Class-Like Context

```danex
class Calculator {
    multiply(a, b) {
        multiply = a * b;            // returns immediately
    }
    (Float result) public divide(a, b) {
        if (b == 0) {
            throw("Division by zero");
        }
        result = a / b;             // returns here
    }
}

```

---
## 📝 Notes

Assigning to result or the method name immediately returns from the method.

This can cause the rest of the method to become unreachable, even if you didn’t mean to.


If your code mysteriously stops running, double-check that you didn’t accidentally assign to the result too early.

Always use a temporary local variable for intermediate work, then assign to result once at the end (or in each branch).

Arrow methods are just sugar for a single `result = expression;` (or `methodName = expression;`).

Void methods can't use `exit;` unless:

There’s no result variable, and

No assignment to the method name happens


Modifiers (`public`, `private`, etc.) must appear after the result clause:


```danex
✅ (Int result) public doStuff(...)

❌ public (Int result) doStuff(...)

```


### Basically:
> 💡 Think of the result variable as a trapdoor. Once you step on it, you fall out of the method.
