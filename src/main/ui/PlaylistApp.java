package ui;

import java.util.Scanner;

import model.Playlist;
import model.Song;

// Playlist application
// References the TellerApp
public class PlaylistApp {
    private Playlist playlist;
    private Scanner input;

    // EFFECTS: runs the Playlist application
    public PlaylistApp() {
        runPlaylist();
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    private void runPlaylist() {
        boolean keepGoing = true;
        String command = null;

        initPlaylist();

        while (keepGoing) {
            displayMenu();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("quit")) {
                keepGoing = false;
            } else {
                processCommand(command);
            }
        }

        System.out.println("\nThank you for using the Playlist Application! Goodbye!");
    }

    // MODIFIES: this
    // EFFECTS: processes input from user
    private void processCommand(String command) {
        if (command.equals("view")) {
            doViewSongs();
        } else if (command.equals("add")) {
            doAddSong();
        } else if (command.equals("remove")) {
            doRemoveSong();
        } else if (command.equals("rate")) {
            doRateSong();
        } else if (command.equals("review")) {
            doReviewSong();
        } else {
            System.out.println("Input not valid, please try again.");
        }
    }

    // MODIFIES: this
    // EFFECTS: prompts user to input a name for the playlist
    // and creates a new empty playlist with the given name
    private void initPlaylist() {
        input = new Scanner(System.in);

        System.out.println("Welcome to the Playlist Application!");
        System.out.println("Please create a name for your playlist.");
        String name = input.nextLine();
        playlist = new Playlist(name);
    }

    // EFFECTS: displays menu of available inputs to user
    private void displayMenu() {
        System.out.println("\nSelect one of the following options:");
        System.out.println("\tview: view all songs in " + playlist.getName());
        System.out.println("\tadd: add a new song to " + playlist.getName());
        System.out.println("\tremove: remove a song from " + playlist.getName());
        System.out.println("\trate: rate a song in " + playlist.getName());
        System.out.println("\treview: review a song in " + playlist.getName());
        System.out.println("\tquit: quit the application");
    }

    // EFFECTS: prints current songs in playlist in order of addition
    private void doViewSongs() {
        if (playlist.getPlaylist().size() == 0) {
            System.out.println("\nThere are currently no songs in " + playlist.getName());
        } else {
            System.out.println("\nSongs in " + playlist.getName() + ":");
            for (Song nextSong : playlist.getPlaylist()) {
                String songName = nextSong.getTitle();
                String songArtist = nextSong.getArtist();
                String songGenre = nextSong.getGenre();

                System.out.println(songName + " by " + songArtist + "; Genre: " + songGenre);
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: prompts user to add a song with a title, artist, and genre
    private void doAddSong() {
        System.out.println("\nWhat is the title of the song you would like to add?");
        String addName = input.nextLine();

        System.out.println("\nWho is the artist that created this song?");
        String addArtist = input.nextLine();

        System.out.println("\nWhat is the genre of this song?");
        String addGenre = input.nextLine();

        if (!playlist.inPlaylist(addName, addArtist)) {
            playlist.addSong(addName, addArtist, addGenre);
            System.out.println("\n" + addName + " by " + addArtist + "; Genre: "
                    + addGenre + " was added to " + playlist.getName());
        } else {
            System.out.println("\n" + addName + " by " + addArtist + " is already in " + playlist.getName());
        }
    }

    // MODIFIES: this
    // EFFECTS: prompts user to choose a song to remove from playlist and removes it
    private void doRemoveSong() {
        System.out.println("\nWhat is the title of the song you would like to remove?");
        String removeName = input.nextLine();

        System.out.println("\nWho is the artist that created this song?");
        String removeArtist = input.nextLine();

        if (playlist.inPlaylist(removeName, removeArtist)) {
            playlist.removeSong(removeName, removeArtist);
            System.out.println("\n" + removeName + " by " + removeArtist + " was removed from " + playlist.getName());
        } else {
            System.out.println("\n" + removeName + " by " + removeArtist + " is not in " + playlist.getName());
        }
    }

    // REQUIRES: user input for rating must be an integer
    // MODIFIES: this
    // EFFECTS: prompts user to rate the chosen song from 1-5
    private void doRateSong() {
        System.out.println("\nWhat is the title of the song you would like to rate?");
        String rateTitle = input.nextLine();

        System.out.println("\nWho is the artist that created this song?");
        String rateArtist = input.nextLine();

        if (playlist.inPlaylist(rateTitle, rateArtist)) {
            System.out.println("\nGive " + rateTitle + " by " + rateArtist + " a rating from 1-5:");
            int rating = input.nextInt();
            if (rating >= 1 && rating <= 5) {
                playlist.rateSong(rateTitle, rateArtist, rating);
                System.out.println("\n" + rateTitle + " by " + rateArtist + " was given a rating of " + rating);
            } else {
                System.out.println("\n" + rating + " is not an accepted rating");
            }
        } else {
            System.out.println("\n" + rateTitle + " by " + rateArtist + " is not in " + playlist.getName());
        }
    }

    // MODIFIES: this
    // EFFECTS: prompts user to give a review of the chosen song
    // using 1-150 characters
    private void doReviewSong() {
        System.out.println("\nWhat is the title of the song you would like to review?");
        String reviewTitle = input.nextLine();

        System.out.println("\nWho is the artist that created this song?");
        String reviewArtist = input.nextLine();

        if (playlist.inPlaylist(reviewTitle, reviewArtist)) {
            System.out.println("\nGive " + reviewTitle + " by " + reviewArtist + " a review (1-150 characters):");
            String review = input.nextLine();
            if (review.length() >= 1 && review.length() <= 150) {
                playlist.reviewSong(reviewTitle, reviewArtist, review);
                System.out.println("\n" + reviewTitle + " by " + reviewArtist + " was rated" + playlist.getName());
            } else {
                System.out.println("\nYour review has " + review.length() + " characters");
                System.out.println("\nOnly reviews within 1-150 characters are accepted.");
            }
        } else {
            System.out.println("\n" + reviewTitle + " by " + reviewArtist + " is not in " + playlist.getName());
        }
    }
}