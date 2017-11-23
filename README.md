# GibbonDice
RNG utilities for jMonkeyEngine

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
