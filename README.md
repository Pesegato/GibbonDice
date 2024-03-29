<div align="center"><img alt="Static Badge" src="https://img.shields.io/badge/Gibbon-Dice-yellow?style=for-the-badge&logo=github"  height="100" align="center">

 # RNG utilities for jMonkeyEngine

[![Maven Central](https://img.shields.io/maven-central/v/io.github.pesegato/GibbonDice?style=for-the-badge)](https://central.sonatype.com/artifact/io.github.pesegato/GibbonDice) 
[![javadoc](https://javadoc.io/badge2/io.github.pesegato/GibbonDice/javadoc.svg?style=for-the-badge)](https://javadoc.io/doc/io.github.pesegato/GibbonDice) 

</div>

## Instructions

Step 1: Compile/include this library

Step 2: Create file named `GibbonDice.json` on the `GoldMonkey` folder, like this:

```
[
 {
    "id": "mammal.monkey",
    "weight": 7
  },
 {
    "id": "mammal.cat",
    "weight": 2
  },
 {
    "id": "mammal.dog",
    "weight": 1
  }

]
```

Step 3: Attach the GibbonDice appstate to your application

Step 4: Invoke GibbonDice.get("mammal") and you will receive either monkey, cat, or dog with 7/10, 2/10 and 1/10 probability respectively.

It also supports hierarchical outcomes, for example:

animal.mammal 2

animal.reptilian 1

animal.mammal.monkey 5

animal.mammal.cat 5

animal.reptilian.lizard 2

animal.reptilian.snake 3

This time, the outcome will either be 2/3 mammal or 1/3 reptilian:

If mammal, you will get 5/10 monkey or 5/10 cat.

If reptilian, you will get 2/5 lizard or 3/5 snake.
