Within you will find /src, /bin, /resources, /makefile and an ExperimentBash Script

Instructions for makefile:
The command "make Main" will run both parallel and sequential algorithms and print the results \
in two separate file under resources with the filterSize and arraySize appended to the name.
You can choose the name of the datafile, the filtersize and the arraysize when calling the main command (in that order)
filtersize = FSIZE
arraysize = ARRSIZE
datafile = DATA
However, these are optional parameters and the program will run without them

"Make clean"
Will clean out the /bin and /Resources folders

"Make Experiment"
This was used by a bash script which was used to run the experiment. The experiment used its own
dedicated class called "MainExperiment.java". It made use of a QueryGenerator class (under ./src)
This command allows for specifying the filtersize and arraysize (similarly to make main) as well as the sequential cutoff.

"Main Everything"
Cleaned, compiled and ran the experiment

"Main 2D"
Lastly, a 2D filter was implemented - sequential and parallel methods. Their speeds were not tested
(since this was outside of this assignment's scope). However, correctness was validated in the same
way as explained in the report

Thanks

NDLZAC001
