MinIntChange
============

An algorithm to mine (frequent) closed numerical patterns and their generators (convex hulls)
> Revisiting numerical pattern mining:  Extracting Closed Patterns and their Generators 
  M. Kaytoue, S. O. Kuznetsov, A. Napoli, Published at IJCAI 2O11        

This eclipse project contains the java source files of algorithms designed and/or used in the submitted paper.

The original part lies in classes MinIntChangeC, MinInt-ChangeGHash, and MinIntChangeGTrie (package algorithms).

To run experiments, the best way to proceed is as follow

1. Download Eclipse (eclipse.org)

2. Import the project Archive

3. Edit the class Main.java for setting up your argments (not in command line :))

4. Run the main from the class Main
   
   
Note: Allocate memory for the java virtual machine (JVM)
* Menu "Run"
* sub-menu "Run Configurations..."
* Configuration "Main"
* Tab "Arguments"
* Field "VM arguments" 
* add, e.g. -Xmx2560M to allocate 2.5GB of memory.
