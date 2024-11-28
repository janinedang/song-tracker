# My Personal Project

## Project Description
*What will the application do? Who will use it?*

The project I am proposing to design is a music rating application. In this application, you can add songs to your playlist, rate each song on a scale out of five, and leave reviews on the songs. This application is for people who enjoy listening to music and want to keep track of the songs they listen to. Whether you’re a casual listener or a seasoned music critic, anyone can use this program!

*Why is this project of interest to you?*

This project is of interest to me because I have always enjoyed music. I listen to music all the time; when I take walks, do homework, and sometimes I dedicate time just to listen to music. Because I love listening to music, having an application where I can record my opinions of songs will make it easier to keep track of the songs I listen to. 

## User Stories
- As a user, I want to be able to add a song to my playlist.
- As a user, I want to be able to view the list of song titles in my playlist.
- As a user, I want to be able to remove a song from my playlist.
- As a user, I want to be able to choose a song from my playlist and give it a rating out of 5.
- As a user, I want to be able to give a song a review.
- As a user, when I select the quit option from the application menu, I want to be reminded to save my playlist to file and have the option to do so or not.
- As a user, when I start the application, I want to be given the option to load my playlist from file.

## Instructions for End User

- You can generate the required action "adding multiple songs to a playlist" by clicking on the "Add" button at the bottom of the window.
- You can generate the first required action related to the user story "adding multiple songs to a playlist" (removing songs from a playlist) by first clicking on a song to select it, then clicking on the "Remove" button at the bottom of the window.
- You can generate the second required action related to the user story "adding multiple Xs from a Y" (rating a song on a playlist) by first clicking on a song to select it, then clicking on the "Rate" button at the bottom of the window.
- You can generate the third required action related to the user story "adding multiple Xs from a Y" (reviewing a song on a playlist) by first clicking on a song to select it, then clicking on the "Review" button at the bottom of the window.
- You can locate my visual component by first creating/loading a playlist to get to the main playlist page, where a PNG of my application's logo is located at the top right.
- You can save the state of my application by clicking on the "Quit" button at the bottom of the window, which will then prompt you to either save or not save your playlist before exiting the application.
- You can reload the state of my application by starting my application, which will prompt you to either load the state of the application or start new.

## Phase 4: Task 2

Thu Nov 28 05:13:59 PST 2024
Creep by Radiohead was added to the playlist.

Thu Nov 28 05:14:08 PST 2024
Creep by Radiohead was rated 4.

Thu Nov 28 05:14:19 PST 2024
Creep by Radiohead was given the review: 'I am a creep... I am a weirdo...'.

Thu Nov 28 05:15:03 PST 2024
Creep by Radiohead was removed from the playlist.

## Phase 4: Task 3
*Changes I would make*
I would take the private classes (AddAction, RemoveAction, RateAction, and ReviewAction) from inside the PlaylistGUI class and create completely new classes for each of these classes. This change would make it easier to edit the functionality of these classes as these classes would be more accessible this way. 

Additionally, I would make each of these classes all extend the same abstract class, as there is a lot of repeated code across these classes. This would result in less code duplication and improve coupling.

I would also create a new Button class that creates a new JButton with a given name and a given ActionListener, as all of my buttons require a specific ActionListener. This would reduce the repetition of creating new buttons and adding ActionListeners in my code.

Another change I would make is to move the quit button from the PlaylistGUI class to the PlaylistAppGUI, as the quit button is the reason that PlaylistGUI has a field of JsonWriter. This change would make it so that PlaylistAppGUI has fields for both JsonReader and JsonWriter, and would make it so that I can deal with the code for saving and loading files in one place.

