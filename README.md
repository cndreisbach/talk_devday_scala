Scala: A Modern Language
========================

This repo contains a talk I gave at Developer Day in Durham, NC. I tried to make it a good introduction to what Scala is and why you might use it. Even more fun, I wrote my slides in Markdown and generated them with my own Scala slideshow-generation application. Check out the included code for the slideshow generator. It could use more work to add new themes, but I'm especially proud of how it loads and presents external code. This allowed me to compile and test all code presented in the application.

To compile this application and generate the talk, you'll need [Buildr](http://buildr.apache.org/), which has [installation instructions on its site](http://buildr.apache.org/installing.html). If you already have Ruby on your system, you should be able to run `gem install buildr` and have everything work. Once you have Buildr set up, a simple `buildr` command in the repo main directory should get you going.