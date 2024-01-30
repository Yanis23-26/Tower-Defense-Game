#!/bin/bash

javac */*.java */*/*.java
java Main.Game
rm */*.class
rm */*/*.class