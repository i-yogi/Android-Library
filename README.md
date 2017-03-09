# Android-Library

Android library to fetch top 20 news articles using HackerNews API (https://github.com/HackerNews/API).
For Android 4.4 KitKat(19) and up.

Instructions to import library (TopHackerNews.aar):

Step 1: In Android project (min 4.4) 
        Click File -> New -> New Module...
Step 2: Select 'Import .JAR/ .AAR Package' and
        Click 'Next'
Step 3: Select the 'TopHackerNews.aar' using File Finder and
        Click 'Finish'
Step 4: Open 'Gradle Scripts -> build.gradle (Module: app)' and
        add 'compile project(":TopHackerNews")' in dependencies
        ex.
        
        dependencies {
        
          compile project(":TopHackerNews")
        }
Step 5: Sync Project


Instructions to Instanciate and Use Library

Step 1: Inside onCreate()
        Instanciate the library by creating a object,
        _> TopHackerNews object = new TopHackerNews(this);
Step 2: Initialize using
        _> object.initSDK();
Step 3: Display Top 20 news article using Hacker New API
        _> object.showHAckerNews();
