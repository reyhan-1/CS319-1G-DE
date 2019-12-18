package GameLogic;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class HighScoreManager {
    private File highScores;
    private ArrayList<String> names;
    private ArrayList<Integer> scores;
    private String label;
    public HighScoreManager() {
        highScores= new File("C:\\Users\\orkan\\Documents\\GitHub\\CS319-1G-DE\\Defender\\src\\Data\\highscores.txt");
        names = new ArrayList<>();
        scores = new ArrayList<>();
        if( !highScores.exists()){
            try {
                highScores.createNewFile();
            } catch (IOException ioException){}
        }
        try {
            FileReader fr = new FileReader(highScores);
            BufferedReader br = new BufferedReader(fr);
            String line;
            while ((line = br.readLine()) != null) {
                int bar = line.indexOf('|');
                String name = line.substring(0, bar);
                int score = Integer.parseInt(line.substring(bar + 1, line.length()));
                names.add(name);
                scores.add(score);
            }
            sortLists();
            label = setScoresLabel();
        } catch (IOException ioException){}
    }
    public void sortLists(){
        int temp = 0;
        String temp2 = "";
        for(int i = 0; i < scores.size(); i++){
            for(int j = 1; j < (scores.size() - i); j++){
                if(scores.get(j-1)<scores.get(j)){
                    temp2 = names.get(j-1);
                    temp = scores.get(j-1);
                    names.set(j-1, names.get(j));
                    names.set(j, temp2);
                    scores.set(j-1, scores.get(j));
                    scores.set(j, temp);
                }
            }
        }
    }

    public String getLabel(){
        return label;
    }
    private String setScoresLabel(){
        String scoresLabel = "Rank\tName\tScore\n";
        for( int i = 0; i < scores.size(); i++){
            scoresLabel = scoresLabel + (i+1) + "\t\t" + names.get(i) + "\t\t" + scores.get(i) + "\n";
        }
        return scoresLabel;
    }
    public void addScore(String name, int score){
        if( name == ""){
            name = "def";
        }
        if( !highScores.exists()){
            try {
                highScores.createNewFile();
            } catch (IOException ioException){}
        }
        names.add(name);
        scores.add(score);
        sortLists();
    }
}

