Lucene Index Merger
========

## Purpose

Index Merger is designed to support merging multiple indexes into one single index. We mainly used it to merge ClueWeb12 index.

## How to use
Build using Maven:

```
mvn package appassembler:assemble
```
To execute the merger you may use the follow command:
```
sh target/appassembler/bin/Main Path/dir/Indexes Path/to/store/mergedIndex

```
The `PathOfDirContainingIndexes` must contain the all directories of each index that you're willing to merge.  `PathOfDirContainingIndexes` is the directory that your merged index will be.  
