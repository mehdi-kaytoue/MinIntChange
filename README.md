MinIntChange
============

An algorithm to mine (frequent) closed numerical patterns and their generators (convex hulls)
Revisiting numerical pattern mining:  Extracting Closed Patterns and their Generators 
M. Kaytoue, S. O. Kuznetsov, A. Napoli, Published at IJCAI 2O11        

This eclipse project contains the java source files of 
algorithms designed and/or used in the submitted paper.

The original part lies in classes MinIntChangeC, MinInt-
ChangeGHash, and MinIntChangeGTrie (package algorithms).

To run experiments, the best way to proceed is as follow
1/ Download Eclipse (eclipse.org)
2/ Import the project Archive
3/ Edit the class Main.java 
	3.1/ Change the data filename:
	 - Datasets used in the paper can be found in ./data
	 - Comment/un-comment the desired dataset 
	3.2/ Change minimal support
	 - absolute: to use an absolute minimal support, 
	   set minSuppRelative = -1 and modify minSup
	 - relative: to use a relative minimal support, 
	   set minSuppRelative between 0 and 1
	   (ratio of minSup/#objects) 
	3.3/ Set printResult = true for printing the results,
	                     = false to see only execution 
	                       times and #patterns
	3.4/ Comment/un comment the algorithms to experiment
4/ Run the main from the class Main
   
   
Note: Allocate memory for the java virtual machine (JVM)
   => Menu "Run"
   => sub-menu "Run Configurations..."
   => Configuration "Main"
   => Tab "Arguments"
   => Field "VM arguments" 
   => add, e.g. -Xmx2560M to allocate 2.5GB of memory.
