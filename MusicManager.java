import java.util.*;
import java.io.*;

// Single-file Music Playlist Manager
public class MusicManager {
    private static int songIdCounter = 1;

    // Inner class representing a Song
    static class Song {
        private int songId;
        private String title;
        private String artist;
        private double duration; // minutes
        private String genre;

        public Song(String title, String artist, double duration, String genre) {
            this.songId = songIdCounter++;
            this.title = title;
            this.artist = artist;
            this.duration = duration;
            this.genre = genre;
        }

        // Getters and setters
        public int getSongId() { return songId; }
        public String getTitle() { return title; }
        public String getArtist() { return artist; }
        public double getDuration() { return duration; }
        public String getGenre() { return genre; }

        public void setTitle(String title) { this.title = title; }
        public void setArtist(String artist) { this.artist = artist; }
        public void setDuration(double duration) { this.duration = duration; }
        public void setGenre(String genre) { this.genre = genre; }

        @Override
        public String toString() {
            return songId + ". " + title + " | " + artist + " | " + duration + " mins | " + genre;
        }
    }

    // Playlist (list of songs)
    private static List<Song> playlist = new ArrayList<>();
    private static Scanner sc = new Scanner(System.in);
    private static int currentIndex = -1;

    public static void main(String[] args) {
        boolean running = true;
        System.out.println("🎵 Welcome to Music Playlist Manager 🎵");

        while (running) {
            System.out.println("\n--- MENU ---");
            System.out.println("1. Add Song");
            System.out.println("2. Remove Song");
            System.out.println("3. Show Playlist");
            System.out.println("4. Shuffle Playlist");
            System.out.println("5. Search Song (title/artist)");
            System.out.println("6. Sort Playlist");
            System.out.println("7. Play Songs");
            System.out.println("8. Update Song");
            System.out.println("9. Save Playlist");
            System.out.println("10. Load Playlist");
            System.out.println("0. Exit");
            System.out.print("Choose: ");

            int choice = Integer.parseInt(sc.nextLine());

            switch (choice) {
                case 1: addSong(); break;
                case 2: removeSong(); break;
                case 3: showPlaylist(); break;
                case 4: shufflePlaylist(); break;
                case 5: searchSong(); break;
                case 6: sortPlaylist(); break;
                case 7: playSongs(); break;
                case 8: updateSong(); break;
                case 9: savePlaylist(); break;
                case 10: loadPlaylist(); break;
                case 0: running = false; System.out.println("Exiting..."); break;
                default: System.out.println("Invalid choice.");
            }
        }
    }

    private static void addSong() {
        System.out.print("Title: "); String title = sc.nextLine();
        System.out.print("Artist: "); String artist = sc.nextLine();
        System.out.print("Duration (minutes): "); double duration = Double.parseDouble(sc.nextLine());
        System.out.print("Genre: "); String genre = sc.nextLine();
        playlist.add(new Song(title, artist, duration, genre));
        System.out.println("Song added!");
    }

    private static void removeSong() {
        System.out.print("Enter Song ID to remove: ");
        int id = Integer.parseInt(sc.nextLine());
        boolean removed = playlist.removeIf(s -> s.getSongId() == id);
        System.out.println(removed ? "Song removed." : "Song not found.");
    }

    private static void showPlaylist() {
        if (playlist.isEmpty()) System.out.println("Playlist is empty.");
        else playlist.forEach(System.out::println);
    }

    private static void shufflePlaylist() {
        Collections.shuffle(playlist);
        System.out.println("Playlist shuffled.");
    }

    private static void searchSong() {
        System.out.print("Enter title or artist to search: ");
        String keyword = sc.nextLine().toLowerCase();
        boolean found = false;
        for (Song s : playlist) {
            if (s.getTitle().toLowerCase().contains(keyword) || s.getArtist().toLowerCase().contains(keyword)) {
                System.out.println(s);
                found = true;
            }
        }
        if (!found) System.out.println("No match found.");
    }

    private static void sortPlaylist() {
        System.out.println("Sort by: 1. Title  2. Artist  3. Duration");
        int option = Integer.parseInt(sc.nextLine());
        switch (option) {
            case 1: playlist.sort(Comparator.comparing(Song::getTitle)); break;
            case 2: playlist.sort(Comparator.comparing(Song::getArtist)); break;
            case 3: playlist.sort(Comparator.comparingDouble(Song::getDuration)); break;
            default: System.out.println("Invalid option."); return;
        }
        System.out.println("Playlist sorted.");
    }

    private static void playSongs() {
        if (playlist.isEmpty()) { System.out.println("Playlist is empty."); return; }
        System.out.println("1. Play Next  2. Play Previous  3. Play All");
        int option = Integer.parseInt(sc.nextLine());
        switch (option) {
            case 1:
                currentIndex = (currentIndex + 1) % playlist.size();
                System.out.println("Playing: " + playlist.get(currentIndex));
                break;
            case 2:
                currentIndex = (currentIndex - 1 + playlist.size()) % playlist.size();
                System.out.println("Playing: " + playlist.get(currentIndex));
                break;
            case 3:
                for (Song s : playlist) System.out.println("Playing: " + s);
                break;
            default: System.out.println("Invalid option.");
        }
    }

    private static void updateSong() {
        System.out.print("Enter Song ID to update: ");
        int id = Integer.parseInt(sc.nextLine());
        for (Song s : playlist) {
            if (s.getSongId() == id) {
                System.out.print("New Title: "); s.setTitle(sc.nextLine());
                System.out.print("New Artist: "); s.setArtist(sc.nextLine());
                System.out.print("New Duration: "); s.setDuration(Double.parseDouble(sc.nextLine()));
                System.out.print("New Genre: "); s.setGenre(sc.nextLine());
                System.out.println("Song updated!");
                return;
            }
        }
        System.out.println("Song not found.");
    }

    private static void savePlaylist() {
        System.out.print("Enter filename to save: ");
        String filename = sc.nextLine();
        try (PrintWriter out = new PrintWriter(filename)) {
            for (Song s : playlist) {
                out.println(s.getSongId() + "," + s.getTitle() + "," + s.getArtist() + "," + s.getDuration() + "," + s.getGenre());
            }
            System.out.println("Playlist saved to " + filename);
        } catch (Exception e) {
            System.out.println("Error saving file: " + e.getMessage());
        }
    }

    private static void loadPlaylist() {
        System.out.print("Enter filename to load: ");
        String filename = sc.nextLine();
        try (Scanner fileScanner = new Scanner(new File(filename))) {
            playlist.clear();
            songIdCounter = 1;
            while (fileScanner.hasNextLine()) {
                String[] parts = fileScanner.nextLine().split(",");
                if (parts.length == 5) {
                    Song s = new Song(parts[1], parts[2], Double.parseDouble(parts[3]), parts[4]);
                    // manually set songId to maintain sequence
                    playlist.add(s);
                }
            }
            System.out.println("Playlist loaded from " + filename);
        } catch (Exception e) {
            System.out.println("Error loading file: " + e.getMessage());
        }
    }
}
