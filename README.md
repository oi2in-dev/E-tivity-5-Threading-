# E-tivity-5-Threading

This is my Threading Assignment for ED5042

Thw first example i have used is by pasting any image url into the text field, Then it will fetch the image and place it at the placeholder.
(I have a link pre-inserted for testing purposes, but it will work with any link)

The Second Example is is an implementation of the "startProgress" method, which requires an argument of a View object. 
When this function is used, it begins a new thread with a Task object and resets the progress of a progress bar to 0.
A separate class called Task implements the Runnable interface, which implies it has a run() function that will be called when the new thread is launched.
A loop in the run() method runs ten times while sleeping for one second each time. 
The progress of the progress bar is set to the current iteration number (i) at each iteration of the loop.
