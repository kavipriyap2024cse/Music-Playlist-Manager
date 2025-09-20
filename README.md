# Music-Playlist-Manager

**DESCRIPTION**

The Music Playlist Manager is a simple Java-based project that allows users to create, organize, and manage their personal music playlists. 
It demonstrates the use of Object-Oriented Programming (OOP) concepts such as classes, objects, inheritance, and polymorphism, along with file handling and basic data structures.

**FEATURES**

Add, remove, and update songs in a playlist

Create and manage multiple playlists

Search songs by title, artist, or genre

Play songs sequentially (next/previous/all) or in shuffle mode

Sort songs by title, artist, or duration

Save and load playlists using file handling

**Tech Stack**

1.Language: Java
2.Frontened:Console-based (Java) / JavaFX
3.Backened: Java classes and modules
4.Database: SQL

**OOPS concept**
Encapsulation

Wrapping data (variables) and methods (functions) into a single unit (class).
Protects data by providing controlled access using getters/setters.
Example: In a Song class, attributes like title and artist are private, and accessed via methods.

Abstraction

Hiding internal details and showing only essential features.
Achieved using abstract classes and interfaces in Java.
Example: A Player interface may have methods like play() and pause(), but the internal logic is hidden.

Inheritance

Mechanism of acquiring properties and methods of one class into another.
Promotes code reusability.
Example: A Playlist class can inherit from a generic LibraryItem class.

Polymorphism

Ability of one function or object to take many forms.
Two types:
Compile-time (Overloading) – Same method name with different parameters.
Runtime (Overriding) – Subclass provides its own implementation of a method.
Example: play() method behaves differently for a single song vs. a playlist.
