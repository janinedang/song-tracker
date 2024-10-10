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
            command = input.nextLine();
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

        System.out.println("\n--------------------------------------");
        System.out.println("Welcome to the Playlist Application!");
        System.out.println("Please create a name for your playlist.");
        String name = input.nextLine();
        playlist = new Playlist(name);
    }

    // EFFECTS: displays menu of available inputs to user
    private void displayMenu() {
        System.out.println("\n--------------------------------------");
        System.out.println("\nSelect one of the following options:");
        System.out.println("\tview: view all songs in " + playlist.getName());
        System.out.println("\tadd: add a new song to " + playlist.getName());
        System.out.println("\tremove: remove a song from " + playlist.getName());
        System.out.println("\trate: rate a song in " + playlist.getName());
        System.out.println("\treview: review a song in " + playlist.getName());
        System.out.println("\tquit: quit the application");
    }

    // EFFECTS: prints current songs in playlist with its title, artist, genre,
    // rating, and review in order of addition
    private void doViewSongs() {
        if (playlist.getPlaylist().size() == 0) {
            System.out.println("\nThere are currently no songs to view in " + playlist.getName());
        } else {
            System.out.println("\nSongs in " + playlist.getName() + ":");

            for (Song nextSong : playlist.getPlaylist()) {
                String songTitle = nextSong.getTitle();
                String songArtist = nextSong.getArtist();
                String songGenre = nextSong.getGenre();
                int songRating = nextSong.getRating();
                String songReview = nextSong.getReview();

                System.out.println("\n" + songTitle + " by " + songArtist);
                System.out.println("\tGenre: " + songGenre);
                System.out.println("\tRating: " + songRating);
                System.out.println("\tReview: " + songReview);
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: prompts user to add a song with a title, artist, and genre
    private void doAddSong() {
        String addTitle = askSongTitle();
        String addArtist = askSongArtist();
        String addGenre = askSongGenre();

        if (!playlist.inPlaylist(addTitle, addArtist)) {
            playlist.addSong(addTitle, addArtist, addGenre);
            System.out.println("\n" + addTitle + " by " + addArtist + "; Genre: "
                    + addGenre + " was added to " + playlist.getName());
        } else {
            System.out.println("\n" + addTitle + " by " + addArtist + " is already in " + playlist.getName());
        }
    }

    // MODIFIES: this
    // EFFECTS: prompts user to choose a song to remove from playlist and removes it
    private void doRemoveSong() {
        if (playlist.getPlaylist().size() == 0) {
            System.out.println("\nThere are currently no songs to remove in " + playlist.getName());
        } else {
            String removeTitle = askSongTitle();
            String removeArtist = askSongArtist();

            if (playlist.inPlaylist(removeTitle, removeArtist)) {
                playlist.removeSong(removeTitle, removeArtist);
                System.out.println(
                        "\n" + removeTitle + " by " + removeArtist + " was removed from " + playlist.getName());
            } else {
                System.out.println("\n" + removeTitle + " by " + removeArtist + " is not in " + playlist.getName());
            }
        }
    }

    // REQUIRES: user input for rating must be an integer
    // MODIFIES: this
    // EFFECTS: prompts user to rate the chosen song from 1-5
    private void doRateSong() {
        if (playlist.getPlaylist().size() == 0) {
            System.out.println("\nThere are currently no songs to rate in " + playlist.getName());
        } else {
            String rateTitle = askSongTitle();
            String rateArtist = askSongArtist();

            if (playlist.inPlaylist(rateTitle, rateArtist)) {
                System.out.println("\nGive " + rateTitle + " by " + rateArtist + " a rating from 1-5:");
                int rating = input.nextInt();
                input.nextLine();

                if (rating >= 1 && rating <= 5) {
                    playlist.rateSong(rateTitle, rateArtist, rating);
                    System.out.println("\n" + rateTitle + " by " + rateArtist + " was given a rating of " + rating);
                } else {
                    System.out.println("\n" + rating + " is not a valid rating");
                    System.out.println("Only ratings from 1-5 are accepted");
                }
            } else {
                System.out.println("\n" + rateTitle + " by " + rateArtist + " is not in " + playlist.getName());
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: prompts user to give a review of the chosen song using 1-150
    // characters, if review length not from 1-150, review is not accepted
    private void doReviewSong() {
        if (playlist.getPlaylist().size() == 0) {
            System.out.println("\nThere are currently no songs to review in " + playlist.getName());
        } else {
            String reviewTitle = askSongTitle();
            String reviewArtist = askSongArtist();

            if (playlist.inPlaylist(reviewTitle, reviewArtist)) {
                System.out.println("\nGive " + reviewTitle + " by " + reviewArtist + " a review (1-150 characters):");
                String review = input.nextLine();

                if (review.length() >= 1 && review.length() <= 150) {
                    playlist.reviewSong(reviewTitle, reviewArtist, review);
                    System.out.println("\n" + reviewTitle + " by " + reviewArtist + " was given the review:");
                    System.out.println("\t" + review);
                } else {
                    System.out.println("\nYour review has " + review.length() + " characters");
                    System.out.println("Only reviews within 1-150 characters are accepted.");
                }
            } else {
                System.out.println("\n" + reviewTitle + " by " + reviewArtist + " is not in " + playlist.getName());
            }
        }
    }

    // EFFECTS: prompts user for a song title, if song title has a length of zero
    // the user will be prompted until given a title with length > 0 and
    // returns the given title
    private String askSongTitle() {
        String askTitle = "";
        boolean lengthZero = true;

        while (lengthZero) {
            System.out.println("\nWhat is the title of the song?");
            askTitle = input.nextLine();

            if (askTitle.length() > 0) {
                lengthZero = false;
            } else {
                System.out.println("\nPlease enter a valid song title");
            }
        }
        return askTitle;
    }

    // EFFECTS: prompts user for an artist name, if artist has a length of zero
    // the user will be prompted until given an artist with length > 0 and
    // returns the given artist name
    private String askSongArtist() {
        String askArtist = "";
        boolean lengthZero = true;

        while (lengthZero) {
            System.out.println("\nWho is the artist?");
            askArtist = input.nextLine();

            if (askArtist.length() > 0) {
                lengthZero = false;
            } else {
                System.out.println("\nPlease enter a valid artist name");
            }
        }
        return askArtist;
    }

    // EFFECTS: prompts user for a genre, if genre has a length of zero
    // the user will be prompted until given a genre with length > 0 and
    // returns the given genre
    private String askSongGenre() {
        String askGenre = "";
        boolean lengthZero = true;

        while (lengthZero) {
            System.out.println("\nWhat is the genre?");
            askGenre = input.nextLine();

            if (askGenre.length() > 0) {
                lengthZero = false;
            } else {
                System.out.println("\nPlease enter a valid genre");
            }
        }
        return askGenre;
    }
}