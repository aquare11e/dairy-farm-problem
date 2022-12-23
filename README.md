# Dairy farmer problem

## Description

A dairy farmer wishes to manage the breeding of the cows in his farm. For this reason, he defines the 
Cow entity (cowId, nickName).

Cows can either give birth to calves by insemination or end their life span. 

Your mission is to create a data structure to support the dairy farm and the following operations on it (assume you start with a single cow that is always alive, and that all calves are born female):

- GiveBirth (parentCowId, childCowId, childNickName) – adds a new female calf to the farm.
- EndLifeSpan (cowId) – removes the cow from the farm.
- Print farm data – outputs entire farm to the standard output in a readable manner.

---

## Additional task
Implement same, but you CAN NOT use Java built in arrays/collections/lists/maps
(or any data structure which inherits or uses them).


So, for instance using the brackets “[“or “]” anywhere is forbidden.

You must implement your own data structure to support the same operations.