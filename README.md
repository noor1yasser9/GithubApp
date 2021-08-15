<h1 align="center">Github App</h1>
<h2 align="center">The MVVM arch using Coroutine, Daggar, and Android Jetpack.</h2>
<h1 align = "center"><img src="https://user-images.githubusercontent.com/41232970/129378338-b71bc09f-b383-48e4-bb0f-b82d48c01909.png" width="300px"/></h1>

## Overview 
GithubApp is a sample Android project using Github API based on MVVM architecture. It showcases the app development with well-designed architecture and up-to-date Android tech stacks.

[![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-android--best--practices-brightgreen.svg?style=flat)](https://android-arsenal.com/details/3/4975)  [![kotlin](https://img.shields.io/badge/Kotlin-1.4.xxx-brightgreen.svg)](https://kotlinlang.org/)  [![coroutines](https://img.shields.io/badge/coroutines-asynchronous-red.svg)](https://kotlinlang.org/docs/reference/coroutines-overview.html)  [![Dagger 2](https://img.shields.io/badge/Dagger-2.xx-orange.svg)](https://google.github.io/dagger/)  [![Kotlin-Android-Extensions ](https://img.shields.io/badge/Kotlin--Android--Extensions-plugin-red.svg)](https://kotlinlang.org/docs/tutorials/android-plugin.html) [![MVVM ](https://img.shields.io/badge/Clean--Code-MVVM-brightgreen.svg)](https://github.com/googlesamples/android-architecture)  ![MVP ](https://img.shields.io/badge/Clean--Code-MVP-brightgreen.svg)
[![Build Status](https://app.bitrise.io/app/b7eabce000fac983/status.svg?token=i6oJjdA4ZD4wM6NDA5cB7g&branch=master)](https://app.bitrise.io/app/b7eabce000fac983)![MVVM3](https://user-images.githubusercontent.com/1812129/68319232-446cf900-00be-11ea-92cf-cad817b2af2c.png)

Model-View-ViewModel (ie MVVM) is a template of a client application architecture, proposed by John Gossman as an alternative to MVC and MVP patterns when using Data Binding technology. Its concept is to separate data presentation logic from business logic by moving it into particular class for a clear distinction.
You can also check [**MVP**](https://github.com/ahmedeltaher/Android-MVP-Architecture)

**Why Promoting MVVM VS MVP:**
- ViewModel has Built in LifeCycleOwerness, on the other hand Presenter not, and you have to take this responsiblty in your side.
- ViewModel doesn't have a reference for View, on the other hand Presenter still hold a reference for view, even if you made it as weakreference.
- ViewModel survive configuration changes, while it is your own responsiblities to survive the configuration changes in case of Presenter. (Saving and restoring the UI state)

**MVVM Best Pratice:**
- Avoid references to Views in ViewModels.
- Instead of pushing data to the UI, let the UI observe changes to it.
- Distribute responsibilities, add a domain layer if needed.
- Add a data repository as the single-point entry to your data.
- Expose information about the state of your data using a wrapper or another LiveData.
- Consider edge cases, leaks and how long-running operations can affect the instances in your architecture.
- Don’t put logic in the ViewModel that is critical to saving clean state or related to data. Any call you make from a ViewModel can be the last one.

  ![mvvm2](https://user-images.githubusercontent.com/1812129/68319008-e9d39d00-00bd-11ea-9245-ebedd2a2c067.png)
  
<p align="center"> 
  Visitor count<br>
  <img src="https://profile-counter.glitch.me/GithubAppNoor1yasser9/count.svg" />
</p>
<br/> 

## Screenshot
![ss](https://user-images.githubusercontent.com/41232970/129376139-516fc2d0-1e1e-42a5-98c1-0b468beff18c.png)

## 🕹 Sample Video
<video src="https://user-images.githubusercontent.com/41232970/129381717-6d83574b-90a9-4fbb-a442-dc45fb127bde.mp4"></video>

## ✨ Features Project Android:
- 100% Kotlin
- Dagger Hilt
- MVVM architecture
- Android architecture components
- Navigation Jetpack
- Single activity
- dataBinding
- Coroutines


## 🌟 Features App
- Offline support: app makes use of NetworkBoundResource, which uses database as the single source of truth
- Advanced uses of Room
- MVVM with Android Architecture Components(Room, LiveData, Stateflow, ViewModel)
- Material design.
- Dagger dependency injector


## 📃 Libraries used

### Android Jetpack
* [Lifecycle: Create a UI that automatically responds to lifecycle events.](https://developer.android.com/topic/libraries/architecture/lifecycle)

* [LiveData: Build data objects that notify views when the underlying database changes.](https://developer.android.com/topic/libraries/architecture/livedata)

* [ViewModel: Store UI-related data that isn't destroyed on app rotations. Easily schedule asynchronous tasks for optimal execution.](https://developer.android.com/topic/libraries/architecture/viewmodel)

* [Room: Access your app's SQLite database with in-app objects and compile-time checks.](https://developer.android.com/topic/libraries/architecture/room)

* [Navigation: Handle everything needed for in-app navigation.](https://developer.android.com/topic/libraries/architecture/navigation/)

### Http

* [Retrofit2: Type-safe HTTP client for Android and Java by Square, Inc.](https://github.com/square/retrofit)

* [OkHttp: An HTTP+HTTP/2 client for Android and Java applications.](https://github.com/square/okhttp)

### DI
* [Dagger-hilt: Dependency injection with Hilt](https://developer.android.com/training/dependency-injection/hilt-android)

### Others

* [Glide: An image loading and caching library for Android focused on smooth scrolling](https://github.com/bumptech/glide)

* [Timber: A logger with a small, extensible API which provides utility on top of Android's normal Log class.](https://github.com/JakeWharton/timber)

* [Wang.avi](https://github.com/81813780/AVLoadingIndicatorView)  


## License
    Copyright 2021 Noor Yasser

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.


