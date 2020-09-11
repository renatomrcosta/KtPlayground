# Renato's Kt Playground

A playground to explore and experiment with Kotlin Features


## How to use it

I firmly believe that only through practice one can truly grok a concept or pattern. The suggestion is then to approach each individual topic, understand the code and topic, make assumptions, execute, verify your assumptions, rinse and repeat. 

Each particular step should be a self contained executable, so probably there won't be a large interconnected code base to get in the way of understanding the concepts explored. 

Open each of the topics (described in detail below) and run, alter, explore the code.

## Topics 

Each current topic explored (be it something personal or something broached in one of my online presentations) is separated into its own discrete package:

- coroutines
    - basics: Encompasses the Introduction to Coroutines talk. In it, I approach a breadth of different topics, from the initial composition, up to some error handling and tricky shared mutable state shenanigans
    - channels / flow: packages in which I follow and experiment with the topics presented in the [coroutines by example repo](https://github.com/Kotlin/kotlinx.coroutines/blob/master/docs/coroutines-guide.md)
    - devcop: Encompasses my second presentation on Kotlin Asynchronous data streams (Channels / Flow). Has some neat patterns in there
- runningWithScissors: Package that contains code for my running with scissors talk. Basically, it helps us explore how incovenient and poorly performing spawning workers with a single runnable to read from a queue is, while trying to grok options around the usage of coroutines to do more with less.
- kc2019: Package containing assorted code from different workshops from KotlinConf 2019.
- util: Util code that can be shared among different examples

Also some tests exploring use cases for coroutines also exist using kotest.

# Presentation Slides

If you've attended one of my talks and found your way to this Repo, you can also find the slides linked below:

- Coroutines - A practical approach (English): [LINK](https://drive.google.com/file/d/1-9lAQ8l_BovYUrq6ViMP4zLyZh-ArWCc/view?usp=sharing)
- Coroutines em Prática (Português do Brasil): [LINK](https://docs.google.com/presentation/d/1wmCXPintYTt_QzDHAQ6ri3aYjTc0bbAr2H2kanqAyjY/edit?usp=sharing)
