import blocks.Entity;
public class gameLoop {
    Entity[][] table;
    public void Setup(int mapSize){
        table = new Entity[mapSize][mapSize];

        switch(mapSize){
            case 0:

                break;
            case 1:


                break;
            default:
                throw new RuntimeException("No Proper Selection");
        }
    }


    public void AdvanceFrame(){

    }
}
