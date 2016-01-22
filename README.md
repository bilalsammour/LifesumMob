Lifesum - Lifesum Mob - Android
==========================


**Lifesum Mob** is a challenge app for Lifesum that that does search of food with a given API, all data in the app must be manage by any ORM.

----------


Building Instructions
------------------------
Start Android Studio and close any open Android Studio projects. From the Android Studio menu select File > New > Import Project. Alternatively, from the Welcome screen, select Import project (Eclipse ADT, Gradle, etc.). Select the Eclipse ADT project folder with the AndroidManifest.xml file and click Ok.


Packages
-----------
 - Activities - Android activities.
 - Fragments - Android fragments.
 - Adapters - Android adapters that extend `BaseListAdapter` which contains a lot of operations used in any adapter.
 - Utils - Static classes like parser.
 - Models - Thin models and loders represent M in [MVC][1]. The following are some main classes:
  - LoaderRequestManager - A manager to handle all shared [Retrofit][2] processes. It is a [generic class][3].
  - BaseAdapterLoader - The base loader for any loader that used to retrieve data list that will shown in an adapter It is a [generic class][3].
  - CategoriesLoader - The end loader that gets the data from the given API and fills the adapter.


External Libraries
---------------------

 - [Retrofit][2]
 - [GSON][4]


Design
--------

> I am building an application and designing its classes because I am software engineer, but I am not a graphic designer so I used a simple material design.


  [1]: https://en.wikipedia.org/wiki/Model%E2%80%93view%E2%80%93controller "MVC"
  [2]: https://github.com/square/retrofit "Retrofit"
  [3]: https://en.wikipedia.org/wiki/Generics_in_Java "Generics in Java"
  [4]: https://github.com/google/gson "GSON"
