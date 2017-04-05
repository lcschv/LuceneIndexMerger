Index Merger
========

## Purpose

Index Merger is designed to support merging multiple indexes into one single index. We mainly used it to merge ClueWeb12 index.


Build using Maven:

```
mvn package appassembler:assemble
```
To execute the merger you may use the follow command:
```
sh target/appassembler/bin/Main PATHOFINDEXES PATHTOMERGEINDEX

```
