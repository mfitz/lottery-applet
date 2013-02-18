Lottery Applet
===============

An animated Java applet to randomly pick 6 lottery numbers between 1 and 49. I wrote the applet itself back in 2001, but I have recently wrapped it in a Maven project. 

![Lottery applet screenshot](/src/main/resources/images/lottery-applet.png "Lottery applet screenshot")

![Lottery applet screenshot 2](/src/main/resources/images/lottery-applet-dropping.png "Lottery applet screenshot 2")

Building
===========

Uses Apache Maven (http://maven.apache.org/). To compile and build a distro in zip format,
use:

    mvn clean assembly:assembly 
    
Running the applet
============

Having built a distro, you can unpack the subsequent zip file to a local directory. There you will see a new subdirectory called something like "lottery-applet-1.0-SNAPSHOT". Inside this directory you will find a jar file, some GIF files (49 in total - 1 for each lottery ball), and an HTML file. You can run the applet locally using the JDK's appletviewer tool:

    appletviewer lottery-applet.html

Alternatively, you could open lottery-applet.html in a java-enabled browser. As with all Java applets, your particular browser and JRE combination may be different from those on which I have tested this method of running the applet, and that difference may cause some form of fail. But hey - that's applets for you. I did write this back in 2001, so YMMV. There's a reason applets died out, right?

You could also drop the 3 files into the same directory of a web server and serve the applet up over HTTP, if you fancy. I've done that on my personal website at http://www.michaelfitzmaurice.com/LotteryApplet2.html 

Running the applet in an IDE
============

Everything the applet needs at runtime is in the same directory when you build and unpack the distro. However, this is not the case when launching the applet inside your IDE. The lottery ball image files are in a different directory from the applet's runtime codebase, so we need a way to tell the applet where to find them. For this reason, I added an applet parameter ("image-path"). You should use a relative path for this parameter; in Eclipse, I configured it thus (note the trailing slash character):

    ../../src/main/resources/images/
    
![Lottery applet image-path param](/src/main/resources/images/lottery-image-path-eclipse.png "Lottery applet image-path")    

I also set the width to 400 pixels and the height to 300. Note that the minimum workable width is 300 pixels (each ball image is 50 pixels and there are six).

