package sample;

public class Shop{

    private PlantTags[] currentlyAvailablePlants;

    public Shop(int level){

        if (level == 1){
            currentlyAvailablePlants = new PlantTags[1];
            currentlyAvailablePlants[0] = new PlantTags("PeaShooter");
        }
        if(level==2){
            currentlyAvailablePlants = new PlantTags[2];
            currentlyAvailablePlants[0] = new PlantTags("PeaShooter");
            currentlyAvailablePlants[1] = new PlantTags("SunFlower");
        }
        if(level==3){
            currentlyAvailablePlants = new PlantTags[3];
            currentlyAvailablePlants[0] = new PlantTags("PeaShooter");
            currentlyAvailablePlants[1] = new PlantTags("SunFlower");
            currentlyAvailablePlants[2] = new PlantTags("CherryBomb");
        }if(level==4 || level == 5){
            currentlyAvailablePlants = new PlantTags[4];
            currentlyAvailablePlants[0] = new PlantTags("PeaShooter");
            currentlyAvailablePlants[1] = new PlantTags("SunFlower");
            currentlyAvailablePlants[2] = new PlantTags("CherryBomb");
            currentlyAvailablePlants[3] = new PlantTags("Stone");
        }
    }

    public PlantTags getPlant(int index){
        return currentlyAvailablePlants[index];
    }

    public class PlantTags{
        String name;
        boolean available = true;

        PlantTags(String name){
            this.name = name;
        }

        public boolean getAvailabilityStatus(){
            return available;
        }

        public void changeAvailabilityStatus(){
            available = !available;
        }
    }
}
