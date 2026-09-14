package com.anlai.superjumper;
import com.badlogic.gdx.Preferences;//简单的额小数据库
import com.badlogic.gdx.Gdx;

public class SaveManager {
    private Preferences prefs;
    private int highScore = 0;
    private int bestTime = 99999;
    public SaveManager() {
        prefs = Gdx.app.getPreferences("HighScore");
        load();
    }
        private void load(){
            highScore = prefs.getInteger("score",0);
            bestTime = prefs.getInteger("time",999999);
        }

        public void checkScore(int score,int time){
            if(score > highScore){
                highScore = score;
                bestTime = time;
                save();
            }
            else if(score == highScore){
                if(time < bestTime){
                    bestTime = time;
                    save();
                }
            }
        }
        private void save(){
            prefs.putInteger("score",highScore);

            prefs.putInteger("time",bestTime);

            prefs.flush();
        }
        public int getHighScore(){

            return highScore;
        }
        public int getBestTime(){

            return bestTime;
        }
    }


