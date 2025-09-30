package tile;


import model.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.*;

public class TileManager {

    private final File fa = new File("src/main/java/res/tiles/grass.png");
    private final File fb = new File("src/main/java/res/tiles/wall.png");
    private final File fc = new File("src/main/java/res/tiles/water.png");
    private final File map01 = new File("src/main/java/res/maps/map01.txt");

    GamePanel gp;
    Tile [] tile;
    int mapTileNum[] [];

    public TileManager(GamePanel gp) {
        this.gp = gp;

        tile = new Tile[10];
        mapTileNum = new int [gp.maxScreenCol][gp.maxScreenRow];

        getTileImage();
        loadMap(map01);
    }

    public void getTileImage() {

        try{
            tile[0] =  new Tile();
            tile[0].image = ImageIO.read(fa);
            tile[1] =  new Tile();
            tile[1].image = ImageIO.read(fb);
            tile[2] =  new Tile();
            tile[2].image = ImageIO.read(fc);
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public void loadMap(File map01) {
        try{
                InputStream is = new FileInputStream(this.map01);
                InputStreamReader isr = new InputStreamReader(is);
                BufferedReader br = new BufferedReader(isr);

                int col = 0;
                int row = 0;

                while(col < gp.maxScreenCol && row < gp.maxScreenRow) {

                    String line = br.readLine();

                    while(col < gp.maxScreenCol) {
                        String numbens[] = line.split(" ");
                        int num = Integer.parseInt(numbens[col]);

                        mapTileNum[col][row] = num;
                        col++;
                    }
                    if(col == gp.maxScreenCol) {
                        col = 0;
                        row++;
                    }
                }
                br.close();
        }catch (Exception e){

        }
    }

    public void draw(Graphics2D g2) {

        int col = 0;
        int row = 0;
        int x = 0;
        int y = 0;

        while (col < gp.maxScreenCol && row < gp.maxScreenRow) {

            int tileNum = mapTileNum[col] [row];

            g2.drawImage(tile[tileNum].image , x, y, gp.tileSize, gp.tileSize, null);
            col++;
            x += gp.tileSize;

            if(col >= gp.maxScreenCol) {
                col = 0;
                x = 0;
                row++;
                y += gp.tileSize;
            }
        }

    }
}
