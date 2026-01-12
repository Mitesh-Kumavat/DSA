# Bit Manipulation

---

## 🔢 Binary Basics (Very Important)

- Computers store everything in **binary (base 2)**
- Digits used: `0` and `1`

| Decimal | Binary |
| ------- | ------ |
| 0       | 0000   |
| 1       | 0001   |
| 2       | 0010   |
| 3       | 0011   |
| 4       | 0100   |
| 5       | 0101   |
| 6       | 0110   |
| 7       | 0111   |
| 8       | 1000   |

👉 **Each position represents a power of 2**

Example:

```
5 = 0101 = (1×2⁰ + 0×2¹ + 1×2²)
```

---

# ⚙️ Bitwise Operators (CORE FOUNDATION)

Bitwise operators work **bit-by-bit**, not on whole numbers.

---

## 1️⃣ AND (`&`)

### Definition

```
1 & 1 = 1
otherwise = 0
```

### Truth Table

| A   | B   | A & B |
| --- | --- | ----- |
| 0   | 0   | 0     |
| 0   | 1   | 0     |
| 1   | 0   | 0     |
| 1   | 1   | 1     |

### Example

```
5 & 3
5 = 0 1 0 1
3 = 0 0 1 1
------------
    0 0 0 1   =>  1
```

### Use Cases

- Check if a bit is set
- Check odd/even number

```java
if ((n & 1) == 1) → odd
```

### Tip 🧠

> AND **filters bits** (keeps only common 1s)

---

## 2️⃣ OR (`|`)

### Definition

```
0 | 0 = 0
otherwise = 1
```

### Truth Table

| A   | B   | A   | B   |
| --- | --- | --- | --- |
| 0   | 0   | 0   |
| 0   | 1   | 1   |
| 1   | 0   | 1   |
| 1   | 1   | 1   |

### Example

```
5 | 3
5 = 0 1 0 1
3 = 0 0 1 1
-----------
    0 1 1 1   =>  7
```

### Use Cases

- Set a bit

```java
n | (1 << i)   // sets ith bit
```

### Tip 🧠

> OR **forces bits to 1**

---

## 3️⃣ XOR (`^`) ⭐ MOST IMPORTANT

### Definition

```
Same bits → 0
Different bits → 1
```

### Truth Table

| A   | B   | A ^ B |
| --- | --- | ----- |
| 0   | 0   | 0     |
| 0   | 1   | 1     |
| 1   | 0   | 1     |
| 1   | 1   | 0     |

### Example

```
5 ^ 3
5 = 0 1 0 1
3 = 0 0 1 1
-----------
    0 1 1 0  =>  6
```

### 🔥 Super Properties

```text
x ^ x = 0
x ^ 0 = x
x ^ y ^ x = y
```

### Use Cases

- Find unique element
- Swap without temp

```java
a = a ^ b;
b = a ^ b;
a = a ^ b;
```

### Tip 🧠

> XOR **cancels duplicates**

---

## 4️⃣ NOT (`~`)

### Definition

```
~0 = 1
~1 = 0
```

### Example

```
~5
5 = 00000101
~ = 11111010
```

> Computer first converst the number to its binary form (using fixed bits, e.g., 8 bits here), then flips all bits. and if the number is negative, then it is represented in Two's Complement form.
> So, `11111010` in Two's Complement is `-6`.

```java
~5 = -6
```

### Tip 🧠

> NOT flips **all bits** (including sign bit)

---

## 5️⃣ Left Shift (`<<`)

### Meaning

```
n << k = n × (2^k)
```

### Example

```
5 << 1
0101 → 1010 = 10
```

### Explaination

- Shifts bits to left
- Fills right with `0`

### Use Cases

- Multiply by power of 2

```java
n << 3 = n * 8
```

---

## 6️⃣ Right Shift (`>>`)

### Meaning

```
n >> k = n / (2^k)
```

### Example

```
10 >> 1
1010 → 0101 = 5
```

### Explaination

- Shifts bits to right
- Fills left with sign bit (0 for +ve, 1 for -ve)

### Important

- Preserves **sign bit**

---

## 7️⃣ Unsigned Right Shift (`>>>`) (Java only)

- Fills left with `0`
- Used for unsigned numbers

```java
-5 >>> 1
```

---

# 🧩 Common Bit Tricks (MUST KNOW)

## Check ith Bit

```java
(n & (1 << i)) != 0
```

## Set ith Bit

```java
n | (1 << i)
```

## Clear ith Bit

```java
n & ~(1 << i)
```

## Toggle ith Bit

```java
n ^ (1 << i)
```

---

## Power of Two Check

```java
(n & (n - 1)) == 0
```

Why?

- Power of 2 has **only one 1 bit**

---

## Count Set Bits

```java
while (n > 0) {
    n = n & (n - 1);
    count++;
}
```

Each step removes one set bit.

---

## Isolate Lowest Set Bit

```java
n & (-n)
```

---

# 🧠 How to Think in Bit Problems

Ask:

- Is parity involved? → XOR
- Is power of 2 involved? → AND
- Is bit position important? → shifts

---

# 🏁 Final Advice

- Bit manipulation is about **patterns**, not formulas
- Practice converting numbers to binary
- Dry run on paper

---
