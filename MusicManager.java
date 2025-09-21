import java.util.*;
import java.io.*;

public class MusicManager {
    static class Song {
        int id;String title, artist, genre; double duration;
        static int counter = 1;
        Song(String t, String a, double d, String g){
            id=counter++; 
            title=t;
            artist=a;
            duration=d;
            genre=g; 
        }
        public String toString(){
            return id+". "+title+" | "+artist+" | "+duration+" mins | "+genre;
        }
    }

    static List<Song> playlist = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args){
        while(true){
            System.out.println("\n1.Add 2.Remove 3.Show 4.Search 5.Save 6.Load 0.Exit");
            int ch = Integer.parseInt(sc.nextLine());
            switch(ch){
                case 1 -> addSong();
                case 2 -> removeSong();
                case 3 -> showPlaylist();
                case 4 -> searchSong();
                case 5 -> savePlaylist();
                case 6 -> loadPlaylist();
                case 0 -> { System.out.println("Bye!"); return; }
                default -> System.out.println("Invalid choice.");
            }
        }
    }

    static void addSong(){
        System.out.print("Title: "); 
        String t=sc.nextLine();
        System.out.print("Artist: "); 
        String a=sc.nextLine();
        System.out.print("Duration: "); 
        double d=Double.parseDouble(sc.nextLine());
        System.out.print("Genre: "); 
        String g=sc.nextLine();
        playlist.add(new Song(t,a,d,g));
        System.out.println("Song added.");
    }

    static void removeSong(){
        System.out.print("ID: ");
        int id=Integer.parseInt(sc.nextLine());
        boolean removed=false;
        for(int i=0;i<playlist.size();i++){
            if(playlist.get(i).id==id){ 
                playlist.remove(i); removed=true;
                break;
            }
        }
        System.out.println(removed ? "Song removed." : "Not found.");
    }

    static void showPlaylist(){
        if(playlist.isEmpty()) 
            System.out.println("Playlist empty.");
        else for(Song s:playlist)
            System.out.println(s);
    }

    static void searchSong(){
        System.out.print("Keyword: "); 
        String k=sc.nextLine().toLowerCase();
        boolean found=false;
        for(Song s:playlist){
            if(s.title.toLowerCase().contains(k) || s.artist.toLowerCase().contains(k)){
                System.out.println(s); 
                found=true;
            }
        }
        if(!found) System.out.println("No match found.");
    }

    static void savePlaylist(){
        System.out.print("Filename: ");
        String f=sc.nextLine();
        try(PrintWriter out=new PrintWriter(f)){
            for(Song s:playlist) out.println(s.id+","+s.title+","+s.artist+","+s.duration+","+s.genre);
            System.out.println("Saved.");
        }catch(Exception e){ 
            System.out.println("Error: "+e);
        }
    }

    static void loadPlaylist(){
        System.out.print("Filename: "); 
        String f=sc.nextLine();
        try(Scanner file=new Scanner(new File(f))){
            playlist.clear();
            Song.counter=1;
            while(file.hasNextLine()){
                String[] p=file.nextLine().split(",");
                playlist.add(new Song(p[1],p[2],Double.parseDouble(p[3]),p[4]));
            }
            System.out.println("Loaded.");
        }catch(Exception e){ 
            System.out.println("Error: "+e);
        }
    }
}
